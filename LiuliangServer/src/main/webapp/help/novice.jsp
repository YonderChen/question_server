<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
    <meta http-equiv="expires" content="0">
	<title>流量符-新商家必读</title>
    <meta name="keywords" content="流量符帮助中心，如何刷流量，流量，提升店铺排名，权重、淘宝流量、京东流量、手机淘宝流量">
    <meta name="description" content="流量符帮助中心，帮助快速提升店铺销量，提高店铺权重，流量推广好助手">
	<link rel="shortcut icon" type="image/x-icon" href="../images/favicon.ico" media="screen">
	<script type="text/javascript" src="../js/base.js"></script>
	<script type="text/javascript" src="../js/jquery.js"></script>
    <link rel="stylesheet" href="../static_shop/style/common.css">
    <link rel="stylesheet" href="../static_shop/style/help_center.css">
</head>
<body style=" background:#fff;">
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
			
			<div class="novice">
    	<h1>新手入门：网站流程介绍及收费说明</h1>
        <div class="novice-con">
        	<h3>什么是流量符网站？</h3>
            <p>流量符是一家专业提供最真实有效搜索流量的网站。我们的流量是根据商家提供的关键词进行自然搜索，找到商品后真实 点击进店停留一段时间，并随机浏览店铺其他商品，可以被淘宝后台准确的统计到，并形成良好的数据反馈，助力提升搜索排名。</p>
            
            <h3>网站的流量是如何进店的？</h3>
            <div class="pc-iphone"><label>电脑端流量：</label><span>发布任务成功后，流量就会根据所填关键词，通过自然搜索的方式，找到该商品，点击进店，并在店铺内停留1~2分钟，且随机<br>浏览店内其他商品。所有数据都会被记录到淘宝后台的搜索统计，可以在淘宝后台的生意参谋软件里找到详细数据记录。</span></div>
            <div class="pc-iphone" style="margin-top:20px;"><label>手机端流量：</label><span>发布手机端任务后，流量会根据所填关键词，通过自然搜索的方式，在手机淘宝APP里找到该商品，点击进店，并在店铺内停留<br>1~2分钟，且随机浏览店内其他商品。绝非虚假的浏览器流量，上千部手机的真实IP，可以被准确的计入淘宝后台。</span></div>
            
            <h3>选择我们的理由</h3>
            <div class="select">
            	<div class="select-left">
                	<div class="leftdiv">
                    	<h4>计入搜索：</h4>
                    	<span>真正可以被淘宝搜索统计，计入搜索引擎排序<br>的点击流量</span>
                    </div>
                    <div class="leftdiv">
                    	<h4>提高权重：</h4>
                    	<span>每次搜索经过是有效的真实点击，有效提升商品在<br>搜索引擎排序中的点击率权重</span>
                    </div>
                    <div class="leftdiv">
                    	<h4>长久停留：</h4>
                    	<span>流量入店铺后停留时间长达3分钟,是真正的优质流量</span>
                    </div>
                    <div class="leftdiv" style="border-bottom:none;">
                    	<h4>规律分布：</h4>
                    	<span>每日流量的入店时间根据网购用户的行为习惯规律分布</span>
                    </div>
                	
                </div>
                <div class="select-left" style="margin-left:140px;">
                	<div class="leftdiv">
                    	<h4>自然搜索：</h4>
                    	<span>通过自然搜索商品关键词找到商品，点击商品进入店铺</span>
                    </div>
                    <div class="leftdiv">
                    	<h4>优化数据：</h4>
                    	<span>流量真实点击，有效提高店铺的服务反馈数据的同时，<br>优化店铺跳失率数据</span>
                    </div>
                    <div class="leftdiv">
                    	<h4>深度访问：</h4>
                    	<span>流量进店后会随机点开其他页面进行深度浏览</span>
                    </div>
                    <div class="leftdiv" style="border-bottom:none;">
                    	<h4>简单方便：</h4>
                    	<span>流程安全方便，简单易懂，好用！</span>
                    </div>
                </div>
            </div>
            
            <h3 style="margin:0 0 5px;">网站操作流程</h3>
            <img src="${ctx}/static_shop/images/help_center/novice1.jpg">
            
            <h3 style="margin-bottom:0px;">支持的平台</h3>
            <img src="${ctx}/static_shop/images/help_center/novice2.jpg">
            
            <h3>如何收费</h3>
            <div class="novice-fee">网站的收费分为两部分：<a href="${ctx }/web/shop/accountmanage/dealmanage/renewalvip" target="_blank">购买会员 </a>和<a href="${ctx }/web/shop/accountmanage/dealmanage/get_score" target="_blank"> 购买积分</a>。<b>购买会员是获得网站的使用资格；购买积分是用于发布流量任务。</b></div>
            
            <img src="${ctx}/static_shop/images/help_center/novice3.jpg">
            <img src="${ctx}/static_shop/images/help_center/novice4.jpg">
            
            <div class="novice-pro">
            	<span>注：1访客=3积分，即2~3毛钱一个访客流量。</span>
                <p>如何绑定店铺？<a href="${ctx}/help/newbie_guide_7.jsp" target="_blank">请点击</a></p>
                <p>如何发布流量任务？<a href="${ctx}/help/newbie_guide_12.jsp" target="_blank">请点击</a></p>
                <p>如何管理已发布的任务？<a href="${ctx}/help/newbie_guide_16.jsp" target="_blank">请点击</a></p>
            </div>
            
            <h3>常见问题</h3>
            <div class="novice-problem">
            	<h5>问：流量符网站的付款方式除了网银支付外，还可以支付宝转账吗？</h5>
                <p>答：暂时不支持支付宝付款接口；建议您使用网银在线支付，若是只能使用支付宝的话，麻烦联系在线客服的QQ：800036159。</p>
                
                <h5>问：你们支持哪些电商平台啊？</h5>
                <p>答：目前只支持淘宝天猫平台，后续如果有开发其他平台流量业务，会另行通知的。</p>
                
                <h5>问：可以刷手机淘宝流量或者直通车流量吗？</h5>
                <p>答：目前平台可支持刷淘宝天猫的PC端流量、手机淘宝流量，直通车流量暂不支持，如有业务拓展，会另行通知的。</p>
                
                <h5>问：你们平台的一个ID最多可以绑定几个店铺？</h5>
                <p>答：一个ID在一个平台最多可以绑定三个店铺，如果您店铺比较多的话，可以再重新注册一个账号。</p>
                
                <h5>问：付完会员费之后，要发布任务的话，是不是还要先买积分？</h5>
                <p>答：对的，会员费和购买积分是分开的，要发布流量任务的话，需要先购买积分，1访客=3积分。</p>
                
                <div>更多常见问题，可以参考网站的<a href="${ctx}/help/help_center.jsp?left_list_id=-2" target="_blank">帮助中心</a>。</div>
            </div>
            
        </div>
    	
        
       
    </div>
			
			<jsp:include page='/include/footer.jsp' flush="true"></jsp:include>


</body></html>