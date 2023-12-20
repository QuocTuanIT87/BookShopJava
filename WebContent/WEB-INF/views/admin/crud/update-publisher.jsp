<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet"
	href="assets/styles/admin/update-publisher.css" />
</head>
<body>
	<div class="wrapper">
		<a href="listpublisher.htm">
			<button class="btn-back">
				<i class="fa-solid fa-chevron-left"></i> Back
			</button>
		</a>
		<div class="container">
			<form
				action="add-publisher.htm?type=edit&id=${publisher.idPublisher }"
				method="post" class="form-control">
				<h1 class="title">UPDATE PUBLISHER</h1>
				<h2 class="notify-success">${successMessage}</h2>
				<h2 class="notify-failed">${failedMessage}</h2>
				<div class="content">
					<div class="field">
						<label class="form-label"><b>Publisher name</b></label> <input
							class="input-field" type="text" required
							placeholder="Publisher name" name="name"
							value="${publisher.publisherName}" />
					</div>

					<div class="field">
						<label class="form-label"><b>Address</b></label> <input
							class="input-field" type="text" required placeholder="Address"
							name="address" value="${publisher.address}" />
					</div>

					<div class="field">
						<label class="form-label"><b>Phone number</b></label> <input
							class="input-field" type="text" required
							placeholder="Phone number" name="phone"
							value="${publisher.phoneNumber}" />
					</div>
				</div>

				<div class="cover-btn">
					<input class="btn-submit" type="submit" value="SAVE CHANGES" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>