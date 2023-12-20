<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Category</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet"
	href="assets/styles/admin/add-category.css" />
</head>
<body>
	<div class="wrapper">
		<a href="list-category.htm">
			<button class="btn-back">
				<i class="fa-solid fa-chevron-left"></i> Back
			</button>
		</a>
		<div class="container">
			<form action="add-category.htm" method="post"
				enctype="multipart/form-data" class="form-control">
				<h1 class="title">ADD NEW CATEGORY</h1>
				<h2 class="notify-success">${successMessage}</h2>
				<h2 class="notify-failed">${failedMessage}</h2>
				<div class="content">
					<div class="field">
						<label class="form-label"><b>Category name</b></label> <input
							class="input-field" type="text" required
							placeholder="Category name" name="category" />
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
					<input class="btn-submit" type="submit" value="ADD CATEGORY" />
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