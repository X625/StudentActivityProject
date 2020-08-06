package edu.txstate.cs.service;

import static edu.txstate.cs.helper.UserSpecification.belongsToDepartment;
import static edu.txstate.cs.helper.UserSpecification.hasFirstname;
import static edu.txstate.cs.helper.UserSpecification.hasLastname;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.springframework.data.jpa.domain.Specification.where;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import edu.txstate.cs.exception.UserRegisterationException;
import edu.txstate.cs.helper.Util;
import edu.txstate.cs.model.bto.MealBTO;
import edu.txstate.cs.model.bto.TicketBTO;
import edu.txstate.cs.model.bto.UserBTO;
import edu.txstate.cs.model.dto.PasswordResetToken;
import edu.txstate.cs.model.dto.User;
import edu.txstate.cs.model.embeddable.Meal;
import edu.txstate.cs.model.enums.Candidate;
import edu.txstate.cs.model.enums.TicketType;
import edu.txstate.cs.repository.PasswordTokenRepository;
import edu.txstate.cs.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	PasswordTokenRepository passwordTokenRepository;
	
	@Autowired
	private EmailService emailService;
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	public List<UserBTO> searchPeople(String deptName, String fname, String lname){
		
		return userRepo.findAll(where(hasFirstname(fname))
					.and(hasLastname(lname))
					.and(belongsToDepartment(deptName)))
					.stream()
					.map(Util::mapToUserBTO)
					.collect(toList());
	}
	
	
	
	public Set<MealBTO> getAllUserMealPlans(){
		User user = getLoggedInUser();
		return user.getMeals()
					.stream()
					.map(m -> new MealBTO(m.getName(), m.getPrice()))
					.collect(toSet());
	}
	
	public boolean purchaseMealPlan(MealBTO bto) {
		User user = getLoggedInUser();
		Meal meal = Util.mapToMeal(bto);
		boolean result = user.getMeals().add(meal);
		userRepo.save(user);
		return result;
	}
	
	
	public void purchaseTickets(Map<TicketType, Integer> tickets) {
		User user = getLoggedInUser();
		for(TicketType key: tickets.keySet()) {
			user.addTicket(key, tickets.get(key));
		}
		userRepo.save(user);
	}
	
	public Pair<Integer, List<TicketBTO>>  getUserTickets(){
		User user = getLoggedInUser();
		Map<TicketType, Integer> tickets = user.getTickets();
		if(tickets != null) {
			List<TicketBTO> list = tickets.keySet().stream().map(k -> new TicketBTO(k, tickets.get(k))).collect(toList());
			int total = list.stream().mapToInt(bto -> bto.getType().getPrice() * bto.getQuantity()).sum();
			return new ImmutablePair<Integer, List<TicketBTO>>(total, list);
		}
		return null;
	}



	public boolean hasVoted() {
		User user = getLoggedInUser();
		return user.hasVoted();
	}


	public Map<Candidate, Long> getVoteResults() {
		 return userRepo.findAll().stream()
				 .filter(p -> p.hasVoted())
				 .collect(groupingBy(User::getVote, counting()));
	}


	public void vote(Candidate candidate) {
		User user = getLoggedInUser();
		user.setVote(candidate);
		userRepo.save(user);
	}



	public UserBTO getProfile() {
		User user = getLoggedInUser();
		return Util.mapToUserBTO(user);
	}
	
	public void updateProfile(UserBTO bto) {
		User user = getLoggedInUser();
		
		user.setFname(bto.getFname());
		user.setLname(bto.getLname());
		user.setAddress(bto.getAddress());
		user.setEmail(bto.getEmail().toLowerCase());
		user.setUsername(bto.getUsername().toLowerCase());
		if(!StringUtils.isEmpty(bto.getPassword())) {
			String encodedPassword = passwordEncoder.encode(bto.getPassword());
			user.setPassword(encodedPassword);
		}
		
		userRepo.save(user);
	}



	public boolean checkUsernameAvailability(String username) {
		return userRepo.existsByUsername(username);
	}
	
	public boolean checkEmailAvailability(String email) {
		return userRepo.existsByEmail(email);
	}



	public void signup(UserBTO bto) throws UserRegisterationException {
		
		if(userRepo.existsByEmail(bto.getEmail())){
			throw new UserRegisterationException("Email already taken");
		}
		
		if(userRepo.existsByUsername(bto.getUsername())){
			throw new UserRegisterationException("Username already taken");
		}
		
		User user = new User();
		user.setFname(bto.getFname());
		user.setLname(bto.getLname());
		user.setAddress(bto.getAddress());
		user.setEmail(bto.getEmail().toLowerCase());
		user.setUsername(bto.getUsername().toLowerCase());
		user.setPassword(passwordEncoder.encode(bto.getPassword()));
		userRepo.save(user);
	}
	


	public void createPasswordResetTokenForUser(UserBTO bto, String token) {
		User user = userRepo.findByEmail(bto.getEmail());
	    PasswordResetToken myToken = new PasswordResetToken(token, user);
	    passwordTokenRepository.save(myToken);
	}
	
	
	public boolean forgotPassword(String email) {
		final User user = userRepo.findByEmail(email);
		if(user != null) {
			final String token = UUID.randomUUID().toString();
			passwordTokenRepository.save(new PasswordResetToken(token, user));
			
			String text = "http://localhost:8080/resetpassword?token="+token;
			emailService.sendEmail(user.getEmail(),"Reset your password",text);
			return true;
		}
		return false;
	}
	

	public boolean isResetPasswordTokenValid(String token) {
		PasswordResetToken userToken = passwordTokenRepository.findByToken(token);
		if(userToken != null && userToken.getUpdated() == null && userToken.getExpired().compareTo(new Date()) > 0) {
			final User user = userToken.getUser();			
			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
			final Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			return true;
		}
		return false;
	}


	public String getLoggedInDisplayname() {
		return getLoggedInUser().getDisplayName();
	}
	
	
	public User getLoggedInUser() {
		 UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 return userRepo.findByUsername(ud.getUsername());
	}


	public void resetPasssword(String password) {
		User user = getLoggedInUser();
		PasswordResetToken prToken = passwordTokenRepository.findByUserAndUpdatedIsNull(user);
		prToken.setUpdated(new Date());
		passwordTokenRepository.save(prToken);
		user.setPassword(passwordEncoder.encode(password));
		userRepo.save(user);
	}



	public void generateNewPoll() {
		User loggedinUser = getLoggedInUser();
		List<User> users = userRepo.findAll();
		Random rand = new Random();
		for(User u : users) {
			if(u.getUsername() != loggedinUser.getUsername()) {
				u.setVote(Candidate.values()[rand.nextInt(3)]);
				userRepo.save(u);
			}
		}
		
	}
}
