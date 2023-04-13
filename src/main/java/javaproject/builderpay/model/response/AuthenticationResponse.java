package javaproject.builderpay.model.response;

import java.time.LocalDate;

public class AuthenticationResponse {
	
	private String token;
	
	private LocalDate expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDate getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(LocalDate expiresIn) {
		this.expiresIn = expiresIn;
	}
	
}
