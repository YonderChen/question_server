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
            	<h1>新手指南<span> —如何购买积分？</span></h1>
                <h2>第一步：请先登录</h2>
                <h2>第二步：进入购买积分页面有4种途径，如下图：点击右上顶部【购买积分】①；点击导航栏【购买积分】②；点击进入【个人中心】，点击【续费会员】②③都可进入续费页面</h2>
                <img src="${ctx}/static_shop/images/help_center/pay_integral1.jpg">
                <h2>第三步：如下图：选择【请选择积分】和【选择支付方式】；</h2>
                <h2>第四步：如下图：点击【立即购买】；</h2>
                <img src="${ctx}/static_shop/images/help_center/pay_integral2.jpg">
                <h2>第五步：第X步骤 付款完成以后，返回页面点击【已完成付款】即可；</h2>
                <img src="${ctx}/static_shop/images/help_center/pay_integral3.jpg">
                
                <h3>常见问题</h3>
                <p>问：购买积分是什么样的评分标准？</p>
                <p>答：支付 1,000 元，总计获得: 10,000 积分（即1访客=0.3元）<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支付 2,000 元，总计获得: 21,818 积分（即1访客=0.275元）<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支付 5,000 元，总计获得: 60,000 积分（即1访客=0.25元）<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支付 10,000 元，总计获得: 133,333 积分 （即1访客=0.225元）<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支付 20,000 元，总计获得: 300,000 积分 （即1访客=0.2元）<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支付 30,000 元，总计获得: 450,000 积分 （即1访客=0.2元）<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性购买越多积分，折扣越大哦。</p>
                <br>
                <p>问：购买的积分在网站有哪些用途？</p>
                <p>答：发布流量任务、购买方可流量（1访客大约是0.2积分）、续费VIP会员。</p>
                <br>
                <p>问：积分用不掉的话，可以提现吗？</p>
                <p>答：不可以提现哦。平台暂时没有开发此项功能，积分一经购买，就不予提现了哦。</p>
                <br>
                <p>问：在哪里查积分使用记录？</p>
                <p>答：登陆您的流量符个人账号，在【个人中心】—【积分记录】里可以看到您的积分使用记录哦。</p>
                
                
        	</div>
       
        </div>
	</div>
</div>

			<jsp:include page='/include/footer.jsp' flush="true"></jsp:include>


</body></html>