<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<meta http-equiv="X-UA-Compatible"content="IE=10; IE=9; IE=8; IE=7; IE=EDGE">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
		<meta http-equiv="expires" content="0">
		<title>流量符-最专业的淘宝刷流量平台，提升网站流量和排名，真实搜索流量，刷人气</title>
		<meta name="keywords" content="流量，流量符，刷流量，刷网站人气排名，淘宝流量，京东流量，手机淘宝流量，网店推广">
		<meta name="description" content="流量符是一个提供真实有效的搜索流量的网站，用于降低店铺转化率、提升网店排名，可刷淘宝天猫、京东流量，快速提升人气，安全稳定">
		<link rel="shortcut icon" type="image/x-icon" href="${ctx}/images/favicon.ico" media="screen">
		<script type="text/javascript" src="${ctx}/js/base.js"></script>
	  	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<link rel="stylesheet" href="${ctx}/static_shop/style/common.css">
		<link rel="stylesheet" href="${ctx}/static_shop/style/login.css">
		<link rel="stylesheet" href="${ctx}/static_shop/style/register.css">

	<style type="text/css">
	</style>
   
	<script type="text/javascript">
		function reset_passwd(){
			var password = $("#password").val().trim();
			var password_check = $("#password_check").val().trim();
			if(password == ""){
				alert("密码不能为空");
				scrollAndSelect("password");
			}
			if(password_check == ""){
				alert("密码不能为空");
				scrollAndSelect("password_check");
			}
			if(password != password_check){
				alert("两次密码不一致");
				scrollAndSelect("password_check");
			}
			$("#from").submit();
		}	
	</script>
	</head>
	<body>
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="#">重置登陆密码</a></div>
</div>
<div class="container">
    <div class="Shadowbox">
        <div class="Shadowboxp"></div>
        <div class="register">
            <div class="passwd-title">
            	<span>重置登录密码</span>
                <a href="${ctx}">返回登录</a>
            </div>
            <div class="inputBox ModifyEmail">
                <div class="inputMain">
                <form action="${ctx }/web/shop/reset_passwd_success" method="post" name="from" id="from">
                	<input type="hidden" name="userId" value="${userId}">
                	<input type="hidden" name="sign" value="${sign}">
                	<input type="hidden" name="time" value="${time}">
                    <div class="item clearfix">
                        <label class="tit">请输入您的新密码:</label>
                        <div class="inp">
                            <em class="inpbox" style="position: relative;">
                                <i class="icon-E"></i>                      
                                <input type="text" cname="one" placeholder="请输入新密码" id="password" name="password" class="txt placebox" regname="password" emptyerr="密码不能为空" value=""> 
							</em>
                            <!-- <span class="error">此邮箱未注册流量符网站</span> -->
                               
                        </div>
                    </div>
                    <div class="item clearfix">
                        <label class="tit">确认新密码:</label>
                        <div class="inp">
                            <em class="inpbox" style="position: relative;">
                                <i class="icon-E"></i>                      
                                <input type="text" cname="one" placeholder="确认新密码" id="password_check" name="password_check" class="txt placebox" regname="password_check" emptyerr="密码不能为空" value=""> 
							</em>
                        </div>
                    </div>
					<p class="item Emailsub">
                        <a href="javascript:reset_passwd();" class="Emailsubbtn">确认修改</a>
                    </p>
                    </form>
                 </div>
            </div>
            
        </div>
        <div class="Shadowboxb"></div>
    </div>
</div>
			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>
	</body>
</html>