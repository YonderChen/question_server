<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:if test="#request.lltask == null">
<div class='no-found'>无结果.</div>
</s:if>
<div>
	<h4>基础信息</h4>
	<table width="100%" border="1" style="table-layout:fixed; word-wrap:break-word;">
	  <tr>
	    <td align="right" width="30%"><b>平台：</b></td>
	    <td>
			<s:if test="#request.lltask.llShop.bindPlat == 'taobao'">淘宝</s:if>
			<s:if test="#request.lltask.llShop.bindPlat == 'tmall'">天猫</s:if>
			<s:if test="#request.lltask.llShop.bindPlat == 'jd'">京东</s:if>
	    </td>
	  </tr>
	  <tr>
	     <td align="right"><b>店铺：</b></td>
	    <td>${lltask.llShop.bindName } </td>
	  </tr>
	  <tr>
	     <td align="right"><b>商品名：</b></td>
	    <td>${lltask.goodsName } </td>
	  </tr>
	  <tr>
	     <td align="right"><b>商品url：</b></td>
	    <td>${lltask.goodsUrl } </td>
	  </tr>
	  <tr>
	     <td align="right"><b>商品图片：</b></td>
	    <td><img style="height: 80px;width: 100px;" src="${ctx}${lltask.goodsImg}" /></td>
	  </tr>
	  <tr>
	     <td align="right"><b>关键词/每日订单量：</b></td>
	    <td>
	    	${lltask.keyword1}/${lltask.orderNumberOneDay1}
	    	<s:if test="#request.lltask.orderNumberOneDay2 > 0">
	    	, ${lltask.keyword2}/${lltask.orderNumberOneDay2}
	    	</s:if>
	    	<s:if test="#request.lltask.orderNumberOneDay3 > 0">
	    	, ${lltask.keyword3}/${lltask.orderNumberOneDay3}
	    	</s:if>
	    	<s:if test="#request.lltask.orderNumberOneDay4 > 0">
	    	, ${lltask.keyword4}/${lltask.orderNumberOneDay4}
	    	</s:if>
	    	<s:if test="#request.lltask.orderNumberOneDay5 > 0">
	    	, ${lltask.keyword5}/${lltask.orderNumberOneDay5}
	    	</s:if>
	    </td>
	  </tr>
	  <tr>
	     <td align="right"><b>流量来源分布占比：</b></td>
	    <td>${lltask.searchSource } </td>
	  </tr>
	  <tr>
	     <td align="right"><b>持续天数：</b></td>
	    <td>${lltask.durationDay } 天 </td>
	  </tr>
	  <tr>
	     <td align="right"><b>创建时间：</b></td>
	    <td>
			<s:date name="#request.lltask.createTime" format="yyyy-MM-dd HH:mm:ss"/>
		</td>
	  </tr>
	</table>
</div>
<div>
	<h4>增值服务</h4>
	<table width="100%" border="1" style="table-layout:fixed; word-wrap:break-word;">
	  <tr>
	    <td align="right" width="30%"><b>页面停留时间：</b></td>
	    <td>
	    	<s:if test="#request.lltask.pageStayType == 0">
	    		30~60秒
	    	</s:if>
	    	<s:if test="#request.lltask.pageStayType == 1">
	    		60~120秒
	    	</s:if>
	    	<s:if test="#request.lltask.pageStayType == 2">
	    		120~180秒
	    	</s:if>
	    </td>
	  </tr>
	  <tr>
	     <td align="right"><b>流量访问时间：</b></td>
	    <td>
	    	<s:if test="#request.lltask.visitTimeType == 0">
	    		全天平均分布
	    	</s:if>
	    	<s:if test="#request.lltask.visitTimeType == 1">
	    		随机分布
	    	</s:if>
	    	<s:if test="#request.lltask.visitTimeType == 2">
	    		网购用户习惯曲线分布
	    	</s:if>
		</td>
	  </tr>
	  <tr>
	     <td align="right"><b>优先审单：</b></td>
	    <td>
	    	<s:if test="#request.lltask.isQuickVerify == 0">
	    		普通
	    	</s:if>
	    	<s:else>
	    		优先
	    	</s:else>
		</td>
	  </tr>
	  <tr>
	     <td align="right"><b>流量优先执行：</b></td>
	    <td>
	    	<s:if test="#request.lltask.isQuickExecute == 0">
	    		普通
	    	</s:if>
	    	<s:else>
	    		优先
	    	</s:else>
		</td>
	  </tr>
	</table>
</div>
<div>
	<h4>费用统计</h4>
	<b>(总访客量 * 每个访客积分) + 增值服务 = ${lltask.costScore }积分</b>
</div>