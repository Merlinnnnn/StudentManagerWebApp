package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ForgotPassDao;
import model.ForgotPass;
import utils.EmailUtility;

/**
 * Servlet implementation class ForgotPassController
 */
@WebServlet("/forgotpass")
public class ForgotPassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
	private String port;
	private String user;
	private String pass;
	private ForgotPassDao fd;

	public void init() {
		fd = new ForgotPassDao();
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("forgotpass/forgotpass.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forgotpass(request, response);
	}
	
	public void forgotpass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ForgotPass fp = new ForgotPass();
		fp.setTenDN(request.getParameter("tenDN"));
		fp.setEmail(request.getParameter("email"));
		try {
			if (fd.forgotPass(fp)) {
				EmailUtility.sendEmail(host, port, user, pass, fp.getEmail(), "Forgot Password", fp.getMatKhau());
				request.setAttribute("NOTIFICATION", "Send Email Successfully");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
				dispatcher.forward(request, response);
			}
			else
				doGet(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
