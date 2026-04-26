package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

			pr = conn.prepareStatement(
					"INSERT INTO patient " + " (name,email,telefone,birthDate)" + "VALUES " + "(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pr.setString(1, p.getName());
			pr.setString(2, p.getEmail());
			pr.setString(3, p.getTelefone());
			pr.setDate(4, Date.valueOf(p.getBirthDate()));
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

			pr = conn.prepareStatement(
					"UPDATE  patient " + " Set name =?, email = ?, telefone = ?,birthDate =? " + "where id =?");
			pr.setString(1, p.getName());
			pr.setString(2, p.getEmail());
			pr.setString(3, p.getTelefone());
			pr.setDate(4, Date.valueOf(p.getBirthDate()));
			pr.setInt(5, p.getId());
			pr.executeUpdate();

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

			pr = conn.prepareStatement("DELETE from  patient" + " Where id = ?");
			pr.setInt(1, id);
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
	public Patient findById(Integer id) {
		PreparedStatement pr = null;
		ResultSet rs = null;

		try {

			pr = conn.prepareStatement("Select * from  patient" + " Where id = ?");
			pr.setInt(1, id);
			rs = pr.executeQuery();

			if (rs.next()) {

				return instantPatient(rs);

			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {

			DB.closeResultSet(rs);
			DB.closeStatement(pr);

		}

	}

	@Override
	public List<Patient> findAll() {
		PreparedStatement pr = null;
		ResultSet rs = null;

		try {

			pr = conn.prepareStatement("Select * from  patient" + " Order By name");
			rs = pr.executeQuery();

			List<Patient> list = new ArrayList<>();

			while (rs.next()) {

				Patient pt = instantPatient(rs);
				list.add(pt);

			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {

			DB.closeResultSet(rs);
			DB.closeStatement(pr);

		}

	}

	private Patient instantPatient(ResultSet rs) throws SQLException {
		Patient pt = new Patient();
		pt.setName(rs.getString("name"));
		pt.setEmail(rs.getString("email"));
		pt.setTelefone(rs.getString("telefone"));
		pt.setBirthDate(rs.getDate("birthDate").toLocalDate());
		pt.setId(rs.getInt("id"));

		return pt;

	}

}
