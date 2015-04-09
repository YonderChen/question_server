<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<jsp:include page="../../../include/style.jsp" flush="true"></jsp:include>
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
					<form action="" method="post" id="frm_binding">
						<div class="register-main">
							<!-- 续费会员时显示 -->
							<div class="binding-shop">
								<div class="business-bind-wrap clearfix">
									<div class="business-bind-left">
										<h3>
											选择平台
										</h3>
										<input type="radio" name="bind_plat" value="taobao">淘宝&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="bind_plat" value="tmall" checked="checked">天猫 &nbsp;&nbsp;&nbsp;
										<input type="radio" name="bind_plat" value="jd">京东
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
														<input type="text" class="txt" name="bind_name" value="">(店铺主旺旺绑定后无法修改)
													<div class="inp">
														
													</div>
												</div>
												<div>
														<i style="color: red;">*</i> 店铺首页网址（URL）:
														<input type="text" name="bind_url" value="">
													<div class="inp">
														
													</div>
												</div>
												<div>
														验证码:<b>9650-ALTS</b>
												</div>
												<p>
													1.将验证码加到您的店铺里某个上架商品的标题上，类似这样：
												</p>
												<p>
													2.再将这个商品的详情页链接，复制到下面输入框
												</p>
												<div>
													<i style="color: red;">*</i> 商品网址（URL）:
													<input type="text" name="check_url" value="">
												</div>

												<button class="btn btn-primary" data-toggle="button" data-loading-text="提交审核" onclick="">提交审核</button>
												<button class="btn btn-cancel" data-dismiss="modal" aria-hidden="true" data-toggle="button" data-loading-text="重置">重置</button>
												
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
					</form>
				</div>
				<div class="Shadowboxb"></div>
			</div>
		</div>
	</body>
</html>