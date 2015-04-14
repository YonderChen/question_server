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
							获得积分
						</th>
						<th width="15%">
							价格
						</th>
						<th width="30%">
							转账交易号
						</th>
						<th width="20%">
							创建时间
						</th>
						<th width="20%">
							状态
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.pageBean.list" id="order">
					<tr>
						<td>
							${order.num }
						</td>
						<td>
							${order.price}
						</td>
						<td>
							${order.dealId}
						</td>
						<td>
							<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<s:if test="status == 0">待审核</s:if>
							<s:else>
								<s:if test="status == 1">审核通过</s:if>
								<s:else>审核失败</s:else>
							</s:else>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
			<jsp:include page="../../../include/ajax_pager.jsp" flush="true"></jsp:include>
</s:else>
