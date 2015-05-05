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
	<title>流量符-个人中心，汇聚更多流量</title>
    <meta name="keywords" content="流量符、买流量、搜索流量、流量精灵、淘流量、流量查询、刷人气、刷流量网站、淘宝流量、京东流量、手机淘宝流量">
    <meta name="description" content="流量符是一个专业的刷流量网站，通过搜索关键词，将流量导入网店中，来提高网店排名，刷淘宝排名、京东排名、网店访问量和刷人气，是网店推广、网络营销必备的流量推广神器！">
	<link rel="shortcut icon" type="image/x-icon" href="${ctx}/images/favicon.ico" media="screen">
	<script type="text/javascript" src="${ctx}/js/base.js"></script>
  	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
  	<script type="text/javascript" src="${ctx}/js/jquery-form.js"></script>
	<link rel="stylesheet" href="${ctx}/static_shop/style/common.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/person_center.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/popup.css">
</head>
<body>
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="${ctx }/web/shop/userinfo?left-list-id=3">基本信息</a></div>
</div>
    <div class="wrap clearfix">
			<jsp:include page="/include/left.jsp" flush="true"></jsp:include> 
	<div class="business-right">
      <div class="business-right-comm">
          <div class="business-info">
            <div class="business-info-hd">基本信息</div>
            <div class="business-info-bd">
                
                
                <div class="business-info-list">
                
                    <div class="business-info-list-hd">
                      <span>登录密码</span>
                      <b class="set">已设置</b>
                      <a class="fr" id="pwd_edit_a" href="javascript:;">修改</a>
                    </div>
                    <div id="pwd_edit_div" class="business-info-list-bd" style="display:none;">
                    
                        <form action="#" method="post" name="user-adderss" id="from-userpwd">
                        <input name="op" type="hidden" value="pwd">
                        <div class="user-info-form user-login-box">
                            <p class="setTips">为了您的账号安全，请定期更换登陆密码，并确保登录密码设置与提现密码不一样。</p>
                            <div class="item clearfix">
                              <label class="tit"><i>*</i>原密码</label>
                              <div class="inp">
                                <em class="inpbox">
                                  <i class="icon-P"></i>
                                  <input type="password" id="oldPassword" placeholder="请输入密码" autocomplete="off" cname="one" class="txt placebox" name="old_pwd" regname="loginpassword" emptyerr="密码不能为空">
                                </em>
                              </div>
                            </div>
                            <div class="item clearfix">
                              <label class="tit"><i>*</i>新密码</label>
                              <div class="inp">
                                <em class="inpbox">
                                  <i class="icon-P"></i>
                                  <input type="password" id="newPassword" placeholder="请输入密码" cname="one" class="txt placebox" name="new_pwd1" confirmationpass="password" regname="loginpassword" emptyerr="密码不能为空">
                                </em>
                              </div>
                            </div>  
                            <div class="item clearfix">
                              <label class="tit"><i>*</i>确认新密码</label>
                              <div class="inp">
                                <em class="inpbox">
                                  <i class="icon-P"></i>
                                  <input type="password" id="newPassword_c" placeholder="请输入确认密码" cname="one" class="txt placebox" name="new_pwd2" confirmation="password" regname="loginpassword" emptyerr="确认密码不能为空" confirmationerr="输入密码不一致,请重新填写">
                                </em>          
                              </div>
                            </div>
                            <p class="item itembtnp">
                              <a href="javascript:editPass();" class="itembtn">确认修改</a>
                            </p>                                   
                        </div>
                        </form>
<script type="text/javascript"> 

	function editPass() {
		if ($("#oldPassword").val().trim() == "") {
			alert("原始密码不能为空");
			$("#oldPassword").select();
			return;
		}
		if ($("#newPassword").val().trim() == "") {
			alert("新密码不能为空");
			$("#newPassword").select();
			return;
		}
		if ($("#newPassword").val().trim() != $("#newPassword_c").val().trim()) {
			alert("两次新密码不一致");
			$("#newPassword_c").select();
			return;
		}
		var url = "${ctx}/web/shop/edit_pass";
		$.ajax({
			url:url,
			type:'post',
			data:{
				oldPassword: $("#oldPassword").val().trim(),
				newPassword:$("#newPassword").val().trim()
			},
			dataType:'text',
			timeout:60000,
			error: function(e) {
				alert("连接服务器超时,请稍后再试.");
			},
			success: function(result){
				result = eval("("+result+")");
				if (result.success) {
					$("#oldPassword").val("");
					$("#newPassword").val("");
					$("#newPassword_c").val("");
				} else {
					$("#newPassword").val("");
					$("#newPassword_c").val("");
					$("#oldPassword").select();
				}
				alert(result.msg);
			}
		});
	}
</script>
                    </div>           
                </div>
                
                <div class="business-info-list">
                    <div class="business-info-list-hd business-info-list-hd-lx">
                      <span>联系方式</span>
                    </div>
                    <div class="business-info-list-small">
                          <div class="business-info-list-small-list">
                            <div class="business-info-list-small-list-hd">
                              <span>QQ</span>
                              <b id="user_qq_info">${sessionServerUserInfo.userqq}</b>
                              <a class="fr" id="qq_edit_a" href="javascript:;">修改</a>                       
                            </div>
                            <div id="qq_edit_div" class="business-info-list-small-list-bd" style="display:none;">
                            <!-- 修改QQ -->
                            <form action="#" method="post" name="user-adderss" id="from-userqq">
                            <input name="op" type="hidden" value="qq">
                              <div class="user-info-form user-qq-box">
                                <p class="setTips">为了便于与客服联系，请填写您的常用QQ</p>
                                <div class="item clearfix">
                                  <label class="tit"><i>*</i>新的QQ号码</label>
                                  <div class="inp">
                                    <em class="inpbox">
                                      <i class="icon-Q"></i>            
                                      <input type="text" warn="请输入QQ号" cname="one" placeholder="请输入QQ号" name="userqq" class="txt placebox" id="userqq" regname="qq" emptyerr="QQ号不能为空">  
                                    </em>
                                                                      </div>
                                </div>
                                <p class="item itembtnp">
                                  <a href="javascript:edit_qq();" class="itembtn">确认修改</a>
                                                                  </p>                                                
                             </div>
                             </form>
                            
<script type="text/javascript"> 

	function edit_qq() {
		if ($("#userqq").val().trim() == "") {
			alert("QQ不能为空");
			$("#userqq").select();
			return;
		}
		var url = "${ctx}/web/shop/edit_qq";
		$.ajax({
			url:url,
			type:'post',
			data:{
				userqq: $("#userqq").val().trim()
			},
			dataType:'text',
			timeout:60000,
			error: function(e) {
				alert("连接服务器超时,请稍后再试.");
			},
			success: function(result){
				result = eval("("+result+")");
				if (result.success) {
					$("#user_qq_info").html($("#userqq").val());
					$("#userqq").val("");
				}
				alert(result.msg);
			}
		});
	}
	
</script>
                            <!-- 修改QQ结束 -->
                            </div>
                          </div>
                          
                          
                          
                          <div class="business-info-list-small-list">
                            <div class="business-info-list-small-list-hd">
                              <span>邮箱</span>
                              <b>${sessionServerUserInfo.email}</b>                     
                            </div> 
                            
                          
                          
                          <div class="business-info-list-small-list">
                            <div class="business-info-list-small-list-hd">
                              <span>手机</span>
                              <b id="user_phone_info">${sessionServerUserInfo.phone}</b>
                              
                              <a class="fr" id="phone_edit_a" href="javascript:;">修改</a>
                              
                              <!-- <a class="fr" href="javascript:;">设置</a> -->
                                                
                            </div>
                            <div id="phone_edit_div" class="business-info-list-small-list-bd" style="display:none;">
                            <!-- 修改手机  -->
                                <div class="user-info-form user-mobile-box">
                                <form action="#" method="post" name="user-adderss" id="from-mobile">
                                <input name="op" type="hidden" value="tel">
                                   <p class="setTips">为了便于我们与您联系，请填写您的常用手机号</p>
                                    <div class="item clearfix">
                                      <label class="tit"><i>*</i><!-- 新 -->手机号</label>
                                      <div class="inp">
                                        <em class="inpbox">
                                          <i class="icon-M"></i>            
                                          <input type="text" cname="one" placeholder="请输入手机号" name="usermoblie" class="txt placebox" id="usermoblie" regname="mobile" emptyerr="手机号不能为空"> 
                                        </em> 
                                                                              </div>
                                    </div> 
                                    <p class="item itembtnp">
                                      <a href="javascript:edit_phone();" class="itembtn">确认修改</a>
                                                                          </p> 
                                </form>                
<script type="text/javascript"> 
$(function(){
	var menu = getQueryString("menu");
	if(menu != null) {
		$("#" + menu + "_edit_div").show();
	}
})

	function edit_phone() {
		if ($("#usermoblie").val().trim() == "") {
			alert("手机号不能为空");
			$("#usermoblie").select();
			return;
		}
		var url = "${ctx}/web/shop/edit_phone";
		$.ajax({
			url:url,
			type:'post',
			data:{
				phone: $("#usermoblie").val().trim()
			},
			dataType:'text',
			timeout:60000,
			error: function(e) {
				alert("连接服务器超时,请稍后再试.");
			},
			success: function(result){
				result = eval("("+result+")");
				if (result.success) {
					$("#user_phone_info").html($("#usermoblie").val());
					$("#usermoblie").val("");
				}
				alert(result.msg);
			}
		});
	}
	
</script>                               
                                 </div> 
                            <!-- 修改手机结束 -->	
                            </div>
                          </div>  
                          
                                            
                    </div>        
                </div>           
                                              
            </div>
          </div>
           
        </div>
  </div>
</div>
</div>
			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>
<script type="text/javascript"> 

$(function(){
	
	var $list = $('.business-info-list-bd,.business-info-list-small-list-bd');
    $('.business-info-list-hd a,.business-info-list-small-list-hd a').click(function(){
	    if($(this).hasClass('userhead')) return;
	    $('.business-info-list-bd:visible,.business-info-list-small-list-bd:visible').slideUp();
	    if(!$(this).parent().next().is(':hidden'))return;
	    $(this).parent().next().slideDown();
	    //$(this).parent().find('.placebox').placeholder(); 
    });
	
	
})
</script>

</body></html>