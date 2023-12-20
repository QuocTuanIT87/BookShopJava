<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Publishers Manage</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet"
	href="assets/styles/admin/listpublisher.css" />
</head>
<body>
	<div>
		<%@include file="/WEB-INF/views/admin/sidebar.jsp"%>
		<div class="list-book">

			<h1 class="title-list">
				<i class="fa-solid fa-toolbox icon-manager"></i> Publisher
				Management <a href="add-publisher.htm"><button class="btn-add">
						<i class="fa-solid fa-plus"></i> Add Publisher
					</button></a>
			</h1>
			<div class="table">
				<div class="table-row header-table">
					<p class="table-cell">No.</p>
					<p class="table-cell">Name</p>
					<p class="table-cell">Address</p>
					<p class="table-cell">Phone number</p>
					<p class="table-cell">Action</p>
				</div>

				<c:forEach var="publisher" items="${listPublishers}"
					varStatus="index">

					<div class="table-row">
						<p class="table-cell">${index.count}</p>
						<p class="table-cell">${publisher.publisherName}</p>
						<p class="table-cell">${publisher.address}</p>
						<p class="table-cell">${publisher.phoneNumber}</p>
						<div class="table-cell action">
							<button class="btn-delete btn">Delete</button>
							<a href="edit-publisher.htm?id=${publisher.idPublisher }">
								<button class="btn-edit btn">Edit</button>
							</a>
						</div>

						<div class="modal">
							<div class="inner-modal">
								<p class="title-modal">Are you sure you want to delete?</p>
								<div class="cover-btn">
									<a href="delete-publisher.htm?id=${publisher.idPublisher }">
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