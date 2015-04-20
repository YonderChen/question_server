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
	if(left_list_id != null) {
		choose_left_list_id(left_list_id);
	}
})

function choose_left_list_id(id){
	if(id == 0){
		guide_help_center_0();
	} else if(id < 7) {
		guide_help_center(id);
	} else {
		choose_help_center(id);
	}
}

function choose_help_center(id) {
	$("#guide_help_center_0").hide();
	$("#guide_help_center_1_6").hide();
	$("#help_center_7_13").show();
	for(var i=7;i<14;i++){
		if(id!=i){
			$("#help_content_" + i).hide();
		} else {
			$("#help_content_" + i).show();
		}
	}
}

function guide_help_center(id) {
	$("#help_center_7_13").hide();
	$("#guide_help_center_0").hide();
	$("#guide_help_center_1_6").show();
	if(id<0){
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
}
function guide_help_center_0() {
	$("#help_center_7_13").hide();
	$("#guide_help_center_1_6").hide();
	$("#guide_help_center_0").show();
}
</script>           

<div class="help_left">
    
    <dl>
        <dt><a target="_self" style="color:#2BA6DF;" href="${ctx}/help/help_center.jsp">常见问题</a></dt>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=7">流量相关</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=8">注册相关</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=9">登录相关</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=10">绑定店铺</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=11">购买积分</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=12">会员充值</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=13">发布任务</a></dd>
    </dl>
    <dl>
        <dt><a href="${ctx}/help/help_center.jsp?left_list_id=-1">新手指南</a></dt>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=1">注册登录</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=2">购买会员</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=3">绑定店铺</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=4">购买积分</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=5">发布任务</a></dd>
        <dd><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=6">任务管理</a></dd>
    </dl>
    <dl>
        <dt><a target="_self" href="${ctx}/help/help_center.jsp?left_list_id=0">网站公告</a></dt>
    </dl>
</div>   