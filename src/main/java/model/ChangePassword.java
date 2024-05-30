package model;

import java.io.Serializable;

public class ChangePassword implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tenDN;
	private String matKhauCu;
	private String matKhauMoi;
	private String xacNhanMatKhau;
	public String getTenDN() {
		return tenDN;
	}
	public void setTenDN(String tenDN) {
		this.tenDN = tenDN;
	}
	public String getMatKhauCu() {
		return matKhauCu;
	}
	public void setMatKhauCu(String matKhauCu) {
		this.matKhauCu = matKhauCu;
	}
	public String getMatKhauMoi() {
		return matKhauMoi;
	}
	public void setMatKhauMoi(String matKhauMoi) {
		this.matKhauMoi = matKhauMoi;
	}
	public String getXacNhanMatKhau() {
		return xacNhanMatKhau;
	}
	public void setXacNhanMatKhau(String xacNhanMatKhau) {
		this.xacNhanMatKhau = xacNhanMatKhau;
	}
}
