<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.DataDao"%>
<%
String hoTen = (String) session.getAttribute("hoTen");
if (hoTen == null) {
	// Xử lý khi giá trị hoTen từ session là null
	hoTen = "Không có tên";
}
List<String> list_khoa = DataDao.Khoa();
List<String> list_gioiTinh = new ArrayList<>();
list_gioiTinh.add("Nam");
list_gioiTinh.add("Nữ");
List<String> list_lop = DataDao.Lop();
%>
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
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;700&display=swap">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<style>
.errorFont {
	font-family: 'Nunito', sans-serif;
	font-size: 18px;
	font-weight: bold;
	color: #ff0000;
	content: "* ";
}

.errorFont::before {
	content: "* ";
	color: red;
	font-weight: bold;
}
</style>
<script>
	$(document).ready(function() {
		$(".manageStudent").validate({
			rules : {
				hoTen : "required",
				tenDN : "required",
				matKhau : "required",
				email : {
					required : true,
					email : true,
				},
				cccd : "required",
				sdt : "required",
				diaChi : "required",

			},
			messages : {
				hoTen : "Vui lòng nhập họ và tên",
				tenDN : "Vui lòng nhập tên đăng nhập",
				matKhau : "Vui lòng nhập mật khẩu",
				email : {
					required : "Vui lòng nhập email",
					email : "Email không đúng định dạng",
				},
				cccd : "Vui lòng nhập CCCD",
				sdt : "Vui lòng nhập số điện thoại",
				diaChi : "Vui lòng nhập địa chỉ",

			},
			errorClass : 'errorFont',
			errorElement : 'h1'
		});
	});
</script>
</head>
<jsp:include page="../common/header_admin.jsp"></jsp:include>
<body>
	<div class="container-fluid">
		<div class="row flex-nowrap">
			<jsp:include page="../common/menuAdmin.jsp"></jsp:include>
			<div
				class="card shadow col-md-10 d-flex flex-column align-items-stretch o-hidden border-0 shadow-lg my-5">
				<h1>Quản Lý Sinh Viên</h1>
				<form action="<%=request.getContextPath()%>/ImportController"
					method="post" enctype="multipart/form-data" accept-charset="UTF-8">
					<div>
						<input type="file" name="file" accept=".xls">
					</div>
					<button type="submit" class="btn btn-primary">Import</button>
				</form>
				<form action="<%=request.getContextPath()%>/student" method="post"
					class="manageStudent" accept-charset="UTF-8">
					<div class="form-group row">
						<div class="col-md-4">
							<table class="table table-bordered text-center">
								<thead>
									<tr>
										<th>Mã Sinh Viên</th>
										<th>Họ Tên</th>
										<th>Niên Khóa</th>
										<th>Khoa</th>
										<th>Trạng Thái</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="sinhVien" items="${list_sinhVien}">
										<tr>
											<td><a
												href="<%=request.getContextPath()%>
															/student?action=infoSV&maSV=${sinhVien.getMa()}">${sinhVien.getMa()}
											</a></td>
											<td><c:out value="${sinhVien.getHoTen()}" /></td>
											<td><c:out value="${sinhVien.getNienKhoa()}" /></td>
											<td><c:out value="${sinhVien.getKhoa()}" /></td>
											<td><c:if test="${sinhVien.isTrangThai()}">
								                    Còn học
								                </c:if> <c:if test="${not sinhVien.isTrangThai()}">
								                    Nghỉ học
								                </c:if></td>
										</tr>
									</c:forEach>
									<!-- } -->
								</tbody>
							</table>
						</div>
						<div class="col-md-4">
							<input type="text" value="${sinhVien.getMa()}" name="maSV"
								style="display: none">
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Họ Tên:</label>
								<div class="col-sm-9">
									<input type="text" value="${sinhVien.getHoTen()}" name="hoTen"
										required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Ngày sinh:</label>
								<div class="col-sm-9">
									<input type="date" value="${sinhVien.getNgaySinh()}"
										name="ngaySinh" required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Giới Tính:</label>
								<div class="col-sm-9">
									<select name="gioiTinh" id="gioiTinh">
										<%
										String gioiTinhSV = (String) request.getAttribute("gioiTinhSinhVien");
										for (String gioiTinh : list_gioiTinh) {
										%>
										<option value="<%=gioiTinh%>"
											<%if (gioiTinh.equals(gioiTinhSV)) {%> selected="selected"
											<%}%>>
											<%=gioiTinh%></option>
										<c:if test=""></c:if>
										<%
										}
										%>
									</select>
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Khoa:</label>
								<div class="col-sm-9">
									<select name="khoa" id="khoa">
										<%
										String tenKhoa = (String) request.getAttribute("tenKhoa");
										for (String khoa : list_khoa) {
										%>
										<option value="<%=khoa%>" <%if (khoa.equals(tenKhoa)) {%>
											selected="selected" <%}%>>
											<%=khoa%></option>
										<c:if test=""></c:if>
										<%
										}
										%>
									</select>
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Tên Đăng Nhập:</label>
								<div class="col-sm-9">
									<input type="text" value="${sinhVien.getTenDN()}" name="tenDN"
										required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Mật Khẩu:</label>
								<div class="col-sm-9">
									<input type="text" value="${sinhVien.getMatKhau()}"
										name="matKhau" required="required">
								</div>
							</fieldset>
						</div>
						<div class="col-md-4">
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Lớp:</label>
								<div class="col-sm-9">
									<select name="lop" id="lop">
										<%
										String lopSV = (String) request.getAttribute("lopSinhVien");
										for (String lop : list_lop) {
										%>
										<option value="<%=lop%>" <%if (lop.equals(lopSV)) {%>
											selected="selected" <%}%>>
											<%=lop%></option>
										<c:if test=""></c:if>
										<%
										}
										%>
									</select>
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">CCCD:</label>
								<div class="col-sm-9">
									<input type="text" value="${sinhVien.getCccd()}" name="cccd"
										required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Email:</label>
								<div class="col-sm-9">
									<input type="email" value="${sinhVien.getEmail()}" name="email"
										required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Số Điện Thoại:</label>
								<div class="col-sm-9">
									<input type="text" value="${sinhVien.getSoDienThoai()}"
										name="sdt" required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Địa Chỉ:</label>
								<div class="col-sm-9">
									<input type="text" value="${sinhVien.getDiaChi()}"
										name="diaChi" required="required">
								</div>
							</fieldset>
							<div>
								<button type="submit" class="btn btn-primary" name="chucNang"
									value="them">Thêm</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="submit" class="btn btn-primary" name="chucNang"
									value="sua">Sửa</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="submit" class="btn btn-primary" name="chucNang"
									value="xoa">Xóa</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%
	// Lấy thông điệp từ thuộc tính (attribute) của request
	String alertMessage = (String) request.getAttribute("message");

	// Kiểm tra nếu có thông điệp thì hiển thị alert
	if (alertMessage != null && !alertMessage.isEmpty()) {
	%>
	<script>
                alert("<%=alertMessage%>");
	</script>
	<%
	}
	%>
</body>
<jsp:include page="../common/footer.jsp"></jsp:include>
</html>