package dev.mrkevr.bankingapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.mrkevr.bankingapplication.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByAccountNumber(String accountNumber);
	
	Optional<User> findByEmail(String email);
	
	boolean existsByAccountNumber(String accountNumber);

	boolean existsByEmail(String email);
	
//	@Query("SELECT u FROM User u WHERE u.email = :email AND BINARY u.password = :password")
	@Query(value = "SELECT * FROM users u WHERE u.email = :email AND BINARY u.password = :password", nativeQuery = true)
	Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
	
	@Modifying
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.email = :email")
    int changePasswordByEmail(@Param("email") String email, @Param("newPassword") String newPassword);
}
