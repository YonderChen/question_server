<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<input type="hidden" id="userId" name="userId" value="${user.userId }"/>
<input type="hidden" id="isAdmin" name="isAdmin" value="${isAdmin }"/>
<s:if test="#request.isAdmin">
<input type="hidden" id="roleIds" name="roleIds" value="${roleIds }"/>
</s:if>
<table width="100%" border="1">
  <tr>
    <td align="right">用户名：</td>
    <td>${user.username }</td>
  </tr>
  <tr>
    <td align="right">姓名：</td>
    <td>${user.name }</td>
  </tr>
  <tr>
    <td align="right">联系电话：</td>
    <td>${user.phone }</td>
  </tr>
  <tr>
    <td align="right">状态<em class="text-error"  style="font-size:16px">*</em>：</td>
    <td>
    <select id="status_e" name="status_e" class="span2">
						<option value="1" <s:if test="#request.user.status == 1">selected</s:if>>正常</option>
						<option value="2" <s:if test="#request.user.status == 2">selected</s:if>>冻结</option>
					</select>
    </td>
  </tr>
  	<s:if test="#request.isAdmin">
	<tr>
    <td align="right" style="padding-top:5px;">所属角色<em class="text-error"  style="font-size:16px">*</em>：</td>
    <td id="roleTd_e">
    <s:iterator value="#request.roleList" id="role">
		<label class="checkbox inline" title="${role.description }"><input type="checkbox" id="${role.roleId }"/>${role.name }</label>
	</s:iterator>
    </td>
  </tr>
	</s:if>
	<s:else>
	<tr>
    <td align="right">所属角色：</td>
    <td>${user.roleName }</td>
  </tr>
	</s:else>
</table>