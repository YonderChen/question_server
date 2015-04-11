<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
	</head>
	<body>
		<jsp:include page="../../../include/style.jsp" flush="true"></jsp:include>
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					账户管理
				</li>
				<li>
					积分列表
				</li>
			</ul>
		</div>

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
						<th width="25%">
							价格
						</th>
						<th width="40%">
							转账交易号
						</th>
						<th width="20%">
							状态
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.list" id="order">
					<tr>
						<td>
							${order.scoreNum }
						</td>
						<td>
							${order.price}
						</td>
						<td>
							${order.dealId}
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
</s:else>
</body></html>