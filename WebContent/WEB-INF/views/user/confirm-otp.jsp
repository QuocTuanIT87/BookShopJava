<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirm OTP</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet" href="assets/styles/login.css" />
</head>
<body>
	<div class="wrapper">
		<%@include file="/WEB-INF/views/include/header.jsp"%>
		<div class="cover-content">
			<div class="cover-form">
				<form action="confirm-otp.htm" class="form-controll" method="post">
					<h1>Confirm OTP</h1>

					<h2 class="notify-success">${successMessage}</h2>
					<h2 class="notify-failed">${failedMessage}</h2>

					<input class="input-register" type="text" placeholder="OTP"
						required name="otp" /> <input class="btn-login" type="submit"
						value="Confirm OTP" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>