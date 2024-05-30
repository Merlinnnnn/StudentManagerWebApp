package model;

import java.io.Serializable;

public class ForgotPass implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tenDN;
	private String email;
	private String matKhau;
	
	public String getTenDN() {
		return tenDN;
	}
	public void setTenDN(String tenDN) {
		this.tenDN = tenDN;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
}
