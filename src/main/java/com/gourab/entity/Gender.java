package com.gourab.entity;

public enum Gender {
	MALE, FEMALE, OTHERS;

	public static Gender convertToGender(String str) {
		Gender user_gender;
		switch (str) {
		case "male" -> user_gender = Gender.MALE;
		case "female" -> user_gender = Gender.FEMALE;
		default -> user_gender = Gender.OTHERS;
		}
		return user_gender;
	}
}
