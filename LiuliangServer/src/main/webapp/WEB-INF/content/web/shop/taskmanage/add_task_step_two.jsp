<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
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
    <link rel="stylesheet" href="${ctx}/static_shop/style/register.css">
    <link rel="stylesheet" href="${ctx}/static_shop/style/release.css">
</head>
<body>
			<jsp:include page="/include/top.jsp" flush="true"></jsp:include>
<div class="breadcrumbs">
    <div class="wrap"><a href="${ctx}">首页</a> &gt; <a href="#">发布任务</a> &gt; <a href="#">填写流量信息</a> </div>
</div>
    <form id="to_step_three" action="/trade/step_three" method="post" enctype="multipart/form-data" onsubmit="return sub_check()">
    <input type="hidden" name="clone_sn" value="">
    <div class="container">
        <div class="Shadowbox">
            <div class="Shadowboxp"></div>
            <div class="register">
            
                <div class="register-title">
                    
                        <span class="bram-title">填写流量信息</span>
                        <div class="Process">
                            <ul class="clearfix">
                                <li class="cur" style="width:20%"><em class="Processyes">1<i></i></em><span class="">选择平台</span><strong></strong></li>
                                <li class="cur" style="width:20%"><em class="Processyes">2<i></i></em><span class="">选择店铺</span><strong></strong></li>
                                <li class="cur" style="width:20%"><em class="Processyes">3<i></i></em><span class="">选择流量类型</span><strong></strong></li>
                                <li class="" style="width:20%"><em class="Processing">4<i></i></em><span class="">填写流量信息</span><strong></strong></li>
                                <li class="" style="width:20%"><em class="">5<i></i></em><span class="">选择增值服务</span><strong></strong></li>
                                <li class="Processlast" style="width:82px"><em>6<i></i></em><span>发布任务</span><strong></strong></li>
                            </ul>
                        </div>
                </div>
                
                
                <div class="issue-task-two"> 
                    <div class="shop-type">任务类型已选择：<i class="plat_small plat_tmall"></i>天猫 | 迪尼贝儿旗舰店 | 自然搜索流量<a class="back_step_one" href="javascript:;">返回编辑</a></div>
                    
                    <div class="flow-info checkout-steps">
                        <h1>第四步：填写流量信息</h1>
                        <h3>商品信息</h3>
                        <div>
                            <div class="step-complete-Writ-box-1">
                            
                            
                                <div class="step-item inp">
                                    <strong>1.商品链接： </strong>
                                    <input type="text" name="item_url" class="text w460 J_NAME_INPUT" reg="url" value="">
                                    <span class="newtips">必填</span>
                                    <span id="hd_item_url" class="error" style="display:none;"></span>
                                </div>
                                <div class="step-item inp">
                                    <strong>2.商品名： </strong>
                                    <input type="text" name="item_title" class="text w460 J_URL_INPUT" reg="name" trade="" regname="noempty" value="">
                                    <span class="newtips">必填</span>
                                    <span id="hd_item_title" class="error" style="display:none;"></span>
                                </div>    
                                <div class="step-item inp">
                                    <strong>3.商品主图： </strong>
                                    <input type="file" name="item_img" class="input_file" checkfor=".J_upfile_1_cf" reg="mainpic">
                                    <span id="hd_item_img" class="error" style="display:none;"></span>
                                </div>
                                
                            </div>
                        </div>
                        
                        <h3>流量设置</h3>
                        <h4>1.设置访客来源及每日访客数<span class="red">3积分/访客</span></h4>
                        <div class="flow-source">
                            <p class="prompt"><label>提示：</label><span>请填写3-5个访客入店搜索的关键词, 流量将根据您设置的关键词和入口找到您的商品并点击进入您的店铺<a href="/help/help_center_pro?left_list_id=13&amp;display_pro_id=trade2" target="_blank">为什么要填写3-5个关键词？</a></span><span class="red" style="padding-left:35px;">请务必确保综合搜索关键词后，您的商品排名在前10页，否则容易导致的流量误差过大，严重影响店铺转化率！</span></p>
                            <div class="keyword">
                                                                <div>
                                    <label class="kwd_num">关键词1：</label><input type="text" class="text w200" name="kwd[]" reg="empty" value="">&nbsp;&nbsp;<label>每日访客数：</label><input type="text" class="text w100" name="visit[]" reg="empty" value="">&nbsp;访客/天<span style="font-size:12px; color:#666; margin:0;">（最低10访客/天）</span>
                                                                        <span class="red">必填</span>
                                                                        <span id="hd_kwd_info1" class="error" style="display:none;"></span>
                                </div>
                                                                <div>
                                    <label class="kwd_num">关键词2：</label><input type="text" class="text w200" name="kwd[]" reg="empty" value="">&nbsp;&nbsp;<label>每日访客数：</label><input type="text" class="text w100" name="visit[]" reg="empty" value="">&nbsp;访客/天<span style="font-size:12px; color:#666; margin:0;">（最低10访客/天）</span>
                                                                        <span class="red">必填</span>
                                                                        <span id="hd_kwd_info2" class="error" style="display:none;"></span>
                                </div>
                                                                <div>
                                    <label class="kwd_num">关键词3：</label><input type="text" class="text w200" name="kwd[]" reg="empty" value="">&nbsp;&nbsp;<label>每日访客数：</label><input type="text" class="text w100" name="visit[]" reg="empty" value="">&nbsp;访客/天<span style="font-size:12px; color:#666; margin:0;">（最低10访客/天）</span>
                                                                        <span class="red">必填</span>
                                                                        <span id="hd_kwd_info3" class="error" style="display:none;"></span>
                                </div>
                                                            </div>

                            <p class="step-list-add"><a id="add_key_words" href="javascript:;"><img src="http://www.liuliangfu.com/static/images/icon_add.gif">支付30积分添加1个关键词</a>（最多可添加2个）</p>
                            
                            <div id="kwd_demo" style="display:none;">
                                <div class="kwd_clone">
                                    <label class="kwd_num"></label><input type="text" class="text w200" name="kwd[]" value="" disabled="disabled">&nbsp;&nbsp;<label>每日访客数：</label><input type="text" class="text w100" name="visit[]" value="" disabled="disabled">&nbsp;访客/天<span style="font-size:12px; color:#666; margin:0;">（最低10访客/天）</span> <span><a href="javascript:;" class="kwd_del">删除</a></span>
                                    <span class="error" style="display:none;"></span>
                                </div>
                            </div>
                        </div>
                        
                        <h4>2.设置流量来源分布占比<span>（所有渠道流量来源占比需为100%）</span></h4>
                        <div class="distribution">
                            <p class="prompt"><label>提示：</label><span>搜索关键词将随机分配给不同的搜索渠道</span></p>
                            <div class="percentage">
                                <div><label>电脑端淘宝自然搜索占比：</label><input type="text" name="pc_percent" class="pctext" value="100" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">%</div>
                                                                <div><label>电脑端天猫自然搜索占比：</label><input type="text" name="pc_tm_percent" class="pctext" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">%</div>
                                                                                                <!-- <div><label>手机淘宝自然搜索占比：</label><input type="text" name="phone_percent" class="apptext" value="" onKeyUp="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" />%</div> -->
                                                                <!-- <div><label><input type="checkbox" name="perc" />天猫自然搜索占比：</label><input type="text" class="apptext" />%</div> -->
                            </div>
                            <div><span id="hd_percent" class="error" style="margin-left:0;display:none;">所有渠道流量来源占比需为100%</span></div>
                        </div>
                        
                        <h4>3.选择流量购买时长<span>（任务发布后，可根据店铺实际情况，在任务管理中修改每日访客数）</span></h4>
                        <div class="select-time">
                            <p class="prompt"><label>提示：今日18:00前发布的流量任务今日会开始执行，18:00点之后发布的流量会明日开始执行。</label></p>
                            <div class="seletime">
                                                                <label><input type="radio" name="pay_days" value="7">7天</label>
                                                                <label><input type="radio" name="pay_days" value="10" checked="checked">10天</label>
                                                                <label><input type="radio" name="pay_days" value="15">15天</label>
                                                                <label><input type="radio" name="pay_days" value="30">30天</label>
                                                            </div>
                        </div>
                        
                        <div class="release-bottom">
                            <!-- <div class="release-bott1">每日访客数<span>700</span>，购买时长<span>7</span>天，总需：<strong>7000</strong>积分</div> -->
                            <!-- <div class="release-bott2 release-botts">基础流量费：<div><span>100</span>积分/关键词*<span>3</span>关键词+1000积分/天*<span>7</span>天=<strong>2000</strong>积分</div></div> -->
                            <div class="release-bott3">
                                <div>每日访客数: <span class="visit_num">0</span></div>
                                <div>购买时长: <span class="pay_days_num">10</span> 天</div>
                                <div>基础流量费: <span class="visit_num">0</span> <span> × </span> <span class="pay_days_num">10</span> <span> × 3 </span> <span> = </span> <span class="sum_score">0.00</span> 积分</div>
                                <div id="add_kwd" style="display: none;">添加关键词<span class="add_kwd_num"></span>个，需：<span class="add_kwd_score"></span> 积分</div>
                                <div>小计：<strong class="xiaoji">0.00</strong> 积分</div>
                            </div>
                        </div>
                        
                    </div>
                    
                    <div class="checkout-btnbox">
                        <div class="publish-btnbox">
                            <span class="sk-arrange-gray mo-t"><a class="sk-arrange-next to-prev back_step_one">上一步</a></span>
                            <span class="sk-arrange-gray mo-t"><a class="sk-arrange-next goto_step_three itembtn">下一步</a></span>
                        </div>     
                    </div>
                </div>
                
            </div>
            <div class="Shadowboxb"></div>
        </div>
    </div>
    </form>
			<jsp:include page="/include/footer.jsp" flush="true"></jsp:include>


</body></html>