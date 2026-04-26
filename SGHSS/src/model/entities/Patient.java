package model.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Patient {
	private Integer id;
	private String name;
	private String email;
	private String telefone;
	private LocalDate birthDate;

	public Patient() {
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Patient(Integer id, String name, String email, String telefone, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.telefone = telefone;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null || !email.contains("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
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
		Patient other = (Patient) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", email=" + email + ", telefone =" + telefone + ", birthDate="
				+ birthDate + "]";
	}

}
