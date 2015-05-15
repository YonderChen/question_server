<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<jsp:include page="../../include/style.jsp" flush="true"></jsp:include>
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
		var url = "${ctx}/web/admin/useradmin/taskmanage/list";
		$.ajax( {
			url : url,
			type : 'post',
			data : {
				page : page,
				pageSize : pageSize,
				username : $("#username").val().trim(),
				bindName : $("#bindName").val().trim(),
				beginTime : $("#beginTime").val().trim(),
				endTime : $("#endTime").val().trim(),
				bindPlat : $("#bindPlat").val().trim(),
				status : $("#status").val().trim(),
				taskId : $("#taskId").val().trim()
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
	
	function check_task(taskId){
		var url = "${ctx}/web/admin/useradmin/taskmanage/check_task";
		$.ajax( {
			url : url,
			type : 'post',
			data : {
				taskId : taskId,
			},
			dataType : 'text',
			timeout : 60000,
			error : function(e) {
				alert("审核失败，连接异常");
			},
			success : function(result) {
				if (!isOutTime(result)) {
					result = eval("("+result+")");
					if (result.success) {
						$("#task_status_"+taskId).html("执行中");
						$("#check_a_"+taskId).remove();
						alert("审核成功");
					} else {
						alert(result.msg);
					}
				}
			}
		});
	}
	
	function showImg(photo_url) {
		$('#showImg').modal('show');
		//设置显示放大后的图片位置  
		$("#showImg").find("img").attr("src", photo_url);  
		//单击放大后的图片消失  
		$("#showImg").click(function(){  
			$('#showImg').modal('hide');
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
					任务列表
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
						店铺名称:
					</label>
					<input name="bindName" id="bindName" type="text" class="span2" placeholder="店铺名称"/>
				</li>
				<li>
					<label>
						状态:
					</label>
				    <select id="status" name="status" class="span2">
						<option value="-1" selected="selected">请选择</option>
						<option value="0">未发布</option>
						<option value="1">待审核</option>
						<option value="2">执行中</option>
						<option value="3">已完成</option>
						<option value="4">已取消</option>
						<option value="5">审核不通过</option>
						<option value="6">任务修改，待审核</option>
					</select>
				</li>
				<li>
					<label>
						任务编号:
					</label>
					<input name="taskId" id="taskId" type="text" class="span2" placeholder="任务编号"/>
				</li>
				<li>
					<label>
						创建日期:
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
			<ul class="seachform">
				<li>
					<label>
						平台:
					</label>
				    <select id="bindPlat" name="bindPlat" class="span2">
						<option value="" selected="selected">请选择</option>
						<option value="taobao" >淘宝</option>
						<option value="tmall" >天猫</option>
						<option value="jd" >京东</option>
					</select>
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