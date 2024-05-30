package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.User;
import utils.JDBCUtils;

public class AdminDao {
	private final static String SELECT_QuanTriVien = "SELECT * FROM QuanTriVien WHERE Email = ?";
	private final static String UPDATEINFO_SQL = "CALL SuaThongTinLienLacQuanTriVien(?,?,?,?)";
	private final static String Ma_QuanTriVien = "SELECT MaQTV FROM QuanTriVien WHERE Email = ?";

	public static User xemQuanTriVien(String email) {
		User user = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QuanTriVien);) {
			preparedStatement.setString(1, email);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String hoTen = rs.getString("HoTen");
				LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();
				String gioiTinh = rs.getString("gioiTinh");
				String diaChi = rs.getString("DiaChi");
				String soDT = rs.getString("SoDienThoai");
				LocalDate ngayNhanChuc = rs.getDate("NgayNhanChuc").toLocalDate();
				user = new User(email, hoTen, ngaySinh, gioiTinh, diaChi, soDT, ngayNhanChuc);
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return user;
	}

	public static int changeInfo(User user, String oldEmail) throws ClassNotFoundException {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATEINFO_SQL)) {
			preparedStatement.setString(1, oldEmail);
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getDiaChi());
			preparedStatement.setString(4, user.getSoDienThoai());

			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
			User new_user = xemQuanTriVien(user.getEmail());
			if (new_user.getEmail().equals(user.getEmail()) && new_user.getDiaChi().equals(user.getDiaChi())
					&& new_user.getSoDienThoai().equals(user.getSoDienThoai()))
				result = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return result;
	}

	public static Integer LayMaQuanTriVien(String email) {
		Integer maQTV = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(Ma_QuanTriVien);) {
			preparedStatement.setString(1, email);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				maQTV = rs.getInt("MaQTV");
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return maQTV;
	}
}
