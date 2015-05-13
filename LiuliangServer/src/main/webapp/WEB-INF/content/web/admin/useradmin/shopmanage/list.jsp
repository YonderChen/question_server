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
							平台账号
						</th>
						<th width="10%">
							店铺名称
						</th>
						<th width="10%">
							绑定平台
						</th>
						<th width="20%">
							店铺地址
						</th>
						<th width="25%">
							验证商品地址
						</th>
						<th width="10%">
							验证码
						</th>
						<th width="10%">
							状态
						</th>
						<th width="15%">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.pageBean.list" id="shop">
					<tr>
						<td>
							${shop.serverUser.username }
						</td>
						<td>
							${shop.bindName }
						</td>
						<td>
							<s:if test="bindPlat == 'taobao'">淘宝</s:if>
							<s:if test="bindPlat == 'tmall'">天猫</s:if>
							<s:if test="bindPlat == 'jd'">京东</s:if>
						</td>
						<td>
							${shop.shopUrl}
						</td>
						<td>
							${shop.verifyGoodsUrl}
						</td>
						<td>
							${shop.verifyCode}
						</td>
						<td id="status_${shop.shopId}">
							<s:if test="status == 0">待审核</s:if>
							<s:if test="status == 1">审核通过</s:if>
							<s:if test="status == 2">审核失败</s:if>
						</td>
						<td id="op_${shop.shopId}">
							<button class="btn btn-link" type="button" onclick="javascript:editInput('${shop.shopId }');" data-toggle="button" data-loading-text="<i class='icon-pencil'></i>&nbsp;编辑"><i class="icon-pencil"></i>&nbsp;编辑</button>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
			<jsp:include page="../../include/ajax_pager.jsp" flush="true"></jsp:include>
</s:else>
