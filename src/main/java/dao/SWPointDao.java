package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.SWPoint;
import utils.JDBCUtils;

public class SWPointDao {
	private static String XemCTXH = "CALL XemDanhSachCTXH(?)";
	private static String XemHoatDongConLai = "CALL XemDanhSachHoatDongConLai(?)";
	private static String thamGiaHD = "CALL ThamGiaHoatDong(?,?,?)";
	private static String diemCTXH = "SELECT COALESCE(SUM(h.diem), 0) as tongdiem from diemctxh as d inner join hoatdong as h on d.mahd = h.mahoatdong where masv = ?;";
	private static String AdminXemHoatDong = "SELECT * FROM HoatDong";
	private static String XemChiTietHoatDong = "SELECT * FROM HoatDong WHERE MaHoatDong = ?";
	private static String InsertHD = "INSERT INTO HoatDong(TenHoatDong, MoTa, NgayBatDau, Diem, NgayKetThuc, ToChuc, MaNguoiTao) VALUES (?,?,?,?,?,?,?);";
	private static String UpdateHD = "Update HoatDong SET TenHoatDong = ?, MoTa = ?, NgayBatDau = ?, Diem = ?,"
			+ " NgayKetThuc = ?, ToChuc = ?, MaNguoiTao= ? WHERE MaHoatDong = ?;";
	public static List<SWPoint> XemDanhSachCTXH(Integer maSV) {
		List<SWPoint> list_sw = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(XemCTXH);) {
			preparedStatement.setInt(1, maSV);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer maHoatDong = rs.getInt("MaHoatDong");
				String tenHoatDong = rs.getString("TenHoatDong");
				String moTa = rs.getString("MoTa");
				LocalDate ngayBatDau = rs.getDate("NgayBatDau").toLocalDate();
				LocalDate ngayKetThuc = rs.getDate("NgayKetThuc").toLocalDate();
				Integer diem = rs.getInt("Diem");
				String toChuc = rs.getString("TenToChuc");
				list_sw.add(new SWPoint(maHoatDong, tenHoatDong, ngayBatDau, ngayKetThuc, diem, toChuc, moTa));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return list_sw;
	}

	public static List<SWPoint> XemDanhSachHoatDongConLai(Integer maSV) {
		List<SWPoint> list_sw = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(XemHoatDongConLai);) {
			preparedStatement.setInt(1, maSV);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer maHoatDong = rs.getInt("MaHoatDong");
				String tenHoatDong = rs.getString("TenHoatDong");
				String moTa = rs.getString("MoTa");
				LocalDate ngayBatDau = rs.getDate("NgayBatDau").toLocalDate();
				LocalDate ngayKetThuc = rs.getDate("NgayKetThuc").toLocalDate();
				Integer diem = rs.getInt("Diem");
				String toChuc = rs.getString("TenToChuc");
				list_sw.add(new SWPoint(maHoatDong, tenHoatDong, ngayBatDau, ngayKetThuc, diem, toChuc, moTa));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return list_sw;
	}

	public static void InsertCertification(SWPoint sw) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(thamGiaHD)) {
			preparedStatement.setInt(1, sw.getMaSV());
			preparedStatement.setInt(2, sw.getMaHoatDong());
			preparedStatement.setDate(3, JDBCUtils.getSQLDate(sw.getNgayDangKy()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
	}

	public static Integer XemDiemCTXH(Integer maSV) {
		Integer diem = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(diemCTXH);) {
			preparedStatement.setInt(1, maSV);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				diem = rs.getInt("TongDiem");
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return diem;
	}

	public static List<SWPoint> XemDanhSachHoatDong() {
		List<SWPoint> list_sw = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(AdminXemHoatDong);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer maHoatDong = rs.getInt("MaHoatDong");
				String tenHoatDong = rs.getString("TenHoatDong");
				String moTa = rs.getString("MoTa");
				LocalDate ngayBatDau = rs.getDate("NgayBatDau").toLocalDate();
				LocalDate ngayKetThuc = rs.getDate("NgayKetThuc").toLocalDate();
				Integer diem = rs.getInt("Diem");
				String toChuc = rs.getString("ToChuc");
				Integer maNguoiTao = rs.getInt("MaNguoiTao");
				list_sw.add(
						new SWPoint(maHoatDong, tenHoatDong, ngayBatDau, ngayKetThuc, diem, toChuc, moTa, maNguoiTao));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return list_sw;
	}

	public static SWPoint XemChiTietHoatDong(Integer maHD) {
		SWPoint sw = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(XemChiTietHoatDong);) {
			preparedStatement.setInt(1, maHD);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer maHoatDong = rs.getInt("MaHoatDong");
				String tenHoatDong = rs.getString("TenHoatDong");
				String moTa = rs.getString("MoTa");
				LocalDate ngayBatDau = rs.getDate("NgayBatDau").toLocalDate();
				LocalDate ngayKetThuc = rs.getDate("NgayKetThuc").toLocalDate();
				Integer diem = rs.getInt("Diem");
				String toChuc = rs.getString("ToChuc");
				Integer maNguoiTao = rs.getInt("MaNguoiTao");
				sw = new SWPoint(maHoatDong, tenHoatDong, ngayBatDau, ngayKetThuc, diem, toChuc, moTa, maNguoiTao);
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return sw;
	}

	public static Integer Insert(SWPoint sw) {
		Integer result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(InsertHD)) {
			preparedStatement.setString(1, sw.getTenHoatDong());
			preparedStatement.setString(2, sw.getMoTa());
			preparedStatement.setDate(3, JDBCUtils.getSQLDate(sw.getNgayBatDau()));
			preparedStatement.setInt(4, sw.getDiem());
			preparedStatement.setDate(5, JDBCUtils.getSQLDate(sw.getNgayKetThuc()));
			preparedStatement.setString(6, sw.getToChuc());
			preparedStatement.setInt(7, sw.getMaNguoiTao());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return result;
	}
	
	public static Integer Update(SWPoint sw) {
		Integer result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UpdateHD)) {
			preparedStatement.setString(1, sw.getTenHoatDong());
			preparedStatement.setString(2, sw.getMoTa());
			preparedStatement.setDate(3, JDBCUtils.getSQLDate(sw.getNgayBatDau()));
			preparedStatement.setInt(4, sw.getDiem());
			preparedStatement.setDate(5, JDBCUtils.getSQLDate(sw.getNgayKetThuc()));
			preparedStatement.setString(6, sw.getToChuc());
			preparedStatement.setInt(7, sw.getMaNguoiTao());
			preparedStatement.setInt(8, sw.getMaHoatDong());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return result;
	}
}
