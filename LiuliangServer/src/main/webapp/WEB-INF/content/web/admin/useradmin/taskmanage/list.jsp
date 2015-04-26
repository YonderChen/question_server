<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:if test="#request.pageBean.allRow == 0">
<div class='no-found'>无结果.</div>
</s:if>
<s:else>

                        <table width="95%" class="tablelist" style="table-layout:fixed; word-wrap:break-word;">
				<thead>
					<tr>
						<th width="10%">
							用户名
						</th>
						<th width="10%">
							商品图片
						</th>
						<th width="5%">
							平台
						</th>
						<th width="15%">
							店铺
						</th>
						<th width="20%">
							商品
						</th>
						<th width="10%">
							总访客数
						</th>
						<th width="10%">
							日访客数
						</th>
						<th width="5%">
							总天数
						</th>
						<th width="5%">
							状态
						</th>
						<th width="10%">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.pageBean.list" id="task">
					<tr>
						<td>
							${task.serverUser.username }
						</td>
						<td>
							<img style="height: 80px;width: 100px;" src="${ctx}${task.goodsImg}" />
						</td>
						<td>
							<s:if test="llShop.bindPlat == 'taobao'">淘宝</s:if>
							<s:if test="llShop.bindPlat == 'tmall'">天猫</s:if>
							<s:if test="llShop.bindPlat == 'jd'">京东</s:if>
						</td>
						<td>
							${task.llShop.bindName}
						</td>
						<td>
							${task.goodsName}
						</td>
						<td>
							${(task.orderNumberOneDay1 + task.orderNumberOneDay2 
							+task.orderNumberOneDay3 + task.orderNumberOneDay4 
							+ task.orderNumberOneDay5) * task.durationDay}
						</td>
						<td>
							${task.orderNumberOneDay1 + task.orderNumberOneDay2 
							+task.orderNumberOneDay3 + task.orderNumberOneDay4 
							+ task.orderNumberOneDay5}
						</td>
						<td>
							${task.durationDay}
						</td>
						<td id="task_status_${task.taskId}">
							<s:if test="status == 0">未发布</s:if>
							<s:if test="status == 1">待审核</s:if>
							<s:if test="status == 2">执行中</s:if>
							<s:if test="status == 3">已完成</s:if>
							<s:if test="status == 4">已取消</s:if>
							<s:if test="status == 5">审核不通过</s:if>
							<s:if test="status == 6">任务修改，待审核</s:if>
						</td>
						<td>
							<a href="${ctx}/web/admin/useradmin/taskmanage/task_detail?taskId=${task.taskId}" class="btn btn-link" target="_blank">[详情]</a>
							<s:if test="status == 1">
								<a id="check_a_${task.taskId}" href="javascript:check_task('${task.taskId}')" class="btn btn-link">[审核]</a>
							</s:if>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
			<jsp:include page="../../include/ajax_pager.jsp" flush="true"></jsp:include>
</s:else>
