<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>模拟商户--订单退款页面</title>
</head>
<body>

<div class="nav">
    <span class="arrow goback"><em></em></span>

    <h1>退款请求</h1>
</div>
<div class="nav-wrap"></div>

<div class="grid">

<form method="post" action="${ctx }/refundOrder.htm" id="paySignForm">

    <ul class="form-wrap" id="J-form-wrap">

        <li class="form-item form-item-border clearfix">
            <label>接口版本</label>
			  <input type="text" class="" name="version" value="1.0" minlength="15" maxlength="18" />
        </li>

        <li class="form-item form-item-border clearfix">
            <label>商户号</label>
			<input type="text" class="" name="merchantNum" value="${merchantNum}" placeholder="请输入商户号" minlength="15" maxlength="50"/>
        </li>

        <li class="form-item form-item-border clearfix">
            <label>交易号</label>
            <input type="text" class="" name="tradeNum" value="" placeholder="请输入交易号" minlength="15" maxlength="50" />
        </li>

        <li class="form-item form-item-border clearfix">
            <label>原交易号</label>
            <input type="text" class="" name="oTradeNum" value="" placeholder="请输入原交易号" minlength="15" maxlength="50"/>
        </li>

        <li class="form-item form-item-border clearfix">
            <label>交易日期</label>
			<input type="text" class="" name="tradeDate" value="<fmt:formatDate value="${nowTime}" pattern="yyyyMMdd"/>"
                       placeholder="请输入交易日期" minlength="15" maxlength="50" />
        </li>

        <li class="form-item form-item-border clearfix">
            <label>交易时间</label>
			<input type="text" class="" name="tradeTime" value="<fmt:formatDate value="${nowTime}" pattern="HHmmss"/>" 
                       placeholder="请输入交易时间" minlength="15" maxlength="50" />
        </li>


        <li class="form-item form-item-border clearfix">
            <label>交易金额</label>
            <input type="text" class="" name="tradeAmount" value="1" autocomplete="off"
                       placeholder="请输入交易金额" minlength="15" maxlength="50" data-callback="input.status"/>
               
        </li>

        <li class="form-item form-item-border clearfix">
            <label>货币种类</label>
			<input type="text" class="" name="tradeCurrency" value="CNY" autocomplete="off"
                       placeholder="请输入交易币种" minlength="15" maxlength="50" data-callback="input.status"/>
               
        </li>

        <li class="form-item form-item-border clearfix">
            <label>异步通知</label>
            <input type="text" class="" name="tradeNotice" value=""
                       autocomplete="off" placeholder="请输入异步通知地址" minlength="15" maxlength="200"
                       data-callback="input.status"/>
               
        </li>

        <li class="form-item form-item-border clearfix">
            <label>交易备注</label>
            <input type="text" class="" name="tradeNote" value=""
                       autocomplete="off" placeholder="交易备注" minlength="15" maxlength="200"
                       data-callback="input.status"/>  <input type="submit" value="退款">
        </li>

        
    </ul>
</form>
</div>
</body>
</html>
