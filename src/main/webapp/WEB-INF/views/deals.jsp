<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="<%=request.getContextPath()%>" />
<c:choose>
	<c:when test="${empty menu}">
	<script>
		var val = $('#mType').val();
		if (val == 2) {
			j_loader_GET("${context}/restaurant/menu", "html", "mainContainer");
		} else if (val == 3) {
			j_loader_GET("${context}/restaurant/card", "html", "mainContainer");
		}
	</script>
	</c:when>
	<c:otherwise>
		<style>
#dealoftheday .item img {
	width: 80%;
	margin: auto;
	height: 550px;
	display: block;
}

@media screen and (max-width: 720px) {
	#dealoftheday .item img {
		width: 80%;
		margin: auto;
		height: 400px;
	}
}

@media screen and (max-width: 480px) {
	#dealoftheday .item img {
		width: 100%;
		margin: auto;
		height: 400px;
	}
}

@media screen and (max-width: 320px) {
	#dealoftheday .item img {
		width: 100%;
		margin: auto;
		height: 400px;
	}
}
</style>
		<div class='overlay_img_dod blink_me'><img style='display:inline-block;width:50px;height:50px;border-radius:50%;' src="../images/dod.gif"></div>
		<div id="dealoftheday" class="owl-carousel owl-theme">
			<c:forEach items="${menu}" var="entry" varStatus="mapLoop">
				<c:forEach items="${entry.value}" var="item" varStatus="loop">

					<div class="item">
						<img src="${item.URL}">
						<div class='overlay_font_desc'>
						<c:if test="${not empty item.description}">
						${item.description}
						</c:if>
						<c:if test="${item.discount!=0}">
						<div style='font-size:28px;'>
						Discounted @ ${item.discount}% 
						</div>
						</c:if>
						</div>
					</div>

				</c:forEach>
			</c:forEach>

		</div>
		<div style='height:20px;'></div>
		<div style='text-align: center;'>
			<input style='width: 50%; display: inline-block' type="button"
				class="btn btn-success btn-block animateBtn"
				onclick='j_loader_GET("../restaurant/card", "html", "mainContainer");'
				value="Check out all items" />
		</div>
		<script>
			$(document).ready(function() {

				$("#dealoftheday").owlCarousel({

					navigation : false, // Show next and prev buttons
					slideSpeed : 500,
					paginationSpeed : 500,
					singleItem : true,
					autoPlay : 10000

				});

			});
		</script>
	</c:otherwise>
</c:choose>