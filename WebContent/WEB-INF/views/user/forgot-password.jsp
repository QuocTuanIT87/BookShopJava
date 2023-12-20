<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet" href="assets/styles/login.css" />
</head>
<body>
	<div class="wrapper">
		<%@include file="/WEB-INF/views/include/header.jsp"%>
		<div class="cover-content">
			<div class="cover-form">
				<form action="get-otp.htm" class="form-controll" method="post">
					<h1>Reset password</h1>

					<h2 class="notify-success">${successMessage}</h2>
					<h2 class="notify-failed">${failedMessage}</h2>

					<input class="input-register" type="email" placeholder="Email"
						required name="email" /> 

					 <input class="btn-login" type="submit" value="Send OTP" />

				</form>
				
			</div>
		</div>
	</div>
</body>
</html>