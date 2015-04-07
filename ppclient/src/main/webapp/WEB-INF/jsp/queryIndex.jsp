<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>模拟商户--交易查询</title>
</head>
<body>
<div class="nav">
    <span class="arrow goback"><em></em></span>

    <h3>交易查询</h3>
</div>
<div class="nav-wrap"></div>

<div class="grid">

<form method="post" action="${ctx}/queryOrder.htm" id="queryTradeForm">

    <ul class="form-wrap" id="J-form-wrap">
    	<li class="form-item form-item-border clearfix">
            <label>接口版本:</label>
         	<input type="text" class="" name="version" value="1.0" minlength="15" maxlength="18" data-callback="input.status"/>

        </li>
        <li class="form-item form-item-border clearfix">
            <label>商户号:  </label>
			<input type="text" class="" name="merchantNum" value="${merchantNum}" placeholder="请输入商户号" minlength="15" maxlength="50"/>
            
        </li>


        <li class="form-item form-item-border clearfix">
            <label>交易号:  </label>
            <input type="text" class="" name="tradeNum" value=""placeholder="请输入交易号" minlength="15" maxlength="50"/>   <input type="submit" value="查询">
        </li>      
    </ul>
</form>
</div>
</body>
</html>
