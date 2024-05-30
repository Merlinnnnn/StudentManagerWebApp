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
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<jsp:include page="../common/header_admin.jsp"></jsp:include>
<body>
	<div class="container-fluid">
		<div class="row flex-nowrap">
			<jsp:include page="../common/menuAdmin.jsp"></jsp:include>
			<div class="card shadow col-md-10 d-flex flex-column o-hidden border-0 shadow-lg my-5">
				<h1>Quản Lý Giấy Xác Nhận</h1>
				<form action="<%=request.getContextPath()%>/certification"
					method="get" accept-charset="UTF-8">
					<div class="form-group row">
						<div class="col-md-5">
							<table class="table table-bordered text-center">
								<thead>
									<tr>
										<th>Số Thứ Tự</th>
										<th>Mã Sinh Viên</th>
										<th>Thời Gian Gửi</th>
										<th>Trạng Thái</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="yeuCau" items="${list_yeuCau}">
										<tr>
											<td><c:out value="${yeuCau.getSoThuTu()}" /></td>
											<td><c:out value="${yeuCau.getMaSV()}" /></td>
											<td><c:out value="${yeuCau.StringGetThoiGianDangKy()}" /></td>
											<td><c:out value="${yeuCau.getTrangThai()}" /></td>
										</tr>
									</c:forEach>
									<!-- } -->
								</tbody>
							</table>
						</div>
						<div class="col-md-4">
							<table class="table table-bordered text-center">
								<thead>
									<tr>
										<th>Số Thứ Tự</th>
										<th>Mã Người Xác Nhận</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="duyet" items="${list_duyet}">
										<tr>
											<td><c:out value="${duyet.getSoThuTu()}" /></td>
											<td><c:out value="${duyet.getMaNguoiXacNhan()}" /></td>
										</tr>
									</c:forEach>
									<!-- } -->
								</tbody>
							</table>
						</div>
					</div>
				</form>
				<button type="button" class="btn btn-primary" onclick="displayChart()">Hiện Biểu Đồ</button>
				<div class="row">
					<c:set var="yeuCau" value="[" />
					<c:forEach var="item" items="${list_yeuCau}" varStatus="loop">
						<c:set var="yeuCau"
							value="${yeuCau}${item.toJsonYeuCau()}${!loop.last ? ',' : ''}" />
					</c:forEach>
					<c:set var="yeuCau" value="${yeuCau}]" />
					<div class="col-md-5">
						<canvas id="chart"></canvas>
					</div>
				</div>
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
                alert("<%=alertMessage%>
		");
	</script>
	<%
	}
	%>
</body>
<jsp:include page="../common/footer.jsp"></jsp:include>
<script>
function displayChart() {
    var yeuCau = ${yeuCau};
    var statusData = yeuCau.map(function (item) {
        return item.yeuCau;
    });

    var statusCounts = {};
    statusData.forEach(function (status) {
        statusCounts[status] = (statusCounts[status] || 0) + 1;
    });

    var barChartData = {
        labels: Object.keys(statusCounts),
        datasets: [{
            label: 'Số lượng yêu cầu Theo Trạng Thái',
            data: Object.values(statusCounts),
            backgroundColor: ['rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'],
            borderColor: ['rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'],
            borderWidth: 1
        }]
    };

    var barCtx = document.getElementById('chart').getContext('2d');
    var barChart = new Chart(barCtx, {
        type: 'bar',
        data: barChartData
    });
}
</script>
</html>