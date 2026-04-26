package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.PatientDao;
import model.entities.Patient;

public class PatientDaoJDBC implements PatientDao {

	private Connection conn;

	public PatientDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	public void insert(Patient p) {
		PreparedStatement pr = null;
		try {

			pr = conn.prepareStatement("INSERT INTO patient " + " (name,email,birthDate)" + "VALUES " + "(?,?,?)",
					Statement.NO_GENERATED_KEYS);
			pr.setString(1, p.getName());
			pr.setString(2, p.getEmail());
			pr.setDate(3, Date.valueOf(p.getBirthDate()));
			int rowsAffects = pr.executeUpdate();
			if (rowsAffects > 0) {
				ResultSet rs = pr.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					p.setId(id);

				}

				DB.closeResultSet(rs);

			} else {

				throw new DbException("Unexpected error! No rows affected!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {

			DB.closeStatement(pr);

		}

	}

	@Override
	public void update(Patient p) {
		PreparedStatement pr = null;
		try {

			pr = conn
					.prepareStatement("UPDATE from Patient" + " Set name =?, email = ?, birthDate =? " + "where id =?");
			pr.setString(1, p.getName());
			pr.setString(2, p.getEmail());
			pr.setDate(3, Date.valueOf(p.getBirthDate()));
			pr.setInt(4, p.getId());
			pr.executeUpdate();
			int rowsAffects = pr.executeUpdate();
			if (rowsAffects > 0) {

				System.out.println("Rows Affected : " + rowsAffects);

			} else {

				throw new DbException("Unexpected error! No rows affected!");

			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {

			DB.closeStatement(pr);

		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement pr = null;

		try {

			pr = conn.prepareStatement("DELETE from  Patient" + "Where id = ?");
			pr.setInt(1, id);
			pr.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {

			DB.closeStatement(pr);

		}

	}

	@Override
	public Patient findById(Integer id) {
		return null;
	}

	@Override
	public List<Patient> findAll() {
		return null;
	}

}
