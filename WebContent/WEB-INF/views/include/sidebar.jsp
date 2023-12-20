<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Sidebar</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet" href="assets/styles/sidebar.css" />

</head>
<body>

	<div class="sidebar">
		<p class="title-sidebar">Category</p>

		<c:forEach var="category" items="${listCategorys}" varStatus="index">

			<div class="item-category">
				<img class="img-cate" src="${category.image}" /> <span
					class="title-cate">${category.categoryName}</span>
			</div>
		</c:forEach>

	</div>


</body>
</html>
