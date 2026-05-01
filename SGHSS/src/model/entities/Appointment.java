package model.entities;

import java.sql.Timestamp;
import java.util.Objects;

public class Appointment {

	private Integer id;
	private Timestamp timeDate;
	private String description;
	private Patient patient;

	public Appointment() {

	}

	public Appointment(Integer id, Timestamp timeDate, String description, Patient patient) {
		this.id = id;
		this.timeDate = timeDate;
		this.description = description;
		this.patient = patient;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDateTime() {
		return timeDate;
	}

	public void setDateTime(Timestamp timeDate) {
		this.timeDate = timeDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Patient getPatient() {
		return patient;
	}

public void setPatient(Patient patient) {
this.patient = patient;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", timeDate=" + timeDate + ", description=" + description + ", patient="
				+ patient + "]";
	}

}
