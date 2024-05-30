<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
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
<jsp:include page="../common/header_colab.jsp"></jsp:include>
<body>
	<div class="container-fluid">
		<div class="row flex-nowrap">
			<jsp:include page="../common/menuColab.jsp"></jsp:include>
			<div
				class="col-md-9 card d-flex flex-column align-items-stretch o-hidden border-0 shadow-lg my-5">
				<div class="card-header">
					<h1>Duyệt Giấy Xác Nhận</h1>

					<div class="form-group">
						<table class="table table-bordered text-center"
							aria-hidden="false">

							<thead>
								<tr>
									<th>Thời Gian Đăng Ký</th>
									<th>Tên Giấy Xác Nhận</th>
									<th>Mã Học Kỳ</th>
									<th>Mã Sinh Viên</th>
									<th>Số Thứ Tự</th>
									<th>Số Lượng</th>
									<th>Chi Phí</th>
									<th>Thời Gian Nhận</th>
									<th>Tình Trạng</th>
									<th>Mã Người Xác Nhận</th>
									<th>Tình Trạng Giấy</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="cerInfoColab" items="${listCerInfoColab}">
									<tr>
										<td><c:out
												value="${cerInfoColab.StringGetThoiGianDangKy()}" /></td>
										<td><c:out value="${cerInfoColab.getTenLoaiGiay()}" /></td>
										<td><c:out value="${cerInfoColab.getMaHocKy()}" /></td>
										<td><c:out value="${cerInfoColab.getMaSV()}" /></td>
										<td><a
											href="<%=request.getContextPath()%>
										/certification?action=infoRe&soThuTu=${cerInfoColab.getSoThuTu()}" 
										onclick="showButton(${cerInfoColab.getTinhTrang()}, ${cerInfoColab.getTrangThai()});">
										${cerInfoColab.getSoThuTu()}</a></td>
										<td><c:out value="${cerInfoColab.getSoLuong()}" /></td>
										<td><c:out value="${cerInfoColab.getChiPhi()}" /></td>
										<td><c:out
												value="${cerInfoColab.StringGetThoiGianNhan()}" /></td>
										<td><c:out value="${cerInfoColab.getTrangThai()}" /></td>
										<td><c:out value="${cerInfoColab.getMaNguoiXacNhan()}" /></td>
										<td><c:if test="${cerInfoColab.getTinhTrang()}">
								                    Đang Hoạt Động
								                </c:if> <c:if test="${not cerInfoColab.getTinhTrang()}">
								                    Đang Bảo Trì
								                </c:if></td>
									</tr>
								</c:forEach>
								<!-- } -->
							</tbody>
						</table>
					</div>
					<form action="<%=request.getContextPath()%>/certification"
						class="Duyet" method="get" accept-charset="UTF-8">
						<div>
							<input type="hidden" value="${soThuTu}"
								name="soThuTu" required="required" readonly>
							<label>Lý Do Gửi:</label> <input type="text" value="${CerRe.getLyDo()}"
								name="lyDoGui" required="required" readonly> <label>Lý Do
								Phản Hồi:</label> <input type="text" value="${CerRe.getLyDoXacNhan()}" name="lyDoXacNhan"
								required="required">
						</div>
						<div>
							<button type="submit" class="btn btn-primary" name="action" id="xacNhan"
								value="xacNhan" style="display: none">Xác Nhận</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="submit" class="btn btn-primary" name="action" id="tuChoi"
								value="tuChoi" style="display: none">Từ Chối</button>
						</div>
					</form>
					<div class="card-body">
						<button type="button" onclick="displayChart()">Hiện Biểu
							Đồ</button>
						<c:set var="khoa" value="[" />
						<c:forEach var="item" items="${listCerInfoColab}" varStatus="loop">
							<c:set var="khoa"
								value="${khoa}${item.toJsonKhoa()}${!loop.last ? ',' : ''}" />
						</c:forEach>
						<c:set var="khoa" value="${khoa}]" />

						<c:set var="nienKhoa" value="[" />
						<c:forEach var="item" items="${listCerInfoColab}" varStatus="loop">
							<c:set var="nienKhoa"
								value="${nienKhoa}${item.toJsonNienKhoa()}${!loop.last ? ',' : ''}" />
						</c:forEach>
						<c:set var="nienKhoa" value="${nienKhoa}]" />
						<div class="row">
							<div class="col-md-4">
								<canvas id="chart1"></canvas>
							</div>
							<div class="col-md-4">
								<canvas id="chart2"></canvas>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var xacNhanBtn = document.getElementById("xacNhan");
		var tuChoiBtn = document.getElementById("tuChoi");
	</script>
	<%
	Boolean tinhTrang = (boolean) request.getAttribute("tinhTrang");
	String trangThai = (String) request.getAttribute("trangThai");
	if (tinhTrang) {
		if(trangThai.equals("Chờ Xác Nhận")) {
	%>
	<script>
		xacNhanBtn.style.display = "inline-block";
		tuChoiBtn.style.display = "inline-block";
	</script>
	<%
		}
	}
	else {
	%>
	<script>
		xacNhanBtn.style.display = "none";
		tuChoiBtn.style.display = "none";	
	</script>
	<%
	}
	%>
</body>
<jsp:include page="../common/footer.jsp"></jsp:include>
<script>
	function displayChart() {
		var khoa = ${khoa};
		var statusData1 = khoa.map(function(item) {
			return item.khoa;
		});

		var statusCounts1 = {};
		statusData1.forEach(function(status) {
			statusCounts1[status] = (statusCounts1[status] || 0) + 1;
		});

		var pieChartData1 = {
			labels : Object.keys(statusCounts1),
			datasets : [ {
				label : 'Số lượng yêu cầu Theo Khoa',
				data : Object.values(statusCounts1),
				backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
						'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
						'rgba(255, 159, 64, 0.2)' ],
				borderColor : [ 'rgba(255, 99, 132, 1)',
						'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
						'rgba(255, 159, 64, 1)' ],
				borderWidth : 1
			} ]
		};

		var pieCtx1 = document.getElementById('chart1').getContext('2d');
		var pieChart1 = new Chart(pieCtx1, {
			type : 'pie',
			data : pieChartData1
		});

		var nienKhoa = ${nienKhoa};
		var statusData2 = nienKhoa.map(function(item) {
			return item.nienKhoa;
		});

		var statusCounts2 = {};
		statusData2.forEach(function(status) {
			statusCounts2[status] = (statusCounts2[status] || 0) + 1;
		});

		var pieChartData2 = {
			labels : Object.keys(statusCounts2),
			datasets : [ {
				label : 'Số lượng yêu cầu Theo Niên Khoá',
				data : Object.values(statusCounts2),
				backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
						'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
						'rgba(255, 159, 64, 0.2)' ],
				borderColor : [ 'rgba(255, 99, 132, 1)',
						'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
						'rgba(255, 159, 64, 1)' ],
				borderWidth : 1
			} ]
		};

		var pieCtx2 = document.getElementById('chart2').getContext('2d');
		var pieChart2 = new Chart(pieCtx2, {
			type : 'pie',
			data : pieChartData2
		});
	}
</script>
</html>