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

	function find_passwd(){
		$("#from").submit();
	}
	
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
            <div class="inputBox ModifyEmail">
                <div class="inputMain">
                <form action="${ctx }/web/shop/send_success" method="post" name="from" id="from">
                    <div class="item clearfix">
                        <label class="tit">请输入您注册的邮箱:</label>
                        <div class="inp">
                            <em class="inpbox" style="position: relative;">
                                <i class="icon-E"></i>                      
                                <input type="text" cname="one" placeholder="请输入邮箱地址" id="email" name="email" class="txt placebox" regname="email" emptyerr="邮箱地址不能为空" value=""> 
							</em>
                            <!-- <span class="error">此邮箱未注册流量符网站</span> -->
                               
                        </div>
                    </div>
					<div class="item clearfix">
						<label class="tit">
							<i>*</i>验证码:
						</label>
						<div class="inp">
							<em class="inpbox" style="position: relative;"> 
							<i class="icon-Y"></i> 
							<input name="code" style="width: 115px;" type="text" class="txt placebox" id="code" size="15" maxlength="6" cname="one" placeholder="请输入验证码" regname="code" emptyerr="验证码不能为空" value="">
							</em>
							<em style="cursor: pointer; vertical-align: middle; display: inline-block; margin-left: 5px;">
								<img src="${ctx}/web/shop/code" id="img" onclick="javascript:changeCode();" title="换一张" class="yz_img">
							</em>
							<a href="javascript:;"
								style="font-size: 12px; color: #07d; margin-left: 5px;"
								class="yz_a">看不清，换一张</a>
							<!-- <span class="error">验证码输入有误，请重新输入</span> -->
						</div>
					</div>
					<p class="item Emailsub">
                        <a href="javascript:find_passwd();" class="Emailsubbtn">确认修改</a>
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