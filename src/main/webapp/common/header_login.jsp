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
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<title>header</title>
</head>

<body>
	<header>
		<nav class="navbar navbar-expand-lg">
			<div class="container">
				<a class="navbar-brand" href="#"> <img
					src="https://files.catbox.moe/j7yb5t.png" alt="Logo"
					style="max-height: 350px; max-width: 100%;">
				</a>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav ms-auto">
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/mainPage/mainPage.jsp">Trang Chủ</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>
</body>
</html>