<#assign pagingTag = JspTaglibs["/WEB-INF/tld/paging.tld"] />
<#setting number_format = "###"/>
<#assign returnUrl = "admin" />

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
	
	function fn_list(){
		$("#noticeFrm").prop("action", "/join/admin");
		$('#nowPage').val('1');
 		formSubmit("noticeFrm");
	}
	
	var checkflag = 'false';
	
	function fn_select(){
		if( checkflag == 'false' ){
			$("input[name=chk]").prop("checked", true);
			checkflag = 'true';
	      }else{
	    	  $("input[name=chk]").prop("checked", false);
	    	  checkflag = 'false';
	      }
	}
	
	function fn_approve(){
		if($('input:checkbox[name=chk]').is(':checked') == false){
			jsAlert.alert("알림", "승인할 사용자를 선택해주세요");
			return false;
		}
		jsAlert.confirm("확인", "해당 사용자를 승인 하시겠습니까?", fn_approveYn, null, "확인", "취소");
	}
	
	function fn_delay(){
		if($('input:checkbox[name=chk]').is(':checked') == false){
			jsAlert.alert("알림", "반려할 사용자를 선택해주세요");
			return false;
		}
		jsAlert.confirm("확인", "해당 사용자를 반려 하시겠습니까?", fn_delayYn, null, "확인", "취소");
	}
	
	function fn_approveYn(){
		var seqs = [];
		$("input[name=chk]:checked").each(function() {
			seqs[seqs.length] = $(this).val();
		});
		
		$("#memSeq").val(seqs.join(","));
		
		ajaxFormExecute("noticeFrm", "/join/ajaxUserApprove", "post", false, false, ajaxReturnApprove);
	}
	
	function ajaxReturnApprove(data){
		if(data.sw){
    		jsAlert.alert("알림", "승인되었습니다.", null, fn_list, "확인");
    	}else{
    		jsAlert.alert("알림", "승인에 실패하였습니다.<br>관리자에게 문의바랍니다.");
    	}
	}
	
	function fn_delayYn(){
		var seqs = [];
		$("input[name=chk]:checked").each(function() {
			seqs[seqs.length] = $(this).val();
		});
		
		$("#memSeq").val(seqs.join(","));
		
		ajaxFormExecute("noticeFrm", "/join/ajaxUserDelay", "post", false, false, ajaxReturnDelay);
	}
	
	function ajaxReturnDelay(data){
		if(data.sw){
    		jsAlert.alert("알림", "반려되었습니다.", null, fn_list, "확인");
    	}else{
    		jsAlert.alert("알림", "반려에 실패하였습니다.<br>관리자에게 문의바랍니다.");
    	}
	}
	
	function ready(){
		$('ul.tabs li').click(function(){
			var tab_id = $(this).attr('data-tab');

			$('ul.tabs li').removeClass('current');
			$('.tab-content').removeClass('current');

			$(this).addClass('current');
			$("#"+tab_id).addClass('current');
		})
	}
	
</script>

<style>
ul.tabs{
	margin: 0px;
	padding: 0px;
	list-style: none;
}

ul.tabs li{
	background: none;
	color: #222;
	display: inline-block;
	padding: 10px 15px;
	cursor: pointer;
}

ul.tabs li.current{
	background: #ededed;
	color: #222;
}

.tab-content{
	display: none;
	background: #ededed;
	padding: 15px;
}

.tab-content.current{
	display: inherit;
}
</style>


<div class="right_col" role="main">
	<div class="">
    	<div class="page-title">
  			<div class="title_left">
  				<ul class="tabs">
  					<li class="tab-link current" data-tab="tab-1"><a href='/join/admin'>승인 대기</a></li>
					<li class="tab-link" data-tab="tab-2"><a href='/join/delay'>반려</a></li>
  				</ul>
			</div>
		</div>
	
		<div class="clearfix"></div>

		<div id="tab-1" class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
					
						<form class="form-inline" name="noticeFrm" id="noticeFrm" method="post" >
							<input type="hidden" id="memSeq" name="memSeq" value="" />
						</form>
						
						<td class="">총 ${pageInfo.rowMax}건</td>
					
						<div class="table-responsive">
							<table class="table table-striped jambo_table bulk_action">
								<thead>
									<tr class="headings">
										<th class="column-title"><input type="checkbox" id="checkAll" name="checkAll" /></th>
										<th class="column-title">NO</th>
										<th class="column-title">가입일 </th>
										<th class="column-title">ID</th>
										<th class="column-title">국가</th>
										<th class="column-title">TimeZone</th>
										<th class="column-title">Telegram ID</th>
										<th class="column-title">래퍼럴 코드</th>
									</tr>
								</thead>
								<tbody>
									<#if resultList?has_content>
									<#assign rNum = pageInfo.rowMax - pageInfo.startRowNum + 1 />
									<#list resultList as rMap>
									<tr style="cursor:pointer;">
										<td class=""><input type="checkbox" name="chk" value="${rMap.memSeq!}" /></td>
										<td class="" >${rNum}</td>
										<td class="" >${rMap.adminDate!}</td>
										<td class="" >${rMap.id!}</td>
										<td class="" >${rMap.country!}</td>
										<td class="" >${rMap.timezone!}</td>
										<td class="" >${rMap.telegramId!}</td>
										<td class="" >${rMap.refCode!}</td>
									</tr>
									
									<#assign rNum = rNum - 1/>
									</#list>
									<#else>
										<tr>
											<td colspan="7"><strong>사용자가 존재하지 않습니다.</strong></td>
										</tr>
									</#if>
								</tbody>
							</table>
							
							<div class="row">
								<div class="col-md-4 col-sm-6 col-xs-6 text-left">
									<button type="button" class="btn btn-danger" onclick="fn_delay();">반려</button>
								</div>
								<div class="col-md-4 col-sm-6 col-xs-8 text-right">
									<button type="button" class="btn btn-secondary" onclick="fn_select();">전체선택/해제</button>
								</div>
								<div class="col-md-4 col-sm-6 col-xs-4 text-right">
									<button type="button" class="btn btn-primary" onclick="fn_approve();">승인</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<nav aria-label="Page navigation example" class="content-center text-center">
					<@pagingTag.Paging url="/join/admin" pageCount="${pageInfo.pageCount!}" rowCount="${pageInfo.rowCount!}" rowMax="${pageInfo.rowMax!}" nowPage="${pageInfo.nowPage!}" params="" />
				</nav>
			</div>
		</div>
	</div>
</div>