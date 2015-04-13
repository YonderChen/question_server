<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<jsp:include page="../../../include/style.jsp" flush="true"></jsp:include>
	<script type="text/javascript">
	
	$(document).ready(function(){
	    numChange();
	  	$("#num").change(function(){
		    numChange();
	  	});
	});
	
	function numChange() {
		//金额变更
		$("#price").html($("#num").val() * "${VIPPriceRate }")
	}
	
	function submit() {
		if($("#num").val().trim() == ""){
			alert("请选择续费月数");
			return;
		}
		if($("#dealId").val().trim() == ""){
			alert("请输入转账交易号");
			$("#dealId").select();
			return;
		}
		var url = "${ctx}/web/admin/useradmin/verifymanage/dealmanage/add_vip_order";
		$.ajax({
			url:url,
			type:'post',
			data:{
				num : $("#num").val().trim(),
				dealId : $("#dealId").val().trim()
			},
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
						$("#price").val("taobao");
						$("#dealId").val("");
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
					续费会员
				</li>
			</ul>
		</div>

<div class="container">
    <div class="Shadowbox">
        <div class="register">
            <div class="register-main">
                  <div class="register">
                    <div class="register_info">
						<h3>1.请选择要续费的月数</h3>
                        <div>
                        	续费月数：<select name="num" id="num" class="span2">
								<option value="3">3个月</option>
								<option value="6" selected="selected">6个月 </option>
								<option value="12">12个月</option>
							</select>
							价格：<b id="price" style="color: red;"></b>元
                        </div>
                        <div>
                            <h3>2.输入转账交易号</h3>
                           	 交易号：<input type="text" id="dealId" name="dealId" value="">
                            <div>
                            	<img id="jiaoyihao_img" alt="交易号" src="${ctx}/images/jiaoyihao.jpg">
                            </div>
                        </div>
                        <button class="btn btn-primary" data-toggle="button" data-loading-text="提交审核" onclick="submit();">提交审核</button>
                    </div>
                </div>
              
              <div class="pay-problem">
                  <h3 class="f18 cor555">常见问题</h3>
                  <p class="f14 cor555">问：转账成功后，续费会员多久才会生效？ </p>
                  <p class="f14 cor555">答：要等后台管理人员验证订单的真实性后，续费会员就会生效，一般在两天之内。</p>
              </div>
            
            </div>  
        </div>
        <div class="Shadowboxb"></div>
    </div>
</div>
</body></html>