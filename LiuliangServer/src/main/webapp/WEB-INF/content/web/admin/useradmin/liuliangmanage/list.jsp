<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:if test="#request.pageBean.allRow == 0">
<div class='no-found'>无结果.</div>
</s:if>
<s:else>

                        <table width="95%" class="tablelist" style="table-layout:fixed; word-wrap:break-word;">
				<thead>
					<tr>
						<th width="15%">
							用户名
						</th>
						<th width="15%">
							店铺名称
						</th>
						<th width="15%">
							任务编号
						</th>
						<th width="15%">
							关键词
						</th>
						<th width="10%">
							计划流量
						</th>
						<th width="10%">
							完成流量
						</th>
						<th width="15%">
							日期
						</th>
						<th width="15%">
							完成情况
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.pageBean.list" id="liuliang">
					<tr>
						<td>
							${liuliang.llTask.serverUser.username }
						</td>
						<td>
							${liuliang.llTask.llShop.bindName }
						</td>
						<td>
							${liuliang.llTask.taskId }
						</td>
						<td>
							${liuliang.keyword }
						</td>
						<td>
							${liuliang.num }
						</td>
						<td>
							${liuliang.numCurrent }
						</td>
						<td>
							<s:date name="date" format="yyyy-MM-dd"/>
						</td>
						<td>
							<s:if test="doStatus == 0" >添加失败</s:if>
							<s:if test="doStatus == 1" >待处理</s:if>
							<s:if test="doStatus == 2" >处理中</s:if>
							<s:if test="doStatus == 3" >已完成</s:if>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
			<jsp:include page="../../include/ajax_pager.jsp" flush="true"></jsp:include>
</s:else>
