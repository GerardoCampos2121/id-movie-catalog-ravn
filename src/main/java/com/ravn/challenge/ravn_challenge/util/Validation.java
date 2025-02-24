package com.ravn.challenge.ravn_challenge.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
			Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {		
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.matches();
	}

}
