package com.example.demo.common;

import lombok.Getter;

@Getter
public class RestException extends Exception{
	
	private static final long serialVersionUID = 3828695984559132901L;
	
	private int statusCode;
    private String errorMessage; // 에러메시지
    
    public RestException(int statusCode) {
    	this.statusCode = statusCode;
    }
    
    public RestException(int statusCode, String errorMessage) {
    	this.statusCode = statusCode;
    	this.errorMessage = errorMessage;
    }
}
