<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<title>"网银+"PC版demo</title>
<style type="text/css">
	html,body {
		padding: 0;
		margin: 0;
	}
</style>
</head>
<body>
	<form action="${ctx}/clientOrder.htm"  method="post" target="iframeLayer" id="payForm">
		version:<input type="txt" name="version" value="1.1"><br/>
		token:<input type="txt" name="token" value=""><br/>
		merchantNum:<input type="txt" name="merchantNum" value="${merchantNum}"><br/>
		merchantRemark:<input type="txt" name="merchantRemark" value="备注"><br/>
		tradeNum:<input type="txt" name="tradeNum" value="<%= System.currentTimeMillis() %>"><br/>
		tradeName:<input type="txt" name="tradeName" value="商品1111"><br/>
		tradeDescription:<input type="txt" name="tradeDescription" value="其它描述信息"><br/>
		tradeTime：<input type="txt" name="tradeTime" value="2014-06-25 19:51:12"><br/>
		tradeAmount:<input type="txt" name="tradeAmount" value="1"><br/>
		currency:<input type="txt" name="currency" value="CNY"><br/>
		successCallbackUrl:<input type="txt" name="successCallbackUrl" value="http://client.demo.com:8080${ctx}/success.htm"><br/>
		forPayLayerUrl:<input type="txt" name="forPayLayerUrl" value="http://client.demo.com:8080${ctx}/forPayLayer.html"><br/>
		notifyUrl:<input type="txt" name="notifyUrl" value="http://client.demo.com:8080${ctx}/asynNotify.htm"><br/>
		ip:<input type="txt" name="ip" value="10.45.251.153"><br/>
		<input type="submit" value="showlayer" id="showlayerButton">
	</form>
	<iframe id="iframeLayer" frameborder="0" name="iframeLayer" class="iframeLayer" allowTransparency="true" style="display:none;position:absolute; z-index:999; width:100%; height:100%; top: 0px; left: 0px; right:0; bottom: 0; background:url('img/loading.gif') center center no-repeat;" src=""></iframe>

<!-- 生成全局命名空间 window.WYPLUS -->
<script src="${ctx}/js/wyplus-ctrl.js"></script>	
<script>
	(function(){
		var submitBtn = document.getElementById('showlayerButton');
		submitBtn.onclick = function(){

			// 提交表单时调用 WYPLUS.open() 方法;
			// 需要传入的参数：
			// 1、formId (提交的表单ID)
			// 2、iframeId (嵌入的iframeID)

			WYPLUS.open({
				//需要提交的表单ID
				formId: 'payForm',
				//iframe ID
				iframeId: 'iframeLayer'
			});
		}
	})();
</script>
</body>
</html>