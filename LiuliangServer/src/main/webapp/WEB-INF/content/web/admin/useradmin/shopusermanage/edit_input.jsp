<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<input type="hidden" id="userId" name="userId" value="${user.userId }"/>
<table width="100%" border="1">
  <tr>
    <td align="right">平台账号：</td>
    <td>${user.username }</td>
  </tr>
  <tr>
    <td align="right">会员到期时间：</td>
    <td>
		<input style="width:180px;" name="e_vipEndTime" id="e_vipEndTime" type="text" value="<s:date name="#request.user.vipEndTime" format="yyyy-MM-dd HH:mm:ss"/>" class="txt placebox" onclick="javascript:WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
  </td>
  </tr>
  <tr>
    <td align="right">剩余积分：</td>
    <td>
    	<input style="width:180px;" name="e_score" id="e_score" type="text" class="span2" maxlength="50" value="${user.score }"/>
    </td>
  </tr>
  <tr>
    <td align="right">邮箱：</td>
    <td>
    	<input style="width:180px;" name="e_email" id="e_email" type="text" class="span2" maxlength="50" value="${user.email }"/>
    </td>
  </tr>
  <tr>
    <td align="right">QQ：</td>
    <td>
    	<input style="width:180px;" name="e_userqq" id="e_userqq" type="text" class="span2" maxlength="50" value="${user.userqq }"/>
    </td>
  </tr>
  <tr>
    <td align="right">手机：</td>
    <td>
    	<input style="width:180px;" name="e_phone" id="e_phone" type="text" class="span2" maxlength="50" value="${user.phone }"/>
    </td>
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
</table>