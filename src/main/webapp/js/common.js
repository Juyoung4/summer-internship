var path = window.location.pathname;
	path = path.replace(/\/$/, "");
	path = decodeURIComponent(path);
var sct = $(window).scrollTop();

(function($){
	$.fn.extend({
	    tabMethod: function(){
			$(".tab li").on("click focus",function(e){
				e.preventDefault();

				var tg = $(this).find("a").attr("href");

				$(this).siblings("li").removeClass("active");
				$(this).addClass("active");

				$(this).parent().siblings(".tab_content").hide();

				if(tg == "#all"){
					$(this).parent().siblings(".tab_content").show();
				}else{
					$(""+tg+"").show();
				}
			});
		}
	});
})(jQuery);

$.namespace = function() {
    var a = arguments, o = null, i, j, d;
    for (i = 0; i < a.length; i = i + 1) {
        d = a[i].split(".");
        o = window;
        for (j = 0; j < d.length; j = j + 1) {
            o[d[j]] = o[d[j]] || {};
            o = o[d[j]];
        }
    }
    return o;
};

$.namespace("App");
App = {
	init : function(){
		App.chkBrowser();
	},
	chkBrowser : function(){
		// 브라우저 및 버전을 구하기 위한 변수들.
	    'use strict';
	    var agent = navigator.userAgent.toLowerCase(),
	        name = navigator.appName,
	        browser,
	        os = '';
	    
	    // MS 계열 브라우저를 구분하기 위함.
	    if(name === 'Microsoft Internet Explorer' || agent.indexOf('trident') > -1 || agent.indexOf('edge/') > -1) {
	        browser = 'ie';
	    } else if(agent.indexOf('safari') > -1) { // Chrome or Safari
	        if(agent.indexOf('opr') > -1) { // Opera
	            browser = 'opera';
	        } else if(agent.indexOf('chrome') > -1) { // Chrome
	            browser = 'chrome';
	        } else { // Safari
	            browser = 'safari';
	        }
	    } else if(agent.indexOf('firefox') > -1) { // Firefox
	        browser = 'firefox';
	    }

	    // Mac OS
	    if (navigator.userAgent.indexOf('Mac OS X') > -1) {
	    	os = 'mac'
	    }

	    // IE: ie7~ie11, Edge: edge, Chrome: chrome, Firefox: firefox, Safari: safari, Opera: opera
	    document.getElementsByTagName('html')[0].className = browser;
	    document.getElementsByTagName('body')[0].className = os;

		if( /Android/i.test(navigator.userAgent)) {
		    // 안드로이드
		    $("body").addClass("android");
		} else if (/iPhone|iPad|iPod/i.test(navigator.userAgent)) {
		    // iOS 아이폰, 아이패드, 아이팟
		    $("body").addClass("ios");
		}
	}
}

$.namespace("App.main");
App.main = {
	init : function(){
		w_h = window.innerWidth;

		App.main.gnb();
		App.main.setSwipe();
		App.main.bindEvent();
		App.main.scrollEvent();
		App.main.resizeEvent();
		App.main.popup();
		App.main.motionEvent();

		$("#wrap").addClass("loaded");
		$(".sub_visual h2,.sub_visual p,.sub_visual span").addClass("active");

		if($(".sub_visual").length){
			$("#mHeader").addClass("sub");
			$("#container").addClass("sub_page");
			var img = $("#mHeader img");
			img.attr("src", img.attr("src").replace("_off", "_on"));
		}else{
			$("#mHeader").addClass("main");
		}

		sct = $(window).scrollTop();
			
		$(".opacityUp, [class^='page_sub_tit'], [class^='page_sub_tit'] + p,[class^='page_sub_tit'] + p + p,.title_border").each(function(){
			var tgTop = $(this).offset().top  - $(window).height();
			if(sct > tgTop){
				$(this).addClass("active");
			}
		});

		if(sct > 0){
			$("#header").addClass("fixed");
			$("#mHeader").addClass("fixed");
			if($("#mHeader").hasClass("main")){
				var img = $("#mHeader.main img");
				img.attr("src", img.attr("src").replace("_off", "_on"));
			}
		}else{
			$("#header").removeClass("fixed");
			$("#mHeader").removeClass("fixed");
			if($("#mHeader").hasClass("main")){
				var img = $("#mHeader.main img");
				img.attr("src", img.attr("src").replace("_on", "_off"));
			}
		}

		if(w_h < 992){
			App.main.fontResize();
		}

		$(".android .accordian_wrap > div:eq(0),.ios .accordian_wrap > div:eq(0)").addClass("active");

	},
	setSwipe : function(){
		//메인 슬라이더 갯수
		var mainSlider_len = $('#visual .slider').children('div').length;

		//if (mainSlider_len > 1) {
			$('#visual .slider').on('init', function(){
				$("#visual .slick-slide").eq(0).addClass("active-item");
			}).on("beforeChange", function(event, slick, currentSlide, nextSlide){
				$("#visual .slick-slide").removeClass("active-item");
				$(this).find(".slick-slide").eq(nextSlide).addClass("active-item");
	        }).slick({
                fade:true,
				dots:true,
				infinite: true,
				slidesToShow: 1,
                slidesToScroll: 1,
				arrows:true,
				speed:1000,
				autoplay: true,
				autoplaySpeed:2500,
  				pauseOnHover:false,
				responsive: [
					{
					  breakpoint: 768,
					  settings: {
					  	dots:false,
					  	arrows:false
					  }
					}
				]
            });
		//}

		// 메인 뉴스리스트
        $(".product_slider .slider").slick({
        	slidesToShow:3,
        	slidesToScroll:1,
			infinite: true,
			arrows:true,
			draggable: false,
			responsive: [
				{
				  breakpoint: 768,
				  settings: {
				  	slidesToShow:3,
        			slidesToScroll:1,
				    arrows:false,
				    centerMode: true,
  					variableWidth: true
				  }
				}
			]
        });
	},
	gnb : function(){
		$("#header .depth1 > li").on("mouseenter focusin",function(){
			$("#wrap").addClass("menu_on");
		});

		$("#header .depth1 > li").on("mouseleave blur",function(){			
			$("#wrap").removeClass("menu_on");
		});

		$("#mNav > ul.mGnb > li").click(function(e){
			e.preventDefault();

			$(this).toggleClass("active");
			$(this).find(".m_depth2_box").stop().slideToggle();
		});
		$("#mNav .m_depth2_box a").click(function(e){
			e.stopPropagation();
		});
		$(".btn_menu").click(function(e){
			e.preventDefault();

			$("#wrap").append("<div class='bg'></div>");
			$(".bg").fadeIn();
			$("#mNav").stop().animate({
				right:0
			});
		});
		$("#mNav .btn_close").click(function(e){
			e.preventDefault();

			$("#mNav").stop().animate({
				right:"-280px"
			});
		});

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

		$('.sub_tab a').each(function() {
			var $li = $(".sub_tab li.active");
			$(window).on('load', function() {
				var left = $li.position().left;
				$li.closest('.stage').scrollLeft(left);				
			});
			return false;
		});
	},
	bindEvent : function(){
		$(".tab li").length && $(".tab li").tabMethod();

		if(w_h > 991){
			$(".accordian_wrap > div").click(function(){
				$(".accordian_wrap > div").removeClass("active");
				$(this).addClass("active");

				var $this = $(this);
				var tl = new TimelineLite({paused:true});
				
				TweenMax.set($(".accordian_wrap > div"), {css:{width:"16.667%"}});
				TweenMax.set($this, {css:{width:"50%"}});
			});
		}else{
			$(".gnc_main_sec1 .mobile_btn a").click(function(e){
				e.preventDefault();
				var tg = $(this).attr("href");

				$(".gnc_main_sec1 .mobile_btn a").removeClass("on");
				$(this).addClass("on");
				$(".accordian_wrap > div").removeClass("active").hide();
				$(tg).addClass("active").show();
			});
		}

	},
	scrollEvent : function(){
		$(window).scroll(function(){
			sct = $(window).scrollTop();
			
			$(".opacityUp, [class^='page_sub_tit'], [class^='page_sub_tit'] + p ,[class^='page_sub_tit'] + p + p, .title_border").each(function(){
				var tgTop = $(this).offset().top  - $(window).height();
				if(sct > tgTop){
					$(this).addClass("active");
				}
			});

			if(sct > 0){
				$("#header").addClass("fixed");
				$("#mHeader").addClass("fixed");
				if($("#mHeader").hasClass("main")){
					var img = $("#mHeader.main img");
					img.attr("src", img.attr("src").replace("_off", "_on"));
				}
			}else{
				$("#header").removeClass("fixed");
				$("#mHeader").removeClass("fixed");
				if($("#mHeader").hasClass("main")){
					var img = $("#mHeader.main img");
					img.attr("src", img.attr("src").replace("_on", "_off"));
				}
			}

		});
	},
	resizeEvent : function(){

		$(window).resize(function(){
			w_h = window.innerWidth;

			if(w_h < 768){
				App.main.fontResize();
			}

			if($(".sub_tab").length){
				var tgW = 0;
				$(".sub_tab li").each(function(){

					tgW += $(this).outerWidth(true);
					return tgW;
				});

				if(tgW > $(".sub_tab .content_inner").outerWidth(true)){
					$('.sub_tab').addClass("scroll");
					var gw=1;
					var cu=0;
					$('.sub_tab ul li').each(function(){
						var w=$(this).width();
						gw=gw+w;
						$('.sub_tab ul').css({'width':gw + 10});
					});
				}else{
					$('.sub_tab').removeClass("scroll");
				}
			}

			var accrodianActive = $(".accordian_wrap > div.active").index();

			if(w_h > 991){
				$(".accordian_wrap > div").css({
					width:"16.667%",
					display:"block"
				});
				$(".accordian_wrap > div.active").css({
					width:"50%"
				});
			}else{
				$(".accordian_wrap > div").css({
					width:"100%",
					display:"none"
				});
				$(".accordian_wrap > div.active").css({
					width:"100%",
					display:"block"
				});
				$(".gnc_main_sec1 .mobile_btn a").removeClass("on");
				$(".gnc_main_sec1 .mobile_btn a").eq(accrodianActive).addClass("on");
			}

			if(w_h > 991){
				$(".accordian_wrap > div").click(function(){
					$(".accordian_wrap > div").removeClass("active");
					$(this).addClass("active");

					var $this = $(this);
					var tl = new TimelineLite({paused:true});
					
					TweenMax.set($(".accordian_wrap > div"), {css:{width:"16.667%"}});
					TweenMax.set($this, {css:{width:"50%"}});
				});
			}else{
				$(".gnc_main_sec1 .mobile_btn a").click(function(e){
					e.preventDefault();
					var tg = $(this).attr("href");

					$(".gnc_main_sec1 .mobile_btn a").removeClass("on");
					$(this).addClass("on");
					$(".accordian_wrap > div").removeClass("active").hide();
					$(tg).addClass("active").show();
				});
			}
		});
	},
	popup : function(){
		//메인 공지사항 팝업
		$(".main_popup").each(function(){
			var el = $(this);
			var win_height = $(window).height();
			var pop_height = $(this).outerHeight(true);
			var pop_width = $(this).outerWidth(true);
			var top_value = $(window).scrollTop() + (win_height - pop_height) /2;
			var left_value = pop_width / 2;
			el.css({
				top:top_value,
				marginLeft:-left_value
			});
		});

		//팝업
		$("a[rel='modal:open']").click(function(e){
			e.preventDefault();

			var el = $(this);
			var elPop = el.attr("href");
			$('.popup').hide();
			$(""+elPop+"").fadeIn();
			$("#wrap").append("<div class='bg'></div>");
			$(".bg").show();
			el.attr('data-focus','on');

			var target = $(""+elPop+"");
			var win_height = $(window).height();
			var pop_height = target.outerHeight(true);
			var top_value = $(window).scrollTop() + (win_height - pop_height) /2;
			target.attr("tabindex","0");
			target.show().css("top",top_value);
			target.focus();
		});

		/*팝업닫기*/
		$(document).on("click touchend",'.bg, .btn_close',function(event){
			event.preventDefault();
			$('.popup').hide();
			$('.bg').fadeOut().remove();
			$("a[data-focus~=on]").focus(); // 표시해둔 곳으로 초점 이동
			$("a[data-focus~=on]").removeAttr("data-focus");

			$("#mNav").stop().animate({
				right:"-280px"
			});
			$("#mNav > ul > li").removeClass("active");
			$("#mNav > ul > li > .m_depth2_box").stop().slideUp();
		});

		/*키보드 esc 팝업닫기*/
		$(document).keyup(function(e) {
			if (e.keyCode == 27) { // escape key maps to keycode `27`
				blurPopup();
			}
		});
		$(document).on('keydown', '[data-focus-prev], [data-focus-next]', function(e){
			var next = $(e.target).attr('data-focus-next'),
				prev = $(e.target).attr('data-focus-prev'),
				target = next || prev || false;
			
			if(!target || e.keyCode != 9) {
			  return;
			}
			
			if( (!e.shiftKey && !!next) || (e.shiftKey && !!prev) ) {
			  setTimeout(function(){
				$('[data-focus="' + target + '"]').focus();
			  }, 1);
			}
			
		});
	},
	motionEvent:function(){

		$('.motion_card').each(function() {

			var $this = $(this);
			var off_set = $this.attr('data-offset');
			var tl = new TimelineLite({paused:true});

			if(off_set == undefined){
			    off_set = '100%';
			}

			TweenMax.set($this, {css:{transformPerspective:400, transformStyle:"preserve-3d"}});
			tl.from($this, 1.6, {y:'40%', z: 40, rotationX:4,force3D:true, ease:Power3.easeOut});

			tl.from($this, 0.5, {autoAlpha:0, ease:Power3.easeOut},"-=1.6");

			$this.waypoint(function() {
				tl.play();
				this.destroy();
			}, {
				offset: off_set
			});

		});
	},
	fontResize:function(){
		var resolution=640, font=20;
		var width = $(window).width();
		var newFont = font * (width/resolution);
		$('html').css('font-size', newFont);
	}
}

$(function(){ 
	App.init();
	App.main.init();
});

$(window).on('load',function(){

	if($(".sub_tab").length){
		var tgW = 0;
		$(".sub_tab li").each(function(){

			tgW += $(this).outerWidth(true);
			return tgW;
		});

		if(tgW > $(".sub_tab .content_inner").outerWidth(true)){
			$('.sub_tab').addClass("scroll");
			var gw=1;
			var cu=0;
			$('.sub_tab ul li').each(function(){
				var w=$(this).width();
				gw=gw+w;
				$('.sub_tab ul').css({'width':gw + 10});
			});
		}
	}
});

//HTML로 넘어온 파라미터 받기
function locationParameter(){
	var parameter = location.search;                                     // 주소창의 값을 전부 가져옴
	var paramIndex = parameter.indexOf("?");                             // ?(물음표) 뒤부터 파라미터이므로, 우선 물음표의 위치를 찾음
	parameter = parameter.substring(paramIndex+1);						 // 물음표 + 1 ( 여기부터 파라미터이므로 ) 자리를 잘라서 담음

	var params = new Object();

	for(var i = 0 ; location.search.split("&")[i] ; i++ ) {              // 반복문을 돌면서(&가 있을때까지-즉,파라미터갯수만큼)
		var param = parameter.split("&")[i];

		var key = "";
		var value = "";

		if(param.indexOf("=") > -1){
			var paramArg = param.split("=");
			key = paramArg[0];
			value = paramArg[1];
		}else{
			key = param;
		}
		//console.log("param : " + param + ", key : " + key + ", value : " + value);
		params[key] = value;
	}

	//console.log("params : " + JSON.stringify(params));
	return params;
}
function blurPopup(){
	$('.popup').hide();
	$('.bg').fadeOut().remove();
	$("a[data-focus~=on]").focus(); // 표시해둔 곳으로 초점 이동
	$("a[data-focus~=on]").removeAttr("data-focus");
}