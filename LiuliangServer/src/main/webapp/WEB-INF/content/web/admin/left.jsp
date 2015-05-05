<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title></title>
	<meta http-equiv="X-UA-Compatible"content="IE=10; IE=9; IE=8; IE=7; IE=EDGE">
	<link type="text/css" href="${ctx}/style/style.css" rel="stylesheet"/>
  	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript">
	$('document').ready(function() {
		//导航切换
		$(".menuson li").click(function(){
			$(".menuson li.active").removeClass("active")
			$(this).addClass("active");
		});
	
		$('.title').click(function(){
			var $ul = $(this).next('ul');
			$('dd').find('ul').slideUp();
			if($ul.is(':visible')){
				$(this).next('ul').slideUp();
			}else{
				$(this).next('ul').slideDown();
			}
		});
	});
	
	function changeTabfunction(url){
	$(".menuson li.active").removeClass("active");
	$(".menuson li").each(function(e){
	     var u=$(this).find("a").attr("href");
	     if(u == url){
	      $(this).addClass("active");
	     }
	     
	});
	
	
	}
	</script>
  </head>
  <body style="background:#f0f9fd;width:187px; height:500px; overflow-x:hidden;">
	<div class="lefttop"><span></span>功能目录</div>
    
    <dl class="leftmenu">
        
   ${menuHtml}
    
    </dl>
    

</body>
</html>