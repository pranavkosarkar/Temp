package com.healthcare.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name="diag_tests")
@AttributeOverride(name="id",column = @Column(name="test_id"))
@ToString(callSuper = true,exclude = "appointments")
public class DiagTest extends BaseClass {
	private int cost;
	private String name;
	private String description;
	//DiagTest *------>* Appointment
	@ManyToMany //mandatory
	@JoinTable(name="diag_appointments",joinColumns = @JoinColumn(name="test_id"),inverseJoinColumns = @JoinColumn(name="appointment_id"))
	
	private Set<Appointment> appointments=new HashSet<>();
	public DiagTest(int cost, String name, String description) {
		super();
		this.cost = cost;
		this.name = name;
		this.description = description;
	}
	

}
