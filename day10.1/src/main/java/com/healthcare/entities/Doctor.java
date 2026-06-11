package com.healthcare.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="doctors")
//@AttributeOverride(name = "id",column = @Column(name="doctor_id"))
//Lombok annotations
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"userDetails","appointments"},callSuper = true)
public class Doctor extends BaseClass {
	@Column(length = 100)
	private String qualifications;
	@Column(name="experience_in_years")
	private int experienceInYears;
	private int fees;
	@Column(name="appointment_time")
	private int appointmentTime;// in minutes
	@Column(length = 100)
	private String speciality;
	//Doctor 1--->1 User : one to one uni dir association 
	@OneToOne(cascade = CascadeType.ALL) 
	//to specify FK col name & not null constraint
	@JoinColumn(name="doctor_id",nullable = false)
	@MapsId
	private User userDetails;
	//Doctor 1----->* Appointment
	@OneToMany(mappedBy = "myDoctor"/* ,fetch = FetchType.EAGER */)
	private List<Appointment> appointments=new ArrayList<>();
	//parameterized ctor - to init basic doc details
	public Doctor(String qualifications, int experienceInYears, int fees, int appointmentTime, String speciality) {
		super();
		this.qualifications = qualifications;
		this.experienceInYears = experienceInYears;
		this.fees = fees;
		this.appointmentTime = appointmentTime;
		this.speciality = speciality;
	}
	
	

}
