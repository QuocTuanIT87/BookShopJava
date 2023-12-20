<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Discount</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script>
	$(function() {
		$("#start-day-input").datepicker({
			minDate : 0, // 0 means today
			dateFormat : 'dd-mm-yy', // Set your desired date format
		});
	});

	$(function() {
		$("#end-day-input").datepicker({
			minDate : 0, // 0 means today
			dateFormat : 'dd-mm-yy', // Set your desired date format
		});
	});
</script>



<link type='text/css' rel="stylesheet"
	href="assets/icons/fontawesome-free-6.4.2-web/css/all.css" />
<link type='text/css' rel="stylesheet"
	href="assets/styles/admin/add-discount.css" />
</head>
<body>
	<div class="wrapper">
		<a href="list-promotion.htm">
			<button class="btn-back">
				<i class="fa-solid fa-chevron-left"></i> Back
			</button>
		</a>
		<div class="container">
			<form action="add-discount.htm?type=edit&id=${discount.idDiscount }"
				method="post" class="form-control">
				<h1 class="title">UPDATE DISCOUNT CODE</h1>
				<h2 class="notify-success">${successMessage}</h2>
				<h2 class="notify-failed">${failedMessage}</h2>
				<div class="content">
					<div class="field">
						<label class="form-label"><b>Detail discount</b></label> <input
							class="input-field" type="text" required
							placeholder="Category name" name="detail"
							value="${discount.detailDiscount }" />
					</div>

					<div class="field">
						<label class="form-label"><b>Start day</b></label> <input readonly
							class="input-field" type="text" required autocomplete="off"
							name="start" id="start-day-input" value="${startDay }" placeholder="Start day"/>
					</div>

					<div class="field">
						<label class="form-label"><b>End day</b></label> <input readonly
							class="input-field" type="text" required autocomplete="off"
							placeholder="End day" name="end" id="end-day-input"
							value="${endDay }" />
					</div>

					<div class="field">
						<label class="form-label"><b>CODE</b></label> <input
							class="input-field" type="text" required
							placeholder="Category name" name="code" value="${discount.code }" />
					</div>

					<div class="field">
						<label class="form-label"><b>Discount percent</b></label> <input
							class="input-field" type="number" required
							placeholder="Category name" name="percent"
							value="${discount.discountPercent }" />
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