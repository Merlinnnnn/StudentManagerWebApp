<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%
String hoTen = (String) session.getAttribute("hoTen");
if (hoTen == null) {
	// Xử lý khi giá trị hoTen từ session là null
	hoTen = "Không có tên";
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Menu</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<style><%@include file="menu.css"%></style>
<body class="bgrcolor">
	<div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bl-color">
		<div
			class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
			<p
				class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
				<span class="fs-5 d-none d-sm-inline">Menu</span>
			</p>
			<ul
				class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start"
				id="menu">
				<li class="nav-item"><a
					href="<%=request.getContextPath()%>/mainPage/mainPage.jsp"
					class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i>
						<span class="ms-1 d-none d-sm-inline text-white">Trang chủ</span>
				</a></li>
				<li class="nav-item"><a
					href="<%=request.getContextPath()%>/colab?action=info"
					class="nav-link px-0 align-middle"> <i class="fs-4 bi-table"></i>
						<span class="ms-1 d-none d-sm-inline text-white">Thông Tin Cá Nhân</span></a></li>
				<li class="nav-item"><a
					href="<%=request.getContextPath()%>/certification?action=infoColab"
					class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i>
						<span class="ms-1 d-none d-sm-inline text-white">Giấy Xác Nhận</span>
				</a></li>
				<li class="nav-item"><a
					href="<%=request.getContextPath()%>/question?action=showReply"
					class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i>
						<span class="ms-1 d-none d-sm-inline text-white">Hỏi Đáp</span>
				</a></li>
			</ul>
			<hr>
		</div>
	</div>
	<div class="col py-3"></div>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</html>
