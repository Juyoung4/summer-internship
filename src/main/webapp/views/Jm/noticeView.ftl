<script src="/js/download.js"></script>
<script type="text/javascript">
	
	function fn_down(fn){
		var filename = fn.replace(/^.*[\\\/]/, '');
		var x = new XMLHttpRequest();
		x.open("GET", "/common/imageload?fullImageFileNm="+fn, true);
		x.responseType = 'blob';
		x.onload=function(e){download(x.response, filename, "image/gif" ); }
		x.send();
	}

	function fn_modi(){
		$("#noticeFrm").prop("action", "/Jm/noticeWrite");
		formSubmit("noticeFrm");
	}
	
	function fn_del(){
		jsAlert.confirm("확인", "해당 게시물을 삭제 하시겠습니까?", fn_deleteYn, null, "확인", "취소");
    }
	
	function fn_deleteYn(){
		
		ajaxFormExecute("noticeFrm", "/Jm/ajaxNoticeDelete", "post", false, false, ajaxReturnDelete);
    }
	
	function ajaxReturnDelete(data){
		if(data.sw){
    		jsAlert.alert("알림", "삭제되었습니다.", null, fn_list, "확인");
    	}else{
    		jsAlert.alert("알림", "삭제에 실패하였습니다.<br>관리자에게 문의바랍니다.");
    	}
	}
	
	function fn_list(){
		location.href="/Jm/notice";		
	}
	
</script>
	
<form class="form-inline" name="noticeFrm" id="noticeFrm" method="post" >
	<input type="hidden" id="noticeSeq" name="noticeSeq" value="${noticeInfo.noticeSeq!}" />
</form>

<div class="right_col" role="main">
	<div class="">
    	<div class="page-title">
  			<div class="title_left">
				<h3>공지사항</h3>
			</div>
		</div>
	
		<div class="clearfix"></div>
		
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel board_top">
					<div class="x_title">
						<div class="row">
							<div class="col-md-8 col-sm-8 col-xs-8">
								<h4>${noticeInfo.noticeTitle!}</h4>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-4 text-right">
								${noticeInfo.regDate!}
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="x_panel">
					<div class="x_content" style="display:none;">
						<div class="row">
							<div class="col-md-4 col-sm-4 col-xs-4 text-left">
								<h4>첨부파일</h4>
							</div>
							<div class="col-md-8 col-sm-8 col-xs-8 text-right">
								<ul>
									<li>
										<span class="inline-block mgr5">${noticeInfo.orgImgNm!}</span><button type="button" class="btn btn-primary" onClick="fn_down('${noticeInfo.imgNm?js_string}')">다운로드</button>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="x_content">
						${noticeInfo.noticeContent!}
					</div>
					<div class="text-right">
						<button type="button" class="btn btn-primary" onclick="fn_modi();">수정</button>
						<button type="button" class="btn btn-danger" onclick="fn_del();">삭제</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="clearfix"></div>
	</div>
</div>