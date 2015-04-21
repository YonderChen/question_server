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
            	<h1>新手指南<span> —如何对已完成的任务进行一键发布？</span></h1>
                <h2>第一步：请先登录</h2>
                <h2>第二步：如下图：点击进入【个人中心】，下拉至【任务管理】，点击按钮【一键发布】即可发布任务</h2>
                <img src="${ctx}/static_shop/images/help_center/task_yjfb1.jpg">
                <h2>第三步：【填写流量信息】<br>按照图中要求正确填写相应资料，信息填写正确后，点击【下一步】</h2>
                <img src="${ctx}/static_shop/images/help_center/task_yjfb2.jpg">
                <h2>第四步：选择【增值服务】<br>按照图中要求正确填写相应资料，信息填写正确后，点击【立即发布】</h2>
                <img src="${ctx}/static_shop/images/help_center/task_yjfb3.jpg">
                <h2>第五步：任务发布成功后，显示【支付成功】提示</h2>
                <img src="${ctx}/static_shop/images/help_center/task_yjfb4.jpg">

                
        	</div>
       
        </div>
	</div>
</div>

			<jsp:include page='/include/footer.jsp' flush="true"></jsp:include>


</body></html>