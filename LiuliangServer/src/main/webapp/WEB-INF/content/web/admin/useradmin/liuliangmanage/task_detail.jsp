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
    <link rel="stylesheet" href="${ctx}/static_shop/style/release.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/detail.css">
	<script type="text/javascript">

	function isOutTime(result) {
		if (result.indexOf("win.location.href='${ctx}/web/admin/index'") > 0) {
			alert("登录超时,请重新登录.");
			var win = window;
			while (win != window.parent) {
				win = window.parent;
			}
			win.location.href='${ctx}/web/admin/index';
			return true;
		}
		return false;
	}
	
	function check_task(taskId){
		var url = "${ctx}/web/admin/useradmin/taskmanage/check_task";
		$.ajax( {
			url : url,
			type : 'post',
			data : {
				taskId : taskId,
			},
			dataType : 'text',
			timeout : 60000,
			error : function(e) {
				alert("审核失败，连接异常");
			},
			success : function(result) {
				if (!isOutTime(result)) {
					result = eval("("+result+")");
					if (result.success) {
						$("#task_status_"+taskId).html("执行中");
						$("#check_a_"+taskId).remove();
						alert(result.msg);
					} else {
						alert(result.msg);
					}
				}
			}
		});
	}
</script>
</head>
<body>
<div class="breadcrumbs">
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="#">任务详情</a></div>
</div>
<div class="task-detail">
    <div class="task-detail-left">
    	<h3>任务信息</h3>
        <p>任务编号：${llTask.taskId }</p>
        <p>任务发布时间：<s:date name="#request.llTask.createTime" format="yyyy-MM-dd HH:mm:ss"/></p>
        <p>店铺名：<i class="plat_small plat_tmall"></i>${llTask.llShop.bindName }</p>
        <p>客户端类型：
		<s:if test="#request.llTask.clientType == 'pc'">pc端</s:if>
		<s:else>
			<s:if test="#request.llTask.clientType == 'phone'">手机端</s:if>
			<s:else>
				未知端
			</s:else>
		</s:else>
        </p>
        <p>流量类型：
            <s:if test="#request.llTask.taskType == 0">自然搜索流量</s:if>
            <s:else>自然搜索流量</s:else>
        </p>
        <p>任务计划总流量：<span>
        ${(llTask.orderNumberOneDay1 + llTask.orderNumberOneDay2 
			+llTask.orderNumberOneDay3 + llTask.orderNumberOneDay4 
			+ llTask.orderNumberOneDay5) * llTask.durationDay}
		</span>访客</p>
        <p>计划访客数：<span>
        ${llTask.orderNumberOneDay1 + llTask.orderNumberOneDay2 
			+llTask.orderNumberOneDay3 + llTask.orderNumberOneDay4 
			+ llTask.orderNumberOneDay5}
		</span>访客/日</p>
        <!-- <p>流量起止时间：-0001-11-30 - -0001-11-30</p> -->
        
        <s:if test="#request.llTask.llShop.bindPlat == 'taobao' || #request.llTask.llShop.bindPlat == 'tmall'">
	        <div class="source">
		        <label>流量来源：</label>
		        <div>淘宝自然搜索率 ${llTask.searchSource }%</div>
		        <label>流量来源：</label>
		        <div>天猫自然搜索率 ${100 - llTask.searchSource }%</div>
	        </div>
        </s:if>
        <p>${llTask.keyword1 } : ${llTask.orderNumberOneDay1 } 访客 / 天</p>
        <p>${llTask.keyword2 } : ${llTask.orderNumberOneDay2 } 访客 / 天</p>
        <p>${llTask.keyword3 } : ${llTask.orderNumberOneDay3 } 访客 / 天</p>
        <s:if test="#request.llTask.orderNumberOneDay4 > 0">
        	<p>${llTask.keyword4 } : ${llTask.orderNumberOneDay4 } 访客 / 天</p>
		</s:if>
        <s:if test="#request.llTask.orderNumberOneDay5 > 0">
        	<p>${llTask.keyword5 } : ${llTask.orderNumberOneDay5 } 访客 / 天</p>
		</s:if>
    </div>
    <div class="task-detail-right">
    	<div class="task-state">
        	<h4 class="f16"><i></i>任务状态：
        		<span id="task_status_${llTask.taskId}">
		            <s:if test="#request.llTask.status == 0">未发布</s:if>
		            <s:if test="#request.llTask.status == 1">待审核</s:if>
		            <s:if test="#request.llTask.status == 2">任务进行中</s:if>
		            <s:if test="#request.llTask.status == 3">已完成</s:if>
		            <s:if test="#request.llTask.status == 4">已取消</s:if>
		            <s:if test="#request.llTask.status == 5">审核不通过</s:if>
		            <s:if test="#request.llTask.status == 6">任务修改,待审核</s:if>
	            </span>
			</h4>
			<s:if test="#request.llTask.status == 1">
            	<p id="check_a_${llTask.taskId}" class="main-f">点击审核：<a href="javascript:check_task('${llTask.taskId}')"><em>通过审核</em></a></p>
            </s:if>
        </div>
    </div>
</div>
<div class="task-list">
	<h3>任务商品</h3>
    <table>
    	<tbody><tr>
        	<td width="65%" style="text-align:left; padding-left:80px;">商品名</td>
            <td width="35%">每日计划访客数</td>
        </tr>
        <tr>
        	<td>
            	<div class="intableProList">
                    <img class="img" width="50" height="50" src="${ctx }${llTask.goodsImg }">
                    <p class="text">衣服</p>
                </div>
            </td>
            <td>
	            <span>
		            ${llTask.orderNumberOneDay1 + llTask.orderNumberOneDay2 
						+llTask.orderNumberOneDay3 + llTask.orderNumberOneDay4 
						+ llTask.orderNumberOneDay5}
				</span>访客/日
			</td>
        </tr>
    </tbody></table>
</div>
<div class="task-detail-fee">
	<h3>费用合计</h3>
		<table>
			<tbody>
				<tr>
					<th width="20%">
						分类
					</th>
					<th width="25%">
						费用明细
					</th>
					<th width="30%">
						小计
					</th>
					<th width="25%">
						合计
					</th>
				</tr>
				<tr>
					<td>
						总访客数
					</td>
					<td>
						<p>
							访客数：${(llTask.orderNumberOneDay1 + llTask.orderNumberOneDay2
							+llTask.orderNumberOneDay3 + llTask.orderNumberOneDay4 +
							llTask.orderNumberOneDay5) * llTask.durationDay}访客
						</p>
					</td>
					<td>
						${oneVisitCostScore }积分/访客
					</td>
					<td>
						${(llTask.orderNumberOneDay1 + llTask.orderNumberOneDay2
						+llTask.orderNumberOneDay3 + llTask.orderNumberOneDay4 +
						llTask.orderNumberOneDay5) * llTask.durationDay}访客 X
						${oneVisitCostScore } =
						<span>${(llTask.orderNumberOneDay1 +
							llTask.orderNumberOneDay2 +llTask.orderNumberOneDay3 +
							llTask.orderNumberOneDay4 + llTask.orderNumberOneDay5) *
							llTask.durationDay * oneVisitCostScore}</span>（ 积分 ）
					</td>
				</tr>
				<tr>
					<td>
						增值服务
					</td>
					<td>
						<s:if test="#request.llTask.pageStayType > 0">
							<s:if test="#request.llTask.pageStayType == 1">
				            	【页面停留时间优化（停留时间60~120秒，${pageStayCostScoreMap['1'] }积分）】<br>
				            </s:if>
							<s:if test="#request.llTask.pageStayType == 2">
				            	【页面停留时间优化（停留时间120~180秒，${pageStayCostScoreMap['2'] }积分）】<br>
				            </s:if>
			            </s:if>
						<s:if test="#request.llTask.visitTimeType > 0">
							<s:if test="#request.llTask.visitTimeType == 1">
				            	【流量访问时间优化（随机分布，${visitTimeCostScoreMap['1'] }积分）】<br>
				            </s:if>
							<s:if test="#request.llTask.visitTimeType == 2">
				            	【流量访问时间优化（网购用户习惯曲线分布，${visitTimeCostScoreMap['2'] }积分）】<br>
				            </s:if>
			            </s:if>
						<s:if test="#request.llTask.isQuickVerify > 0">
			            	【快速完成任务（优先审单，${quickVerifyCostScore }积分）】<br>
			            </s:if>
						<s:if test="#request.llTask.isQuickExecute > 0">
			            	【快速完成任务（流量优先执行，${quickExecuteCostScore }积分）】<br>
			            </s:if>
						<s:if test="#request.llTask.orderNumberOneDay4 > 0">
			            	【添加关键词（每个${oneKeywordCostScore }积分）】<br>
			            </s:if>
						<s:if test="#request.llTask.orderNumberOneDay5 > 0">
			            	【添加关键词（每个${oneKeywordCostScore }积分）】<br>
			            </s:if>
					</td>
					<td>
						<s:if test="#request.llTask.pageStayType == 1">
			            	【+ ${pageStayCostScoreMap['1'] }】
			            </s:if>
						<s:if test="#request.llTask.pageStayType == 2">
			            	【+ ${pageStayCostScoreMap['2'] }】
			            </s:if>
						<s:if test="#request.llTask.visitTimeType == 1">
			            	【+ ${visitTimeCostScoreMap['1'] }】
			            </s:if>
						<s:if test="#request.llTask.visitTimeType == 2">
			            	【+ ${visitTimeCostScoreMap['2'] }】
			            </s:if>
						<s:if test="#request.llTask.isQuickVerify > 0">
			            	【+ ${quickVerifyCostScore }】
			            </s:if>
						<s:if test="#request.llTask.isQuickExecute > 0">
			            	【+ ${quickExecuteCostScore }】
			            </s:if>
						<s:if test="#request.llTask.orderNumberOneDay4 > 0">
			            	【+ ${oneKeywordCostScore }】<br>
			            </s:if>
						<s:if test="#request.llTask.orderNumberOneDay5 > 0">
			            	【+ ${oneKeywordCostScore }】<br>
			            </s:if>
						积分
					</td>
					<td>
						<span>${llTask.costScore - (llTask.orderNumberOneDay1 +
							llTask.orderNumberOneDay2 +llTask.orderNumberOneDay3 +
							llTask.orderNumberOneDay4 + llTask.orderNumberOneDay5) *
							llTask.durationDay * oneVisitCostScore}</span>（ 积分 ）
					</td>
				</tr>
			</tbody>
		</table>
		<div class="task-detail-bott">费用合计：<span>${llTask.costScore }</span>积分</div>
</div>
			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>

</body></html>