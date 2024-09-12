package com.example.crm.document;

import java.util.Objects;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

public class Address {
	private AddressType addressType;
	@Field("ulke")
	@Indexed
	private String country;
	private String city;
	private String line1;
	private String line2;
	@Field("posta_kodu")
	private String zipCode;

	public Address() {
	}

	public Address(String country, String city, String line1, String line2, String zipCode) {
		this.country = country;
		this.city = city;
		this.line1 = line1;
		this.line2 = line2;
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressType, city, country, line1, line2, zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return addressType == other.addressType && Objects.equals(city, other.city)
				&& Objects.equals(country, other.country) && Objects.equals(line1, other.line1)
				&& Objects.equals(line2, other.line2) && Objects.equals(zipCode, other.zipCode);
	}

	@Override
	public String toString() {
		return "Address [addressType=" + addressType + ", country=" + country + ", city=" + city + ", line1=" + line1
				+ ", line2=" + line2 + ", zipCode=" + zipCode + "]";
	}

}
