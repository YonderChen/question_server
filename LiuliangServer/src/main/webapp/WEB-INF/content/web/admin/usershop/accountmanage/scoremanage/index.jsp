<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<jsp:include page="../../../include/style.jsp" flush="true"></jsp:include>
	<script type="text/javascript">
	
	$(document).ready(function(){
	    priceChange();
	  	$("#price").change(function(){
		    priceChange();
	  	});
	});
	
	function priceChange() {
		//金额变更
		$("#scoreNum").html($("#price").val()*1.8)
	}
	
	function submit() {
		if($("#price").val().trim() == ""){
			alert("请选择充值金额");
			return;
		}
		if($("#dealId").val().trim() == ""){
			alert("请输入转账交易号");
			$("#dealId").select();
			return;
		}
		var url = "${ctx}/web/admin/usershop/accountmanage/scoremanage/add";
		$.ajax({
			url:url,
			type:'post',
			data:{
				price : $("#price").val().trim(),
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
					购买积分
				</li>
			</ul>
		</div>

<div class="container">
    <div class="Shadowbox">
        <div class="register">
            <div class="register-main">
                  <div class="register">
                    <div class="register_info">
						<h3>1.请选择积分<span>因每月所售积分有限，<em>建议提前一次性选择足够1个月使用的积分数量</em></span></h3>
                        <div>
                        	充值金额：<select name="price" id="price" class="span2">
								<option value="50">50元</option>
								<option value="100" selected="selected">100元 </option>
								<option value="500">500元</option>
							</select>
							您将获得的积分：<b id="scoreNum"></b>
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
                  <p class="f14 cor555">问：转账成功后，积分多久会到账？ </p>
                  <p class="f14 cor555">答：要等后台管理人员验证订单的真实性后，积分就会发放到账，一般在两天之内。</p>
              </div>
            
            </div>  
        </div>
        <div class="Shadowboxb"></div>
    </div>
</div>
</body></html>