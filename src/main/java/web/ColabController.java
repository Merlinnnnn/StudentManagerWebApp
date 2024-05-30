package web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ColabDao;
import dao.StudentDao;
import dao.UserDao;
import model.User;

/**
 * Servlet implementation class ColabController
 */
@WebServlet("/colab")
public class ColabController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void init() throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
        try {
            switch (action) {
                case "info":
                    showInfo(request, response);
                    break;
                case "changeinfo":
                	changeInfo(request, response);
                	break;
                case "infoAdmin":
                	showInfoAdmin(request, response);
                	break;
                case "infoCTV":
                	Integer maCTV = Integer.valueOf(request.getParameter("maCTV"));
                	showInfoCTV(request, response, maCTV);
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
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("chucNang");
        try {
            switch (action) {
                case "them":
                	insert(request, response);
                    break;
                case "sua":
                	update(request, response);
                	break;
                case "xoa":
                	delete(request, response);
                	break;
                default:
                	showInfoAdmin(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}
	
	private void showInfo(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
		        String email = UserDao.LayEmail(session.getAttribute("username").toString());
		        User congTacVien = ColabDao.xemCongTacVien(email);
		        request.setAttribute("congTacVien", congTacVien);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("colab/info.jsp");
		        dispatcher.forward(request, response);
		    }
	
	private void changeInfo(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
				String email = UserDao.LayEmail(session.getAttribute("username").toString());
				User user = ColabDao.xemCongTacVien(email);
		        session.setAttribute("oldEmail", email);
		        request.setAttribute("email", email);
		        request.setAttribute("diaChi", user.getDiaChi());
		        request.setAttribute("soDT", user.getSoDienThoai());
		        RequestDispatcher dispatcher = request.getRequestDispatcher("changeinfo/changeinfoColab.jsp");
		        dispatcher.forward(request, response);
		    }
	
	private void showInfoAdmin(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
		        List<User> list_congTacVien = ColabDao.AdminXemCTV();
				String email = ColabDao.LayEmailTuMaCTV(1);
				User user = ColabDao.xemCongTacVien(email);
				user.setTenDN(StudentDao.LayTenDangNhap(email));
				user.setMatKhau(StudentDao.LayMatKhau(email));
				user.setMa(1);
				request.setAttribute("gioiTinhCongTacVien", user.getGioiTinh());
		        request.setAttribute("congTacVien", user);
		        request.setAttribute("list_congTacVien", list_congTacVien);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/manageColab.jsp");
		        dispatcher.forward(request, response);
		    }
	
	private void showInfoCTV(HttpServletRequest request, HttpServletResponse response, Integer maCTV)
		    throws SQLException, ServletException, IOException {
		        List<User> list_congTacVien = ColabDao.AdminXemCTV();
				String email = ColabDao.LayEmailTuMaCTV(maCTV);
				User user = ColabDao.xemCongTacVien(email);
				user.setTenDN(StudentDao.LayTenDangNhap(email));
				user.setMatKhau(StudentDao.LayMatKhau(email));
				user.setMa(maCTV);
				request.setAttribute("gioiTinhCongTacVien", user.getGioiTinh());
		        request.setAttribute("congTacVien", user);
		        request.setAttribute("list_congTacVien", list_congTacVien);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/manageColab.jsp");
		        dispatcher.forward(request, response);
		    }
	
	public void insert(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		User user = new User();
		user.setTenDN(request.getParameter("tenDN"));
		user.setMatKhau(request.getParameter("matKhau"));
		user.setEmail(request.getParameter("email"));
		user.setLoaiTK("ctv");
		user.setHoTen(request.getParameter("hoTen"));
		user.setNgaySinh(LocalDate.parse(request.getParameter("ngaySinh")));
		user.setGioiTinh(request.getParameter("gioiTinh"));
		user.setDiaChi(request.getParameter("diaChi"));
		user.setSoDienThoai(request.getParameter("sdt"));
		user.setNgayTao(LocalDate.now());
		try {
			int result = UserDao.insertColab(user);
			if (result == 1) {
				showInfoAdmin(request, response);
			} else
				showInfoAdmin(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		User user = new User();
		user.setMa(Integer.valueOf(request.getParameter("maCTV")));
		user.setTenDN(request.getParameter("tenDN"));
		user.setMatKhau(request.getParameter("matKhau"));
		user.setEmail(request.getParameter("email"));
		user.setHoTen(request.getParameter("hoTen"));
		user.setNgaySinh(LocalDate.parse(request.getParameter("ngaySinh")));
		user.setGioiTinh(request.getParameter("gioiTinh"));
		user.setDiaChi(request.getParameter("diaChi"));
		user.setSoDienThoai(request.getParameter("sdt"));
		String email = ColabDao.LayEmailTuMaCTV(user.getMa());
		try {
			int result = UserDao.updateColab(user, email);
			if (result == 1) {
				showInfoAdmin(request, response);
			} else
				showInfoAdmin(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		Integer maCTV = Integer.valueOf(request.getParameter("maCTV"));
		try {
			int result = UserDao.deleteColab(maCTV);
			if (result == 1) {
				showInfoAdmin(request, response);
			} else
				showInfoAdmin(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
