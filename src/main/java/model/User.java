package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tenDN;
	private String matKhau;
	private String email;
	private String loaiTK;
	private String hoTen;
	private LocalDate ngaySinh;
	private String gioiTinh;
	private String diaChi;
	private String cccd;
	private String soDienThoai;
	private LocalDate ngayTao;
	private String lop;
	private Integer nienKhoa;
	private boolean trangThai;
	private String khoa;
	private Integer ma;
	
	public User() {
		
	}

	public User(String tenDN, String matKhau, String email, String loaiTK, String hoTen, LocalDate ngaySinh,
			String gioiTinh, String diaChi, String cccd, String soDienThoai, LocalDate ngayTao, String lop,
			Integer nienKhoa, String khoa) {
		super();
		this.tenDN = tenDN;
		this.matKhau = matKhau;
		this.email = email;
		this.loaiTK = loaiTK;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.cccd = cccd;
		this.soDienThoai = soDienThoai;
		this.ngayTao = ngayTao;
		this.lop = lop;
		this.nienKhoa = nienKhoa;
		this.khoa = khoa;
	}

	public User(String hoTen, boolean trangThai, Integer ma) {
		super();
		this.hoTen = hoTen;
		this.trangThai = trangThai;
		this.ma = ma;
	}

	public User(String hoTen, Integer nienKhoa, String khoa, Integer ma, boolean trangThai) {
		super();
		this.hoTen = hoTen;
		this.nienKhoa = nienKhoa;
		this.khoa = khoa;
		this.ma = ma;
		this.trangThai = trangThai;
	}

	public User(String email, String hoTen, LocalDate ngaySinh, String gioiTinh, String diaChi, String soDienThoai,
			LocalDate ngayTao, boolean trangThai) {
		super();
		this.email = email;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.ngayTao = ngayTao;
		this.trangThai = trangThai;
	}
	
	public User(String email, String hoTen, LocalDate ngaySinh, String gioiTinh, String diaChi, String soDienThoai,
			LocalDate ngayTao) {
		super();
		this.email = email;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.ngayTao = ngayTao;
	}

	public User(String email, String hoTen, LocalDate ngaySinh, String gioiTinh, String diaChi, String cccd, String soDienThoai,
			LocalDate ngayTao, String lop, Integer nienKhoa, boolean trangThai, String khoa) {
		super();
		this.email = email;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.cccd = cccd;
		this.soDienThoai = soDienThoai;
		this.ngayTao = ngayTao;
		this.lop = lop;
		this.nienKhoa = nienKhoa;
		this.trangThai = trangThai;
		this.khoa = khoa;
	}

	public String getTenDN() {
		return tenDN;
	}

	public void setTenDN(String tenDN) {
		this.tenDN = tenDN;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoaiTK() {
		return loaiTK;
	}

	public void setLoaiTK(String loaiTK) {
		this.loaiTK = loaiTK;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	
	public String StringGetNgaySinh()
    {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	return this.ngaySinh.format(df);
    }
	
	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	
	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public LocalDate getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(LocalDate ngayTao) {
		this.ngayTao = ngayTao;
	}

	public String getLop() {
		return lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}

	public Integer getNienKhoa() {
		return nienKhoa;
	}

	public void setNienKhoa(Integer nienKhoa) {
		this.nienKhoa = nienKhoa;
	}

	public String getKhoa() {
		return khoa;
	}

	public void setKhoa(String khoa) {
		this.khoa = khoa;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public Integer getMa() {
		return ma;
	}

	public void setMa(Integer ma) {
		this.ma = ma;
	}
}
