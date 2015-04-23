<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		
<div id="pager" class="pager">
</div>
		
<script type="text/javascript"> 
$(function(){
	buildPager();
})

function buildPager(){
	var totalPage = parseInt("${pageBean.totalPage}");
	var currentPage = parseInt("${pageBean.currentPage}");
	var pageSize = parseInt("${pageBean.pageSize}");
	if(totalPage > 1){
		var sb = new StringBuilder();
		if(currentPage > 1){
			sb.append("<a href='javascript:searchPage(" + (currentPage - 1) + ")'>上一页</a>&nbsp;");
		}
		for(var i=1;i<totalPage+1;i++){
			if(i==currentPage){
				sb.append("<strong>" + i + "</strong>&nbsp;");
			} else {
				sb.append("<a href='javascript:searchPage(" + i + ")'>" + i + "</a>&nbsp;");
			}
		}
		if(currentPage < totalPage){
			sb.append("<a href='javascript:searchPage(" + (currentPage + 1) + ")'>下一页</a>&nbsp;");
		}
		$("#pager").html(sb.toString());
	}
}
</script>