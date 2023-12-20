<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Orders Manager</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet"
	href="assets/styles/admin/listorder.css" />
</head>
<body>
	<div>
		<%@include file="/WEB-INF/views/admin/sidebar.jsp"%>
		<div class="list-book">
			<h1 class="title-list">
				<i class="fa-solid fa-toolbox icon-manager"></i> Order Management
			</h1>
			<div class="table">
				<div class="table-row header-table">
					<p class="table-cell">No.</p>
					<p class="table-cell">Order date</p>
					<p class="table-cell">Status</p>
					<p class="table-cell">User name</p>
					<p class="table-cell">Address</p>
					<p class="table-cell">Sum money</p>
					<p class="table-cell">Phone number</p>
					<p class="table-cell">Action</p>
				</div>

				<c:forEach var="order" items="${listOrders}" varStatus="index">

					<div class="table-row">
						<p class="table-cell">${index.count}</p>
						<fmt:formatDate value="${order.createAt}" pattern="dd-MM-yyyy"
							var="formattedDate" />
						<p class="table-cell">
							<c:out value="${formattedDate}" />
						</p>

						<p class="table-cell">
							<c:choose>
								<c:when test="${order.status == 0}">
									<a href="admin/confirm-order.htm?id=${order.idOrder }">
										<button class="btn-confirm">Order confirmation</button>
									</a>
								</c:when>
								<c:when test="${order.status == 1}">
									<a href="admin/success-delivery.htm?id=${order.idOrder }">
										<button class="btn-success-delivery btn">Successful
											delivery</button>
									</a>
									<a href="admin/cancel-order.htm?id=${order.idOrder }">
										<button class="btn-cancel-order btn">Cancel order</button>
									</a>
								</c:when>
								<c:when test="${order.status == 2}">
									<button class="btn-completed ">Completed</button>
								</c:when>
								<c:when test="${order.status == 3}">
									<button class="btn-canceled ">Delivery failed</button>
								</c:when>
								<c:when test="${order.status == 4}">
									<button class="btn-canceled ">Canceled</button>
								</c:when>
							</c:choose>


						</p>
						<p class="table-cell">${order.name}</p>
						<p class="table-cell max-address">${order.address}</p>
						<p class="table-cell max-address">
							<fmt:formatNumber value="${order.sumMoney}" pattern="#,##0 ₫" />
						</p>
						<p class="table-cell">${order.phoneNumber}</p>
						<div class="table-cell action">
							<a href="detail-order.htm">
								<button class="detail-btn">See details</button>
							</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>