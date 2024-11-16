package com.gourab.filters;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import com.gourab.utils.FileHandler;

public class Validators {
	private static final Pattern NAME_PATTERN;
	private static final Pattern EMAIL_PATTERN;
	private static final Pattern PASSWORD_PATTERN;
	private static final Pattern GENDER_PATTERN;
	private static final Set<String> IMAGE_FORMATS;
	static {
		NAME_PATTERN = Pattern.compile("([a-zA-Z0-9_\\s]+)");
		EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
		PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,16}$");
		GENDER_PATTERN = Pattern.compile("^(male|female|others)$");
		IMAGE_FORMATS = new HashSet<String>(Arrays.asList("jpg", "jpeg", "png", "gif", "svg", "webp"));
	}

	public static boolean isName(String name) {
		return name != null && NAME_PATTERN.matcher(name).matches();
	}

	public static boolean isEmail(String email) {
		return email != null && EMAIL_PATTERN.matcher(email).matches();
	}

	public static boolean isPassword(String password) {
		return password != null && PASSWORD_PATTERN.matcher(password).matches();
	}

	public static boolean isGender(String gender) {
		return gender != null && GENDER_PATTERN.matcher(gender).matches();
	}

	public static boolean isAgreed(String agreement) {
		return agreement != null && agreement.equals("on");
	}

	public static boolean isFile(String fileName) {
		if (!fileName.isBlank()) {
			String extention = FileHandler.getFileExtension(fileName).toLowerCase();
			return IMAGE_FORMATS.contains(extention);
		}
		return true;
	}
}
