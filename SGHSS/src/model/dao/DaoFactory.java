package model.dao;

import db.DB;
import model.dao.impl.AppointmentDaoJDBC;
import model.dao.impl.PatientDaoJDBC;

public class DaoFactory {

	public static PatientDao createPatientDao() {
		return new PatientDaoJDBC(DB.getConnection());
	}
	
	public static AppointmentDao createAppointmentDao()
	{
		return new AppointmentDaoJDBC(DB.getConnection());
	}
}
