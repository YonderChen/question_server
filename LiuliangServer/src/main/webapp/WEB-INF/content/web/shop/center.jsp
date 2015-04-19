<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!-- saved from url=(0032)http://www.liuliangfu.com/center -->
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
<body>
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
<div class="breadcrumbs">
    <div class="wrap"><a href="http://www.liuliangfu.com/">首页</a> &gt; <a href="http://www.liuliangfu.com/center#">个人中心</a></div>
</div>

	<div style="width:1000px; margin:5px auto;">
		<a href="http://www.liuliangfu.com/help/help_center_notice?notice_id=10" target="_blank">
			<img src="${ctx}/static_shop/images/banner417.png" height="65" width="1000">
		</a>
	</div>
    <div style="width:1000px; margin:5px auto;">
	    <a href="http://www.liuliangfu.com/help/propaganda" target="_blank">
	    	<img src="${ctx}/static_shop/images/banner4161.png" height="65" width="1000">
	    </a>
    </div>
    <div style="width:1000px; margin:5px auto;">
	    <a href="http://www.liuliangfu.com/help/raiders" target="_blank">
	    	<img src="${ctx}/static_shop/images/banner4162.png" width="495" height="65">
	    </a>
    	<a href="http://www.liuliangfu.com/help/propagandas" target="_blank" style="float:right;">
    		<img src="${ctx}/static_shop/images/banner4163.png" width="495" height="65">
    	</a>
    </div>
    
    <div class="wrap clearfix">
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
        <dd><a target="_self" href="http://www.liuliangfu.com/center/userinfo?left-list-id=3">基本信息</a></dd>
        <dd><a target="_self" href="http://www.liuliangfu.com/bind/bind_shop">绑定店铺</a></dd>
        <dd><a target="_self" href="http://www.liuliangfu.com/pay/pay_group">续费会员</a></dd>
        <dd><a target="_self" href="http://www.liuliangfu.com/pay/pay_score">购买积分</a></dd>
        <dd><a target="_self" href="http://www.liuliangfu.com/center/invite_business?left-list-id=7">邀请商家</a></dd>
    </dl>
</div>    
	<div class="right">
    	<!-- 右侧顶部账号积分等信息 start -->
    	<div class="righttop">
            <div class="user_grade">
                <div class="user_gradel">
                流量符账号：<span class="span100">${sessionServerUserInfo.username }</span>&nbsp;&nbsp;
                会员等级：<span class="span100">
					<s:if test="#session.sessionServerUserInfo.vipEndTime == null">普通会员</s:if>
					<s:else>
						<s:if test="#session.sessionServerUserInfo.vipEndTime.time < #request.nowDate.time">VIP已过期</s:if>
						<s:else>
							VIP会员
						</s:else>
					</s:else>
                </span>
                        <!-- <span class="span1">VIP</span> -->
                		<!-- 其他两种会员类型
                        <span class="span100">VIP会员</span>
                        <span class="span100">普通会员</span>
                        -->
                        
					<s:if test="#session.sessionServerUserInfo.vipEndTime == null">
                    	<a href="http://www.liuliangfu.com/pay/pay_group" target="_blank">&nbsp;&nbsp;&nbsp;充值VIP</a>
                    </s:if>
					<s:else>
						<span class="span2">到期时间：<s:date name="#session.sessionServerUserInfo.vipEndTime" format="yyyy-MM-dd"/></span>
						<a href="http://www.liuliangfu.com/pay/pay_group" target="_blank">续费VIP会员</a>
                    </s:else>
                </div>
                <div class="user_grader business-left">
                    <span>安全等级：</span>
                    <a class="icon-setting phone" href="http://www.liuliangfu.com/center/userinfo/mobile"></a>
                    <a class="icon-setting mess" href="http://www.liuliangfu.com/center/userinfo/email"></a>
                    <a class="icon-setting money" href="http://www.liuliangfu.com/center/userinfo/pwd"></a>
                </div>
                
            </div>

            
            <div class="available">
                <div class="available_left">
                    <div class="available_yj">
                        <strong>可用积分：</strong><span><strong>${sessionServerUserInfo.score }</strong>点</span>
                        <a href="http://www.liuliangfu.com/pay/pay_score" target="_blank">购买积分</a>
                    </div>
                    <div class="available_yj">
                        <strong>已用积分：</strong><span><strong>${sessionServerUserInfo.scoreUsed }</strong>点</span>
                        <a href="http://www.liuliangfu.com/center/integral_record" target="_blank">积分记录</a>
                    </div>
                    
                </div>
                <div class="available_right"><a href="http://www.liuliangfu.com/trade/step_one" target="_blank">发布任务</a></div>
            </div>            
        </div>
        <!-- 右侧顶部账号积分等信息 end -->
        
        <!-- 网站公告常见问题 start -->
        <div class="business-right-comm" style="margin-top:10px;">
            <div class="business-right-money clearfix">
                <div class="business-exchange-content">
                    <ul class="tab-list" style="padding-left:10px;">
                        <li class="active notice-li"><a href="#">网站公告</a></li>
                        <!-- <li class="problem-li"><a href="#">常见问题</a></li> -->
                        <span style="display:inline-block; margin-left:20px; color:#f00; margin-top:10px;">请先购买流量符VIP会员再加群，否则一律拒绝！流量符VIP商家交流qq群：328892256</span>
                    </ul>
                </div>
                <table style="margin-top:6px;width:100%" class="notice-table">
                                        <tbody><tr style="height:35px; color:#555; font-size:14px;">
                      <td><a href="http://www.liuliangfu.com/help/help_center_notice?notice_id=11" target="_blank">【教程】商家如何设置引流的关键词</a></td>
                      <td style="width:90px;text-align:center;">2015/04/17</td>
                    </tr>
                                        <tr style="height:35px; color:#555; font-size:14px;">
                      <td><a href="http://www.liuliangfu.com/help/help_center_notice?notice_id=9" target="_blank">【重要】访客IP地区的相对集中不会影响店铺的点击率</a></td>
                      <td style="width:90px;text-align:center;">2015/04/15</td>
                    </tr>
                                        <tr style="height:35px; color:#555; font-size:14px;">
                      <td><a href="http://www.liuliangfu.com/help/help_center_notice?notice_id=2" target="_blank">流量符商家会员必读</a></td>
                      <td style="width:90px;text-align:center;">2015/04/15</td>
                    </tr>
                                        <tr style="height:35px; color:#555; font-size:14px;">
                      <td><a href="http://www.liuliangfu.com/help/help_center_notice?notice_id=4" target="_blank">网站购买会员的价格调整公告</a></td>
                      <td style="width:90px;text-align:center;">2015/04/14</td>
                    </tr>
                                        <tr style="height:35px; color:#555; font-size:14px;">
                      <td><a href="http://www.liuliangfu.com/help/propaganda" target="_blank">什么才是真实有效的搜索点击流量？</a></td>
                      <td style="width:90px;text-align:center;">2015/04/14</td>
                    </tr>
                                        <tr style="height:35px; color:#555; font-size:14px;">
                      <td><a href="http://www.liuliangfu.com/help/raiders" target="_blank">怎样用我们的流量才会更有效果？</a></td>
                      <td style="width:90px;text-align:center;">2015/04/14</td>
                    </tr>
                                        <tr style="height:35px; color:#555; font-size:14px;">
                      <td><a href="http://www.liuliangfu.com/help/propagandas" target="_blank">解析虚假流量如何导致商品降权</a></td>
                      <td style="width:90px;text-align:center;">2015/04/14</td>
                    </tr>
                                    </tbody></table>
                <table style="margin-top:6px;width:100%; display:none;" class="problem-table">
                                    </table>
            </div>
        </div>
        <!-- 网站公告常见问题 end -->
        
        
        
        <!-- 未审核通过的任务 start -->
                <!-- 未审核通过的任务 end -->
        
        
        
        
        <!-- 任务管理 start -->
        <div class="tasklist">
            <h1 style="font-size:2em; border:none;">任务管理</h1>

            <ul class="business-tabs">
              <li class="active"><a href="http://www.liuliangfu.com/center/index">
              <span>所有任务（4）</span></a></li>
              <li><a href="http://www.liuliangfu.com/center/index/ing">
              <span>进行中的任务（0）</span></a></li>
              <li><a href="http://www.liuliangfu.com/center/index/fin">
              <span>已完成的任务（0）</span></a></li>
            </ul>

            <form action="http://www.liuliangfu.com/center/index/all" method="get" id="frm_tasks">
            <div class="clearfix task-list" style="padding:18px 2px">
                <span style="margin-left:0;">平台：</span>
                <select class="J-binding-type" name="plat" style="width: 100px;">
                    <option value="">请选择</option>
                                        <option value="taobao">淘宝</option>
                                        <option value="tmall">天猫</option>
                                        <option value="jd">京东</option>
                                    </select>
                <span>店铺：</span>
                <select class="J-binding-name" name="shop">
                    <option value="">请选择</option>
                                        <option value="182">迪尼贝儿旗舰店</option>
                                    </select>
                <span>任务类型：</span>
                <select class="J-trade-type" name="trade_type">
                    <option value="">请选择</option>
                    <option value="1">自然搜索流量</option>
                </select>
                
                <span>任务状态：</span>
                <select name="trade_status">
                    <option value="" selected="">请选择</option>
                    <option value="0">未发布</option>
                    <option value="1">待审核</option>
                    <option value="2">任务进行中</option>
                    <option value="3">已完成</option>
                    <option value="4">已取消</option>
                    <option value="5">审核不通过</option>
                    <option value="6">任务修改,待审核</option>
                </select>

                <div style="height:10px; width:100%;"></div>
                <span style="margin-left:0;">任务发布时间：</span>
                <input style="width:100px; border:1px solid #CCC;" name="st" value="" onclick="WdatePicker()">
                <span style="margin-left:0;">-</span>
                <input style="width:100px; border:1px solid #CCC;" name="et" value="" onclick="WdatePicker()">

                <span style="margin-left:16px;">高级搜索：</span>
                <select name="query_key" style="width: 100px;">
                   <option value="" selected="">请选择</option>
                   <option value="1">任务编号</option>
                   <option value="2">商品名</option>
                </select>
                <em style="margin-left:16px;display: inline-block;position: relative;zoom: 1;height: 20px;border: 1px solid #CCCCCC;border-radius: 2px;vertical-align: middle;">
                <input name="query_val" type="text" style="height:20px;width:120px;" value="">
                </em>
                
                <a id="J-submiit-btn" class="business-mess-sent-btn" style="margin-left:8px;">搜索</a>
            </div>
            </form>
            
            <div class="task-list-title">
            	<span style="width:30%;">商品</span><span style="width:10%;">总访客数</span><span style="width:18%;">每日计划访客数</span><span style="width:17%;">已完成天数/总天数</span><span style="width:15%;">状态</span><span style="width:10%;">操作</span>
            </div>
            <div class="business-tabs-tasklinesheet-more task-list-table" style="margin-top:10px;">
            	                                <table border="0" bordercolor="#C9E7F7" cellspacing="0" cellpadding="0">
                    <tbody><tr>
                        <th colspan="6">
                            <span><i class="plat_small plat_tmall"></i>迪尼贝儿旗舰店&nbsp;&nbsp;任务编号：14223248481368<a href="http://www.liuliangfu.com/center/trade_detail/14223248481368" class="trade-detail" target="_blank">[详情]</a>&nbsp;&nbsp;发布时间：2015-04-17 13:06:59</span>
                            <!-- <span class="right_red" style="color:#0077DD; cursor:pointer;">增加每日访客数</span> -->
                            <!-- <span class="right_red cancel-trade" name="123" style="color:#0077DD; cursor:pointer;">撤销任务</span> -->
                        </th>
                    </tr>
                    <tr>
                        <td class="nb" style="width:30%;">
                            <div class="intableProLists">
                                <img src="${ctx}/static_shop/images/1429247219-553094f33abac.jpg" width="50" height="50" class="img">
                                <p class="text">迪尼贝儿新生儿纯棉包被婴儿抱被抱毯盖被子秋冬款加厚婴幼儿用品</p>
                                
                            </div>
                        </td>
                        <td style="font-size:14px; width:10%;">
                        7000                        </td>
                        <td style="font-size:14px; width:18%;">
                        1000                        </td>
                        <td style="font-size:14px; width:15%;">
                         0 / 7                        </td>
                        <td style="font-size:14px; width:15%;">
                         未发布                        </td>
                        <td style="font-size:14px; width:12%;">
                                                        <a href="http://www.liuliangfu.com/trade/rechange/14223248481368" class="jx-trade">继续发布</a>
                                                    </td>
                    </tr>
                </tbody></table>
                                <table border="0" bordercolor="#C9E7F7" cellspacing="0" cellpadding="0">
                    <tbody><tr>
                        <th colspan="6">
                            <span><i class="plat_small plat_tmall"></i>迪尼贝儿旗舰店&nbsp;&nbsp;任务编号：14284999491872<a href="http://www.liuliangfu.com/center/trade_detail/14284999491872" class="trade-detail" target="_blank">[详情]</a>&nbsp;&nbsp;发布时间：2015-04-12 12:51:18</span>
                            <!-- <span class="right_red" style="color:#0077DD; cursor:pointer;">增加每日访客数</span> -->
                            <!-- <span class="right_red cancel-trade" name="123" style="color:#0077DD; cursor:pointer;">撤销任务</span> -->
                        </th>
                    </tr>
                    <tr>
                        <td class="nb" style="width:30%;">
                            <div class="intableProLists">
                                <img src="${ctx}/static_shop/images/1428814278-5529f9c6e1624.jpg" width="50" height="50" class="img">
                                <p class="text">迪尼贝儿宝宝枕头儿童加长卡通防偏头荞麦枕婴幼儿定型枕 加长版</p>
                                
                            </div>
                        </td>
                        <td style="font-size:14px; width:10%;">
                        300                        </td>
                        <td style="font-size:14px; width:18%;">
                        30                        </td>
                        <td style="font-size:14px; width:15%;">
                         0 / 10                        </td>
                        <td style="font-size:14px; width:15%;">
                         未发布                        </td>
                        <td style="font-size:14px; width:12%;">
                                                        <a href="http://www.liuliangfu.com/trade/rechange/14284999491872" class="jx-trade">继续发布</a>
                                                    </td>
                    </tr>
                </tbody></table>
                                <table border="0" bordercolor="#C9E7F7" cellspacing="0" cellpadding="0">
                    <tbody><tr>
                        <th colspan="6">
                            <span><i class="plat_small plat_tmall"></i>迪尼贝儿旗舰店&nbsp;&nbsp;任务编号：14281219031672<a href="http://www.liuliangfu.com/center/trade_detail/14281219031672" class="trade-detail" target="_blank">[详情]</a>&nbsp;&nbsp;发布时间：2015-04-04 12:31:43</span>
                            <!-- <span class="right_red" style="color:#0077DD; cursor:pointer;">增加每日访客数</span> -->
                            <!-- <span class="right_red cancel-trade" name="123" style="color:#0077DD; cursor:pointer;">撤销任务</span> -->
                        </th>
                    </tr>
                    <tr>
                        <td class="nb" style="width:30%;">
                            <div class="intableProLists">
                                <img src="${ctx}/static_shop/images/1428121903-551f692f24d62.jpg" width="50" height="50" class="img">
                                <p class="text">衣服</p>
                                
                            </div>
                        </td>
                        <td style="font-size:14px; width:10%;">
                        300                        </td>
                        <td style="font-size:14px; width:18%;">
                        30                        </td>
                        <td style="font-size:14px; width:15%;">
                         0 / 10                        </td>
                        <td style="font-size:14px; width:15%;">
                         未发布                        </td>
                        <td style="font-size:14px; width:12%;">
                                                        <a href="http://www.liuliangfu.com/trade/rechange/14281219031672" class="jx-trade">继续发布</a>
                                                    </td>
                    </tr>
                </tbody></table>
                                <table border="0" bordercolor="#C9E7F7" cellspacing="0" cellpadding="0">
                    <tbody><tr>
                        <th colspan="6">
                            <span><i class="plat_small plat_tmall"></i>迪尼贝儿旗舰店&nbsp;&nbsp;任务编号：14223248408372<a href="http://www.liuliangfu.com/center/trade_detail/14223248408372" class="trade-detail" target="_blank">[详情]</a>&nbsp;&nbsp;发布时间：2015-01-27 10:14:00</span>
                            <!-- <span class="right_red" style="color:#0077DD; cursor:pointer;">增加每日访客数</span> -->
                            <!-- <span class="right_red cancel-trade" name="123" style="color:#0077DD; cursor:pointer;">撤销任务</span> -->
                        </th>
                    </tr>
                    <tr>
                        <td class="nb" style="width:30%;">
                            <div class="intableProLists">
                                <img src="${ctx}/static_shop/images/1422324840-54c6f468746b4.jpg" width="50" height="50" class="img">
                                <p class="text">迪尼贝儿新生儿纯棉包被婴儿抱被抱毯盖被子秋冬款加厚婴幼儿用品</p>
                                
                            </div>
                        </td>
                        <td style="font-size:14px; width:10%;">
                        7000                        </td>
                        <td style="font-size:14px; width:18%;">
                        1000                        </td>
                        <td style="font-size:14px; width:15%;">
                         0 / 7                        </td>
                        <td style="font-size:14px; width:15%;">
                         未发布                        </td>
                        <td style="font-size:14px; width:12%;">
                                                        <a href="http://www.liuliangfu.com/trade/rechange/14223248408372" class="jx-trade">继续发布</a>
                                                    </td>
                    </tr>
                </tbody></table>
                                
                <div class="pager"></div>
            </div>
        </div>
        <!-- 任务管理 end -->
        
        
	</div>
</div>

<div class="business-popup-under J_popBG"></div>
<div class="business-popup J_popCON none"></div>
		<div class="footer">
			<div>
				Copyright (c) 2015 Liuliang*** Inc. All Rights.
				<a href="http://www.miitbeian.gov.cn/" target="_blank" style="color: #000000;">苏ICP备******号-*</a>
				<!-- <a href="http://www.anquan.org/s/www.renqifu.com" name="mCjpeW3arepiX4qyXbu3uXd5998GIHXei6m7162beGTVpCrKl6" >安全联盟</a> -->
			</div>
		</div>

<script>
$(function (){
	
	//常见问题，网站公告点击效果
	$('.notice-li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.notice-table').show();
		$('.problem-table').hide();
	})
	$('.problem-li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.problem-table').show();
		$('.notice-table').hide();
	})
	
    $('#J-submiit-btn').click(function (){
        $('#frm_tasks')[0].submit();
    })

    // $('.business-tabs li').click(function (){
    //     $(this).addClass('active').siblings().removeClass('active');
    // });
})

</script>

</body></html>