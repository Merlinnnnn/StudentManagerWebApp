package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.UserDao;
import model.User;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
        try {
            switch (action) {
                case "info":
                    showInfo(request, response);
                    break;
                case "changeinfo":
                    changeInfo(request, response);
                    break;
                default:
                	showInfo(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void showInfo(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
		        String email = UserDao.LayEmail(session.getAttribute("username").toString());
		        User quanTriVien = AdminDao.xemQuanTriVien(email);
		        request.setAttribute("quanTriVien", quanTriVien);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/info.jsp");
		        dispatcher.forward(request, response);
		    }
	
	private void changeInfo(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
				String email = UserDao.LayEmail(session.getAttribute("username").toString());
				User user = AdminDao.xemQuanTriVien(email);
		        session.setAttribute("oldEmail", email);
		        request.setAttribute("email", email);
		        request.setAttribute("diaChi", user.getDiaChi());
		        request.setAttribute("soDT", user.getSoDienThoai());
		        RequestDispatcher dispatcher = request.getRequestDispatcher("changeinfo/changeinfoAdmin.jsp");
		        dispatcher.forward(request, response);
		    }
}
