<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style><%@include file="Header.css"%></style>
<title>header</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg  "
			style="padding-left: 0; margin-left: 0;">
			<div class="container">
				<a class="navbar-brand" href="#"> <img
					src="https://files.catbox.moe/j7yb5t.png" alt="Logo"
					style="max-height: 350px; max-width: 100%;">
				</a>
				<div class="dropdown pb-3 ms-auto">
					<a href="#"
						class="d-flex align-items-center text-white text-decoration-none dropdown-toggle"
						id="dropdownUser" data-bs-toggle="dropdown" aria-expanded="false">
						<img src="https://files.catbox.moe/s95pma.jpg" alt="hugenerd"
						width="60" height="60" class="rounded-circle"> <span
						style="font-size: 20px;" class="d-none d-sm-inline mx-1">${hoTen}</span>
					</a>
					<ul class="dropdown-menu dropdown-menu-dark text-small shadow"
						aria-labelledby="dropdownUser">
						<li><a class="dropdown-item"
							href="<%=request.getContextPath()%>/user?action=changepass">Đổi
								Mật Khẩu</a></li>
						<li>
							<hr class="dropdown-divider">
						</li>
						<li><a class="dropdown-item"
							href="<%=request.getContextPath()%>/user?action=logout">Đăng
								Xuất</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</html>