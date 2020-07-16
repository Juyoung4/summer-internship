<#assign pagingTag = JspTaglibs["/WEB-INF/tld/paging.tld"] />
<#setting number_format = "###"/>
<#assign ticketStatus1 = "미등록">
<#assign ticketStatus2 = "등록">
<#assign size=resultList?size>

<script src="/js/download.js"></script>
<script type="text/javascript">
	var form;
	var seq;
	$(document).ready(function(){
		$('#modalevent').on('show.bs.modal', function(event) {          
            seq = $(event.relatedTarget).data('id');
        });
	});
	
	function fn_list(){
		$("#useTicketFrm").prop("action", "/payment/useTicketMg");
		$('#nowPage').val('1');
 		formSubmit("useTicketFrm");
	}
	
	//이벤트 설정 + 이용권 등록
	function fn_event(){
		//seq없으면 이용권 등록, 있으면 이벤트 등록
		if(!seq){
			jsAlert.confirm("확인", "이용권을 등록 하시겠습니까?", fn_eventYn, null, "확인", "취소")
    	}else{
    		jsAlert.confirm("확인", "해당 이벤트를 등록 하시겠습니까?", fn_eventYn, null, "확인", "취소")
    	}
	}
	
	function fn_eventYn(){
		$('#useTicketSeq').val(seq);
		//seq없으면 이용권 등록, 있으면 이벤트 등록
		if(!seq){
			form = "useTicketInsertFrm"
		}
		else{
			form = "useTicketFrm"
		}
		ajaxFormExecute(form, "/payment/ajaxuseTicketRegister", "post", false, false, ajaxReturnInsert);
	}
	
	function ajaxReturnInsert(data){
		seq = "";
		if(data.sw){
    		jsAlert.alert("알림", "이벤트 등록 되었습니다.", null, fn_list, "확인");
    	}else{
    		jsAlert.alert("알림", "이벤트 등록에 실패하였습니다.<br>관리자에게 문의바랍니다.");
    	}
	}
	
	function fn_delete(useTicketSeq, useTicketName) {
		jsAlert.confirm("확인", "이용권을 삭제 하시겠습니까?", fn_deleteYn(), null, "확인", "취소")
	}
	
	function fn_deleteYn(){
		ajaxFormExecute("useTicketFrm", "/payment/ajaxuseTicketDelete", "post", false, false, ajaxReturnDelete);
	}
	
	function ajaxReturnDelete(data){
		if(data.sw){
    		jsAlert.alert("알림", "삭제되었습니다.", null, fn_list, "확인");
    	}else{
    		jsAlert.alert("알림", "삭제에 실패하였습니다.<br>관리자에게 문의바랍니다.");
    	}
	}
	
	function fn_test(){
		$("#useTicketInsertFrm").prop("action", "/payment/useTicketMg");
		$('#nowPage').val('1');
 		formSubmit("useTicketInsertFrm");
	}
</script>

<!-- 이용권 modal창 -->
<div class="modal fade" id="modalevent">
  <div class="modal-dialog">
    <div class="modal-content">
      <!-- header -->
      <div class="modal-header">
        <!-- header title -->
        <h4 class="modal-title">이벤트 설정</h4>
      </div>
      <!-- body -->
      <div class="modal-body">
       	적용할 이벤트를 선택해 주세요. <br>
       	<form class="form-inline" name="useTicketFrm" id="useTicketFrm" method="post" >
		<input type="hidden" id="useTicketSeq" name="useTicketSeq" value="" />
          	<select type="select" name="searchType" id="searchType">
			    <option value="OP">OPEN Discount</option>
				<option value="SU">SUMMER Discount</option>
				<option value="SP">SPRING Discount</option>
				<option value="FA">FALL Discount</option>
				<option value="WI">WINTER Discount</option>
			</select>
			<button type="button" class="btn btn-default"  style="float:right;" data-dismiss="modal">이벤트 해제</button>
		</form>
      </div>
      <!-- Footer -->
      <div class="modal-footer">
        <center>
        	<button type="button" class="btn btn-primary" onclick="fn_event();">적용</button>
        	<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
        </center>
      </div>
    </div>
  </div>
</div>


<!-- 화면 -->
<div class="right_col" role="main">
	<div class="">
    	<div class="page-title">
  			<div class="title_left">
				<h3>이용권 리스트 예제</h3>
			</div>
		</div>
	
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-10 col-sm-10 col-xs-10">
				<div class="x_panel">
					<div class="x_content">
						<form class="form-inline" id="useTicketInsertFrm" method="post" >
						<div class="table-responsive">
							<table class="table table-striped jambo_table bulk_action">
								<tbody>
									<tr>
										<td style="background-color:#E2E2E2">이용권 이름 설정</td>
										<td><input type="text" id="useTicketName" name="useTicketName" value="" /></td>
									</tr>
									<tr>
										<td style="background-color:#E2E2E2">사용기간 설정</td>
										<td>
											<button type="button" class="btn btn-success">+1일</button>
											<button type="button" class="btn btn-success">+7일</button>
											<button type="button" class="btn btn-success">+30일</button>
											<button type="button" class="btn btn-success">+365일</button>
											<input type="text" id="useTicketPeriod" name="useTicketPeriod" value="" />일
										</td>
									</tr> 
									<tr>
										<td style="background-color:#E2E2E2">거래소</td>
										<td>
											<select type="select" name="EXsearchType" id="EXsearchType">
											    <option value="BitMEX">BitMEX</option>
												<option value="Soool">Soool</option>
												<option value="Coold">Coold</option>
											</select>
										</td>
									</tr>
									<tr>
										<td style="background-color:#E2E2E2">가격</td>
										<td>
										<input type="text" id="useTicketPrice" name="useTicketPrice" value="" />
										<button type="button" style="float:right;" class="btn btn-default" onclick="fn_event()">저장</button>
										</td>
									</tr> 
								</tbody>
							</table>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				총 <span style="color:red">${size}</span> 건
				<div class="x_panel">
					<div class="x_content">
						<div class="table-responsive">
							<table class="table table-striped jambo_table bulk_action">
								<thead>
									<tr class="headings">
										<th class="column-title">번호</th>
										<th class="column-title">거래소</th>
										<th class="column-title">이용권명</th>
										<th class="column-title">적용기간</th>
										<th class="column-title">상태</th>
										<th class="column-title">가격</th>
										<th class="column-title">이벤트</th>
										<th class="column-title">등록</th>
									</tr>
								</thead>
								<tbody>
									<#if resultList?has_content>
									<#assign rNum = pageInfo.rowMax - pageInfo.startRowNum + 1 />
									<#list resultList as rMap>
										<#if rMap.useTicketStatus == 1>
											<#assign ticketStatus1 = "등록" />
											<#assign ticketStatus2 = "해제" />
										</#if>
									<tr style="cursor:pointer;">
										<td class="even pointer">${rNum}</td>
										<td class="even pointer">${rMap.exchangeName!}</td>
										<td class="even pointer">${rMap.useTicketName!}권</td>
										<td class="even pointer">${rMap.applicationPeriod!}일</td>
										<td class="even pointer">${ticketStatus1}</td>
										<td class="even pointer">${rMap.useTicketPrice!}</td>
										<td class="even pointer">${rMap.useTicketEvent?default(" - ")}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" data-toggle="modal" data-target="#modalevent" data-id='${rMap.useTicketSeq}' class="btn btn-success">설정</button> </td>
										<td> 
											<button type="button" class="btn btn-default">${ticketStatus2}</button>
											<button type="button" onclick="javascript:fn_delete('${rMap.useTicketSeq}','${rMap.useTicketName}')" class="btn btn-danger">삭제</button>
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
							<@pagingTag.Paging url="/payment/useTicketMg" pageCount="${pageInfo.pageCount!}" rowCount="${pageInfo.rowCount!}" rowMax="${pageInfo.rowMax!}" nowPage="${pageInfo.nowPage!}" params="" />
						</nav>	
			</div>
		</div>
	</div>
</div>