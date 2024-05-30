<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Footer</title>
</head>
<style><%@include file="Header.css"%></style>
<body>
	<footer>

    <div class="container-fluid bg-col" >
        <div class="row">
            <div class="col-md-4">
                <h4>Liên kết hữu ích</h4>
                <ul class="navbar-nav ms-auto">
                    <li><a class="nav-link" href="<%=request.getContextPath()%>/mainPage/mainPage.jsp">Trang chủ</a></li>
                    <li><a class="nav-link" href="<%=request.getContextPath()%>/mainPage/mainPage.jsp">Giới thiệu</a></li>
                </ul>
            </div>
            <div class="col-md-4">
                <h4>Theo dõi chúng tôi</h4>
                <ul class="navbar-nav ms-auto">
                    <li><a class="nav-link" href="https://www.facebook.com/">Facebook</a></li>
                    <li><a class="nav-link" href="https://www.facebook.com/">Instagram</a></li>
                </ul>
            </div>
            <div class="col-md-4" >
                <h4>Liên hệ</h4>
                <p>Email: p.cthssv@hcmute.edu.vn</p>
                <p>Điện thoại: 037221223</p>
            </div>
        </div>
    </div>

</footer>
</body>
</html>