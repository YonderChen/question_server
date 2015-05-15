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
	<link rel="stylesheet" href="${ctx}/static_shop/style/common.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/person_center.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/popup.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/register.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/release.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/detail.css">
	<style>
	<!--
	.pay_order_detail p {
		height: 40px;
	}
.pay-order-detail{width:1000px; overflow:hidden; margin:0 auto;}
.pay-order-detail-left{width:392px; height:488px; overflow:hidden; float:left; border:1px solid #E0E0E0; border-radius:5px; background:#fff;}
.pay-order-detail-left h3{width:360px; height:45px; line-height:45px; padding-left:20px; border-bottom:1px solid #E7E7E7; font-size:16px; font-weight:bold; color:#444;}
.pay-order-detail-left p{color:#555; font-size:14px; line-height:40px; padding-left:20px;}
.pay-order-detail-left p span{color:#f00;}
.pay-order-detail-left .source{overflow:hidden; font-size:14px; color:#555; padding-left:20px;}
.pay-order-detail-left .source label{display:block; width:78px; float:left; line-height:25px;}
.pay-order-detail-left .source div{display:block; width:280px; float:left; line-height:25px;}

.pay-order-detail-right{width:598px; overflow:hidden; float:right; border:1px solid #E0E0E0; border-radius:5px; background:#fff;}
.pay-order-detail-right h3{width:560px; height:45px; line-height:45px; padding-left:20px; border-bottom:1px solid #E7E7E7; font-size:16px; font-weight:bold; color:#444;}
.pay-order-detail-right p{color:#555; font-size:14px; line-height:30px; padding-left:20px;}
.pay-order-detail-right p span{color:#f00;}
.pay-order-detail-right .source{overflow:hidden; font-size:14px; color:#555; padding-left:20px;}
.pay-order-detail-right .source label{display:block; width:78px; float:left; line-height:25px;}
.pay-order-detail-right .source div{display:block; width:280px; float:left; line-height:25px;}
	-->
	</style>
</head>
<body>
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="#">订单详情</a></div>
</div>
<div class="task-detail">
    <div class="pay-order-detail-left">
    	<h3>订单信息</h3>
        <p>订单编号：${order.orderId }</p>
        <p>订单创建时间：<s:date name="#request.order.createTime" format="yyyy-MM-dd HH:mm:ss"/></p>
        <p>交易号：${order.dealId }</p>
        <p>理由：${order.reason }</p>
        <p>支付时间：<s:date name="#request.order.payTime" format="yyyy-MM-dd HH:mm:ss"/></p>
        <p>
		<s:if test="#request.llPayRecordBean.recordType == 1">积分数：</s:if>
		<s:else>
			<s:if test="#request.llPayRecordBean.recordType == 2">vip续费月数：</s:if>
			<s:else>
				数量：
			</s:else>
		</s:else>
		${order.num }
        </p>
    	<div class="task-state" style="padding-top: 20px;">
        	<h4 class="f16"><i></i>订单状态：
        		<span id="task_status">
					<s:if test="#request.order.status == 0">待审核</s:if>
					<s:if test="#request.order.status == 1">审核通过</s:if>
					<s:if test="#request.order.status == 2">审核失败</s:if>
	            </span>
			</h4>
        </div>
	</div>
    <div class="pay-order-detail-right">
    	<h3>交易成功截图</h3>
        <img class="img" alt="" style="max-width: 100%" src="${ctx }${order.payImgUrl }">
    </div>
</div>
			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>
</body></html>