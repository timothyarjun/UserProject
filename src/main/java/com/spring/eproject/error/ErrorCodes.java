package com.spring.eproject.error;

public enum ErrorCodes implements ErrorCode{
	
	INTERNAL_SERVER_ERROR("500", "Internal Server Error"), 
	PASSWORD_NOT_MATCHING("220", "New Password and Confirm Password not matching"),
	USER_ID_MANDATORY("222", "User Id is mandatory"),
	USER_ID_NOT_MATCHING("221", "User Id not matching"),
	PASSWORD_WRONG("223", "Wrong password");
	
	private String errorKey;
	private String defaultMessage;
	
	private ErrorCodes(String errorKey, String defaultMessage) {
		this.errorKey = errorKey;
		this.defaultMessage = defaultMessage;
	}

	@Override
	public String errorKey() {
		return errorKey;
	}

	@Override
	public String defaultMessage() {
		return defaultMessage;
	}

}
