package com.gorazer.mail_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
@Data
public class ContactRequest {
    @NotBlank private String name;
    @Email @NotBlank private String email;
    @NotBlank private String message;
    private String honeypot;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getHoneypot() {
		return honeypot;
	}
	public void setHoneypot(String honeypot) {
		this.honeypot = honeypot;
	}

}
