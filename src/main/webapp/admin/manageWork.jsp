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
List<String> list_tenToChuc = DataDao.TenToChuc();
List<String> list_maToChuc = DataDao.MaToChuc();
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
		$(".manageWork").validate({
			rules : {
				tenHoatDong : "required",
				moTa : "required",
				diem : "required",
			},
			messages : {
				tenHoatDong : "Tên hoạt động không được nhập trống",
				moTa : "Mô tả không được để không",
				diem : "Điểm không được để trống",
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
				<h1>Quản Lý Hoạt Động</h1>
				<form action="<%=request.getContextPath()%>/swpoint" method="post"
					class="manageWork" accept-charset="UTF-8">
					<div class="form-group row">
						<div class="col-md-4">
							<input type="text" value="${hd.getMaHoatDong()}"
								name="maHoatDong" style="display: none">
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Tên Hoạt Động:</label>
								<div class="col-sm-9">
									<input type="text" value="${hd.getTenHoatDong()}"
										name="tenHoatDong" required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Mô Tả:</label>
								<div class="col-sm-9">
									<input type="text" value="${hd.getMoTa()}" name="moTa"
										required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Ngày Bắt Đầu:</label>
								<div class="col-sm-9">
									<input type="date" value="${hd.getNgayBatDau()}"
										name="ngayBatDau" required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Ngày Kết Thúc:</label>
								<div class="col-sm-9">
									<input type="date" value="${hd.getNgayKetThuc()}"
										name="ngayKetThuc" required="required">
								</div>
							</fieldset>
						</div>
						<div class="col-md-4">
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Điểm:</label>
								<div class="col-sm-9">
									<input type="number" value="${hd.getDiem()}" name="diem"
										required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Tổ Chức:</label>
								<div class="col-sm-9">
									<select name="toChuc" id="toChuc">
										<%
										String toChuc = (String) request.getAttribute("toChuc");
										for (int i = 0; i < list_tenToChuc.size(); i++) {
											String tenToChuc = list_tenToChuc.get(i);
											String maToChuc = list_maToChuc.get(i);
										%>
										<option value="<%=maToChuc%>"
											<%if (maToChuc.equals(toChuc)) {%> selected="selected" <%}%>>
											<%=tenToChuc%>
										</option>
										<%
										}
										%>
									</select>
								</div>
							</fieldset>
							<div>
								<button type="submit" class="btn btn-primary" name="chucNang"
									value="them">Thêm</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="submit" class="btn btn-primary" name="chucNang"
									value="sua">Sửa</button>
							</div>
						</div>
						<div>
							<table class="table table-bordered text-center">
								<thead>
									<tr>
										<th>Mã Hoạt Động</th>
										<th>Tên Hoạt Động</th>
										<th>Mô Tả</th>
										<th>Ngày Bắt Đầu</th>
										<th>Ngày Kết Thúc</th>
										<th>Điểm</th>
										<th>Tổ Chức</th>
										<th>Mã người Tạo</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="hd" items="${list_hd}">
										<tr>
											<td><a
												href="<%=request.getContextPath()%>
															/swpoint?action=infoHD&maHD=${hd.getMaHoatDong()}">${hd.getMaHoatDong()}
											</a></td>
											<td><c:out value="${hd.getTenHoatDong()}" /></td>
											<td><c:out value="${hd.getMoTa()}" /></td>
											<td><c:out value="${hd.getStringNgayBatDau()}" /></td>
											<td><c:out value="${hd.getStringNgayKetThuc()}" /></td>
											<td><c:out value="${hd.getDiem()}" /></td>
											<td><c:out value="${hd.getToChuc()}" /></td>
											<td><c:out value="${hd.getMaNguoiTao()}" /></td>
										</tr>
									</c:forEach>
									<!-- } -->
								</tbody>
							</table>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<jsp:include page="../common/footer.jsp"></jsp:include>
</html>