package com.healthcare.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class UserDTO {
	private String fn;
	private String ln;
	private LocalDate birthDate;
}
