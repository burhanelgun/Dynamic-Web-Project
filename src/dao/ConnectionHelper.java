package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.DatabaseConnectionBean;

public class ConnectionHelper {
	DatabaseConnectionBean dbConnectionBean;

	public void init(Properties prop) throws Exception {
		dbConnectionBean = new DatabaseConnectionBean(prop.getProperty("myDriver"), prop.getProperty("myUrl"),
				prop.getProperty("databaseName"), prop.getProperty("databasePassword"), prop.getProperty("JNDIName"),
				Boolean.valueOf(prop.getProperty("useDataSource")));
	}

	public void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	public void closePreparedStatement(PreparedStatement pr) {
		try {
			if (pr != null)
				pr.close();

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	protected void closeResultSet(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();

		} catch (Exception e) {
			System.err.println(e);
		}

	}

	public Connection getConnection() throws Exception {
		Connection conn;
		if (!(dbConnectionBean.isUsingDataSource())) {
			Class.forName(dbConnectionBean.getMyDriver());
			conn = DriverManager.getConnection(dbConnectionBean.getMyUrl(), dbConnectionBean.getDatabaseName(),
					dbConnectionBean.getDatabasePassword());
		} else {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(dbConnectionBean.getJNDIname());
			conn = ds.getConnection();
		}
		return conn;
	}

	public Connection getConnectionWithoutAutoCommit() throws Exception {
		Connection conn = getConnection();
		conn.setAutoCommit(false);
		return conn;
	}

}
