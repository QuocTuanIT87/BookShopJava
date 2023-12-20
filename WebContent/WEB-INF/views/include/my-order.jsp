<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My order</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet" href="assets/styles/my-order.css" />
</head>
<body>
	<div>
		<h3>My Orders</h3>
		<div class="table">
			<div class="table-row header-table">
				<p class="table-cell bold">Code order</p>
				<p class="table-cell bold">Date of purchase</p>
				<p class="table-cell bold">Receiver</p>
				<p class="table-cell bold">Total Money</p>
				<p class="table-cell bold">Status</p>
				<p class="table-cell bold">Action</p>
			</div>

			<c:forEach var="order" items="${orders}" varStatus="index">
				<div class="table-row">
					<p class="table-cell code">${order.idOrder }</p>
					<fmt:formatDate value="${order.createAt}" pattern="dd-MM-yyyy"
						var="formattedDate" />
					<p class="table-cell">
						<c:out value="${formattedDate}" />
					</p>
					<p class="table-cell">${order.name }</p>
					<p class="table-cell bold money-color">
						<fmt:formatNumber value="${order.sumMoney}" pattern="#,##0 ₫" />
					</p>
					<c:choose>
						<c:when test="${order.status == 0}">
							<p class="table-cell confirm-status">Wait for confirmation</p>

						</c:when>
						<c:when test="${order.status == 1}">
							<p class="table-cell delivery-status">Delivering</p>
						</c:when>
						<c:when test="${order.status == 2}">
							<p class="table-cell success-status">Successful delivery</p>
						</c:when>
						<c:when test="${order.status == 3}">

							<p class="table-cell cancel-status">Delivery failed</p>
						</c:when>

						<c:when test="${order.status == 4}">

							<p class="table-cell cancel-status">Canceled</p>
						</c:when>
					</c:choose>
					<p class="table-cell color-detail">See details</p>
				</div>

				<!--  -->
				<c:forEach var="product" items="${order.orderDetails}"
					varStatus="index">
					
				</c:forEach>
			</c:forEach>






		</div>
	</div>
</body>
</html>