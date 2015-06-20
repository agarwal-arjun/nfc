<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="context" value="<%=request.getContextPath()%>" />
<style>
.carousel-inner>.item>img, .carousel-inner>.item>a>img {
	width: 80%;
	margin: auto;
	height: 550px;
}

@media screen and (max-width: 720px) {
	.carousel-inner>.item>img, .carousel-inner>.item>a>img {
		width: 80%;
		margin: auto;
		height: 400px;
	}
}

@media screen and (max-width: 480px) {
	.carousel-inner>.item>img, .carousel-inner>.item>a>img {
		width: 100%;
		margin: auto;
		height: 400px;
	}
}

@media screen and (max-width: 320px) {
	.carousel-inner>.item>img, .carousel-inner>.item>a>img {
		width: 100%;
		margin: auto;
		height: 400px;
	}
}
</style>
<div>
	<br>
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<c:forEach items="${menu}" var="entry" varStatus="mapLoop">
				<c:forEach items="${entry.value}" var="item" varStatus="loop">
					<li data-target="#myCarousel" data-slide-to="${loop.index}"
						<c:if test="${loop.index==0}">class="active"</c:if>></li>
				</c:forEach>
			</c:forEach>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<c:forEach items="${menu}" var="entry" varStatus="mapLoop">
				<c:forEach items="${entry.value}" var="item" varStatus="loop">
					<div class="item<c:if test="${loop.index==0}"> active</c:if>">
						<img src="${item.URL}" alt="Deal of day" width="460" height="345">
						<div class="carousel-caption">
							<h3>${item.name}</h3>
							<p>${item.description}</p>
						</div>
					</div>
				</c:forEach>
			</c:forEach>
		</div>

		<!-- Left and right controls -->
		<a style='width: 10%' class="left carousel-control" href="#myCarousel"
			role="button" data-slide="prev"> <span
			class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a style='width: 10%' class="right carousel-control"
			href="#myCarousel" role="button" data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<br>
	<div style='text-align: center;'>
		<input style='width: 50%; display: inline-block' type="button"
			class="btn btn-success btn-block animateBtn"
			onclick='j_loader_GET("${context}/restaurant/card", "html", "mainContainer");'
			value="Check out all items" />
	</div>
</div>
</div>
