<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
    <meta http-equiv="expires" content="0">
	<title>帮助中心-流量符</title>
    <meta name="keywords" content="流量符帮助中心，如何刷流量，流量，提升店铺排名，权重、淘宝流量、京东流量、手机淘宝流量">
    <meta name="description" content="流量符帮助中心，帮助快速提升店铺销量，提高店铺权重，流量推广好助手">
	<link rel="shortcut icon" type="image/x-icon" href="../images/favicon.ico" media="screen">
	<script type="text/javascript" src="../js/base.js"></script>
	<script type="text/javascript" src="../js/jquery.js"></script>
    <link rel="stylesheet" href="../static_shop/style/common.css">
    <link rel="stylesheet" href="../static_shop/style/help_center.css">
</head>
<body>
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href="${ctx }">首页</a> &gt; <a id="dqlj_a" href="${ctx}/help/help_center.jsp?left_list_id=-2">常见问题</a></div>
</div>
    <div class="wrap clearfix"> 
    
	<jsp:include page='help_content_left.jsp' flush="true"></jsp:include>
					
    <div id="help_center_7_13" class="help-center-right" style="display: none;">
    	<div class="business-right-comm">
        	<div class="help-center">
	        	<div id="help_content_7">
					<jsp:include page='help_content_7.jsp' flush="true"></jsp:include>
				</div>
	        	<div id="help_content_8">
					<jsp:include page='help_content_8.jsp' flush="true"></jsp:include>  
				</div>
	        	<div id="help_content_9">
					<jsp:include page='help_content_9.jsp' flush="true"></jsp:include>  
				</div>
	        	<div id="help_content_10">
					<jsp:include page='help_content_10.jsp' flush="true"></jsp:include>  
				</div>
	        	<div id="help_content_11">
					<jsp:include page='help_content_11.jsp' flush="true"></jsp:include> 
				</div>
	        	<div id="help_content_12">
					<jsp:include page='help_content_12.jsp' flush="true"></jsp:include> 
				</div> 
	        	<div id="help_content_13">
					<jsp:include page='help_content_13.jsp' flush="true"></jsp:include>   
				</div>    
        	</div>
        </div>
	</div>
    
   <div id="guide_help_center_1_6" class="newbie-guide-right" style="display: none;">
    	<div class="business-right-comm">
        	<div class="help-center">
            	<h1>新手指南</h1>
	        	<div id="help_content_1">
					<jsp:include page='help_content_1.jsp' flush="true"></jsp:include>
				</div>
	        	<div id="help_content_2">
					<jsp:include page='help_content_2.jsp' flush="true"></jsp:include>  
				</div>
	        	<div id="help_content_3">
					<jsp:include page='help_content_3.jsp' flush="true"></jsp:include>  
				</div>
	        	<div id="help_content_4">
					<jsp:include page='help_content_4.jsp' flush="true"></jsp:include>  
				</div>
	        	<div id="help_content_5">
					<jsp:include page='help_content_5.jsp' flush="true"></jsp:include> 
				</div>
	        	<div id="help_content_6">
					<jsp:include page='help_content_6.jsp' flush="true"></jsp:include> 
				</div> 
            </div>
        </div>
	</div>
    
   <div id="guide_help_center_0" class="newbie-guide-right" style="display: none;">
    	<div class="business-right-comm">
        	<div class="help-center">
           		<h1>网站公告</h1>
	        	<div id="help_content_0">
					<jsp:include page='help_content_0.jsp' flush="true"></jsp:include>
				</div>
            </div>
        </div>
	</div>
</div>

			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>


</body></html>