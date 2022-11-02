package com.example.demo.ui.model.response;

public enum ErrorMessages {
	MISSING_REQUIRED_FIELD("Missing required field. Please check documentation"),
	RECORD_ALREADY_EXISTS("Record already exists"),
	INTERNAL_SERVER_ERROR("Internal server error"),
	NO_RECORD_FOUND("No record found"),
	AUTHENTICATION_FAILED("Authentication failed"),
	COULD_NOT_UPDATE_RECORD("Could not update record"),
	EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified");
	
	private String errorMessage;
	
	ErrorMessages(String errorMessage){
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
