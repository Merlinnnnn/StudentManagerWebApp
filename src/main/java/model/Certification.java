package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Certification implements Serializable {
	private static final long serialVersionUID = 1L;
	private String maLoaiGiay;
	private String tenLoaiGiay;
	private BigDecimal chiPhi;
	private Integer soThuTu;
	private Integer soLuong;
	private String lyDo;
	private String trangThai;
	private LocalDateTime thoiGianDangKy;
	private LocalDateTime thoiGianNhan;
	private String noiNhan = "";
	private Integer maSV;
	private String maHocKy;
	private Integer maNguoiXacNhan;
	private String khoa;
	private Integer nienKhoa;
	private Boolean tinhTrang;
	private String moTa;
	private String lyDoXacNhan;

	public Certification(String lyDo, String lyDoXacNhan, Boolean tinhTrang, String trangThai) {
		super();
		this.lyDo = lyDo;
		this.lyDoXacNhan = lyDoXacNhan;
		this.tinhTrang = tinhTrang;
		this.trangThai = trangThai;
	}

	public Certification(Integer soThuTu, Integer maNguoiXacNhan) {
		super();
		this.soThuTu = soThuTu;
		this.maNguoiXacNhan = maNguoiXacNhan;
	}

	public Certification(String maLoaiGiay, Integer soLuong, String lyDo, LocalDateTime thoiGianDangKy, Integer maSV, String maHocKy) {
		super();
		this.maLoaiGiay = maLoaiGiay;
		this.soLuong = soLuong;
		this.lyDo = lyDo;
		this.thoiGianDangKy = thoiGianDangKy;
		this.maSV = maSV;
		this.maHocKy = maHocKy;
	}

	public Certification(Integer soThuTu, LocalDateTime thoiGianNhan, Integer maNguoiXacNhan) {
		super();
		this.soThuTu = soThuTu;
		this.thoiGianNhan = thoiGianNhan;
		this.maNguoiXacNhan = maNguoiXacNhan;
	}

	public Certification(Integer soThuTu) {
		super();
		this.soThuTu = soThuTu;
	}
	
	public Certification(String tenLoaiGiay, BigDecimal chiPhi, Integer soThuTu, Integer soLuong, String trangThai,
			LocalDateTime thoiGianDangKy, LocalDateTime thoiGianNhan, String maHocKy) {
		super();
		this.tenLoaiGiay = tenLoaiGiay;
		this.chiPhi = chiPhi;
		this.soThuTu = soThuTu;
		this.soLuong = soLuong;
		this.trangThai = trangThai;
		this.thoiGianDangKy = thoiGianDangKy;
		this.thoiGianNhan = thoiGianNhan;
		this.maHocKy = maHocKy;
	}
	
	public Certification(String tenLoaiGiay, BigDecimal chiPhi, Integer soThuTu, Integer soLuong, String trangThai,
			LocalDateTime thoiGianDangKy, LocalDateTime thoiGianNhan, String maHocKy, Integer maNguoiXacNhan, Integer maSV, String khoa, Integer nienKhoa, Boolean tinhTrang) {
		super();
		this.tenLoaiGiay = tenLoaiGiay;
		this.chiPhi = chiPhi;
		this.soThuTu = soThuTu;
		this.soLuong = soLuong;
		this.trangThai = trangThai;
		this.thoiGianDangKy = thoiGianDangKy;
		this.thoiGianNhan = thoiGianNhan;
		this.maHocKy = maHocKy;
		this.maNguoiXacNhan = maNguoiXacNhan;
		this.maSV = maSV;
		this.khoa = khoa;
		this.nienKhoa = nienKhoa;
		this.tinhTrang = tinhTrang;
	}

	public Certification(String maLoaiGiay, String tenLoaiGiay, BigDecimal chiPhi, Boolean tinhTrang, String moTa, Integer maNguoiXacNhan) {
		super();
		this.maLoaiGiay = maLoaiGiay;
		this.tenLoaiGiay = tenLoaiGiay;
		this.chiPhi = chiPhi;
		this.maNguoiXacNhan = maNguoiXacNhan;
		this.tinhTrang = tinhTrang;
		this.moTa = moTa;
	}

	public Certification(Integer soThuTu, String trangThai, LocalDateTime thoiGianDangKy, Integer maSV) {
		super();
		this.soThuTu = soThuTu;
		this.trangThai = trangThai;
		this.thoiGianDangKy = thoiGianDangKy;
		this.maSV = maSV;
	}

	public String getMaLoaiGiay() {
		return maLoaiGiay;
	}

	public void setMaLoaiGiay(String maLoaiGiay) {
		this.maLoaiGiay = maLoaiGiay;
	}

	public Integer getSoThuTu() {
		return soThuTu;
	}

	public void setSoThuTu(Integer soThuTu) {
		this.soThuTu = soThuTu;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public String getLyDo() {
		return lyDo;
	}

	public void setLyDo(String lyDo) {
		this.lyDo = lyDo;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public LocalDateTime getThoiGianDangKy() {
		return thoiGianDangKy;
	}

	public String StringGetThoiGianDangKy() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
		return this.thoiGianDangKy.format(df);
	}

	public void setThoiGianDangKy(LocalDateTime thoiGianDangKy) {
		this.thoiGianDangKy = thoiGianDangKy;
	}

	public LocalDateTime getThoiGianNhan() {
		return thoiGianNhan;
	}

	public String StringGetThoiGianNhan() {
		if(this.thoiGianNhan == null)
			return "";
		DateTimeFormatter df = DateTimeFormatter.ofPattern("'Nhận giấy từ' HH 'giờ' mm 'ngày' dd/MM/yy");
		return this.thoiGianNhan.format(df);
	}

	public void setThoiGianNhan(LocalDateTime thoiGianNhan) {
		this.thoiGianNhan = thoiGianNhan;
	}

	public String getNoiNhan() {
		return noiNhan;
	}

	public void setNoiNhan(String noiNhan) {
		this.noiNhan = noiNhan;
	}

	public Integer getMaSV() {
		return maSV;
	}

	public void setMaSV(Integer maSV) {
		this.maSV = maSV;
	}

	public String getTenLoaiGiay() {
		return tenLoaiGiay;
	}

	public void setTenLoaiGiay(String tenLoaiGiay) {
		this.tenLoaiGiay = tenLoaiGiay;
	}

	public BigDecimal getChiPhi() {
		return chiPhi;
	}

	public void setChiPhi(BigDecimal chiPhi) {
		this.chiPhi = chiPhi;
	}

	public String getMaHocKy() {
		return maHocKy;
	}

	public void setMaHocKy(String maHocKy) {
		this.maHocKy = maHocKy;
	}

	public Integer getMaNguoiXacNhan() {
		return maNguoiXacNhan;
	}

	public void setMaNguoiXacNhan(Integer maNguoiXacNhan) {
		this.maNguoiXacNhan = maNguoiXacNhan;
	}
	
	public String toJsonKhoa() {
        return "{\"khoa\":\"" + this.getKhoa() + "\"}";
    }
	
	public String toJsonNienKhoa() {
        return "{\"nienKhoa\":\"" + this.getNienKhoa().toString() + "\"}";
    }

	public String toJsonYeuCau() {
        return "{\"yeuCau\":\"" + this.getTrangThai() + "\"}";
    }
	
	public String getKhoa() {
		return khoa;
	}

	public void setKhoa(String khoa) {
		this.khoa = khoa;
	}

	public Integer getNienKhoa() {
		return nienKhoa;
	}

	public void setNienKhoa(Integer nienKhoa) {
		this.nienKhoa = nienKhoa;
	}

	public Boolean getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(Boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getLyDoXacNhan() {
		return lyDoXacNhan;
	}

	public void setLyDoXacNhan(String lyDoXacNhan) {
		this.lyDoXacNhan = lyDoXacNhan;
	}
}
