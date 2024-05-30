<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
<style>
<%@ include file="forgotPass.css"%>
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div
		class="container d-flex justify-content-center align-items-center min-vh-100">
		<form action="<%=request.getContextPath()%>/forgotpass" method="post"
			accept-charset="UTF-8">
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
							<h2>Quên mật khẩu</h2>
						</div>
						<div class="input-group mb-3">
							<input type="text" class="form-control" id="tenDN"
								placeholder="Tên Đăng Nhập" name="tenDN" required>
						</div>
						<div class="input-group mb-1">
							<input type="text" class="form-control" id="email"
								placeholder="Email" name="email" required>
						</div>
						<div class="input-group mb-5 d-flex justify-content-between">
							<div class="input-group mb-3">
								<button class="btn btn-primary mx-auto d-block">Gửi mã</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>

