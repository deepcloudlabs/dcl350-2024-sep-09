package com.example.hr.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.example.ddd.Entity;

// Ubiquitous Language: Employee, TC Kimlik No, Full Name, Salary, Iban, Department, Birth Year,... 
// Ubiquitous Language: increaseSalary, Rate, promote
// Entity Class -> i. identity 
//                ii. persist 
//               iii. business method 
//                iv. mutable
@Entity(id = "identity", aggregate = true) // i. documentation ii. sonarqube iii. low/no-code
public class Employee {
	private TcKimlikNo identity;
	private FullName fullname;
	private Salary salary;
	private Iban iban;
	private BirthYear birthYear;
	private List<Department> departments;
	private Photo photo;
	private JobStyle jobStyle;

	public static class Builder {
		private TcKimlikNo identity;
		private FullName fullname;
		private Salary salary;
		private Iban iban;
		private BirthYear birthYear;
		private List<Department> departments;
		private Photo photo;
		private JobStyle jobStyle;

		public Builder identity(String value) {
			this.identity = TcKimlikNo.valueOf(value);
			return this;
		}

		public Builder fullname(String firstName, String lastName) {
			this.fullname = new FullName(firstName, lastName);
			return this;
		}

		public Builder salary(double value) {
			return salary(value, FiatCurrency.TL);
		}

		public Builder salary(double value, FiatCurrency currency) {
			this.salary = Salary.valueOf(value, currency);
			return this;
		}

		public Builder salary(double value, String currency) {
			this.salary = Salary.valueOf(value, FiatCurrency.valueOf(currency));
			return this;
		}

		public Builder iban(String value) {
			this.iban = Iban.of(value);
			return this;
		}

		public Builder birthYear(int value) {
			this.birthYear = new BirthYear(value);
			return this;
		}

		public Builder departments(String... departments) {
			this.departments = Arrays.stream(departments).map(Department::valueOf).toList();
			return this;
		}

		public Builder departments(Department... departments) {
			this.departments = Arrays.stream(departments).toList();
			return this;
		}

		public Builder photo(String values) {
			this.photo = new Photo(values);
			return this;
		}

		public Builder photo(byte[] values) {
			this.photo = new Photo(values);
			return this;
		}

		public Builder jobStyle(String value) {
			this.jobStyle = JobStyle.valueOf(value);
			return this;
		}

		public Builder jobStyle(JobStyle jobStyle) {
			this.jobStyle = jobStyle;
			return this;
		}

		public Employee build() {
			// i. validation rules
			// ii. business rules
			Salary MIN_WAGE = Salary.valueOf(20_000, this.salary.currency());
			if (this.departments.contains(Department.IT)
					&& this.jobStyle == JobStyle.FULL_TIME
					&& this.salary.lessThan(MIN_WAGE.multiply(Rate.of(2.0)))) {
				throw new IllegalArgumentException("BR2: FULL_TIME IT Employee's salary must be over than 3 times MIN WAGE.".formatted(salary));
			}
			// iii. invariants
			// iv. constraints
			// v. regulations/standards
			return new Employee(this);
		}
	}

	private Employee(Builder builder) {
		this.identity = builder.identity;
		this.fullname = builder.fullname;
		this.salary = builder.salary;
		this.iban = builder.iban;
		this.birthYear = builder.birthYear;
		this.departments = builder.departments;
		this.photo = builder.photo;
		this.jobStyle = builder.jobStyle;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

	public FullName getFullname() {
		return fullname;
	}

	public Salary getSalary() {
		return salary;
	}

	public Iban getIban() {
		return iban;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public Photo getPhoto() {
		return photo;
	}

	public JobStyle getJobStyle() {
		return jobStyle;
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
		Employee other = (Employee) obj;
		return Objects.equals(identity, other.identity);
	}

	@Override
	public String toString() {
		return "Employee [identity=" + identity + ", fullname=" + fullname + ", salary=" + salary + ", iban=" + iban
				+ ", birthYear=" + birthYear + ", departments=" + departments + ", jobStyle=" + jobStyle + "]";
	}

	public FullName changeLastName(String lastName) {
//        i. validation rules
//       ii. business rules
//      iii. invariants   
//       iv. constraints
//        v. regulations/standards
		this.fullname = FullName.of(fullname.firstName(), lastName);
		return this.fullname;
	}

	public Salary increaseSalary(Rate rate) {
//      i. validation rules
//     ii. business rules
//    iii. invariants   
//     iv. constraints
//      v. regulations/standards
		salary = this.salary.multiply(rate);
		return this.salary;
	}

	public List<Department> promote(Department department) {
//      i. validation rules
		Objects.requireNonNull(department);
//     ii. business rules
		if (this.departments.contains(department))
			throw new IllegalArgumentException("BR1: Department [%s] already exists.".formatted(department.name()));
		if (this.departments.contains(Department.IT)
				&& this.jobStyle == JobStyle.FULL_TIME
				&& this.salary.multiply(Rate.of(3.0)).lessThan(Salary.valueOf(20_000, this.salary.currency()))) {
			throw new IllegalArgumentException("BR2: Department [%s] already exists.".formatted(department.name()));
		}
//    iii. invariants   
//     iv. constraints
//      v. regulations/standards

		this.departments = new ArrayList<>(this.departments);
		this.departments.add(department);
		return this.departments;
	}
}
