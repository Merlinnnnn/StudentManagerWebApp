<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.LoginDao"%>
<%@ page import="model.LoginBean"%>
<%@ page import="web.LoginController"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Student Management System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style><%@include file="login.css"%></style>
</head>
<body>
	<%
	LoginController.checklogin(request, response);
	%>
	<jsp:include page="../common/header_login.jsp"></jsp:include>
	<form action="<%=request.getContextPath()%>/login" method="post">
		<div
			class="container d-flex justify-content-center align-items-center min-vh-100">
			<div class="row border rounded-5 p-3 bg-white shadow box-area">
				<div
					class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box"
					style="background: linear-gradient(to bottom, #00558c, #0187c6);">
					<div class="featured-image mb-3">
						<img src="https://files.catbox.moe/mfkmm0.png" class="img-fluid"
							style="width: 250px;">
					</div>
					<p class="text-white fs-2"
						style="font-family: 'Courier New', Courier, monospace; font-weight: 600;">HCMUTE</p>
					<small class="text-white text-wrap text-center"
						style="width: 17rem; font-family: 'Courier New', Courier, monospace;">Sáng
						tạo - Nhân bản - Hội nhập</small>
				</div>
				<div class="col-md-6 right-box">
					<div class="row align-items-center">
						<div class="header-text mb-4">
							<h2>ĐĂNG NHẬP</h2>
							<p></p>
						</div>
						<div class="input-group mb-3">
							<input type="text" class="form-control" id="username"
								placeholder="Tên đăng nhập" name="username" required>
						</div>
						<div class="input-group mb-1">
							<input type="password" class="form-control" id="password"
								placeholder="Mật khẩu" name="password" required>
						</div>
						<div class="input-group mb-5 d-flex justify-content-between">
							<div class="forgot">
								<small><a
									href="<%=request.getContextPath()%>/forgotpass">Quên mật
										khẩu?</a></small>
							</div>
						</div>
						<div class="input-group mb-3">
							<button type="submit" class="btn btn-primary">Đăng nhập</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>