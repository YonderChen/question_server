<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<jsp:include page="../include/style.jsp" flush="true"></jsp:include>
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
		var url = "${ctx}/web/admin/apptextimage/list";
		$.ajax( {
			url : url,
			type : 'post',
			data : {
				page : page,
				pageSize : pageSize,
				id : $("#record_id").val().trim(),
				ownerId : $("#owner_id").val().trim()
			},
			dataType : 'text',
			timeout : 60000,
			error : function(e) {
				$("#ajaxSearchId").html("<div class='no-found'>连接服务器超时,请稍后再试.</div>");
				$("#searchBtn").button('reset');
				$(".active").removeClass('active');
			},
			success : function(result) {
				if (!isOutTime(result)) {
					$("#searchBtn").button('reset');
					$(".active").removeClass('active');
					$("#ajaxSearchId").html(result);
				}
			}
		});
	}
	
	function del(id) {
		$(".btn-cancel").button('loading');
		$(".btn-primary").button('loading');
		var url = "${ctx}/web/admin/apptextimage/del";
		$.ajax({
			url:url,
			type:'post',
			data:{
				id: id
			},
			dataType:'text',
			timeout:60000,
			error: function(e) {
				$(".btn-cancel").button('reset');
				$(".btn-primary").button('reset');
				alert("连接服务器超时,请稍后再试.");
			},
			success: function(result){
				if (!isOutTime(result)) {
					$(".btn-cancel").button('reset');
					$(".btn-primary").button('reset');
					result = eval("("+result+")");
					if (result.success) {
						alert(result.msg);
						ajaxSearch(null);
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
					功能目录
				</li>
				<li>
					APP管理
				</li>
				<li>
					文字图片管理
				</li>
			</ul>
		</div>

		<div class="rightinfo">
			<ul class="seachform">

				<li>
					<label>
						记录id:
					</label>
					<input name="record_id" id="record_id" type="text" class="span2" placeholder="记录id"/>
				</li>
				<li>
					<label>
						作者uid:
					</label>
					<input name="owner_id" id="owner_id" type="text" class="span2" placeholder="作者uid"/>
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
		
		
		<div id="showImg" class="modal hide fade" style="max-width: 560px; min-width: 80px; width: auto;" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true" style="z-index:100000;position: absolute;" data-backdrop="static">
	           <img id="showImg_img" />  
	        <div style="display: none;">
				<button id="closeShowButton" class="btn btn-cancel" data-dismiss="modal" aria-hidden="true" data-toggle="button" data-loading-text="关闭">关闭</button>
			</div>
	    </div>
	</body>
</html>