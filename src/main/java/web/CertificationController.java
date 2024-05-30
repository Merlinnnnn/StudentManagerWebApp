package web;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.CertificationDao;
import dao.ColabDao;
import dao.DataDao;
import dao.StudentDao;
import dao.UserDao;
import model.Certification;
import utils.EmailUtility;

/**
 * Servlet implementation class CertificationController
 */
@WebServlet("/certification")
public class CertificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "info":
				ShowInfo(request, response);
				break;
			case "delete":
				Delete(request, response, request.getParameter("loaitk"));
				break;
			case "infoColab":
				ShowInfoColab(request, response);
				break;
			case "xacNhan":
				Confirm(request, response, "Đã Xác Nhận");
				break;
			case "tuChoi":
				Confirm(request, response, "Bị Từ Chối");
				break;
			case "infoAdmin":
				ShowInfoAdmin(request, response);
				break;
			case "infoCer":
				String maLoaiGiay = request.getParameter("maLoaiGiay");
				ShowInfoCer(request, response, maLoaiGiay);
				break;
			case "them":
				InsertCer(request, response);
				break;
			case "sua":
				UpdateCer(request, response);
				break;
			case "infoDash":
				ShowInfoDash(request, response);
				break;
			case "infoRe":
				Integer soThuTu = Integer.valueOf(request.getParameter("soThuTu"));
				ShowRe(request, response, soThuTu);
				break;
			default:
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			Insert(request, response);
		} catch (SQLException e) {
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

	public void ShowInfo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		String email = UserDao.LayEmail(session.getAttribute("username").toString());
		Integer maSV = StudentDao.LayMaSV(email);
		session.setAttribute("maSV", maSV);
		List<Certification> cf = CertificationDao.XemGiayXacNhan(maSV);
		request.setAttribute("listCerInfo", cf);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student/certification.jsp");
		dispatcher.forward(request, response);
	}

	public void Delete(HttpServletRequest request, HttpServletResponse response, String loaitk)
			throws SQLException, ServletException, IOException {
		System.out.println(loaitk);
		try {
			int result = CertificationDao.DeleteCertification(Integer.parseInt(request.getParameter("soThuTu")));
			if (result == 1)
				request.setAttribute("NOTIFICATION", "Delete Successfully");
			if (loaitk.equals("sv"))
				ShowInfo(request, response);
			else if (loaitk.equals("ctv"))
				ShowInfoColab(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Insert(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		Integer maSV = Integer.valueOf(session.getAttribute("maSV").toString());
		LocalDateTime thoiGianDangKy = LocalDateTime.now();
		Integer soLuong = Integer.valueOf(request.getParameter("soLuong"));
		String lyDo = request.getParameter("lyDo");
		String maLoaiGiay = CertificationDao.LayMaLoaiGiay(request.getParameter("tenLoaiGiay"));
		Integer namHoc = null;
		Integer hocKy = null;
		String maHocKy = null;
		if (maLoaiGiay.equals("G004"))
			try {
				namHoc = Integer.valueOf(request.getParameter("namHoc"));
				hocKy = Integer.valueOf(request.getParameter("hocKy"));
				maHocKy = DataDao.MaHocKy(namHoc, hocKy);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Certification cf = new Certification(maLoaiGiay, soLuong, lyDo, thoiGianDangKy, maSV, maHocKy);
		CertificationDao.InsertCertification(cf);
		SendEmailRequest(request, response, cf);
		ShowInfo(request, response);
	}

	public void SendEmailRequest(HttpServletRequest request, HttpServletResponse response, Certification cf)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext context = getServletContext();
		String host = context.getInitParameter("host");
		String port = context.getInitParameter("port");
		String user = context.getInitParameter("user");
		String pass = context.getInitParameter("pass");
		String mess = "Mã Sinh Viên: " + cf.getMaSV() + "\nTên Giấy Xác Nhận: " + request.getParameter("tenLoaiGiay")
				+ "\nSố Lượng: " + cf.getSoLuong().toString() + "\nThời Gian Đăng Ký: " + cf.StringGetThoiGianDangKy();
		if (cf.getMaHocKy() != null)
			mess = mess + "\nMã Học Kỳ: " + cf.getMaHocKy();
		try {
			EmailUtility.sendEmail(host, port, user, pass, "tuannguyen23823@gmail.com",
					"Request From Student: " + session.getAttribute("hoTen"), mess);
			request.setAttribute("NOTIFICATION", "Send Email Successfully");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void ShowInfoColab(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<Certification> cf = CertificationDao.CTVXemGiayXacNhan();
		request.setAttribute("tinhTrang", false);
		request.setAttribute("listCerInfoColab", cf);
		RequestDispatcher dispatcher = request.getRequestDispatcher("colab/certification.jsp");
		dispatcher.forward(request, response);
	}
	
	public void ShowRe(HttpServletRequest request, HttpServletResponse response, Integer soThuTu)
			throws SQLException, ServletException, IOException {
		Certification giay = CertificationDao.CTVXemLyDo(soThuTu);
		List<Certification> cf = CertificationDao.CTVXemGiayXacNhan();
		request.setAttribute("listCerInfoColab", cf);
		request.setAttribute("CerRe", giay);
		request.setAttribute("tinhTrang", giay.getTinhTrang());
		request.setAttribute("trangThai", giay.getTrangThai());
		request.setAttribute("soThuTu", soThuTu);
		RequestDispatcher dispatcher = request.getRequestDispatcher("colab/certification.jsp");
		dispatcher.forward(request, response);
	}

	public void Confirm(HttpServletRequest request, HttpServletResponse response, String trangThai)
			throws SQLException, ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Integer soThuTu = Integer.valueOf(request.getParameter("soThuTu"));
			LocalDateTime thoiGianNhan = LocalDateTime.now();
			Integer maNguoiXacNhan = ColabDao.LayMaCTV(session.getAttribute("hoTen").toString());
			String lyDo = request.getParameter("lyDoXacNhan");
			Certification cf = new Certification(soThuTu, thoiGianNhan, maNguoiXacNhan);
			cf.setTrangThai(trangThai);
			cf.setLyDo(lyDo);
			int result = CertificationDao.ConfirmCertification(cf);
			if (result == 1)
				request.setAttribute("NOTIFICATION", "Confirm Successfully");
			SendEmailReply(request, response, cf);
			ShowInfoColab(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SendEmailReply(HttpServletRequest request, HttpServletResponse response, Certification cf)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		String host = context.getInitParameter("host");
		String port = context.getInitParameter("port");
		String user = context.getInitParameter("user");
		String pass = context.getInitParameter("pass");
		Integer maSV = CertificationDao.LayMaSV(cf.getSoThuTu());
		String email = StudentDao.LayEMailTuMaSV(maSV);
		String tenLoaiGiay = CertificationDao.LayTenLoaiGiay(cf.getSoThuTu());
		String trangThai = CertificationDao.LayTrangThai(cf.getSoThuTu());
		String mess = "\nTên Giấy Xác Nhận: " + tenLoaiGiay + "\nTrạng Thái: " + trangThai + "\nLý Do: "
				+ cf.getLyDo() + "\nChi Tiết: Vui lòng xem ở trang Online";
		try {
			EmailUtility.sendEmail(host, port, user, pass, email, "Reply From Phòng CTSV", mess);
			request.setAttribute("NOTIFICATION", "Send Email Successfully");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void ShowInfoAdmin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<Certification> list_giay = CertificationDao.AdminXemGiayXacNhan();
		request.setAttribute("giay", list_giay.get(0));
		request.setAttribute("tinhTrang", list_giay.get(0).getTinhTrang());
		request.setAttribute("list_giay", list_giay);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin/manageCer.jsp");
		dispatcher.forward(request, response);
	}

	private void ShowInfoCer(HttpServletRequest request, HttpServletResponse response, String maLoaiGiay)
			throws SQLException, ServletException, IOException {
		List<Certification> list_giay = CertificationDao.AdminXemGiayXacNhan();
		System.out.println(maLoaiGiay);
		Certification giay = CertificationDao.XemChiTietGiayXacNhan(maLoaiGiay);
		request.setAttribute("giay", giay);
		request.setAttribute("tinhTrang", giay.getTinhTrang());
		request.setAttribute("list_giay", list_giay);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin/manageCer.jsp");
		dispatcher.forward(request, response);
	}

	private void InsertCer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Certification> list_giay = CertificationDao.AdminXemGiayXacNhan();

		try {
			BigDecimal chiPhi = getBigDecimalParameter(request, "chiPhi");
			String maLoaiGiay = request.getParameter("maLoaiGiay");

			if (isMaLoaiGiayExist(list_giay, maLoaiGiay)) {
				request.setAttribute("warning", "Đã tồn tại mã loại giấy!!!");
			} else {
				HttpSession session = request.getSession();
				String tenLoaiGiay = request.getParameter("tenLoaiGiay");
				String moTa = request.getParameter("moTa");
				String email = UserDao.LayEmail(session.getAttribute("username").toString());
				Integer maNguoiXacNhan = AdminDao.LayMaQuanTriVien(email);
				Certification cf = new Certification(maLoaiGiay, tenLoaiGiay, chiPhi, true, moTa, maNguoiXacNhan);
				try {
					int result = CertificationDao.InsertCer(cf);
					if (result == 1) {
						ShowInfoAdmin(request, response);
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.setAttribute("giay", list_giay.get(0));
			request.setAttribute("list_giay", list_giay);
			request.setAttribute("tinhTrang", list_giay.get(0).getTinhTrang());
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin/manageCer.jsp");
			dispatcher.forward(request, response);
		} catch (NumberFormatException e) {
			request.setAttribute("warning", "Chi phí không đúng định dạng");
			request.setAttribute("giay", list_giay.get(0));
			request.setAttribute("list_giay", list_giay);
			request.setAttribute("tinhTrang", list_giay.get(0).getTinhTrang());
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin/manageCer.jsp");
			dispatcher.forward(request, response);
		}
	}

	private BigDecimal getBigDecimalParameter(HttpServletRequest request, String parameterName)
			throws NumberFormatException {
		return new BigDecimal(request.getParameter(parameterName));
	}

	private boolean isMaLoaiGiayExist(List<Certification> list_giay, String maLoaiGiay) {
		for (Certification giay : list_giay) {
			if (giay.getMaLoaiGiay().equals(maLoaiGiay)) {
				return true;
			}
		}
		return false;
	}

	private void UpdateCer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Certification> list_giay = CertificationDao.AdminXemGiayXacNhan();

		try {
			BigDecimal chiPhi = getBigDecimalParameter(request, "chiPhi");
			String maLoaiGiay = request.getParameter("maLoaiGiay");

			if (!isMaLoaiGiayExist(list_giay, maLoaiGiay)) {
				request.setAttribute("warning", "Không tồn tại mã loại giấy!!!");
			} else {
				HttpSession session = request.getSession();
				String tenLoaiGiay = request.getParameter("tenLoaiGiay");
				Boolean tinhTrang = Boolean.valueOf(request.getParameter("tinhTrang"));
				String email = UserDao.LayEmail(session.getAttribute("username").toString());
				String moTa = request.getParameter("moTa");
				Integer maNguoiXacNhan = AdminDao.LayMaQuanTriVien(email);
				Certification cf = new Certification(maLoaiGiay, tenLoaiGiay, chiPhi, tinhTrang, moTa, maNguoiXacNhan);

				try {
					int result = CertificationDao.UpdateCer(cf);
					if (result == 1) {
						ShowInfoAdmin(request, response);
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.setAttribute("giay", list_giay.get(0));
			request.setAttribute("list_giay", list_giay);
			request.setAttribute("tinhTrang", list_giay.get(0).getTinhTrang());
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin/manageCer.jsp");
			dispatcher.forward(request, response);
		} catch (NumberFormatException e) {
			request.setAttribute("warning", "Chi phí không đúng định dạng");
			request.setAttribute("giay", list_giay.get(0));
			request.setAttribute("tinhTrang", list_giay.get(0).getTinhTrang());
			request.setAttribute("list_giay", list_giay);
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin/manageCer.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void ShowInfoDash(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<Certification> list_duyet = CertificationDao.ThongKeDuyet();
		List<Certification> list_yeuCau = CertificationDao.ThongKeYeuCau();
		request.setAttribute("list_duyet", list_duyet);
		request.setAttribute("list_yeuCau", list_yeuCau);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin/dash.jsp");
		dispatcher.forward(request, response);
	}

}
