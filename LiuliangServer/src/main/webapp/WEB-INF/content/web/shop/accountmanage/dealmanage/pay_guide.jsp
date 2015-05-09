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
   	<script type="text/javascript" src="${ctx}/js/calendar/WdatePicker.js"></script>
	<link rel="stylesheet" href="${ctx}/static_shop/style/common.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/person_center.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/popup.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/register.css">
    
</head>
<body>
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="${ctx }/web/shop/accountmanage/dealmanage/get_score">购买积分</a></div>
</div>
    <div class="container">
    <div class="Shadowbox">
        <div class="Shadowboxp"></div>
        <div class="register">
        
            <div class="register-title">
            	<span class="bram-title">支付流程</span>
            </div>    
            <div class="register-main">
              
                  <div class="register">
                    <div class="register_info">
                        <div class="pay-integral">
                            <h1 style="margin-bottom:20px;">1.选择订单</h1>
                        </div>
                        <div class="pay-integral">
                            <h1 style="margin-bottom:20px;">2.转账订单指定金额到指定支付宝</h1>
	                        <div>                            
		                        <div class="item clearfix">
		                            <label class="tit" style="float: none;">pc支付宝网页转账操作说明：</label><br>
		                           	<img alt="pay_guide_1_pc" src="${ctx}/images/pay_guide_1_pc.png">
		                        </div>   
		                        <div class="item clearfix">
	                            	<label class="tit" style="float: none;">手机支付宝客户端转账操作说明：</label><br>
	                            	<img alt="pay_guide_1_1phone" width="250px" height="400px" src="${ctx}/images/pay_guide_1_1phone.png">
	                            	<img alt="pay_guide_1_2phone" width="250px" height="400px" src="${ctx}/images/pay_guide_1_2phone.png">
	                            </div>
	                        </div>
                        </div>
                        
                        <div class="pay-integral">
                            <h1 style="margin-bottom:20px;">3.转账成功截图上传</h1>
                        	<div>                   
	                            <div>                     
			                        <div class="item clearfix">
			                            <label class="tit" style="float: none;">截图实例：<em style="color: red;">（注意：截图需要登录手机支付宝，截取交易成功的完整界面）</em></label><br>
			                           	<img alt="pay_guide_2_phone" width="250px" height="400px" src="${ctx}/images/pay_guide_2_phone.png">
			                        </div>   
	                            </div>
	                        </div>
                        </div>
                        
                        <div class="pay-integral">
                            <h1 style="margin-bottom:20px;">4.输入交易相关信息</h1>
                        	<div>                            
	                            <div class="item clearfix">
	                            	<img alt="交易信息" src="${ctx}/images/input_pay_info.png" style="float: left">
	                            </div>                            
	                            <div class="item clearfix">
		                            <label class="tit" style="float: none;">交易相关信息说明：</label><br>
	                            	<img alt="交易信息说明" width="250px" height="400px" src="${ctx}/images/pay_guide_info.png">
	                            </div>
	                        </div>
                        </div>
                        
                        <div class="pay-integral">
                            <h1 style="margin-bottom:20px;">提交订单</h1>
                        	<div>                   
	                            <div>                     
			                        <div class="item clearfix">
			                            <label class="tit" style="float: none;">等待管理员审核</label><br>
			                        </div>   
	                            </div>
	                        </div>
                        </div>
                    </div>
                </div>
            </div>  
        </div>
        <div class="Shadowboxb"></div>
    </div>
</div>			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>


</body></html>