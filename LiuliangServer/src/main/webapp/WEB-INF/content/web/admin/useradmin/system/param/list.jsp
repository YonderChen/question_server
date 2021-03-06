<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:if test="#request.paramList.size == 0">
<div class='no-found'>无结果.</div>
</s:if>
<s:else>

                       <table width="95%" class="tablelist">
				<thead>
					<tr>
						<th width="15%">
							参数KEY
						</th>
						<th width="30%">
							参数名
						</th>
						<th width="20%">
							参数值
						</th>
						<th width="12%">
							创建时间
						</th>
						<th width="12%">
							最近修改时间
						</th>
						<th width="11%">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.paramList" id="sysParam">
					<tr>
						<td>
							${sysParam.paramId }
						</td>
						<td>
							${sysParam.name }
						</td>
						<td style="padding-top:10px;">
							<input name="value_${sysParam.paramId }" id="value_${sysParam.paramId }" type="text" class="span3" value="${sysParam.value }" placeholder="参数值(不能为空)" maxlength="90"/>
						</td>
						<td>
							<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<s:date name="modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<button class="btn btn-link" type="button" onclick="javascript:edit('${sysParam.paramId }');" data-toggle="button" data-loading-text="<i class='icon-ok'></i>&nbsp;保存"><i class="icon-ok"></i>&nbsp;保存</button>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
</s:else>
