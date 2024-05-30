<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.DataDao"%>
<%
String hoTen = (String) session.getAttribute("hoTen");
if (hoTen == null) {
	// Xử lý khi giá trị hoTen từ session là null
	hoTen = "Không có tên";
}
List<String> list_gioiTinh = new ArrayList<>();
list_gioiTinh.add("Nam");
list_gioiTinh.add("Nữ");
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
		$(".manageColab").validate({
			rules : {
				hoTen : "required",
				ngaySinh : "required",
				tenDN : "required",
				matKhau : "required",
				email : {
					required : true,
					email : true,
				},
				sdt : "required",
				diaChi : "required",

			},
			messages : {
				hoTen : "Vui lòng nhập họ và tên",
				ngaySinh : "Vui lòng chọn ngày sinh",
				tenDN : "Vui lòng nhập tên đăng nhập",
				matKhau : "Vui lòng nhập mật khẩu",
				email : {
					required : "Vui lòng nhập email",
					email : "Email không đúng định dạng",
				},
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
				class="card shadow col-md-10 d-flex flex-column o-hidden border-0 shadow-lg my-5">
				<h1>Quản Lý Phòng CTSV</h1>
				<form action="<%=request.getContextPath()%>/colab" method="post"
					accept-charset="UTF-8" class="manageColab">
					<div class="form-group row">
						<div class="col-md-4">
							<table class="table table-bordered text-center">
								<thead>
									<tr>
										<th>Mã Cộng Tác Viên</th>
										<th>Họ Tên</th>
										<th>Trạng Thái</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="congTacVien" items="${list_congTacVien}">
										<tr>
											<td><a
												href="<%=request.getContextPath()%>
															/colab?action=infoCTV&maCTV=${congTacVien.getMa()}">${congTacVien.getMa()}
											</a></td>
											<td><c:out value="${congTacVien.getHoTen()}" /></td>
											<td><c:if test="${congTacVien.isTrangThai()}">
								                    Còn Hoạt Động
								                </c:if> <c:if test="${not congTacVien.isTrangThai()}">
								                    Ngưng Hoạt Động
								                </c:if></td>
										</tr>
									</c:forEach>
									<!-- } -->
								</tbody>
							</table>
						</div>
						<div class="col-md-4">
							<input type="text" value="${congTacVien.getMa()}" name="maCTV"
								style="display: none">
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Họ Tên:</label>
								<div class="col-sm-9">
									<input type="text" value="${congTacVien.getHoTen()}"
										name="hoTen" required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Ngày sinh:</label>
								<div class="col-sm-9">
									<input type="date" value="${congTacVien.getNgaySinh()}"
										name="ngaySinh" required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Giới Tính:</label>
								<div class="col-sm-9">

									<select name="gioiTinh" id="gioiTinh">
										<%
										String gioiTinhCTV = (String) request.getAttribute("gioiTinhCongTacVien");
										for (String gioiTinh : list_gioiTinh) {
										%>
										<option value="<%=gioiTinh%>"
											<%if (gioiTinh.equals(gioiTinhCTV)) {%> selected="selected"
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
								<label class="col-sm-3 col-form-label">Tên Đăng Nhập:</label>
								<div class="col-sm-9">
									<input type="text" value="${congTacVien.getTenDN()}"
										name="tenDN" required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Mật Khẩu:</label>
								<div class="col-sm-9">
									<input type="text" value="${congTacVien.getMatKhau()}"
										name="matKhau" required="required">
								</div>
							</fieldset>
						</div>
						<div class="col-md-4">
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Email:</label>
								<div class="col-sm-9">
									<input type="email" value="${congTacVien.getEmail()}"
										name="email" required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Số Điện Thoại:</label>
								<div class="col-sm-9">
									<input type="text" value="${congTacVien.getSoDienThoai()}"
										name="sdt" required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Địa Chỉ:</label>
								<div class="col-sm-9">
									<input type="text" value="${congTacVien.getDiaChi()}"
										name="diaChi" required="required">
								</div>
							</fieldset>
							<div class="d-flex">
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

</body>
<jsp:include page="../common/footer.jsp"></jsp:include>
</html>