package com.healthcare.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "patients")
//@AttributeOverride(name = "id", column = @Column(name = "patient_id"))
@NoArgsConstructor
@ToString(callSuper = true,exclude="userDetails")
public class Patient extends BaseClass {
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Enumerated(EnumType.STRING)
	@Column(name = "blood_group")
	private BloodGroup bloodGroup;
	@Column(name = "family_history", length = 500)
	private String familyHistory;
	// How will you establish the association between Patient 1---->1 User
	@OneToOne //mandatory - othewise throws MappingException
	@JoinColumn(name="patient_id",nullable = false) //optional BUT reco
	@MapsId
	private User userDetails;
	
	public Patient(Gender gender, BloodGroup bloodGroup, String familyHistory) {
		super();
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.familyHistory = familyHistory;
	}
	
	

}
