package edu.txstate.cs.service;

import static edu.txstate.cs.helper.RoommateSpecification.availabilityDateBetween;
import static edu.txstate.cs.helper.RoommateSpecification.priceBetween;
import static edu.txstate.cs.helper.RoommateSpecification.hasGenderOf;
import static java.util.stream.Collectors.toList;
import static org.springframework.data.jpa.domain.Specification.where;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.txstate.cs.helper.Util;
import edu.txstate.cs.model.bto.RoommateBTO;
import edu.txstate.cs.model.enums.Gender;
import edu.txstate.cs.repository.RoommateRepo;

@Service
public class RoommateService {

	@Autowired
	RoommateRepo repo;

	public List<RoommateBTO> findRoommates(Gender gender, LocalDate fromDate, LocalDate toDate, Integer price){
		
		List<RoommateBTO> result = repo.findAll(where(hasGenderOf(gender))
				.and(availabilityDateBetween(fromDate, toDate)), Sort.by(Sort.Direction.ASC, "price")).stream().map(Util::mapToRoommateBTO).collect(toList());
		
		if(result == null) {
			return new ArrayList<>();
		}
		
		if(price == null || result.size() == 1) {
			return result;
		}
		
		
		List<RoommateBTO> clist = new ArrayList<>();
		for(int i=0; i<result.size(); i++) {
			RoommateBTO currBTO = result.get(i);
			Integer currPrice = currBTO.getPrice();
			if(currPrice < price){
				continue;
			}
			
			if(currPrice == price || i == 0 ) {
				clist.add(currBTO);
				return clist;
			}

			RoommateBTO preBTO = result.get(i-1);
			Integer prePrice = preBTO.getPrice();
			
			if(currPrice - price == price - prePrice) {
				clist.add(preBTO);
				clist.add(currBTO);
			}else if(currPrice - price < price - prePrice) {
				clist.add(currBTO);
			} else {
				clist.add(result.get(i-1));
				
			}
			return clist;
		}
		
		clist.add(result.get(result.size()-1));
		return clist;
		
	}
}
