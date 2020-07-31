<#assign crudText = "저장" />
<#assign pagingTag = JspTaglibs["/WEB-INF/tld/paging.tld"] />
<#setting number_format = "###"/>

<script type="text/javascript">
	
	function fn_list(){
		$("#manageFrm").prop("action", "/refferal/manage");
		$('#nowPage').val('1');
 		formSubmit("manageFrm");
	}

	function fn_save(){
		if(!refferalInsertFrm.refferalName.value){
			jsAlert.alert("알림", "refferalName을 입력하세요.");
			return false;
		}
		
		if(!refferalInsertFrm.refferalCode.value){
			jsAlert.alert("알림", "refferalCode를 입력하세요.");
			return false;
		}
		
		jsAlert.confirm("확인", "refferal 등록 하시겠습니까?", fn_submit, null, "확인", "취소");
		
	}
	function fn_submit(){
		ajaxFormExecute("refferalInsertFrm", "/refferal/ajaxRefferalSubmit", "post", false, false, ajaxReturnSubmit);
    }
    
    function ajaxReturnSubmit(data){
		if(data.sw){
    		jsAlert.alert("알림", "등록되었습니다.", null, fn_list, "확인");
    	}else{
    		jsAlert.alert("알림", "등록에 실패하였습니다.<br>관리자에게 문의바랍니다.");
    	}
	}
	
	function fn_copyName(data){
		var dummy = document.createElement("textarea");
	  	document.body.appendChild(dummy);
	  	dummy.value = data;
	  	dummy.select();
	  	document.execCommand("copy");
	  	document.body.removeChild(dummy);
	}
</script>
 
 
 
<div class="right_col" role="main">
	<div class="">
    	<div class="page-title">
  			<div class="title_left">
				<h3>레퍼럴 관리</h3>
			</div>
		</div>
	
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-10 col-sm-10 col-xs-10">
				<div class="x_panel">
					<div class="x_content">
						<form class="form-inline" id="refferalInsertFrm" method="post" >
						<div class="table-responsive">
							<table class="table table-striped jambo_table bulk_action">
								<tbody>
									<tr>
										<td style="background-color:#E2E2E2">레퍼럴 코드 이름</td>
										<td><input type="text" id="refferalName" name="refferalName" value="" /></td>
									</tr>
									<tr>
										<td style="background-color:#E2E2E2">레퍼럴 코드 등록</td>
										<td>
										<input type="text" id="refferalCode" name="refferalCode" value="" />
										<button type="button" style="float:right;" class="btn btn-default" onclick="fn_save()">저장</button>
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
							<form class="form-inline" name="manageFrm" id="manageFrm" method="post" >
								<input type="hidden" id="idx" name="idx" value="" />
							</form>
							<table class="table table-striped jambo_table bulk_action">
								<thead>
									<tr class="headings">
										<th class="column-title">num</th>
										<th class="column-title">레퍼럴 코드</th>
										<th class="column-title">레퍼럴 코드 이름</th>
									</tr>
								</thead>
								<tbody>
									<#if resultList?has_content>
									<#assign rNum = pageInfo.rowMax - pageInfo.startRowNum + 1 />
									<#list resultList as rMap>
									<tr style="cursor:pointer;">
										<td class="even pointer">${rNum}</td>
										<td class="even pointer">${rMap.cName!}
											<button class="btn btn-default" onclick="javascript:fn_copyName('${rMap.cName}')">clone</button>
										</td>
										<td class="even pointer">${rMap.cCode!}</td>
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
					<@pagingTag.Paging url="/refferal/manage" pageCount="${pageInfo.pageCount!}" rowCount="${pageInfo.rowCount!}" rowMax="${pageInfo.rowMax!}" nowPage="${pageInfo.nowPage!}" params="searchText=${searchText!}&searchType=${searchType!}" />
				</nav>
			</div>
		</div>
	</div>
</div>