<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<style>
.box {
	width: 100%;
	background-color: lavender;
	box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.1);
}

.box h1 {
	text-align: center;
	margin-bottom: 20px;
}

.box p {
	margin-bottom: 20px;
	margin-left: 20px;
	margin-right: 20px;
}
</style>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<jsp:include page="../common/header_student.jsp"></jsp:include>
<body>
	<div class="container-fluid">
		<div class="row flex-nowrap">
			<jsp:include page="../common/menuStudent.jsp"></jsp:include>
			<div class="col-auto col-md-10 bgrcolor">
				
				<div class=" col-md-12 card d-flex flex-column o-hidden border-0 shadow-lg my-5">
				<h1>Tham Gia Công Tác Xã Hội</h1>
				<label for="diemCTXH">Tổng số điểm tham gia: ${requestScope.diem}</label>
					<table class="table table-bordered text-center">
						<thead>
							<tr>
								<th>Mã Hoạt Độn</th>
								<th>Tên Hoạt Động</th>
								<th>Mô Tả</th>
								<th>Ngày Bắt Đầu</th>
								<th>Ngày Kết Thúc</th>
								<th>Điểm</th>
								<th>Tổ Chức</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="swInfo" items="${listSW}">
								<tr>
									<td><c:out value="${swInfo.getMaHoatDong()}" /></td>
									<td><c:out value="${swInfo.getTenHoatDong()}" /></td>
									<td><c:out value="${swInfo.getMoTa()}" /></td>
									<td><c:out value="${swInfo.getStringNgayBatDau()}" /></td>
									<td><c:out value="${swInfo.getStringNgayKetThuc()}" /></td>
									<td><c:out value="${swInfo.getDiem()}" /></td>
									<td><c:out value="${swInfo.getToChuc()}" /></td>
								</tr>
							</c:forEach>
							<!-- } -->
						</tbody>

					</table>
				</div>
				
				<div class=" col-md-12 card d-flex flex-column o-hidden border-0 shadow-lg my-5">
				<h1>Đăng Ký Hoạt Động Công Tác Xã Hội</h1>
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
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="hdInfo" items="${listHD}">
								<tr>
									<td><c:out value="${hdInfo.getMaHoatDong()}" /></td>
									<td><c:out value="${hdInfo.getTenHoatDong()}" /></td>
									<td><c:out value="${hdInfo.getMoTa()}" /></td>
									<td><c:out value="${hdInfo.getStringNgayBatDau()}" /></td>
									<td><c:out value="${hdInfo.getStringNgayKetThuc()}" /></td>
									<td><c:out value="${hdInfo.getDiem()}" /></td>
									<td><c:out value="${hdInfo.getToChuc()}" /></td>
									<td><a
										href="<%=request.getContextPath()%>
										/swpoint?action=insert&maHoatDong=${hdInfo.getMaHoatDong()}">Đăng Ký</a></td>
								</tr>
							</c:forEach>
							<!-- } -->
						</tbody>

					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="../common/footer.jsp"></jsp:include>
</html>