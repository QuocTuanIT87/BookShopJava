<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet" href="assets/styles/login.css" />
</head>
<body>
	<div class="wrapper">
		<%@include file="/WEB-INF/views/include/header.jsp"%>
		<div class="cover-content">
			<div class="cover-form">
				<form action="update-password.htm" class="form-controll"
					method="post">
					<h1>Change password</h1>

					<h2 class="notify-success">${successMessage}</h2>
					<h2 class="notify-failed">${failedMessage}</h2>

					<input class="input-register" type="password"
						placeholder="New password" required name="password" /> <input
						class="input-register" type="password"
						placeholder="Re-new password" required name="re-password" /> <input
						class="btn-login" type="submit" value="Update password" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>