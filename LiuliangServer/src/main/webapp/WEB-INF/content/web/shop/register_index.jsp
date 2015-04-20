<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!-- saved from url=(0039)http://www.liuliangfu.com/user/register -->
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
	<link rel="stylesheet" href="${ctx}/static_shop/style/register.css">

	<script type="text/javascript">
	function register() {
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
		if ($("#password_confirm").val().trim() != $("#password").val().trim()) {
			alert("两次密码不一致.");
			$("#password_confirm").select();
			return;
		}
		if ($("#useremail").val().trim() == "") {
			alert("邮箱不能为空.");
			$("#useremail").select();
			return;
		}
		if ($("#userqq").val().trim() == "") {
			alert("QQ不能为空.");
			$("#userqq").select();
			return;
		}
		if ($("#usermobile").val().trim() == "") {
			alert("手机不能为空.");
			$("#usermobile").select();
			return;
		}
		
		var url = "${ctx}/web/shop/register";
		$.ajax({
			url:url,
			type:'post',
			data:{
				username:$("#username").val().trim(),
				password:$("#password").val().trim(),
				email:$("#useremail").val().trim(),
				userqq:$("#userqq").val().trim(),
				phone:$("#usermobile").val().trim(),
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
	
	</script>
</head>
<body>
<div class="state" style="background:#F2F2F2;">
    <div class="wrap">
    	        <a href="#">注册</a> ｜ <a href="${ctx}/web/shop/index">登录</a>
        <span class="fr"><a href="${ctx}/help/help_center.jsp" target="_blank">帮助中心</a></span>
    	    </div>
</div>

<div class="header">
    <div class="wrap">
        <a class="logo fl" href="http://www.liuliangfu.com/" style="margin-top:7px;"><img src="${ctx}/static_shop/images/logo.png"></a>
        <menu class="business-menu fr" style="width:500px;">
            <!-- <a href="/" target="_blank">首页</a> -->
            <a href="http://www.liuliangfu.com/pay/pay_score" target="_blank">购买积分</a>
            <a href="http://www.liuliangfu.com/trade/step_one" target="_blank">发布任务流量</a>
            <a href="http://www.liuliangfu.com/center/invite_business?left-list-id=7" target="_blank">邀请返利</a>
            <a href="http://www.liuliangfu.com/help/novice" target="_blank">新商家必读</a>
            <a href="http://www.liuliangfu.com/center">个人中心</a>
        </menu>
    </div>
</div>
<div class="breadcrumbs">
	<div class="wrap"><a href="http://www.liuliangfu.com/">首页</a> &gt; <a href="http://www.liuliangfu.com/user/register#">注册</a> &gt; <a href="http://www.liuliangfu.com/user/register#">填写注册信息</a></div>
</div>
<div class="container">
    <div class="Shadowbox">
        <div class="Shadowboxp"></div>
        <div class="register">
            <div class="register-title">
            	<span class="register-title" style="margin-top:15px;"></span>
                <div class="Process">
                    <ul class="clearfix">
                        <li class="" style="width:25%"><em class="Processing">1<i></i></em><span class="processspan">注册账号</span><strong></strong></li>
                        <li class="" style="width:25%"><em class="">2<i></i></em><span class="">购买会员</span><strong></strong></li>
                        <li class="" style="width:25%"><em class="">3<i></i></em><span>绑定店铺</span><strong></strong></li>
                        <li class="" style="width:25%"><em class="">4<i></i></em><span>购买积分</span><strong></strong></li>
                        <li class="Processlast" style="width:82px"><em>5<i></i></em><span>发布流量任务</span><strong></strong></li>
                    </ul>
                </div>
                <strong>已有账号?<a href="${ctx}/web/shop/index">立即登陆</a></strong>
            </div>    
            <div class="register-main">
            <div class="register-tabs">
				<form autocomplete="off" action="/user/register" method="post">
                <input type="hidden" name="invite_id" value="0">
                <input type="hidden" name="partner" value="0">
                <div class="register-tabs-bd">
                    <div class="register-tabs-main">
                        <div class="register-business clearfix">


                            <div class="register-business-form">                            
                                <div class="item clearfix">
                                    <label class="tit">用户名:</label>
                                    <div class="inp">
                                        <em class="inpbox">
                                            <i class="icon-U"></i>
                                            <input type="text" id="username" name="username" cname="one" placeholder="请输入用户名" warn="用户名4-15个字符，一个汉字为2个字符 " checkurl="ture" class="txt placebox" regname="username" emptyerr="用户名不能为空">
                                        </em>
                                    </div>
                                </div>
                                <div class="item clearfix">
                                    <label class="tit">密码:</label>
                                    <div class="inp">
                                        <em class="inpbox">
                                            <i class="icon-P"></i>
                                            <input type="password" autocomplete="off" placeholder="请输入密码" warn="长度6~16位数字、字母、字符包含两种 " cname="one" class="txt placebox" confirmationpass="password" id="password" name="password" regname="loginpassword" emptyerr="密码不能为空">
                                        </em>                   
                                    </div>
                                </div>  
                                <div class="item clearfix">
                                    <label class="tit">确认密码:</label>
                                    <div class="inp">
                                        <em class="inpbox">
                                            <i class="icon-P"></i>
                                            <input type="password" placeholder="请输入确认密码" warn="请再次确认密码" cname="one" class="txt placebox" name="confirmationpassword" confirmation="password" id="password_confirm" emptyerr="确认密码不能为空" confirmationerr="输入密码不一致,请重新填写">
                                        </em>                   
                                    </div>
                                </div>  
                                <div class="item clearfix">
                                    <label class="tit">邮箱:</label>
                                    <div class="inp">
                                        <em class="inpbox">
                                            <i class="icon-E"></i>                      
                                            <input type="text" warn="请填写您的常用邮箱" cname="one" checkurl="ture" placeholder="请输入邮箱地址" name="useremail" id="useremail" class="txt placebox" regname="email" emptyerr="邮箱地址不能为空">
                                        </em>   
                                    </div>
                                </div>  
                                <div class="item clearfix">
                                    <label class="tit">QQ号:</label>
                                    <div class="inp">
                                        <em class="inpbox">
                                            <i class="icon-Q"></i>                      
                                            <input type="text" warn="便于客服与您联系" cname="one" checkurl="ture" placeholder="请输入qq号" name="userqq" class="txt placebox" id="userqq" regname="qq" emptyerr="QQ号不能为空">  
                                        </em>   
                                    </div>
                                </div>
                                
                                <div class="item clearfix">
                                    <label class="tit">手机:</label>
                                    <div class="inp">
                                        <em class="inpbox">
                                            <i class="icon-M"></i>                      
                                            <input type="text" warn="" cname="one" checkurl="ture" placeholder="请输入手机号" name="usermobile" class="txt placebox" id="usermobile" regname="mobile" emptyerr="手机号不能为空">  
                                        </em>
                                              
                                    </div>
                                    <!-- <a href="javascript:;" class="cord" onClick="cord();">发送验证码</a> -->
                                    <!-- <span class="cordspan" style="display:none;">60秒后可重新发送</span> -->
                                </div>


<!--                                 <div id="rqftype" class="item clearfix" >
                                    <label class="tit">验证码:</label>
                                    <div class="inp">
                                        <em class="inpbox">
                                            <i class="icon-YZM"></i>                      
                                            <input type="text" warn="无验证码不可注册" cname="one" checkurl="ture" placeholder="请输入验证码" name="code" class="txt placebox" id="code" regname="code" emptyerr="验证码不能为空"/>  
                                        </em>          
                                    </div>
                                </div>   -->                                                                    
                                <p class="item loginsub inp">
                                    <button type="button" onclick="register();" class="loginbtn" id="submit_btn" name="submit_btn">立即注册</button>
                                    <label for="recommend_chk"><input canempty="ture" checkurl="ture" regname="recommend_chk" type="checkbox" checked="checked" id="recommend_chk" name="login_remember">我同意流量符<a href="${ctx}/static_shop/html/protocol.html" target="_blank">服务协议</a></label>
                                </p>                                    
                            </div>
                            
                            <div class="register-business-info">
                                <h3>为什么选择我们？</h3>
                                <p>流量符是一个提供优质流量的网站，支持淘宝、天猫、京东等各大平台；我们的流量是全网唯一可以被淘宝后台的搜索引擎统计到的优质流量，真实、有效；</p>
                            </div>
                            
                        </div>                
                    </div>
                </div>
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