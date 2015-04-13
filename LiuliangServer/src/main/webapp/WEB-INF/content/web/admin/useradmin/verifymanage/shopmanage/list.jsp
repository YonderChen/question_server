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
							店铺旺旺
						</th>
						<th width="25%">
							店铺地址
						</th>
						<th width="40%">
							验证商品地址
						</th>
						<th width="10%">
							验证码
						</th>
						<th width="10%">
							状态
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.pageBean.list" id="shop">
					<tr>
						<td>
							${shop.bindName }
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
