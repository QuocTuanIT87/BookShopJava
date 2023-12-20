<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Books Manage</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet"
	href="assets/styles/admin/listbook.css" />
</head>
<body>
	<div>
		<%@include file="/WEB-INF/views/admin/sidebar.jsp"%>
		<div class="list-book">
			<h1 class="title-list">
				<i class="fa-solid fa-toolbox icon-manager"></i> Book Management <a
					href="add-book.htm"><button class="btn-add">
						<i class="fa-solid fa-plus"></i> Add Book
					</button></a>
			</h1>
			<div class="table">
				<div class="table-row header-table">
					<p class="table-cell">No.</p>
					<p class="table-cell">Image</p>
					<p class="table-cell">Name</p>
					<p class="table-cell">Price</p>
					<p class="table-cell">Discount percent</p>
					<p class="table-cell">Quantity</p>
					<p class="table-cell">Category</p>
					<p class="table-cell">Publisher</p>
					<p class="table-cell">Action</p>
				</div>

				<c:forEach var="book" items="${listBooks}" varStatus="index">

					<div class="table-row">
						<p class="table-cell">${index.count}</p>
						<p class="table-cell">
							<img class="book-image" src="${book.image}" alt="book-image" />
						</p>
						<p class="table-cell">${book.bookName}</p>
						<p class="table-cell">
							<fmt:formatNumber value="${book.price}" pattern="#,##0 ₫" />
						</p>
						<p class="table-cell">${book.discountPercent}%</p>
						<p class="table-cell">${book.quantity}</p>
						<p class="table-cell">${book.category.categoryName}</p>
						<p class="table-cell">${book.publisher.publisherName}</p>
						<div class="table-cell action">
							<button class="btn-delete btn">Delete</button>
							<a href="edit-book.htm?id=${book.idBook }">
								<button class="btn-edit btn">Edit</button>
							</a>
						</div>

						<div class="modal">
							<div class="inner-modal">
								<p class="title-modal">Are you sure you want to delete?</p>
								<div class="cover-btn">
									<a href="delete-book.htm?id=${book.idBook }">
										<button class="btn btn-delete-modal">Delete</button>
									</a>
									<button class="btn btn-cancel">Cancel</button>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

	<script>
		const deleteButtons = document.querySelectorAll('.btn-delete');
		const modals = document.querySelectorAll('.modal');
		const deleteModalBtn = document.querySelector('.btn-delete-modal');
		const cancelBtns = document.querySelectorAll('.btn-cancel');
		
		deleteButtons.forEach(function(deleteButton, index) {
		    deleteButton.addEventListener('click', function(e) {
		        // Ẩn tất cả các modals trước khi hiển thị modal tương ứng
		        modals.forEach((item) => {
		            item.classList.remove('active');
		        });

		        // Hiển thị modal tương ứng với deleteButton được click
		        modals[index].classList.add('active');
		    });
		});

		deleteModalBtn.addEventListener('click', function(e) {
			modal.classList.remove('active');
		});

		cancelBtns.forEach((item, index) => {
			item.addEventListener('click', function(e) {
				  modals[index].classList.remove('active');
			});
		})
	</script>

</body>
</html>