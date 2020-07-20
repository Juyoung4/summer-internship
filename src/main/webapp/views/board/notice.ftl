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
		
		ajaxFormExecute("noticeFrm", "/board/ajaxNoticeDelete", "post", false, false, ajaxReturnDelete);
	}
	
	function ajaxReturnDelete(data){
		if(data.sw){
    		jsAlert.alert("알림", "삭제되었습니다.", null, fn_list, "확인");
    	}else{
    		jsAlert.alert("알림", "삭제에 실패하였습니다.<br>관리자에게 문의바랍니다.");
    	}
	}
	
	function fn_list(){
		$("#noticeFrm").prop("action", "/board/notice");
		$('#nowPage').val('1');
 		formSubmit("noticeFrm");
	}
	
	function fn_view(noticeSeq){
		$("#noticeFrm").prop("action", "/board/noticeView");
		$('#noticeSeq').val(noticeSeq);
 		formSubmit("noticeFrm");
	}
	
	function fn_write(){
		location.href="/board/noticeWrite";	
	}
	
	function fn_search(){
		$("#noticeFrm").prop("action", "/board/notice");
		$('#nowPage').val('1');
 		formSubmit("noticeFrm");
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
							<table class="table table-striped jambo_table bulk_action">
								<thead>
									<tr class="headings">
										<th class="column-title"><input type="checkbox" id="checkAll" name="checkAll" /></th>
										<th class="column-title">번호</th>
										<th class="column-title">제목</th>
										<th class="column-title">작성일자 </th>
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
				</div>
				<nav aria-label="Page navigation example" class="content-center text-center">
					<@pagingTag.Paging url="/board/notice" pageCount="${pageInfo.pageCount!}" rowCount="${pageInfo.rowCount!}" rowMax="${pageInfo.rowMax!}" nowPage="${pageInfo.nowPage!}" params="searchText=${searchText!}&searchType=${searchType!}" />
				</nav>
			</div>
		</div>
	</div>
</div>