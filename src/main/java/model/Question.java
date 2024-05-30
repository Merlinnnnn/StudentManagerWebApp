package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Question  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer maCauHoi;
	private Integer maSV;
	private String noiDung;
	private LocalDateTime ngayGui;
	private boolean trangThai;
	private String phanHoi;
	private LocalDateTime ngayPhanHoi;
	private Integer maCTV;
	
	public Question(Integer maCauHoi, String noiDung, LocalDateTime ngayGui, boolean trangThai, String phanHoi, LocalDateTime ngayPhanHoi) {
		super();
		this.maCauHoi = maCauHoi;
		this.noiDung = noiDung;
		this.ngayGui = ngayGui;
		this.trangThai = trangThai;
		this.phanHoi = phanHoi;
		this.ngayPhanHoi = ngayPhanHoi;
	}

	public Question(Integer maSV, String noiDung, LocalDateTime ngayGui) {
		super();
		this.maSV = maSV;
		this.noiDung = noiDung;
		this.ngayGui = ngayGui;
	}

	public Question(Integer maCauHoi, Integer maSV, String noiDung, LocalDateTime ngayGui, String phanHoi,
			LocalDateTime ngayPhanHoi, Integer maCTV) {
		super();
		this.maCauHoi = maCauHoi;
		this.maSV = maSV;
		this.noiDung = noiDung;
		this.ngayGui = ngayGui;
		this.phanHoi = phanHoi;
		this.ngayPhanHoi = ngayPhanHoi;
		this.maCTV = maCTV;
	}

	public Question(Integer maCauHoi, String phanHoi, LocalDateTime ngayPhanHoi, Integer maCTV) {
		super();
		this.maCauHoi = maCauHoi;
		this.phanHoi = phanHoi;
		this.ngayPhanHoi = ngayPhanHoi;
		this.maCTV = maCTV;
	}

	public Integer getMaSV() {
		return maSV;
	}

	public void setMaSV(Integer maSV) {
		this.maSV = maSV;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public LocalDateTime getNgayGui() {
		return ngayGui;
	}

	public String StringGetNgayGui() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
		return this.ngayGui.format(df);
	}
	
	public void setNgayGui(LocalDateTime ngayGui) {
		this.ngayGui = ngayGui;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public String getPhanHoi() {
		return phanHoi;
	}

	public void setPhanHoi(String phanHoi) {
		this.phanHoi = phanHoi;
	}

	public LocalDateTime getNgayPhanHoi() {
		return ngayPhanHoi;
	}

	public String StringGetNgayPhanHoi() {
		if(this.ngayPhanHoi == null)
			return "";
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
		return this.ngayPhanHoi.format(df);
	}
	
	public void setNgayPhanHoi(LocalDateTime ngayPhanHoi) {
		this.ngayPhanHoi = ngayPhanHoi;
	}

	public Integer getMaCauHoi() {
		return maCauHoi;
	}

	public void setMaCauHoi(Integer maCauHoi) {
		this.maCauHoi = maCauHoi;
	}

	public Integer getMaCTV() {
		return maCTV;
	}

	public void setMaCTV(Integer maCTV) {
		this.maCTV = maCTV;
	}
}
