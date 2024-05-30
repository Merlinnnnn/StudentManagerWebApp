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
import dao.LoginDao;
import dao.StudentDao;
import dao.UserDao;
import model.LoginBean;
import model.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;

	public void init() {
		loginDao = new LoginDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		authenticate(request, response);
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);

		try {
			if (loginDao.validate(loginBean)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("pass", password);
				session.setAttribute("logout", false);
				String loaitk = loginDao.LayLoaiTaiKhoan(username);
				if (loaitk.equals("sv")) {
					String email = UserDao.LayEmail(username);
					User sinhVien = StudentDao.xemSinhVien(email);
					if (sinhVien.isTrangThai()) {
						session.setAttribute("hoTen", sinhVien.getHoTen());
						session.setAttribute("sinhVien", sinhVien);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/student/info.jsp");
						dispatcher.forward(request, response);
					} else
						doGet(request, response);
				} else if (loaitk.equals("ctv")) {
					String email = UserDao.LayEmail(username);
					User congTacVien = ColabDao.xemCongTacVien(email);
					System.out.println(congTacVien.isTrangThai());
					if (congTacVien.isTrangThai()) {
						session.setAttribute("hoTen", congTacVien.getHoTen());
						request.setAttribute("congTacVien", congTacVien);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/colab/info.jsp");
						dispatcher.forward(request, response);
					} else
						doGet(request, response);
				} else if (loaitk.equals("qtv")) {
					String email = UserDao.LayEmail(username);
					User quanTriVien = AdminDao.xemQuanTriVien(email);
					session.setAttribute("hoTen", quanTriVien.getHoTen());
					request.setAttribute("quanTriVien", quanTriVien);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/info.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				doGet(request, response);
			}
		} catch (

		ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void checklogin(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("logout") != null)
			if (!(boolean) session.getAttribute("logout")) {
				LoginDao loginDao = new LoginDao();
				String username = (String) session.getAttribute("username");
				String password = (String) session.getAttribute("pass");
				LoginBean loginBean = new LoginBean(username, password);
				try {
					if (loginDao.validate(loginBean)) {
						session.setAttribute("username", username);
						session.setAttribute("pass", password);
						String loaitk = loginDao.LayLoaiTaiKhoan(username);
						if (loaitk.equals("sv")) {
							String email = UserDao.LayEmail(username);
							User sinhVien = StudentDao.xemSinhVien(email);
							if (sinhVien.isTrangThai()) {
								session.setAttribute("hoTen", sinhVien.getHoTen());
								session.setAttribute("sinhVien", sinhVien);
								RequestDispatcher dispatcher = request.getRequestDispatcher("/student/info.jsp");
								dispatcher.forward(request, response);
							}
						} else if (loaitk.equals("ctv")) {
							String email = UserDao.LayEmail(username);
							User congTacVien = ColabDao.xemCongTacVien(email);
							if(congTacVien.isTrangThai()) {
							session.setAttribute("hoTen", congTacVien.getHoTen());
							request.setAttribute("congTacVien", congTacVien);
							RequestDispatcher dispatcher = request.getRequestDispatcher("/colab/info.jsp");
							dispatcher.forward(request, response);
							}
						} else if (loaitk.equals("qtv")) {
							String email = UserDao.LayEmail(username);
							User quanTriVien = AdminDao.xemQuanTriVien(email);
							session.setAttribute("hoTen", quanTriVien.getHoTen());
							request.setAttribute("quanTriVien", quanTriVien);
							RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/info.jsp");
							dispatcher.forward(request, response);
						}
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
}}
