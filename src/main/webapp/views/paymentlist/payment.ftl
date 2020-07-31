<#assign pagingTag = JspTaglibs["/WEB-INF/tld/paging.tld"] />
<#setting number_format = "###"/>
<#assign Price=0>
<#if resultList?has_content>
	<#assign size=resultList?size>
	<#list resultList as R>
		<#assign Price = Price+(R.ticketprice)/> 
	</#list>
<#else>
	<#assign size=0>
</#if>
<script type="text/javascript">
	function fn_search(){
		$("#searchFrm").prop("action", "/paymentlist/payment");
		$('#nowPage').val('1');
 		formSubmit("searchFrm");
	}
</script>
 
 
<div class="right_col" role="main">
	<div class="">
    	<div class="page-title">
  			<div class="title_left">
				<h3>이용권 결제 관리</h3>
			</div>
		</div>
	
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-10 col-sm-10 col-xs-10">
				<div class="x_panel">
					<div class="x_content">
						<form class="form-inline" id="searchFrm" method="post" >
						<div class="table-responsive">
							<table class="table table-striped jambo_table bulk_action">
								<tbody>
									<tr>
										<td style="background-color:#E2E2E2">ID 검색</td>
										<td><input type="text" id="searchID" name="searchID" value="" /></td>
									</tr>
									<tr>
										<td style="background-color:#E2E2E2">기간</td>
										<td>
										<input type="date" id="searchPeriods" name="searchPeriods" />
										~
										<input type="date" id="searchPeriodf" name="searchPeriodf" />
										</td>
									</tr> 
									<tr>
										<td style="background-color:#E2E2E2">이용권 명</td>
										<td>
											<select type="select" name="useticketname" id="useticketname">
													<option value="ALL">전체</option>
												<#if resultUseTicketList?has_content>
									          	<#list resultUseTicketList as uMap>
									          		<option value="${uMap.useTicketSeq}">${uMap.useTicketName} 권</option>
									          	</#list>
									          	<#else>
									          		<option value="NONE">전체</option>
									          	</#if>
											</select>
											<button type="button" style="float:right;" class="btn btn-primary" onclick="fn_search()">검색</button>
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
		&nbsp;총 &nbsp;<span style="color:red">${size}</span> 건 &nbsp;&nbsp;&nbsp;총 금액: <span style="color:red">${Price}</span>원
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
						<div class="table-responsive">
							<table class="table table-striped jambo_table bulk_action">
								<thead>
									<tr class="headings">
										<th class="column-title">num</th>
										<th class="column-title">거래소</th>
										<th class="column-title">결제일</th>
										<th class="column-title">구매자 ID</th>
										<th class="column-title">이용권 명</th>
										<th class="column-title">이벤트 적용</th>
										<th class="column-title">결제 금액</th>
									</tr>
								</thead>
								<tbody>
									<#if resultList?has_content>
									<#assign rNum = pageInfo.rowMax - pageInfo.startRowNum/>
									<#list resultList as rMap>
									<tr style="cursor:pointer;">
										<td class="even pointer">${rNum}</td>
										<td class="even pointer">${rMap.exchangeName!}</td>
										<td class="even pointer">${rMap.paymentDay!}</td>
										<td class="even pointer">${rMap.id!}</td>
										<td class="even pointer">${rMap.useTicketName!}</td>
										<td class="even pointer">${rMap.eventname!} (${rMap.discountrate!}%)</td>
										<td class="even pointer">${rMap.ticketprice!} 원</td>
									</tr>
									<#assign rNum = rNum - 1/>
									</#list>
									<#else>
										<tr>
											<td colspan="4"><strong>결제 내역이 존재하지 않습니다.</strong></td>
										</tr>
									</#if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<nav aria-label="Page navigation example" class="content-center text-center">
					<@pagingTag.Paging url="/paymentlist/payment" pageCount="${pageInfo.pageCount!}" rowCount="${pageInfo.rowCount!}" rowMax="${pageInfo.rowMax!}" nowPage="${pageInfo.nowPage!}" params="searchText=${searchText!}&searchType=${searchType!}" />
				</nav>
			</div>
		</div>
	</div>
</div>