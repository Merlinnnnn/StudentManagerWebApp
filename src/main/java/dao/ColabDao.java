package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.User;
import utils.JDBCUtils;

public class ColabDao {
	private final static String SELECT_CongTacVien = "SELECT * FROM CongTacVien WHERE Email = ?";
	private final static String EMAILTuMa_CongTacVien = "SELECT Email FROM CongTacVien WHERE MaCTV = ?";
	private final static String HoTen_CongTacVien = "SELECT HoTen FROM CongTacVien WHERE Email = ?";
	private final static String Ma_CongTacVien = "SELECT MaCTV FROM CongTacVien WHERE HoTen = ?";
	private final static String UPDATEINFO_SQL = "CALL SuaThongTinLienLacCongTacVien(?,?,?,?)";
	private final static String AdminXemCongTacVien = "SELECT MaCTV, HoTen, TrangThai FROM CongTacVien";
	public static User xemCongTacVien(String email) {
		User user = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CongTacVien);) {
			preparedStatement.setString(1, email);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String hoTen = rs.getString("HoTen");
				LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();
				String gioiTinh = rs.getString("gioiTinh");
				String diaChi = rs.getString("DiaChi");
				String soDT = rs.getString("SoDienThoai");
				LocalDate ngayThamGia = rs.getDate("NgayThamGia").toLocalDate();
				boolean trangThai = rs.getBoolean("TrangThai");
				user = new User(email, hoTen, ngaySinh, gioiTinh, diaChi, soDT, ngayThamGia, trangThai);
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return user;
	}

	public static List<User> AdminXemCTV() {
		List<User> list_user = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(AdminXemCongTacVien);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer ma = rs.getInt("MaCTV");
				String hoTen = rs.getString("HoTen");
				boolean trangThai = rs.getBoolean("TrangThai");
				list_user.add(new User(hoTen, trangThai, ma));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return list_user;
	}
	
	public static String LayEmailTuMaCTV(Integer maCTV) {
		String email = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(EMAILTuMa_CongTacVien);) {
			preparedStatement.setInt(1, maCTV);
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

	public static String LayHoTen(String email) {
		String hoTen = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(HoTen_CongTacVien);) {
			preparedStatement.setString(1, email);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				hoTen = rs.getString("HoTen");
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return hoTen;
	}

	public static Integer LayMaCTV(String hoTen) {
		Integer maCTV = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(Ma_CongTacVien);) {
			preparedStatement.setString(1, hoTen);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				maCTV = rs.getInt("MaCTV");
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return maCTV;
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
			User new_user = xemCongTacVien(user.getEmail());
			if (new_user.getEmail().equals(user.getEmail()) && new_user.getDiaChi().equals(user.getDiaChi())
					&& new_user.getSoDienThoai().equals(user.getSoDienThoai()))
				result = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return result;
	}
}
