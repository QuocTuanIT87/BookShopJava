<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Sidebar</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet"
	href="assets/styles/admin/sidebar.css" />

</head>
<body>

	<div class="sidebar">
		<div class="container">
			<a href="home.htm"> <img src="assets/images/admin.png"
				class="avatar-admin" alt="admin" />
				<p class="name">Administrators</p>
			</a>

			<div class="nav">

				<a href="listbook.htm">
					<div class="item-nav">
						<i class="fa-solid fa-book-open icon-list"></i>
						<p class="name-item">Books</p>
					</div>
				</a><a href="listorder.htm">
					<div class="item-nav">
						<i class="fa-solid fa-box icon-list"></i>
						<p class="name-item">Orders</p>
					</div>
				</a> <a href="listuser.htm">
					<div class="item-nav">
						<i class="fa-regular fa-user icon-list"></i>
						<p class="name-item">Users</p>
					</div>
				</a> <a href="listpublisher.htm">
					<div class="item-nav">
						<i class="fa-solid fa-chalkboard-user icon-list"></i>
						<p class="name-item">Publishers</p>
					</div>
				</a> <a href="list-category.htm">
					<div class="item-nav">
						<i class="fa-solid fa-layer-group icon-list"></i>
						<p class="name-item">Categories</p>
					</div>
				</a> <a href="list-promotion.htm">
					<div class="item-nav">
						<i class="fa-solid fa-percent icon-list"></i>
						<p class="name-item">Promotions</p>
					</div>
				</a>
			</div>
		</div>
		<a href="logout.htm">
			<button class="btn-logout">
				<i class="fa-solid fa-right-from-bracket"></i>
			</button>
		</a>
	</div>
</body>
</html>
