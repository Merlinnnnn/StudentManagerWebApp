<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.DataDao"%>
<%
List<String> list_tenLoaiGiay = DataDao.TenLoaiGiay();
String hoTen = (String) session.getAttribute("hoTen");
if (hoTen == null) {
	// Xử lý khi giá trị hoTen từ session là null
	hoTen = "Không có tên";
}
List<Integer> list_namHoc = DataDao.NamHoc(hoTen);
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
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<style><%@include file="certification.css"%></style>
<jsp:include page="../common/header_student.jsp"></jsp:include>
<body class="bgrcolor">
	<div class=" container-fluid bgrcolor">
		<div class="row flex-nowrap bgrcolor">
			<jsp:include page="../common/menuStudent.jsp"></jsp:include>
			<div class=" col-md-10 card d-flex flex-column align-items-stretch o-hidden border-0 shadow-lg my-5">
				<h1>Đăng Ký Giấy Xác Nhận</h1>
				<form action="<%=request.getContextPath()%>/certification"
					method="post" accept-charset="UTF-8">
					<div class="form-group">
						<div>
							<label>Chọn Giấy Xác Nhận:</label><select name="tenLoaiGiay"
								id="tenLoaiGiay" onchange="showInput()">
								<%
								for (String tenLoaiGiay : list_tenLoaiGiay) {
								%>
								<option value="<%=tenLoaiGiay%>"><%=tenLoaiGiay%></option>
								<%
								}
								%>
							</select> &nbsp;&nbsp; <label for="namHoc" id="lbNamHoc"
								style="display: none;">Năm học:</label> <select name="namHoc"
								id="namHoc" style="display: none;">
								<%
								for (Integer namHoc : list_namHoc) {
								%>
								<option value="<%=namHoc%>"><%=namHoc%></option>
								<%
								}
								%>
							</select> &nbsp;&nbsp; <label for="hocKy" id="lbHocKy"
								style="display: none">Học kỳ:</label><select name="hocKy"
								id="hocKy" style="display: none;">
								<option value="1">1</option>
								<option value="2">2</option>
							</select>
						</div>
						<div>
							<label>Chọn Số Lượng:</label><select name="soLuong" id="soLuong">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select> <label for="lyDo">Lý Do: </label><input type="text" id="lyDo"
								name="lyDo" placeholder="Lý Do Xin Giấy Xác Nhận" required>
						</div>
						<div>
							<button type="submit" class="btn btn-primary">Đăng Ký</button>
						</div>
						<table class="table table-bordered text-center">
							<thead>
								<tr>
									<th>Thời Gian Đăng Ký</th>
									<th>Tên Giấy Xác Nhận</th>
									<th>Số Thứ Tự</th>
									<th>Số Lượng</th>
									<th>Chi Phí</th>
									<th>Thời Gian Nhận</th>
									<th>Tình Trạng</th>
									<th>Xóa</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="cerInfo" items="${listCerInfo}">
									<tr>
										<c:choose>
											<c:when test="${cerInfo.getTrangThai() ne 'Đã Xóa'}">
												<td><c:out value="${cerInfo.StringGetThoiGianDangKy()}" /></td>
												<td><c:out value="${cerInfo.getTenLoaiGiay()}" /></td>
												<td><c:out value="${cerInfo.getSoThuTu()}" /></td>
												<td><c:out value="${cerInfo.getSoLuong()}" /></td>
												<td><c:out value="${cerInfo.getChiPhi()}" /></td>
												<td><c:out value="${cerInfo.StringGetThoiGianNhan()}" /></td>
												<td><c:out value="${cerInfo.getTrangThai()}" /></td>
												<td><c:if
														test="${empty cerInfo.StringGetThoiGianNhan()}">
														<a
															href="<%=request.getContextPath()%>
															/certification?action=delete&loaitk=sv&soThuTu=${cerInfo.getSoThuTu()}">Xóa</a>
													</c:if> <c:if test="${not empty cerInfo.StringGetThoiGianNhan()}">
														<p>Xóa</p>
													</c:if></td>
											</c:when>
										</c:choose>
									</tr>
								</c:forEach>
								<!-- } -->
							</tbody>

						</table>

					</div>
				</form>
				<div class="box">
					<h1>Quy Trình Nhận Giấy Xác Nhận Đã Đăng Ký</h1>
					<p>1. Sinh viên nhận Giấy theo thời gian nhận.</p>
					<p>2. Sinh viên liên hệ Phòng Tuyển sinh và Công tác sinh viên
						A1-203 để nhận giấy. Buổi sáng từ 7h00 đến 11h30, buổi chiều từ
						13h đến 16h30 các ngày từ thứ hai đến thứ sáu và sáng thứ bảy.</p>
					<p>3. Sinh viên cung cấp Số thứ tự và họ tên cho nhân viên trả
						giấy.</p>
					<p>4. Thời hạn nhận giấy xác nhận đã đăng ký là 2 tuần, sau
						thời gian trên các loại giấy quá hạn sẽ bị hủy.</p>
					<p>Lưu ý: nếu sinh viên đã đăng ký nhưng không nhận giấy thì sẽ
						bị khóa chức năng đăng ký của loại giấy đó. Để được mở khóa, Sinh
						viên liên hệ Phòng Tuyển sinh và công tác sinh viên A1-203 để xử
						lý.</p>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="../common/footer.jsp"></jsp:include>
<script>
	function showInput() {
		var selectOption = document.getElementById("tenLoaiGiay");
		var namHoc = document.getElementById("namHoc");
		var lbNamHoc = document.getElementById("lbNamHoc");
		var hocKy = document.getElementById("hocKy");
		var lbHocKy = document.getElementById("lbHocKy");
		var selectedValue = selectOption.options[selectOption.selectedIndex].value;

		if (selectedValue === "Giấy Bảng Điểm") {
			namHoc.style.display = "inline-block";
			lbNamHoc.style.display = "inline-block";
			hocKy.style.display = "inline-block";
			lbHocKy.style.display = "inline-block";
		} else {
			namHoc.style.display = "none";
			lbNamHoc.style.display = "none";
			hocKy.style.display = "none";
			lbHocKy.style.display = "none";
		}
	}
</script>
</html>