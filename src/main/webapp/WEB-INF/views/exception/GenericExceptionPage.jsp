<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Cache-Control" content="no-cache">
			<meta http-equiv="Expires" content="-1">
				<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
				<%@ taglib prefix="form"
					uri="http://www.springframework.org/tags/form"%>
				<c:set var="contextPath" value="<%=request.getContextPath()%>" />

				<link rel="shortcut icon"
					href="${contextPath}/images/favicon.ico"
					type="image/x-icon" />
				<title>Nikata</title>

				<link href="${contextPath}/assets/css/ccpayment.css"
					rel="stylesheet" type="text/css" />
				<link href="${contextPath}/assets/css/base.css" rel="stylesheet"
					type="text/css" />

				<script>
function backButtonOverride()
{
  setTimeout("backButtonOverrideBody()", 1);
}

function backButtonOverrideBody()
{
  try {
    history.forward();
  } catch (e) {
    // OK to ignore
  }
  setTimeout("backButtonOverrideBody()", 500);
}
</script>
</head>
<body onload="backButtonOverride()">


	<div id="header-full">
		<div id="header">
			<div id="logo">
				<a href="http://nfc-pickweed.rhcloud.com/"><img
					src="${contextPath}/images/favicon.png" alt="Nikata"
					title="Nikata" style="height: 60px" /></a>
			</div>
		</div>
	</div>
	<div style="font-size: 14px" align="center">
		<b> </b>
		<table align="center" border="0" cellpadding="0" cellspacing="0"
			width="70%">
			<tbody>
				<tr>
					<td height="50">&nbsp;</td>
				</tr>


				<tr>
					<td align="center">&nbsp;</td>
				</tr>

				<tr>
					<td><b>Yikes! There's a technical glitch!</b></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Why?</td>
				</tr>
				<tr>
					<td><li>Session expired due to inactivity on page for a
							while.</li></td>
				</tr>
				<tr>
					<td><li>Double-click on any of the buttons</li></td>
				</tr>
				<tr>
					<td><li>Nikata encountered an obstacle</li></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><b>Don't worry, just:</b></td>
				</tr>
				<tr style="height: 10px">
					<td></td>
				</tr>
				<tr>
					<td><li>Clear your cookies and temporary internet files
							for your browser.</li></td>
				</tr>
				<tr>
					<td><li>Open a new browser and re-login.</li></td>
				</tr>

				<tr>
					<td><li>If you were in the middle of a transaction, check
							its status before reinitiating a new one. If the technical glitch
							persists, it's time to call on our specialists at
							support@Nikata.com</li></td>
				</tr>
			</tbody>
		</table>
		<b> </b>
	</div>
	<b>
		<div class="clear"></div>
		<div class="footer-query-top" style="margin-bottom: 0px"></div>
		<div id="footer" class="footer-query">
			
			<div class="fr footer-left">© 2014-2015 Nikata.com is powered by
				Nikata </div>
		</div>


		<meta http-equiv="Pragma" content="no-cache">
			<meta http-equiv="Cache-Control" content="no-cache">
				<meta http-equiv="Expires" content="-1">
	</b>
</body>
</html>