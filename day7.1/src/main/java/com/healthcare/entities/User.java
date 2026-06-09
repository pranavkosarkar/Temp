package com.healthcare.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * users table 
column - id(PK) , created_on : date , last_updated : datetime
first name , last name, email ,password , dob:date ,phone role:enum,reg_amount :int

 */
@NoArgsConstructor //default constructor
@Getter // all getters
@Setter //all setters
@ToString(exclude = "password")
//JPA annotations
@Entity //to declare Entity class - to tell Hibernate to manage entity life cycle
@Table(name="users")//customizes table name
public class User {
	@Id //To declare PK constraint
	//automatic ID generation
//	@GeneratedValue 
	/*to specify a strategy for PK generation - default AUTO
	 * 
	*/
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	//auto increment constraint added on PK key column
	@Column(name="user_id")
	private Long userId;
	@CreationTimestamp //generates auto date at the entity creation time
	@Column(name="created_on")
	private LocalDate createdOn;
	@UpdateTimestamp //generates auto datetime(Timestamp)  at the entity updation time
	@Column(name="last_updated")
	private LocalDateTime lastUpdated;	
	@Column(name="first_name",length = 30) //col name , varchar(30)
	private String firstName;
	@Column(name="last_name",length = 30) //col name , varchar(30)
	private String lastName;
	@Column(length = 30,unique = true) //col name , varchar(30) , unique constraint
	private String email;
	@Column(length = 300, nullable = false)//NOT NULL constraint
	private String password;
	private LocalDate dob;
	@Column(length = 14,unique = true)
	private String phone;
	@Enumerated(EnumType.STRING) //col type - varchar | enum
	private UserRole role;
	@Column(name="reg_amount")
	private Integer regAmount;
	public User(String firstName, String lastName, String email, String password, LocalDate dob, String phone,
			UserRole role, Integer regAmount) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.phone = phone;
		this.role = role;
		this.regAmount = regAmount;
	}
	
}
