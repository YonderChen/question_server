<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<jsp:include page="../../include/style.jsp" flush="true"></jsp:include>
	<script type="text/javascript">
	
	$(document).ready(function(){
	    shopPlatChange();
	  	$("#shopPlat").change(function(){
		    shopPlatChange();
	  	});
	  	$("#searchSource").change(function(){
	  		$("#searchSource_cut").val(100 - $("#searchSource").val());
	  	})
	});
	
	function shopPlatChange() {
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
	
	function additional(){
		if(!$("#additional").is(":hidden")){
			$("#additional").hide();
		} else {
			$("#additional").show();
		}
	}
	
	function submit() {
		if($("#bindPlat").val().trim() == ""){
			alert("请选择平台");
			$("#bindPlat").select();
			return;
		}
		if($("#shopId").val().trim() == ""){
			alert("请选择店铺");
			$("#shopId").select();
			return;
		}
		if($("#goodsUrl").val().trim() == ""){
			alert("请输入商品URL");
			$("#goodsUrl").select();
			return;
		}
		if($("#goodsName").val().trim() == ""){
			alert("请输入商品名称");
			$("#goodsName").select();
			return;
		}
		if($("#goodsImgFile").val().trim() == ""){
			alert("请选择商品主图");
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
			},
			success: function(result){
				if (!isOutTime(result)) {
					result = eval("("+result+")");
					alert(result.msg);
					if (result.success) {
						location.reload();
					}
				}
			}
		});
	}
	
</script>
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
		<p></p>
		<div class="container">
			<form id="task_form" method="post" enctype="multipart/form-data">
				<div>
					<h3>选择店铺</h3>
					<b>
						选择平台
					</b>
					<select name="bindPlat" id="bindPlat" class="span2">
						<option value="taobao">淘宝</option>
						<option value="tmall" selected="selected">天猫 </option>
						<option value="jd">京东</option>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<b>
						选择店铺
					</b>
					<select name="shopId" id="shopId" class="span2">
						<option value="402881834ca77f8c014ca781d73d0001" selected="selected">A</option>
						<option value="402881834ca7b224014ca7b281660001">B</option>
						<option value="402882eb4ca44f76014ca4508d220001">C</option>
					</select>
				</div>
				<div>
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
				</div>
				<div>
					<h3>
						流量设置
					</h3>
					<h4>
						设置访客来源及每日访客数 <b style="color: red;">（3积分/访客）</b>
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
					<div id="searchSource_div">
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
					</div>
					<h4>
						选择流量购买时长<b>（任务发布后，可根据店铺实际情况，在任务管理中修改每日访客数）</b>
					</h4>
					<div id="selectTime">
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
	    		<input class="btn btn-primary" type="button" onclick="additional();" value="增值服务">
				</div>
	            <div id="additional" style="display: none;">
					<h3>增值服务</h3>
	            	<div>
	                	<h4>店铺数据优化</h4>
	                	<div>
		                   	<h5>1.页面停留时间优化</h5>
		                    <b>提示：流量到达店铺后将默认停留30~60秒，选择此项服务后将有助于优化店铺平均停留时间</b>
							<b>设置停留时间</b>
							<select name="pageStayType" id="pageStayType" class="span2">
								<option value="0" selected="selected">30~60秒</option>
								<option value="1">60~120秒</option>
								<option value="2">120~180秒 </option>
							</select>
	                	</div>
	                </div>
	                <div>
	                	<h4>流量分布优化</h4>
	                	<div>
		                   	<h5>1.流量访问时间优化</h5>
		                   	<b>提示：流量进入店铺时间默认全天平均分布，选择此项服务后，流量将按照固定的分布规律进入店铺；</b>
							<select name="visitTimeType" id="visitTimeType" class="span2">
								<option value="0" selected="selected">全天平均分布</option>
								<option value="1">随机分布（50积分）</option>
								<option value="2">网购用户习惯曲线分布（60积分）</option>
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
								<option value="1">任务优先审核（50积分）</option>
							</select>
	                	</div>
	                	<div>
	                    	<h4>2.流量优先执行</h4>
	                        <div>
	                        	<h5>提示：平台默认所有商家的流量任务按照任务发布时间排队执行，选择此项服务后，当系统繁忙时，优先执行你的任务；</h5>
			                	<select name="isQuickExecute" id="isQuickExecute" class="span2">
									<option value="0" selected="selected">普通</option>
									<option value="1">任务优先执行（50积分）</option>
								</select>
	                        </div>
	                    </div>
	                </div>
	            </div>
			</form>
	    	<input class="btn btn-primary" type="button" onclick="submit();" value="发布任务">
		</div>
	</body>
</html>