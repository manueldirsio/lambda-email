package com.aztlan.lambda.apigateway.entity;
public class Request {

	EmailDTO email;
	private String httpMethod;
	public EmailDTO getEmail() {
		return email;
	}
	public void setEmail(EmailDTO email) {
		this.email = email;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	@Override
	public String toString() {
		return "Request [email=" + email.toString() + ", httpMethod=" + httpMethod + "]";
	}
	
	
}
