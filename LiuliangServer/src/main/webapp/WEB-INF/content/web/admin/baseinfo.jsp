<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<jsp:include page="include/style.jsp" flush="true"></jsp:include>
			<script type="text/javascript">
	function preFunArray() {
		var funs = new Array();
		funs.push(loadData);
		return funs;
	}
	
	function loadData() {
		if ("${editPassModal}" == "true") {
			editPassInput();
		}
		$(".usual ul").idTabs();
	}
	
	function editPassInput() {
		$('#editPassModal').modal('show');
	}
	
	function editInfoInput() {
		$('#editInfoModal').modal('show');
	}
	
	function editPass() {
		if ($("#oldPassword").val().trim() == "") {
			alert("原始密码不能为空");
			scrollAndSelect("oldPassword");
			return;
		}
		if ($("#newPassword").val().trim() == "") {
			alert("新密码不能为空");
			scrollAndSelect("newPassword");
			return;
		}
		if ($("#newPassword").val().trim() != $("#newPassword_c").val().trim()) {
			alert("两次新密码不一致");
			scrollAndSelect("newPassword_c");
			return;
		}
		$(".btn-cancel").button('loading');
		$(".btn-primary").button('loading');
		var url = "${ctx}/web/admin/edit_pass";
		$.ajax({
			url:url,
			type:'post',
			data:{
				oldPassword: $("#oldPassword").val().trim(),
				newPassword:$("#newPassword").val().trim()
			},
			dataType:'text',
			timeout:60000,
			error: function(e) {
				$(".btn-cancel").button('reset');
				$(".btn-primary").button('reset');
				$(".active").removeClass('active');
				alert("连接服务器超时,请稍后再试.");
			},
			success: function(result){
				$(".btn-cancel").button('reset');
				$(".btn-primary").button('reset');
				$(".active").removeClass('active');
				if (!isOutTime(result)) {
					result = eval("("+result+")");
					if (result.success) {
						$("#oldPassword").val("");
						$("#newPassword").val("");
						$("#newPassword_c").val("");
						$('#editPassModal').modal('hide');
					} else {
						alert(result.msg);
						$("#newPassword").val("");
						$("#newPassword_c").val("");
						scrollAndSelect("oldPassword");
					}
				}
			}
		});
	}
	
	function editInfo() {
		if ($("#name").val().trim() == "") {
			alert("姓名不能为空");
			scrollAndSelect("name");
			return;
		}
		if ($("#phone").val().trim() == "") {
			alert("联系电话不能为空");
			scrollAndSelect("phone");
			return;
		}
		if (!isTelephone($("#phone").val().trim())) {
			alert("联系电话无效");
			scrollAndSelect("phone");
			return;
		}
		$(".btn-cancel").button('loading');
		$(".btn-primary").button('loading');
		var url = "${ctx}/web/admin/edit_info";
		$.ajax({
			url:url,
			type:'post',
			data:{
				name: $("#name").val().trim(),
				phone: $("#phone").val().trim()
			},
			dataType:'text',
			timeout:60000,
			error: function(e) {
				$(".btn-cancel").button('reset');
				$(".btn-primary").button('reset');
				$(".active").removeClass('active');
				alert("连接服务器超时,请稍后再试.");
			},
			success: function(result){
				$(".btn-cancel").button('reset');
				$(".btn-primary").button('reset');
				$(".active").removeClass('active');
				if (!isOutTime(result)) {
					result = eval("("+result+")");
					if (result.success) {
						$('#editInfoModal').modal('hide');
						location.reload();
					} else {
						alert(result.msg);
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
					基本信息
				</li>
			</ul>
		</div>
		<div class="mainindex">
			<div class="welinfo">
				姓名： <b>${sessionServerUserInfo.name }</b><br>
				用户名：<b>${sessionServerUserInfo.username }</b><br>
				电子邮箱：<b>${sessionServerUserInfo.email }</b><br>
				联系电话：<b>${sessionServerUserInfo.phone }</b><br>
			</div>
	
			<div class="welinfo">
				您上次登录的时间：<s:date name="#session.loginLast" format="yyyy-MM-dd HH:mm:ss" />
			</div>
	
			<div class="welinfo">
				<i><a href="javascript:editPassInput();">修改密码</a></i>
				<i><a href="javascript:editInfoInput();">修改基础信息</a></i>
			</div>
		</div>
	<div id="editPassModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="editPassModalLabel" aria-hidden="true" style="z-index:100000;position: absolute;" data-backdrop="static">
			<div class="modal-header">
				<button type="button" class="close btn-cancel" data-dismiss="modal" aria-hidden="true" data-toggle="button" data-loading-text="×">×</button>
			<h3 id="editPassModalLabel">修改密码</h3>
			</div>
			<div class="modal-body">
				<table width="100%" border="1">
				  <tr>
				    <td align="right">原始密码<em class="text-error"  style="font-size:16px">*</em>：</td>
				    <td><input name="oldPassword" id="oldPassword" type="password" class="span2" maxlength="50" value="" placeholder="原始密码"/></td>
				  </tr>
				  <tr>
				    <td align="right">新密码<em class="text-error"  style="font-size:16px">*</em>：</td>
				    <td><input name="newPassword" id="newPassword" type="password" class="span2" maxlength="50" value="" placeholder="新密码"/></td>
				  </tr>
				  <tr>
				    <td align="right">确认新密码<em class="text-error"  style="font-size:16px">*</em>：</td>
				    <td><input name="newPassword_c" id="newPassword_c" type="password" class="span2" maxlength="50" value="" placeholder="确认新密码"/></td>
				  </tr>
				</table>
			</div>
			<div class="modal-footer">
			<button class="btn btn-primary" data-toggle="button" data-loading-text="保存" onclick="javascript:editPass();">保存</button>
			<button class="btn btn-cancel" data-dismiss="modal" aria-hidden="true" data-toggle="button" data-loading-text="关闭">关闭</button>
			</div>
		</div>
		
		<div id="editInfoModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="editInfoModalLabel" aria-hidden="true" style="z-index:100000;position: absolute;" data-backdrop="static">
			<div class="modal-header">
				<button type="button" class="close btn-cancel" data-dismiss="modal" aria-hidden="true" data-toggle="button" data-loading-text="×">×</button>
			<h3 id="editInfoModalLabel">修改基础信息</h3>
			</div>
			<div class="modal-body">
				<table width="100%" border="1">
				  <tr>
				    <td align="right">姓名<em class="text-error"  style="font-size:16px">*</em>：</td>
				    <td><input name="name" id="name" type="text" class="span2" maxlength="20" value="${sessionServerUserInfo.name }" placeholder="姓名"/></td>
				  </tr>
				  <tr>
				    <td align="right">联系电话<em class="text-error"  style="font-size:16px">*</em>：</td>
				    <td><input name="phone" id="phone" type="text" class="span2" maxlength="20" value="${sessionServerUserInfo.phone }" placeholder="联系电话"/></td>
				  </tr>
				</table>
			</div>
			<div class="modal-footer">
			<button class="btn btn-primary" data-toggle="button" data-loading-text="保存" onclick="javascript:editInfo();">保存</button>
			<button class="btn btn-cancel" data-dismiss="modal" aria-hidden="true" data-toggle="button" data-loading-text="关闭">关闭</button>
			</div>
		</div>
	</body>
</html>