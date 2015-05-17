<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:if test="#request.pageBean.allRow == 0">
<div class='no-found'>无结果.</div>
</s:if>
<s:else>

                        <table width="95%" class="tablelist">
				<thead>
					<tr>
						<th width="10%">
							用户uid
						</th>
						<th width="10%">
							账户类型
						</th>
						<th width="10%">
							第三方id
						</th>
						<th width="10%">
							本地帐号
						</th>
						<th width="10%">
							昵称
						</th>
						<th width="10%">
							头像
						</th>
						<th width="10%">
							更新时间
						</th>
						<th width="10%">
							上一次登录ip
						</th>
						<th width="10%">
							状态
						</th>
						<th width="10%">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.pageBean.list" id="user">
					<tr>
						<td>
							${user.uid}
						</td>
						<td>
							<s:if test="userType == 0">第三方账户</s:if>
							<s:if test="userType == 1">本地账户</s:if>
						</td>
						<td>
							${user.openId}
						</td>
						<td>
							${user.username}
						</td>
						<td>
							${user.name}
						</td>
						<td>
							<s:if test="userType == 1"><img alt="" style="max-width: 50px; max-height: 50px;" src="${ctx}${user.figureurl}" /></s:if>
							<s:else><img alt="" style="max-width: 50px; max-height: 50px;" src="${user.figureurl}" /></s:else>
						</td>
						<td>
							<s:date name="updateAt" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							${user.lastLoginIp}
						</td>
						<td>
							<s:if test="status == 0">正常</s:if>
							<s:if test="status == 1">禁言（不能发布）</s:if>
							<s:if test="status == 2">封号（不能登录）</s:if>
						</td>
						<td>
							<button class="btn btn-link" type="button" onclick="editInput('${user.uid }');" data-toggle="button" data-loading-text="<i class='icon-pencil'></i>&nbsp;编辑"><i class="icon-pencil"></i>&nbsp;编辑</button>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
			<jsp:include page="../include/ajax_pager.jsp" flush="true"></jsp:include>
</s:else>
