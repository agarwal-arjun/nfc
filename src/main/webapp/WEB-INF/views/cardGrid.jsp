<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="context" value="<%=request.getContextPath()%>" />



<div class="viewTypeWrap">
<span style="border:1px solid #ddd;padding:5px 0px 5px 0px">
<span class='gridViewBtn'>GRID</span> 
<span class='gridViewBtnC' onclick='loadList();'>LIST</span>
</span>
</div>
<style>.activeUser input[type=number]{display:inline-block;width:80px;}.animateBtn{float:right;display:inline-block;width:80px!important}</style>
<c:forEach items="${menu}" var="entry" varStatus="mapLoop">
	<div class="container food_categories" attr-index="${mapLoop.index}" id="${entry.key.shortName}"  <c:if test="${mapLoop.index>0 }">style="display: none"</c:if>>
		
		<section>
			<div class="page-header"></div>
			<section id="main-content">
				<div><h2>${entry.key.fullName} Menu</h2></div>
				<c:forEach items="${entry.value}" var="innerEntry" varStatus="loop">
				<section class="wrapper site-min-height">
					<div class="row mt">
						<div class="col-lg-12">
				<c:if test="${innerEntry.key.id!=0 }">
							<h4>${innerEntry.key.fullName}</h4>
						<hr/>
				</c:if>
				<c:forEach items="${innerEntry.value}" var="item" varStatus="loop">
								<div class="col-lg-4 col-md-4 col-sm-4 mb">
									<div class="content-panel pn">
										<div style="background:url(${item.URL});" class="blog-bg1">
											<div class="blog-title">
												${item.name} <br> Rs.${item.amount}
											</div>
										</div>
										<div class="blog-text" id="form-group">
											<p>${item.description}</p>
											<div class='activeUser'>
											<label for="quantity"><b><spring:message
														code="label.quantity" /></b></label> <input type="number"
												class="form-control" id="quantity_${item.id}" value="1" min="1">
											<input type="button" id="b${item.id}"
												class="btn btn-success btn-block animateBtn"
												pname="${item.name}" pprice="${item.amount}"
												pmid="${item.id}" onclick="addTolist(this);"
												value="<spring:message code="label.addButton" />"
												/>
											</div>
										</div>
									</div>
								</div>
								<%--${item} ${!loop.last ? ', ' : ''}--%>
							</c:forEach>
							</div>
					</div>
				</section>
			</c:forEach>
						
			</section>
		</section>
	</div>
</c:forEach>

<script>
manageView();
</script>