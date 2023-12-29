package dev.mrkevr.bankingapplication;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.mrkevr.bankingapplication.entity.Role;
import dev.mrkevr.bankingapplication.entity.User;
import dev.mrkevr.bankingapplication.entity.embeddable.Address;
import dev.mrkevr.bankingapplication.entity.embeddable.FullName;
import dev.mrkevr.bankingapplication.repository.UserRepository;

@SpringBootApplication
public class BankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			
			User admin = User.builder()
				.email("admin@admin.com")
				.phone("1-8000-ADMIN")
				.fullName(new FullName("ADMIN", "ADMIN", "ADMIN"))
				.address(new Address("ADMIN", "ADMIN", "ADMIN"))
				.isActive(true)
				.accountBalance(BigDecimal.ZERO)
				.accountNumber("ADMIN")
				.password(passwordEncoder.encode("admin"))
				.role(Role.ROLE_ADMIN)
				.build();
			
			if(!userRepository.existsByEmail("admin@admin.com")) {
				userRepository.save(admin);
			}
		};
	}
}
