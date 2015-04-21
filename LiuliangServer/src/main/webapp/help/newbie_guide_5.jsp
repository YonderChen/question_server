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
            	<h1>新手指南<span> —如何续费会员？</span></h1>
                <h2>第一步：请先登录</h2>
                <h2>第二步：进入会员续费页面有3种途径，如图：点击右上顶部【续费会员】①；或者点击进入【个人中心】，点击【续费会员】②③都可进入续费页面</h2>
                <img src="${ctx}/static_shop/images/help_center/renew_vip1.jpg">
                <h2>第三步：选择【续费时长】和【选择支付方式】；</h2>
                <h2>第四步：确认付费后，点击【确认付款】；若暂无需续费，可点击【跳过此步骤】</h2>
                <img src="${ctx}/static_shop/images/help_center/renew_vip2.jpg">
                <h2>第五步：付款完成后，返回页面点击【已完成付款】即可</h2>
                <img src="${ctx}/static_shop/images/help_center/renew_vip3.jpg">
                
                <h3>常见问题</h3>
                <p>问：会员到期不续费了，账户里还有少量积分没用完，可以继续发布任务把积分消耗掉吗？</p>
                <p>答：不可以的，只有VIP会员才可以发布流量任务，所以必须再次购买成为VIP会员，才能继续发布任务。</p>
                
        	</div>
       
        </div>
	</div>	
</div>

			<jsp:include page='/include/footer.jsp' flush="true"></jsp:include>


</body></html>