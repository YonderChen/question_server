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
                  <li class="active"><a href="${ctx}/web/shop/taskmanage/task_list">
                  <span>所有任务（${allLLTaskCount}）</span></a></li>
                  <!-- <li><a href="/center/trade_manage/ing">
                  <span>进行中的任务（0）</span></a></li>
                  <li><a href="/center/trade_manage/fin">
                  <span>已完成的任务（0）</span></a></li> -->
                </ul>
                <div class="business-info-bd">
                    <div class="integral-serch">
                        <form id="condition_form" action="${ctx }/web/shop/taskmanage/task_list?left-list-id=1" method="get">
                        <div class="clearfix task-list" style="padding:18px 2px">
                            <span style="margin-left:0;">平台：</span>
                            <select class="J-binding-type" id="bindPlat" name="bindPlat" style="width: 100px;">
                                <option value="">请选择</option>
                                <option value="taobao">淘宝</option>
                                <option value="tmall">天猫</option>
                                <option value="jd">京东</option>
                            </select>
                            <span>店铺：</span>
                            <select name="shopId" id="shopId" name="shopId" style="width: 150px;">
                            </select>
                            
                            <span>任务状态：</span>
                            <select id="status" name="status">
                                <option value="" selected="">请选择</option>
                                <option value="0">未发布</option>
                                <option value="1">待审核</option>
                                <option value="2">任务进行中</option>
                                <option value="3">已完成</option>
                                <option value="4">已取消</option>
                                <option value="5">审核不通过</option>
                                <option value="6">任务修改,待审核</option>
                            </select>

                            <span>流量类型：</span>
                            <select id="taskType" name="taskType">
                                <option value="" selected="">请选择</option>
                                <option value="0">自然搜索流量</option>
                            </select>
                            
                            <div style="height:10px; width:100%;"></div>
                            <span style="margin-left:0;">任务发布时间：</span>
                            <input style="width:100px; border:1px solid #CCC;" name="beginTime" value="<s:date name="#request.llTaskRecordBean.beginTime" format="yyyy-MM-dd"/>" onclick="javascript:WdatePicker()">
                            <span style="margin-left:0;">-</span>
                            <input style="width:100px; border:1px solid #CCC;" name="endTime" value="<s:date name="#request.llTaskRecordBean.endTime" format="yyyy-MM-dd"/>" onclick="javascript:WdatePicker()">

                            <span style="margin-left:16px;">高级搜索：</span>
                            <select name="queryKey" id="queryKey" style="width: 100px;">
                               <option value="" selected="">请选择</option>
                               <option value="taskId">任务编号</option>
                               <option value="goodsName">商品名</option>
                            </select>
                            <em style="margin-left:16px;display: inline-block;position: relative;zoom: 1;height: 20px;border: 1px solid #CCCCCC;border-radius: 2px;vertical-align: middle;">
								<input name="queryValue" id="queryValue" type="text" style="height:20px;width:120px;" value="${llTaskRecordBean.queryValue}">
                            </em>
                            
                            <input type="hidden" id="current_page" name="page" value="${pageBean.currentPage}">
                            <a id="J-submiit-btn" class="business-mess-sent-btn" href="javascript:search();" style="margin-left:8px;">搜索</a>
                        </div>
                        </form>
                    </div>
                    
                    <div class="task-list-title" style="margin-top: 60px;">
                        <span style="width:30%;">商品</span><span style="width:10%;">总访客数</span><span style="width:18%;">每日计划访客数</span><span style="width:17%;">已完成天数/总天数</span><span style="width:15%;">状态</span><span style="width:10%;">操作</span>
                    </div>
                    <div class="business-tabs-tasklinesheet-more task-list-table" style="margin-top:10px;">
                    
							<s:if test="#request.pageBean.list.size > 0">
								<s:iterator value="#request.pageBean.list" id="record">
										<table border="0" bordercolor="#C9E7F7" cellspacing="0" cellpadding="0">
											<tbody>
												<tr>
													<th colspan="6">
														<span><i class="plat_small plat_${record.llShop.bindPlat}"></i>${record.llShop.bindName}&nbsp;&nbsp;任务编号：${record.taskId}<a href="${ctx}/web/shop/taskmanage/task_detail?taskId=${record.taskId}" class="trade-detail" target="_blank">[详情]</a>&nbsp;&nbsp;发布时间：<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></span>
													</th>
												</tr>
												<tr>
													<td class="nb" style="width: 30%;">
														<div class="intableProLists">
															<img src="${ctx}${record.goodsImg}" width="50" height="50" class="img">
															<p class="text">
																${record.goodsName}
															</p>

														</div>
													</td>
													<td style="font-size: 14px; width: 10%;">
														${(record.orderNumberOneDay1 + record.orderNumberOneDay2 
															+record.orderNumberOneDay3 + record.orderNumberOneDay4 
															+ record.orderNumberOneDay5) * record.durationDay}
													</td>
													<td style="font-size: 14px; width: 18%;">
														${record.orderNumberOneDay1 + record.orderNumberOneDay2 
															+record.orderNumberOneDay3 + record.orderNumberOneDay4 
															+ record.orderNumberOneDay5}
													</td>
													<td style="font-size: 14px; width: 15%;">
														0 / ${record.durationDay}
													</td>
													<td style="font-size: 14px; width: 15%;">
						                                <s:if test="status == 0">未发布</s:if>
						                                <s:if test="status == 1">待审核</s:if>
						                                <s:if test="status == 2">任务进行中</s:if>
						                                <s:if test="status == 3">已完成</s:if>
						                                <s:if test="status == 4">已取消</s:if>
						                                <s:if test="status == 5">审核不通过</s:if>
						                                <s:if test="status == 6">任务修改,待审核</s:if>
													</td>
													<td style="font-size: 14px; width: 12%;">
						                                <s:if test="status == 0">
															<a href="${ctx }/web/shop/taskmanage/add_task_step_two?taskId=${record.taskId}" class="jx-trade">继续发布</a>
														</s:if>
													</td>
												</tr>
											</tbody>
										</table>
									</s:iterator>
							</s:if>
							<s:else>
	                        	<div>
		                        	<span style="width:100%; text-align:center; font-size:16px; border:none; color:#555; padding-top:20px;">暂无记录</span>
		                        </div>
							</s:else>
							
						<jsp:include page="/include/pager.jsp" flush="true"></jsp:include>
					</div>
                    
                </div>
              </div>
              
            </div>
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