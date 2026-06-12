package model.dao.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import model.dao.AppointmentDao;
import model.dao.PatientDao;
import model.entities.Appointment;

public class AppointmentService {
//	- createAppointment(...)
//	- updateAppointment(...)
//	- deleteAppointment(...)
//	- findAppointmentById(...)
//	- findAllAppointments(...)

	private AppointmentDao appointmentDao;
	private PatientDao patientDao;

	public AppointmentService() {
	}

	public AppointmentService(AppointmentDao appointmentDao) {
		this.appointmentDao = appointmentDao;
	}

	public void createAppointment(Appointment a) {

		if (a.getDateTime() == null) {
			throw new IllegalArgumentException("Data não pode ser nula");

		}

		if (validaAgendamento(a.getDateTime()) != true) {
			throw new IllegalArgumentException("Data inválida");

		}

		if (a.getId() == null || patientDao.findById(a.getPatient().getId()) == null|| a.getPatient() == null
				|| a.getDescription().isEmpty()) {
			throw new IllegalArgumentException("Erro ao criar agendamento");

		}

		if () {
			throw new IllegalArgumentException("Erro ao criara agendamento");

		}

		appointmentDao.insert(a);
	}

	private static boolean validaAgendamento(Timestamp timestamp) {

		LocalDate hoje = LocalDate.now();
		LocalDateTime hoj = hoje.atStartOfDay();
		Timestamp ho = Timestamp.valueOf(hoj);

		if (timestamp == null) {
			return false;

		}

		if (timestamp.before(ho)) {
			return false;

		}

//		if (timestamp.isAfter(hoje.plusYears(5))) {
//			return false;
//		}

		return true;
	}
}
