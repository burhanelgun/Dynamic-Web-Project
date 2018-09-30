package bean;

import java.sql.SQLException;

public class DatabaseConnectionBean {
	private String myDriver = new String();
	private String myUrl = new String();
	private String databaseName = new String();
	private String databasePassword = new String();
	private String JNDIname = new String();
	private boolean isUsingDataSource;
	
	public DatabaseConnectionBean() throws SQLException {

	}
	public DatabaseConnectionBean(String myDriver, String myUrl, String databaseName, String databasePassword,String JNDIname,boolean isUsingDataSource) {
		this.myDriver = myDriver;
		this.myUrl = myUrl;
		this.databaseName = databaseName;
		this.databasePassword = databasePassword;
		this.JNDIname=JNDIname;
		this.isUsingDataSource = isUsingDataSource;
	}
	public boolean isUsingDataSource() {
		return isUsingDataSource;
	}
	public String getMyDriver() {
		return myDriver;
	}
	public String getJNDIname() {
		return JNDIname;
	}
	public String getMyUrl() {
		return myUrl;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public String getDatabasePassword() {
		return databasePassword;
	}
}
