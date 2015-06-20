<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="context" value="<%=request.getContextPath()%>" />


<script src="${context}/assets/js/orderForm.js"></script>
<script src="${context}/assets/js/orderProcess.js"></script>

<header>
	<div class="navbar navbar-default navbar-fixed-top navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a style="color: #eee;float:right;" href="#"
					onclick="loadHome()"
					class="navbar-brand"> 
					<div style='margin-top:-5px;'>${sessionScope.merchant.firstName} ${sessionScope.merchant.lastName}</div>
					<div style='color:#ddd;font-size:10px;text-align: right'><spring:message code="label.brand" /></div>
				</a>
				
				<button style="float: left;margin-left:5px;" type="button" class="navbar-toggle" id='menuClicker' onclick='showLeftMenu("leftMenuSlider");'>
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				
			</div>
			<div class="collpase navbar-collapse collapse" id="headerMenuCard">
				<c:forEach items="${menu}" var="entry" varStatus="mapLoop">
					<a href="#${entry.key.shortName}"
						class="btn btn-primary navbar-btn navshow ${item} ${mapLoop.first ? 'navSelected' : ''}"
						id="nav${entry.key.shortName}" type="submit"> 
								${entry.key.fullName}
					</a>
				</c:forEach>
			</div>
		</div>
	</div>
</header>
<div id='firstMenuItems' class='firstTimeMenu'>
<div>
<div style="background: rgb(148, 69, 69);color: white;font-size:16px;" class="firstMenuBtn navshow_left"> 
							Select Menu Category
</div>
<c:forEach items="${menu}" var="entry" varStatus="mapLoop">
					<div href="#${entry.key.shortName}"
						class="firstMenuBtn navshow_first"
						id="side_nav${entry.key.shortName}"> 
								${entry.key.fullName}
					</div>
</c:forEach>
</div>
</div>
<div class="modal_lay_notify"></div>
<div id='leftMenuSlider' class='leftMenu leftMenuHidden'>
<div>
<div style="background: rgb(148, 69, 69);color: white;" class="firstMenuBtn navshow_left"> 
								Select Menu Category
</div>
<c:forEach items="${menu}" var="entry" varStatus="mapLoop">
					<div href="#${entry.key.shortName}"
						class="sideMenuBtn navshow_left ${item} ${mapLoop.first ? 'navSelected' : ''}"
						id="side_nav${entry.key.shortName}"> 
								${entry.key.fullName}
					</div>
</c:forEach>
</div>
</div>
<div id='cardmenudisplay'>
<script>loadList();</script>
</div>

<c:if test="${sessionScope.mType!=3}">
<div class="bottomCart activeUser">
	<div id='orderCounts' class='order-count'>0</div>
	<div style="margin-top: 10px;">
		<span id='orderMessage' class="cartMsg"> <spring:message
				code="label.emptyCart" />
		</span> <span id='reviewOrder' class="reviewOrder"> <spring:message
				code="label.reviewOrder" />
		</span>
	</div>
</div>

 
<div id="bottomCartReview" class="bottomCartReview activeUser">
<span attr-target='bottomCartReview' class="hideReview">X</span>
<div class="container" id="order">
	<section>
		<div style="color: white;">
			<h3>
				<spring:message code="label.orderSummary" />
			</h3>
		</div>

		<table style='background: white;text-align: left' data-role="table"
			data-mode="columntoggle"
			class="ui-responsive table table-condensed table-hover table-striped table-bordered"
			id="bill_table" border='0' width='50%' align='center'
			style="border-collapse: collapse" cellspacing='3' cellpadding='5'>
			<thead>
				<tr class="info"
					style="font-variant: font-style:normal; color: black; font-size: 15px;">
					<th data-priority="6">Name</th>
					<th data-priority="1">Price</th>
					<th data-priority="2">Qty</th>
					<th data-priority="3" id="qwe">Amount</th>
					<th data-priority="4">Update</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				</tr>
			</tbody>
		</table>

		
		<div style="text-align: right">
		<span style="color: white;padding-right:10px;font-size:14px;" class="calsum">
			<span id="totalSumOfAmount"></span>
		</span>
		<input id="qaz" class="btn btn-round btn-primary" type="button" value='Submit
			Order'/>
			</div>
	</section>
</div>
</div>
</c:if>
<input type='hidden' id='activeSection'/>