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
		var url = "${ctx}/web/admin/useradmin/dealmanage/list_score_order";
		$.ajax( {
			url : url,
			type : 'post',
			data : {
				page : page,
				pageSize : pageSize,
				username : $("#username").val().trim(),
				beginTime : $("#beginTime").val().trim(),
				endTime : $("#endTime").val().trim(),
				status : $("#status").val().trim()
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
	
	function editInput(orderId) {
		$('#editModal').modal('show');
    	$(".btn-cancel").button('loading');
		$(".btn-primary").button('loading');
		$("#editDetail").html("<div class='no-found'>加载中...</div>");
		var url = "${ctx}/web/admin/useradmin/dealmanage/edit_score_order_input";
		$.ajax( {
			url : url,
			type : 'post',
			data : {
				orderId: orderId
			},
			dataType : 'text',
			timeout : 60000,
			error : function(e) {
				$("#editDetail").html("<div class='no-found'>连接服务器超时,请稍后再试.</div>");
				$(".btn-cancel").button('reset');
				$(".btn-primary").button('reset');
				$(".active").removeClass('active');
			},
			success : function(result) {
				$(".btn-cancel").button('reset');
				$(".btn-primary").button('reset');
				$(".active").removeClass('active');
				if (!isOutTime(result)) {
					$("#editDetail").html(result);
				}
			}
		});
	}
	
	function edit() {
		$(".btn-cancel").button('loading');
		$(".btn-primary").button('loading');
		var url = "${ctx}/web/admin/useradmin/dealmanage/edit_score_order";
		$.ajax({
			url:url,
			type:'post',
			data:{
				orderId : $("#orderId").val(),
				num : $("#e_num").val(),
				status : $("#e_status").val()
			},
			dataType:'text',
			timeout:60000,
			error: function(e) {
				$(".btn-cancel").button('reset');
				$(".btn-primary").button('reset');
				$(".active").removeClass('active');
				alert("连接服务器超时,请稍后再试.");
			},
			success: function(result){
				$(".btn-cancel").button('reset');
				$(".btn-primary").button('reset');
				$(".active").removeClass('active');
				if (!isOutTime(result)) {
					result = eval("("+result+")");
					if (result.success) {
						$('#editModal').modal('hide');
						alert(result.msg);
						ajaxSearch(null);
					} else {
						alert(result.msg);
					}
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
					账户管理
				</li>
				<li>
					购买积分列表
				</li>
			</ul>
		</div>

		<div class="rightinfo">
			<ul class="seachform">
				<li>
					<label>
						平台账号:
					</label>
					<input name="username" id="username" type="text" class="span2" placeholder="平台账号"/>
				</li>
				<li>
					<label>
						创建时间:
					</label>
                    <input type="text" class="span2" style="width:100px;" name="beginTime" id="beginTime" value="" onclick="javascript:WdatePicker()">
                    -
                    <input type="text" class="span2" style="width:100px;" name="endTime" id="endTime" value="" onclick="javascript:WdatePicker()">
					
				</li>
				<li>
					<label>
						状态:
					</label>
				    <select id="status" name="status" class="span2">
						<option value="-1" selected="selected">请选择</option>
						<option value="0">待审核</option>
						<option value="1">审核通过</option>
						<option value="2">审核失败</option>
					</select>
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
		
	<div id="editModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true" style="z-index:100000;position: absolute;" data-backdrop="static">
		<div class="modal-header">
			<button type="button" class="close btn-cancel" data-dismiss="modal" aria-hidden="true" data-toggle="button" data-loading-text="×">×</button>
		<h3 id="editModalLabel">编辑</h3>
		</div>
		<div class="modal-body" id="editDetail">
		
		</div>
		<div class="modal-footer">
		<button id="edit_order_button" class="btn btn-primary" data-toggle="button" data-loading-text="保存" onclick="javascript:edit();">保存</button>
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