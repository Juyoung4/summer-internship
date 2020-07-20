<script src="/js/download.js"></script>
<script type="text/javascript">

function fn_event() {
	document.getElementById("test").background = "red"
}
</script>
	
<form class="form-inline" name="commentFrm" id="commentFrm" method="post" >
</form>

<div class="right_col" role="main">
	<div class="">
    	<div class="page-title">
  			<div class="title_left">
				<h3>댓글 테스트</h3>
			</div>
		</div>
	
		<div class="clearfix"></div>
		
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel board_top">
					<div class="x_title">
						<div class="row">
							<div class="col-md-8 col-sm-8 col-xs-8">
								<h4>1</h4>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-4 text-right">
								2
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
					<div class="x_content">
						4
					</div>
					<div class="text-right">
						<button type="button" data-target="#modaltest" data-toggle="modal">설정</button>
						
					</div>
				</div>
			</div>
		</div>
		
		<div class="clearfix"></div>
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
        <button type="button" id="test" class="btn btn-primary" onclick="fn_event()">적용</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
\