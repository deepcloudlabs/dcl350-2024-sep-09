package com.example.crm.document;

import java.util.Objects;

import org.springframework.data.mongodb.core.index.Indexed;

public class Phone {
	private PhoneType phoneType;
	@Indexed
	private String number;
	private String external;

	public Phone() {
	}

	public Phone(PhoneType phoneType, String number, String external) {
		this.phoneType = phoneType;
		this.number = number;
		this.external = external;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExternal() {
		return external;
	}

	public void setExternal(String external) {
		this.external = external;
	}

	@Override
	public int hashCode() {
		return Objects.hash(external, number, phoneType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phone other = (Phone) obj;
		return Objects.equals(external, other.external) && Objects.equals(number, other.number)
				&& phoneType == other.phoneType;
	}

	@Override
	public String toString() {
		return "Phone [phoneType=" + phoneType + ", number=" + number + ", external=" + external + "]";
	}

}
