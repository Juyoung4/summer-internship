<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- Meta, title, CSS, favicons, etc. -->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>ETS 관리자</title>
	<link rel="shortcut icon" type="image/x-icon" href="/images/common/favicon.png">
	<!-- Bootstrap -->
	<link href="/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- jquery-ui -->
	<link href="/css/jquery-ui.min.css" rel="stylesheet">
	<!-- Font Awesome -->
	<link href="/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<!-- NProgress -->
	<link href="/vendors/nprogress/nprogress.css" rel="stylesheet">
	<!-- iCheck -->
	<link href="/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
	
	<!-- Custom Theme Style -->
	<link href="/build/css/custom.min.css" rel="stylesheet">
	<link href="/build/css/style.css" rel="stylesheet">
	
	<!-- jQuery -->
    <script src="/vendors/jquery/dist/jquery.min.js"></script>
    <script src="/js/jquery-ui.js"></script>
</head>
<script type="text/javascript">	
	var dtNow = new Date();
	
	$(function() {
		$(".datepicker").datepicker({
			buttonImageOnly: false,
			prevText: '이전달',
			nextText: '다음달',
			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			dayNamesMin:['일','월','화','수','목','금','토'],
			changeYear: true,
			minDate: '-1y',
			yearRange: 'c-1:c+3',
			changeMonth: true,
			showMonthAfterYear: false,
			dateFormat: 'yy-mm-dd',
			duration: '',
			isRTL: false,
			beforeShow : function(input, inst) {
		        if($(this).val() != ""){
		        	var selectYear = new Date($(this).val());
		        	var nowYear = dtNow.getFullYear();
		        	var num = parseInt(selectYear.getFullYear()) - nowYear;
		        	if(num > 100){
						inst.settings.yearRange = Number(nowYear) - 1 + ':' + Number(Number(nowYear) + num);
					} else if (num < -100){
						inst.settings.yearRange = Number(Number(Number(nowYear) + num) - 1) + ':' + Number(Number(nowYear) + 3);
					} else {
						inst.settings.yearRange = Number(nowYear) - 100 + ':' + Number(Number(nowYear) + 3);
					}
				}
		    }
		}).keyup(function(e) {
	    if(e.keyCode == 8 || e.keyCode == 46) {
	        $.datepicker._clearDate(this);
	    }});
		
		// 다국어 선택
		$(".lang").click(function(e){
			e.preventDefault();

			$(this).toggleClass("active");
			$(this).find("ul").toggle();
		});
		$(".lang").on("mouseleave blur",function(){
			$(this).removeClass("active");
			$(this).find("ul").hide();
		});
		$(".lang ul").click(function(e){
			e.stopPropagation();
		});
	});	
</script>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="/board/notice" class="site_title">
							<img src="/images/logo.png" alt="ETS" style="position:absolute;left:30px;top:7px;width:150px;" />
						</a>
					</div>
					<div class="clearfix"></div>

					<br />
					
					<!-- header content -->
					<#include "inc/header.ftl"/>
					<!-- /header content -->

          		</div>
        	</div>
        	
        	<div class="lang">
				<a href="#">
					<#if springMacroRequestContext.getLocale()?has_content && springMacroRequestContext.getLocale() == 'en'>
					EN
					</#if>
					<#if springMacroRequestContext.getLocale()?has_content && springMacroRequestContext.getLocale() == 'cn'>
					CN
					</#if>
					<#if springMacroRequestContext.getLocale()?has_content && springMacroRequestContext.getLocale() == 'ko'>
					KR
					</#if>
				</a>
				<ul>
					<li><a href="?lang=ko">KR</a></li>
					<li><a href="?lang=en">EN</a></li>
					<li><a href="?lang=cn">CN</a></li>
				</ul>
			</div>
			
			<!-- page content -->
			${body}
			<!-- /page content -->

			<!-- footer content -->
			<#include "inc/footer.ftl"/>
			<!-- /footer content -->
			
		</div>
	</div>
	
    <!-- Bootstrap -->
    <script src="/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="/vendors/nprogress/nprogress.js"></script>
    <!-- iCheck -->
    <script src="/vendors/iCheck/icheck.min.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="/build/js/custom.min.js"></script>
    
    <script type="text/javascript" src="/js/validation/common.js"></script>
    <script type="text/javascript" src="/js/validation/jquery.alerts-1.1/jquery.alerts.js"></script>
	<link rel="stylesheet" href="/js/validation/jquery.alerts-1.1/jquery.alerts.css"/>
	<script type="text/javascript" src="/js/lib/jquery.inputmask.js"></script>
	<script type="text/javascript" src="/js/lib/jquery.inputmask.date.extensions.js"></script>
	
</body>
</html>