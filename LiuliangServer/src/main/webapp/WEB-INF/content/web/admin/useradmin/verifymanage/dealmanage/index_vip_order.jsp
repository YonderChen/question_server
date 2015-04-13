<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<jsp:include page="../../../include/style.jsp" flush="true"></jsp:include>
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
		var url = "${ctx}/web/admin/useradmin/verifymanage/dealmanage/list_vip_order";
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
			},
			success : function(result) {
				if (!isOutTime(result)) {
					$("#searchBtn").button('reset');
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
					账户管理
				</li>
				<li>
					会员续费列表
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
					<button id="searchBtn" class="btn btn-info" onclick="ajaxSearch(1);" data-toggle="button" data-loading-text="<i class='icon-search'></i>&nbsp;搜索中..."><i class="icon-search"></i>&nbsp;搜索</button>
				</li>

			</ul>
			<div id="ajaxSearchId">
			
			</div>
		</div>
		
	</body>
</html>