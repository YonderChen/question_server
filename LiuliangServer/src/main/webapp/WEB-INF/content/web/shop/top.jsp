<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="state" style="background:#F2F2F2;">
    <div class="wrap">
    	        <a href="http://www.liuliangfu.com/center/userinfo">${sessionServerUserInfo.username }</a> ｜ <a href="${ctx }/web/shop/logout">退出</a>
        <span class="fr"><a href="http://www.liuliangfu.com/help/help_center_pro?left_list_id=100" target="_blank">帮助中心</a></span>
        <span class="fr" style="margin-right:40px;">积分: ${sessionServerUserInfo.score } 点</span>
        <span class="fr" style="margin-right:40px;">
            <a href="http://www.liuliangfu.com/pay/pay_score">购买积分</a> ｜ <a href="http://www.liuliangfu.com/pay/pay_group">续费会员</a>
        </span>
		    </div>
</div>
<div class="header">
    <div class="wrap">
        <a class="logo fl" href="http://www.liuliangfu.com/" style="margin-top:7px;"><img src="${ctx}/static_shop/images/logo.png"></a>
        <menu class="business-menu fr" style="width:500px;">
            <!-- <a href="/" target="_blank">首页</a> -->
            <a href="http://www.liuliangfu.com/pay/pay_score" target="_blank">购买积分</a>
            <a href="http://www.liuliangfu.com/trade/step_one" target="_blank">发布任务流量</a>
            <a href="http://www.liuliangfu.com/center/invite_business?left-list-id=7" target="_blank">邀请返利</a>
            <a href="http://www.liuliangfu.com/help/novice" target="_blank">新商家必读</a>
            <a href="./流量符-个人中心，汇聚更多流量_files/流量符-个人中心，汇聚更多流量.html">个人中心</a>
        </menu>
    </div>
</div>
