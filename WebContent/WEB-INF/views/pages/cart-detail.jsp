<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart Detail</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet"
	href="assets/styles/cart-detail.css" />
</head>
<body>
	<div>
		<%@include file="/WEB-INF/views/include/header.jsp"%>
		<div class="wrapper">
			<div class="container">
				<div class="inner">
					<c:choose>
						<c:when test="${cart.size() > 0}">
							<div class="left-cart">
								<div class="header-cart">
									<p class="left-header">CART (${cart.size()} products)</p>
									<div class="right-header">
										<p class="quantity-header">Quantity</p>
										<p>Money</p>
									</div>
								</div>
								<div class="list-item">

									<c:forEach var="item" items="${cart}" varStatus="index">

										<div class="item">
											<div class="cover-img">
												<img src="${item.book.image}" class="img-item" />
											</div>
											<div class="des">
												<p class="book-name">${item.book.bookName }</p>
												<div class="cover-price">
													<p class="price">
														<fmt:formatNumber
															value="${item.book.price - (item.book.price * item.book.discountPercent / 100)}"
															pattern="#,##0 ₫" />
													</p>
													<p class="old-price">
														<fmt:formatNumber value="${item.book.price}"
															pattern="#,##0 ₫" />
													</p>
												</div>
											</div>
											<div class="cover-input">
												<a
													href="update-item-quantity.htm?id=${item.book.idBook}&quantity=${item.quantity - 1}">
													<p class="btn-change-quantity sub">
														<i class="fa-solid fa-minus"></i>
													</p>
												</a> <input type="number" value="${item.quantity}" min="1"
													max="${item.book.quantity}" class="input-quantity" /> <a
													href="update-item-quantity.htm?id=${item.book.idBook}&quantity=${item.quantity + 1}">
													<p class="btn-change-quantity plus">
														<i class="fa-solid fa-plus"></i>
													</p>
												</a>
											</div>
											<div>
												<p class="total-money-item">
													<fmt:formatNumber
														value="${(item.book.price - (item.book.price * item.book.discountPercent / 100)) * item.quantity}"
														pattern="#,##0 ₫" />
												</p>
											</div>
											<div class="btn-delete">
												<a href="remove-item-cart.htm?id=${item.book.idBook}"> <i
													class="fa-regular fa-trash-can"></i>
												</a>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
							<div class="right-cart">
								<div class="inner-right">
									<div class="cover-sum-money">
										<p class="label">Book money</p>

										<!-- Hiển thị tổng tiền -->
										<c:choose>
											<c:when test="${cart.size() > 0}">
												<c:forEach var="item" items="${cart}" varStatus="index">
													<c:set var="total"
														value="${total + ((item.book.price - (item.book.price * item.book.discountPercent / 100)) * item.quantity)}" />
												</c:forEach>
											</c:when>
											<c:otherwise>
												<c:set var="total" value="0" />
											</c:otherwise>
										</c:choose>

										<p class="detail-money">
											<fmt:formatNumber value="${total}" pattern="#,##0 ₫" />
										</p>

									</div>
									<div class="cover-sum-money">
										<p class="label">Shipping fee (Standard Delivery)</p>
										<p class="detail-money">19.000 đ</p>
									</div>
									<div class="cover-sum-money cover-total">
										<p class="label-total">Total Amount</p>
										<p class="detail-money detail-money-total" id="total-money">
											<fmt:formatNumber value="${total + 19000}" pattern="#,##0 ₫" />
										</p>
									</div>
									<div class="cover-btn-pay">
										<a href="order-check.htm"><button class="btn-pay">PAY</button>
										</a>
									</div>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="cover-empty-cart">
								<img src="assets/images/ico_emptycart.svg" class="img-empty" />
								<p class="title-empty">There are no products in your cart.</p>
								<a href="home.htm">
									<button class="btn-buy">SHOP NOW</button>
								</a>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>

	<script>
		document
				.addEventListener(
						'DOMContentLoaded',
						function() {
							// Get the quantity inputs and buttons
							var quantityInputs = document
									.querySelectorAll('.input-quantity');
							var subButtons = document.querySelectorAll('.sub');
							var plusButtons = document
									.querySelectorAll('.plus');

							// Iterate over each set of elements
							for (var i = 0; i < quantityInputs.length; i++) {
								// Subtract one when the minus button is clicked
								subButtons[i]
										.addEventListener(
												'click',
												function(event) {
													var quantityInput = event.target
															.closest(
																	'.cover-input')
															.querySelector(
																	'.input-quantity');
													if (!quantityInput.value) {
														quantityInput.value = 1;
													} else if (quantityInput.value > 1) {
														quantityInput.value = parseInt(quantityInput.value) - 1;
													}
												});

								// Add one when the plus button is clicked
								plusButtons[i]
										.addEventListener(
												'click',
												function(event) {
													var quantityInput = event.target
															.closest(
																	'.cover-input')
															.querySelector(
																	'.input-quantity');
													if (!quantityInput.value) {
														quantityInput.value = 1;
													} else if (quantityInput.value < 99) {
														quantityInput.value = parseInt(quantityInput.value) + 1;
													}
												});
							}
						});
	</script>

</body>
</html>