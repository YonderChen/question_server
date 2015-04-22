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
  	<script type="text/javascript" src="${ctx}/js/jquery-form.js"></script>
   	<script type="text/javascript" src="${ctx}/js/calendar/WdatePicker.js"></script>
	<link rel="stylesheet" href="${ctx}/static_shop/style/common.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/person_center.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/popup.css">
</head>
<body>
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="${ctx }/web/shop/center">基本信息</a></div>
</div>
    <div class="wrap clearfix">
			<jsp:include page="/include/left.jsp" flush="true"></jsp:include> 
	<div class="business-right">
          <div class="business-right-comm">
              <div class="business-info">
                <div class="integral-record-hd">积分记录</div>
                
                <div class="business-info-bd">
                    <div class="integral-serch">
                        <form action="${ctx}/web/shop/accountmanage/dealmanage/score_record" method="post">
                            <div class="integral-search-type">
                                <label>请选择类型：</label>
                                <select id="type_select" name="type">
                                    <option value="" selected="">全部</option>
                                    <option value="1">购买</option>
                                    <option value="2">消费</option>
                                </select>
                                <input type="hidden" name="type_hidden" id="type_hidden" value="${type}">
                            </div>
                            <div class="integral-search-time">
                                <label>起止时间：</label>
                                <input type="text" name="beginTime" class="Wdate" onclick="WdatePicker()" value="<s:date name="#request.beginTime" format="yyyy-MM-dd"/>">
                                至
                                <input type="text" name="endTime" class="Wdate" onclick="WdatePicker()" value="<s:date name="#request.endTime" format="yyyy-MM-dd"/>">
                            </div>
                            
                            <input type="submit" class="integral-search-butt" value="提交查询">
                        </form>
                    </div>
                    <p class="integral-careful"><span>注意：</span>为了减少服务器负荷，只能查找最近一个月内的流量使用记录。
                    </p>
                    
                    <table class="integral-record-table">
						<thead>
                    	<tr>
                            <th width="30%">时间</th>
                            <th width="10%">类型</th>
                            <th width="10%">数量</th>
                            <th width="20%">剩余积分</th>
                            <th width="30%">备注</th>
                        </tr>
                        </thead>
                        <tbody>
							<s:if test="#request.pageBean.list.size > 0">
								<s:iterator value="#request.pageBean.list" id="record">
								<tr>
									<td>
										<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<s:if test="type == 1">购买</s:if>
										<s:else>
											<s:if test="type == 2">消费</s:if>
										</s:else>
									</td>
									<td>
										${record.num}
									</td>
									<td>
										${record.remain}
									</td>
									<td>
										${record.remark}
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
                    <div class="pager"></div>
                    
                </div>
              </div>
              
            </div>
      </div>
</div>
			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>
<script type="text/javascript"> 

$(function(){
	$("#type_select").val($("#type_hidden").val());
})
</script>

</body></html>