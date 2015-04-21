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
            	<h1>新手指南<span> —如何找回密码？</span></h1>
                <h2>第一步：点击【忘记密码？】</h2>
                <img src="${ctx}/static_shop/images/help_center/pwd1.jpg">
                <h2>第二步：输入在流量符网站注册时填写的邮箱，输入验证码，点击【下一步】</h2>
                <img src="${ctx}/static_shop/images/help_center/pwd2.jpg">&gt;
                <h2>第三步：请到邮箱查收确认邮件，点击相应链接完成验证。如果10分钟内未收到验证邮件，请点击【重新发送邮件】</h2>
                <img src="${ctx}/static_shop/images/help_center/pwd3.jpg">
                <h2>第四步：进入邮箱，点击链接，进行【重置密码】</h2>
                <img src="${ctx}/static_shop/images/help_center/pwd4.jpg">
                
                
                <h3>常见问题</h3>
                <p>问：忘记登录用户名怎么办？</p>
                <p>答：可以联系网站客服，提供注册时填写的邮箱、QQ号或者手机号，由客服帮您找回您的用户名。</p>
                
                                
        	</div>
       
        </div>
	</div>	
</div>

			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>


</body></html>