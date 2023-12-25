package dev.mrkevr.bankingapplication.dto;

import java.math.BigDecimal;

import dev.mrkevr.bankingapplication.entity.embeddable.Address;
import dev.mrkevr.bankingapplication.entity.embeddable.FullName;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserInfoResponse {

	FullName fullName;

	Address address;

	String accountNumber;

	boolean isActive;

	BigDecimal accountBalance;

	String email;

	String phone;
}
