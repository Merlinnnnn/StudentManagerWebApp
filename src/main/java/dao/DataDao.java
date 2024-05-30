package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.JDBCUtils;

public class DataDao {
	public static List<String> Lop() throws ClassNotFoundException {
		List<String> list_lop = new ArrayList<>();

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT MaLop FROM Lop")) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				list_lop.add(rs.getString("MaLop"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return list_lop;
	}
	
	public static List<String> TenToChuc() throws ClassNotFoundException {
		List<String> list_tenToChuc = new ArrayList<>();

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT TenToChuc FROM ToChuc")) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				list_tenToChuc.add(rs.getString("TenToChuc"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return list_tenToChuc;
	}
	
	public static List<String> MaToChuc() throws ClassNotFoundException {
		List<String> list_maToChuc = new ArrayList<>();

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT MaToChuc FROM ToChuc")) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				list_maToChuc.add(rs.getString("MaToChuc"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return list_maToChuc;
	}

	public static Integer NienKhoa(String lop) throws ClassNotFoundException {
		Integer nienKhoa = null;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT NienKhoa FROM Lop WHERE MaLop = ?")) {
			preparedStatement.setString(1, lop);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				nienKhoa = rs.getInt("NienKhoa");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return nienKhoa;
	}
	
	public static List<Integer> NamHoc(String hoTen) throws ClassNotFoundException {
		List<Integer> list_namHoc = new ArrayList<>();
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT nh.namhoc\r\n" + "FROM namhoc nh\r\n"
								+ "JOIN nienkhoa nk ON nh.namhoc >= nk.nambatdau AND nh.namhoc <= nk.namketthuc\r\n"
								+ "WHERE nk.nienkhoa = (select nienKhoa from sinhvien where HoTen = ?);")) {
			preparedStatement.setString(1, hoTen);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				list_namHoc.add(rs.getInt("NamHoc"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return list_namHoc;
	}

	public static List<String> Khoa() throws ClassNotFoundException {
		List<String> khoa = new ArrayList<>();

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT TenKhoa FROM Khoa")) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				khoa.add(rs.getString("TenKhoa"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return khoa;
	}

	public static List<String> TenLoaiGiay() throws ClassNotFoundException {
		List<String> list_tenLoaiGiay = new ArrayList<>();

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT TenLoaiGiay FROM LoaiGiay")) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				list_tenLoaiGiay.add(rs.getString("TenLoaiGiay"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return list_tenLoaiGiay;
	}
	
	public static String MaHocKy(Integer namHoc, Integer hocKy) throws ClassNotFoundException {
		String maHocKy = null;
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT MaHocKy FROM HocKy WHERE HocKy = ? AND NamHoc = ?")) {
			preparedStatement.setInt(1, hocKy);
			preparedStatement.setInt(2, namHoc);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				maHocKy = rs.getString("MaHocKy");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return maHocKy;
	}
}
