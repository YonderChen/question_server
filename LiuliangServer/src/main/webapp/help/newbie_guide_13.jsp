<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
    <meta http-equiv="expires" content="0">
	<title>帮助中心-流量符</title>
    <meta name="keywords" content="流量符帮助中心，如何刷流量，流量，提升店铺排名，权重、淘宝流量、京东流量、手机淘宝流量">
    <meta name="description" content="流量符帮助中心，帮助快速提升店铺销量，提高店铺权重，流量推广好助手">
	<link rel="shortcut icon" type="image/x-icon" href="../images/favicon.ico" media="screen">
	<script type="text/javascript" src="../js/base.js"></script>
	<script type="text/javascript" src="../js/jquery.js"></script>
    <link rel="stylesheet" href="../static_shop/style/common.css">
    <link rel="stylesheet" href="../static_shop/style/help_center.css">
</head>
<body>
			<jsp:include page="/include/top_unlogin.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href=${ctx }>首页</a> &gt; <a href="${ctx}/help/help_center.jsp?left_list_id=-1">新手指南</a></div>
</div>
    <div class="wrap clearfix"> 
    
	<jsp:include page='help_content_left.jsp' flush="true"></jsp:include>
			<div class="newbie-guide-right">
    	<div class="business-right-comm">        	
        	<div class="guide-help-center">
            	<h1>新手指南<span> —如何发布京东任务？</span></h1>
                <h2>第一步：如下图：点击【发布流量任务】，进入发布流量任务页面点击【选择平台】（淘宝、天猫、<span class="red">京东</span>）；也可通过【个人中心】，点击【发布任务】</h2>
                <h2>第二步：【选择店铺】</h2>
                
                <h2>第三步：【选择流量类型】，点击【下一步】</h2>
                <img src="${ctx}/static_shop/images/help_center/task1jd.jpg">
                <h2>第四步：填写【流量信息】<br>按照图中要求正确填写相应资料，信息填写正确后，点击【下一步】</h2>
                <img src="${ctx}/static_shop/images/help_center/task2jd.jpg">
                <h2>第五步：选择【增值服务】<br>按照图中要求正确填写相应资料，信息填写正确后，点击【立即发布】</h2>
                <img src="${ctx}/static_shop/images/help_center/task3jd.jpg">
                <h2>第六步：发布任务成功，显示【支付成功】提示</h2>
                <img src="${ctx}/static_shop/images/help_center/task4jd.jpg">
                
        	</div>
       
        </div>
	</div>
</div>

			<jsp:include page='/include/footer.jsp' flush="true"></jsp:include>


</body></html>