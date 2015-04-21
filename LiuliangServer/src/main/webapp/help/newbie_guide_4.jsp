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
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href=${ctx }>首页</a> &gt; <a href="${ctx}/help/help_center.jsp?left_list_id=-1">新手指南</a></div>
</div>
    <div class="wrap clearfix"> 
    
	<jsp:include page='help_content_left.jsp' flush="true"></jsp:include>
			<div class="newbie-guide-right">
    	<div class="business-right-comm">        	
        	<div class="guide-help-center">
            	<h1>新手指南<span> —如何购买会员？</span></h1>
                <h2>第一步：请先登录</h2>
                <h2>第二步：点击右上顶部【开通会员】——选择【开通时间】 和 【选择支付方式】——【确认付款】;</h2>
                <img src="${ctx}/static_shop/images/help_center/pay_vip1.jpg">
                <h2>第三步：付款完成后，返回页面点击【已完成付款】即可</h2>
                <img src="${ctx}/static_shop/images/help_center/pay_vip2.jpg">
                <h2>第四步：购买会员成功</h2>
                <img src="${ctx}/static_shop/images/help_center/pay_vip3.jpg">
                
                <h3>常见问题</h3>
                <p>问：你们的会员费是怎么收的呀？</p>
                <p>答：商家最低购买周期为三个月；可购买会员时长分为：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第一档：3个月60元；<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第二档：6个月120元；<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第三档：12个月216元（9.5折）；<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第四档：24个月398元（8.3折）；<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性购买的VIP会员时间越长，优惠越多哦。</p>
                <br>
                <p>问：VIP会员到期了，能不能用账户里的积分抵扣续费会员？</p>
                <p>答：不可以的，商家购买/续费VIP会员只能使用网银在线支付或联系客服使用支付宝转账方式；暂时不支持积分兑换方式。</p>
                <br>
                <p>问：网站的付款方式除了网银支付外，还可以支付宝转账吗？</p>
                <p>答：暂时不支持支付宝付款接口；建议您使用网银在线支付，若是只能使用支付宝的话，麻烦联系在线客服的QQ：800036159。</p>
                
        	</div>
       
        </div>
	</div>	
</div>

			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>


</body></html>