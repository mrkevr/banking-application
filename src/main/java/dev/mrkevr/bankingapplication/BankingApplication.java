package dev.mrkevr.bankingapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}
	
//	@Bean
//	CommandLineRunner commandLineRunner(UserService userService) {
//		return args -> {
//			boolean validUser1 = userService.isValidUser("ray_holt@precinct99.gov", "password");
//			boolean validUser2 = userService.isValidUser("ray_holt@precinct99.gov", "passwords");
//			System.out.println(validUser1);
//			System.out.println(validUser2);
//		};
//	}
}
