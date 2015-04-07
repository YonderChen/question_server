<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>网银+</title>
</head>
<body>

<div class="nav">
    <span class="arrow goback"><em></em></span>

    <h1>支付结果</h1>
</div>
<div class="nav-wrap"></div>

<div class="grid">

    <div class="noticeWrap grid94">
        支付结果: ${errorMsg}
        <br/>
    </div>


</div>


<!--submit btn start-->
<div class="grid94">
    <a href="javascript:history.go(-1);" id="J-next-btn" class="btn btn-actived mt15">返回</a>
</div>
<!--submit btn end-->

</body>
</html>
