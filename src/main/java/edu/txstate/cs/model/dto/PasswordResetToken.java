package edu.txstate.cs.model.dto;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
public class PasswordResetToken {
	
 
    @Id
    private String token;
 
    @Setter(AccessLevel.PUBLIC)
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    
    
    @Transient
	private static final long ONE_MINUTE_IN_MILLIS = 60000;
 
    private Date created;
    private Date expired;
    
    @Setter(AccessLevel.PUBLIC)
    private Date updated;
    
    public PasswordResetToken(String token, User user) {
    	this.user = user;
    	this.token = token;
  
    	Calendar date = Calendar.getInstance();
    	long t= date.getTimeInMillis();
    	this.created = new Date();
    	this.expired = new Date(t + (10 * ONE_MINUTE_IN_MILLIS));
    }
}
