package model.dao.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import model.dao.AppointmentDao;
import model.dao.PatientDao;
import model.entities.Appointment;

public class AppointmentService {
//	- createAppointment(...) ok
//	- updateAppointment(...)
//	- deleteAppointment(...)
//	- findAppointmentById(...)
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
		appointmentDao.update(a);
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
