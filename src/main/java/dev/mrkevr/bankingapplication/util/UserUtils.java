package dev.mrkevr.bankingapplication.util;

import java.security.SecureRandom;
import java.time.Year;
import java.util.Random;

public final class UserUtils {

	/*
	 * Codes for response
	 */
	public static final String USER_CREATION_SUCCESS_CODE = "100";
	public static final String USER_CREATION_FAILED_CODE = "101";
	public static final String EMAIL_ALREADY_EXISTS_CODE = "102";
	public static final String USER_FIND_SUCCESS_CODE = "103";
	public static final String USER_FIND_FAILED_CODE = "104";
	public static final String PASSWORD_CHANGE_SUCCESS_CODE = "105";
	public static final String PASSWORD_CHANGE_FAILED_CODE = "106";
	public static final String USER_DELETION_SUCCESS_CODE = "107";
	public static final String USER_DELETION_FAILED_CODE = "108";
	
	public static final String JWT_EXPIRED_CODE = "210";
	public static final String JWT_INVALID_CODE = "211";

	/*
	 * Messages for response
	 */
	public static final String USER_CREATION_SUCCESS_MESSAGE = "User created successfully";
	public static final String USER_CREATION_FAILED_MESSAGE = "User creation failed";
	public static final String EMAIL_ALREADY_EXISTS_MESSAGE = "Email is already taken by another user";
	public static final String USER_DELETION_SUCCESS_MESSAGE = "User deleted successfully";
	public static final String USER_DELETION_FAILED_MESSAGE = "User deletion failed";
	
	/*
	 * Pre authorize
	 */
	public static final String HAS_ROLE_ADMIN = "hasAuthority('ROLE_ADMIN')";
	public static final String HAS_ROLE_USER = "hasAuthority('ROLE_USER')";
	public static final String HAS_ANY_ROLE_ADMIN_AND_USER= "hasAnyRole('ROLE_ADMIN','ROLE_USER')";

	private UserUtils() {
	}

	public static String generateAccountNumber() {
		Random random = new Random();
		// Generate a random 6-digit number between 100000 and 999999
		int min = 100_000;
		int max = 999_999;
		int randomNumber = random.nextInt(max - min + 1) + min;
		String prefix = String.valueOf(Year.now());
		return prefix + String.valueOf(randomNumber);
	}
	
	public static String generatePassword() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		SecureRandom secureRandom = new SecureRandom();
		StringBuilder sb = new StringBuilder(12);

		for (int i = 0; i < 12; i++) {
			int randomIndex = secureRandom.nextInt(characters.length());
			char randomChar = characters.charAt(randomIndex);
			sb.append(randomChar);
		}
		return sb.toString();
	}
}
