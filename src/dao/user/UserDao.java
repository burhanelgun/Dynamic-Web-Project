package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Properties;

import bean.UserBean;
import dao.ConnectionHelper;
import interfaces.IServis;

public class UserDao extends ConnectionHelper implements IServis {


	public ArrayList<UserBean> getAllUsers() throws Exception {
		ArrayList<UserBean> customerList = new ArrayList<UserBean>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("Select * From nametable");
			rs = stmt.executeQuery();
			while (rs.next()) {
				UserBean customer = new UserBean(rs.getString("firstname"), rs.getString("lastname"), "sasd", "dsadds");
				customerList.add(customer);
			}
		} catch (Exception e) {
			closeResultSet(rs);

			closePreparedStatement(stmt);

			closeConnection(conn);
		} finally {
			if (conn != null) {
				closeConnection(conn);
			}
		}

		return customerList;
	}



	public void register(UserBean user) throws Exception, SQLIntegrityConstraintViolationException {
		Connection conn = null;
		try {
			conn = getConnectionWithoutAutoCommit();

			addNewRecord(user, conn);


			conn.commit();
		} catch (Exception e) {
			if (conn != null) {
				try {
					conn.rollback();
					if (e.toString().split(" ")[1].equals("Duplicate")) {
						throw new Exception("You have already added this number!");
					}
					throw new Exception("You have already registered.You can add new phone.");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if (conn != null) {
				closeConnection(conn);
			}
		}
	}

	public void addNewPhone(UserBean user) throws Exception, SQLIntegrityConstraintViolationException {
		Connection conn = null;
		try {
			conn = getConnectionWithoutAutoCommit();
			updateRecord(user, conn);
			conn.commit();
		} catch (Exception e) {
			if (conn != null) {
				try {
					conn.rollback();
					if (e.toString().split(" ")[1].equals("Cannot") && e.toString().split(" ")[2].equals("add")
							&& e.toString().split(" ")[3].equals("or")) {
						throw new Exception("You haven't registered yet. Please register firstly!");
					} else if (e.toString().split(" ")[1].equals("Duplicate")) {
						throw new Exception("You have already added this number!");
					} else {
						throw new Exception("Error!");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			closeConnection(conn);
		}
	}

	public void updateRecord(UserBean user, Connection conn) throws SQLException {
		String queryT = " insert into phonetable (tckn,number) values (?, ?)";
		PreparedStatement preparedStmt = conn.prepareStatement(queryT);
		preparedStmt.setString(1, user.getTCKN());
		preparedStmt.setString(2, user.getNumber());
		preparedStmt.executeUpdate();
		preparedStmt.close();
		closePreparedStatement(preparedStmt);
	}

	public void addNewRecord(UserBean user, Connection conn) throws SQLException {
		System.out.println("user.getTCKN = " +  user.getTCKN() + "  user.getFirstName = " +  user.getFirstName() + " user.getLastName = " +  user.getLastName() + " user.getNumber = " +  user.getNumber());

		PreparedStatement preparedStmt = conn
				.prepareStatement("insert into nametable (tckn,firstname,lastname) values (?, ?, ?)");
		preparedStmt.setString(1, user.getTCKN());
		preparedStmt.setString(2, user.getFirstName());
		preparedStmt.setString(3, user.getLastName());
		preparedStmt.executeUpdate();
		closePreparedStatement(preparedStmt);

		preparedStmt = conn.prepareStatement("insert into phonetable (tckn,number) values (?, ?)");
		preparedStmt.setString(1, user.getTCKN());
		preparedStmt.setString(2, user.getNumber());
		preparedStmt.executeUpdate();
		closePreparedStatement(preparedStmt);
	}
	
	@Override
	public void start(Properties prop) throws Exception {
		super.init(prop);
	}

	@Override
	public void stop() {

	}
}