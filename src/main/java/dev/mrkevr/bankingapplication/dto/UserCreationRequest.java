package dev.mrkevr.bankingapplication.dto;

import dev.mrkevr.bankingapplication.entity.embeddable.Address;
import dev.mrkevr.bankingapplication.entity.embeddable.FullName;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

	FullName fullName;

	Address address;

	String email;

	String phone;
}
