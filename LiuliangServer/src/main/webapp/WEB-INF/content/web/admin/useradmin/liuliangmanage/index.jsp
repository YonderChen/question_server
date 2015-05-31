<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<jsp:include page="../../include/style.jsp" flush="true"></jsp:include>
  		<script type="text/javascript" src="${ctx}/js/showImg.js"></script>
	<script type="text/javascript">
	function preFunArray() {
		var funs = new Array();
		funs.push(loadData);
		return funs;
	}
	
	function loadData() {
		ajaxSearch(1);
	}
	
	function ajaxSearch(page) {
		if (page == null) {
			page = $("#currentPage").val().trim();
		}
		var pageSize = 10;
		if ($("#pageSize").val() != null) {
			pageSize = $("#pageSize").val().trim();
		}
		$("#searchBtn").button('loading');
		$("#ajaxSearchId").html("<div class='no-found'>加载中...</div>");
		var url = "${ctx}/web/admin/useradmin/liuliangmanage/list";
		$.ajax( {
			url : url,
			type : 'post',
			data : {
				page : page,
				pageSize : pageSize,
				username : $("#username").val().trim(),
				bindName : $("#bindName").val().trim(),
				taskId : $("#taskId").val().trim(),
				doStatus : $("#doStatus").val().trim(),
				beginTime : $("#beginTime").val().trim(),
				endTime : $("#endTime").val().trim()
			},
			dataType : 'text',
			timeout : 60000,
			error : function(e) {
				$("#ajaxSearchId").html("<div class='no-found'>连接服务器超时,请稍后再试.</div>");
				$("#searchBtn").button('reset');
				$(".active").removeClass('active');
			},
			success : function(result) {
				$("#searchBtn").button('reset');
				$(".active").removeClass('active');
				if (!isOutTime(result)) {
					$("#ajaxSearchId").html(result);
				}
			}
		});
	}
	
</script>
	</head>
	<body>
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					流量管理
				</li>
				<li>
					流量记录
				</li>
			</ul>
		</div>

		<div class="rightinfo">
			<ul class="seachform">

				<li>
					<label>
						用户名:
					</label>
					<input name="username" id="username" type="text" class="span2" placeholder="用户名"/>
				</li>
				<li>
					<label>
						店铺名称:
					</label>
					<input name="bindName" id="bindName" type="text" class="span2" placeholder="店铺名称"/>
				</li>
				<li>
					<label>
						任务编号:
					</label>
					<input name="taskId" id="taskId" type="text" class="span2" placeholder="任务编号"/>
				</li>
				<li>
					<label>
						状态:
					</label>
				    <select id="doStatus" name="doStatus" class="span2">
						<option value="" selected="selected">请选择</option>
						<option value="0" >添加失败</option>
						<option value="1" >待处理</option>
						<option value="2" >处理中</option>
						<option value="3" >已完成</option>
					</select>
				</li>
				<li>
					<label>
						日期:
					</label>
                    <input type="text" class="span2" style="width:100px;" name="beginTime" id="beginTime" value="" onclick="javascript:WdatePicker()">
                    -
                    <input type="text" class="span2" style="width:100px;" name="endTime" id="endTime" value="" onclick="javascript:WdatePicker()">
					
				</li>
				<li>
					<label>
						&nbsp;
					</label>
					<button id="searchBtn" class="btn btn-info" onclick="javascript:ajaxSearch(1);" data-toggle="button" data-loading-text="<i class='icon-search'></i>&nbsp;搜索中..."><i class="icon-search"></i>&nbsp;搜索</button>
				</li>

			</ul>
			<div id="ajaxSearchId">
			
			</div>
		</div>
		
	
	<div id="detailModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="detailModalLabel" aria-hidden="true" style="z-index:100000;position: absolute;" data-backdrop="static">
		<div class="modal-header">
			<button type="button" class="close btn-cancel" data-dismiss="modal" aria-hidden="true" data-toggle="button" data-loading-text="×">×</button>
		<h3 id="detailModalLabel">任务详情</h3>
		</div>
		<div class="modal-body" id="detailDiv">
		
		</div>
		<div class="modal-footer">
		<button class="btn btn-cancel" data-dismiss="modal" aria-hidden="true" data-toggle="button" data-loading-text="关闭">关闭</button>
		</div>
	</div>
		
	<div id="showImg" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true" style="z-index:100000;position: absolute;" data-backdrop="static">
           <img id="showImg_img" />  
        <div style="display: none;">
			<button id="closeShowButton" class="btn btn-cancel" data-dismiss="modal" aria-hidden="true" data-toggle="button" data-loading-text="关闭">关闭</button>
		</div>
    </div>
	</body>
</html>