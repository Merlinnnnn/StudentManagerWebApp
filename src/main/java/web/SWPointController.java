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

import dao.AdminDao;
import dao.SWPointDao;
import dao.StudentDao;
import dao.UserDao;
import model.SWPoint;

/**
 * Servlet implementation class SWPointController
 */
@WebServlet("/swpoint")
public class SWPointController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "info":
				ShowInfo(request, response);
				break;
			case "insert":
				InsertSW(request, response);
				break;
			case "infoAdmin":
				ShowInfoAdmin(request, response);
				break;
			case "infoHD":
				Integer maHD = Integer.valueOf(request.getParameter("maHD"));
				ShowInfoHD(request, response, maHD);
				break;
			default:
				ShowInfo(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			default:
				ShowInfoAdmin(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	public void ShowInfo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		String email = UserDao.LayEmail(session.getAttribute("username").toString());
		Integer maSV = StudentDao.LayMaSV(email);
		session.setAttribute("maSV", maSV);
		List<SWPoint> sw = SWPointDao.XemDanhSachCTXH(maSV);
		List<SWPoint> hd = SWPointDao.XemDanhSachHoatDongConLai(maSV);
		request.setAttribute("listSW", sw);
		request.setAttribute("listHD", hd);
		Integer tongDiem = SWPointDao.XemDiemCTXH(maSV);
		request.setAttribute("diem", tongDiem);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student/swpoint.jsp");
		dispatcher.forward(request, response);
	}

	public void InsertSW(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		Integer maSV = Integer.valueOf(session.getAttribute("maSV").toString());
		Integer maHD = Integer.valueOf(request.getParameter("maHoatDong"));
		SWPoint sw = new SWPoint(maHD, LocalDate.now(), maSV);
		SWPointDao.InsertCertification(sw);
		ShowInfo(request, response);
	}

	private void ShowInfoAdmin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<SWPoint> list_hd = SWPointDao.XemDanhSachHoatDong();
		SWPoint swpoint = list_hd.get(0);
		request.setAttribute("hd", swpoint);
		request.setAttribute("toChuc", swpoint.getToChuc());
		request.setAttribute("list_hd", list_hd);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin/manageWork.jsp");
		dispatcher.forward(request, response);
	}

	private void ShowInfoHD(HttpServletRequest request, HttpServletResponse response, Integer maHD)
			throws SQLException, ServletException, IOException {
		List<SWPoint> list_hd = SWPointDao.XemDanhSachHoatDong();
		SWPoint swpoint = SWPointDao.XemChiTietHoatDong(maHD);
		request.setAttribute("hd", swpoint);
		request.setAttribute("toChuc", swpoint.getToChuc());
		request.setAttribute("list_hd", list_hd);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin/manageWork.jsp");
		dispatcher.forward(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer diem = Integer.valueOf(request.getParameter("diem"));
		String tenHoatDong = request.getParameter("tenHoatDong");
		String moTa = request.getParameter("moTa");
		LocalDate ngayBatDau = LocalDate.parse(request.getParameter("ngayBatDau"));
		LocalDate ngayKetThuc = LocalDate.parse(request.getParameter("ngayKetThuc"));
		String toChuc = request.getParameter("toChuc");
		HttpSession session = request.getSession();
		String email = UserDao.LayEmail(session.getAttribute("username").toString());
		Integer maNguoiXacNhan = AdminDao.LayMaQuanTriVien(email);
		SWPoint sw = new SWPoint(tenHoatDong, ngayBatDau, ngayKetThuc, diem, toChuc, moTa, maNguoiXacNhan);
		try {
			int result = SWPointDao.Insert(sw);
			if (result == 1) {
				ShowInfoAdmin(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer diem = Integer.valueOf(request.getParameter("diem"));
		String tenHoatDong = request.getParameter("tenHoatDong");
		String moTa = request.getParameter("moTa");
		LocalDate ngayBatDau = LocalDate.parse(request.getParameter("ngayBatDau"));
		LocalDate ngayKetThuc = LocalDate.parse(request.getParameter("ngayKetThuc"));
		String toChuc = request.getParameter("toChuc");
		HttpSession session = request.getSession();
		String email = UserDao.LayEmail(session.getAttribute("username").toString());
		Integer maNguoiXacNhan = AdminDao.LayMaQuanTriVien(email);
		Integer maHoatDong = Integer.valueOf(request.getParameter("maHoatDong"));
		SWPoint sw = new SWPoint(maHoatDong, tenHoatDong, ngayBatDau, ngayKetThuc, diem, toChuc, moTa, maNguoiXacNhan);
		try {
			int result = SWPointDao.Update(sw);
			if (result == 1) {
				ShowInfoAdmin(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
