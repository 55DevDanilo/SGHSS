package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.AppointmentDao;
import model.entities.Appointment;
import model.entities.Patient;

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
					"INSERT INTO Appointment " + "(timeDate,description,patient)" + " Values (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pr.setTimestamp(1, a.getDateTime());
			pr.setString(2, a.getDescription());
			pr.setInt(3, a.getPatient().getId());

			int rowsAffects = pr.executeUpdate();
			if (rowsAffects > 0) {
				ResultSet rs = pr.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					a.setId(id);

				}
				DB.closeResultSet(rs);

			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {

			DB.closeStatement(pr);
		}
	}

	@Override
	public void update(Appointment a) {
		PreparedStatement pr = null;

		try {
			pr = conn.prepareStatement(
					"UPDATE appointment" + " Set timeDate = ?, description = ?, patient = ? " + "where id= ?");
			pr.setTimestamp(1, a.getDateTime());
			pr.setString(2, a.getDescription());
			pr.setInt(3, a.getPatient().getId());
			pr.setInt(4, a.getId());
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
			pr = conn.prepareStatement("DELETE from  appointment" + " Where id = ?");
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
	public Appointment findById(Integer id) {
		PreparedStatement pr = null;
		ResultSet rs = null;
		try {
			pr = conn.prepareStatement("SELECT a.*, p.name, p.email, p.telefone " + "FROM appointment a "
					+ "JOIN patient p ON a.patient = p.id " + "WHERE a.id = ?");
			pr.setInt(1, id);
			rs = pr.executeQuery();

			if (rs.next()) {

				return instantAppointment(rs);

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
	public List<Appointment> findAll() {
		PreparedStatement pr = null;
		ResultSet rs = null;

		try {

			pr = conn.prepareStatement("SELECT a.*, p.name, p.email, p.telefone " + "FROM appointment a "
					+ "JOIN patient p ON a.patient = p.id " + "ORDER BY a.timeDate");
			rs = pr.executeQuery();

			List<Appointment> list = new ArrayList<>();
			while (rs.next()) {

				Appointment ap = instantAppointment(rs);
				list.add(ap);

			}

			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());

		} finally {

			DB.closeResultSet(rs);
			DB.closeStatement(pr);
		}

	}

	private Appointment instantAppointment(ResultSet rs) throws SQLException {

		Appointment ap = new Appointment();

		ap.setDateTime(rs.getTimestamp("timeDate"));
		ap.setDescription(rs.getString("description"));
		ap.setId(rs.getInt("id"));

		Patient p = new Patient();
		p.setId(rs.getInt("patient"));
		p.setName(rs.getString("name"));
		p.setEmail(rs.getString("email"));
		p.setTelefone(rs.getString("telefone"));

		ap.setPatient(p);

		return ap;
	}

}
