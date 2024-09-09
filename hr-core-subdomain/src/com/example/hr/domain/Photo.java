package com.example.hr.domain;

import java.util.Base64;

@ValueObject
public record Photo(byte[] values) {
	public Photo(String values) {
		this(Base64.getDecoder().decode(values));
	}

	public String toBase64() { // utility method
		return Base64.getEncoder().encodeToString(values);
	}
}
