<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<jsp:include page="../../../include/style.jsp" flush="true"></jsp:include>
	<script type="text/javascript">
	$('document').ready(function() {
		var randomVerifyCode = getVerifyCode();
		$("#verifyCode").val(randomVerifyCode);
		$("#verifyCode_b").html(randomVerifyCode);
	});
	
	function getVerifyCode() {
	    var str = "";
	    var numberArr = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];
	    var wordArr = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
		str += randomWord(4, numberArr);
		str += "-";
		str += randomWord(4, wordArr);
		return str;
	}
	function randomWord(length, arr){
	    var str = "";
	    // 随机产生
	    for(var i=0; i<length; i++){
	        pos = Math.round(Math.random() * (arr.length-1));
	        str += arr[pos];
	    }
	    return str;
	}
	function bind() {
		if($("#bindPlat").val().trim() == ""){
			alert("请选择平台");
			return;
		}
		if($("#bindName").val().trim() == ""){
			alert("请输入店铺旺旺");
			$("#bindName").select();
			return;
		}
		if($("#shopUrl").val().trim() == ""){
			alert("请输入店铺首页URL");
			$("#shopUrl").select();
			return;
		}
		if($("#verifyGoodsUrl").val().trim() == ""){
			alert("请输入验证商品URL");
			$("#verifyGoodsUrl").select();
			return;
		}
		$(".btn-cancel").button('loading');
		$(".btn-primary").button('loading');
		var url = "${ctx}/web/admin/usershop/accountmanage/shopmanage/add";
		$.ajax({
			url:url,
			type:'post',
			data:{
				bindPlat : $("#bindPlat").val().trim(),
				bindName : $("#bindName").val().trim(),
				shopUrl : $("#shopUrl").val().trim(),
				verifyCode : $("#verifyCode").val().trim(),
				verifyGoodsUrl : $("#verifyGoodsUrl").val().trim()
			},
			dataType:'text',
			timeout:60000,
			error: function(e) {
				$(".btn-cancel").button('reset');
				$(".btn-primary").button('reset');
				alert("连接服务器超时,请稍后再试.");
			},
			success: function(result){
				if (!isOutTime(result)) {
					$(".btn-cancel").button('reset');
					$(".btn-primary").button('reset');
					result = eval("("+result+")");
					alert(result.msg);
					if (result.success) {
						bindPlat:$("#bindPlat").val("taobao");
						bindName:$("#bindName").val("");
						shopUrl:$("#shopUrl").val("");
						verifyCode:$("#verifyCode").val("");
						verifyGoodsUrl:$("#verifyGoodsUrl").val("");
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
					账户管理
				</li>
				<li>
					绑定店铺
				</li>
			</ul>
		</div>

		<div class="container">
			<div class="Shadowbox">
				<div class="Shadowboxp"></div>
				<div class="register">
					<div class="register-main">
						<!-- 续费会员时显示 -->
						<div class="binding-shop">
							<div class="business-bind-wrap clearfix">
								<div class="business-bind-left">
									<h3>
										选择平台
									</h3>
									<select name="bindPlat" id="bindPlat" class="span2">
										<option value="taobao">淘宝</option>
										<option value="tmall" selected="selected">天猫 </option>
										<option value="jd">京东</option>
									</select>
								</div>

								<div class="business-bind-right">

									<!--add-->
									<h3>
										绑定新店铺
									</h3>
									<div class="business-bind-box">
										<div class="business-bind-list business-bind-new">

											<div>
													<i style="color: red;">*</i> 店铺主旺旺（账号）:
													<input type="text" class="txt" id="bindName" name="bindName" value="">(店铺主旺旺绑定后无法修改)
												<div class="inp">
													
												</div>
											</div>
											<div>
													<i style="color: red;">*</i> 店铺首页网址（URL）:
													<input type="text" id="shopUrl" name="shopUrl" value="">
												<div class="inp">
													
												</div>
											</div>
											<div>
												验证码: <b id="verifyCode_b">9650-ALTS</b>
												<input type="hidden" id="verifyCode" name="verifyCode" value="9650-ALTS">
												
											</div>
											<p>
												1.将验证码加到您的店铺里某个上架商品的标题上，类似这样：
											</p>
											<img alt="商品验证" src="${ctx}/images/binding_shopimg.jpg">
											<p>
												2.再将这个商品的详情页链接，复制到下面输入框
											</p>
											<div>
												<i style="color: red;">*</i> 商品网址（URL）:
												<input type="text" id="verifyGoodsUrl" name="verifyGoodsUrl" value="">
											</div>

											<button class="btn btn-primary" data-toggle="button" data-loading-text="提交审核" onclick="bind();">提交审核</button>
											
										</div>
									</div>
									<!--add-->
									<div class="binding-shop-list">
										<p>
											提示：绑定的店铺提交认证后，平台预计在1小时内完成审核操作，只有审核通过的商家才可以发布任务
										</p>
									</div>


								</div>
							</div>
						</div>
						<div class="pay-problem">
							<h3 class="f18 cor555">
								常见问题
							</h3>
							<p class="f14 cor555">
								问：提交店铺认证后，多久可以通过审核呢？
							</p>
							<p class="f14 cor555">
								答：一般在提交后的1小时内完成。
							</p>
							<br>
							<p class="f14 cor555">
								问：一个账号最多可以绑定几个店铺呢？
							</p>
							<p class="f14 cor555">
								答：一个平台最多可以绑定十个店铺。
							</p>
							<br>
							<p class="f14 cor555">
								问：商品名称里的验证码需要放多长时间？
							</p>
							<p class="f14 cor555">
								答：绑定的店铺通过审核之后，就可以去掉验证码了。另：绑定店铺是自动审核的，符合条件系统就会自动审核通过。。
							</p>
						</div>

					</div>
				</div>
				<div class="Shadowboxb"></div>
			</div>
		</div>
	</body>
</html>