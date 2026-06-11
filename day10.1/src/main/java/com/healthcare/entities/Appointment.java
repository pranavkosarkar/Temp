package com.healthcare.entities;

import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "appointments")
@AttributeOverride(name = "id", column = @Column(name = "appointment_id"))
//Lombok
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = { "myDoctor", "myPatient" })

public class Appointment extends BaseClass {
	@Column(name = "start_date_time")
	private LocalDateTime startDateTime;
	@Column(name = "end_date_time")
	private LocalDateTime endtDateTime;
	@Enumerated(EnumType.STRING)
	private Status status = Status.SCHEDULED;
	// Establish association between Appointment *------->1 Doctor
	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private Doctor myDoctor;
	// Establish association between Appointment *------>1 Patient
	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient myPatient;

	public Appointment(LocalDateTime startDateTime, LocalDateTime endtDateTime) {
		super();
		this.startDateTime = startDateTime;
		this.endtDateTime = endtDateTime;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Appointment) {
			Appointment a = (Appointment) o;
			return getId() == a.getId();
		}
		return false;
	}
	@Override
	public int hashCode() {
		long id=getId();
		return (int) id;
	}

}
