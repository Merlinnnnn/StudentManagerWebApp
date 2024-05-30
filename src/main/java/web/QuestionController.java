package web;

import java.io.IOException;
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

import dao.ColabDao;
import dao.QuestionDao;
import dao.StudentDao;
import dao.UserDao;
import model.Question;
import utils.EmailUtility;

/**
 * Servlet implementation class QuestionController
 */
@WebServlet("/question")
public class QuestionController extends HttpServlet {
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
			case "showReply":
				ShowReply(request, response);
				break;
			case "reply":
				Reply(request, response);
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
		request.setCharacterEncoding("UTF-8");
		try {
			GuiHoiDap(request, response);
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

	protected void GuiHoiDap(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		Integer maSV = Integer.valueOf(session.getAttribute("maSV").toString());
		String noiDung = request.getParameter("noiDung");
		LocalDateTime ngayGui = LocalDateTime.now();
		Question qs = new Question(maSV, noiDung, ngayGui);
		QuestionDao.GuiHoiDap(qs);
		SendEmailQuestion(request, response, qs);
		ShowInfo(request, response);
	}

	public void ShowInfo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		String email = UserDao.LayEmail(session.getAttribute("username").toString());
		Integer maSV = StudentDao.LayMaSV(email);
		session.setAttribute("maSV", maSV);
		List<Question> list_qs = QuestionDao.XemHoiDap(maSV);
		request.setAttribute("listQues", list_qs);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student/question.jsp");
		dispatcher.forward(request, response);
	}

	public void SendEmailQuestion(HttpServletRequest request, HttpServletResponse response, Question qs)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext context = getServletContext();
		String host = context.getInitParameter("host");
		String port = context.getInitParameter("port");
		String user = context.getInitParameter("user");
		String pass = context.getInitParameter("pass");
		String mess = "Mã Sinh Viên: " + qs.getMaSV() + "\nNội Dung Gửi: " + qs.getNoiDung() + "\nNgày Gửi: "
				+ qs.StringGetNgayGui();
		try {
			EmailUtility.sendEmail(host, port, user, pass, "tuannguyen23823@gmail.com",
					"Question From Student: " + session.getAttribute("hoTen"), mess);
			request.setAttribute("NOTIFICATION", "Send Email Successfully");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void ShowReply(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<Question> list_qs = QuestionDao.XemPhanHoi();
		request.setAttribute("listQuesColab", list_qs);
		RequestDispatcher dispatcher = request.getRequestDispatcher("colab/question.jsp");
		dispatcher.forward(request, response);
	}

	protected void Reply(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		Integer maCauHoi = Integer.valueOf(request.getParameter("maCauHoi"));
		String noiDungPhanHoi = request.getParameter("ndPhanHoi");
		LocalDateTime ngayPhanHoi = LocalDateTime.now();
		Integer maCTV = ColabDao.LayMaCTV(session.getAttribute("hoTen").toString());
		Question qs = new Question(maCauHoi, noiDungPhanHoi, ngayPhanHoi, maCTV);
		QuestionDao.GuiPhanHoi(qs);
		SendEmailReply(request, response, qs);
		ShowReply(request, response);
	}

	public void SendEmailReply(HttpServletRequest request, HttpServletResponse response, Question qs)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		String host = context.getInitParameter("host");
		String port = context.getInitParameter("port");
		String user = context.getInitParameter("user");
		String pass = context.getInitParameter("pass");
		Integer maSV = QuestionDao.LayMaSV(qs.getMaCauHoi());
		String email = StudentDao.LayEMailTuMaSV(maSV);
		String mess = "Mã Câu Hỏi: " + qs.getMaCauHoi() + "\nMã Sinh Viên: " + maSV + "\nNội Dung Phản Hồi: "
				+ qs.getPhanHoi() + "\nNgày Phản Hồi: " + qs.StringGetNgayPhanHoi()
				+ "\nChi Tiết: Vui lòng xem ở trang Online";
		try {
			EmailUtility.sendEmail(host, port, user, pass, email, "Reply Question from Phòng CTSV", mess);
			request.setAttribute("NOTIFICATION", "Send Email Successfully");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
