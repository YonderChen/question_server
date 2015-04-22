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
    <link rel="stylesheet" href="${ctx}/static_shop/style/register.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/business_bind.css">
	<script type="text/javascript">
	
	$(document).ready(function(){
	    bindPlatChange($("input[name='bind_plat'][checked]").val());
	});
	
	function bindPlatChange(bindPlat) {
		$("#bindPlat").val(bindPlat);
	    if(bindPlat == "taobao") {
	    	$("#bindName_desc").html("店铺主旺旺");
	    	$("#bindName_alert").html("店铺主旺旺");
	    	$("#binding_shopimg_taobao").show();
	    	$("#binding_shopimg_tmall").hide();
	    	$("#binding_shopimg_jd").hide();
	    	$("#taobao_li").removeClass();
	    	$("#tmall_li").removeClass();
	    	$("#jd_li").removeClass();
	    	$("#taobao_li").addClass("active");
	    }
	    else if (bindPlat == "tmall"){
	    	$("#bindName_desc").html("店铺名称");
	    	$("#bindName_alert").html("店铺名称");
	    	$("#binding_shopimg_taobao").hide();
	    	$("#binding_shopimg_tmall").show();
	    	$("#binding_shopimg_jd").hide();
	    	$("#taobao_li").removeClass();
	    	$("#tmall_li").removeClass();
	    	$("#jd_li").removeClass();
	    	$("#tmall_li").addClass("active");
	    } else {
	    	$("#bindName_desc").html("店铺名称");
	    	$("#bindName_alert").html("店铺名称");
	    	$("#binding_shopimg_taobao").hide();
	    	$("#binding_shopimg_tmall").hide();
	    	$("#binding_shopimg_jd").show();
	    	$("#taobao_li").removeClass();
	    	$("#tmall_li").removeClass();
	    	$("#jd_li").removeClass();
	    	$("#jd_li").addClass("active");
	    }
	    
		var url = "${ctx}/web/shop/accountmanage/shopmanage/list";
		$.ajax({
			url:url,
			type:'post',
			data:{
				bindPlat : bindPlat,
			},
			dataType:'text',
			timeout:60000,
			error: function(e) {
				alert("连接服务器超时,请稍后再试.");
			},
			success: function(result){
				result = eval("("+result+")");
				if (result.success) {
					$("#shop_list_info").html(result.msg);
				} else {
					alert(result.msg);
				}
			}
		});
	}
	
	function bind() {
		if($("#bindPlat").val().trim() == ""){
			alert("请选择平台");
			return;
		}
		if($("#bindName").val().trim() == ""){
			alert("请输入店铺旺旺");
			$("#bindName").select();
			return;
		}
		if($("#shopUrl").val().trim() == ""){
			alert("请输入店铺首页URL");
			$("#shopUrl").select();
			return;
		}
		if($("#verifyGoodsUrl").val().trim() == ""){
			alert("请输入验证商品URL");
			$("#verifyGoodsUrl").select();
			return;
		}
		var url = "${ctx}/web/shop/accountmanage/shopmanage/add";
		$.ajax({
			url:url,
			type:'post',
			data:{
				bindPlat : $("#bindPlat").val().trim(),
				bindName : $("#bindName").val().trim(),
				shopUrl : $("#shopUrl").val().trim(),
				verifyGoodsUrl : $("#verifyGoodsUrl").val().trim()
			},
			dataType:'text',
			timeout:60000,
			error: function(e) {
				alert("连接服务器超时,请稍后再试.");
			},
			success: function(result){
				result = eval("("+result+")");
				alert(result.msg);
				if (result.success) {
					location.reload();
				}
			}
		});
	}
	
</script>
</head>
<body>
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="${ctx }/web/shop/accountmanage/shopmanage/bind_shop">绑定店铺</a></div>
</div>
<div class="container">
    <div class="Shadowbox">
        <div class="Shadowboxp"></div>
        <div class="register">
        
            <div class="register-title">
            	<span class="bram-title">绑定店铺</span>
                
                <!-- 续费VIP会员 -->
                <!-- <span class="bram-title">续费VIP会员</span> -->
                <div class="Process">
                    <ul class="clearfix">
                        <li class="cur" style="width:25%"><em class="Processyes">1<i></i></em><span class="processspan">注册账号</span><strong></strong></li>
                        <li class="cur" style="width:25%"><em class="Processyes">2<i></i></em><span class="processspan">购买会员</span><strong></strong></li>
                        <li class="" style="width:25%"><em class="Processing">3<i></i></em><span class="processspan">绑定店铺</span><strong></strong></li>
                        <li class="" style="width:25%"><em class="">4<i></i></em><span class="">购买积分</span><strong></strong></li>
                        <li class="Processlast" style="width:82px"><em>5<i></i></em><span>发布流量任务</span><strong></strong></li>
                    </ul>
                </div>
            </div>
            <form action="/bind/bind_shop/taobao" method="post" id="frm_binding">
            <div class="register-main">
                <!-- 续费会员时显示 -->
              	<!-- <div class="user-payvip">流量符账号：<span class="red">test4</span>&nbsp;&nbsp;您的会员级别：<span class="red">VIP</span>会员，会员有效期剩余：<span class="red f16">983</span>天</div> -->
                <!-- <div class="user-payvip">流量符账号：<span class="red">test4</span>&nbsp;&nbsp;您的会员级别：<span class="red">普通</span>会员(原会员VIP已过期)</div> -->
                <div class="binding-shop">

                    <div class="business-bind-wrap clearfix">
                        <div class="business-bind-left">
                        	<h3>选择平台</h3>
                            <ul class="clearfix J_inputlink">
                                                                <li id="taobao_li" class="active"><i></i>
                                <label><a>
                                  <input type="radio" name="bind_plat" onclick="bindPlatChange('taobao')" value="taobao" checked="checked">淘宝                                </a></label>
                                </li>
                                                                <li id="tmall_li"><i></i>
                                <label><a>
                                  <input type="radio" name="bind_plat" onclick="bindPlatChange('tmall')" value="tmall">天猫                                </a></label>
                                </li>
                                                                <li id="jd_li"><i></i>
                                <label><a>
                                  <input type="radio" name="bind_plat" onclick="bindPlatChange('jd')" value="jd">京东                                </a></label>
                                </li>
                                                        	</ul>    
                            	<input type="hidden" id="bindPlat" value="">
                        </div>
                        
                        <div class="business-bind-right">    
                                          
                          <!--add-->
                          	<h1>绑定新店铺</h1>
                        	<div class="business-bind-box">
                                <div class="business-bind-list business-bind-new">

                                    <div class="item clearfix">
                                        <label class="tit"><i>*</i><span id="bindName_desc">店铺主旺旺</span>:</label>
                                         <div class="inp">
                                             <em class="inpbox">
                                                 <input type="text" class="txt" checkurl="true" id="bindName" name="bindName" regname="userid" emptyerr="旺旺不能为空" value="">
                                             </em>
                                                                                      </div>
                                         <span style="color:#565656; font-size:12px; line-height:35px; margin-left:10px;">(<span id="bindName_alert">店铺主旺旺</span>绑定后无法修改)</span>
                                     </div>
                                     <div class="item clearfix">
                                         <label class="tit"><i>*</i>店铺首页网址（URL）:</label>
                                         <div class="inp">
                                             <em class="inpbox">
                                             <input type="text" class="txt w320" checkurl="true" id="shopUrl" name="shopUrl" regname="shopurl" emptyerr="店铺首页网址不能为空" style="ime-mode:disabled" value="">
                                             </em>
                                                                                      </div>
                                      </div>
                                      <div class="item clearfix">
                                         <label class="tit">验证码:</label>
                                         <div class="bindingshop-yzm inp">
                                             <div class="binding-yzm"><span>${goodsVerifyCode}</span><a class="J_copytext" data-copy="${goodsVerifyCode}" href="javascript:;">复制验证码</a><div class="zclip" id="zclip-ZeroClipboardMovie_1" style="position: absolute; left: 150px; top: 0px; width: 70px; height: 35px; z-index: 99;"><embed id="ZeroClipboardMovie_1" src="http://www.renqifu.com/static/js/ZeroClipboard.swf" loop="false" menu="false" quality="best" bgcolor="#ffffff" width="70" height="35" name="ZeroClipboardMovie_1" align="middle" allowscriptaccess="always" allowfullscreen="false" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" flashvars="id=1&amp;width=70&amp;height=35" wmode="transparent"></div></div>
                                             <p>1.将验证码加到您的店铺里某个上架商品的标题上，类似这样：</p>
                                             <div class="binding-shopimg">
												<img id="binding_shopimg_taobao" style="display: none;" alt="商品验证" src="${ctx}/static_shop/images/business/binding_shopimg_taobao.jpg">
												<img id="binding_shopimg_tmall" style="display: none;" alt="商品验证" src="${ctx}/static_shop/images/business/binding_shopimg_tmall.jpg">
												<img id="binding_shopimg_jd" style="display: none;" alt="商品验证" src="${ctx}/static_shop/images/business/binding_shopimg_jd.jpg">
                                           </div>
                                             
                                         </div>
                                      </div>
                                      <p style="color:#555; font-size:14px; line-height:50px; margin-left:170px;">2.再将这个商品的详情页链接，复制到下面输入框</p>
                                      <div class="item clearfix">
                                         <label class="tit"><i>*</i>商品网址（URL）:</label>
                                         <div class="inp">
                                             <em class="inpbox">
                                             <input type="text" class="txt w320" checkurl="true" id="verifyGoodsUrl" name="verifyGoodsUrl" regname="check_url" emptyerr="商品网址不能为空" style="ime-mode:disabled" value="">
                                             </em>
                                                                                      </div>
                                      </div>
        							
                                      <p class="item">
                                        <input class="savebtn" style="cursor:pointer;display:inline-block;" type="button" onclick="bind();" ondblclick="return false;" value="提交审核">
                                                                               </p>
                                
                                </div> 
                            </div>
                                                      <!--add-->
                          <div class="binding-shop-list">
                              <h1>已绑定的店铺<span class="red">(0)</span><span class="shop-num">每个平台可绑定十个店铺</span></h1>
                              <table border="1" bordercolor="#E5E5E5" style="table-layout:fixed;word-wrap:break-word;">
                                
								<thead>
		                    	<tr>
		                            <td width="25%">店铺主旺旺</td>
		                            <td width="50%">店铺地址</td>
		                            <td width="25%">状态</td>
		                        </tr>
		                        </thead>
                        <tbody id="shop_list_info">                     <tr>
                                	<td colspan="3">
                                没有绑定的店铺
                                	</td>
                                </tr>
                                                              </tbody></table>
                              <p>提示：绑定的店铺提交认证后，平台预计在1小时内完成审核操作，只有审核通过的商家才可以发布任务</p>
                          </div>
                          
                        
                  	</div>
                  </div>
                    
                    
                </div>
              
            
              <div class="pay-problem">
                  <h3 class="f18 cor555">常见问题</h3>
                  <p class="f14 cor555">问：提交店铺认证后，多久可以通过审核呢？ </p>
                  <p class="f14 cor555">答：一般在提交后的1小时内完成。</p>
                  <br>
                  <p class="f14 cor555">问：一个账号最多可以绑定几个店铺呢？ </p>
                  <p class="f14 cor555">答：一个平台最多可以绑定十个店铺。</p>
                  <br>
                  <p class="f14 cor555">问：商品名称里的验证码需要放多长时间？ </p>
                  <p class="f14 cor555">答：绑定的店铺通过审核之后，就可以去掉验证码了。另：绑定店铺是自动审核的，符合条件系统就会自动审核通过。。</p>
              </div>
            
            </div></form>  
        </div>
        <div class="Shadowboxb"></div>
    </div>
</div>
			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>


</body></html>