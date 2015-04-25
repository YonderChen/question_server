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
</head>
<body>
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="#">发布任务</a> &gt; <a href="#">选择增值服务</a> </div>
</div>
<form id="to_step_two" action="${ctx}/web/shop/taskmanage/add_task_step_two" method="post">
	<input type="hidden" name="taskId" value="${llTask.taskId}">
</form>
<div class="container">
    <div class="Shadowbox">
        <div class="Shadowboxp"></div>
        <div class="register">
        
            <div class="register-title">
            	
            		<span class="bram-title">选择增值服务</span>
                    <div class="Process">
                        <ul class="clearfix">
                            <li class="cur" style="width:20%"><em class="Processyes">1<i></i></em><span class="">选择平台</span><strong></strong></li>
                            <li class="cur" style="width:20%"><em class="Processyes">2<i></i></em><span class="">选择店铺</span><strong></strong></li>
                            <li class="cur" style="width:20%"><em class="Processyes">3<i></i></em><span class="">选择流量类型</span><strong></strong></li>
                            <li class="cur" style="width:20%"><em class="Processyes">4<i></i></em><span class="">填写流量信息</span><strong></strong></li>
                            <li class="" style="width:20%"><em class="Processing">5<i></i></em><span class="">选择增值服务</span><strong></strong></li>
                            <li class="Processlast" style="width:82px"><em>6<i></i></em><span>发布任务</span><strong></strong></li>
                        </ul>
                    </div>
            </div>
            
            <h1 class="issue-task-thh1">第五步：增值服务</h1>
            <form id="step_three_form" action="${ctx}/web/shop/taskmanage/add_task_success" method="post">
                    <input type="hidden" id="taskId" name="taskId" value="${llTask.taskId}">
            <div class="issue-task-three">
            	<div class="store-data">
                	<h3>店铺数据优化</h3>
                	<div class="residence-time">
                    	<h4>1.页面停留时间优化</h4>
							<div>
								<h5>
									提示：流量到达店铺后将默认停留30~60秒，选择此项服务后将有助于优化店铺平均停留时间
								</h5>
								<p>
									<label>
										<input name="sleep_time" id="pageStayType_checkbox1" class="J_accessTotal" onclick="changepageStayType_checkbox1();" type="checkbox" value="${pageStayCostScoreMap['1'] }">
										设置停留时间至60~120秒<span>${pageStayCostScoreMap['1'] }积分</span>
									</label>
									<label>
										<input name="sleep_time" id="pageStayType_checkbox2" class="J_accessTotal" onclick="changepageStayType_checkbox2();" type="checkbox" value="${pageStayCostScoreMap['2'] }">
										设置停留时间至120~180秒<span>${pageStayCostScoreMap['2'] }积分</span>
									</label>
								</p>
								<input type="hidden" name="pageStayType" id="pageStayType" value="${llTask.pageStayType }">
							</div>
						</div>
                    
                </div>



				<div class="store-data">
					<h3>
						流量分布优化
					</h3>
					<div class="residence-time">
						<h4>
							1.流量访问时间优化
						</h4>
						<div>
							<h5>
								提示：流量进入店铺时间默认全天平均分布，选择此项服务后，流量将按照固定的分布规律进入店铺；
								<!-- 收费：<span>100</span>积分 -->
							</h5>
							<p>
								<label>
									<input name="visit_time" id="visitTimeType_checkbox1" class="J_accessTotal" onclick="changevisitTimeType_checkbox1();" type="checkbox" value="${visitTimeCostScoreMap['1'] }">
									随机分布<span>${visitTimeCostScoreMap['1'] }积分</span>
								</label>
								<label>
									<input name="visit_time" id="visitTimeType_checkbox2" class="J_accessTotal" onclick="changevisitTimeType_checkbox2();" type="checkbox" value="${visitTimeCostScoreMap['2'] }">
									网购用户习惯曲线分布<span>${visitTimeCostScoreMap['2'] }积分</span>
								</label>
							</p>
						</div>
						<input type="hidden" name="visitTimeType" id="visitTimeType" value="${llTask.visitTimeType }">
					</div>
				</div>


				<div class="store-data" style="border:none;">
                	<h3>快速完成任务</h3>
						<div class="residence-time">
							<h4>
								1.优先审单
							</h4>
							<div>
								<h5>
									提示：选择此项服务后。平台将优先审核您发布的流量任务
								</h5>
								<p>
									<label>
										<input name="first_check" id="isQuickVerify_checkbox" onclick="changeIsQuickVerify()" class="J_accessTotal" type="checkbox" value="${quickVerifyCostScore }">
										任务优先审核（<span>${quickVerifyCostScore }</span>积分）
									</label>
								</p>
							</div>
							<input type="hidden" name="isQuickVerify" id="isQuickVerify" value="${llTask.isQuickVerify }">
						</div>

						<div class="residence-time">
							<h4>
								2.流量优先执行
							</h4>
							<div>
								<h5>
									提示：平台默认所有商家的流量任务按照任务发布时间排队执行，选择此项服务后，当系统繁忙时，优先执行你的任务；
								</h5>
								<p>
									<label>
										<input name="first_execute" id="isQuickExecute_checkbox" onclick="changeIsQuickExecute()" class="J_accessTotal" type="checkbox" value="${quickExecuteCostScore }">
										任务优先执行（<span>${quickExecuteCostScore }</span>积分）
									</label>
								</p>
							</div>
							<input type="hidden" name="isQuickExecute" id="isQuickExecute" value="${llTask.isQuickExecute }">
						</div>
					</div>
                
            </div>
            </form>
            
            <div class="fee-total"><strong>费用合计：</strong>基础流量费<span>${llTask.costScore}</span>积分+增值服务<span id="service_score">0</span>积分=<span id="sum_score" class="f18">0</span>积分</div>
            
            <div class="checkout-btnbox">
                <div class="publish-btnbox">
                    <span class="sk-arrange-gray mo-t"><a href="javascript:pre_step();" class="sk-arrange-next to-prev back_step_two">上一步</a></span>
                    <span class="sk-arrange-gray mo-t"><a href="javascript:publish_task();" class="sk-arrange-next J_FIVE_NEXT total goto_step_four"><span>立即发布</span></a></span>
                </div>     
            </div>
            
        </div>
        <div class="Shadowboxb"></div>
    </div>
</div>
			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>

<script type="text/javascript">


	$(document).ready(function(){
		cal_source();
	});
	
	function cal_source(){
		var costScore = parseInt("${llTask.costScore}");
		var service_score = 0;
		if($("#pageStayType").val().trim() != ""){
			var pageStayType = parseInt($("#pageStayType").val());
			if(pageStayType > 0){
				service_score += parseInt($("#pageStayType_checkbox" + pageStayType).val());
			}
		}
		if($("#visitTimeType").val().trim() != ""){
			var visitTimeType = parseInt($("#visitTimeType").val());
			if(visitTimeType > 0){
				service_score += parseInt($("#visitTimeType_checkbox" + visitTimeType).val());
			}
		}
		if($("#isQuickVerify").val().trim() != ""){
			var isQuickVerify = parseInt($("#isQuickVerify").val());
			if(isQuickVerify > 0){
				service_score += parseInt($("#isQuickVerify_checkbox").val());
			}
		}
		if($("#isQuickExecute").val().trim() != ""){
			var isQuickExecute = parseInt($("#isQuickExecute").val());
			if(isQuickExecute > 0){
				service_score += parseInt($("#isQuickExecute_checkbox").val());
			}
		}
		var sum_score = costScore + service_score;
		$("#service_score").html(service_score);
		$("#sum_score").html(sum_score);
	}

	function changepageStayType_checkbox1(){
		$("#pageStayType_checkbox2").removeAttr("checked");
		if($("#pageStayType_checkbox1").attr("checked") == "checked"){
			$("#pageStayType_checkbox1").removeAttr("checked");
			$("#pageStayType").val("0");
		} else {
			$("#pageStayType_checkbox1").attr("checked", "checked");
			$("#pageStayType").val("1");
		}
		cal_source();
	}
	
	function changepageStayType_checkbox2(){
		$("#pageStayType_checkbox1").removeAttr("checked");
		if($("#pageStayType_checkbox2").attr("checked") == "checked"){
			$("#pageStayType_checkbox2").removeAttr("checked");
			$("#pageStayType").val("0");
		} else {
			$("#pageStayType_checkbox2").attr("checked", "checked");
			$("#pageStayType").val("2");
		}
		cal_source();
	}
	
	function changevisitTimeType_checkbox1(){
		$("#visitTimeType_checkbox2").removeAttr("checked");
		if($("#visitTimeType_checkbox1").attr("checked") == "checked"){
			$("#visitTimeType_checkbox1").removeAttr("checked");
			$("#visitTimeType").val("0");
		} else {
			$("#visitTimeType_checkbox1").attr("checked", "checked");
			$("#visitTimeType").val("1");
		}
		cal_source();
	}
	
	function changevisitTimeType_checkbox2(){
		$("#visitTimeType_checkbox1").removeAttr("checked");
		if($("#visitTimeType_checkbox2").attr("checked") == "checked"){
			$("#visitTimeType_checkbox2").removeAttr("checked");
			$("#visitTimeType").val("0");
		} else {
			$("#visitTimeType_checkbox2").attr("checked", "checked");
			$("#visitTimeType").val("2");
		}
		cal_source();
	}
	
	function changeIsQuickVerify(){
		if($("#isQuickVerify_checkbox").attr("checked") == "checked"){
			$("#isQuickVerify_checkbox").removeAttr("checked");
			$("#isQuickVerify").val("0");
		} else {
			$("#isQuickVerify_checkbox").attr("checked", "checked");
			$("#isQuickVerify").val("1");
		}
		cal_source();
	}
	
	function changeIsQuickExecute(){
		if($("#isQuickExecute_checkbox").attr("checked") == "checked"){
			$("#isQuickExecute_checkbox").removeAttr("checked");
			$("#isQuickExecute").val("0");
		} else {
			$("#isQuickExecute_checkbox").attr("checked", "checked");
			$("#isQuickExecute").val("1");
		}
		cal_source();
	}
	
	function publish_task() {
		$("#step_three_form").submit();
	}
	
	function pre_step(){
		$("#to_step_two").submit();
	}
</script>

</body></html>