package dev.mrkevr.bankingapplication.dto;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenericResponse {

	String status;

	String message;

	Object body;

	LocalDateTime timeStamp;
}
