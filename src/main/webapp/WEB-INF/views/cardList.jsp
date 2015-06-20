<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="context" value="<%=request.getContextPath()%>" />




<div style="margin-top: 50px"></div>
<c:forEach items="${menu}" var="entry" varStatus="mapLoop">
	<div class="container food_categories" attr-index="${mapLoop.index}" id="${entry.key.shortName}" <c:if test="${mapLoop.index>0 }">style="display: none"</c:if>>
		<div class='categoryHeader'>
		<h4>${entry.key.fullName}</h4>
		<div class="viewTypeWrap">
		<span style=" padding: 5px 0px 5px 0px">
		<span class='gridViewBtnC'
		onclick='loadGrid()'>GRID</span>
		<span class='gridViewBtn'>LIST</span>
	</span>
	</div>
		</div>
		
				<c:forEach items="${entry.value}" var="innerEntry" varStatus="loop">
				<c:if test="${innerEntry.key.id!=0 }">
						<div style="padding:10px;font-weight:bold;color:rgb(148, 69, 69);background:whitesmoke;">
							${innerEntry.key.fullName}
						</div>
				</c:if>
				<table style="box-shadow:0px 0px 0px transparent;border:1px solid #ddd;margin:0;" class="table table-condensed">
				<tbody>
				<c:forEach items="${innerEntry.value}" var="item" varStatus="loop">
					<tr>
						<td>
						<div><b>${item.name}</b></div>
						<div style='font-size:10px;'>${item.description}</div>
						</td>
						<td style='text-align:right;min-width:100px;'>Rs. ${item.amount}</td>
						<td class='activeUser'><input style='width:100%' type="number" class="form-control"
							id="quantity_${item.id}" value="1" min="1"></td>
						<td class='activeUser'><input style='width:90%' type="button" id="b${item.id}"
							class="btn btn-success btn-block animateBtn" pname="${item.name}"
							pprice="${item.amount}" pmid="${item.id}"
							onclick="addTolist(this);"
							value="Add" /></td>
					</tr>
					</c:forEach>
					</tbody>
		</table>
					<%--${item} ${!loop.last ? ', ' : ''}--%>
				</c:forEach>
			
	</div>
</c:forEach>

<script>
manageView();
</script>