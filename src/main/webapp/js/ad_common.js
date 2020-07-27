$(document).ready(function(){

	var rightHi = $(".cont_tab").height(); /* 탭메뉴의 높이값 */
	var bottomWi = $(".cont_lnb").width(); /* lnb 메뉴의 넓이값*/
	var winWi = $(window).height();
	var contrHi = $(".cont_area").height();
	var sss =$(".cont_area").css("padding");

	$(".cont_lnb").css("height",contrHi); //lnb의 높이값
	//$(".cont_right").css("margin-left",bottomWi+1);
	$(".none_box").css({
		height : rightHi,
		width : bottomWi
		/* 아무것도 없는 div의 높이는 오른쪽의 탭박스 높이, 넓이는 아래있는 lnb의 넓이*/
	});
	$(".main_logo > a").click(function(){
		$(this).removeAttr("href").attr("href", "/");
	});
	$(".type_select01").selectbox();
});
