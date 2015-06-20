<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--
	JEVVvO5J806JKFL774cyCK5TBWxk1M0ekLumq_U9MXi0BKwSBCvgnc9Vf8u 
	<form  method="post" action="https://www.paypal.com/cgi-bin/webscr" target="_top" style="opacity: 1;"> -->
	
<form  method="post" action="https://www.sandbox.paypal.com/cgi-bin/webscr" target="_top" style="opacity: 1;"> 
<div id="errorBox" class="hide"></div>
<input type="hidden" name="button" value="buynow">
<input type="hidden" name="business" value="WCWG68APUT6BG">
<input type="hidden" name="item_name" value="Chicken Biryani">
<input type="hidden" name="quantity" value="1">
<input type="hidden" name="amount" value="1">
<input type="hidden" name="currency_code" value="USD">
<input type="hidden" name="shipping" value="0.1">
<input type="hidden" name="tax" value="0.2">
<input type="hidden" name="notify_url" value="http://10.30.159.61:8080/nfc/response/callback">
<input type="hidden" name="cmd" value="_xclick">
<input type="hidden" name="bn" value="JavaScriptButton_buynow">
<input type="hidden" name="env" value="www">
<button class="paypal-button large" type="submit">Buy Now</button>
</form>
</body>
</html>
