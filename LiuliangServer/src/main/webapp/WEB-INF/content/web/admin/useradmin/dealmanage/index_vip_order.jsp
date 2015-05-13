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
		var url = "${ctx}/web/admin/useradmin/dealmanage/list_vip_order";
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
	
	function check_vip_order(orderId, status) {
		var url = "${ctx}/web/admin/useradmin/dealmanage/check_vip_order";
		$.ajax( {
			url : url,
			type : 'post',
			data : {
				orderId : orderId,
				status : status
			},
			dataType : 'text',
			timeout : 60000,
			error : function(e) {
				alert("连接服务器超时,请稍后再试.");
				$(".btn").button('reset');
				$(".active").removeClass('active');
			},
			success : function(result) {
				$(".btn").button('reset');
				$(".active").removeClass('active');
				if (!isOutTime(result)) {
					result = eval("("+result+")");
					if (result.success) {
						var status_desc = "待审核";
						if(result.msg == "1"){
							status_desc = "审核通过";
						} else {
							status_desc = "审核失败";
						}
						$("#status_"+orderId).html(status_desc);
						$("#op_"+orderId).html("无");
						alert("操作成功");
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
					<button id="searchBtn" class="btn btn-info" onclick="javascript:ajaxSearch(1);" data-toggle="button" data-loading-text="<i class='icon-search'></i>&nbsp;搜索中..."><i class="icon-search"></i>&nbsp;搜索</button>
				</li>

			</ul>
			<div id="ajaxSearchId">
			
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