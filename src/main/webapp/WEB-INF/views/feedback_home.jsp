<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> --%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" import="java.util.*"%>
<c:set var="context" value="<%=request.getContextPath()%>" />
<c:set var="SID" value="<%=session.getId()%>" />
<c:set var="merchant" value='<%=session.getAttribute("merchantId")%>' />
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="icon" href="${context}/images/favicon.ico"
	type="image/x-icon">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">

<meta name="description" content="">
<meta name="author" content="Nikata, Restaurant, Dining, Food, Drinks">
<meta name="keyword" content="Nikata, Restaurant, Dining, Food, Drinks">
<title>provide feedback</title>
<link href="../assets/css/style.css" rel="stylesheet">

<script src="../assets/js/jquery.min.js"></script>

<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<script src="../assets/js/bootstrap.min.js"></script>

<link href="../assets/css/datepicker.css" rel="stylesheet">
<script src="../assets/js/bootstrap-datepicker.js"></script>


<script>
<%
Cookie[] cookies=request.getCookies();
String cookie=null;
for (int i=0; i<cookies.length; i++)
{
	if(cookies[i].getName().equals("social")){
	  cookie=cookies[i].getValue();
	  break;
	}
}%>
window.glob = '${context}';
window.onload = function() {
	var cookieVal="<%=cookie%>";
	if(cookieVal==null)
	var obj;
	if(cookieVal!=undefined){
		obj =cookieVal.split(",");
		$('#userId').val(obj[0].split(":")[1]);
		$('#firstName').val(obj[1].split(":")[1]);
		$('#lastName').val(obj[2].split(":")[1]); 
		 $('#email').val(obj[3].split(":")[1]);
	}
}

	$(function() {
		$(".btn-primary").click(function() {
			//$(this).attr("disabled", "disabled");
			var userJson = decodeURIComponent($('#feedbackForm').serialize());
			var jsonStr=createJson(userJson);
			var isValid=validateData(jsonStr);
			if(isValid){
						
						$.ajax({
							url : "${context}/restaurant/feedback",
							type : "POST",
							beforeSend: function(xhr) {
					            xhr.setRequestHeader("Accept", "application/json");
					            xhr.setRequestHeader("Content-Type", "application/json");
					        } ,
							
							data : jsonStr,
							
							success : function(data) {
								alert("Thank You!");
							}
						});
			
						$('#mainContainer').hide();
						$('#mainContainerReturn').show();
			}else{
				
				return false;
			}

		});
	});
	
	function getName() {
		if (document.cookie != '') {
			var cookie_val = '';
			index = document.cookie.indexOf('social');
			
			if (index != -1) {
				namestart = (document.cookie.indexOf("=", index) + 1);
				nameend = document.cookie.indexOf(";", index);
				if (nameend == -1) {
					nameend = document.cookie.length;
				}
				cookie_val = document.cookie.substring(namestart, nameend);
				alert(cookie_val);
				return cookie_val;
			}
		}
	}
	
	
	function createJson(str){
		var dataAr=str.split("&");
		var jsonStr="{";
			for	(index = 0; index < dataAr.length; index++) {
			    if(index==(dataAr.length-1))
			    	jsonStr =jsonStr+ JSON.stringify(dataAr[index].split("=")[0])+":"+JSON.stringify(dataAr[index].split("=")[1])+"}";
				else
					jsonStr =jsonStr+ JSON.stringify(dataAr[index].split("=")[0])+":"+JSON.stringify(dataAr[index].split("=")[1])+",";
			}
		return jsonStr;
	}
	
	function validateData(jsonStr) {
		var numbers = /^\d+$/;
		var re =/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
		 
		var validateObj=JSON.parse(jsonStr);
		var errorStr;
		if(validateObj.firstName==''){
			errorStr="First Name should not be blank";
		}else if(validateObj.lastName==''){
			errorStr="Last Name should not be blank";
		}else if(validateObj.mobile==''||validateObj.mobile.length<10||validateObj.mobile.length>13){
			errorStr="mobile number length not valid";
		}else if(!numbers.test(validateObj.mobile)){
			errorStr="mobile number should be numbers";
		}else if(validateObj.email==''){
			errorStr="email should not be blank";
		}else if(!re.test(validateObj.email)){
			errorStr="email is not valid";
		}else if(validateObj.dob!='' && isValidDate(validateObj.dob)){
			errorStr="dob is not valid";
		}else if(validateObj.anniversary!='' && isValidDate(validateObj.anniversary)){
			errorStr="anniversary is not valid";
		}
		if(errorStr!=undefined && errorStr.length>0){
			$('#errorDisplay').show();
			document.getElementById('errorDisplay').innerHTML = errorStr;
			return 0;
		}else
			return 1;
	}
	
	$(function() {
		$('#anniversary').datepicker({
			format:"yyyy-mm-dd"
		});
		$('#dob').datepicker({
			format:"yyyy-mm-dd"
		});
	});
	
	function isValidDate(dateString)
	{
	    // First check for the pattern
	    if(!/^\d{2}\/\d{2}\/\d{4}$/.test(dateString))
	        return false;

	    // Parse the date parts to integers
	    var parts = dateString.split("/");
	    var day = parseInt(parts[1], 10);
	    var month = parseInt(parts[0], 10);
	    var year = parseInt(parts[2], 10);

	    // Check the ranges of month and year
	    if(year < 1000 || year > 3000 || month == 0 || month > 12)
	        return false;

	    var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

	    // Adjust for leap years
	    if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
	        monthLength[1] = 29;

	    // Check the range of the day
	    return day > 0 && day <= monthLength[month - 1];
	}
</script>
<style>
select {
	display: inline-block !important;
}

.forminput {
	width: 48%;
	display: inline-block !important;
	margin: 10px 0px 10px 0px;
}

select {
	padding: 5px;
	border: 1px solid #ddd;
	border-radius: 4px;
}
</style>
</head>
<body>
<!-- enctype="multipart/form-data" -->
	<div class='jbackground'></div>
	<div style="min-width: 340px;" id='mainContainer' class="container">
		<div class="page-header">
  			<h1>Feedback Form</h1>
		</div>
		<form:form name="feedbackForm" id="feedbackForm" action="${context}/restaurant/feedback"
			method="POST" commandName="feedback">
			<%-- <form:errors path="*" cssClass="error" element="div" /> --%>
			<div style="min-width: 340px;text-align: center;display:none;" id='errorDisplay' class="alert alert-danger">
			</div>
			<table class="table form-group">
			<tr>
					<td>		
					<label for="firstName">First Name*:</label>		
					<!-- <span>First Name*:</span> -->
					</td>
					<td>
					<form:input path="firstName" id="firstName" required="true" class="form-control forminput"/>
					</td>
			</tr>
			<tr>
					
					<td><label for="lastName">Last Name*:</label></td>
					<td><form:input path="lastName" id= "lastName" required="true" class="form-control forminput"/></td>	
					
			</tr>
			
			<tr>
					<td><label for="mobile">Mobile*:</label></td>
					<td><form:input path="mobile" required="true" class="form-control forminput"/></td>
			</tr>
			
			<tr>	
					<td>
					<label for="email">Email*:</label>
					</td>
					<td>
					<form:input path="email" id="email" required="true" class="form-control forminput"/>
					</td>
					
					
			</tr>
			<tr>
					<td>
						<label for="rating">Restaurant Ratng:</label>
					</td>
					<td>
						<form:select path="rating" class="form-control forminput" >
						<form:option value="1" />
						<form:option value="2" />
						<form:option value="3" seleted="true"/>
						<form:option value="4" />
						<form:option value="5" />
					</form:select>					
					</td>
			</tr>
			
			<tr>
				<td>
						<label for="dob">Date Of Birth:</label>
				</td>
				<td>
					<form:input id="dob" class="form-control forminput" type="text" path="dob" />
				</td>
				
			</tr>
			<tr>
					<td>
						<label for="anniversary">Anniversary:</label>
					</td>
					<td>
						<form:input id="anniversary" class="form-control forminput" type="text"  path="anniversary" />
					</td>
					
			</tr>
			<tr>	
					<td>
					<span></span>
					<label for="eatOutIndex">How often do you eat out?:</label>
					</td>
					<td>
					<form:select path="eatOutIndex" class="form-control forminput" >
						<form:option value="1" />
						<form:option value="2" />
						<form:option value="3" selected ="true"/>
						<form:option value="4" />
						<form:option value="5" />
					</form:select>
					</td>
					
					
			</tr>
			<tr>
				<td>
					<label for="comment">Comment:</label>
				</td>
				<td>
					<form:textarea   path="comment" cols="20" rows="3" class="form-control forminput"/>
					<!-- <textarea path="comment" cols="20" rows="3" ></textarea> -->
				</td>
			</tr>
			<tr>
			<td>
			<input type="button" class="btn-primary" name="button" id="submit"
				value="submit" rows="5"/>
				</td>
			</tr>
			</table>
			
			
			<form:input type="hidden" path="merchant" value="${merchant}" />
			<form:input type="hidden" path="userId" id="userId" />
			<!-- 			<input type="submit" value="submit"/> -->
		</form:form>
	</div>
	<div style="min-width: 340px;text-align: center;display:none;" id='mainContainerReturn' class="alert alert-success">
	<strong>Thank You!</strong>
	</div>
	
</body>
</html>