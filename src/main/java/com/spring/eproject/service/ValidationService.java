package com.spring.eproject.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
	
	@Value("${validation.pattern.email:(^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$)}")
	private String emailPattern;

	@Value("${validation.pattern.name:(^[a-zA-Z\\s]+$)}")
	private String namePattern;

	@Value("${validation.pattern.phone:([0-9]{10})}")
	private String phoneIndianPattern;

	@Value("${validation.pattern.password:((?=.*[0-9])(?=.*[a-zA-Z]).{8,15})}")
	private String passwordPattern;
		
	@Value("${validation.pattern.date:(^(1[0-2]|0[1-9])(/|-)(3[01]|[12][0-9]|0[1-9]|[1-9])(/|-)[0-9]{4}$)}")
	private String datePatternString;

	public boolean validateEmail(String email) {
		return patternMatches(email, emailPattern);
	}
	
	public boolean validateName(String name) {
		return patternMatches(name, namePattern);
	}
	
	public boolean validatePhone(String phone) {
		return patternMatches(phone, phoneIndianPattern);
	}
	
	public boolean validatePassword(String password) {
		return patternMatches(password, passwordPattern);
	}
	
		
	private boolean patternMatches(String value,String patternString) {
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
	
	
}
