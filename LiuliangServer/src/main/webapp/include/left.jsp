<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	<div class="left">
    <dl style="border-top:none;">
        <dt>流量管理</dt>
        <dd id="dd_id_1">
            <a target="_self" href="${ctx }/web/shop/taskmanage/task_list?left-list-id=1">任务管理</a>
        </dd>
        <dd id="dd_id_2">
            <a target="_self" href="${ctx}/web/shop/accountmanage/dealmanage/score_record?left-list-id=2">积分记录</a>
        </dd>
        <dd id="dd_id_3">
            <a target="_self" href="${ctx}/web/shop/taskmanage/liuliang_list?left-list-id=3">流量明细</a>
        </dd>
    </dl>
    
    <dl>
        <dt>账户管理</dt>
        <dd id="dd_id_4"><a target="_self" href="${ctx }/web/shop/userinfo?left-list-id=4">基本信息</a></dd>
        <dd id="dd_id_5"><a target="_self" href="${ctx }/web/shop/accountmanage/dealmanage/pay_record?left-list-id=5">充值记录</a></dd>
        <dd><a target="_self" href="${ctx }/web/shop/accountmanage/shopmanage/bind_shop">绑定店铺</a></dd>
        <dd><a target="_self" href="${ctx }/web/shop/accountmanage/dealmanage/renewalvip">续费会员</a></dd>
        <dd><a target="_self" href="${ctx }/web/shop/accountmanage/dealmanage/get_score">购买积分</a></dd>
    </dl>
<script type="text/javascript"> 

$(function(){
	var left_list_id = getQueryString("left-list-id");
	$("#dd_id_" + left_list_id).addClass("left_list_dd");
})
	
</script>
</div>    