<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login Page</title>

<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet" href="assets/styles/login.css" />
</head>
<body>
	<div class="wrapper">
		<%@include file="/WEB-INF/views/include/header.jsp"%>
		<div class="cover-content">
			<div class="cover-form">
				<form action="login.htm" class="form-controll" method="post">
					<h1>Login account</h1>

					<h2 class="notify-success">${successMessage}</h2>
					<h2 class="notify-failed">${failedMessage}</h2>

					<input class="input-register" type="email" placeholder="Email"
						required name="email" /> <input class="input-register"
						type="password" placeholder="Password" required name="password" />

					<a href="forgot-password.htm">
						<p class="btn-forgot">Forgot password?</p>
					</a> <input class="btn-login" type="submit" value="Login" />

				</form>
				<div>
					<p>
						Do not have an account? <a class="btn-register"
							href="register.htm">Register</a>
					</p>
				</div>
			</div>
		</div>
	</div>

</body>
</html>