package web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DataDao;
import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		try {
			switch (action) {
			case "register":
				register(request, response);
				break;
			case "changepass":
				changePass(request, response);
				break;
			case "logout":
				session.setAttribute("logout", true);
				RequestDispatcher dispatcherlogout = request.getRequestDispatcher("login/login.jsp");
				dispatcherlogout.forward(request, response);
				break;
			default:
				session.setAttribute("logout", true);
				RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
				dispatcher.forward(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

	public void register(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String lop = request.getParameter("lop");
		Integer nienKhoa = null;
		try {
			nienKhoa = DataDao.NienKhoa(lop);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = new User();
		user.setTenDN(request.getParameter("tenDN"));
		user.setMatKhau(request.getParameter("matKhau"));
		user.setEmail(request.getParameter("email"));
		user.setLoaiTK("sv");
		user.setHoTen(request.getParameter("hoTen"));
		user.setNgaySinh(LocalDate.parse(request.getParameter("ngaySinh")));
		user.setGioiTinh(request.getParameter("gioiTinh"));
		user.setDiaChi(request.getParameter("diaChi"));
		user.setCccd(request.getParameter("cccd"));
		user.setSoDienThoai(request.getParameter("sdt"));
		user.setNgayTao(LocalDate.now());
		user.setLop(lop);
		user.setNienKhoa(nienKhoa);
		user.setKhoa(request.getParameter("khoa"));
		try {
			int result = UserDao.insertStudent(user);
			if (result == 1) {
				request.setAttribute("NOTIFICATION", "User Registered Successfully");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
				dispatcher.forward(request, response);
			} else
				response.sendRedirect("register/register.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void changePass(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("changepass/changepass.jsp");
		dispatcher.forward(request, response);
	}
}
