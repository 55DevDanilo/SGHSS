package model.dao;

import java.util.List;

import model.entities.Patient;

public interface PatientDao extends Dao<Patient> {

	void insert(Patient p);

	void update(Patient p);

	void deleteById(Integer id);

	Patient findById(Integer id);

	List<Patient> findAll();
}
