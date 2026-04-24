package model.dao;

import java.util.List;

import model.entities.Appointment;


public interface AppointmentDao extends Dao<Appointment> {
	
	
	void insert(Appointment a);

	void update(Appointment a);

	void deleteById(Integer id);

	Appointment findById(Integer id);

	List<Appointment> findAll();
	
	

}
