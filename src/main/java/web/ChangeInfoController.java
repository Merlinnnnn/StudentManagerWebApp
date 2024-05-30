package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.ColabDao;
import dao.StudentDao;
import model.User;

/**
 * Servlet implementation class ChangeInfo
 */
@WebServlet("/changeinfo")
public class ChangeInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao;
	public void init() {
		studentDao = new StudentDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("mainPage/mainPage.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		switch (action) {
		    case "student":
		        student(request, response);
		        break;
		    case "colab":
		        colab(request, response);
		        break;
		    case "admin":
		        admin(request, response);
		        break;
		    default:
		        break;
		}
	}
	
	public void student(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		HttpSession session = request.getSession();
		user.setEmail(request.getParameter("email"));
		user.setCccd(request.getParameter("cccd"));
		user.setSoDienThoai(request.getParameter("soDienThoai"));
		user.setDiaChi(request.getParameter("diaChi"));
		try {
			int result = studentDao.changeInfo(user, session.getAttribute("oldEmail").toString());
			if(result == 1) {
				request.setAttribute("NOTIFICATION", "Change Info Successfully");
				User sinhVien = StudentDao.xemSinhVien(user.getEmail());
				request.setAttribute("sinhVien", sinhVien);
				RequestDispatcher dispatcher = request.getRequestDispatcher("student/info.jsp");
				dispatcher.forward(request, response);
			}
			else
				response.sendRedirect("changeinfo/changeinfoStudent.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void colab(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		HttpSession session = request.getSession();
		user.setEmail(request.getParameter("email"));
		user.setSoDienThoai(request.getParameter("soDienThoai"));
		user.setDiaChi(request.getParameter("diaChi"));
		try {
			int result = ColabDao.changeInfo(user, session.getAttribute("oldEmail").toString());
			if(result == 1) {
				request.setAttribute("NOTIFICATION", "Change Info Successfully");
				User congTacVien = ColabDao.xemCongTacVien(user.getEmail());
				request.setAttribute("congTacVien", congTacVien);
				RequestDispatcher dispatcher = request.getRequestDispatcher("colab/info.jsp");
				dispatcher.forward(request, response);
			}
			else
				response.sendRedirect("changeinfo/changeinfoColab.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		HttpSession session = request.getSession();
		user.setEmail(request.getParameter("email"));
		user.setSoDienThoai(request.getParameter("soDienThoai"));
		user.setDiaChi(request.getParameter("diaChi"));
		try {
			int result = AdminDao.changeInfo(user, session.getAttribute("oldEmail").toString());
			if(result == 1) {
				request.setAttribute("NOTIFICATION", "Change Info Successfully");
				User quanTriVien = AdminDao.xemQuanTriVien(user.getEmail());
				request.setAttribute("quanTriVien", quanTriVien);
				RequestDispatcher dispatcher = request.getRequestDispatcher("admin/info.jsp");
				dispatcher.forward(request, response);
			}
			else
				response.sendRedirect("changeinfo/changeinfoAdmin.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
