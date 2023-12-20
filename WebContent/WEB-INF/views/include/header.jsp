<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>HomePage</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet" href="assets/styles/header.css" />

</head>
<body>

	<div class="cover-header">
		<div class="header">
			<div class="cover-logo">
				<a href="home.htm"> <img class="logo"
					src="assets/images/logo.png" />
				</a>
			</div>
			<div class="cover-search">
				<form class="form-control" action="" method="post">
					<input class="search-bar" type="text" /> <span class="btn-search">Search
						<i class="fa-solid fa-magnifying-glass"></i>
					</span>
				</form>
			</div>
			<div class="navigate-header">
				<a href="home.htm" type="button">
					<div class="btn-navigate">
						<i class="fa-solid fa-house"></i> <span class="title-nav">Home</span>
					</div>
				</a> <a href="account.htm">
					<div class="btn-navigate">
						<i class="fa-regular fa-face-smile-wink"></i>

						<c:if test="${not empty currentUser}">
							<span class="title-nav">${currentUser.name}</span>
						</c:if>

						<c:if test="${empty currentUser}">
							<span class="title-nav">Account</span>
						</c:if>


					</div>
				</a> <a href="cart-detail.htm">
					<div class="btn-navigate">
						<i class="fa-solid fa-cart-arrow-down"></i>
					</div>
				</a>

				<c:if test="${not empty currentUser}">
					<a href="logout.htm">
						<div class="btn-navigate">
							<i class="fa-solid fa-right-from-bracket"></i>
						</div>
					</a>
				</c:if>


			</div>
		</div>
	</div>
</body>
</html>
