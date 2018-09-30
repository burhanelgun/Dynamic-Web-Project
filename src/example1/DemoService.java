package example1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import bean.UserBean;
import dao.ConnectionHelper;
import dao.ServiceFacade;

@Path("/DemoService")
public class DemoService {

	@GET
	@Path("/user/number={number}")
	@Produces("application/json")
	public Response getUserWithPhone(@PathParam("number") String number) throws Exception {

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/telefon_rehberi", "root", "1");
		Class.forName("com.mysql.jdbc.Driver");

		Statement stm;
		stm = conn.createStatement();
		String sql = "SELECT * FROM phonetable WHERE number='" + number + "'";
		ResultSet rst;
		rst = stm.executeQuery(sql);
		ArrayList<UserBean> customerList = new ArrayList<>();
		while (rst.next()) {
			UserBean customer = new UserBean(rst.getString("tckn"), rst.getString("number"), "dsad", "dsadds");
			customerList.add(customer);
		}
		return Response.status(200).entity(customerList).build();

	}

	@GET
	@Path("/user/firstname={firstname}&lastname={lastname}")
	@Produces("application/json")
	public ArrayList<UserBean> getUserWithNameAndSurname(@PathParam("firstname") String firstname,
			@PathParam("lastname") String lastname) throws Exception {

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/telefon_rehberi", "root", "1");
		Class.forName("com.mysql.jdbc.Driver");

		Statement stm;
		stm = conn.createStatement();
		String sql = "SELECT * FROM nametable WHERE firstname='" + firstname + "'" + "AND lastname='" + lastname + "'";
		ResultSet rst;
		rst = stm.executeQuery(sql);
		ArrayList<UserBean> customerList = new ArrayList<>();
		while (rst.next()) {
			UserBean customer = new UserBean(rst.getString("tckn"), rst.getString("firstname"),
			rst.getString("lastname"), "dsadds");
			customerList.add(customer);
		}
		return customerList;

	}

	@GET
	@Path("/user/tcno={tcno}")
	@Produces("application/json")
	public Response getUserWithTCNO(@PathParam("tcno") String tcno) throws Exception {

		Connection conn = ServiceFacade.getInstance().getUserDao().getConnection();
		Class.forName("com.mysql.jdbc.Driver");

		Statement stm;
		stm = conn.createStatement();
		String sql = "SELECT * FROM nametable WHERE tckn='" + tcno + "'";
		ResultSet rst;
		rst = stm.executeQuery(sql);
		ArrayList<UserBean> customerList = new ArrayList<>();
		while (rst.next()) {
			UserBean customer = new UserBean(rst.getString("firstname"), rst.getString("lastname"), "sasd", "dsadds");
			customerList.add(customer);
		}
		return Response.status(200).entity(customerList).build();
	}

	
	@GET
	@Path("/users")
	@Produces("application/json")
	public ArrayList<UserBean> getAllUsers() throws Exception {
		ArrayList<UserBean> customerList = ServiceFacade.getInstance().getUserDao().getAllUsers();
		return customerList;
	}
	
	
	@POST
	@Path("/user")
	@Consumes("application/json")
	public void storeUser(UserBean user) throws Exception {
		
		ServiceFacade.getInstance().getUserDao().register(user);		
	}

}