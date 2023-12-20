<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Register Page</title>

<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet" href="assets/styles/register.css" />
</head>
<body>
	<div class="wrapper">
		<%@include file="/WEB-INF/views/include/header.jsp"%>
		<div class="cover-content">
			<div class="cover-form">

				<form:form class="form-controll" action="register.htm" method="post"
					modelAttribute="user">
					<h1>Register account</h1>

					<h2 class="notify-success">${successMessage}</h2>
					<h2 class="notify-failed">${failedMessage}</h2>

					<form:input class="input-register" type="email" placeholder="Email"
						required="true" path="email" />
					<form:errors path="email" />
					
					<form:input class="input-register" type="text" placeholder="Full name"
						required="true" path="name" />
					<form:errors path="name" />

					<form:input class="input-register" type="password"
						placeholder="Password" required="true" path="password" />


					<input class="input-register" type="password"
						placeholder="Re-password" required name="re-password" />
					<form:errors path="password" />
					<input class="btn-register" type="submit" value="Register" />
				</form:form>
				<div>
					<p>
						Do you already have an account? <a class="btn-login"
							href="login.htm">Login</a>
					</p>
				</div>
			</div>
		</div>
	</div>

</body>
</html>