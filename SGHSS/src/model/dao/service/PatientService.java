package model.dao.service;

import java.util.regex.Pattern;

import model.dao.PatientDao;
import model.dao.impl.PatientDaoJDBC;
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

	// private PatientDaoJDBC patientDAOJDBC;
	private PatientDao patientDao;

	public PatientService(PatientDao patientDao) {
		// this.patientDAOJDBC = patientDAOJDBC;
		this.patientDao = patientDao;
	}

	public void createPatient(Patient p) {

		if (p.getName() == null || p.getName().isBlank() || p.getName().length() < 3) {
			throw new IllegalArgumentException("Preenchimento incorreto do nome do paciente");

		}

		if (p.getEmail() == null || p.getEmail().isBlank()) {
			throw new IllegalArgumentException("E-mail não pode ser vazio");

		}

		if (validaEmail(p.getEmail()) != true) {
			throw new IllegalArgumentException("Email inválido");

		}

		if (patientDao.findByEmail(p.getEmail()) != null) {
			throw new IllegalArgumentException("Email Já cadastrado");
		}

		if (p.getTelefone() == null || p.getTelefone().isBlank()) {
			throw new IllegalArgumentException("Telefone não pode ser vazio");
		}

		if (p.getTelefone().length() < 10) {
			throw new IllegalArgumentException("Telefone inválido");
		}

		// Validação da data

		patientDao.insert(p);
	}

	private static boolean validaEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		String emailVerificar = email;
		return Pattern.compile(emailRegex).matcher(emailVerificar).matches();
	}

}
