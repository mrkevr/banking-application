package dev.mrkevr.bankingapplication.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import dev.mrkevr.bankingapplication.entity.embeddable.Address;
import dev.mrkevr.bankingapplication.entity.embeddable.FullName;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "User")
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name = "firstName", column = @Column(name = "first_name")),
		@AttributeOverride(name = "middleName", column = @Column(name = "middle_name")),
		@AttributeOverride(name = "lastName", column = @Column(name = "last_name")) })
	FullName fullName;

	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name = "street", column = @Column(name = "street")),
		@AttributeOverride(name = "city", column = @Column(name = "city")),
		@AttributeOverride(name = "state", column = @Column(name = "state")) })
	Address address;
	
	@Column(name = "account_number", unique = true)
	String accountNumber;

	@Column(name = "email")
	String email;
	
	@Column(name = "phone")
	String phone;

	@Column(name = "account_balance")
	BigDecimal accountBalance;

	@Column(name = "is_active")
	boolean isActive;

	@CreationTimestamp
	@Column(name = "created")
	LocalDateTime created;

	@UpdateTimestamp
	@Column(name = "modified")
	LocalDateTime modified;
}
