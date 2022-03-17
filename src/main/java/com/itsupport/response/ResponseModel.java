package com.itsupport.response;

import java.io.Serializable;

public class ResponseModel implements Serializable {

	String message;

	public ResponseModel(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}