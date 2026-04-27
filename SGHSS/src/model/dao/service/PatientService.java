package model.dao.service;

import java.util.HashSet;
import java.util.Set;

import model.entities.Patient;

public class PatientService {

//	createPatient(Patient p)
//	updatePatient(Patient p)
//	deletePatient(Integer id)
//	findPatientById(Integer id)
//	findAllPatients()
//	Métodos privados (validação):
//	validateName(...)
//	validateEmail(...)
//	validateTelefone(...)
//	validateBirthDate(...)

	private Set<Patient> patient = new HashSet<>();

	public PatientService() {

	}

	public void createPatient(Patient p) {
		try {
			if (p == null) {
				throw new IllegalArgumentException("Paciente não pode ser nulo");

			}

			if (patient.contains(p) {
				throw new IllegalArgumentException("Pacient já inserido");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
