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

public class StudentDao {
	private final static String SELECT_SinhVien = "SELECT * FROM sinhvien WHERE Email = ?";
	private final static String HoTen_SinhVien= "SELECT HoTen FROM sinhvien WHERE Email = ?";
	private final static String Ma_SinhVien= "SELECT MaSV FROM sinhvien WHERE Email = ?";
	private final static String EmailMa_SinhVien= "SELECT Email FROM sinhvien WHERE MaSV = ?";
	private final static String UPDATEINFO_SQL = "CALL SuaThongTinLienLac(?,?,?,?,?)";
	private final static String AdminXemSinhVien = "SELECT MaSV, HoTen, Khoa, NienKhoa, TrangThai FROM SinhVien";
	private final static String SetTenDangNhap = "SELECT TenDangNhap FROM TaiKhoan WHERE Email = ?";
	private final static String SetMatKhau = "SELECT MatKhau FROM TaiKhoan WHERE Email = ?";
	public static User xemSinhVien(String email) {
		User user = null;
        try (Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SinhVien);) {
            preparedStatement.setString(1, email);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String hoTen = rs.getString("HoTen");
                LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();
                String gioiTinh = rs.getString("gioiTinh");
                String diaChi = rs.getString("DiaChi");
                String cccd = rs.getString("CCCD");
                String soDT = rs.getString("SoDienThoai");
                LocalDate ngayNhapHoc = rs.getDate("NgayNhapHoc").toLocalDate();
                String lop = rs.getString("Lop");
                Integer nienKhoa = rs.getInt("nienKhoa");
                boolean trangThai = rs.getBoolean("TrangThai");
                String khoa = rs.getString("Khoa");
                user = new User(email, hoTen, ngaySinh, gioiTinh, diaChi, cccd, soDT, ngayNhapHoc, lop, nienKhoa, trangThai, khoa);
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return user;
	}
	
	public static List<User> AdminXemSinhVien() {
		List<User> user = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(AdminXemSinhVien);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String hoTen = rs.getString("HoTen");
                Integer nienKhoa = rs.getInt("NienKhoa");
                String khoa = rs.getString("Khoa");
                Integer ma = rs.getInt("MaSV");
                boolean trangThai = rs.getBoolean("TrangThai");
                user.add(new User(hoTen, nienKhoa, khoa, ma, trangThai));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return user;
	}
	
	public static String LayHoTen(String email) {
		String hoTen = null;
		try (Connection connection = JDBCUtils.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(HoTen_SinhVien);) {
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
	
	public static String LayTenDangNhap(String email) {
		String tenDN = null;
		try (Connection connection = JDBCUtils.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SetTenDangNhap);) {
	            preparedStatement.setString(1, email);
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	            	tenDN = rs.getString("tenDangNhap");
	            }
	        } catch (SQLException exception) {
	            JDBCUtils.printSQLException(exception);
	        }
	        return tenDN;
	}
	
	public static String LayMatKhau(String email) {
		String matKhau = null;
		try (Connection connection = JDBCUtils.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SetMatKhau);) {
	            preparedStatement.setString(1, email);
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	            	matKhau = rs.getString("MatKhau");
	            }
	        } catch (SQLException exception) {
	            JDBCUtils.printSQLException(exception);
	        }
	        return matKhau;
	}
	
	public static Integer LayMaSV(String email) {
		Integer maSV = null;
		try (Connection connection = JDBCUtils.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(Ma_SinhVien);) {
	            preparedStatement.setString(1, email);
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
	
	public static String LayEMailTuMaSV(Integer maSV) {
		String email = null;
		try (Connection connection = JDBCUtils.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(EmailMa_SinhVien);) {
	            preparedStatement.setInt(1, maSV);
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
	
	public int changeInfo(User user, String oldEmail) throws ClassNotFoundException {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATEINFO_SQL)) {
			preparedStatement.setString(1, oldEmail);
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getDiaChi());
			preparedStatement.setString(4, user.getCccd());
			preparedStatement.setString(5, user.getSoDienThoai());
			
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
			User new_user = xemSinhVien(user.getEmail());
			if(new_user.getEmail().equals(user.getEmail()) && new_user.getDiaChi().equals(user.getDiaChi())
					&& new_user.getCccd().equals(user.getCccd()) && new_user.getSoDienThoai().equals(user.getSoDienThoai()))
				result = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return result;
	}
}
