<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>遇见你, 听你说</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
	    <link rel="shortcut icon" href="${ctx }/images/favicon.ico" type="image/x-icon" />
		<link rel="icon" href="${ctx }/images/favicon.ico" type="image/x-icon" />
		<link type="text/css" href="${ctx}/style/bootstrap.min.css" rel="stylesheet">
  		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript">
		var u = navigator.userAgent, app = navigator.appVersion;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		var isMicroMessenger = u.indexOf('MicroMessenger') > -1; //微信中打开
		function linkToAppDownloadPage(){
			if(isAndroid){
				location.href='http://a.app.qq.com/o/simple.jsp?pkgname=com.example.yujianni';
			}
			else{
				if(isMicroMessenger){
					alert("微信用户请在浏览器中打开该页面在点击下载。");
				}
				location.href='https://itunes.apple.com/us/app/yu-jian-ni/id983458853?l=zh&ls=1&mt=8';
			}
		}
	</script>
	</head>
	<body>
	<div style="margin-left: 10px; margin-right: 10px; min-width: 270px">
		<table width="100%">
			<tr>
				<td colspan="2">
					<h4>${record.content }</h4>
				</td>
			</tr>
			<tr>
				<td>
					<b style="font-style: normal; color: gray; font-size: 10px;"><s:date name="#request.record.createTime" format="yyyy-MM-dd HH:mm:ss"/></b>
				</td>
				<td align="right">
					<b style="font-style: normal; color: blue; font-size: 10px; margin-right: 10px;"><a href="javascript:linkToAppDownloadPage();">遇见你, 听你说</a></b>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<img src="${ctx}${record.imageUrl }" style="max-width:100%;" alt="图片" />
				</td>
			</tr>
			<tr>
				<td>
				</td>
				<td align="right">
					<b style="font-style: normal; color: gray; font-size: 10px; margin-right: 10px;">
						点赞：${record.praiseCount }
						分享：${record.shareCount }
					</b>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="100%">
						<tr>
							<td>
								<img width="72px" height="72px" src="${ctx }/images/sy/ICON/72x72.png" alt="icon" /> 
							</td>
							<td>
								<h4 style="color: rgb(66,224,255);">遇见你  听你说</h4>
								<h5>在最美的文字里遇见你</h5>
							</td>
							<td>
								<button class="btn btn-primary" data-toggle="button" data-loading-text="下载" onclick="javascript:linkToAppDownloadPage();">下载</button>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>