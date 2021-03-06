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
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="${ctx }/web/shop/taskmanage/liuliang_list?left-list-id=3">充值记录</a></div>
</div>
    <div class="wrap clearfix">
			<jsp:include page="/include/left.jsp" flush="true"></jsp:include> 
	<div class="business-right">
          <div class="business-right-comm">
              <div class="business-info">
                <div class="integral-record-hd">流量明细</div>
                
                <div class="business-info-bd">
                    <div class="integral-serch">
                        <form id="condition_form" action="${ctx }/web/shop/taskmanage/liuliang_list?left-list-id=3" method="post">
                            <div class="integral-search-type">
                                <label>关键词：</label>
                                <input type="text" id="keyword" name="keyword" class="Wdate" value="${llLiuliangBean.keyword}">
                            </div> 
                            <div class="integral-search-time">
                                <label>起止时间：</label>
                                <input type="text" name="beginTime" class="Wdate" onclick="javascript:WdatePicker();" value="<s:date name="#request.llLiuliangBean.beginTime" format="yyyy-MM-dd"/>">
                                至
                                <input type="text" name="endTime" class="Wdate" onclick="javascript:WdatePicker();" value="<s:date name="#request.llLiuliangBean.endTime" format="yyyy-MM-dd"/>">
                            </div>
                            <div class="integral-search-type">
                                <label>状态：</label>
							    <select id="status" name="status">
									<option value="" selected="selected">请选择</option>
									<option value="0" >添加失败</option>
									<option value="1" >添加成功</option>
								</select>
                            </div>
                            <input type="hidden" id="current_page" name="page" value="${pageBean.currentPage}">
                            <input type="submit" class="integral-search-butt" style="float: right;" onclick="javascript:resetPage();" value="提交查询">
                        </form>
                    </div>
                    <p class="integral-careful" style="display: none;">
                    	<span>注意：</span>
                    	为了减少服务器负荷，只能查找最近一个月内的流量使用记录。
                    </p>
                    <table class="integral-record-table">
						<thead>
                    	<tr>
                            <th width="13%">任务ID</th>
                            <th width="12%">店铺类型</th>
                            <th width="12%">任务类型</th>
                            <th width="10%">宝贝ID</th>
                            <th width="13%">关键词</th>
                            <th width="10%">计划点击</th>
                            <th width="10%">完成点击</th>
                            <th width="10%">日期</th>
                            <th width="10%">状态</th>
                        </tr>
                        </thead>
                        <tbody>
							<s:if test="#request.pageBean.allRow > 0">
								<s:iterator value="#request.pageBean.list" id="liuliang">
								<tr>
									<td>
										${liuliang.llTask.taskId}
									</td>
									<td>
						                <s:if test="llTask.llShop.bindPlat == 'taobao'">淘宝</s:if>
						                <s:elseif test="llTask.llShop.bindPlat == 'tmall'">天猫</s:elseif>
						                <s:elseif test="llTask.llShop.bindPlat == 'jd'">京东</s:elseif>
						                <s:else>未知</s:else>
									</td>
									<td>
						                <s:if test="llTask.clientType == 'pc'">PC端</s:if>
						                <s:elseif test="llTask.clientType == 'phone'">手机端</s:elseif>
						                <s:else>未知</s:else>
									</td>
									<td>
										${liuliang.goodId}
									</td>
									<td>
										${liuliang.keyword}
									</td>
									<td>
										${liuliang.num}
									</td>
									<td>
										${liuliang.numCurrent}
									</td>
									<td>
										<s:date name="date" format="yyyy-MM-dd"/>
									</td>
									<td>
										<s:if test="doStatus == 0" >添加失败</s:if>
										<s:if test="doStatus == 1" >待处理</s:if>
										<s:if test="doStatus == 2" >处理中</s:if>
										<s:if test="doStatus == 3" >已完成</s:if>
									</td>
								</tr>
								</s:iterator>
							</s:if>
							<s:else>
	                        	<tr>
		                        	<td colspan="5" style="width:100%; text-align:center; font-size:16px; border:none; color:#555; padding-top:20px;">暂无记录</td>
		                        </tr>
							</s:else>
                                            </tbody></table> 
                    
							
					<jsp:include page="/include/pager.jsp" flush="true"></jsp:include>
                    
                </div>
              </div>
              
            </div>
      </div>
</div>
			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>
<script type="text/javascript"> 

$(function(){
	$("#status").val("${llLiuliangBean.status}");
})

function resetPage(){
	$("#current_page").val(1);
}

function searchPage(page){
	$("#current_page").val(page);
	$("#condition_form").submit();
}

</script>

</body></html>