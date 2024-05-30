package web;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;

import dao.StudentDao;
import dao.UserDao;
import model.User;

/**
 * Servlet implementation class ImportController
 */
@MultipartConfig
@WebServlet("/ImportController")
public class ImportController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ImportController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		InputStream input = request.getPart("file").getInputStream();
		UserDao.ImportExcel(request, input);
		List<User> list_sinhVien = StudentDao.AdminXemSinhVien();
		String email = StudentDao.LayEMailTuMaSV(1);
		User user = StudentDao.xemSinhVien(email);
		user.setTenDN(StudentDao.LayTenDangNhap(email));
		user.setMatKhau(StudentDao.LayMatKhau(email));
		user.setMa(1);
		request.setAttribute("sinhVien", user);
		request.setAttribute("tenKhoa", user.getKhoa());
		request.setAttribute("gioiTinhSinhVien", user.getGioiTinh());
		request.setAttribute("lopSinhVien", user.getLop());
		request.setAttribute("list_sinhVien", list_sinhVien);
		String message = (String)request.getAttribute("mess");
		System.out.println(message);
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin/manageStudent.jsp");
		dispatcher.forward(request, response);
	}
}
