<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta charset="utf-8">
<title>Home Page</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet" href="assets/styles/homepage.css" />
</head>
<body>
	<div class="wrapper">
		<%@include file="/WEB-INF/views/include/header.jsp"%>
		<div class="cover-content">
			<div class="inner-content">
				<%@include file="/WEB-INF/views/include/sidebar.jsp"%>
				<div class="content">
					<div class="slider-content">
						<%@include file="/WEB-INF/views/include/slider.jsp"%>
					</div>
					<div class="book-trend">
						<p class="title-list-book">Selling products</p>

						<div class="inner-list-book">
							<div class="item-book">
								<img class="img-book-item" src="assets/images/item-book.jpg" />
								<p class="book-name">Thay Đổi Cuộc Sống Với Nhân Số Học</p>
								<p class="price-book">681.300đ</p>
								<p class="old-price">757.000đ</p>
								<div class="stars"></div>
								<div class="discount">10%</div>
							</div>
						</div>
					</div>
					<div class="list-book">
						<p class="title-list-book">List books</p>

						<div class="inner-list-book">
							<c:forEach var="book" items="${listBooks}">

								<a href="book-detail.htm?id=${book.idBook}">
									<div class="item-book">
										<img class="img-book-item" src="${book.image }" />
										<p class="book-name">${book.bookName}</p>
										<p class="price-book">
											<fmt:formatNumber
												value="${book.price - (book.price * book.discountPercent / 100)}"
												pattern="#,##0 ₫" />
										</p>
										<p class="old-price">
											<fmt:formatNumber value="${book.price}" pattern="#,##0 ₫" />
										</p>
										<div class="stars">
											<c:choose>
												<c:when test="${book.startCount == 0}">
												</c:when>
												<c:when test="${book.startCount == 1}">
													<i class="fa-solid fa-star"></i>
													<i class="fa-regular fa-star"></i>
													<i class="fa-regular fa-star"></i>
													<i class="fa-regular fa-star"></i>
													<i class="fa-regular fa-star"></i>
												</c:when>
												<c:when test="${book.startCount == 2}">
													<i class="fa-solid fa-star"></i>
													<i class="fa-solid fa-star"></i>
													<i class="fa-regular fa-star"></i>
													<i class="fa-regular fa-star"></i>
													<i class="fa-regular fa-star"></i>
												</c:when>
												<c:when test="${book.startCount == 3}">
													<i class="fa-solid fa-star"></i>
													<i class="fa-solid fa-star"></i>
													<i class="fa-solid fa-star"></i>
													<i class="fa-regular fa-star"></i>
													<i class="fa-regular fa-star"></i>
												</c:when>
												<c:when test="${book.startCount == 4}">
													<i class="fa-solid fa-star"></i>
													<i class="fa-solid fa-star"></i>
													<i class="fa-solid fa-star"></i>
													<i class="fa-solid fa-star"></i>
													<i class="fa-regular fa-star"></i>
												</c:when>
												<c:when test="${book.startCount == 5}">
													<i class="fa-solid fa-star"></i>
													<i class="fa-solid fa-star"></i>
													<i class="fa-solid fa-star"></i>
													<i class="fa-solid fa-star"></i>
													<i class="fa-solid fa-star"></i>
												</c:when>
											</c:choose>
										</div>
										<div class="discount">${book.discountPercent}%</div>
									</div>
								</a>
							</c:forEach>
						</div>


					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>