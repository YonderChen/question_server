<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:if test="#request.pageBean.allRow == 0">
<div class='no-found'>无结果.</div>
</s:if>
<s:else>

                        <table width="95%" class="tablelist">
				<thead>
					<tr>
						<th width="15%">
							商品图片
						</th>
						<th width="5%">
							平台
						</th>
						<th width="20%">
							店铺
						</th>
						<th width="25%">
							商品
						</th>
						<th width="8%">
							总访客数
						</th>
						<th width="8%">
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
						<td>
							<s:if test="status == 0">未发布</s:if>
							<s:if test="status == 1">待审核</s:if>
							<s:if test="status == -1">审核失败</s:if>
							<s:if test="status == 2">执行中</s:if>
							<s:if test="status == 3">执行完毕</s:if>
						</td>
						<td>
							<button class="btn btn-link" type="button" onclick="alert('详情');" data-toggle="button" data-loading-text="<i class='icon-pencil'></i>&nbsp;详情"><i class="icon-pencil"></i>&nbsp;详情</button>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
			<jsp:include page="../../include/ajax_pager.jsp" flush="true"></jsp:include>
</s:else>
