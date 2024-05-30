<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Student Management System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<style><%@include file="info.css"%></style>
<jsp:include page="../common/header_student.jsp"></jsp:include>
<body>
	<div class="container-fluid">
		<div class="row flex-nowrap">
			<jsp:include page="../common/menuStudent.jsp"></jsp:include>
			<div class="card shadow col-md-5 d-flex flex-column align-items-stretch card o-hidden border-0 shadow-lg my-5">
			<div class="card-header py-3 text-center bg-primary">
				<h1>Thông Tin Sinh Viên</h1>
				</div>
				<div class="card-body">
				<fieldset class="form-group row">
					<label class="col-sm-3 col-form-label">Họ Tên:</label>
					 <div class="col-sm-9">
					<input type="text"
						value="${sinhVien.getHoTen()}" name="hoTen" required="required"
						readonly>
						</div>
				</fieldset>
				<fieldset class="form-group row">
					<label class="col-sm-3 col-form-label">Ngày sinh:</label> 
					<div class="col-sm-9">
					<input type="text"
						value="${sinhVien.StringGetNgaySinh()}" name="ngaySinh"
						required="required" readonly>
						</div>
				</fieldset>
				<fieldset class="form-group row">
					<label class="col-sm-3 col-form-label">Giới Tính:</label>
					<div class="col-sm-9">
					<input type="text"
						value="${sinhVien.getGioiTinh()}" name="gioiTinh"
						required="required" readonly>
						</div>
				</fieldset>
				<fieldset class="form-group row">
					<label class="col-sm-3 col-form-label">Khoa:</label>
					<div class="col-sm-9">
					<input type="text"
						value="${sinhVien.getKhoa()}" name="khoa" required="required"
						readonly>
						</div>
				</fieldset>
				<fieldset class="form-group row">
					<label class="col-sm-3 col-form-label">Lớp:</label>
					<div class="col-sm-9">
					<input type="text" value="${sinhVien.getLop()}"
						name="lop" required="required" readonly>
						</div>
				</fieldset>
				<fieldset class="form-group row">
					<label class="col-sm-3 col-form-label">Niên Khóa:</label>
					<div class="col-sm-9">
					<input type="text"
						value="${sinhVien.getNienKhoa()}" name="nienKhoa"
						required="required" readonly>
						</div>
				</fieldset>
				<fieldset class="form-group row">
					<label class="col-sm-3 col-form-label">Trạng Thái:</label>
					<div class="col-sm-9">
					<input type="text"
						value="${sinhVien.isTrangThai() ? 'Còn học' : 'Nghỉ học'}"
						name="trangThai" required="required" readonly>
						</div>
				</fieldset>
				</div>
			</div>
			<div class="card shadow col-md-5 d-flex flex-column align-items-stretch card o-hidden border-0 shadow-lg my-5">
				<div class="card-header py-3 text-center bg-primary">
				<h1>Thông Tin Liên Lạc</h1>
				</div>
				<div class="card-body">
				<fieldset class="form-group row">
					<label class="col-sm-3 col-form-label">CCCD:</label> 
					<div class="col-sm-9">
					<input type="text"
						value="${sinhVien.getCccd()}" name="cccd" required="required"
						readonly>
						</div>
				</fieldset>
				<fieldset class="form-group row">
					<label class="col-sm-3 col-form-label">Email:</label> 
					<div class="col-sm-9">
					<input type="text"
						value="${sinhVien.getEmail()}" name="email" required="required"
						readonly>
						</div>
				</fieldset>
				<fieldset class="form-group row">
					<label class="col-sm-3 col-form-label">Số Điện Thoại:</label> 
					<div class="col-sm-9">
					<input type="text"
						value="${sinhVien.getSoDienThoai()}" name="sdt"
						required="required" readonly>
						</div>
				</fieldset>
				<fieldset class="form-group row">
					<label class="col-sm-3 col-form-label">Địa Chỉ:</label> 
					<div class="col-sm-9">
					<input type="text"
						value="${sinhVien.getDiaChi()}" name="diaChi" required="required"
						readonly>
						</div>
				</fieldset>
				<a class="form-group text-centre"
					href="<%=request.getContextPath()%>/student?action=changeinfo">Cập nhật thông tin liên lạc</a>
			</div>
		</div>
	</div>
	</div>
</body>
<jsp:include page="../common/footer.jsp"></jsp:include>
</html>