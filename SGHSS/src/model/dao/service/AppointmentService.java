package model.dao.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import model.dao.AppointmentDao;
import model.dao.PatientDao;
import model.entities.Appointment;
import model.entities.Patient;

public class AppointmentService {
//	- createAppointment(...) ok
//	- updateAppointment(...) ok
//	- deleteAppointment(...)ok
//	- findAppointmentById(...)ok
//	- findAllAppointments(...)
//****************Corrigir validações******************* Verificar conversa no ChatGPT
	private AppointmentDao appointmentDao;
	private PatientDao patientDao;

	public AppointmentService() {
	}

	public AppointmentService(AppointmentDao appointmentDao, PatientDao patientDao) {
		this.appointmentDao = appointmentDao;
		this.patientDao = patientDao;
	}

	public void createAppointment(Appointment a) {

		if (a.getDateTime() == null) {
			throw new IllegalArgumentException("Data não pode ser nula");

		}

		if (validaAgendamento(a.getDateTime()) != true) {
			throw new IllegalArgumentException("Data inválida");

		}

		if (a.getPatient() == null || a.getPatient().getId() == null) {
			throw new IllegalArgumentException("Erro ao criar agendamento");

		}

		if (patientDao.findById(a.getPatient().getId()) == null)

		{
			throw new IllegalArgumentException("Paciente não encontrado");

		}

		if (a.getDescription() == null || a.getDescription().isEmpty()) {
			throw new IllegalArgumentException("Descrição não pode ser vazia");
		}

		appointmentDao.insert(a);

	}

	public void updateAppointment(Appointment a) {

		if (a.getId() == null) {

			throw new IllegalArgumentException("Id não pode ser nulo");

		}

		if (patientDao.findById(a.getPatient().getId()) == null) {

			throw new IllegalArgumentException("Id do paciente não encontrado");

		}

		if (a.getDescription() == null || a.getDescription().isEmpty()) {
			throw new IllegalArgumentException("Descrição não pode ser vazia");
		}

		if (validaAgendamento(a.getDateTime()) != true) {
			throw new IllegalArgumentException("Data inválida");

		}
		
		if (appointmentDao.findById(a.getId()) == null) {

			throw new IllegalArgumentException("Agendamento não existente");

		}

		appointmentDao.update(a);
	}

	public void deleteAppointment(Appointment a) {

		if (a.getId() == null) {

			throw new IllegalArgumentException("Id não pode ser nulo");

		}

		if (patientDao.findById(a.getPatient().getId()) == null) {

			throw new IllegalArgumentException("Id do paciente não encontrado");

		}

		if (appointmentDao.findById(a.getId()) == null) {

			throw new IllegalArgumentException("Agendamento não existente");

		}
		appointmentDao.deleteById(a.getId());
	}

	public Appointment findAppointmentById(Appointment a) {

		if (a.getId() == null ||appointmentDao.findById(a.getId()) == null) {
			throw new IllegalArgumentException("Id não encontrado");

		}

		return appointmentDao.findById(a.getId());
	}

	public List<Appointment> findAll() {
		return appointmentDao.findAll();
	}

	private static boolean validaAgendamento(Timestamp timestamp) {
		Instant now = Instant.now();
		LocalDate hoje = LocalDate.now();
		LocalDateTime hoj = hoje.atStartOfDay();
		Timestamp ho = Timestamp.valueOf(hoj);
		Timestamp tmstp = Timestamp.from(now);

		if (timestamp == null) {
			return false;

		}

		if (tmstp.getTime() > timestamp.getTime()) {
			return false;
		}

		return true;
	}
}
