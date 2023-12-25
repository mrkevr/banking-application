package dev.mrkevr.bankingapplication.util;

import java.time.Year;
import java.util.Random;

public class UserUtils {

	public static final String USER_CREATION_SUCCESS_CODE = "101";
	public static final String USER_CREATION_SUCCESS_MESSAGE = "User created cuccessfully";
	
	public static String generateAccountNumber() {
		Random random = new Random();
		// Generate a random 6-digit number between 100000 and 999999
		int min = 100_000;
		int max = 999_999;
		int randomNumber = random.nextInt(max - min + 1) + min;
		String prefix = String.valueOf(Year.now());
		return prefix + String.valueOf(randomNumber);
	}

}
