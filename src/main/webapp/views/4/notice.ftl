<#assign pagingTag = JspTaglibs["/WEB-INF/tld/paging.tld"] />
<#setting number_format = "###"/>

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
		
		$("#noticeSeq").val(seqs.join(","));
		
		ajaxFormExecute("noticeFrm", "/4/ajaxNoticeDelete", "post", false, false, ajaxReturnDelete);
	}
	
	function ajaxReturnDelete(data){
		if(data.sw){
    		jsAlert.alert("알림", "삭제되었습니다.", null, fn_list, "확인");
    	}else{
    		jsAlert.alert("알림", "삭제에 실패하였습니다.<br>관리자에게 문의바랍니다.");
    	}
	}
	
	function fn_list(){
		$("#noticeFrm").prop("action", "/4/notice");
		$('#nowPage').val('1');
 		formSubmit("noticeFrm");
	}
	
	function fn_view(noticeSeq){
		$("#noticeFrm").prop("action", "/4/noticeView");
		$('#noticeSeq').val(noticeSeq);
 		formSubmit("noticeFrm");
	}
	
	function fn_write(){
		location.href="/4/noticeWrite";	
	}
	
	function fn_search(){
		if(noticeFrm.searchText.value == ""){
			jsAlert.alert("검색","검색어를 입력하세요");
			return ;
		}
		$("#noticeFrm").prop("action", "/4/notice");
	}
	
	function fn_comment() {
		location.href="/4/comment";
	}
	
</script>
 
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
				<div class="x_panel">
					<div class="x_content">
						<div class="row">
							<div class="col-md-6 col-sm-6 col-xs-6 text-left">
								<button type="button" class="btn btn-danger" onclick="fn_delete();">삭제</button>
							</div>
							<div class="col-md-6 col-sm-6 col-xs-6 text-right">
								<button type="button" class="btn btn-primary" onclick="fn_write();">등록</button>
								<button type="button" class="btn btn-primary" onclick="fn_comment();">테스트</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="clearfix"></div>

		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
						<div class="table-responsive">
						<form class="form-inline" name="noticeFrm" id="noticeFrm" method="post" >
								<input type="hidden" id="noticeSeq" name="noticeSeq" value="" />
								<select type="select" name="searchType" id="searchType">
								    <option value="">전체</option>
									<option value="T">제목</option>
									<option value="C">내용</option>
								</select>
								<input type="text" name="searchText" id="searchText" value="${searchText}" />
								<button onclick="fn_search()">검색</button>
							</form>
							<table class="table table-striped jambo_table bulk_action">
								<thead>
									<tr class="headings">
										<th class="column-title"><input type="checkbox" id="checkAll" name="checkAll" /></th>
										<th class="column-title">번호</th>
										<th class="column-title">제목</th>
										<th class="column-title">작성일자 </th>
										<th class="column-title">이벤트</th>
										<th class="column-title">등록</th>
									</tr>
								</thead>
								<tbody>
									<#if resultList?has_content>
									<#assign rNum = pageInfo.rowMax - pageInfo.startRowNum + 1 />
									<#list resultList as rMap>
									<tr style="cursor:pointer;">
										<td class=" "><input type="checkbox" name="chk" value="${rMap.noticeSeq!}" /></td>
										<td class="even pointer" onclick="javascript:fn_view('${rMap.noticeSeq}')">${rNum}</td>
										<td class="even pointer" onclick="javascript:fn_view('${rMap.noticeSeq}')">${rMap.noticeTitle!}</td>
										<td class="even pointer" onclick="javascript:fn_view('${rMap.noticeSeq}')">${rMap.regDate!}</td>
										<td> <button type="button" class="btn btn-success" data-target="#modaltest" data-toggle="modal">설정</button> </td>
										<td> 
											<button type="button" class="btn btn-default">해제</button> 
											<button type="button" class="btn btn-danger">삭제</button> 
										</td>
									</tr> 
									<#assign rNum = rNum - 1/>
									</#list>
									<#else>
										<tr>
											<td colspan="4"><strong>게시물이 존재하지 않습니다.</strong></td>
										</tr>
									</#if>
								</tbody>
							</table>
						</div>
					</div>
				<nav aria-label="Page navigation example" class="content-center text-center">
					<@pagingTag.Paging url="/board/notice" pageCount="${pageInfo.pageCount!}" rowCount="${pageInfo.rowCount!}" rowMax="${pageInfo.rowMax!}" nowPage="${pageInfo.nowPage!}" params="" />
				</nav>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="modaltest" >
  <div class="modal-dialog">
    <div class="modal-content">
      <!-- header -->
      <div class="modal-header">
        <!-- 닫기(x) 버튼 -->
        <button type="button" class="close" data-dismiss="modal">×</button>
        <!-- header title -->
        <h4 class="modal-title">이벤트 설정</h4>
      </div>
      <!-- body -->
      <div class="modal-body">
            적용할 이벤트를 선택해 주세요. <br>
            	<select type="select" name="searchType" id="searchType">
				    <option value="OP">OPEN 이벤트</option>
					<option value="SU">SUMMER 이벤트</option>
					<option value="SP">SPRING 이벤트</option>
					<option value="FA">FALL 이벤트</option>
					<option value="WI">WINTER 이벤트</option>
				</select>
				<button type="button" class="btn btn-default" data-dismiss="modal">해제</button>
      </div>
      <!-- Footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">적용</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
\