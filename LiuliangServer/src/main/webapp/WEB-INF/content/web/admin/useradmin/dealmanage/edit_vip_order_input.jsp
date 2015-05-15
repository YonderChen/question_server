<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<input type="hidden" id="orderId" name="orderId" value="${order.orderId }"/>
<table width="100%" border="1">
  <tr>
    <td align="right">平台账号：</td>
    <td>${order.serverUser.username }</td>
  </tr>
  <tr>
    <td align="right">续费月数：</td>
    <td>
		<s:if test="#request.order.status == 1">
			${order.num }
			<script type="text/javascript">
				$("#edit_order_button").hide();
			</script>
		</s:if>
		<s:else>
    		<input style="width:180px;" name="e_num" id="e_num" type="text" class="span2" maxlength="50" value="${order.num }"/>
			<script type="text/javascript">
				$("#edit_order_button").show();
			</script>
		</s:else>
    </td>
  </tr>
  <tr>
    <td align="right">价格：</td>
    <td>${order.price }</td>
  </tr>
  <tr>
    <td align="right">转账交易号：</td>
    <td>${order.dealId }</td>
  </tr>
  <tr>
    <td align="right">理由：</td>
    <td>${order.reason }</td>
  </tr>
  <tr>
    <td align="right">付款时间：</td>
    <td><s:date name="payTime" format="yyyy-MM-dd HH:mm:ss"/></td>
  </tr>
  <tr>
    <td align="right">付款截图：</td>
    <td><img width="50px" height="80px" alt="" src="${ctx}${order.payImgUrl}" onclick="javascript:showImg('${ctx}${order.payImgUrl}');" /></td>
  </tr>
  <tr>
    <td align="right">创建事件：</td>
    <td><s:date name="#request.order.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
  </tr>
  <tr>
    <td align="right">状态：</td>
    <td>
		<s:if test="#request.order.status == 1">
			审核通过
		</s:if>
		<s:else>
		    <select id="e_status" name="e_status" class="span2">
				<option value="0" <s:if test="#request.order.status == '0'">selected</s:if>>待审核</option>
				<option value="1" <s:if test="#request.order.status == '1'">selected</s:if>>审核通过</option>
				<option value="2" <s:if test="#request.order.status == '2'">selected</s:if>>审核失败</option>
			</select>
		</s:else>
	</td>
  </tr>
</table>