<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>注册</title>
	<meta http-equiv="X-UA-Compatible"content="IE=10; IE=9; IE=8; IE=7; IE=EDGE">
	<link rel="shortcut icon" href="${ctx }/images/favicon.ico" type="image/x-icon" />
	<link rel="icon" href="${ctx }/images/favicon.ico" type="image/x-icon" />
	<link type="text/css" href="${ctx}/style/style.css" rel="stylesheet"/>
	<script type="text/javascript" src="${ctx}/js/base.js"></script>
  	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
   	<script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
   	<script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>   
   	<script type="text/javascript" src="${ctx}/js/cloud.js"></script>   
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
		if ($("#email").val().trim() == "") {
			alert("邮箱不能为空.");
			$("#email").select();
			return;
		}
		/*
		if ($("#code").val().trim() == "") {
			alert("验证码不能为空.");
			$("#code").select();
			return;
		}
		*/
		$("#loginBtn").button('loading');
		var url = "${ctx}/web/admin/register";
		$.ajax({
			url:url,
			type:'post',
			data:{
				username:$("#username").val().trim(),
				password:$("#password").val().trim(),
				email:$("#email").val().trim(),
			},
			dataType:'json',
			timeout:60000,
			error: function(e) {
				$("#loginBtn").button('reset');
				$(".active").removeClass('active');
				alert("连接服务器超时,请稍后再试.");
			},
			success: function(result){
				$("#loginBtn").button('reset');
				$(".active").removeClass('active');
				if (result.success) {
					window.location.href = result.redirectUrl;
				} else {
					alert(result.msg);
				}
			}
		});
	}
	
	function identifyLogin() {
		var url = "${ctx}/web/admin/identify";
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
		$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		$(window).resize(function(){
    		$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    	}); 
		identifyLogin();
		if ($.cookie("rmbUser") == "true") {
        	$("#rememberMe").prop("checked", true);
       	 	$("#username").val($.cookie("username"));
        	$("#password").val($.cookie("password"));
    	}
	});
	
	function changeCode() {
		document.getElementById("img").src="${ctx}/web/admin/code?num="+Math.random();
	}
	
	</script>
  </head>
  <body style="background-color:#1c77ac; background-image:url(${ctx }/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
  
	<div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>Welcome
</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>

		<div class="loginbody">

			<div class="loginbox">
				<form action="" method="post" onkeydown="if(event.keyCode==13) {register();}">
					<ul>
						<li>
							<input id="username" name="username" type="text" class="loginuser" value="" placeholder="用户名" maxlength="50" />
						</li>
						<li>
							<input id="password" name="password" type="password" class="loginpwd" value="" placeholder="密码" maxlength="50" autocomplete="off" />
						</li>
						<li>
							<input id="password_confirm" name="password_confirm" type="password" class="loginpwd" value="" placeholder="确认密码" maxlength="50" autocomplete="off" />
						</li>
						<li>
							<input id="email" name="email" type="text" class="loginpwd" value="" placeholder="邮箱" maxlength="50" autocomplete="off" />
						</li>
						<li style="display: none;">
							<input id="code" name="code" type="text" class="loginpwd1" value="" placeholder="验证码" maxlength="5" />
							<img src="${ctx}/web/admin/code" style="vertical-align: middle; padding-left: 10px; cursor: pointer;" id="img" onclick="javascript:changeCode();" title="换一张" />
						</li>
						<li>
							<input id="registerBtn" type="button" class="loginbtn" value="注册" onclick="javascript:register();" data-toggle="button" data-loading-text="注册" />
						</li>
					</ul>
				</form>

			</div>
		</div>



		<div class="loginbm">Copyright©2015 yonder </div>
    
</body>
</html>