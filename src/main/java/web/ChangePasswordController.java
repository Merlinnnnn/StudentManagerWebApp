package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ChangePasswordDao;
import model.ChangePassword;

/**
 * Servlet implementation class ChangePasswordController
 */
@WebServlet("/changepass")
public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChangePasswordDao cpd;
	public void init() {
		cpd = new ChangePasswordDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("changepass/changepass.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		changePass(request, response);
	}
	
	public void changePass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ChangePassword cp = new ChangePassword();
		HttpSession session = request.getSession();
		cp.setTenDN(session.getAttribute("username").toString());
		cp.setMatKhauCu(request.getParameter("matKhauCu"));
		cp.setMatKhauMoi(request.getParameter("matKhauMoi"));
		cp.setXacNhanMatKhau(request.getParameter("xacNhanMatKhau"));
		try {
			int result = cpd.changePass(cp);
			if(result == 1) {
				request.setAttribute("NOTIFICATION", "Change Password Successfully");
				RequestDispatcher dispatcher = request.getRequestDispatcher("student/info.jsp");
				dispatcher.forward(request, response);
			}
			else
				doGet(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
