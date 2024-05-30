<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.LoginBean"%>
<%@ page import="web.LoginController"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Your Website</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<style>
<%@include file="style.css"%>
</style>
<jsp:include page="../common/header.jsp"></jsp:include>
<body>
	<div class="form">
		<div class="ctnGioiThieu space-between">
			<h1 class="display-4">Trường Đại Học Sư Phạm Kỹ Thuật TP HCM</h1>
			<p class="lead">Dịch vụ sinh viên</p>
		</div>
		<div class="container mt-5 ">
			<div class="card shadow mt-4">
				<h2 id="GioiThieu" class="text-center mb-4">Giới thiệu hệ thống</h2>
				<div class="row row-cols-1 row-cols-md-3 g-4">
					<div class="col">
						<div class="card h-100">
							<div class="card-body">
								<h5 class="card-title text-primary">Quản lý Học Tập</h5>
								<p class="card-text">Hồ sơ Sinh Viên: Hệ thống cung cấp một
									nền tảng để sinh viên có thể quản lý thông tin cá nhân, các
									loại điểm rèn luyện điểm ctxh.</p>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="card h-100">
							<div class="card-body">
								<h5 class="card-title text-success">Hỗ Trợ Sinh Viên</h5>
								<p class="card-text">Hỗ trợ sinh viên trong các vấn đề liên
									quan đến giấy xác nhận và giải đáp các thắc mắc</p>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="card h-100">
							<div class="card-body">
								<h5 class="card-title text-info">Kết Nối Cộng Đồng Sinh
									Viên</h5>
								<p class="card-text">Nơi để sinh viên trao đổi, thắc mắc về
									các vấn đề học tập</p>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
</body>
</html>
