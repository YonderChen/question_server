<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
     
<script language="javascript" src="../static_shop/js/help_center_left.js"></script>
<script>
$(function(){
	$('.pro-con a').click(function(){
	    $(this).parent().siblings(".pro-con").slideUp();
	    $('.pro-con:visible').slideUp();
	    if(!$(this).parent().next().is(':hidden'))return;
	    $(this).parent().next().slideDown();
	   //$(this).parent().find('.placebox').placeholder(); 
    });
	$('.pro h3').click(function(){
		$(this).siblings(".pro-con").slideUp();
		$('.pro-con:visible').slideUp();
		if(!$(this).next().is(':hidden'))return;
		$(this).next().slideDown();
	})
	var left_list_id = getQueryString("left_list_id");
	choose_left_list_id(left_list_id);
})

function choose_left_list_id(id){
	if(id == 0){
		guide_help_center_0();
	} else if(id < 7 && id > -2) {
		guide_help_center(id);
	} else {
		choose_help_center(id);
	}
}

function choose_help_center(id) {
	$("#cjwt_a").css("color","#2BA6DF")
	$("#xszn_a").css("color","")
	$("#wzgg_a").css("color","")
	$("#dqlj_a").html("常见问题");
	$("#dqlj_a").attr("href", "${ctx}/help/help_center.jsp?left_list_id=-2");
	if(id == -2){
		for(var i=7;i<14;i++){
			$("#help_content_" + i).show();
		}
	} else {
		for(var i=7;i<14;i++){
			if(id!=i){
				$("#help_content_" + i).hide();
			} else {
				$("#help_content_" + i).show();
			}
		}
	}
	$("#guide_help_center_0").hide();
	$("#guide_help_center_1_6").hide();
	$("#help_center_7_13").show();
}

function guide_help_center(id) {
	$("#cjwt_a").css("color","")
	$("#xszn_a").css("color","#2BA6DF")
	$("#wzgg_a").css("color","")
	$("#dqlj_a").html("新手指南");
	$("#dqlj_a").attr("href", "${ctx}/help/help_center.jsp?left_list_id=-1");
	if(id == -1){
		for(var i=1;i<7;i++){
			$("#help_content_" + i).show();
		}
	} else {
		for(var i=1;i<7;i++){
			if(id!=i){
				$("#help_content_" + i).hide();
			} else {
				$("#help_content_" + i).show();
			}
		}
	}
	$("#help_center_7_13").hide();
	$("#guide_help_center_0").hide();
	$("#guide_help_center_1_6").show();
}
function guide_help_center_0() {
	$("#cjwt_a").css("color","")
	$("#xszn_a").css("color","")
	$("#wzgg_a").css("color","#2BA6DF")
	$("#dqlj_a").html("网站公告");
	$("#dqlj_a").attr("href", "${ctx}/help/help_center.jsp?left_list_id=0");
	$("#help_center_7_13").hide();
	$("#guide_help_center_1_6").hide();
	$("#guide_help_center_0").show();
}
</script>           

<div class="help_left">
    
    <dl>
        <dt><a target="_self" id="cjwt_a" style="color:#2BA6DF;" href="${ctx}/help/help_center.jsp?left_list_id=-2">常见问题</a></dt>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=7">流量相关</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=8">注册相关</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=9">登录相关</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=10">绑定店铺</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=11">购买积分</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=12">会员充值</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=13">发布任务</a></dd>
    </dl>
    <dl>
        <dt><a target="_self" id="xszn_a" href="${ctx}/help/help_center.jsp?left_list_id=-1">新手指南</a></dt>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=1">注册登录</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=2">购买会员</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=3">绑定店铺</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=4">购买积分</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=5">发布任务</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=6">任务管理</a></dd>
    </dl>
    <dl>
        <dt><a target="_self" id="wzgg_a" href="${ctx}/help/help_center.jsp?left_list_id=0">网站公告</a></dt>
    </dl>
</div>   