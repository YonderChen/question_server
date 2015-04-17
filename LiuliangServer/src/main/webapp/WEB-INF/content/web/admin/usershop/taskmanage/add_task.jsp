<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<jsp:include page="../../include/style.jsp" flush="true"></jsp:include>
	<script type="text/javascript">
	var process_now = 1;
	
	$(document).ready(function(){
		bindPlatChange("tmall");
	  	$("#searchSource").change(function(){
	  		$("#searchSource_cut").val(100 - $("#searchSource").val());
	  	})
	});
	
	function checkBindPlat(bindPlat){
		$("#checked_img_taobao").hide();
		$("#checked_img_tmall").hide();
		$("#checked_img_jd").hide();
		$("#unchecked_img_taobao").show();
		$("#unchecked_img_tmall").show();
		$("#unchecked_img_jd").show();
		$("#unchecked_img_"+bindPlat).hide();
		$("#checked_img_"+bindPlat).show();
	}
	
	function bindPlatChange(newBindPlat) {
		checkBindPlat(newBindPlat);
		$("#bindPlat").val(newBindPlat);
		var url = "${ctx}/web/admin/usershop/taskmanage/load_shop";
		$.ajax( {
			url : url,
			type : 'post',
			data : {
				bindPlat : newBindPlat
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
						$("#shopId").html(result.msg);
					}
				}
			}
		});
	}
	
	function addKeyword(){
		if($("#div_keyword4").is(":hidden")){
			$("#div_keyword4").show();
		} else {
			$("#div_keyword5").show();
		}
	}
	
	function div_keyword4_del(){
		if(!$("#div_keyword4").is(":hidden")){
			$("#div_keyword4").hide();
		}
		$("#keyword4").val("");
		$("#orderNumberOneDay4").val("");
	}
	
	function div_keyword5_del(){
		if(!$("#div_keyword5").is(":hidden")){
			$("#div_keyword5").hide();
		}
		$("#keyword5").val("");
		$("#orderNumberOneDay5").val("");
	}
	
	function ajax_submit() {
		if($("#bindPlat").val().trim() == ""){
			alert("请选择平台");
			to_process(1);
			$("input[name='plat_radio'][value='tmall']").attr('checked',true);
			return;
		}
		if($("#shopId").val().trim() == ""){
			alert("请选择店铺");
			to_process(1);
			$("#shopId").select();
			return;
		}
		if($("#goodsUrl").val().trim() == ""){
			alert("请输入商品URL");
			to_process(2);
			$("#goodsUrl").select();
			return;
		}
		if($("#goodsName").val().trim() == ""){
			alert("请输入商品名称");
			to_process(2);
			$("#goodsName").select();
			return;
		}
		if($("#goodsImgFile").val().trim() == ""){
			alert("请选择商品主图");
			to_process(2);
			$("#goodsImgFile").select();
			return;
		}
		
		var url = "${ctx}/web/admin/usershop/taskmanage/add";
		$("#task_form").ajaxSubmit({
			url:url,
			type:'post',
			dataType:'text',
			timeout:60000,
			error: function(e) {
				alert("连接服务器超时,请稍后再试.");
				$(".btn").button('reset');
				$(".active").removeClass('active');
			},
			success: function(result){
				$(".btn").button('reset');
				$(".active").removeClass('active');
				if (!isOutTime(result)) {
					result = eval("("+result+")");
					alert(result.msg);
					if (result.success) {
						next_process();
					}
				}
			}
		});
	}
	
	function show_process(){
		if(process_now < 3) {
			$("#next_process_btn").show();
		} else {
			$("#next_process_btn").hide();
		}
		if(process_now > 1 && process_now != 4) {
			$("#pre_process_btn").show();
		} else {
			$("#pre_process_btn").hide();
		}
		for(var i=1;i<5;i++){
			if(i==process_now){
				$("#process_" + i).show();
			} else {
				$("#process_" + i).hide();
			}
		}
		for(var i=3;i<7;i++){
			var process_temp = i-2;
			$("#bram_pro_" + i).removeClass();
			if(i < 6){
				$("#cur_pro_" + i).removeClass();
			}
			if(process_temp == process_now){
				$("#bram_pro_" + i).addClass("Processing");
			} else if (process_temp < process_now){
				$("#bram_pro_" + i).addClass("Processyes");
				$("#cur_pro_" + i).addClass("cur");
			}
		}
	}
	
	function to_process(toProcess){
		process = toProcess;
		show_process();
	}
	
	function pre_process(){
		if(process_now <= 1) {
			alert("没有上一步了");
			return;
		}
		process_now--;
		show_process();
	}
	
	function next_process(){
		if(process_now >= 4) {
			alert("没有下一步了");
			return;
		}
		if(process_now == 1){
			if($("#bindPlat").val().trim() == ""){
				alert("请选择平台");
				to_process(1);
				$("#bindPlat").select();
				return false;
			}
			if($("#shopId").val().trim() == ""){
				alert("请选择店铺");
				to_process(1);
				$("#shopId").select();
				return;
			}
		}
		if(process_now == 2){
			if($("#goodsUrl").val().trim() == ""){
				alert("请输入商品URL");
				to_process(2);
				$("#goodsUrl").select();
				return;
			}
			if($("#goodsName").val().trim() == ""){
				alert("请输入商品名称");
				to_process(2);
				$("#goodsName").select();
				return;
			}
			if($("#goodsImgFile").val().trim() == ""){
				alert("请选择商品主图");
				to_process(2);
				$("#goodsImgFile").select();
				return;
			}
		}
		process_now++;
		show_process();
	}
	
</script>

<style>
<!--

.Process{width:530px; padding: 40px 50px 12px; overflow: hidden; margin-left:20px;}
.Process ul{position: relative;zoom:1;height: 10px;line-height: 10px;}
.Process ul li{float: left;background: url(${ctx}/images/Processdis.png) repeat-x;height: 10px;position: relative;}
.Process ul li.Processlast{position: absolute;left: 100%;background: none;top: 0;}
.Process ul li em{background: url(${ctx}/images/Process.png) no-repeat -85px 0;width: 34px;height: 34px;font-size: 14px;line-height: 34px;display: block;position: absolute;left: -17px;top:-12px;text-align: center; font-style:normal;}
.Process ul li span{position: absolute;left: -50%;height: 40px; line-height: 40px;top:-46px;text-align: center;width: 100%;display: block;overflow: hidden;}
.Process ul li.cur{background: url(${ctx}/images/Processpass.png) repeat-x}
.Process ul li em.Processyes{color: #FFF;background-position: 0px 0px;}
.Process ul li em.Processing{color: #FFF;background-position: -36px 0px;}
.Process ul li strong{color: #999;position: absolute;left: 0;top:10px;height: 24px;line-height: 24px;width: 100%;text-align: center;}
.Process ul li bottoms,.Process ul li b{color: #999;position: absolute;left: -50px;top:24px;height: 24px;line-height: 24px;width: 100%; font-style:normal; font-weight:normal;}
.Process ul li i{display: none;width: 13px;height: 7px;position: absolute;top: 50px;left: 50%;margin-left: -7px;background: url(${ctx}/images/makes-task-angle.png) no-repeat -18px -3px ;
}
.Process ul li em.Processing i{display: block;}
.unProcess{padding: 12px 50px 40px;}
.unProcess ul li span{top:auto;bottom: -46px;}

.register-title{padding-top: 20px;}
.bram-title{margin-top:15px; color:#1E9CD7; font-weight:bold; font-size:30px; height:33px; line-height:30px; display:inline-block; float:left;}


.publish-btnbox {
    padding: 36px 0;
	text-align:center;
}

.sk-arrange-next {
    font-size: 24px;
    padding-right: 20px;
    height: 60px;
    line-height: 60px;
}

.sk-arrange-next.to-prev {
    padding-left: 21px;
}

.sk-arrange-next{ text-decoration: none; cursor:pointer; width:76px;height:28px; background:url(${ctx}/images/arrange-next.gif) right center no-repeat; padding-right:15px;display:inline-block; font-size:18px; color:#0697da; line-height:28px;}
.sk-arrange-next{ margin-left:15px;vertical-align: middle;}
.sk-arrange-next.to-prev{ margin-right:15px;}
.sk-arrange-next,.sk-arrange-next:hover,.sk-arrange-next:link,.sk-arrange-next:visited{color:#0697da; }
.sk-arrange-next.to-prev{ background:url(${ctx}/images/arrange-prev.gif) left center no-repeat; padding:0 0 0 15px; }
.sk-arrange-next.disabled,.sk-arrange-next.to-prev.disabled,.sk-arrange-gray{ color:#A6A6A6;}
.sk-arrange-next.disabled{background:url(${ctx}/images/arrange-next-dis.gif) right center no-repeat;}
.sk-arrange-next.to-prev.disabled{background:url(${ctx}/images/arrange-prev-dis.gif) right center no-repeat;}

h3{margin-left: -10px}

hr{margin-left: -30px}

-->
</style>
	</head>
	<body>
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					流量管理
				</li>
				<li>
					发布任务
				</li>
			</ul>
		</div>
		<div class="container">
			<div class="register-title">
				<span id="bram_title" class="bram-title">填写流量信息</span>
				<div class="Process">
					<ul class="clearfix">
						<li id="cur_pro_1" class="cur" style="width: 20%">
							<em id="bram_pro_1" class="Processyes">1<i></i>
							</em><span class="">选择平台</span><strong></strong>
						</li>
						<li id="cur_pro_2" class="cur" style="width: 20%">
							<em id="bram_pro_2" class="Processyes">2<i></i>
							</em><span class="">选择店铺</span><strong></strong>
						</li>
						<li id="cur_pro_3" class="" style="width: 20%">
							<em id="bram_pro_3" class="Processing">3<i></i>
							</em><span class="">选择流量类型</span><strong></strong>
						</li>
						<li id="cur_pro_4" class="" style="width: 20%">
							<em id="bram_pro_4" class="">4<i></i>
							</em><span class="">填写流量信息</span><strong></strong>
						</li>
						<li id="cur_pro_5" class="" style="width: 20%">
							<em id="bram_pro_5" class="">5<i></i>
							</em><span class="">选择增值服务</span><strong></strong>
						</li>
						<li id="cur_pro_6" class="Processlast" style="width: 82px">
							<em id="bram_pro_6" class="">6<i></i>
							</em><span>发布任务</span><strong></strong>
						</li>
					</ul>
				</div>
			</div>
			<hr style="font-size: 10px">
			<form id="task_form" method="post" enctype="multipart/form-data">
				<div id="process_1">
					<h3>选择平台</h3>
					<input name="bindPlat" id="bindPlat" type="hidden" value="tmall" />
					<div>
						<a onclick="bindPlatChange('taobao');">
							<img id="unchecked_img_taobao" src="${ctx}/images/unchecked_img.png" alt="unchecked">
							<img id="checked_img_taobao" style="display: none;" src="${ctx}/images/checked_img.png" alt="checked">
							<img src="${ctx}/images/taobao_logo.png" alt="淘宝">
						</a>
						<a onclick="bindPlatChange('tmall');">
							<img id="unchecked_img_tmall" style="display: none;" src="${ctx}/images/unchecked_img.png" alt="unchecked">
							<img id="checked_img_tmall" src="${ctx}/images/checked_img.png" alt="checked">
							<img src="${ctx}/images/tmall_logo.png" alt="天猫"> </a>
						<a onclick="bindPlatChange('jd');">
							<img id="unchecked_img_jd" src="${ctx}/images/unchecked_img.png" alt="unchecked">
							<img id="checked_img_jd" style="display: none;" src="${ctx}/images/checked_img.png" alt="checked">
							<img src="${ctx}/images/jd_logo.png" alt="京东">
						</a>
					</div>
					<br>
					<h3>选择店铺</h3>
					<b>
						店铺
					</b>
					<select name="shopId" id="shopId" class="span2" style="width: 300px">
					</select>
					<br>
					<h3>选择流量类型</h3>
					<b>
						流量类型
					</b>
					<select name="liuliangType" id="liuliangType" class="span2" style="width: 300px">
						<option value="1" selected="selected">自然搜索流量(630积分起) </option>
					</select>
				</div>
				<div id="process_2" style="display: none;">
					<h3>选择商品</h3>
					<b>
						商品链接
					</b>
					<input type="text" id="goodsUrl" name="goodsUrl" value="">
					<br>
					<b>
						商品名称
					</b>
					<input type="text" id="goodsName" name="goodsName" value="">
					<br>
					<b>
						商品主图
					</b>
					<input type="file" id="goodsImgFile" name="goodsImgFile" value="">
					<br>
					<h3>
						流量设置
					</h3>
					<h4>
						设置访客来源及每日访客数 <b style="color: red;">（${oneVisitCostScore }积分/访客）</b>
					</h4>
					<div>
						<p>
							<b>
								提示：
							</b>
							请填写3-5个访客入店搜索的关键词,
								流量将根据您设置的关键词和入口找到您的商品并点击进入您的店铺，请务必保证根据您的关键词在搜索结果的前十页 能找到您的商品。<a
								href="javascript:alert('为什么呢？');"
								target="_blank">为什么要填写3-5个关键词？</a>
						</p>
						<div id="inputKeyword">
							<div id="div_keyword1">
								<b>关键词1：</b>
								<input type="text" id="keyword1" name="keyword1" value="">
								&nbsp;&nbsp;
								<b>每日访客数：</b>
								<input type="text" id="orderNumberOneDay1" name="orderNumberOneDay1" value="">
								&nbsp;访客/天
								<b style="color: blue;">（最低10访客/天）</b>
								<b style="color: red;">必填</b>
							</div>
							<div id="div_keyword2">
								<b>关键词2：</b>
								<input type="text" id="keyword2" name="keyword2" value="">
								&nbsp;&nbsp;
								<b>每日访客数：</b>
								<input type="text" id="orderNumberOneDay2" name="orderNumberOneDay2" value="">
								&nbsp;访客/天
								<b style="color: blue;">（最低10访客/天）</b>
								<b style="color: red;">必填</b>
							</div>
							<div id="div_keyword3">
								<b>关键词3：</b>
								<input type="text" id="keyword3" name="keyword3" value="">
								&nbsp;&nbsp;
								<b>每日访客数：</b>
								<input type="text" id="orderNumberOneDay3" name="orderNumberOneDay3" value="">
								&nbsp;访客/天
								<b style="color: blue;">（最低10访客/天）</b>
								<b style="color: red;">必填</b>
							</div>
							<div id="div_keyword4" style="display: none;">
								<b>关键词4：</b>
								<input type="text" id="keyword4" name="keyword4" value="">
								&nbsp;&nbsp;
								<b>每日访客数：</b>
								<input type="text" id="orderNumberOneDay4" name="orderNumberOneDay4" value="">
								&nbsp;访客/天
								<b style="color: blue;">（最低10访客/天）</b>
								<a onclick="div_keyword4_del()">删除</a>
							</div>
							<div id="div_keyword5" style="display: none;">
								<b>关键词5：</b>
								<input type="text" id="keyword5" name="keyword5" value="">
								&nbsp;&nbsp;
								<b>每日访客数：</b>
								<input type="text" id="orderNumberOneDay5" name="orderNumberOneDay5" value="">
								&nbsp;访客/天
								<b style="color: blue;">（最低10访客/天）</b>
								<a onclick="div_keyword5_del()">删除</a>
							</div>
			    			<button class="btn btn-primary" type="button" onclick="addKeyword();" data-toggle="button" data-loading-text="<i class='icon-plus'></i>&nbsp;新增关键词"><i class="icon-plus"></i>&nbsp;新增关键词</button>
						</div>
					</div>
					<div>
						<h4>
							设置流量来源分布占比<b>（所有渠道流量来源占比需为100%）</b>
						</h4>
						<p class="prompt">
							提示：搜索关键词将随机分配给不同的搜索渠道
						</p>
						<div class="percentage">
							<div>
								<b>电脑端淘宝自然搜索占比：</b>
								<input type="text" style="width: 30px;" id="searchSource" name="searchSource" value="100">%
							</div>
							<div>
								<b>电脑端天猫自然搜索占比：</b>
								<input type="text" style="width: 30px;" disabled="disabled" id="searchSource_cut" name="searchSource" value="0">%
							</div>
						</div>
						<div>
							<span id="hd_percent" class="error"
								style="margin-left: 0; display: none;">所有渠道流量来源占比需为100%</span>
						</div>
						<h4>
							选择流量购买时长<b>（任务发布后，可根据店铺实际情况，在任务管理中修改每日访客数）</b>
						</h4>
						<p class="prompt">
							<label>
								提示：今日18:00前发布的流量任务今日会开始执行，18:00点之后发布的流量会明日开始执行。
							</label>
						</p>
						<b>
							选择时长
						</b>
						<select name="durationDay" id="durationDay" class="span2">
							<option value="7">7天</option>
							<option value="10" selected="selected">10天 </option>
							<option value="15">15天</option>
							<option value="30">30天</option>
						</select>
					</div>
					<div>
						<h3>费用统计</h3>
						<b>******积分</b>
					</div>
				</div>
	            <div id="process_3" style="display: none;">
					<h3>增值服务</h3>
	            	<div>
	                	<h4>店铺数据优化</h4>
	                	<div>
		                   	<h5>1.页面停留时间优化</h5>
		                    <b>提示：流量到达店铺后将默认停留30~60秒，选择此项服务后将有助于优化店铺平均停留时间</b>
							<b>设置停留时间</b>
							<select name="pageStayType" id="pageStayType" class="span2">
								<option value="0" selected="selected">30~60秒</option>
								<option value="1">60~120秒（${pageStayCostScoreMap['1'] }积分）</option>
								<option value="2">120~180秒（${pageStayCostScoreMap["2"] }积分） </option>
							</select>
	                	</div>
	                </div>
	                <div>
	                	<h4>流量分布优化</h4>
	                	<div>
		                   	<h5>1.流量访问时间优化</h5>
		                   	<b>提示：流量进入店铺时间默认全天平均分布，选择此项服务后，流量将按照固定的分布规律进入店铺；</b>
							<select name="visitTimeType" id="visitTimeType" class="span2" style="width: 300px">
								<option value="0" selected="selected">全天平均分布</option>
								<option value="1">随机分布（${visitTimeCostScoreMap['1'] }积分）</option>
								<option value="2">网购用户习惯曲线分布（${visitTimeCostScoreMap["2"] }积分）</option>
							</select>
	                	</div>
	                </div>
	                <div style="border:none;">
	                	<h4>快速完成任务</h4>
	                	<div>
		                   	<h5>1.优先审单</h5>
		                   	<h5>提示：选择此项服务后。平台将优先审核您发布的流量任务</h5>
		                	<select name="isQuickVerify" id="isQuickVerify" class="span2">
								<option value="0" selected="selected">普通</option>
								<option value="1">任务优先审核（${quickVerifyCostScore }积分）</option>
							</select>
	                	</div>
	                	<div>
	                    	<h4>2.流量优先执行</h4>
	                        <div>
	                        	<h5>提示：平台默认所有商家的流量任务按照任务发布时间排队执行，选择此项服务后，当系统繁忙时，优先执行你的任务；</h5>
			                	<select name="isQuickExecute" id="isQuickExecute" class="span2">
									<option value="0" selected="selected">普通</option>
									<option value="1">任务优先执行（${quickExecuteCostScore }积分）</option>
								</select>
	                        </div>
	                    </div>
	                </div>
					<div>
						<h3>费用统计</h3>
						<b>******积分</b>
					</div>
					<input class="btn btn-primary" type="button" onclick="ajax_submit();" value="发布任务">
	            </div>
	            <div id="process_4" style="display: none;">
                	<h4>任务发布成功</h4>
	            </div>
			</form>
			<div class="publish-btnbox">
				<table width="50%" style="table-layout:fixed; word-wrap:break-word;">
					<tr>
						<td width="50%">
							<span id="pre_process_btn" class="sk-arrange-gray" style="display: none;"><a class="sk-arrange-next to-prev" onclick="pre_process()">上一步</a>
							</span>
						</td>
						<td width="50%">
							<span id="next_process_btn" class="sk-arrange-gray"><a class="sk-arrange-next" onclick="next_process()">下一步</a>
							</span>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>