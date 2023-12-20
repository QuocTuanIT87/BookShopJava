<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Detail</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet"
	href="assets/styles/book-detail.css" />
</head>
<body>
	<div>
		<%@include file="/WEB-INF/views/include/header.jsp"%>
		<div class="wrapper">
			<div class="content">
				<form action="add-cart.htm?id=${book.idBook}" method="post">
					<input type="number" class="id-book" value="${book.idBook}" />
					<div class="inner">
						<div class="cover-image">
							<img class="image-item" src="${book.image}" /> </i> <label
								for="btn-add" class="btn btn-add-cart"> <i
								class="fa-solid fa-cart-arrow-down"></i> <input id="btn-add"
								class="add-inner" type="submit" value="Add to cart" />
							</label>


						</div>
						<div class="des">
							<p class="name-book">${book.bookName }</p>
							<div class="cover-detail-info">
								<div class="title-first">
									Publisher:
									<p class="publisher">${book.publisher.publisherName}</p>
								</div>
								<div>
									Author:
									<p class="author">${book.author}</p>
								</div>
							</div>
							<div class="cover-detail-info">
								<div class="title-first">
									Publish year:
									<p class="publisher">${book.publishYear}</p>
								</div>
								<div>
									Number of book pages:
									<p class="author">${book.pageCount}</p>
								</div>
							</div>
							<div class="cover-detail-info">
								<div class="title-first">
									Category:
									<p class="publisher">${book.category.categoryName}</p>
								</div>
								<div></div>
							</div>
							<div class="star">
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
							<div class="cover-price">
								<p class="price">
									<fmt:formatNumber
										value="${book.price - (book.price * book.discountPercent / 100)}"
										pattern="#,##0 ₫" />
								</p>
								<p class="old-price">
									<fmt:formatNumber value="${book.price}" pattern="#,##0 ₫" />
								</p>
								<p class="discount">-${book.discountPercent}%</p>
							</div>
							<div class="quantity">
								<div>
									The remaining amount:
									<p class="remaining">${book.quantity}</p>
								</div>
							</div>
							<div class="quantity">
								Quantity:
								<div class="cover-input">
									<p class="btn-change-quantity sub">
										<i class="fa-solid fa-minus"></i>
									</p>
									<input type="number" value="1" min="1" max="${book.quantity}"
										class="input-quantity" name="quantity" />
									<p class="btn-change-quantity plus">
										<i class="fa-solid fa-plus"></i>
									</p>
								</div>
							</div>
						</div>
					</div>
				</form>
				<a href="#" id="buy-now"><button class="btn btn-buy">Buy
						now</button></a>
			</div>
		</div>


		<div class="des-content">
			<p class="title-des">Description</p>
			<p class="detail-des">${book.description}</p>
		</div>

		<!-- MODAL REVIEW  -->
		<div class="container-modal">
			<div class="modal">
				<div class="post">
					<div class="text">Thanks for rating us!</div>
					<div class="edit">EDIT</div>
				</div>
				<div class="star-widget">
					<form action="add-comment.htm?id=${book.idBook }" method="post">

						<input type="radio" name="rate" id="rate-5" class="input-modal"
							value="5" /> <label for="rate-5"
							class="fas fa-star label-modal last-star"></label> <input
							class="input-modal" type="radio" name="rate" id="rate-4"
							value="4" /> <label for="rate-4" class="fas fa-star label-modal"></label>
						<input class="input-modal" type="radio" name="rate" id="rate-3"
							value="3" /> <label for="rate-3" class="fas fa-star label-modal"></label>
						<input class="input-modal" type="radio" name="rate" id="rate-2"
							value="2" /> <label for="rate-2" class="fas fa-star label-modal"></label>
						<input class="input-modal" type="radio" name="rate" id="rate-1"
							value="1" /> <label for="rate-1" class="fas fa-star label-modal"></label>




						<div class="form-review">
							<header></header>
							<div class="textarea">
								<textarea spellcheck="false" cols="30"
									placeholder="Describe your experience.." name="comment"></textarea>
							</div>
							<div class="btn-modal">
								<button class="btn-post-comment" type="submit">Post</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!--END MODAL REVIEW  -->


		<div class="content-comment">
			<p class="title-des">Product reviews</p>

			<c:if test="${disableReview != 1 }">
				<div class="cover-btn-review">
					<button class="btn-review">
						<i class="fa-solid fa-pen"></i> Write a review
					</button>
				</div>
			</c:if>


			<c:forEach var="comment" items="${listComments}">
				<div class="cover-comment">
					<div class="left-comment">
						<p class="user-name">${comment.user.name }</p>
						<fmt:formatDate value="${comment.createAt }" pattern="dd-MM-yyyy"
							var="formattedDate" />
						<p class="date-comment">
							<c:out value="${formattedDate}" />
						</p>
						<c:if test="${comment.isBuy ==1 }">
							<p class="purchased">
								<i class="fa-solid fa-shield-heart"></i> Purchased
							</p>
						</c:if>

					</div>
					<div class="right-comment">
						<div class="star-comment">
							<c:choose>
								<c:when test="${comment.startCount == 0}">
								</c:when>
								<c:when test="${comment.startCount == 1}">
									<i class="fa-solid fa-star"></i>
									<i class="fa-regular fa-star"></i>
									<i class="fa-regular fa-star"></i>
									<i class="fa-regular fa-star"></i>
									<i class="fa-regular fa-star"></i>
								</c:when>
								<c:when test="${comment.startCount == 2}">
									<i class="fa-solid fa-star"></i>
									<i class="fa-solid fa-star"></i>
									<i class="fa-regular fa-star"></i>
									<i class="fa-regular fa-star"></i>
									<i class="fa-regular fa-star"></i>
								</c:when>
								<c:when test="${comment.startCount == 3}">
									<i class="fa-solid fa-star"></i>
									<i class="fa-solid fa-star"></i>
									<i class="fa-solid fa-star"></i>
									<i class="fa-regular fa-star"></i>
									<i class="fa-regular fa-star"></i>
								</c:when>
								<c:when test="${comment.startCount == 4}">
									<i class="fa-solid fa-star"></i>
									<i class="fa-solid fa-star"></i>
									<i class="fa-solid fa-star"></i>
									<i class="fa-solid fa-star"></i>
									<i class="fa-regular fa-star"></i>
								</c:when>
								<c:when test="${comment.startCount == 5}">
									<i class="fa-solid fa-star"></i>
									<i class="fa-solid fa-star"></i>
									<i class="fa-solid fa-star"></i>
									<i class="fa-solid fa-star"></i>
									<i class="fa-solid fa-star"></i>
								</c:when>
							</c:choose>
						</div>
						<p class="detail-comment">${comment.commentDetail }</p>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	</div>

	<script>
		document.addEventListener('DOMContentLoaded', function() {
			// Get the quantity input and buttons
			var quantityInput = document.querySelector('.input-quantity');
			var idInput = document.querySelector('.id-book');
			var subButton = document.querySelector('.sub');
			var plusButton = document.querySelector('.plus');

			// Subtract one when the minus button is clicked
			subButton.addEventListener('click', function() {
				if (!quantityInput.value) {
					quantityInput.value = 1;
				} else if (quantityInput.value > 1) {
					quantityInput.value = parseInt(quantityInput.value) - 1;
				}
			});

			// Add one when the plus button is clicked
			plusButton.addEventListener('click', function() {
				if (!quantityInput.value) {
					quantityInput.value = 1;
				} else if (quantityInput.value < 99) {
					quantityInput.value = parseInt(quantityInput.value) + 1;
				}
			});

			// Get the quantity input and buy now link
			var buyNowLink = document.getElementById('buy-now');
			var btnBuyNow = document.querySelector('.btn-buy');

			btnBuyNow.addEventListener('click', function() {
				var quantityValue = quantityInput.value;
				var idValue = idInput.value;
				var hrefValue = 'buy-now.htm?quantity=' + quantityValue
						+ "&id=" + idValue;
				buyNowLink.setAttribute('href', hrefValue);
			});
			
			/*JS for modal Review  */
			const btn = document.querySelector('button');
            const post = document.querySelector('.post');
            const widget = document.querySelector('.star-widget');
            const editBtn = document.querySelector('.edit');
            const coverStarModal = document.querySelector('.cover-star-modal');
           
            btn.onclick = () => {
                widget.style.display = 'none';
               
               /*  post.style.display = 'block'; */
                editBtn.onclick = () => {
                	
                    widget.style.display = 'block';
                   /*  post.style.display = 'none'; */
                };
                return false;
            };
            /*END JS for modal Review  */
            
            const btnReview = document.querySelector('.btn-review');
            const containerModalReview = document.querySelector('.container-modal');
            const modalReview = document.querySelector('.modal');
            
            btnReview.addEventListener('click', () => {
            	containerModalReview.classList.add('active');
            })
            
            containerModalReview.addEventListener('click', () => {
            	containerModalReview.classList.remove('active');
            })
            
            modalReview.addEventListener('click', (e) => {
                e.stopPropagation();
            })
		});
	</script>
</body>
</html>