<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Check</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet"
	href="assets/styles/order-check.css" />
</head>
<body>
	<div class="wrapper">
		<c:if test="${not empty messAddress}">
			<div class="notify">
				<p class="message-noty">${messAddress}</p>
			</div>
		</c:if>
		<c:if test="${not empty messPhone}">
			<div class="notify">
				<p class="message-noty">${messPhone}</p>
			</div>
		</c:if>
		<%@include file="/WEB-INF/views/include/header.jsp"%>
		<div class="container">


			<div class="address">
				<h3 class="title-address">
					DELIVERY ADDRESS
					<p class="btn-edit">Edit</p>
				</h3>
				<div class="cover-detail-address">
					<p class="input-address">
						<i class="fa-solid fa-location-dot icon-address"></i>
						${user.address}
					</p>

				</div>
				<div class="cover-detail-address">
					<p class="input-address">
						<i class="fa-solid fa-mobile-screen-button icon-address"></i>
						${user.phoneNumber}
					</p>
				</div>
				<div class="cover-detail-address">
					<p class="input-address">
						<i class="fa-regular fa-user icon-address"></i> ${user.name }
					</p>
				</div>
			</div>

			<div class="address mt-16">
				<h3 class="title-address">METHOD OF SHIPPING</h3>
				<div class="cover-detail-address">
					<p class="input-address text-bold">
						<i class="fa-solid fa-truck icon-address"></i> Standard delivery:
						19,000 đ
					</p>
				</div>
			</div>

			<div class="address mt-16">
				<h3 class="title-address">PAYMENT METHODS</h3>
				<div class="cover-detail-address">
					<p class="input-address text-bold">
						<i class="fa-regular fa-credit-card icon-address"></i> Payment in
						cash upon receipt (Thanh toán bằng tiền mặt khi nhận hàng)
					</p>
				</div>
			</div>

			<div class="address mt-16">
				<h3 class="title-address">PROMOTION CODE</h3>
				<div class="cover-detail-promotion">
					<p class="title-promotion">Promotional code</p>
					<p class="btn-select-promotion">Select promotional code</p>
				</div>
			</div>

			<div class="address mt-16">
				<h3 class="title-address">CHECK YOUR ORDER AGAIN</h3>

				<c:forEach var="item" items="${cart}" varStatus="index">

					<div class="cover-item">
						<div class="cover-img">
							<img src="${item.book.image}" class="img-item" />
						</div>
						<p class="book-name">${item.book.bookName}</p>
						<p class="cover-price">
						<div class="price">
							<fmt:formatNumber
								value="${item.book.price - (item.book.price * item.book.discountPercent / 100)}"
								pattern="#,##0 ₫" />
							<p class="old-price">
								<fmt:formatNumber value="${item.book.price}" pattern="#,##0 ₫" />
							</p>
						</div>
						</p>
						<p class="quantity">${item.quantity}</p>
						<p class="total-money-item">
							<fmt:formatNumber
								value="${(item.book.price - (item.book.price * item.book.discountPercent / 100)) * item.quantity}"
								pattern="#,##0 ₫" />
						</p>
					</div>
				</c:forEach>



			</div>

			<div class="footer-order">
				<div class="container-footer">
					<div class="top-footer">
						<div class="cover-line-price">

							<c:forEach var="item" items="${cart}" varStatus="index">
								<c:set var="total"
									value="${total + ((item.book.price - (item.book.price * item.book.discountPercent / 100)) * item.quantity)}" />
								<c:set var="discount" value="${item.discountPercent }" />
							</c:forEach>

							<span>Money</span> <span class="detail-price"> <fmt:formatNumber
									value="${total}" pattern="#,##0 ₫" />
							</span>
						</div>

						<div class="cover-line-price">
							<span>Shipping Fee (Standard Delivery)</span> <span
								class="detail-price"><fmt:formatNumber value="${19000}"
									pattern="#,##0 ₫" /></span>
						</div>

						<div class="cover-line-price">
							<span><i class="fa-solid fa-percent"></i> Discount</span> <span
								class="detail-price"><fmt:formatNumber
									value="-${total * discount / 100}" pattern="#,##0 ₫" /></span>
						</div>

						<div class="cover-line-price">
							<span>The total amount</span> <span
								class="detail-price detail-price-sum"><fmt:formatNumber
									value="${total - (total * discount / 100) + 19000}"
									pattern="#,##0 ₫" /></span>
						</div>

					</div>
					<div class="bottom-footer">
						<a href="cart-detail.htm">
							<p class="btn-return">
								<i class="fa-solid fa-arrow-left"></i> Return to cart
							</p>
						</a> <a
							href="confirm-payment.htm?total=${total - (total * discount / 100) + 19000}">
							<button class="btn-confirm-pay">Payment confirmation</button>
						</a>
					</div>
				</div>
			</div>

			<!-- Modal edit address -->
			<div class="modal-address">
				<form action="update-user.htm">
					<div class="modal-container">
						<div class="modal-inner">
							<h3 class="title-modal-address">CHANGE OF DELIVERY ADDRESS</h3>
							
							<div class="cover-input">
								<span class="label-address">Phone number:</span><input
									type="text" required class="form-input"
									value="${user.phoneNumber}" name="phone" />
							</div>
							<div class="cover-input">
								<span class="label-address">Delivery address:</span><input
									type="text" required class="form-input" value="${user.address}"
									name="address" />
							</div>
							<div class="cover-btn">
								<input class="btn-save" type="submit" value="Save" />
							</div>
							<div class="cover-btn">
								<button class="btn-cancel">Cancel</button>
							</div>
						</div>
					</div>
				</form>
			</div>
			<!-- Modal apply promotion -->
			<div class="modal-promotion">
				<form action="">
					<div class="modal-container">
						<div class="modal-inner">
							<h3 class="title-modal-promotion">
								<img src="assets/images/ico_coupon.svg" />CHOOSE PROMOTION CODE
							</h3>

							<c:forEach var="item" items="${listDiscounts}" varStatus="index">
								<div class="item-promotion">
									<div class="cover-img-promotion">
										<img class="img-promotion"
											src="assets/images/voucher-icon.jpg" />
									</div>
									<div class="right-img">
										<p class="detail-promotion">${item.detailDiscount}</p>
										<p class="time-promotion">
											<fmt:formatDate value="${item.startDay}" pattern="dd-MM"
												var="startDay" />
											<fmt:formatDate value="${item.endDay}" pattern="dd-MM"
												var="endDay" />
											<c:choose>
												<c:when test="${startDay == endDay}">
													Applicable in <span class="day"><c:out
													value="${startDay}" /></span> 
												</c:when>
												<c:otherwise>
													Applicable from <span class="day"><c:out
													value="${startDay}" /></span> to <span class="day"><c:out
													value="${endDay}" /></span>
												</c:otherwise>
											</c:choose>
										</p>
										<a href="select-promotion.htm?percent=${item.discountPercent}"
											class="cover-apply-promotion">
											<p class="btn-apply-promotion">Apply</p>
										</a>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script>
            const editAddress = document.querySelector('.btn-edit');
            const modalAddress = document.querySelector('.modal-address');
            const modalAddressCancels = document.querySelectorAll('.btn-cancel');
            const modalPromotion = document.querySelector('.modal-promotion');
            const btnSelectPromotion = document.querySelector('.btn-select-promotion');
            const modalContainers = document.querySelectorAll('.modal-container');

            editAddress.addEventListener('click', () => {
                modalAddress.classList.add('active');
            });

            modalAddressCancels.forEach((btn) => {
                btn.addEventListener('click', () => {
                    modalAddress.classList.remove('active');
                });
            });
            
            modalContainers.forEach((btn) => {
                btn.addEventListener('click', (e) => {
                	 e.stopPropagation();
                });
            });

            btnSelectPromotion.addEventListener('click', () => {
                modalPromotion.classList.add('active');
            });
            
            modalPromotion.addEventListener('click', () => {
                modalPromotion.classList.remove('active');
            });
            
            modalAddress.addEventListener('click', () => {
            	modalAddress.classList.remove('active');
            });

            modalAddressCancels.forEach((btn) => {
                btn.addEventListener('click', () => {
                    modalPromotion.classList.remove('active');
                });
            });
        </script>
</body>
</html>