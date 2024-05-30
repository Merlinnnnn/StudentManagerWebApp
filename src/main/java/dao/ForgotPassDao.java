package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ForgotPass;
import utils.JDBCUtils;

public class ForgotPassDao {
	public boolean forgotPass(ForgotPass fp) {
		String CHECK_SQL = "SELECT * FROM taikhoan WHERE TenDangNhap = ? AND Email = ?";
		String email = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CHECK_SQL)) {
				preparedStatement.setString(1, fp.getTenDN());
				preparedStatement.setNString(2, fp.getEmail());
				System.out.println(preparedStatement);
				
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
					email = rs.getString("Email");
					fp.setMatKhau(rs.getNString("MatKhau"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (email != null)
			return true;
		return false;
	}
}
