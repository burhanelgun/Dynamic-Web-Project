package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import dao.ServiceFacade;
import dao.user.UserDao;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String number = request.getParameter("number");
		String TCKN = request.getParameter("TCKN");
		try {
			UserDao newDao = ServiceFacade.getInstance().getUserDao();
			UserBean newUser = new UserBean(firstName, lastName, number, TCKN);
			newDao.register(newUser);
			RequestDispatcher view = request.getRequestDispatcher("SuccessRegister.html");
			view.forward(request, response);
		}
		catch (Exception e) {
			PrintWriter writer = response.getWriter();
			StringBuilder htmlResponse= new StringBuilder();
			htmlResponse.append("<html>").append(e.getMessage()).append("<form action=\"AddNewPhonePage.jsp\">\n <input type=\"submit\" value=\"Add New Phone\" />\n </form>").append("</html>");
			writer.println(htmlResponse);
		}
	}
}