<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<jsp:include page="../include/style.jsp" flush="true"></jsp:include>
	<script type="text/javascript">
	function getSign(){
		if($("#command").val().trim() == ""){
		 	alert("command不能为空");
		 	$("#command").select();
		 	return ;
		}
		$("#getSign_button").button('loading');
		var url = "${ctx}/web/admin/appapitest/get_sign";
		$.ajax( {
			url : url,
			type : 'post',
			data : {
				command:$("#command").val().trim(),
				version:$("#version").val().trim(),
				root:$("#root").val().trim()
			},
			dataType : 'text',
			timeout : 60000,
			error : function(e) {
				$("#getSign_button").button('reset');
				$(".active").removeClass('active');
				alert("连接服务器超时,请稍后再试.");
			},
			success : function(result) {
				if (!isOutTime(result)) {
					$("#getSign_button").button('reset');
					$(".active").removeClass('active');
					result = eval("("+result+")");
					if(result.success){
						$("#sign").val(result.msg);
					} else {
						alert(result.msg);
					}
				}
			}
		});
	}
	
	function sendCommand(){
		if($("#command").val().trim() == ""){
		 	alert("command不能为空");
		 	$("#command").select();
		 	return ;
		}
		if($("#sign").val().trim() == ""){
		 	alert("sign不能为空");
		 	$("#sign").select();
			return ;
		}
		$("#sendCommand_button").button('loading');
		$("#api_test_form").ajaxSubmit({
			error : function(e) {
				$("#sendCommand_button").button('reset');
				$(".active").removeClass('active');
				alert("连接服务器超时,请稍后再试.");
			},
			success : function(result){
				$("#sendCommand_button").button('reset');
				$(".active").removeClass('active');
				$("#result").val(result);
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
					功能目录
				</li>
				<li>
					购物APP管理
				</li>
				<li>
					购物API测试
				</li>
			</ul>
		</div>

	<div class="rightinfo">
		<form id="api_test_form" action="${ctx}/rs/shopping/post" method="post" enctype="multipart/form-data">
			<div style="float: none; width: 1220px; height: 270px;">
				<div style="float: left;">
					<table border="1">
					  <tr>
					    <td align="right">[命令号, int] <em class="text-error" style="font-size:16px">*</em> <b>command</b>：</td>
					    <td><input name="command" id="command" type="text" class="span2" value="" placeholder="命令号"/></td>
					  </tr>
					  <tr>
					    <td align="right">[版本号, string] <b>version</b>：</td>
					    <td><input name="version" id="version" type="text" class="span2" value="" placeholder="版本号"/></td>
					  </tr>
					  <tr>
					    <td align="right">[应用参数, string(json)] <b>root</b>：</td>
					    <td>
					    	<textarea class="span3" name="root" id="root" style="width: 250px; height: 100px;" placeholder="应用参数"></textarea>
					    </td>
					  </tr>
					  <tr>
					    <td align="right">[数字签名, string] <em class="text-error"  style="font-size:16px">*</em> <b>sign</b>：</td>
					    <td><input name="sign" id="sign" type="text" class="span2" style="width: 250px" value="" placeholder="数字签名"/></td>
					  </tr>
					</table>
				</div>
				<div style="float: left; margin-left: 200px;">
					返回结果：
					<textarea class="span3" name="result" id="result" style="width: 450px; height: 200px;" placeholder="返回结果"></textarea>
				</div>
			</div>
		</form>
		<div>
			<button id="getSign_button" class="btn btn-primary" data-toggle="button" data-loading-text="获取中..." onclick="getSign();">获取sign</button>
			<button id="sendCommand_button" class="btn btn-primary" data-toggle="button" data-loading-text="调用中..." onclick="sendCommand();">调用API</button>
		</div>
	</div>
</body>
</html>