<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>ETS 관리자</title>
	<link rel="shortcut icon" type="image/x-icon" href="/images/common/favicon.png">
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">	
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="/css/font-awesome.min.css">
	<link rel="stylesheet" href="/css/style.css">
	<link rel="stylesheet" href="/css/custom.min.css">
	<script type="text/javascript" src="/webjars/jquery/3.1.1/dist/jquery.min.js"></script>
	<script type="text/javascript" src="/webjars/jquery-ui/1.12.1/jquery-ui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/webjars/jquery-ui/1.12.1/themes/start/jquery-ui.min.css" media="screen, projection" />
	<script src="/js/lib/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<script src="/js/lib/jquery.selectbox-0.2.js" type="text/javascript"></script>
	<script type="text/javascript" src="/webjars/jquery-i18n-properties/1.2.2/jquery.i18n.properties.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/storage.js"></script>
	<script type="text/javascript" src="/js/slick.min.js"></script>
	<script type="text/javascript" src="/js/validation/common.js"></script>	
	<script type="text/javascript" src="/js/validation/jquery.alerts-1.1/jquery.alerts.js"></script>
	<link rel="stylesheet" href="/js/validation/jquery.alerts-1.1/jquery.alerts.css"/>
	<script type="text/javascript" src="/js/lib/jquery.inputmask.js"></script>
	<script type="text/javascript" src="/js/lib/jquery.inputmask.date.extensions.js"></script>
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<style type="text/css">
	<!--
		#login_area {margin-top:100px; background-color:#444;}
		#login_area .login {width:600px; padding:30px 0; margin:0 auto; color:#fff;}
		#login_area .login .login_title {padding:35px 0; border-bottom:1px solid #fff; font-size:35px; font-weight:900; color:#fff; text-align:center;}
		#login_area .login .login_text { padding:30px 0 20px;}
		#login_area .login .login_text:after {display:block;height:0;line-height:0;clear:both;content:"";}
		#login_area .login .login_text > div {float:left;}
		#login_area .login .login_text .info_enter {width:450px;}
		#login_area .login .login_text .info_enter p {margin-bottom:20px;}
		#login_area .login .login_text .info_enter p span {display:inline-block; width:100px; margin-right:15px; font-size:17px; font-weight:700; text-align:center;}
		#login_area .login .login_text .info_enter p input {width:270px; padding:7px; border:1px solid #eee;}
		#login_area .login .login_text .button {width:150px;}
		#login_area .login .login_text .button a {display:block; ; height:82px; line-height:82px; background-color:#ffc000; font-size:19px; font-weight:700; color:#fff; text-align:center; text-decoration:none;}
		#login_area .login .login_text .button a:hover,
		#login_area .login .login_text .button a:focus {}
		.hw_logo {text-align:center;margin-top:100px;}
		.hw_logo img {display:inline-block;width:386px;}
	//-->
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			var params = locationParameter();
			//console.log("params : " + JSON.stringify(params));
			
			if(typeof(params.error) != "undefined" && params.error != ""){
				setSessionStorage("error", params.error);
			}
			
			if(getSessionStorage("error") == "logout"){
				jsAlert.alert("알림", "로그인정보가 없습니다.", null, fn_resetStorage);
			}else if(getSessionStorage("error") == "duplogin"){
				jsAlert.alert("알림", "다른기기에서 로그인되었습니다.", null, fn_resetStorage);
			}
		});
		
		function fn_resetStorage(){
			removeSessionStorage("error");
			
			//브라우저 주소창의 URL값 변경 
			if(typeof (history.pushState) != "undefined"){ 	//브라우저가 지원하는 경우
		        history.pushState(null, "", "/login");
		    }else{											//브라우저가 지원하지 않는 경우 페이지 이동처리
		    	location.href = "/login"; 
		    }
		}
		
		function fn_login() {
			if(checkValidation("loginInfo")){
		    	$.ajax({
		      		url : '/login/duplication',
		      		data: $('#login input').serialize(),
		      		type: 'POST',
		      		dataType : 'json',
		      		beforeSend: function(xhr) {
		        		xhr.setRequestHeader("Accept", "application/json");
		        		appendAjaxLoading();
		      		}
		    	}).done(function(responseData) {
		    		removeAjaxLoading();
//		    		console.log("responseData : " + JSON.stringify(responseData));
		      		var error = responseData.error;
		      		if (error === true) {
		      			$('#sesId').val(responseData.data.sessionId);
		        		if (!confirm("이미 로그인 사용자가 있습니다. 중복로그인 하시겠습니까?")) {	        			
		          			return;
		        		}
		      		}else if(responseData.code == "401"){
		      			jsAlert.alert("알림", "로그인 정보가 일치하지 않습니다.");
		      			return;
		      		}
		
		      		if (responseData.code == "200") {
		      			appendAjaxLoading();
		      			$('#login').submit();
		      		}
		    	});
			}
	  	}
	</script>
</head>
<body style="background:#fffefd;">
	<div class="container">
		<form class="form-signin" role="form" id="login" method="post" action="/signin">
			<input type="hidden" name="removeSessionId" id="sesId" value=""/>
        <h2 class="form-signin-heading">
          <div class="text-center">
            <img src="/images/logo.png" alt="지니코드28" />
          </div>
        </h2>
        <label for="user_id" class="sr-only">아이디</label>
        <input type="text" name="${usernameParameter}" id="user_id" alt="아이디" class="form-control" placeholder="아이디" required autofocus />
        <label for="password" class="sr-only">비밀번호</label>
        <input type="password" name="${passwordParameter}" id="password" alt="Password" class="form-control" placeholder="비밀번호" onkeydown="if(event.keyCode==13) fn_login();" required />
        <div class="checkbox">
          <label style="padding-left:0;">
            <input type="checkbox" value="아이디 저장" style="margin:0 0 0 5px !important;position:relative;"> 아이디 저장
          </label>
        </div>
        <button type="button" class="btn btn-lg btn-info btn-block" type="submit" onClick="fn_login()">로그인</button>
      </form>

      <!-- 오류메세지 팝업 -->
      <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="popError" class="modal fade">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
              <h4 class="modal-title">사용자 알림</h4>
            </div>
            <div class="modal-body">
              <ul class="list-group">
                <li class="list-group-item">아이디 또는 비밀번호를 확인 바랍니다.</li>
              </ul>
              <div class="text-center">
                <button type="button" aria-hidden="true" data-dismiss="modal" class="btn btn-primary">확인</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div> <!-- /container -->
    
    <!-- Custom Theme Scripts -->
    <script src="js/custom.min.js"></script>

    <script>
    $(document).ready(function(){
        // 오류메세지 팝업 노출
        //$("#popError").modal();
    });
    </script>
    
	<!-- STR wrap -->
	<div id="login_area" style="display:none;">
	   <div class="login">
	      <p class="login_title">한화 다이렉트 캠페인 관리자 시스템</p>
	      <div class="login_text">
	         <div class="info_enter">
	         	<form class="form-signin" role="form" id="login" method="post" action="/signin">
	               <input type="hidden" name="removeSessionId" id="sesId" value=""/>
	               <p class="">
	                  <span>ID</span>
	                  <input type="text" name="${usernameParameter}" id="user_id" alt="아이디" class="" value="" placeholder="아이디" required/>
	               </p>
	               <p class="">
	                  <span>Password</span>
	                  <input type="password" name="${passwordParameter}" id="password" alt="Password" class="" value="" placeholder="비밀번호" onkeydown="if(event.keyCode==13) fn_login();" required/>
	               </p>
	            </form>               
	         </div>
	         <div class="button">
	            <a href="javascript:fn_login()">로그인</a>
	         </div>
	      </div>
	   </div>
	</div>
	<!-- END wrap -->
	<!-- Custom Theme Scripts -->
    <script src="js/custom.min.js"></script>

    <script>
      $(document).ready(function(){
          // 오류메세지 팝업 노출
          //$("#popError").modal();
      });
      </script>
</body>
</html>