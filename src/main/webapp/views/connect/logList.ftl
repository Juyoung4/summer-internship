<#assign pagingTag = JspTaglibs["/WEB-INF/tld/paging.tld"] />
<#setting number_format = "###"/>

<script type="text/javascript">
	$(document).ready(function(){
	});
	
	
	
	function fn_list(){
		$("#logFrm").prop("action", "/connect/logList");
		$('#nowPage').val('1');
 		formSubmit("logFrm");
	}
	
	function fn_view(logSeq){
		$("#logFrm").prop("action", "/connect/logList");
		$('#logSeq').val(logSeq);
 		formSubmit("logFrm");
	}
	
	function fn_search(){
		$("#logFrm").prop("action", "/connect/logList");
		$('#nowPage').val('1');
		formSubmit("logFrm");
	}
</script>
 
<div class="right_col" role="main">
	<div class="">
    	<div class="page-title">
  			<div class="title_left">
				<h3>접속 로그</h3>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
						<div class="table-responsive">

                        			<th class="headings" style="width: 25%">검색</th>
                     				<form class="form-inline" name="logFrm" id="logFrm" method="post" >
                        			<input type="hidden" id="logSeq" name="logSeq" value="" />
                        			<select type="select" name="searchType" id="searchType">
                           				<option value="">전체</option>
                           				<option value="I">ID</option>
                           				<option value="T">Telegram ID</option>
                        			</select>
                        			<input type="text" name="searchText" id="searchText" value="${searchText}" style="width: 60%"/>
                        			<button onclick="fn_search()">검색</button>
                     				</form>
                     			
							<table class="table table-striped jambo_table bulk_action">
								<thead>
									<tr class="headings">
										<th class="column-title" style="width: 5%">No</th>
										<th class="column-title" style="width: 15%">접속일</th>
										<th class="column-title" style="width: 15%">ID</th>
										<th class="column-title" style="width: 10%">국가</th>
										<th class="column-title" style="width: 15%">TimeZone</th>
										<th class="column-title" style="width: 15%">Telegram ID</th>
										<th class="column-title" style="width: 10%">이용권</th>
										<th class="column-title" style="width: 15%">이용권 만료일</th>
									</tr>
								</thead>
								<tbody>
									<#if resultList?has_content>
									<#assign rNum = pageInfo.rowMax - pageInfo.startRowNum + 1 />
									<#list resultList as rMap>
									<tr style="cursor:pointer;">
										<td class="even pointer">${rMap.logIdx}</td>
										<td class="even pointer">${rMap.logDate!}</td>
										<td class="even pointer">${rMap.logId!}</td>
										<td class="even pointer">${rMap.logCountry!}</td>
										<td class="even pointer">${rMap.logTimezone!}</td>
										<td class="even pointer">${rMap.logTelgrm!}</td>
										<td class="even pointer">${rMap.logUse!}</td>
										<td class="even pointer">${rMap.logExp!}</td>
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
					<@pagingTag.Paging url="/connect/logList" pageCount="${pageInfo.pageCount!}" rowCount="${pageInfo.rowCount!}" rowMax="${pageInfo.rowMax!}" nowPage="${pageInfo.nowPage!}" params="searchText=${searchText!}&searchType=${searchType!}" />
				</nav>
			</div>
		</div>
	</div>
</div>