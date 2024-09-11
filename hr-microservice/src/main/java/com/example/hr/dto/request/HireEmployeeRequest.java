package com.example.hr.dto.request;

import java.util.List;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record HireEmployeeRequest
(
		@TcKimlikNo String identity,
		@NotEmpty String firstName,
		@NotEmpty String lastName,
		@Iban String iban,
		@Positive double salary,
		@NotNull FiatCurrency currency,
		List<Department> departments,
		@NotNull JobStyle jobStyle,
		@Max(2006) int birthYear,
		@NotEmpty String photo
) 
{

}
