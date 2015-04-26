<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function(){
	var username_top_test = "${sessionServerUserInfo.username}";
	if(username_top_test == null || username_top_test == ""){
		$("#has_login").hide();
		$("#un_login").show();
	} else {
		$("#un_login").hide();
		$("#has_login").show();
	}
})

function isOutTime(result) {
	if (result.indexOf("win.location.href='${ctx}/web/shop/index'") > 0) {
		alert("登录超时,请重新登录.");
		var win = window;
		while (win != window.parent) {
			win = window.parent;
		}
		win.location.href='${ctx}/web/shop/index';
		return true;
	}
	return false;
}
</script>           

<div id="has_login" style="display: none;">
	<div class="state" style="background:#F2F2F2;">
    	<div class="wrap">
    	        <a href="${ctx}/web/shop/userinfo">${sessionServerUserInfo.username }</a> ｜ <a href="${ctx }/web/shop/logout">退出</a>
        
        <span class="fr"><a href="${ctx}/help/help_center.jsp?left_list_id=-2" target="_blank">帮助中心</a></span>
        <span class="fr" style="margin-right:40px;">积分: ${sessionServerUserInfo.score } 点</span>
        <span class="fr" style="margin-right:40px;">
            <a href="${ctx }/web/shop/accountmanage/dealmanage/get_score">购买积分</a> ｜ <a href="${ctx }/web/shop/accountmanage/dealmanage/renewalvip">续费会员</a>
        </span>
		</div>
	</div>
</div>
	
<div id="un_login">
	<div class="state" style="background:#F2F2F2;">
	    <div class="wrap">
	    	        <a href="${ctx }/web/shop/register_index">注册</a> ｜ <a href="${ctx}/web/shop/index">登录</a>
	        <span class="fr"><a href="${ctx}/help/help_center.jsp?left_list_id=-2" target="_blank">帮助中心</a></span>
	    	    </div>
	</div>
</div>
	
<div class="header">
    <div class="wrap">
        <a class="logo fl" href="${ctx }" style="margin-top:7px;"><img src="${ctx}/static_shop/images/logo.png"></a>
        <menu class="business-menu fr" style="width:500px;">
            <a href="${ctx }/web/shop/accountmanage/dealmanage/get_score" target="_blank">购买积分</a>
            <a href="${ctx }/web/shop/taskmanage/add_task_step_one" target="_blank">发布任务流量</a>
            <a href="${ctx }/help/novice.jsp" target="_blank">新商家必读</a>
            <a href="${ctx }/web/shop/center">个人中心</a>
        </menu>
    </div>
</div>

<iframe width="121" height="277" scrolling="no" frameborder="0" allowtransparency="true" src="${ctx}/include/qq_button.jsp" style="display: block; position: fixed; z-index: 2147483646 !important; left: auto; right: 8px; margin-left: 0px; top: 50%; bottom: auto; margin-top: -138px;">
</iframe>