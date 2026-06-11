package com.healthcare.value_types;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdhaarCard {
	@Column(name="card_number",unique = true)
	private String cardNumber;
	private String location;
	@Column(name="card_creation_date")
	private LocalDate cardCreationDate;

}
