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
<jsp:include page="../common/header_colab.jsp"></jsp:include>
<body>
	<div class="container-fluid bgrcolor">
		<div class="row flex-nowrap">
			<jsp:include page="../common/menuColab.jsp"></jsp:include>
			<div class="col-md-10 card d-flex flex-column align-items-stretch o-hidden border-0 shadow-lg my-5">
				<h1>Phản Hồi</h1>
				<div class="form-group">
					<label for="noiDungPhanhoi">Nội Dung Phản Hồi: </label><input
						type="text" id="ndPhanHoi" name="ndPhanHoi"
						placeholder="Nội Dung Phản Hồi" required>
					<table class="table table-bordered text-center">
						<thead>
							<tr>
								<th>Mã Phản Hồi</th>
								<th>Mã Sinh Viên</th>
								<th>Nội Dung</th>
								<th>Ngày Gửi</th>
								<th>Phản Hồi</th>
								<th>Ngày Phản Hồi</th>
								<th>Người Phản Hồi</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="quesInfo" items="${listQuesColab}">
								<tr>
									<td><c:out value="${quesInfo.getMaCauHoi()}" /></td>
									<td><c:out value="${quesInfo.getMaSV()}" /></td>
									<td><c:out value="${quesInfo.getNoiDung()}" /></td>
									<td><c:out value="${quesInfo.StringGetNgayGui()}" /></td>
									<td><c:if test="${empty quesInfo.getPhanHoi()}">
											<a href="#"
												onclick="checkAndReply('${quesInfo.getMaCauHoi()}')">Phản
												hồi</a>
										</c:if> <c:if test="${not empty quesInfo.getPhanHoi()}">
											<c:out value="${quesInfo.getPhanHoi()}" />
										</c:if></td>
									<td><c:out value="${quesInfo.StringGetNgayPhanHoi()}" /></td>
									<td><c:out value="${quesInfo.getMaCTV()}" /></td>
								</tr>
							</c:forEach>
							<!-- } -->
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script>
	function checkAndReply(maCauHoi) {
	    var ndPhanHoi = document.getElementById("ndPhanHoi").value;
	    if (ndPhanHoi.trim() === "") {
	        alert("Vui lòng nhập nội dung phản hồi trước khi gửi.");
	    } else {
	        window.location.href = "<%=request.getContextPath()%>/question?action=reply&maCauHoi="
	            + maCauHoi.toString() + "&ndPhanHoi=" + encodeURIComponent(ndPhanHoi);
	    }
	}
</script>
</body>
<jsp:include page="../common/footer.jsp"></jsp:include>
</html>