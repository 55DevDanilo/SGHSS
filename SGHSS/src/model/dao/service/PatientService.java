package model.dao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import model.dao.PatientDao;
import model.entities.Patient;

public class PatientService {

	// createPatient(Patient p)
	// updatePatient(Patient p)
	// deletePatient(Integer id)
	// findPatientById(Integer id)
	// findAllPatients()
	// Métodos privados (validação):
	// validateName(...)
	// validateEmail(...)
	// validateTelefone(...)
	// validateBirthDate(...)

	// private PatientDaoJDBC patientDAOJDBC;
	private PatientDao patientDao;

	
	public PatientService(PatientDao patientDao) {
		// this.patientDAOJDBC = patientDAOJDBC;
		this.patientDao = patientDao;
	}

	public void createPatient(Patient p) {

		if (validaName(p.getName()) != true) {
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

		if (validaTelefone(p.getTelefone()) != true) {
			throw new IllegalArgumentException("Telefone número de  inválido");
		}

		if (validaDataNascimento(p.getBirthDate()) != true) {
			throw new IllegalArgumentException("Data de nascimento inválido");

		}

		// Validação da data

		patientDao.insert(p);
	}

	public void deletePatient(Patient p) {

		if (p.getId() == null) {
			throw new IllegalArgumentException("Id  não pode ser vazio");

		}

		if (patientDao.findById(p.getId()) == null) {
			throw new IllegalArgumentException("Id não encontrado");

		}

		patientDao.deleteById(p.getId());
	}

	public void updatePatient(Patient p) {

		if (p.getId() == null) {
			throw new IllegalArgumentException("Id  não pode ser vazio");

		}

		if (patientDao.findById(p.getId()) == null) {
			throw new IllegalArgumentException("Id não encontrado");

		}

		patientDao.update(p);
	}

	public Patient findPatientById(Patient p) {

		if (p.getId() == null || patientDao.findById(p.getId()) == null) {
			throw new IllegalArgumentException("Id não encontrado");

		}


		return patientDao.findById(p.getId());
	}

	public List<Patient> findAll() {

		return patientDao.findAll();

	}

	/////////////////// Métodos Estáticos

	private static boolean validaEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		String emailVerificar = email;
		return Pattern.compile(emailRegex).matcher(emailVerificar).matches();

	}

	private static boolean validaName(String name) {
		if (name == null || name.isBlank() || name.length() < 10) {

			return false;

		}

		return true;

	}

	private static boolean validaTelefone(String telefone) {
		if (telefone == null || telefone.isBlank() || telefone.length() < 3) {

			return false;

		}

		return true;
	}

	private static boolean validaDataNascimento(LocalDate birthDate) {

		if (birthDate == null) {
			return false;
		}

		LocalDate hoje = LocalDate.now();

		if (birthDate.isAfter(hoje)) {
			return false; // Não pode ser no futuro
		}

		if (birthDate.isBefore(hoje.minusYears(120))) {
			return false; //
		}

		return true;
	}

}
