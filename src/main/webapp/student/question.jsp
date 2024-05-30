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
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<jsp:include page="../common/header_student.jsp"></jsp:include>
<body>
	<div class="container-fluid">
		<div class="row flex-nowrap">
			<jsp:include page="../common/menuStudent.jsp"></jsp:include>
			<div class="col-auto col-md-10 bgrcolor">
				
				<form action="<%=request.getContextPath()%>/question" method="post"
					accept-charset="UTF-8">
					<div class=" col-md-12 card d-flex flex-column align-items-stretch o-hidden border-0 shadow-lg my-5">
					<h1>Hỏi Đáp</h1>
						<div>
							<label for="noiDung">Nội Dung Câu Hỏi: </label>
						</div>
						<div>
							<input type="text" id="noiDung" name="noiDung"
								placeholder="Nội Dung Câu Hỏi" required>
						</div>
						<div>
							<button type="submit" class="btn btn-primary">Gửi</button>
						</div>
						<table class="table table-bordered text-center">
							<thead>
								<tr>
									<th>Nội Dung</th>
									<th>Ngày Gửi</th>
									<th>Trạng Thái</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="quesInfo" items="${listQues}">
									<tr>
										<td><c:out value="${quesInfo.getNoiDung()}" /></td>
										<td><c:out value="${quesInfo.StringGetNgayGui()}" /></td>
										<td><c:if test="${quesInfo.isTrangThai()}">Đã Phản Hồi
										</c:if> <c:if test="${not quesInfo.isTrangThai()}">Chưa Phản Hồi
										</c:if></td>
									</tr>
								</c:forEach>
								<!-- } -->
							</tbody>

						</table>

					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<jsp:include page="../common/footer.jsp"></jsp:include>
</html>