package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Certification;
import utils.JDBCUtils;

public class CertificationDao {
	private final static String XemGiayXacNhan = "CALL GetGiayXacNhanByMaSV(?)";
	private final static String XoaGiayXacNhan = "CALL XoaGiayXacNhan(?)";
	private final static String DangKyGiayXacNhan = "CALL DangKyGiayXacNhan(?,?,?,?,?,?)";
	private final static String GetMaLoaiGiayFromTenLoaiGiay = "SELECT MaLoaiGiay FROM LoaiGiay WHERE TenLoaiGiay = ?";
	private final static String CTVXemGiayXacNhan = "SELECT * FROM CTVXemGiayXacNhan";
	private final static String DuyetGiayXacNhan = "CALL DuyetGiayXacNhan(?,?,?,?,?)";
	private final static String LayTenLoaiGiay = "SELECT TenLoaiGiay FROM LoaiGiay WHERE MaLoaiGiay = "
			+ "(SELECT MaLoaiGiay FROM XinGiayXacNhan WHERE SoThuTu = ?)";
	private final static String LayMaSV = "SELECT MaSV FROM XinGiayXacNhan WHERE SoThuTu = ?";
	private final static String LayTrangThai = "SELECT TrangThai FROM XacNhanXinGiay WHERE SoThuTu = ?";
	private final static String LayKhoaTuMaSV = "SELECT Khoa FROM Sinhvien WHERE MaSV = ?";
	private final static String LayNienKhoaTuMaSV = "SELECT NienKhoa FROM Sinhvien WHERE MaSV = ?";
	private final static String AdminXemGiay = "SELECT * FROM LoaiGiay";
	private final static String XemChiTietGiay = "SELECT * FROM LoaiGiay WHERE MaLoaiGiay = ?";
	private final static String InsertGiay = "INSERT INTO LoaiGiay VALUES(?,?,?,?,?,?);";
	private final static String UpdateGiay = "Update LoaiGiay SET ChiPhi = ?, TenLoaiGiay = ?, MaNguoiTao = ?, MoTa = ?, TrangThai = ? WHERE MaLoaiGiay = ?";
	private final static String ThongKeYeuCau = "SELECT SoThuTu, MaSV, ThoiGian, TrangThai FROM XinGiayXacNhan";
	private final static String ThongKeDuyet = "SELECT SoThuTu, MaNguoiXacNhan FROM XacNhanXinGiay WHERE MaNguoiXacNhan IS NOT NULL;";
	private final static String CTVXemLyDo = "SELECT X.LyDo AS LyDoGui, N.LyDo AS LyDoXacNhan, L.TrangThai AS TinhTrangGiay, X.TrangThai "
			+ "FROM XinGiayXacNhan X INNER JOIN XacNhanXinGiay N ON X.SoThuTu = N.SoThuTu "
			+ "INNER JOIN LoaiGiay L ON N.MaLoaiGiay = L.MaLoaiGiay WHERE X.SoThuTu = ?;";

	public static List<Certification> XemGiayXacNhan(Integer maSV) {
		List<Certification> cf = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(XemGiayXacNhan);) {
			preparedStatement.setInt(1, maSV);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				LocalDateTime thoiGianDangKy = rs.getTimestamp("ThoiGian").toLocalDateTime();
				String tenLoaiGiay = rs.getString("TenLoaiGiay");
				Integer soThuTu = rs.getInt("SoThuTu");
				Integer soLuong = rs.getInt("SoLuong");
				BigDecimal chiPhi = rs.getBigDecimal("ChiPhi");
				LocalDateTime thoiGianNhan = null;
				Timestamp ts = rs.getTimestamp("ThoiGianNhan");
				if (ts != null)
					thoiGianNhan = ts.toLocalDateTime();
				String trangThai = rs.getString("TrangThai");
				String maHocKy = rs.getString("MaHocKy");

				cf.add(new Certification(tenLoaiGiay, chiPhi, soThuTu, soLuong, trangThai, thoiGianDangKy, thoiGianNhan,
						maHocKy));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return cf;
	}

	public static int DeleteCertification(Integer soThuTu) throws ClassNotFoundException {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(XoaGiayXacNhan)) {
			preparedStatement.setInt(1, soThuTu);
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return result;
	}

	public static void InsertCertification(Certification cf) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DangKyGiayXacNhan)) {
			preparedStatement.setString(1, cf.getMaLoaiGiay());
			preparedStatement.setInt(2, cf.getSoLuong());
			preparedStatement.setString(3, cf.getLyDo());
			preparedStatement.setInt(4, cf.getMaSV());
			preparedStatement.setTimestamp(5, Timestamp.valueOf(cf.getThoiGianDangKy()));
			preparedStatement.setString(6, cf.getMaHocKy());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
	}

	public static String LayMaLoaiGiay(String tenLoaiGiay) {
		String maLoaiGiay = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GetMaLoaiGiayFromTenLoaiGiay);) {
			preparedStatement.setString(1, tenLoaiGiay);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				maLoaiGiay = rs.getString("MaLoaiGiay");
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return maLoaiGiay;
	}

	public static String LayTenLoaiGiay(Integer soThuTu) {
		String tenLoaiGiay = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(LayTenLoaiGiay);) {
			preparedStatement.setInt(1, soThuTu);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				tenLoaiGiay = rs.getString("TenLoaiGiay");
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return tenLoaiGiay;
	}

	public static String LayTrangThai(Integer soThuTu) {
		String trangThai = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(LayTrangThai);) {
			preparedStatement.setInt(1, soThuTu);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				trangThai = rs.getString("TrangThai");
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return trangThai;
	}

	public static String LayKhoa(Integer maSV) {
		String khoa = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(LayKhoaTuMaSV);) {
			preparedStatement.setInt(1, maSV);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				khoa = rs.getString("Khoa");
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return khoa;
	}

	public static Integer LayNienKhoa(Integer maSV) {
		Integer nienKhoa = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(LayNienKhoaTuMaSV);) {
			preparedStatement.setInt(1, maSV);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				nienKhoa = rs.getInt("NienKhoa");
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return nienKhoa;
	}

	public static Integer LayMaSV(Integer soThuTu) {
		Integer maSV = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(LayMaSV);) {
			preparedStatement.setInt(1, soThuTu);
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

	public static List<Certification> CTVXemGiayXacNhan() {
		List<Certification> cf = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CTVXemGiayXacNhan);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				LocalDateTime thoiGianDangKy = rs.getTimestamp("ThoiGian").toLocalDateTime();
				String tenLoaiGiay = rs.getString("TenLoaiGiay");
				Integer soThuTu = rs.getInt("SoThuTu");
				Integer soLuong = rs.getInt("SoLuong");
				BigDecimal chiPhi = rs.getBigDecimal("ChiPhi");
				LocalDateTime thoiGianNhan = null;
				Timestamp ts = rs.getTimestamp("ThoiGianNhan");
				if (ts != null)
					thoiGianNhan = ts.toLocalDateTime();
				String trangThai = rs.getString("TrangThai");
				String maHocKy = rs.getString("MaHocKy");
				Integer maSV = rs.getInt("MaSV");
				Integer maNguoiXacNhan = rs.getInt("MaNguoiXacNhan");
				String khoa = LayKhoa(maSV);
				Integer nienKhoa = LayNienKhoa(maSV);
				Boolean tinhTrang = rs.getBoolean("TinhTrangGiay");
				if (maNguoiXacNhan == 0)
					maNguoiXacNhan = null;
				cf.add(new Certification(tenLoaiGiay, chiPhi, soThuTu, soLuong, trangThai, thoiGianDangKy, thoiGianNhan,
						maHocKy, maNguoiXacNhan, maSV, khoa, nienKhoa, tinhTrang));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return cf;
	}

	public static int ConfirmCertification(Certification cf) {
		int result = 0;
		String trangThai = cf.getTrangThai();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DuyetGiayXacNhan)) {
			preparedStatement.setInt(1, cf.getSoThuTu());
			if(trangThai.equals("Đã Xác Nhận"))
				preparedStatement.setTimestamp(2, Timestamp.valueOf(cf.getThoiGianNhan()));
			else
				preparedStatement.setTimestamp(2, null);
			preparedStatement.setInt(3, cf.getMaNguoiXacNhan());
			preparedStatement.setString(4,  cf.getTrangThai());
			preparedStatement.setString(5, cf.getLyDo());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return result;
	}

	public static List<Certification> AdminXemGiayXacNhan() {
		List<Certification> cf = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(AdminXemGiay);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String tenLoaiGiay = rs.getString("TenLoaiGiay");
				BigDecimal chiPhi = rs.getBigDecimal("ChiPhi");
				String maLoaiGiay = rs.getString("MaLoaiGiay");
				Boolean trangThai = rs.getBoolean("TrangThai");
				String moTa = rs.getString("MoTa");
				Integer maNguoiTao = rs.getInt("MaNguoiTao");
				cf.add(new Certification(maLoaiGiay, tenLoaiGiay, chiPhi, trangThai, moTa, maNguoiTao));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return cf;
	}

	public static List<Certification> ThongKeYeuCau() {
		List<Certification> cf = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ThongKeYeuCau);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer soThuTu = rs.getInt("SoThuTu");
				Integer maSV = rs.getInt("MaSV");
				LocalDateTime thoiGian = rs.getTimestamp("ThoiGian").toLocalDateTime();
				String trangThai = rs.getString("TrangThai");
				cf.add(new Certification(soThuTu, trangThai, thoiGian, maSV));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return cf;
	}

	public static List<Certification> ThongKeDuyet() {
		List<Certification> cf = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ThongKeDuyet);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer soThuTu = rs.getInt("SoThuTu");
				Integer maNguoiXacNhan = rs.getInt("MaNguoiXacNhan");
				cf.add(new Certification(soThuTu, maNguoiXacNhan));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return cf;
	}

	public static Certification XemChiTietGiayXacNhan(String maLoaiGiay) {
		Certification cf = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(XemChiTietGiay);) {
			preparedStatement.setString(1, maLoaiGiay);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String tenLoaiGiay = rs.getString("TenLoaiGiay");
				BigDecimal chiPhi = rs.getBigDecimal("ChiPhi");
				Integer maNguoiTao = rs.getInt("MaNguoiTao");
				Boolean trangThai = rs.getBoolean("TrangThai");
				String moTa = rs.getString("MoTa");
				cf = new Certification(maLoaiGiay, tenLoaiGiay, chiPhi, trangThai, moTa, maNguoiTao);
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return cf;
	}

	public static int InsertCer(Certification cf) {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(InsertGiay);) {
			preparedStatement.setString(1, cf.getMaLoaiGiay());
			preparedStatement.setBigDecimal(2, cf.getChiPhi());
			preparedStatement.setString(3, cf.getTenLoaiGiay());
			preparedStatement.setInt(4, cf.getMaNguoiXacNhan());
			preparedStatement.setString(5, cf.getMoTa());
			preparedStatement.setBoolean(6, cf.getTinhTrang());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return result;
	}

	public static int UpdateCer(Certification cf) {
		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UpdateGiay);) {
			preparedStatement.setBigDecimal(1, cf.getChiPhi());
			preparedStatement.setString(2, cf.getTenLoaiGiay());
			preparedStatement.setInt(3, cf.getMaNguoiXacNhan());
			preparedStatement.setString(4, cf.getMoTa());
			preparedStatement.setBoolean(5, cf.getTinhTrang());
			preparedStatement.setString(6, cf.getMaLoaiGiay());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return result;
	}
	
	public static Certification CTVXemLyDo(Integer soThuTu) {
		Certification cf = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CTVXemLyDo);) {
			preparedStatement.setInt(1, soThuTu);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String lyDoGui = rs.getString("LyDoGui");
				String lyDoXacNhan = rs.getString("LyDoXacNhan");
				Boolean tinhTrang = rs.getBoolean("TinhTrangGiay");
				String trangThai = rs.getString("TrangThai");
				cf = new Certification(lyDoGui, lyDoXacNhan, tinhTrang, trangThai);
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return cf;
	}
}
