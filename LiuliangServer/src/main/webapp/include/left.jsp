<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	<div class="left">
    <dl style="border-top:none;">
        <dt>流量管理</dt>
        <dd>
            <a target="_self" href="http://www.liuliangfu.com/center/trade_manage?left-list-id=1">任务管理</a>
        </dd>
        <dd>
            <a target="_self" href="http://www.liuliangfu.com/center/integral_record?left-list-id=2">积分记录</a>
        </dd>
    </dl>
    
    <dl>
        <dt>账户管理</dt>
        <dd><a target="_self" href="${ctx }/web/shop/userinfo">基本信息</a></dd>
        <dd><a target="_self" href="http://www.liuliangfu.com/bind/bind_shop">绑定店铺</a></dd>
        <dd><a target="_self" href="${ctx }/web/shop/accountmanage/dealmanage/renewalvip">续费会员</a></dd>
        <dd><a target="_self" href="${ctx }/web/shop/accountmanage/dealmanage/get_score">购买积分</a></dd>
    </dl>
</div>    