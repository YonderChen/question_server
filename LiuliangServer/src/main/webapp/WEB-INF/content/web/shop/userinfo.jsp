<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
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
	<link rel="stylesheet" href="${ctx}/static_shop/style/common.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/person_center.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/popup.css">
</head>
<body>
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="${ctx }/web/shop/center">基本信息</a></div>
</div>
			<jsp:include page="/include/banner.jsp" flush="true"></jsp:include>
    
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
                      <a class="fr" href="javascript:;">修改</a>
                    </div>
                    <div class="business-info-list-bd" style="display:none;">
                    
                        <form action="/center/set_pwd" method="post" name="user-adderss" id="from-userpwd">
                        <input name="op" type="hidden" value="pwd">
                        <div class="user-info-form user-login-box">
                            <p class="setTips">为了您的账号安全，请定期更换登陆密码，并确保登录密码设置与提现密码不一样。</p>
                            <div class="item clearfix">
                              <label class="tit"><i>*</i>原密码</label>
                              <div class="inp">
                                <em class="inpbox">
                                  <i class="icon-P"></i>
                                  <input type="password" placeholder="请输入密码" autocomplete="off" cname="one" class="txt placebox" name="old_pwd" regname="loginpassword" emptyerr="密码不能为空">
                                </em>
                                                              </div>
                            </div>
                            <div class="item clearfix">
                              <label class="tit"><i>*</i>新密码</label>
                              <div class="inp">
                                <em class="inpbox">
                                  <i class="icon-P"></i>
                                  <input type="password" placeholder="请输入密码" cname="one" class="txt placebox" name="new_pwd1" confirmationpass="password" regname="loginpassword" emptyerr="密码不能为空">
                                </em>
                              </div>
                            </div>  
                            <div class="item clearfix">
                              <label class="tit"><i>*</i>确认新密码</label>
                              <div class="inp">
                                <em class="inpbox">
                                  <i class="icon-P"></i>
                                  <input type="password" placeholder="请输入确认密码" cname="one" class="txt placebox" name="new_pwd2" confirmation="password" regname="loginpassword" emptyerr="确认密码不能为空" confirmationerr="输入密码不一致,请重新填写">
                                </em>          
                              </div>
                            </div>
                            <p class="item itembtnp">
                              <a href="javascript:;" class="itembtn">确认修改</a>
                                                          </p>                                   
                        </div>
                        </form>
                        
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
                              <b>${sessionServerUserInfo.userqq}</b>
                              <a class="fr" href="javascript:;">修改</a>                       
                            </div>
                            <div class="business-info-list-small-list-bd" style="display:none;">
                            <!-- 修改QQ -->
                            <form action="/center/set_qq" method="post" name="user-adderss" id="from-userqq">
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
                                  <a href="javascript:;" class="itembtn">确认修改</a>
                                                                  </p>                                                
                             </div>
                             </form>
                            
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
                              <b>${sessionServerUserInfo.phone}</b>
                              
                              <a class="fr" href="javascript:;">修改</a>
                              
                              <!-- <a class="fr" href="javascript:;">设置</a> -->
                                                
                            </div>
                            <div class="business-info-list-small-list-bd" style="display:none;">
                            <!-- 修改手机  -->
                                <div class="user-info-form user-mobile-box">
                                <form action="/center/set_mobile" method="post" name="user-adderss" id="from-mobile">
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
                                      <a href="javascript:;" class="itembtn">确认修改</a>
                                                                          </p> 
                                </form>                                               
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