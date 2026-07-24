package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.IOException;

public class DB {

	private static Connection conn = null;

	public static Connection getConnection() {

		try {
			if (conn == null || conn.isClosed()) {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);// DriverManager.getConnection(url, user, password);

			}

		} catch (SQLException e) {

			throw new DbException("Oi 1"+e.getMessage());
		}
		return conn;
	}

	public static void closeConnection() {

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {

				throw new DbException("Oi 2"+e.getMessage());

			}
		}
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();

			} catch (SQLException e) {

				throw new DbException("Oi 3"+e.getMessage());
			}

		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {

				throw new DbException("Oi 4"+e.getMessage());
			}

		}
	}

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;

		} catch (IOException e) {

			throw new DbException("Oi 7"+e.getMessage());
		}
	}

}
