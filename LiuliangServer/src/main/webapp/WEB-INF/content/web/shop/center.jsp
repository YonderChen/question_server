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
</head>
<body>
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="${ctx }/web/shop/center">个人中心</a></div>
</div>
			<jsp:include page="/include/banner.jsp" flush="true"></jsp:include>
    
    <div class="wrap clearfix">
			<jsp:include page="/include/left.jsp" flush="true"></jsp:include> 
	<div class="right">
    	<!-- 右侧顶部账号积分等信息 start -->
    	<div class="righttop">
            <div class="user_grade">
                <div class="user_gradel">
                流量符账号：<span class="span100">${sessionServerUserInfo.username }</span>&nbsp;&nbsp;
                会员等级：<span class="span100">
					<s:if test="#session.sessionServerUserInfo.vipEndTime == null">普通会员</s:if>
					<s:else>
						<s:if test="#session.sessionServerUserInfo.vipEndTime.time < #request.nowDate.time">VIP已过期</s:if>
						<s:else>
							VIP会员
						</s:else>
					</s:else>
                </span>
                        <!-- <span class="span1">VIP</span> -->
                		<!-- 其他两种会员类型
                        <span class="span100">VIP会员</span>
                        <span class="span100">普通会员</span>
                        -->
                        
					<s:if test="#session.sessionServerUserInfo.vipEndTime == null">
                    	<a href="${ctx }/web/shop/accountmanage/dealmanage/renewalvip" target="_blank">&nbsp;&nbsp;&nbsp;开通VIP会员</a>
                    </s:if>
					<s:else>
						<span class="span2">到期时间：<s:date name="#session.sessionServerUserInfo.vipEndTime" format="yyyy-MM-dd"/></span>
						<a href="${ctx }/web/shop/accountmanage/dealmanage/renewalvip" target="_blank">续费VIP会员</a>
                    </s:else>
                </div>
                <div class="user_grader business-left">
                    <span>安全等级：</span>
					<s:if test="#session.sessionServerUserInfo.phone != null">
                    	<a class="icon-setting phone" href="${ctx}/web/shop/userinfo?menu=phone"></a>
                    </s:if>
					<s:if test="#session.sessionServerUserInfo.email != null">
                    	<a class="icon-setting mess" href="${ctx}/web/shop/userinfo?menu=qq"></a>
                    </s:if>
                    <a class="icon-setting money" href="${ctx}/web/shop/userinfo?menu=pwd"></a>
                </div>
                
            </div>

            
            <div class="available">
                <div class="available_left">
                    <div class="available_yj">
                        <strong>可用积分：</strong><span><strong>${sessionServerUserInfo.score }</strong>点</span>
                        <a href="${ctx}/web/shop/accountmanage/dealmanage/get_score" target="_blank">购买积分</a>
                    </div>
                    <div class="available_yj">
                        <strong>已用积分：</strong><span><strong>${sessionServerUserInfo.scoreUsed }</strong>点</span>
                        <a href="${ctx}/web/shop/accountmanage/dealmanage/score_record" target="_blank">积分记录</a>
                    </div>
                    
                </div>
                <div class="available_right"><a href="${ctx}/web/shop/taskmanage/add_task_step_one" target="_blank">发布任务</a></div>
            </div>            
        </div>
        <!-- 右侧顶部账号积分等信息 end -->
        
        <!-- 网站公告常见问题 start -->
        <div class="business-right-comm" style="margin-top:10px;">
            <div class="business-right-money clearfix">
                <div class="business-exchange-content">
                    <ul class="tab-list" style="padding-left:10px;">
                        <li class="active notice-li"><a href="#">网站公告</a></li>
                        <!-- <li class="problem-li"><a href="#">常见问题</a></li> -->
                    </ul>
                    <span style="display: block; margin-left:135px; color:#f00; margin-top:-25px;">请先购买流量符VIP会员再加群，否则一律拒绝！流量符VIP商家交流qq群：328892256</span>
                </div>
                <table style="margin-top:6px;width:100%" class="notice-table">
                                        <tbody><tr style="height:35px; color:#555; font-size:14px;">
                      <td><a href="${ctx }/help/help_center_notice_11.jsp" target="_blank">【教程】商家如何设置引流的关键词</a></td>
                      <td style="width:90px;text-align:center;">2015/04/17</td>
                    </tr>
                                        <tr style="height:35px; color:#555; font-size:14px;">
                      <td><a href="${ctx }/help/help_center_notice_9.jsp" target="_blank">【重要】访客IP地区的相对集中不会影响店铺的点击率</a></td>
                      <td style="width:90px;text-align:center;">2015/04/15</td>
                    </tr>
                                        <tr style="height:35px; color:#555; font-size:14px;">
                      <td><a href="${ctx }/help/help_center_notice_2.jsp" target="_blank">流量符商家会员必读</a></td>
                      <td style="width:90px;text-align:center;">2015/04/15</td>
                    </tr>
                                        <tr style="height:35px; color:#555; font-size:14px;">
                      <td><a href="${ctx }/help/help_center_notice_4.jsp" target="_blank">网站购买会员的价格调整公告</a></td>
                      <td style="width:90px;text-align:center;">2015/04/14</td>
                    </tr>
                                        <tr style="height:35px; color:#555; font-size:14px;">
                      <td><a href="${ctx }/help/propaganda.jsp" target="_blank">什么才是真实有效的搜索点击流量？</a></td>
                      <td style="width:90px;text-align:center;">2015/04/14</td>
                    </tr>
                                        <tr style="height:35px; color:#555; font-size:14px;">
                      <td><a href="${ctx }/help/raiders.jsp" target="_blank">怎样用我们的流量才会更有效果？</a></td>
                      <td style="width:90px;text-align:center;">2015/04/14</td>
                    </tr>
                                        <tr style="height:35px; color:#555; font-size:14px;">
                      <td><a href="${ctx }/help/propagandas.jsp" target="_blank">解析虚假流量如何导致商品降权</a></td>
                      <td style="width:90px;text-align:center;">2015/04/14</td>
                    </tr>
                                    </tbody></table>
                <table style="margin-top:6px;width:100%; display:none;" class="problem-table">
                                    </table>
            </div>
        </div>
        <!-- 网站公告常见问题 end -->
        
        
        
        <!-- 未审核通过的任务 start -->
                <!-- 未审核通过的任务 end -->
        
        
        
        
        <!-- 任务管理 start -->
        <div class="tasklist">                
            <h1 style="font-size:2em; border:none;">任务管理</h1>
                <ul class="business-tabs">
                  <li id="task_tabs_all" class="active"><a href="${ctx}/web/shop/taskmanage/task_list?left-list-id=1&task_status=all">
                  <span>所有任务（${allLLTaskCount}）</span></a></li>
                  <li id="task_tabs_2"><a href="${ctx}/web/shop/taskmanage/task_list?left-list-id=1&task_status=2">
                  <span>进行中的任务（${executingLLTaskCount}）</span></a></li>
                  <li id="task_tabs_3"><a href="${ctx}/web/shop/taskmanage/task_list?left-list-id=1&task_status=3">
                  <span>已完成的任务（${finishLLTaskCount}）</span></a></li>
                </ul>
			<jsp:include page="/include/task_list.jsp" flush="true"></jsp:include>
        </div>
        <!-- 任务管理 end -->
        
        
	</div>
</div>
			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>

<script type="text/javascript"> 

$(function(){
	initConditionForm();
	loadShop();
})

function initConditionForm(){
	$("#bindPlat").val("${llTaskRecordBean.bindPlat}");
	$("#queryKey").val("${llTaskRecordBean.queryKey}");
	$("#status").val("${llTaskRecordBean.status}");
	$("#taskType").val("${llTaskRecordBean.taskType}");
}

function search(){
	searchPage(1)
}

function searchPage(page){
	$("#current_page").val(page);
	$("#condition_form").submit();
}

function loadShop() {
	var url = "${ctx}/web/shop/taskmanage/load_shop";
	$.ajax( {
		url : url,
		type : 'post',
		dataType : 'text',
		timeout : 60000,
		error : function(e) {
			alert("连接服务器超时,加载店铺列表失败,请稍后再试.");
		},
		success : function(result) {
			if (!isOutTime(result)) {
				result = eval("("+result+")");
				if (result.success) {
					$("#shopId").html(result.msg);
					$("#shopId").val("${llTaskRecordBean.shopId}");
				}
			}
		}
	});
}
</script>

</body></html>