package com.example.hr.entity;

import java.util.List;
import java.util.Objects;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
public class EmployeeEntity {
	@Id
	@TcKimlikNo
	private String identity;
	@Column(name = "fname")
	@NotBlank
	private String firstName;
	@Column(name = "lname")
	@NotBlank
	private String lastName;
	@Iban
	private String iban;
	private double salary;
	@Enumerated(EnumType.STRING)
	@NotNull
	private FiatCurrency currency;
	@ElementCollection
	private List<Department> departments;
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private JobStyle jobStyle;
	@Column(name = "byear")
	@Max(2006)
	private int birthYear;
	@Lob
	@Column(columnDefinition = "longblob")
	@NotNull
	private byte[] photo;

	public EmployeeEntity() {
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(FiatCurrency currency) {
		this.currency = currency;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public JobStyle getJobStyle() {
		return jobStyle;
	}

	public void setJobStyle(JobStyle jobStyle) {
		this.jobStyle = jobStyle;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeEntity other = (EmployeeEntity) obj;
		return Objects.equals(identity, other.identity);
	}

	@Override
	public String toString() {
		return "EmployeeEntity [identity=" + identity + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", iban=" + iban + ", salary=" + salary + ", currency=" + currency + ", departments=" + departments
				+ ", jobStyle=" + jobStyle + ", birthYear=" + birthYear + "]";
	}

}
