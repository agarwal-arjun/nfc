<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="<%=request.getContextPath()%>" />

<div style="margin-top: 30px"></div>
<div class="containbg container">
	<div style="display: inline-block;">
		<h3>TOTAL AMOUNT</h3>
		<h5>${txnAmount} INR</h5>
		<h3>ORDER ID</h3>
		<h6>${orderId}</h6>

		<form method="post" action="${postUrl}" target="_top"
			style="opacity: 1;">
			<div id="errorBox" class="hide"></div>
			<input type="hidden" name="button" value="buynow"> <input
				type="hidden" name="business" value="${mbid}"> <input
				type="hidden" name="item_name" value="Chicken Biryani"> <input
				type="hidden" name="quantity" value="1"> <input
				type="hidden" name="amount" value="${txnAmount}"> <input
				type="hidden" name="currency_code" value="USD"> <input
				type="hidden" name="shipping" value="0.1"> <input
				type="hidden" name="tax" value="0.2"> <input type="hidden"
				name="notify_url" value="${notifyUrl}"> <input type="hidden"
				name="cmd" value="_xclick"> <input type="hidden"
				name="item_number" value="${orderId}"> <input type="hidden"
				name="bn" value="JavaScriptButton_buynow"> <input
				type="hidden" name="env" value="www">
			<div><button style="max-width: 300px;"
				class="btn btn-success btn-block animateBtn" type="submit">Pay
				Now</button>
				</div>
				<div style="margin-top: 10px;">
				<button style="max-width: 300px;"
				class="btn btn-danger btn-block animateBtn" type="button" onclick='j_loader_GET("${context}/restaurant/menu", "html", "mainContainer");' id='cancelTxn'>Cancel</button>
				</div>
		</form>
	</div>
</div>
