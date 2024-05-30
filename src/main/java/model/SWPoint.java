package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SWPoint implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer maHoatDong;
	private String tenHoatDong;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private Integer diem;
	private String toChuc;
	private String moTa;
	private LocalDate ngayDangKy;
	private Integer maSV;
	private Integer maNguoiTao;
	
	public SWPoint(Integer maHoatDong, String tenHoatDong, LocalDate ngayBatDau,
			LocalDate ngayKetThuc, Integer diem, String toChuc, String moTa) {
		super();
		this.maHoatDong = maHoatDong;
		this.tenHoatDong = tenHoatDong;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.diem = diem;
		this.toChuc = toChuc;
		this.moTa = moTa;
	}
	
	public SWPoint(String tenHoatDong, LocalDate ngayBatDau,
			LocalDate ngayKetThuc, Integer diem, String toChuc, String moTa, Integer maNguoiTao) {
		super();
		this.tenHoatDong = tenHoatDong;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.diem = diem;
		this.toChuc = toChuc;
		this.moTa = moTa;
		this.maNguoiTao = maNguoiTao;
	}
	
	public SWPoint(Integer maHoatDong, String tenHoatDong, LocalDate ngayBatDau,
			LocalDate ngayKetThuc, Integer diem, String toChuc, String moTa, Integer maNguoiTao) {
		super();
		this.maHoatDong = maHoatDong;
		this.tenHoatDong = tenHoatDong;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.diem = diem;
		this.toChuc = toChuc;
		this.moTa = moTa;
		this.maNguoiTao = maNguoiTao;
	}
	
	public SWPoint(Integer maHoatDong, LocalDate ngayDangKy, Integer maSV) {
		super();
		this.maHoatDong = maHoatDong;
		this.ngayDangKy = ngayDangKy;
		this.maSV = maSV;
	}

	public Integer getMaHoatDong() {
		return maHoatDong;
	}

	public void setMaHoatDong(Integer maHoatDong) {
		this.maHoatDong = maHoatDong;
	}

	public String getTenHoatDong() {
		return tenHoatDong;
	}

	public void setTenHoatDong(String tenHoatDong) {
		this.tenHoatDong = tenHoatDong;
	}

	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}

	public String getStringNgayBatDau() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yy");
		return ngayBatDau.format(df);
	}
	
	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}
	
	public String getStringNgayKetThuc() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yy");
		return ngayKetThuc.format(df);
	}

	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public Integer getDiem() {
		return diem;
	}

	public void setDiem(Integer diem) {
		this.diem = diem;
	}

	public String getToChuc() {
		return toChuc;
	}

	public void setToChuc(String toChuc) {
		this.toChuc = toChuc;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public LocalDate getNgayDangKy() {
		return ngayDangKy;
	}

	public void setNgayDangKy(LocalDate ngayDangKy) {
		this.ngayDangKy = ngayDangKy;
	}

	public Integer getMaSV() {
		return maSV;
	}

	public void setMaSV(Integer maSV) {
		this.maSV = maSV;
	}

	public Integer getMaNguoiTao() {
		return maNguoiTao;
	}

	public void setMaNguoiTao(Integer maNguoiTao) {
		this.maNguoiTao = maNguoiTao;
	}
	
	
}
