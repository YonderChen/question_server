<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
	<head>
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
	function login() {
		if ($("#username").val().trim() == "") {
			alert("用户名不能为空.");
			$("#username").select();
			return;
		}
		if ($("#password").val().trim() == "") {
			alert("密码不能为空.");
			$("#password").select();
			return;
		}
		/*
		if ($("#code").val().trim() == "") {
			alert("验证码不能为空.");
			$("#code").select();
			return;
		}
		*/
		var url = "${ctx}/web/shop/login";
		$.ajax({
			url:url,
			type:'post',
			data:{
				username:$("#username").val().trim(),
				password:$("#password").val().trim(),
				code:$("#code").val().trim()
			},
			dataType:'json',
			timeout:60000,
			error: function(e) {
				alert("连接服务器超时,请稍后再试.");
			},
			success: function(result){
				if (result.success) {
					window.location.href = result.redirectUrl;
				} else {
					changeCode();
					$("#code").val("");
					alert(result.msg);
				}
			}
		});
	}
	
	function identifyLogin() {
		var url = "${ctx}/web/shop/identify";
		$.ajax({
			url:url,
			type:'post',
			data:{
				
			},
			dataType:'json',
			timeout:60000,
			error: function(e) {alertError("连接服务器超时,请稍后再试.");},
			success: function(result){
				if (result.success) {
					window.location.href=result.redirectUrl;
				}
			}
		});
	}
	
	$('document').ready(function() {
		identifyLogin();
	});
	
	function changeCode() {
		document.getElementById("img").src="${ctx}/web/shop/code?num="+Math.random();
	}
	
	</script>
	</head>
	<body>
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="#">找回登陆密码</a></div>
</div>
<div class="container">
    <div class="Shadowbox">
        <div class="Shadowboxp"></div>
        <div class="register">
            <div class="passwd-title">
            	<span>找回登录密码</span>
                <a href="${ctx}">返回登录</a>
            </div>
            
            <div class="inputBox">
            	<div class="pwdtwo">
                	<h3>请到邮箱查收确认邮件，单击相应链接完成验证（48小时有效）</h3>
                    <p>未收到邮件？</p>
                    <p>请先检查是否在垃圾邮件中</p>
                    <p>十分钟内未收到验证邮件，请点击&nbsp;&nbsp;<a href="/user/find_passwd">重新发送邮件</a></p>
                </div>
            	
            </div>
            
        </div>
        <div class="Shadowboxb"></div>
    </div>
</div>
			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>
	</body>
</html>