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
							店铺旺旺
						</th>
						<th width="30%">
							店铺地址
						</th>
						<th width="20%">
							状态
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.list" id="shop">
					<tr>
						<td>
							${shop.shopOwnerAccount }
						</td>
						<td>
							${shop.shopUrl}
						</td>
						<td>
							${shop.status}
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
</s:else>
