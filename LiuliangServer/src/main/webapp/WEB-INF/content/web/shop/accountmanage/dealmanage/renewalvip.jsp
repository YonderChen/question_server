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
    
	<script type="text/javascript">
	
	$(document).ready(function(){
	    paytimeChange($("input[name='paytime'][checked]").val());
	});
	
	function paytimeChange(paytime){
		$("#paytime").val(paytime);
		$("#pay_month").html(paytime);
		var dateLimit = AddMonths($("#begin_time").val(),paytime);
	    $("#date_limit").html(dateLimit);
	}
	
	
	function submit() {
		if($("#paytime").val().trim() == ""){
			alert("请选择续费月数");
			return;
		}
		if($("#dealId").val().trim() == ""){
			alert("请输入转账交易号");
			$("#dealId").select();
			return;
		}
		var url = "${ctx}/web/shop/accountmanage/dealmanage/add_vip_order";
		$.ajax({
			url:url,
			type:'post',
			data:{
				num : $("#paytime").val().trim(),
				dealId : $("#dealId").val().trim()
			},
			dataType:'text',
			timeout:60000,
			error: function(e) {
				alert("连接服务器超时,请稍后再试.");
			},
			success: function(result){
				if (!isOutTime(result)) {
					result = eval("("+result+")");
					alert(result.msg);
					if (result.success) {
						$("#price").val("taobao");
						$("#dealId").val("");
					}
				}
			}
		});
	}
	
	function AddMonths(date,months){
		var nd = new Date(date);
		//alert(nd.getFullYear() + "年" + (nd.getMonth() + 1) + "月" + nd.getDate() + "日");
		var y = nd.getFullYear() + parseInt((nd.getMonth() + 1 + parseInt(months)) / 12);
		var m = (nd.getMonth() + 1 + parseInt(months)) % 12;
		var d = nd.getDate();
		if(m <= 9) m = "0"+m;
		if(d <= 9) d = "0"+d; 
		var cdate = y+"-"+m+"-"+d;
		return cdate;
	}
</script>
</head>
<body>
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="${ctx }/web/shop/accountmanage/dealmanage/renewalvip">续费会员</a></div>
</div>
    <div class="container">
    <div class="Shadowbox">
        <div class="Shadowboxp"></div>
        <div class="register">
        
					<s:if test="#session.sessionServerUserInfo.vipEndTime == null">
						<div class="register-title">
                            	<span class="bram-title">购买VIP会员</span>
				                <!-- 续费VIP会员 -->
				                <!-- <span class="bram-title">续费VIP会员</span> -->
				                <div class="Process">
				                    <ul class="clearfix">
				                        <li class="cur" style="width:25%"><em class="Processyes">1<i></i></em><span class="processspan">注册账号</span><strong></strong></li>
				                        <li class="" style="width:25%"><em class="Processing">2<i></i></em><span class="processspan">购买会员</span><strong></strong></li>
				                        <li class="" style="width:25%"><em class="">3<i></i></em><span>绑定店铺</span><strong></strong></li>
				                        <li class="" style="width:25%"><em class="">4<i></i></em><span>购买积分</span><strong></strong></li>
				                        <li class="Processlast" style="width:82px"><em>5<i></i></em><span>发布流量任务</span><strong></strong></li>
				                    </ul>
				                </div>
				           </div>
					</s:if>
					<s:else>
						<div class="register-title">
                            <span class="bram-title">续费VIP会员</span>
                        </div>
					</s:else>
            <div class="register-main">
                <!-- 续费会员时显示 -->
					<s:if test="#session.sessionServerUserInfo.vipEndTime != null">
	               	<div class="user-payvip">
	               	流量符账号：<span class="red">${sessionServerUserInfo.username }</span>&nbsp;&nbsp;
	               	您的会员级别：<span class="red">VIP</span>会员，会员有效期至：<span class="red f16"><s:date name="#session.sessionServerUserInfo.vipEndTime" format="yyyy-MM-dd"/></span></div>
						<s:if test="#session.sessionServerUserInfo.vipEndTime.time < #request.nowDate.time">
							<input id="begin_time" type="hidden" value="<s:date name="#request.nowDate" format="yyyy-MM-dd"/>">
						</s:if>
						<s:else>
							<input id="begin_time" type="hidden" value="<s:date name="#session.sessionServerUserInfo.vipEndTime" format="yyyy-MM-dd"/>">
						</s:else>
					</s:if>
					<s:else>
							<input id="begin_time" type="hidden" value="<s:date name="#request.nowDate" format="yyyy-MM-dd"/>">
					</s:else>
                    <div class="register">
                    <div class="register_info">
                        
                        <form id="pay_form" action="#" method="post" target="_blank">
                        <div class="select_buy">
                        
                            <h1>1.请选择续费时长</h1>
								<div class="select_buy_input">
                            		<input type="hidden" id="paytime" value="">
									<label style="margin-right: 50px;">
										<input type="radio" onclick="paytimeChange(6);" name="paytime" value="6" data-k="120">
										6个月
										<span style="color: #F80000; margin-left: 4px;">120</span> 元
									</label>
									<label style="margin-right: 50px;">
										<input type="radio" onclick="paytimeChange(9);" name="paytime" value="9" data-k="180">
										9个月
										<span style="color: #F80000; margin-left: 4px;">180</span> 元
									</label>
									<label style="margin-right: 50px;">
										<input type="radio" onclick="paytimeChange(12);" name="paytime" checked="checked" value="12"
											data-k="216">
										12个月
										<span style="color: #F80000; margin-left: 4px;">216</span> 元
										<i>（9折）</i>
										<span class="red">推荐</span>
									</label>
									<label style="margin-right: 50px;">
										<input type="radio" onclick="paytimeChange(24);" name="paytime" value="24" data-k="398">
										24个月
										<span style="color: #F80000; margin-left: 4px;">398</span> 元
										<i>（8.3折）</i>
									</label>
								</div>
										<div class="selected">
                                您已选择购买VIP会员<span id="pay_month" class="pay_month">12</span>个月，有效期至：<span id="date_limit" class="date_limit" datevip="0">2016-04-27</span>
                                <div>费用合计：<span class="pay_total">216</span>元</div>
                            </div>
                        </div>
                        
                        <div class="pay_des">
                            <h1 style="margin-bottom:20px;">2.输入转账交易号</h1>    
                            
                        <div>                            
                                <div class="item clearfix">
                                    <label class="tit">交易号:</label>
                                    <div class="inp">
                                        <em class="inpbox">
                                            <i class="icon-TXM"></i>
                                            <input type="text" id="dealId" name="dealId" autocomplete="off" cname="one" placeholder="请输入交易号" warn=" " checkurl="ture" class="txt placebox" regname="dealId" emptyerr="交易号不能为空">
                                        </em>
                                    </div>
                                </div>
                            <div>
                            	<img id="jiaoyihao_img" alt="交易号" src="${ctx}/images/jiaoyihao.jpg">
                            </div>
                        </div>
                        </div>
                        </form>
                        
                            <div class="btn-box tc">
                                <input type="button" onclick="submit();" name="submit" class="buttons-vip-confirm buttons" value="确认付款">
                                
					<s:if test="#session.sessionServerUserInfo.vipEndTime == null">
						<span class="skip-step"><a href="${ctx}/web/shop/center">跳过此步骤</a>(暂时不开通VIP)</span>
					</s:if>
                            </div>
                    </div>
                </div>

              <div class="pay-problem">
                  <h3 class="f18 cor555">常见问题</h3>
                  <p class="f14 cor555">问：VIP会员开通和续费能使用支付宝支付吗？ </p>
                  <p class="f14 cor555">答：为了保证交易的安全性，平台不能够使用支付宝付款，只能使用网上银行付款</p><br>
                  <p class="f14 cor555">问：买了会员之后，不想用了还可以退还会员费吗？</p>
                  <p class="f14 cor555">答：不可以，会员就是获得网站的使用资格，用了就不能退款的哦。</p>
              </div>
            
            </div>  
        </div>
        <div class="Shadowboxb"></div>
    </div>
</div>
			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>


</body></html>