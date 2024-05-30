package dao;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import model.User;
import utils.JDBCUtils;

public class UserDao {
	private final static String INSERTSV_SQL = "CALL ThemTaiKhoanSinhVien(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String UPDATESV_SQL = "CALL SuaTaiKhoanSinhVien(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String DELETESV_SQL = "UPDATE SinhVien SET TrangThai = 0 WHERE MaSV = ?";
	private final static String INSERTCTV_SQL = "CALL ThemTaiKhoanCongTacVien(?,?,?,?,?,?,?,?,?,?)";
	private final static String UPDATECTV_SQL = "CALL SuaTaiKhoanCongTacVien(?,?,?,?,?,?,?,?,?,?)";
	private final static String DELETECTV_SQL = "UPDATE CongTacVien SET TrangThai = 0 WHERE MaCTV = ?";
	private final static String EMAIL_SQL = "SELECT Email FROM TaiKhoan WHERE TenDangNhap = ?";

	public static void ImportExcel(HttpServletRequest request, InputStream inp) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DataFormatter dataFormatter = new DataFormatter();
		try {
			// inp = new FileInputStream("D://import.xls");
			HSSFWorkbook wb = new HSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			System.out.println(sheet.getLastRowNum());
			for (int i = 1; i < sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				try {
					String tenDangNhap = dataFormatter.formatCellValue(row.getCell(0));
					String matKhau = dataFormatter.formatCellValue(row.getCell(1));
					String email = dataFormatter.formatCellValue(row.getCell(2));
					String loaitk = row.getCell(3).getStringCellValue();
					String hoTen = row.getCell(4).getStringCellValue();
					Cell dateCell = row.getCell(5);
					String strDateSinh = df.format(dateCell.getDateCellValue());
					LocalDate ngaySinh = LocalDate.parse(strDateSinh, dtf);
					String gioiTinh = dataFormatter.formatCellValue(row.getCell(6));
					String diaChi = dataFormatter.formatCellValue(row.getCell(7));
					String cccd = dataFormatter.formatCellValue(row.getCell(8));
					String sdt = dataFormatter.formatCellValue(row.getCell(9));
					dateCell = row.getCell(10);
					String strDateTao = dataFormatter.formatCellValue(row.getCell(10));
					DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate ngayTao = LocalDate.parse(strDateTao, formatterInput);
					String lop = dataFormatter.formatCellValue(row.getCell(11));
					Integer nienKhoa = Integer.valueOf(dataFormatter.formatCellValue(row.getCell(12)));
					String khoa = row.getCell(13).getStringCellValue();
					User user = new User(tenDangNhap, matKhau, email, loaitk, hoTen, ngaySinh, gioiTinh, diaChi, cccd,
							sdt, ngayTao, lop, nienKhoa, khoa);
					insertStudent(user);
				} catch (Exception e) {
					String message = "Dòng " + (i + 1) + ": Bị Lỗi";
					request.setAttribute("mess", message);
				}
			}
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String LayEmail(String tenDangNhap) {
		String email = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(EMAIL_SQL);) {
			preparedStatement.setString(1, tenDangNhap);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				email = rs.getString("Email");
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return email;
	}

	public static int insertStudent(User user) throws ClassNotFoundException {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERTSV_SQL)) {
			preparedStatement.setString(1, user.getTenDN());
			preparedStatement.setString(2, user.getMatKhau());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getLoaiTK());
			preparedStatement.setString(5, user.getHoTen());
			preparedStatement.setDate(6, JDBCUtils.getSQLDate(user.getNgaySinh()));
			preparedStatement.setString(7, user.getGioiTinh());
			preparedStatement.setString(8, user.getDiaChi());
			preparedStatement.setString(9, user.getCccd());
			preparedStatement.setString(10, user.getSoDienThoai());
			preparedStatement.setDate(11, JDBCUtils.getSQLDate(user.getNgayTao()));
			preparedStatement.setString(12, user.getLop());
			preparedStatement.setInt(13, user.getNienKhoa());
			preparedStatement.setString(14, user.getKhoa());

			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return result;
	}

	public static int updateStudent(User user, String email) throws ClassNotFoundException {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATESV_SQL)) {
			preparedStatement.setString(1, user.getTenDN());
			preparedStatement.setString(2, user.getMatKhau());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, user.getHoTen());
			preparedStatement.setDate(6, JDBCUtils.getSQLDate(user.getNgaySinh()));
			preparedStatement.setString(7, user.getGioiTinh());
			preparedStatement.setString(8, user.getDiaChi());
			preparedStatement.setString(9, user.getCccd());
			preparedStatement.setString(10, user.getSoDienThoai());
			preparedStatement.setString(11, user.getLop());
			preparedStatement.setInt(12, user.getNienKhoa());
			preparedStatement.setString(13, user.getKhoa());
			preparedStatement.setInt(14, user.getMa());

			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return result;
	}

	public static int deleteStudent(Integer maSV) throws ClassNotFoundException {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETESV_SQL)) {
			preparedStatement.setInt(1, maSV);

			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return result;
	}

	public static int insertColab(User user) throws ClassNotFoundException {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERTCTV_SQL)) {
			preparedStatement.setString(1, user.getTenDN());
			preparedStatement.setString(2, user.getMatKhau());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getLoaiTK());
			preparedStatement.setString(5, user.getHoTen());
			preparedStatement.setDate(6, JDBCUtils.getSQLDate(user.getNgaySinh()));
			preparedStatement.setString(7, user.getGioiTinh());
			preparedStatement.setString(8, user.getDiaChi());
			preparedStatement.setString(9, user.getSoDienThoai());
			preparedStatement.setDate(10, JDBCUtils.getSQLDate(user.getNgayTao()));

			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return result;
	}

	public static int updateColab(User user, String email) throws ClassNotFoundException {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATECTV_SQL)) {
			preparedStatement.setString(1, user.getTenDN());
			preparedStatement.setString(2, user.getMatKhau());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, user.getHoTen());
			preparedStatement.setDate(6, JDBCUtils.getSQLDate(user.getNgaySinh()));
			preparedStatement.setString(7, user.getGioiTinh());
			preparedStatement.setString(8, user.getDiaChi());
			preparedStatement.setString(9, user.getSoDienThoai());
			preparedStatement.setInt(10, user.getMa());

			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return result;
	}

	public static int deleteColab(Integer maCTV) throws ClassNotFoundException {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETECTV_SQL)) {
			preparedStatement.setInt(1, maCTV);

			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return result;
	}
}
