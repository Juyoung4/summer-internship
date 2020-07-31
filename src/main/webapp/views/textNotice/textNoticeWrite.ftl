<#assign crudText = "등록" />
<#assign returnUrl = "textNotice" />
<#if crud == "MOD">
	<#assign crudText = "수정" />
	<#assign returnUrl = "textNoticeView" />
</#if>
<script type="text/javascript">
	
	$(document).on("change","#uploadImg",function(){
		var fileValue = $(this).val().split("\\");
		var fileName = fileValue[fileValue.length-1];
		
		$('#imgNm').val(fileName);
	});
	
	function replaceAll(str, searchStr, replaceStr) {
		return str.split(searchStr).join(replaceStr);
	}
	
	function fn_save(){
		
		if(writeFrm.textnoticeTitle.value==''){
			jsAlert.alert("알림", "제목을 입력하세요.", $("#textnoticeTitle"));
			return false;
		}
		
		oEditors.getById["textnoticeContent"].exec("UPDATE_CONTENTS_FIELD", []);
		var text = $('#textnoticeContent').val();
		var texttrim = replaceAll(text, '&nbsp;', '');
		texttrim = replaceAll(texttrim, ' ', '');
		texttrim = replaceAll(texttrim, '<p></p>', '');
		if(texttrim==""){
			jsAlert.alert("알림", "내용을 입력하세요.", $("#textnoticeContent"));
			return ;
		}
		
// 		if($('#imgNm').val() == ""){
// 			jsAlert.alert("알림", "첨부할 파일을 등록하여 주세요.", $("#uploadImg"));
// 			return false;
// 		}
		
		jsAlert.confirm("확인", "${crudText!} 하시겠습니까?", fn_submit, null, "확인", "취소");
	}
	
	function fn_submit(){
		ajaxFileFormExecute("writeFrm", "/textNotice/ajaxNoticeSubmit", "post", false, false, ajaxReturnSubmit);
    }
	
	function ajaxReturnSubmit(data){
		if(data.sw){
    		jsAlert.alert("알림", "${crudText!}되었습니다.", null, fn_list, "확인");
    	}else{
    		jsAlert.alert("알림", "${crudText!}에 실패하였습니다.<br>관리자에게 문의바랍니다.");
    	}
	}
	
	function fn_list(){
		location.href="/textNotice/textNotice";		
	}
	
	function fileDelete(){
		var filehtml = "";
		$('#imgNm').val('');
		
		filehtml += '<input id="imgNm" name="imgNm" value="" class="form-control inline-block wd400" value="" readonly="readonly" />\n';
		filehtml += '<label for="uploadImg"><span href="#" class="btn btn-primary">찾아보기</span></label>\n';
		filehtml += '<input type="file" id="uploadImg" name="uploadImg" style="display:none;" accept=".jpg,.png"/>\n';
		filehtml += 'jpg,png 파일만 올려주세요. (000 * 000px)\n';
		
		$('#imgNmArea').html(filehtml);
	}
	
</script>

<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>공지사항 ${crudText!}</h3>
			</div>
		</div>

		<div class="clearfix"></div>

		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
						<form name="writeFrm" id="writeFrm" name="writeFrm" method="post" >
						<input type="hidden" id="textnoticeSeq" name="textnoticeSeq" value="${textnoticeSeq!}" />
						<input type="hidden" name="crud" id="crud" value="${crud!}" />
						
						<div class="table-responsive">
							<table class="table table-striped jambo_table bulk_action table_vertical">
								<tbody>
									<tr>
										<th class="column-title vertical-middle">제목*</th>
										<td><input type="text" id="textnoticeTitle" name="textnoticeTitle" value="${noticeInfo.textnoticeTitle!}" class="form-control inline-block" maxlength="100" /></td>
									</tr>
									<tr>
										<th class="column-title vertical-middle">내용*</th>
										<td>
											<textarea name="textnoticeContent" id="textnoticeContent" class="form-control inline-block"  rows="10" cols="100" style="width:100%; height:300px; display:none;">${noticeInfo.textnoticeContent!}</textarea>
										</td>
									</tr>
									<tr style="display:none;">
										<th class="column-title vertical-middle">파일첨부*</th>
										<td colspan="3">
											<div class="form-group text-left" id="imgNmArea">
												<input id="imgNm" name="imgNm" value="${noticeInfo.orgImgNm!}" class="form-control inline-block wd400" value="" readonly="readonly" />
												<#if noticeInfo.orgImgNm?has_content>
													<span href="#" class="btn btn-danger" onclick="fileDelete()">삭제</span>
												<#else>
												<label for="uploadImg"><span href="#" class="btn btn-primary">찾아보기</span></label>
												<input type="file" id="uploadImg" name="uploadImg" style="display:none;" accept=".jpg,.png"/>
												<br>jpg,png 파일만 올려주세요. (000 * 000px)
												</#if>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						</form>
						<div class="text-right">
							<a href="#" class="btn btn-primary" onclick="fn_save()">${crudText!}</a>
							<a href="#" class="btn btn-info" onclick="fn_list()">취소</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 스마트 에디터2 -->
<script type="text/javascript" src="/src/js/smarteditor2/js/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
	var oEditors = [];

	var sLang = "ko_KR"; // 언어 (ko_KR/ en_US/ ja_JP/ zh_CN/ zh_TW), default = ko_KR
	// 추가 글꼴 목록
	//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];

	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "textnoticeContent",
		sSkinURI: "/src/js/smarteditor2/SmartEditor2Skin.html", 
		htParams : {
			bUseToolbar : true,    // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,  // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true,   // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			fOnBeforeUnload : function(){
				//alert("완료!");
				},
				I18N_LOCALE : sLang
			}, //boolean
			fOnAppLoad : function(){
			//예제 코드
			//oEditors.getById["content"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
		},
		fCreator: "createSEditor2"
	});

	function pasteHTML(filepath) {

		 var sHTML = '<span style="color:#FF0000;"><img src="'+filepath+'"></span>';
		 oEditors.getById["textnoticeContent"].exec("PASTE_HTML", [sHTML]);
	}

	function showHTML() {
		var sHTML = oEditors.getById["textnoticeContent"].getIR();
		alert(sHTML);
	}
					
	function setDefaultFont() {
		var sDefaultFont = '궁서';
		var nFontSize = 24;
		oEditors.getById["textnoticeContent"].setDefaultFont(sDefaultFont, nFontSize);
	}

	function writeReset() {
		document.f.reset();
		oEditors.getById["textnoticeContent"].exec("SET_IR", [""]);
	}
	
</script>