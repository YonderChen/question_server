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
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="#">发布任务</a> &gt; <a href="#">填写流量信息</a> </div>
</div>
	<form id="to_step_one" action="${ctx}/web/shop/taskmanage/add_task_step_one" method="post">
	</form>
    <form id="to_step_three" action="${ctx}/web/shop/taskmanage/add_task_step_three" method="post" enctype="multipart/form-data">
    <div class="container">
        <div class="Shadowbox">
            <div class="Shadowboxp"></div>
            <div class="register">
            
                <div class="register-title">
                    
                        <span class="bram-title">填写流量信息</span>
                        <div class="Process">
                            <ul class="clearfix">
                                <li class="cur" style="width:20%"><em class="Processyes">1<i></i></em><span class="">选择平台</span><strong></strong></li>
                                <li class="cur" style="width:20%"><em class="Processyes">2<i></i></em><span class="">选择店铺</span><strong></strong></li>
                                <li class="cur" style="width:20%"><em class="Processyes">3<i></i></em><span class="">选择流量类型</span><strong></strong></li>
                                <li class="" style="width:20%"><em class="Processing">4<i></i></em><span class="">填写流量信息</span><strong></strong></li>
                                <li class="" style="width:20%"><em class="">5<i></i></em><span class="">选择增值服务</span><strong></strong></li>
                                <li class="Processlast" style="width:82px"><em>6<i></i></em><span>发布任务</span><strong></strong></li>
                            </ul>
                        </div>
                </div>
                
                
                <div class="issue-task-two"> 
                    <div class="shop-type">任务类型已选择：<i class="plat_small plat_tmall"></i>
                    <s:if test="#request.llTask.llShop.bindPlat == 'taobao'">淘宝</s:if>
                    <s:if test="#request.llTask.llShop.bindPlat == 'tmall'">天猫</s:if>
                    <s:if test="#request.llTask.llShop.bindPlat == 'jd'">京东</s:if> | ${llTask.llShop.bindName} | <s:if test="#request.llTask.taskType == 0">自然搜索流量</s:if><s:else>自然搜索流量</s:else><a class="back_step_one" href="javascript:pre_step();">返回编辑</a></div>
                    <input type="hidden" id="bindPlat" name="bindPlat" value="${llTask.llShop.bindPlat}">
                    <input type="hidden" id="shopId" name="shopId" value="${llTask.llShop.shopId}">
                    <input type="hidden" id="taskType" name="taskType" value="${llTask.taskType}">
                    <input type="hidden" id="taskId" name="taskId" value="${llTask.taskId}">
                    <div class="flow-info checkout-steps">
                        <h1>第四步：填写流量信息</h1>
                        <h3>商品信息</h3>
                        <div>
                            <div class="step-complete-Writ-box-1">
                            
                            
                                <div class="step-item inp">
                                    <strong>1.商品链接： </strong>
                                    <input type="text" id="goodsUrl" name="goodsUrl" class="text w460 J_NAME_INPUT" value="${llTask.goodsUrl}">
                                    <span class="newtips">必填</span>
                                    <span id="hd_item_url" class="error" style="display:none;"></span>
                                </div>
                                <div class="step-item inp">
                                    <strong>2.商品名： </strong>
                                    <input type="text"  id="goodsName" name="goodsName" class="text w460 J_URL_INPUT" value="${llTask.goodsName}">
                                    <span class="newtips">必填</span>
                                    <span id="hd_item_title" class="error" style="display:none;"></span>
                                </div>    
                                <div class="step-item inp">
                                    <strong>3.商品主图： </strong>
                                    <input type="file" id="goodsImgFile" name="goodsImgFile" class="input_file" >
                                    <span id="hd_item_img" class="error" style="display:none;"></span>
                                </div>
                                
                            </div>
                        </div>
                        
                        <h3>流量设置</h3>
                        <h4>1.设置访客来源及每日访客数<span class="red">${oneVisitCostScore }积分/访客</span></h4>
                        <div class="flow-source">
                            <p class="prompt"><label>提示：</label><span>请填写3-5个访客入店搜索的关键词, 流量将根据您设置的关键词和入口找到您的商品并点击进入您的店铺<a href="/help/help_center_pro?left_list_id=13&amp;display_pro_id=trade2" target="_blank">为什么要填写3-5个关键词？</a></span><span class="red" style="padding-left:35px;">请务必确保综合搜索关键词后，您的商品排名在前10页，否则容易导致的流量误差过大，严重影响店铺转化率！</span></p>
									<div class="keyword">
										<div id="div_keyword1">
											<label class="kwd_num">
												关键词1：
											</label>
											<input type="text" class="text w200" id="keyword1" name="keyword1" value="${llTask.keyword1}">
											&nbsp;&nbsp;
											<label>
												每日访客数：
											</label>
											<input type="text" class="text w100" id="orderNumberOneDay1" name="orderNumberOneDay1" 
												onchange="javascript:cal_source();" value="${llTask.orderNumberOneDay1}"
												onkeyup="this.value=this.value.replace(/\D/g,'')"
												onafterpaste="this.value=this.value.replace(/\D/g,'')">
											&nbsp;访客/天
											<span style="font-size: 12px; color: #666; margin: 0;">（最低10访客/天）</span>
											<span class="red">必填</span>
											<span id="hd_kwd_info1" class="error" style="display: none;"></span>
										</div>
										<div id="div_keyword2">
											<label class="kwd_num">
												关键词2：
											</label>
											<input type="text" class="text w200" id="keyword2" name="keyword2" value="${llTask.keyword2}">
											&nbsp;&nbsp;
											<label>
												每日访客数：
											</label>
											<input type="text" class="text w100" id="orderNumberOneDay2" name="orderNumberOneDay2" 
												onchange="javascript:cal_source();" value="${llTask.orderNumberOneDay2}"
												onkeyup="this.value=this.value.replace(/\D/g,'')"
												onafterpaste="this.value=this.value.replace(/\D/g,'')">
											&nbsp;访客/天
											<span style="font-size: 12px; color: #666; margin: 0;">（最低10访客/天）</span>
											<span class="red">必填</span>
											<span id="hd_kwd_info2" class="error" style="display: none;"></span>
										</div>
										<div id="div_keyword3">
											<label class="kwd_num">
												关键词3：
											</label>
											<input type="text" class="text w200" id="keyword3" name="keyword3" value="${llTask.keyword3}">
											&nbsp;&nbsp;
											<label>
												每日访客数：
											</label>
											<input type="text" class="text w100" id="orderNumberOneDay3" name="orderNumberOneDay3" 
												onchange="javascript:cal_source();" value="${llTask.orderNumberOneDay3}"
												onkeyup="this.value=this.value.replace(/\D/g,'')"
												onafterpaste="this.value=this.value.replace(/\D/g,'')">
											&nbsp;访客/天
											<span style="font-size: 12px; color: #666; margin: 0;">（最低10访客/天）</span>
											<span class="red">必填</span>
											<span id="hd_kwd_info3" class="error" style="display: none;"></span>
										</div>
										<div id="div_keyword4" style="display: none;">
											<label class="kwd_num">
												关键词4：
											</label>
											<input type="text" class="text w200" id="keyword4" name="keyword4" value="${llTask.keyword4}">
											&nbsp;&nbsp;
											<label>
												每日访客数：
											</label>
											<input type="text" class="text w100" id="orderNumberOneDay4" name="orderNumberOneDay4" 
												onchange="javascript:cal_source();" value="${llTask.orderNumberOneDay4}"
												onkeyup="this.value=this.value.replace(/\D/g,'')"
												onafterpaste="this.value=this.value.replace(/\D/g,'')">
											&nbsp;访客/天
											<span style="font-size: 12px; color: #666; margin: 0;">（最低10访客/天）</span>
											<span class="red"><a style="color: blue;" onclick="div_keyword4_del()">删除</a></span>
											<span id="hd_kwd_info3" class="error" style="display: none;"></span>
										</div>
										<div id="div_keyword5" style="display: none;">
											<label class="kwd_num">
												关键词5：
											</label>
											<input type="text" class="text w200" id="keyword5" name="keyword5" value="${llTask.keyword5}">
											&nbsp;&nbsp;
											<label>
												每日访客数：
											</label>
											<input type="text" class="text w100" id="orderNumberOneDay5" name="orderNumberOneDay5" 
												onchange="javascript:cal_source();" value="${llTask.orderNumberOneDay5}" 
												onkeyup="this.value=this.value.replace(/\D/g,'')" 
												onafterpaste="this.value=this.value.replace(/\D/g,'')">
											&nbsp;访客/天
											<span style="font-size: 12px; color: #666; margin: 0;">（最低10访客/天）</span>
											<span class="red"><a style="color: blue;" onclick="div_keyword5_del()">删除</a></span>
											<span id="hd_kwd_info3" class="error" style="display: none;"></span>
										</div>
									</div>

									<p class="step-list-add"><a id="add_key_words" href="javascript:addKeyword();"><img src="${ctx}/static_shop/images/icon_add.gif">支付${oneKeywordCostScore }积分添加1个关键词</a>（最多可添加2个）</p>
                        </div>
                        
                        <h4>2.设置流量来源分布占比<span>（所有渠道流量来源占比需为100%）</span></h4>
								<div class="distribution">
									<p class="prompt">
										<label>
											提示：
										</label>
										<span>搜索关键词将随机分配给不同的搜索渠道</span>
									</p>
									<div class="percentage">
										<div>
											<label>
												电脑端淘宝自然搜索占比：
											</label>
											<input type="text"  id="searchSource" name="pc_percent" class="pctext"
												value="${llTask.searchSource}"
												onkeyup="this.value=this.value.replace(/\D/g,'')"
												onafterpaste="this.value=this.value.replace(/\D/g,'')">
											%
										</div>
										<div>
											<label>
												电脑端天猫自然搜索占比：
											</label>
											<input type="text" disabled="disabled"  id="searchSource_cut" name="pc_tm_percent" class="pctext"
												value="${100-llTask.searchSource}" onkeyup="this.value=this.value.replace(/\D/g,'')"
												onafterpaste="this.value=this.value.replace(/\D/g,'')">
											%
										</div>
										<!-- <div><label>手机淘宝自然搜索占比：</label><input type="text" name="phone_percent" class="apptext" value="" onKeyUp="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" />%</div> -->
										<!-- <div><label><input type="checkbox" name="perc" />天猫自然搜索占比：</label><input type="text" class="apptext" />%</div> -->
									</div>
									<div>
										<span id="hd_percent" class="error"
											style="margin-left: 0; display: none;">所有渠道流量来源占比需为100%</span>
									</div>
								</div>

								<h4>3.选择流量购买时长<span>（任务发布后，可根据店铺实际情况，在任务管理中修改每日访客数）</span></h4>
								<div class="select-time">
									<p class="prompt">
										<label>
											提示：今日18:00前发布的流量任务今日会开始执行，18:00点之后发布的流量会明日开始执行。
										</label>
									</p>
									<div class="seletime">
										<label>
											<input type="radio" name="pay_days" onclick="changeDurationDay(7);" value="7">
											7天
										</label>
										<label>
											<input type="radio" name="pay_days" onclick="changeDurationDay(10);" value="10"
												checked="checked">
											10天
										</label>
										<label>
											<input type="radio" name="pay_days" onclick="changeDurationDay(15);" value="15">
											15天
										</label>
										<label>
											<input type="radio" name="pay_days" onclick="changeDurationDay(30);" value="30">
											30天
										</label>
										<input type="hidden" id="durationDay" name="durationDay" value="${llTask.durationDay}">
									</div>
								</div>

								<div class="release-bottom">
                            <!-- <div class="release-bott1">每日访客数<span>700</span>，购买时长<span>7</span>天，总需：<strong>7000</strong>积分</div> -->
                            <!-- <div class="release-bott2 release-botts">基础流量费：<div><span>100</span>积分/关键词*<span>3</span>关键词+1000积分/天*<span>7</span>天=<strong>2000</strong>积分</div></div> -->
                            <div class="release-bott3">
                                <div>每日访客数: <span id="visit_day_num" class="visit_num">0</span></div>
                                <div>购买时长: <span id="pay_days_num" class="pay_days_num">10</span> 天</div>
                                <div>基础流量费: <span id="visit_day_num1" class="visit_num">0</span> <span> × </span> <span id="pay_days_num1" class="pay_days_num">10</span> <span> × ${oneVisitCostScore } </span> <span> = </span> <span id="sum_score" class="sum_score">0.00</span> 积分</div>
                                <div id="add_kwd" style="display: none;">添加关键词<span id="add_kwd_num" class="add_kwd_num"></span>个，需：<span id="add_kwd_score" class="add_kwd_score"></span> 积分</div>
                                <div>小计：<strong id="sum_score_xiaoji" class="xiaoji">0</strong> 积分</div>
                            </div>
                        </div>
                        
                    </div>
                    
                    <div class="checkout-btnbox">
                        <div class="publish-btnbox">
                            <span class="sk-arrange-gray mo-t"><a class="sk-arrange-next to-prev back_step_one" href="javascript:pre_step();">上一步</a></span>
                            <span class="sk-arrange-gray mo-t"><a class="sk-arrange-next goto_step_three itembtn" href="javascript:next_step();">下一步</a></span>
                        </div>     
                    </div>
                </div>
                
            </div>
            <div class="Shadowboxb"></div>
        </div>
    </div>
    </form>
			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>
<script type="text/javascript">


	$(document).ready(function(){
		$("#durationDay").val($("input[name='pay_days'][checked]").val());
	  	$("#searchSource").change(function(){
	  		$("#searchSource_cut").val(100 - $("#searchSource").val());
	  	})
	});
	
	function cal_source(){
		var add_kwd_num = 0;
		var add_kwd_score = 0;
		var visit_day_num = parseInt($("#orderNumberOneDay1").val().trim()) + parseInt($("#orderNumberOneDay2").val().trim()) + parseInt($("#orderNumberOneDay3").val().trim());
		
		if($("#orderNumberOneDay4").val().trim() != "" && parseInt($("#orderNumberOneDay4").val().trim()) > 0){
			add_kwd_num++;
			add_kwd_score += parseInt("${oneKeywordCostScore }");
			visit_day_num += parseInt($("#orderNumberOneDay4").val().trim());
		}
		if($("#orderNumberOneDay5").val().trim() != "" && parseInt($("#orderNumberOneDay5").val().trim()) > 0){
			add_kwd_num++;
			add_kwd_score += parseInt("${oneKeywordCostScore }");
			visit_day_num += parseInt($("#orderNumberOneDay5").val().trim());
		}
		var sum_score = visit_day_num * parseInt($("#durationDay").val()) * parseInt("${oneVisitCostScore }");
		var sum_score_xiaoji = add_kwd_score + sum_score;
		$("#visit_day_num").html(visit_day_num);
		$("#visit_day_num1").html(visit_day_num);
		$("#add_kwd_score").html(add_kwd_score);
		$("#add_kwd_score1").html(add_kwd_score);
		$("#add_kwd_num").html(add_kwd_num);
		$("#add_kwd_num1").html(add_kwd_num);
		$("#pay_days_num").html($("#durationDay").val());
		$("#pay_days_num1").html($("#durationDay").val());
		$("#sum_score").html(sum_score);
		$("#sum_score_xiaoji").html(sum_score_xiaoji);
		if(add_kwd_num > 0){
			$("#add_kwd").show();
		} else {
			$("#add_kwd").hide();
		}
	}

	function changeDurationDay(durationDay){
		if(durationDay == $("input[name='pay_days'][checked]").val()){
			$("#durationDay").val(durationDay);
			return;
		}
		$("input[name='pay_days'][checked]").removeAttr("checked");
		$("input[name='pay_days'][value='"+durationDay+"']").attr("checked", "checked");
		$("#durationDay").val(durationDay);
		cal_source();
	}
	
	function addKeyword(){
		if($("#div_keyword4").is(":hidden")){
			$("#div_keyword4").show();
		} else {
			$("#div_keyword5").show();
		}
		cal_source();
	}
	
	function div_keyword4_del(){
		if(!$("#div_keyword4").is(":hidden")){
			$("#div_keyword4").hide();
		}
		$("#keyword4").val("");
		$("#orderNumberOneDay4").val("");
	}
	
	function div_keyword5_del(){
		if(!$("#div_keyword5").is(":hidden")){
			$("#div_keyword5").hide();
		}
		$("#keyword5").val("");
		$("#orderNumberOneDay5").val("");
	}
	
	function next_step() {
		if($("#bindPlat").val().trim() == ""){
			alert("请选择平台");
			to_process(1);
			$("input[name='plat_radio'][value='tmall']").attr('checked',true);
			return;
		}
		if($("#shopId").val().trim() == ""){
			alert("请选择店铺");
			$("#shopId").select();
			return;
		}
		if($("#goodsUrl").val().trim() == ""){
			alert("请输入商品URL");
			$("#goodsUrl").select();
			return;
		}
		if($("#goodsName").val().trim() == ""){
			alert("请输入商品名称");
			$("#goodsName").select();
			return;
		}
		if($("#goodsImgFile").val().trim() == ""){
			alert("请选择商品主图");
			$("#goodsImgFile").select();
			return;
		}
		if($("#keyword1").val().trim() == ""){
			alert("请输入关键词1");
			$("#keyword1").select();
			return;
		}
		if($("#orderNumberOneDay1").val().trim() == ""){
			alert("请输入关键词1每日访客数");
			$("#orderNumberOneDay1").select();
			return;
		}
		if(parseInt($("#orderNumberOneDay1").val().trim()) < 10){
			alert("关键词1每日访客数必须大于等于10");
			$("#orderNumberOneDay1").select();
			return;
		}
		if($("#keyword2").val().trim() == ""){
			alert("请输入关键词2");
			$("#keyword2").select();
			return;
		}
		if($("#orderNumberOneDay2").val().trim() == ""){
			alert("请输入关键词2每日访客数");
			$("#orderNumberOneDay2").select();
			return;
		}
		if(parseInt($("#orderNumberOneDay2").val().trim()) < 10){
			alert("关键词2每日访客数必须大于等于10");
			$("#orderNumberOneDay2").select();
			return;
		}
		if($("#keyword3").val().trim() == ""){
			alert("请输入关键词3");
			$("#keyword3").select();
			return;
		}
		if($("#orderNumberOneDay3").val().trim() == ""){
			alert("请输入关键词3每日访客数");
			$("#orderNumberOneDay3").select();
			return;
		}
		if(parseInt($("#orderNumberOneDay3").val().trim()) < 10){
			alert("关键词3每日访客数必须大于等于10");
			$("#orderNumberOneDay3").select();
			return;
		}
		if($("#orderNumberOneDay4").val().trim() != ""){
			var num4 = parseInt($("#orderNumberOneDay4").val().trim())
			if(num4 < 10 && num4 > 0){
				alert("关键词4每日访客数必须大于等于10");
				$("#orderNumberOneDay4").select();
				return;
			}
		}
		if($("#orderNumberOneDay5").val().trim() != ""){
			var num5 = parseInt($("#orderNumberOneDay5").val().trim())
			if(num5 < 10 && num5 > 0){
				alert("关键词5每日访客数必须大于等于10");
				$("#orderNumberOneDay5").select();
				return;
			}
		}
		
		$("#to_step_three").submit();
	}
	
	function pre_step(){
		$("#to_step_one").submit();
	}
</script>

</body></html>