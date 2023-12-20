<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users Manage</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet"
	href="assets/styles/admin/listuser.css" />
</head>
<body>
	<div>
		<%@include file="/WEB-INF/views/admin/sidebar.jsp"%>
		<div class="list-book">
			<div class="modal">
				<div class="inner-modal">
					<p class="title-modal">Are you sure you want to lock this user?</p>
					<div class="cover-btn">
						<a href="delete-book.htm">
							<button class="btn btn-delete-modal">Delete</button>
						</a>
						<button class="btn btn-cancel">Cancel</button>
					</div>
				</div>
			</div>
			<h1 class="title-list">
				<i class="fa-solid fa-toolbox icon-manager"></i>User Management
			</h1>
			<div class="table">
				<div class="table-row header-table">
					<p class="table-cell">No.</p>
					<p class="table-cell">Image</p>
					<p class="table-cell">Name</p>
					<p class="table-cell">Email</p>
					<p class="table-cell">Address</p>
					<p class="table-cell">Action</p>
				</div>

				<c:forEach var="user" items="${listUsers}" varStatus="index">
					
						<div class="table-row">
							<p class="table-cell">${index.count}</p>
							<p class="table-cell">
								<img class="book-image" src="assets/images/item-book.jpg"
									alt="book-image" />
							</p>
							<p class="table-cell">${user.name}</p>
							<p class="table-cell">${user.email}</p>

							<div class="cover-address">${user.address}</div>



							<div class="table-cell action">
								<c:choose>
									<c:when test="${user.isLock == 0}">
										<a href="lock-user.htm?email=${user.email}">
											<div class="cover-lock">
												<i class="icon-lock fa-solid fa-lock"></i>Lock
											</div>
										</a>
									</c:when>
									<c:otherwise>
										<a href="unlock-user.htm?email=${user.email}">
											<div class="cover-lock">
												<i class="icon-open fa-solid fa-lock-open"></i>Unlock
											</div>
										</a>
									</c:otherwise>
								</c:choose>

							</div>
						</div>
					
				</c:forEach>


			</div>
		</div>
	</div>

	<script>
		const deleteButtons = document.querySelectorAll('.btn-delete');
		const modal = document.querySelector('.modal');
		const deleteModalBtn = document.querySelector('.btn-delete-modal');
		const cancelBtn = document.querySelector('.btn-cancel');

		deleteButtons.forEach(function(deleteButton) {
			deleteButton.addEventListener('click', function(e) {
				// Đặt mã xử lý tại đây khi nút được click
				modal.classList.add('active');
			});
		});

		deleteModalBtn.addEventListener('click', function(e) {
			modal.classList.remove('active');
		});

		cancelBtn.addEventListener('click', function(e) {
			modal.classList.remove('active');
		});
	</script>

</body>
</html>