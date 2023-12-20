<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Book</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet"
	href="assets/styles/admin/add-book.css" />
</head>
<body>
	<div class="wrapper">
		<a href="listbook.htm">
			<button class="btn-back">
				<i class="fa-solid fa-chevron-left"></i> Back
			</button>
		</a>
		<div class="container">
			<form action="add-book.htm" method="post"
				enctype="multipart/form-data" class="form-control">
				<h1 class="title">ADD NEW BOOK</h1>
				<h2 class="notify-success">${successMessage}</h2>
				<h2 class="notify-failed">${failedMessage}</h2>
				<div class="content">
					<div class="field">
						<label class="form-label"><b>Book name</b></label> <input
							class="input-field" type="text" required placeholder="Book name"
							name="name" />
					</div>
					<div class="field">
						<label class="form-label"><b>Price</b></label> <input
							class="input-field" type="number" required placeholder="Price"
							name="price" />
					</div>
					<div class="field">
						<label class="form-label"><b>Author</b></label> <input
							class="input-field" type="text" required placeholder="Author"
							name="author" />
					</div>
					<div class="field">
						<label class="form-label"><b>Publishing year</b></label> <input
							class="input-field" type="number" required
							placeholder="Publishing year" name="year" />
					</div>
					<div class="field">
						<label class="form-label"><b>Description</b></label> <input
							class="input-field" type="text" required
							placeholder="Description" name="description" />
					</div>
					<div class="field">
						<label class="form-label"><b>Number of book pages</b></label> <input
							class="input-field" type="number" required
							placeholder="Page number" name="page" />
					</div>
					<div class="field">
						<label class="form-label"><b>Discount percent</b></label> <input
							class="input-field" type="number" required
							placeholder="Discount percent" name="discount" />
					</div>
					<div class="field">
						<label class="form-label"><b>Quantity</b></label> <input
							class="input-field" type="number" required placeholder="Quantity"
							name="quantity" />
					</div>
					<div class="field">
						<label class="form-label"><b>Category</b></label> <select
							name="category" class="form-select">
							<c:forEach var="category" items="${listCategorys}">
								<option value="${category.idCategory}">${category.categoryName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="field">
						<label class="form-label"><b>Publisher</b></label> <select
							name="publisher" class="form-select">
							<c:forEach var="publisher" items="${listPublishers}">
								<option value="${publisher.idPublisher}">${publisher.publisherName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="field">
						<input class="input-field-img" type="file" accept="image/*"
							required placeholder="Book name" id="imgInp" name="image" /> <label
							class="label-file-img" for="imgInp"><i
							class="fa-regular fa-image"></i> Choose file</label>
					</div>
				</div>

				<div class="choose-file">
					<img id="blah" src="#" alt="your image" class="book-image" />
				</div>
				<div class="cover-btn">
					<input class="btn-submit" type="submit" value="ADD BOOK" />
				</div>
			</form>
		</div>
	</div>

	<script>
            imgInp.onchange = (evt) => {
                const [file] = imgInp.files;
                if (file) {
                    blah.src = URL.createObjectURL(file);
                }
            };
        </script>
</body>
</html>