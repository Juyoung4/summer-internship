<#assign pagingTag = JspTaglibs["/WEB-INF/tld/paging.tld"] />
<#setting number_format = "###"/>
<#assign crudText = "등록" />
<#assign returnUrl = "textNotice" />

<script type="text/javascript">
	
	$(document).ready(function(){
		$("#checkAll").click(function(){
			if($("#checkAll").prop("checked")){
				$("input[name=chk]").prop("checked",true);
			}else{
				$("input[name=chk]").prop("checked",false);
			}
		});
	});
	
	function fn_delete(){
		if($('input:checkbox[name=chk]').is(':checked') == false){
			jsAlert.alert("알림", "삭제할 게시물을 선택해주세요");
			return false;
		}
		jsAlert.confirm("확인", "해당 게시물을 삭제 하시겠습니까?", fn_deleteYn, null, "확인", "취소");
    }
	
	function fn_deleteYn(){
		var seqs = [];
		$("input[name=chk]:checked").each(function() {
			seqs[seqs.length] = $(this).val();
		});
		
		$("#textnoticeSeq").val(seqs.join(","));
		
		ajaxFormExecute("noticeFrm", "/textNotice/ajaxNoticeDelete", "post", false, false, ajaxReturnDelete);
	}
	
	function ajaxReturnDelete(data){
		if(data.sw){
    		jsAlert.alert("알림", "삭제되었습니다.", null, fn_list, "확인");
    	}else{
    		jsAlert.alert("알림", "삭제에 실패하였습니다.<br>관리자에게 문의바랍니다.");
    	}
	}
	
	function fn_list(){
		$("#noticeFrm").prop("action", "/textNotice/textNotice");
		$('#nowPage').val('1');
 		formSubmit("noticeFrm");
	}
	
	function fn_view(textnoticeSeq){
		$("#noticeFrm").prop("action", "/textNotice/textNoticeView");
		$('#textnoticeSeq').val(textnoticeSeq);
 		formSubmit("noticeFrm");
	}
	
	function fn_write(){
		location.href="/textNotice/textNoticeWrite";	
	}
	
	function fn_search(){
		$("#noticeFrm").prop("action", "/textNotice/textNotice");
		$('#nowPage').val('1');
 		formSubmit("noticeFrm");
	}
	
	function fn_dateButton(period){
		$('#endDt').datepicker('setDate', 'today');
		$('#startDt').datepicker('setDate', period);
	}
	
	$(function(){
		$('#rowCount').change(function(){
			$("#selectFrm").prop("action", "/textNotice/textNotice");
			$('#nowPage').val('1');
 			formSubmit("selectFrm");
		});
	});
	
	<!--write----------------------------------------------->
	
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
		
		jsAlert.confirm("확인", "등록 하시겠습니까?", fn_submit, null, "확인", "취소");
	}
	
	function fn_submit(){
		ajaxFileFormExecute("writeFrm", "/textNotice/ajaxNoticeSubmit", "post", false, false, ajaxReturnSubmit);
    }
	
	function ajaxReturnSubmit(data){
		if(data.sw){
    		jsAlert.alert("알림", "등록 되었습니다.", null, fn_list_write, "확인");
    	}else{
    		jsAlert.alert("알림", "등록에 실패하였습니다.<br>관리자에게 문의바랍니다.");
    	}
	}
	
	function fn_list_write(){
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
	
	function fn_preview(){
		$("#writeFrm").prop("action", "/textNotice/textNoticePreview");
 		formSubmit("writeFrm");
	}
	
</script>

<div class="right_col" role="main">
	<div class="">
		
		<!--search section-->
		
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
						<div class="table-responsive">
							<form class="form-inline" name="noticeFrm" id="noticeFrm" method="post">
							<input type="hidden" id="textnoticeSeq" name="textnoticeSeq" value="" />
								<table class="table table-striped jambo_table bulk_action table_vertical">
									<tbody>
										<tr>
											<th>검색어</th>
											<td>
												<input type="text" name="searchText" id="searchText" value="${searchText}" class="form-control inline-block" style="width:80%;"/>
												<button onclick="fn_search()" style="width:15%;height:100%;background-color:black;color:white;">검색</button>
											</td>
										</tr>
										<tr>
											<th>검색기간</th>
											<td>
												<input type="text" id="startDt" name="startDt" value="${startDt}" class="datepicker" /> 
												~ 
												<input type="text" id="endDt" name="endDt" value="${endDt}" class="datepicker" />
												<button name="today" onclick="fn_dateButton('today')" style="width:5%;height:100%;background-color:black;color:white;margin-right:0">오늘</button>
												<button name="yesterday"onclick="fn_dateButton('-1D')" style="width:5%;height:100%;background-color:black;color:white;margin-right:0">어제</button>
												<button name="aWeek" onclick="fn_dateButton('-1W')" style="width:5%;height:100%;background-color:black;color:white;margin-right:0">7일</button>
												<button name="aMonth" onclick="fn_dateButton('-1M')" style="width:5%;height:100%;background-color:black;color:white;margin-right:0">1개월</button>
												<button name="threeMonth" onclick="fn_dateButton('-3M')" style="width:5%;height:100%;background-color:black;color:white;margin-right:0">3개월</button>
												<button name="sixMonth" onclick="fn_dateButton('-6M')" style="width:5%;height:100%;background-color:black;color:white;margin-right:0">6개월</button>
												<button name="aYear" onclick="fn_dateButton('-1Y')" style="width:5%;height:100%;background-color:black;color:white;margin-right:0">1년</button>
											</td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="clearfix"></div>

		<!--list section-->
		
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<strong>총${pageInfo.rowMax!}건  </strong>
							<a href="#" class="btn btn-primary" onclick="fn_list_write()">전체보기</a>						
							<button type="button" class="btn btn-danger" onclick="fn_delete();">삭제</button>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12 text-right">
						<form class="form-inline" name="selectFrm" id="selectFrm" method="post">
							<select type="select" name="rowCount" id="rowCount">
								<option value="5" <#if (pageInfo.rowCount == 5)> selected="selected"</#if>>5개씩 보기</option>
								<option value="10" <#if (pageInfo.rowCount == 10)> selected="selected"</#if>>10개씩 보기</option>
								<option value="15" <#if (pageInfo.rowCount == 15)> selected="selected"</#if>>15개씩 보기</option>
							</select>
						</form>
						<div class="table-responsive">
							<table class="table table-striped jambo_table bulk_action">
								<thead>
									<tr class="headings">
										<th class="column-title"><input type="checkbox" id="checkAll" name="checkAll" /></th>
										<th class="column-title">No.</th>
										<th class="column-title">작성일</th>
										<th class="column-title">제목</th>
										<th class="column-title">내용</th>
										<th class="column-title">등록자</th>
										<th class="column-title">조회수</th>
										<th class="column-title">예약</th>
									</tr>
								</thead>
								<tbody>
									<#if resultList?has_content>
									<#assign rNum = pageInfo.rowMax - pageInfo.startRowNum + 1 />
									<#list resultList as rMap>
									<tr style="cursor:pointer;">
										<td class=" "><input type="checkbox" name="chk" value="${rMap.textnoticeSeq!}" /></td>
										<td class="even pointer" onclick="javascript:fn_view('${rMap.textnoticeSeq}')">${rNum}</td>
										<td class="even pointer" onclick="javascript:fn_view('${rMap.textnoticeSeq}')">${rMap.uploadDate!}</td>
										<td class="even pointer" onclick="javascript:fn_view('${rMap.textnoticeSeq}')">${rMap.textnoticeTitle!}</td>
										<td class="even pointer" onclick="javascript:fn_view('${rMap.textnoticeSeq}')" width=500>${rMap.textnoticeContent!}</td>
										<td class="even pointer" onclick="javascript:fn_view('${rMap.textnoticeSeq}')">${rMap.textnoticeAdmin!}</td>
										<td class="even pointer" onclick="javascript:fn_view('${rMap.textnoticeSeq}')">${rMap.viewCount!}</td>
										<td class="even pointer" onclick="javascript:fn_view('${rMap.textnoticeSeq}')">${rMap.reserv!}</td>					
									</tr>
									<#assign rNum = rNum - 1/>
									</#list>
									<#else>
										<tr>
											<td colspan="8"><strong>게시물이 존재하지 않습니다.</strong></td>
										</tr>
									</#if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<nav aria-label="Page navigation example" class="content-center text-center">
					<@pagingTag.Paging url="/textNotice/textNotice" pageCount="${pageInfo.pageCount!}" rowCount="${pageInfo.rowCount!}" rowMax="${pageInfo.rowMax!}" nowPage="${pageInfo.nowPage!}" params="searchText=${searchText!}&startDt=${startDt!}&endDt=${endDt!}&listNum=${listNum!}" />
				</nav>
			</div>
		</div>
		
		<!--write notice-------------------------------------------------------->
		
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
						<form name="writeFrm" id="writeFrm" name="writeFrm" method="post" >
						<input type="hidden" name="textnoticeSeq" id="textnoticeSeq" value="${textnoticeSeq!}" />	
						<!--<input class="form-control" type="hidden" id="writer" name="writer" value="${sessionInformation!}"/>	-->			
						<div class="table-responsive">
							<table class="table table-striped jambo_table bulk_action table_vertical">
								<tbody>
									<tr>
										<th class="column-title vertical-middle">제목*</th>
										<td><input type="text" id="textnoticeTitle" name="textnoticeTitle" class="form-control inline-block" maxlength="100" /></td>
									</tr>
									<tr>
										<th class="column-title vertical-middle">내용*</th>
										<td>
											<textarea name="textnoticeContent" id="textnoticeContent" class="form-control inline-block"  rows="10" cols="100" style="width:100%; height:300px; display:none;"></textarea>
										</td>
									</tr>
									<tr style="display:none;">
										<th class="column-title vertical-middle">파일첨부*</th>
										<td colspan="3">
											<div class="form-group text-left" id="imgNmArea">
												<input id="imgNm" name="imgNm" class="form-control inline-block wd400" value="" readonly="readonly" />
												<label for="uploadImg"><span href="#" class="btn btn-primary">찾아보기</span></label>
												<input type="file" id="uploadImg" name="uploadImg" style="display:none;" accept=".jpg,.png"/>
												<br>jpg,png 파일만 올려주세요. (000 * 000px)
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						</form>
						<div class="text-right">
							<a href="#" class="btn btn-info" onclick="fn_preview()">미리보기</a>
							<a href="#" class="btn btn-primary" onclick="fn_save()">등록</a>
							<a href="#" class="btn btn-info" onclick="fn_list_write()">취소</a>
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