package com.healthcare.dao;

import com.healthcare.entities.Doctor;

public interface DoctorDao {
//register doctor
	String registerDoctor(Doctor doctor);

	String deleteDoctorDetailsById(Long doctorId);

	Doctor getDoctorDetailsById(Long doctorId);

	Doctor getDoctorDetailsByIdByJoinFetch(Long doctorId);
}
