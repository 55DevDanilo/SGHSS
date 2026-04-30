package model.dao.impl;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.AppointmentDao;
import model.entities.Appointment;

public class AppointmentDaoJDBC implements AppointmentDao {
	private Connection conn;

	public AppointmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Appointment a) {
		PreparedStatement pr = null;
		try {
			pr = conn.prepareStatement(
					"INSERT INTO Appointment " + "(dateTime,description,patient)" + " Values" + "(?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pr.setTimestamp(1,java.sql.Timestamp.valueOf(a.getDateTime()));
			pr.setString(2, a.getDescription());
			pr.setObject(3, a.getPatient());
			int rowsAffects = pr.executeUpdate();
			if (rowsAffects > 0) {
				ResultSet rs = pr.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					a.setId(id);

				}

			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {

			DB.closeStatement(pr);

		}
	}

	@Override
	public void update(Appointment a) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Appointment findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
