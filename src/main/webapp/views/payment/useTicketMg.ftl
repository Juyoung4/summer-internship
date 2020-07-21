<#assign pagingTag = JspTaglibs["/WEB-INF/tld/paging.tld"] />
<#setting number_format = "###"/>

<#if resultUseTicketList?has_content>
	<#assign size=resultUseTicketList?size>
<#else>
	<#assign size=0>
</#if>

<script src="/js/download.js"></script>
<script type="text/javascript">
	var form;
	var seq;
	//설정 버튼 클릭 시 event관련 modal창 띄움
	$(document).ready(function(){
		$('#modalevent').on('show.bs.modal', function(event) {          
            var seq = $(event.relatedTarget).data('id');
        });
	});

	//등록 버튼 클릭 시 event+이용권 modal창 띄움
	$(document).ready(function(){
		$('#modalEvUse').on('show.bs.modal', function(event) {
			//jsAlert.alert("알림", $('#ticketStatus2'));
			if ($(event.relatedTarget).data('event') != "x"){
				useTicketFrm.useTicketSeq.value = $(event.relatedTarget).data('id');
			} else{
				useTicketFrm.useTicketSeq.value ="";
			}
			document.getElementById('useticket').innerHTML = '&nbsp;:&nbsp;&nbsp;&nbsp; '+ $(event.relatedTarget).data('useticket')+"권";
			document.getElementById('period').innerHTML = '&nbsp;:&nbsp;&nbsp;&nbsp; '+ $(event.relatedTarget).data('period')+"일";
			document.getElementById('price').innerHTML = '&nbsp;:&nbsp;&nbsp;&nbsp; '+ $(event.relatedTarget).data('price')+"원";
			document.getElementById('event').innerHTML = '&nbsp;:&nbsp;&nbsp;&nbsp; '+ $(event.relatedTarget).data('event');
        });
	});
	
	//modalEvUse modal창 안에 저장 버튼 누르면 + 해제 버튼 누르면
	function fn_updateState(check1, check2){
		if (!check1 && !check2){
			if (!useTicketFrm.useTicketSeq.value){
				jsAlert.alert("알림", "이벤트 등록 후에 등록 가능합니다");
			} else{
				useTicketFrm.searchType.value="";
				useTicketFrm.useTicketStatus.value=1;
				fn_releaseYn();
			}
		} else{
			useTicketFrm.searchType.value="";
			useTicketFrm.useTicketStatus.value=0;
			useTicketFrm.useTicketSeq.value = check1;
			jsAlert.confirm("확인", check2+"권을 해제 하시겠습니까?", fn_releaseYn, null, "확인", "취소");
		}
	}
	
	function fn_releaseYn(){
		ajaxFormExecute("useTicketFrm", "/payment/ajaxuseTicketRegister", "post", false, false, ajaxReturnrelease);
	}
	
	function ajaxReturnrelease(data){
		if(data.sw){
    		jsAlert.alert("알림", "이용권-이벤트 해제 되었습니다.", null, fn_list, "확인");
    	}else{
    		jsAlert.alert("알림", "이용권-이벤트 등록에 실패하였습니다.<br>관리자에게 문의바랍니다.");
    	}
	}
	
	//이용권 등록하는 form에서 버튼 누르면 일 수 증가
	function fn_addPeriod1(days){
		var period = Number(useTicketInsertFrm.useTicketPeriod.value);
		if (Number(days) == 0){
			period = 0;
		}
		else {
			period += Number(days);
		}
		useTicketInsertFrm.useTicketPeriod.value = period;
	}
	
	//이용권 정보 목록 가져오기
	function fn_list(){
		$("#useTicketInsertFrm").prop("action", "/payment/useTicketMg");
		$('#nowPage').val('1');
 		formSubmit("useTicketInsertFrm");
	}
	
	//이벤트 설정 + 이용권 등록
	function fn_register(){
		//seq없으면 이용권 등록, 있으면 이벤트 등록
		if(!seq){
			if (!useTicketInsertFrm.useTicketName.value || !useTicketInsertFrm.useTicketName.value || !useTicketInsertFrm.useTicketName.value){
				jsAlert.alert("알림", "빈칸을 모두 채워주세요");
			} else{
				//jsAlert.alert("알림", " 채워주세요");
				jsAlert.confirm("확인", "이용권을 등록 하시겠습니까?", fn_registerYn, null, "확인", "취소")	
			}
    	}else{
    		jsAlert.confirm("확인", "해당 이벤트를 등록 하시겠습니까?", fn_registerYn, null, "확인", "취소")
    	}
	}
	
	
	
	function fn_registerYn(){
		$('#useTicketSeq').val(seq);
		//seq없으면 이용권 등록, 있으면 이벤트 등록
		if(!seq){
			form = "useTicketInsertFrm"
		}
		else{
			form = "useTicketFrm"
		}
		ajaxFormExecute(form, "/payment/ajaxuseTicketRegister", "post", false, false, ajaxReturnregister);
	}
	
	function ajaxReturnregister(data){
		seq = "";
		if(data.sw){
    		jsAlert.alert("알림", "이벤트 등록 되었습니다.", null, fn_list, "확인");
    	}else{
    		jsAlert.alert("알림", "이벤트 등록에 실패하였습니다.<br>관리자에게 문의바랍니다.");
    	}
	}
	
	function fn_delete(useTicketSeq, useTicketName) {
		$('#useTicketSeq').val(useTicketSeq);
		jsAlert.confirm("확인", useTicketName+"일권 삭제 하시겠습니까?", fn_deleteYn, null, "확인", "취소");
	}
	
	function fn_deleteYn(useTicketSeq){
		ajaxFormExecute("useTicketFrm", "/payment/ajaxuseTicketDelete", "post", false, false, ajaxReturnDelete);
	}
	
	function ajaxReturnDelete(data){
		if(data.sw){
    		jsAlert.alert("알림", "삭제되었습니다.", null, fn_list, "확인");
    	}else{
    		jsAlert.alert("알림", "삭제에 실패하였습니다.<br>관리자에게 문의바랍니다.");
    	}
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
		<input type="hidden" id="useTicketStatus" name="useTicketStatus" value="" />
			<select type="select" name="searchType" id="searchType">
			<#if eventList?size != 1>
          	<#list eventList as eMap>
          		<#if eMap.eventseq != 0>
          			<option value="${eMap.eventseq}">${eMap.eventname}</option>
          		</#if>
          	</#list>
          	<#else>
          		<option value="${eMap.eventseq}">이벤트가 존재하지 않습니다</option>
          	</#if>
          	</select>
			<button type="button" class="btn btn-default"  style="float:right;" data-dismiss="modal">이벤트 해제</button>
		</form>
      </div>
      <!-- Footer -->
      <div class="modal-footer">
        <center>
        	<button type="button" class="btn btn-primary" onclick="fn_register();">적용</button>
        	<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
        </center>
      </div>
    </div>
  </div>
</div>

<!-- 이용권+이벤트 등록 modal창 -->
<div class="modal fade" id="modalEvUse">
  <div class="modal-dialog" data-backdrop="static">
    <div class="modal-content">
      <!-- body -->
      <!-- 
      <form class="form-inline" name="modalEveUse" id="modalEveUse" method="post" >
      <input type="hidden" id="UseEveSeq" name="UseEveSeq" value="" />
       -->
      <div class="modal-body">
       	<h4 class="modal-title">이용권을 등록하시겠습니까?</h4>
        <table>
	      <tbody>
	        <tr>
	          <td>*	이용권 명</td><td id="useticket"></td>
	        </tr>
	        <tr>
	          <td>* 적용 기간</td><td id="period"></td>
	        </tr>
	        <tr>
	          <td>*	가격</td><td id="price"></td>
	        </tr>
	        <tr>
	          <td>*	이벤트</td><td id="event"></td>
	        </tr>
	      </tbody>
	    </table>
      </div>
      <!--</form> Footer -->
      <div class="modal-footer">
        <center>
        	<button type="button" class="btn btn-primary" onclick="fn_updateState()">등록</button>
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
											<button type="button" onclick="fn_addPeriod1(1)" class="btn btn-primary">+1일</button>
											<button type="button" onclick="fn_addPeriod1(7)" class="btn btn-primary">+7일</button>
											<button type="button" i onclick="fn_addPeriod1(30)" class="btn btn-primary">+30일</button>
											<button type="button" onclick="fn_addPeriod1(365)" class="btn btn-primary">+365일</button>
											<input type="text" id="useTicketPeriod" name="useTicketPeriod" value="0" />일
											<button type="button" onclick="fn_addPeriod1(0)" class="btn btn-danger">RESET</button>
										</td>
									</tr> 
									<tr>
										<td style="background-color:#E2E2E2">거래소</td>
										<td>
											<select type="select" name="EXsearchType" id="EXsearchType">
												<#if resultExchangeList?has_content>
									          	<#list resultExchangeList as exMap>
									          		<option value="${exMap.exchangename}">${exMap.exchangename}</option>
									          	</#list>
									          	<#else>
									          		<option value="NONE">거래소가 존재하지 않습니다</option>
									          	</#if>
											</select>
										</td>
									</tr>
									<tr>
										<td style="background-color:#E2E2E2">가격</td>
										<td>
										<input type="text" id="useTicketPrice" name="useTicketPrice" value="" />
										<button type="button" style="float:right;" class="btn btn-default" onclick="fn_register()">저장</button>
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
									<#if resultUseTicketList?has_content>
									<#assign rNum = pageInfo.rowMax - pageInfo.startRowNum + 1 />
									<#list resultUseTicketList as rMap>
										<#if rMap.useTicketStatus == 1>
											<#assign ticketStatus1 = "등록" />
											<#assign ticketStatus2 = "해제" />
										<#else>
											<#assign ticketStatus1 = "미등록" />
											<#assign ticketStatus2 = "등록" />
										</#if>
									<tr style="cursor:pointer;">
										<td class="even pointer">${rNum}</td>
										<td class="even pointer">${rMap.exchangeName!}</td>
										<td class="even pointer">${rMap.useTicketName!}권</td>
										<td class="even pointer">${rMap.applicationPeriod!}일</td>
										<td class="even pointer">${ticketStatus1}</td>
										<td class="even pointer">${rMap.useTicketPrice!}</td>
										<td class="even pointer">${rMap.eventname?default(" - ")}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" data-toggle="modal" data-target="#modalevent" data-id='${rMap.useTicketSeq}' class="btn btn-success">설정</button> </td>
										<td> 
											<#if ticketStatus2=="등록">
												<button type="button" data-toggle="modal" data-target="#modalEvUse" data-id='${rMap.useTicketSeq}' data-useticket='${rMap.useTicketName}' data-period='${rMap.applicationPeriod}' data-price='${rMap.useTicketPrice}' data-event='${rMap.eventname?default("x")}'class="btn btn-primary">${ticketStatus2}</button>
											<#else>
												<button type="button" id="release" onclick="javascript:fn_updateState('${rMap.useTicketSeq}','${rMap.useTicketName}')" class="btn btn-default">${ticketStatus2}</button> 
											</#if>
											<button type="button" id="delete" onclick="javascript:fn_delete('${rMap.useTicketSeq}','${rMap.useTicketName}')" class="btn btn-danger">삭제</button>
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