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
   	<script type="text/javascript" src="${ctx}/js/calendar/WdatePicker.js"></script>
	<link rel="stylesheet" href="${ctx}/static_shop/style/common.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/person_center.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/popup.css">
</head>
<body>
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="${ctx }/web/shop/taskmanage/task_list?left-list-id=1">管理任务</a></div>
</div>
    <div class="wrap clearfix">
		<jsp:include page="/include/left.jsp" flush="true"></jsp:include> 
		<div class="business-right">
          <div class="business-right-comm">
              <div class="business-info">
                <div class="integral-record-hd">任务管理</div>
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
              
            </div>
      </div>
</div>
			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>
<script type="text/javascript"> 

$(function(){
	initConditionForm();
	loadShop();
	$("#task_tabs_all").removeClass();
	$("#task_tabs_2").removeClass();
	$("#task_tabs_3").removeClass();
	var task_status = "${task_status}";
	$("#task_tabs_" + task_status).addClass("active");
	if(task_status != "all"){
		$("#status_condition_div").hide();
	}
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