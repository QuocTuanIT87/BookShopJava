<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Profile Page</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet" href="assets/styles/profile.css" />
</head>
<body>
	<div class="wrapper">
		<%@include file="/WEB-INF/views/include/header.jsp"%>
		<div class="container">
			<%@include file="/WEB-INF/views/include/sidebar-order.jsp"%>
			<div class="content-sidebar">
				<c:choose>
					<c:when test="${pageNumber == 0}">
						<%@include file="/WEB-INF/views/include/my-order.jsp"%>
					</c:when>
					<c:when test="${pageNumber == 1}">
						<%@include file="/WEB-INF/views/include/info-account.jsp"%>
					</c:when>
					<c:when test="${pageNumber == 2}">

					</c:when>
					<c:when test="${pageNumber == 3}">

					</c:when>
				</c:choose>

			</div>

		</div>
	</div>
</body>
</html>