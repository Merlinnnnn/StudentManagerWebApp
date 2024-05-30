package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ChangePassword;
import utils.JDBCUtils;

public class ChangePasswordDao {
	public int changePass(ChangePassword cp) {
		String CHECK_PASS_SQL = "SELECT * FROM taikhoan WHERE TenDangNhap = ? AND MatKhau = ?";
		String CHANGE_PASS_SQL = "UPDATE taikhoan SET MatKhau = ? WHERE Email = ?";
		int result = 0;
		String matKhauCu = null;
		String email = null;
		try (Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(CHECK_PASS_SQL)) {
			preparedStatement.setString(1, cp.getTenDN());
			preparedStatement.setNString(2, cp.getMatKhauCu());
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				matKhauCu = rs.getString("MatKhau");
				email = rs.getString("Email");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (matKhauCu != null) {
			String matKhauMoi = cp.getMatKhauMoi();
			if(matKhauMoi.equals(cp.getXacNhanMatKhau())) {
				try (Connection connection = JDBCUtils.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASS_SQL)) {
					preparedStatement.setString(1, matKhauMoi);
					preparedStatement.setString(2, email);
					System.out.println(preparedStatement);
					result = preparedStatement.executeUpdate();
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
