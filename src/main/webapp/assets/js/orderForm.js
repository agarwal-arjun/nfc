$(document).ready(function() {

	$('.navshow').click(function() {
		var href=$(this).attr('href');
		//alert(href);
		$('.navshow').each(function() {
			$(this).removeClass('navSelected');
		});
		$('#activeSection').val(href);
		$(this).addClass('navSelected');
		$('.food_categories').slideUp(0);
		$('#firstMenuItems').addClass('hideMargin');
		$(href).slideDown(600);
	});
	
	
	$('.navshow_left').click(function() {
		var href=$(this).attr('href');
		//alert(href);
		$('.navshow_left').each(function() {
			$(this).removeClass('navSelected');
		});
		$('#activeSection').val(href);
		$(this).addClass('navSelected');
		$('.food_categories').slideUp(0);
		$(href).slideDown(600);
		$('#firstMenuItems').addClass('hideMargin');
		showLeftMenu('leftMenuSlider');
	});
	
	$('.navshow_first').click(function() {
		var href=$(this).attr('href');
		//alert(href);
		$('.navshow_left').each(function() {
			$(this).removeClass('navSelected');
		});
		$('#activeSection').val(href);
		$(this).addClass('navSelected');
		$('.food_categories').slideUp(0);
		$(href).slideDown(600);
		$('#firstMenuItems').addClass('hideMargin');
		$('#leftMenuSlider').addClass('leftMenuHidden');
		$('.modal_lay_notify').hide();
	});


	$(".animateBtn").click(function() {
		$("#"+($(this).attr('id'))).animate({
			left : '250px',
			opacity : '0.5',

		}, "slow");

		$("#"+($(this).attr('id'))).animate({
			left : '250px',
			opacity : '1',

		}, "slow");
	});
	
	$('.modal_lay_notify').click(function() {
		$(this).hide();
		$('#leftMenuSlider').addClass('leftMenuHidden');
	});

});

function showLeftMenu(x) {
	//$('#'+x).toggleClass('leftMenuHidden');
	//$('.modal_lay_notify').toggle();
	$('#firstMenuItems').removeClass('hideMargin');
}
function scrollTo(x) {
	$('html, body').animate({
		scrollTop : $("#"+x).offset().top
	}, 2000);
}