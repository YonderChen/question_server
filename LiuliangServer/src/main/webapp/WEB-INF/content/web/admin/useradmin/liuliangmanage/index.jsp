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
				name : $("#name").val().trim()
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
						用户名:
					</label>
					<input name="username" id="username" type="text" class="span2" placeholder="用户名"/>
				</li>
				<li>
					<label>
						姓名:
					</label>
					<input name="name" id="name" type="text" class="span2" placeholder="姓名"/>
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