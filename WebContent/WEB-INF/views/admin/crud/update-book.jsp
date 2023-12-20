<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Book</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet"
	href="assets/styles/admin/update-book.css" />
</head>
<body>
	<div class="wrapper">
		<a href="listbook.htm">
			<button class="btn-back">
				<i class="fa-solid fa-chevron-left"></i> Back
			</button>
		</a>
		<div class="container">
			<form action="add-book.htm?type=edit&id=${book.idBook }" method="post"
				enctype="multipart/form-data" class="form-control">
				<h1 class="title">UPDATE BOOK</h1>
				<h2 class="notify-success">${successMessage}</h2>
				<h2 class="notify-failed">${failedMessage}</h2>
				<div class="content">
					<div class="field">
						<label class="form-label"><b>Book name</b></label> <input
							class="input-field" type="text" required placeholder="Book name"
							name="name" value="${book.bookName }" />
					</div>

					<div class="field">
						<label class="form-label"><b>Price</b></label> <input
							class="input-field" type="number" required placeholder="Price"
							name="price" readonly value="${book.price}" />
					</div>

					<div class="field">
						<label class="form-label"><b>Author</b></label> <input
							class="input-field" type="text" required placeholder="Author"
							name="author" value="${book.author }" />
					</div>
					<div class="field">
						<label class="form-label"><b>Publishing year</b></label> <input
							class="input-field" type="number" required
							placeholder="Publishing year" name="year"
							value="${book.publishYear}" />
					</div>
					<div class="field">
						<label class="form-label"><b>Description</b></label> <input
							class="input-field" type="text" required
							placeholder="Description" name="description"
							value="${book.description }" />
					</div>
					<div class="field">
						<label class="form-label"><b>Number of book pages</b></label> <input
							class="input-field" type="number" required
							placeholder="Page number" name="page" value="${book.pageCount }" />
					</div>
					<div class="field">
						<label class="form-label"><b>Discount percent</b></label> <input
							class="input-field" type="number" required
							placeholder="Discount percent" name="discount"
							value="${book.discountPercent }" />
					</div>
					<div class="field">
						<label class="form-label"><b>Quantity</b></label> <input
							class="input-field" type="number" required placeholder="Quantity"
							name="quantity" value=${book.quantity } />
					</div>
					<div class="field">
						<label class="form-label"><b>Category</b></label> <select
							name="category" class="form-select">
							<option value="${book.category.idCategory }">${book.category.categoryName }</option>
							<c:forEach var="category" items="${listCategorys}">
								<option value="${category.idCategory}">${category.categoryName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="field">
						<label class="form-label"><b>Publisher</b></label> <select
							name="publisher" class="form-select">
							<option value="${book.publisher.idPublisher }">${book.publisher.publisherName }</option>
							<c:forEach var="publisher" items="${listPublishers}">
								<option value="${publisher.idPublisher}">${publisher.publisherName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="field">
						<input class="input-field-img" type="file" accept="image/*"
							placeholder="Book name" id="imgInp" name="image" /> <label
							class="label-file-img" for="imgInp"><i
							class="fa-regular fa-image"></i> Choose file</label>
					</div>
				</div>

				<div class="choose-file">
					<img id="blah" alt="your image" class="book-image"
						src="${book.image }" />
				</div>
				<div class="cover-btn">
					<input class="btn-submit" type="submit" value="SAVE CHANGES" />
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