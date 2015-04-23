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
            <form id="step_three_form" action="/trade/step_four" method="post">
            <div class="issue-task-three">
            	<div class="store-data">
                	<h3>店铺数据优化</h3>
                	<div class="residence-time">
                    	<h4>1.页面停留时间优化</h4>
                        <div>
                        	<h5>提示：流量到达店铺后将默认停留30~60秒，选择此项服务后将有助于优化店铺平均停留时间</h5>
                            <p>
                            									<label><input name="sleep_time" class="J_accessTotal" type="checkbox" s="50" value="2">设置停留时间至60~120秒<span>50积分</span></label>
                            									<label><input name="sleep_time" class="J_accessTotal" type="checkbox" s="60" value="3">设置停留时间至120~180秒<span>60积分</span></label>
                            	                            </p>
                        </div>
                    </div>
                    <!-- 
                    <div class="residence-time">
                    	<h4>2.店铺访问深度优化</h4>
                        <div>
                        	<h5>提示：流量进入店铺后默认访问1-2页，选择此项服务后，将有助于优化店铺平均访问深度</h5>
                            <p>
                            	<label><input disabled="disabled" name="upgrade_ranking" class="J_accessTotal" pvalue="10" type="checkbox"  value="3" />访问2-3页<span>3积分</span></label>
                                <label><input disabled="disabled" name="upgrade_ranking" class="J_accessTotal" pvalue="10" type="checkbox"  value="6" />访问3-4页<span>6积分</span></label>
                                <label><input disabled="disabled" name="upgrade_ranking" class="J_accessTotal" pvalue="10" type="checkbox"  value="9" />访问4-5页<span>9积分</span></label>
                            </p>
                        </div>
                    </div>
                    
                    <div class="residence-time">
                    	<h4>3.店铺跳失率优化</h4>
                        <div>
                        	<h5>提示：流量进入店铺后默认跳失率60%左右，选择此项服务后，将可降低店铺跳失率</h5>
                            <p>
                            	<label><input disabled="disabled" name="upgrade_ranking" class="J_accessTotal" pvalue="10" type="checkbox"  value="3" />访问2-3页<span>3积分</span></label>
                                <label><input disabled="disabled" name="upgrade_ranking" class="J_accessTotal" pvalue="10" type="checkbox"  value="6" />访问3-4页<span>6积分</span></label>
                                <label><input disabled="disabled" name="upgrade_ranking" class="J_accessTotal" pvalue="10" type="checkbox"  value="9" />访问4-5页<span>9积分</span></label>
                            </p>
                        </div>
                    </div>
                    -->
                    
                </div>
                
                
                
                <div class="store-data">
                	<h3>流量分布优化</h3>
                	<div class="residence-time">
                    	<h4>1.流量访问时间优化</h4>
                        <div>
                        	<h5>提示：流量进入店铺时间默认全天平均分布，选择此项服务后，流量将按照固定的分布规律进入店铺；<!-- 收费：<span>100</span>积分 --></h5>
                            <p>
                             									<label><input name="visit_time" class="J_accessTotal" type="checkbox" s="50" value="2">随机分布<span>50积分</span></label>
                            									<label><input name="visit_time" class="J_accessTotal" type="checkbox" s="60" value="3">网购用户习惯曲线分布<span>60积分</span></label>
                            	                            </p>
                        </div>
                    </div>                    
                </div>
                
                
                <div class="store-data" style="border:none;">
                	<h3>快速完成任务</h3>
                	<div class="residence-time">
                    	<h4>1.优先审单</h4>
                        <div>
                        	<h5>提示：选择此项服务后。平台将优先审核您发布的流量任务</h5>
                            <p>
                            	<label><input name="first_check" class="J_accessTotal" type="checkbox" value="50">任务优先审核（<span>50</span>积分）</label>
                            </p>
                        </div>
                    </div>
                    
                	<div class="residence-time">
                    	<h4>2.流量优先执行</h4>
                        <div>
                        	<h5>提示：平台默认所有商家的流量任务按照任务发布时间排队执行，选择此项服务后，当系统繁忙时，优先执行你的任务；</h5>
                            <p>
                            	<label><input name="first_execute" class="J_accessTotal" type="checkbox" value="50">任务优先执行（<span>50</span>积分）</label>
                            </p>
                        </div>
                    </div>
                </div>
                
            </div>
            </form>
            
            <div class="fee-total"><strong>费用合计：</strong>基础流量费<span>1080</span>积分+增值服务<span id="service_score">0</span>积分=<span id="sum_score" class="f18">1080</span>积分</div>
            
            <div class="checkout-btnbox">
                <div class="publish-btnbox">
                    <span class="sk-arrange-gray mo-t"><a class="sk-arrange-next to-prev back_step_two">上一步</a></span>
                    <span class="sk-arrange-gray mo-t"><a class="sk-arrange-next J_FIVE_NEXT total goto_step_four"><span>立即发布</span></a></span>
                </div>     
            </div>
            
        </div>
        <div class="Shadowboxb"></div>
    </div>
</div>
			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>


</body></html>