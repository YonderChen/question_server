<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<input type="hidden" id="uid_e" name="uid_e" value="${user.uid }"/>
<table width="100%" border="1">
  <tr>
    <td align="right">用户uid：</td>
    <td>${user.uid }</td>
  </tr>
  <tr>
    <td align="right">用户类型：</td>
    <td>
		<s:if test="#request.user.userType == 0">第三方账户</s:if>
		<s:if test="#request.user.userType == 1">本地账户</s:if>
	</td>
  </tr>
  <tr>
    <td align="right">第三方id：</td>
    <td>${user.openId}</td>
  </tr>
  <tr>
    <td align="right">本地帐号：</td>
    <td>${user.username}</td>
  </tr>
  <tr>
    <td align="right">昵称：</td>
    <td>${user.name }</td>
  </tr>
  <tr>
    <td align="right">头像：</td>
    <td><img alt="" width="50px" height="50px" src="${ctx}${user.figureurl}" /></td>
  </tr>
  <tr>
    <td align="right">状态<em class="text-error"  style="font-size:16px">*</em>：</td>
    <td>
	    <select id="status_e" name="status_e" class="span2">
			<option value="0" <s:if test="#request.user.status == 0">selected</s:if>>正常</option>
			<option value="1" <s:if test="#request.user.status == 1">selected</s:if>>禁言</option>
			<option value="2" <s:if test="#request.user.status == 2">selected</s:if>>封号</option>
		</select>
    </td>
  </tr>
</table>