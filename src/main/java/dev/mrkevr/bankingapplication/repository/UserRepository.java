package dev.mrkevr.bankingapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.mrkevr.bankingapplication.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByAccountNumber(String accountNumber);

	boolean existsByAccountNumber(String accountNumber);

	boolean existsByEmail(String email);
}
