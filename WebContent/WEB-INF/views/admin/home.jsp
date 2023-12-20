<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Administrators</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet"
	href="assets/styles/admin/home.css" />
</head>
</head>
<body>
	<div class="wrapper">
		<%@include file="/WEB-INF/views/admin/sidebar.jsp"%>
		<h1 class="title">WELCOME TO ADMIN PAGE</h1>
		<div class="container-left">
			<div class="item">
				<div class="left-item">
					<span class="icon-title"><i class="fa-solid fa-chart-simple"></i></span>
					<p class="title-item">Total revenue</p>
					<div>
						<p class="detail">
							<fmt:formatNumber value="${totalRevenuev }" pattern="#,##0 ₫" />
						</p>
					</div>
				</div>
				<div class="right-item">
					<i class="fa-solid fa-hand-holding-dollar"></i>
				</div>
			</div>

			<div class="item">
				<div class="left-item">
					<span class="icon-title"><i class="fa-solid fa-chart-simple"></i></span>
					<p class="title-item">Number of products sold</p>
					<div>
						<p class="detail">${totalProductSold }</p>
					</div>
				</div>
				<div class="right-item">
					<i class="fa-solid fa-chart-pie"></i>
				</div>
			</div>

			<div class="item">
				<div class="left-item">
					<span class="icon-title"><i class="fa-solid fa-chart-simple"></i></span>
					<p class="title-item">Customers number</p>
					<div>
						<p class="detail">${totalUser}</p>
					</div>
				</div>
				<div class="right-item">
					<i class="fa-solid fa-users-line"></i>
				</div>
			</div>
		</div>
	</div>
</body>
</html>