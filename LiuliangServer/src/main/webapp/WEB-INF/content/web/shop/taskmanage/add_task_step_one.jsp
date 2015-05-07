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
	<link rel="stylesheet" href="${ctx}/static_shop/style/common.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/person_center.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/popup.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/register.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/release.css">
</head>
<body>
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="#">发布任务</a> &gt; <a href="#">选择任务类型</a> </div>
</div>
<div class="container">
        <div class="Shadowbox">
            <div class="Shadowboxp"></div>
            <div class="register">
            
                <div class="register-title">
                    <span class="bram-title">选择任务类型</span>
                    <div class="Process">
                        <ul class="clearfix">
                            <li class="cur" style="width:20%"><em class="Processyes">1<i></i></em><span class="">选择平台</span><strong></strong></li>
                            <li class="cur" style="width:20%"><em class="Processyes">2<i></i></em><span class="">选择店铺</span><strong></strong></li>
                            <li class="" style="width:20%"><em class="Processing">3<i></i></em><span class="">选择流量类型</span><strong></strong></li>
                            <li class="" style="width:20%"><em class="">4<i></i></em><span class="">填写流量信息</span><strong></strong></li>
                            <li class="" style="width:20%"><em class="">5<i></i></em><span class="">选择增值服务</span><strong></strong></li>
                            <li class="Processlast" style="width:82px"><em>6<i></i></em><span>发布任务</span><strong></strong></li>
                        </ul>
                    </div>
                </div>    
                
                <div class="new-Release-first-bd">
    
                  <div class="new-Release-select-hd clearfix">
                      <div class="new-Release-select-list-hd  Release-plat">第一步：选择平台</div>
                      <div class="new-Release-select-list-hd  Release-shop">第二步：选择店铺</div>
                      <div class="new-Release-select-list-hd  Release-type">第三步：选择流量类型</div>
                  </div>
                  <form id="trade_form" action="${ctx}/web/shop/taskmanage/add_task_step_two" method="post">
                  <div class="new-Release-select clearfix">
                      <div class="new-Release-select-list Release-plat">
						<ul class="clearfix">
							<li id="taobao_li">
								<i></i>
								<label>
									<em>${taobao_shop_num}</em>
									<a> 
										<input name="plat" id="radio_taobao" type="radio" onclick="javascript:changePlat('taobao', true);" value="taobao">
										<b class="taobao"></b> 
									</a>
								</label>
							</li>

							<li id="tmall_li" class="cur">
								<i></i>
								<label>
									<em class="num">${tmall_shop_num}</em>
									<a> 
										<input name="plat" id="radio_tmall" type="radio" onclick="javascript:changePlat('tmall', true);" value="tmall" checked="checked"> 
										<b class="tmall"></b> 
									</a>
								</label>
							</li>

							<li id="jd_li">
								<i></i>
								<label>
									<em>${jd_shop_num}</em>
									<a> 
										<input name="plat" id="radio_jd" type="radio" onclick="javascript:changePlat('jd', true);" value="jd">
										<b class="jd"></b> 
									</a>
								</label>
							</li>

						</ul>
						<ul class="clearfix">
							<li id="taobao_li">
							</li>
							<li style="height:80px" id="taobao_li">
								<i></i>
								<div class="new-Release-select-list-hd  Release-clientType">客户端类型：</div>
								<div class="new-Release-select-list-hd  Release-clientType-raido"> 
									<label style="font-size: 16px; color: black; font-weight:bold; cursor:pointer">
										<input name="client_type" id="client_radio_pc" type="radio" onclick="javascript:changeClient('pc');" value="pc" checked="checked">pc端
									</label>
									<label style="font-size: 16px; color: black; font-weight:bold; cursor:pointer">
										<input name="client_type" id="client_radio_phone" type="radio" onclick="javascript:changeClient('phone');" value="phone">手机端
									</label>
								</div>
							</li>
						</ul>
						<input type="hidden" id="bindPlat" name="bindPlat" value="">
						<input type="hidden" id="clientType" name="clientType" value="">
					</div>
                   
                   <div class="new-Release-select-list Release-shop store_type_taobao">
                   		<div id="shopInfo">
						</div>
						<input type="hidden" id="shopId" name="shopId" value="${shopId}">
                      </div>
							<div class="new-Release-select-list Release-type Release-type-taobao">
								<ul class="clearfix">

									<li class="s_three">
										<label>
											<input type="radio" name="traffic_type" value="0" checked="checked">
											<em style="position: relative;">自然搜索流量</em>
											<em style="float: right; font-weight: normal; color: #999;">630积分起</em>
										</label>
									</li>
								</ul>
							</div>
							<input type="hidden" id="taskType" name="taskType" value="0">
						</div>  
                  </form>
                  <div class="new-Release-btn">
                    <div class="publish-btnbox">
                      <span class="sk-arrange-gray mo-t"><a id="next_step_a" href="javascript:nextStep();" class="sk-arrange-next ">下一步</a><em>(填写流量信息)</em></span>
                    </div>
                  </div>  
            	<!-- 任务列表 -->
                </div>
              </div>
                
            </div>
            <div class="Shadowboxb"></div>
        </div>
			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>
<script type="text/javascript"> 

$(function(){
	changePlat("${bindPlat}", false);
	changeClient("pc");
})

function changeClient(clientType) {
	if(bindPlat == $("#clientType").val()){
		$("#clientType").val(clientType);
		return;
	}
	$("#clientType").val(clientType);
}

function changePlat(bindPlat, clearShopId){
	if(bindPlat == $("#bindPlat").val()){
		$("#bindPlat").val(bindPlat);
		return;
	}
	$("#bindPlat").val(bindPlat);
	
	$("input[name='plat'][checked]").parent().parent().parent().removeClass();
	$("input[name='plat'][checked]").parent().parent().find("em").removeClass();
	$("input[name='plat'][checked]").removeAttr("checked");
	$("input[name='plat'][value='"+bindPlat+"']").attr("checked", "checked");
	$("input[name='plat'][checked]").parent().parent().parent().addClass("cur");
	$("input[name='plat'][checked]").parent().parent().find("em").addClass("num");
    if(clearShopId){
		$("#shopId").val("");
    }

	var url = "${ctx}/web/shop/taskmanage/load_add_task_shop";
	$.ajax( {
		url : url,
		type : 'post',
		data:{
			bindPlat : bindPlat,
			shopId : $("#shopId").val()
		},
		dataType : 'text',
		timeout : 60000,
		error : function(e) {
			alert("连接服务器超时,加载店铺列表失败,请稍后再试.");
		},
		success : function(result) {
			if (!isOutTime(result)) {
				result = eval("("+result+")");
				if (result.success) {
					$("#shopInfo").html(result.msg);
					if($("input[name='shop_id'][checked]").val() == null){
						$("#shopId").val("");
						$("#next_step_a").addClass("disabled");
					} else{
						$("#shopId").val($("input[name='shop_id'][checked]").val());
						$("#next_step_a").removeClass("disabled");
					}
				}
			}
		}
	});
}

function changeShop(shopId){
	if(shopId == $("input[name='shop_id'][checked]").val()){
		$("#shopId").val(shopId);
		return;
	}
	$("input[name='shop_id'][checked]").parent().parent().parent().removeClass();
	$("input[name='shop_id'][checked]").removeAttr("checked");
	$("input[name='shop_id'][value='"+shopId+"']").attr("checked", "checked");
	$("input[name='shop_id'][checked]").parent().parent().parent().addClass("cur");
	$("#shopId").val(shopId);
}

function nextStep(){
	var bindPlat = $("#bindPlat").val().trim();
	var shopId = $("#shopId").val().trim();
	var clientType = $("#clientType").val().trim();
	if(bindPlat == "" || shopId == "" || clientType == ""){
		return;
	}
	$("#trade_form").submit();
}

</script>

</body></html>