<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<input type="hidden" id="shopId" name="shopId" value="${shop.shopId }"/>
<table width="100%" border="1">
  <tr>
    <td align="right">平台账号：</td>
    <td>${shop.serverUser.username }</td>
  </tr>
  <tr>
    <td align="right">店铺名称：</td>
    <td>
    	<input style="width:180px;" name="e_bindName" id="e_bindName" type="text" class="span2" maxlength="50" value="${shop.bindName }"/>
    </td>
  </tr>
  <tr>
    <td align="right">绑定平台：</td>
    <td>
	    <select id="e_bindPlat" name="e_bindPlat" class="span2">
			<option value="taobao" <s:if test="#request.shop.bindPlat == 'taobao'">selected</s:if>>淘宝</option>
			<option value="tmall" <s:if test="#request.shop.bindPlat == 'tmall'">selected</s:if>>天猫</option>
			<option value="jd" <s:if test="#request.shop.bindPlat == 'jd'">selected</s:if>>京东</option>
		</select>
	</td>
  </tr>
  <tr>
    <td align="right">店铺地址：</td>
    <td>
    	<input style="width:180px;" name="e_shopUrl" id="e_shopUrl" type="text" class="span2" maxlength="50" value="${shop.shopUrl }"/>
    </td>
  </tr>
  <tr>
    <td align="right">验证商品地址：</td>
    <td>
    	<input style="width:180px;" name="e_verifyGoodsUrl" id="e_verifyGoodsUrl" type="text" class="span2" maxlength="50" value="${shop.verifyGoodsUrl }"/>
    </td>
  </tr>
  <tr>
    <td align="right">验证码：</td>
    <td>
    	<input style="width:180px;" name="e_verifyCode" id="e_verifyCode" type="text" class="span2" maxlength="50" value="${shop.verifyCode }"/>
    </td>
  </tr>
  <tr>
    <td align="right">状态<em class="text-error"  style="font-size:16px">*</em>：</td>
    <td>
    <select id="status_e" name="status_e" class="span2">
		<option value="0" <s:if test="#request.shop.status == 0">selected</s:if>>待审核</option>
		<option value="1" <s:if test="#request.shop.status == 1">selected</s:if>>审核成功</option>
		<option value="2" <s:if test="#request.shop.status == 2">selected</s:if>>审核失败</option>
	</select>
    </td>
  </tr>
</table>