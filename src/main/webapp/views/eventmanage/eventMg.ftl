<#assign pagingTag = JspTaglibs["/WEB-INF/tld/paging.tld"] />
<#setting number_format = "###"/>

<script type="text/javascript">
	function fn_list(){
		$("#eventInsertFrm").prop("action", "/eventmanage/eventMg");
		$('#nowPage').val('1');
		formSubmit("eventInsertFrm");
	}
	

	function fn_evsave(){
		if (!eventInsertFrm.EventName.value && !eventInsertFrm.EventPeriods.value && !eventInsertFrm.EventPeriodf.value && !eventInsertFrm.DiscountRate.value){
			jsAlert.alert("알림", "빈칸을 모두 채워주세요");
		} else{
			jsAlert.confirm("확인", "이벤트 저장 하시겠습니까?", fn_evsaveYn, null, "확인", "취소")	
		}
	}
	
	function fn_evsaveYn(){
		eventInsertFrm.EventPeriodS.value=$("#EventPeriodst").val();
		eventInsertFrm.EventPeriodF.value=$("#EventPeriodfi").val();
		ajaxFormExecute("eventInsertFrm", "/eventmanage/ajaxeventSave", "post", false, false, ajaxReturnevsave);
	}
	function ajaxReturnevsave(data){
		if(data.sw){
    		jsAlert.alert("알림", "이벤트 저장 되었습니다.", null, fn_list, "확인");
    	}else{
    		jsAlert.alert("알림", "이벤트 저장에 실패하였습니다.<br>관리자에게 문의바랍니다.");
    	}
	}
	
</script>
 
<div class="right_col" role="main">
	<div class="">
    	<div class="page-title">
  			<div class="title_left">
				<h3>이벤트 관리 예제</h3>
			</div>
		</div>
	
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-6 col-sm-20 col-xs-10">
				<div class="x_panel">
					<div class="x_content">
						<form class="form-inline" id="eventInsertFrm" method="post" >
						<input type="hidden" id="EventPeriodS" name="EventPeriodS" value="" />
						<input type="hidden" id="EventPeriodF" name="EventPeriodF" value="" />
						<div class="table-responsive">
							<table class="table table-striped jambo_table bulk_action">
								<tbody>
									<tr>
										<td style="background-color:#E2E2E2">이벤트 이름</td>
										<td><input type="text" id="EventName" name="EventName" value="" /></td>
									</tr>
									<tr>
										<td style="background-color:#E2E2E2">이벤트 기간</td>
										<td>
										<input type="date" id="EventPeriodst" name="EventPeriodst" />
										~
										<input type="date" id="EventPeriodfi" name="EventPeriodfi" />
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
										<td style="background-color:#E2E2E2">할인률</td>
										<td>
											<input type="text" id="DiscountRate" name="DiscountRate" value="" />%
											<button type="button" style="float:right;" class="btn btn-success" onclick="fn_evsave()">저장</button>
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
				<div class="x_panel">
					<div class="x_content">
						<div class="table-responsive">
							<table class="table table-striped jambo_table bulk_action">
								<thead>
									<tr class="headings">
										<th class="column-title">NO</th>
										<th class="column-title">거래소</th>
										<th class="column-title">이벤트명</th>
										<th class="column-title">이벤트 기간</th>
										<th class="column-title">할인률</th>
										<th class="column-title">상태</th>
										<th class="column-title">변경</th>
									</tr>
								</thead>
								<tbody>
									<#if eventList?has_content>
									<#assign eNum = pageInfo.rowMax - pageInfo.startRowNum/>
									<#list eventList as eMap>
										<tr style="cursor:pointer;">
											<td class="even pointer">${eNum}</td>
											<td class="even pointer">${eMap.exchange!}</td>
											<td class="even pointer">${eMap.eventname!}</td>
											<td class="even pointer">${eMap.eventperiods!}&nbsp;&nbsp; ~ &nbsp;&nbsp;${eMap.eventperiodf!}</td>
											<td class="even pointer">${eMap.discountrate!} %</td>
											<#if eMap.eventstatus == 0>
												<td class="even pointer">종료</td>
											<#else>
												<td class="even pointer">예약</td>
											</#if>
											<td> <button type="button" class="btn btn-success">변경</button> </td>
										</tr>
									<#assign eNum = eNum - 1/>
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
							<@pagingTag.Paging url="/eventmange/eventMg" pageCount="${pageInfo.pageCount!}" rowCount="${pageInfo.rowCount!}" rowMax="${pageInfo.rowMax!}" nowPage="${pageInfo.nowPage!}" params="" />
						</nav>
			</div>
		</div>
	</div>
</div>
