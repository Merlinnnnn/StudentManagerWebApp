<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Change Info</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;700&display=swap">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<style>
.errorFont {
	font-family: 'Nunito', sans-serif;
	font-size: 18px;
	font-weight: bold;
	color: #ff0000;
	content: "* ";
}
.errorFont::before {
	content: "* ";
	color: red;
	font-weight: bold;
}
</style>
<script>
	$(document).ready(function() {
		$(".changeinfo").validate({
			rules : {
				email : {
					required : true,
					email : true,
				},
				soDienThoai : "required",
				diaChi : "required",
			},
			messages : {
				email : {
					required : "Vui lòng nhập email",
					email : "Email không đúng định dạng",
				},
				soDienThoai : "Số điện thoại không được để trống",
				diaChi : "Địa chỉ không được để trống",
			},
			errorClass : 'errorFont',
			errorElement : 'h1'
		});
	});
</script>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container">

		<h2>User ConTact Changed Form</h2>
		<div class="col-md-6 col-md-offset-3">
			<div class="alert alert-success center" role="alert">
				<p>${NOTIFICATION}</p>
			</div>

			<form action="<%=request.getContextPath()%>/changeinfo?action=admin"
				class="changeinfo" method="post" accept-charset="UTF-8">
				<div class="form-group">
					<label for="email">Email:</label> <input type="text"
						value="${requestScope.email}" class="form-control" id="email"
						name="email" required>
				</div>

				<div class="form-group">
					<label for="soDienThoai">Số Điện Thoại:</label> <input type="text"
						value="${requestScope.soDT}" class="form-control" id="soDienThoai"
						name="soDienThoai" required>
				</div>

				<div class="form-group">
					<label for="diaChi">Địa Chỉ:</label> <input type="text"
						value="${requestScope.diaChi}" class="form-control" id="diaChi"
						name="diaChi" required>
				</div>
				<div>
					<button type="submit" class="btn btn-primary mx-auto d-block">Submit</button>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>

</html>