<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:if test="#request.pageBean.allRow == 0">
<div class='no-found'>无结果.</div>
</s:if>
<s:else>

                        <table width="95%" class="tablelist">
				<thead>
					<tr>
						<th width="5%">
							id
						</th>
						<th width="15%">
							作者uid
						</th>
						<th width="10%">
							昵称
						</th>
						<th width="10%">
							头像
						</th>
						<th width="15%">
							文字内容
						</th>
						<th width="15%">
							图片
						</th>
						<th width="10%">
							发表时间
						</th>
						<th width="5%">
							点赞数
						</th>
						<th width="5%">
							分享数
						</th>
						<th width="10%">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.pageBean.list" id="record">
					<tr>
						<td>
							${record.id}
						</td>
						<td>
							${record.owner.uid}
						</td>
						<td>
							${record.owner.name}
						</td>
						<td>
							<s:if test="owner.userType == 1"><img alt="" width="50px" height="50px" src="${ctx}${record.owner.figureurl}" /></s:if>
							<s:else><img alt="" width="50px" height="50px" src="${record.owner.figureurl}" /></s:else>
						</td>
						<td>
							${record.content}
						</td>
						<td>
							<img alt="" style="max-height: 150px; max-width: 150px;" src="${ctx}${record.imageUrl}" onclick="javascript:showImg('${ctx}${record.imageUrl}');" />
						</td>
						<td>
							<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							${record.praiseCount}
						</td>
						<td>
							${record.shareCount}
						</td>
						<td>
							<button class="btn btn-link" type="button" onclick="del(${record.id });" data-toggle="button" data-loading-text="<i class='icon-pencil'></i>&nbsp;删除"><i class="icon-pencil"></i>&nbsp;删除</button>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
			<jsp:include page="../include/ajax_pager.jsp" flush="true"></jsp:include>
</s:else>
