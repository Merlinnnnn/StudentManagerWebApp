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
List<String> list_tinhTrang = new ArrayList<>();
list_tinhTrang.add("Đang Hoạt Động");
list_tinhTrang.add("Đang Bảo Trì");
List<Boolean> list_trangThai = new ArrayList<>();
list_trangThai.add(true);
list_trangThai.add(false);
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
    // Thêm phương thức kiểm tra validBigDecimal
    $.validator.addMethod("validBigDecimal", function(value, element) {
    	return /^[+-]?\d+(\.\d+)?$/.test(value);
    });

    // Áp dụng validate cho form có class manageCer
    $(".manageCer").validate({
        rules: {
            maLoaiGiay: "required",
            tenLoaiGiay: "required",
            moTa: "required",
            chiPhi: {
                required: true,
                validBigDecimal: true,
            },
        },
        messages: {
            maLoaiGiay: "Vui Lòng Nhập Mã Loại Giấy",
            tenLoaiGiay: "Vui Lòng Nhập Tên Loại Giấy",
            moTa: "Vui Lòng Nhập Mô Tả",
            chiPhi: {
                required: "Vui Lòng Nhập Chi Phí",
                validBigDecimal: "Chi Phí Không Đúng Định Dạng"
            }
        },
        errorClass: 'errorFont',
        errorElement: 'h1'
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
				<h1>Quản Lý Giấy Xác Nhận</h1>
				<form action="<%=request.getContextPath()%>/certification"
					class="manageCer" method="get" accept-charset="UTF-8">
					<div class="form-group row">
						<div class="col-md-7">
							<table class="table table-bordered text-center">
								<thead>
									<tr>
										<th>Mã Loại Giấy</th>
										<th>Tên Loại Giấy</th>
										<th>Mô Tả</th>
										<th>Chi Phí</th>
										<th>Trạng Thái</th>
										<th>Mã Người Tạo</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="giay" items="${list_giay}">
										<tr>
											<td><a
												href="<%=request.getContextPath()%>
															/certification?action=infoCer&maLoaiGiay=${giay.getMaLoaiGiay()}">${giay.getMaLoaiGiay()}
											</a></td>
											<td><c:out value="${giay.getTenLoaiGiay()}" /></td>
											<td><c:out value="${giay.getMoTa()}" /></td>
											<td><c:out value="${giay.getChiPhi()}" /></td>
											<td><c:if test="${giay.getTinhTrang()}">
								                    Đang Hoạt Động
								                </c:if> <c:if test="${not giay.getTinhTrang()}">
								                    Đang Bảo Trì
								                </c:if></td> 
											<td><c:out value="${giay.getMaNguoiXacNhan()}" /></td>
										</tr>
									</c:forEach>
									<!-- } -->
								</tbody>
							</table>
						</div>
						<div class="col-md-5">
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Mã Loại Giấy:</label>
								<div class="col-sm-9">
									<input type="text" value="${giay.getMaLoaiGiay()}"
										name="maLoaiGiay" required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Tên Loại Giấy:</label>
								<div class="col-sm-9">
									<input type="text" value="${giay.getTenLoaiGiay()}"
										name="tenLoaiGiay" style="width: 50%" required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Mô Tả:</label>
								<div class="col-sm-9">
									<input type="text" value="${giay.getMoTa()}"
										name="moTa" style="width: 50%" required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Chi Phí:</label>
								<div class="col-sm-9">
									<input type="text" value="${giay.getChiPhi()}" name="chiPhi"
										required="required">
								</div>
							</fieldset>
							<fieldset class="form-group row">
								<label class="col-sm-3 col-form-label">Trạng Thái:</label>
								<div class="col-sm-9">
									<select name="tinhTrang" id="tinhTrang">
										<%
										Boolean tinhTrang = (boolean)request.getAttribute("tinhTrang");
										for (int i = 0; i < list_tinhTrang.size(); i++) {
											String tinhTrangStr = list_tinhTrang.get(i);
											Boolean trangThai = list_trangThai.get(i);
										%>
										<option value="<%=trangThai%>"
											<%if (trangThai == tinhTrang) {%> selected="selected" <%}%>>
											<%=tinhTrangStr%>
										</option>
										<%
										}
										%>
									</select>
								</div>
							</fieldset>
							<div>
								<button type="submit" class="btn btn-primary" name="action"
									value="them">Thêm</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="submit" class="btn btn-primary" name="action"
									value="sua">Sửa</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%
	// Lấy thông điệp từ thuộc tính (attribute) của request
	String alertMessage = (String) request.getAttribute("warning");

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