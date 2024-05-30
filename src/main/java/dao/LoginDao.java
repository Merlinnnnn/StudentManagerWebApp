package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import model.LoginBean;
import utils.JDBCUtils;

public class LoginDao {
	private static String LoaiTaiKhoan = "SELECT LoaiTaiKhoan FROM TaiKhoan WHERE TenDangNhap = ?";
	public boolean validate(LoginBean loginBean) throws ClassNotFoundException{
		boolean status = false;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try (Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM taikhoan WHERE TenDangNhap = ? AND MatKhau = ?")) {
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return status;
	}
	
	public String LayLoaiTaiKhoan(String tenDN) throws ClassNotFoundException{
		String loaitk = null;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try (Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(LoaiTaiKhoan)) {
			preparedStatement.setString(1, tenDN);
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())  {
				loaitk = rs.getString("LoaitaiKhoan");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return loaitk;
	}
}