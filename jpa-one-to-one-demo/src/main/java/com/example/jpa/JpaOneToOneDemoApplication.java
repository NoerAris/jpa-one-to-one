package com.example.jpa;

import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.jpa.model.Gender;
import com.example.jpa.model.User;
import com.example.jpa.model.UserProfile;
import com.example.jpa.repository.UserProfileRepository;
import com.example.jpa.repository.UserRepository;

@SpringBootApplication
public class JpaOneToOneDemoApplication implements CommandLineRunner{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserProfileRepository userProfileRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaOneToOneDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Clean up all database tables
		userProfileRepository.deleteAllInBatch();
		userRepository.deleteAllInBatch();
		
		//Create a User interface
		User user = new User("ARS", "SET", "ars@email.com", "tes1234");
		
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1992, 3, 23);
		
		//Create a User profile instance
		UserProfile userProfile = new UserProfile("021-22345", Gender.MALE, dateOfBirth.getTime(), "TNG", "KBMN", "JTJJR KM. 4", "IDN", "KBMN", "54472");
	
		//Set child reference(userProfile) in parent entity(user)
		user.setUserProfile(userProfile);
		
		//Set parent reference(user) in child entity(userProfile)
		userProfile.setUser(user);
		
		//Save parent reference (which will save the child as well)
		userRepository.save(user);
	}

}
