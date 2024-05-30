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

import dao.DataDao;
import dao.StudentDao;
import dao.UserDao;
import model.User;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/student")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init(){
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
                case "huy":
                	break;
                case "import":
                	showInfoAdmin(request, response);
                	break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
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
                case "infoSV":
                	Integer maSV = Integer.valueOf(request.getParameter("maSV"));
                	showInfoSV(request, response, maSV);
                	break;
                default:
                	showInfo(request, response);
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
		        User sinhVien = StudentDao.xemSinhVien(email);
		        request.setAttribute("sinhVien", sinhVien);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("student/info.jsp");
		        dispatcher.forward(request, response);
		    }
	
	private void changeInfo(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
				String email = UserDao.LayEmail(session.getAttribute("username").toString());
				User user = StudentDao.xemSinhVien(email);
		        session.setAttribute("oldEmail", email);
		        request.setAttribute("email", email);
		        request.setAttribute("diaChi", user.getDiaChi());
		        request.setAttribute("cccd", user.getCccd());
		        request.setAttribute("soDT", user.getSoDienThoai());
		        RequestDispatcher dispatcher = request.getRequestDispatcher("changeinfo/changeinfoStudent.jsp");
		        dispatcher.forward(request, response);
		    }
	
	private void showInfoAdmin(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
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
		        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/manageStudent.jsp");
		        dispatcher.forward(request, response);
		    }
	
	private void showInfoSV(HttpServletRequest request, HttpServletResponse response, Integer maSV)
		    throws SQLException, ServletException, IOException {
		        List<User> list_sinhVien = StudentDao.AdminXemSinhVien();
		        String email = StudentDao.LayEMailTuMaSV(maSV);
				User user = StudentDao.xemSinhVien(email);
				user.setTenDN(StudentDao.LayTenDangNhap(email));
				user.setMatKhau(StudentDao.LayMatKhau(email));
				user.setMa(maSV);
		        request.setAttribute("sinhVien", user);
		        request.setAttribute("tenKhoa", user.getKhoa());
		        request.setAttribute("gioiTinhSinhVien", user.getGioiTinh());
		        request.setAttribute("lopSinhVien", user.getLop());
		        request.setAttribute("list_sinhVien", list_sinhVien);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/manageStudent.jsp");
		        dispatcher.forward(request, response);
		    }
	
	public void insert(HttpServletRequest request, HttpServletResponse response)
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
		String lop = request.getParameter("lop");
		Integer nienKhoa = null;
		try {
			nienKhoa = DataDao.NienKhoa(lop);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = new User();
		user.setMa(Integer.valueOf(request.getParameter("maSV")));
		user.setTenDN(request.getParameter("tenDN"));
		user.setMatKhau(request.getParameter("matKhau"));
		user.setEmail(request.getParameter("email"));
		user.setHoTen(request.getParameter("hoTen"));
		user.setNgaySinh(LocalDate.parse(request.getParameter("ngaySinh")));
		user.setGioiTinh(request.getParameter("gioiTinh"));
		user.setDiaChi(request.getParameter("diaChi"));
		user.setCccd(request.getParameter("cccd"));
		user.setSoDienThoai(request.getParameter("sdt"));
		user.setLop(lop);
		user.setNienKhoa(nienKhoa);
		user.setKhoa(request.getParameter("khoa"));
		String email = StudentDao.LayEMailTuMaSV(user.getMa());
		try {
			int result = UserDao.updateStudent(user, email);
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
		
		Integer maSV = Integer.valueOf(request.getParameter("maSV"));
		try {
			int result = UserDao.deleteStudent(maSV);
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