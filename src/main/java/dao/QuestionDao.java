package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Question;
import utils.JDBCUtils;

public class QuestionDao {
	private static String guiHoiDap = "CALL GuiHoiDap(?,?,?)";
	private static String xemHoiDap = "SELECT H.MaCauHoi, H.NoiDung, H.NgayGui, H.TrangThai, P.NoiDungPHanHoi, P.NgayPhanHoi "
			+ "FROM HoiDap AS H INNER JOIN PhanHoi AS P " + "ON H.MaCauHoi = P.MaPhanHoi WHERE MaSV = ?;";
	private static String xemPhanHoi = "SELECT * FROM XemPhanHoi;";
	private static String guiPhanHoi = "CALL GuiPhanHoi(?,?,?,?)";
	private static String LayMaSV = "SELECT MaSV FROM HoiDap WHERE MaCauHoi = ?;";

	public static void GuiHoiDap(Question qs) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(guiHoiDap)) {
			preparedStatement.setInt(1, qs.getMaSV());
			preparedStatement.setString(2, qs.getNoiDung());
			preparedStatement.setTimestamp(3, Timestamp.valueOf(qs.getNgayGui()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
	}

	public static void GuiPhanHoi(Question qs) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(guiPhanHoi)) {
			preparedStatement.setInt(1, qs.getMaCauHoi());
			preparedStatement.setString(2, qs.getPhanHoi());
			preparedStatement.setTimestamp(3, Timestamp.valueOf(qs.getNgayPhanHoi()));
			preparedStatement.setInt(4, qs.getMaCTV());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
	}

	public static Integer LayMaSV(Integer maCauHoi) {
		Integer maSV = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(LayMaSV);) {
			preparedStatement.setInt(1, maCauHoi);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				maSV = rs.getInt("MaSV");
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return maSV;
	}

	public static List<Question> XemHoiDap(Integer maSV) {
		List<Question> list_qs = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(xemHoiDap)) {
			preparedStatement.setInt(1, maSV);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer maCauHoi = rs.getInt("MaCauHoi");
				String noiDung = rs.getString("NoiDung");
				LocalDateTime ngayGui = rs.getTimestamp("NgayGui").toLocalDateTime();
				Boolean trangThai = rs.getBoolean("TrangThai");
				String phanHoi = rs.getString("NoiDungPhanHoi");
				LocalDateTime ngayPhanHoi = null;
				Timestamp ts = rs.getTimestamp("NgayPhanHoi");
				if (ts != null)
					ngayPhanHoi = ts.toLocalDateTime();
				list_qs.add(new Question(maCauHoi, noiDung, ngayGui, trangThai, phanHoi, ngayPhanHoi));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return list_qs;
	}

	public static List<Question> XemPhanHoi() {
		List<Question> list_qs = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(xemPhanHoi)) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer maCauHoi = rs.getInt("MaCauHoi");
				Integer maSV = rs.getInt("MaSV");
				String noiDung = rs.getString("NoiDung");
				LocalDateTime ngayGui = rs.getTimestamp("NgayGui").toLocalDateTime();
				String phanHoi = rs.getString("NoiDungPhanHoi");
				LocalDateTime ngayPhanHoi = null;
				Timestamp ts = rs.getTimestamp("NgayPhanHoi");
				if (ts != null)
					ngayPhanHoi = ts.toLocalDateTime();
				Integer maCTV = rs.getInt("MaCTV");
				if (maCTV == 0)
					maCTV = null;
				list_qs.add(new Question(maCauHoi, maSV, noiDung, ngayGui, phanHoi, ngayPhanHoi, maCTV));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return list_qs;
	}
}