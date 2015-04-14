<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>遇见你, 听说你</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
	    <link rel="shortcut icon" href="${ctx }/images/favicon.ico" type="image/x-icon" />
		<link rel="icon" href="${ctx }/images/favicon.ico" type="image/x-icon" />
		<link type="text/css" href="${ctx}/style/bootstrap.min.css" rel="stylesheet">
	</head>
	<body >
	<div class="mainindex">
		<table>
			<tr>
				<td colspan="2">
					<h2>${record.content }</h2>
				</td>
			</tr>
			<tr>
				<td>
					<b style="font-style: normal; color: gray; font-size: 10px;"><s:date name="#request.record.createTime" format="yyyy-MM-dd HH:mm:ss"/></b>
				</td>
				<td align="right">
					<a href="http://121.43.150.13/QuestionServer/home.html"><b style="font-style: normal; color: blue; font-size: 10px;">遇见你, 听说你</b></a>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<audio src="${ctx}${record.voiceUrl }" controls="controls">
						Your browser does not support the audio tag.
					</audio>
				</td>
			</tr>
			<tr>
				<td>
				</td>
				<td align="right">
					<b style="font-style: normal; color: gray; font-size: 10px;">
						点赞：${record.praiseCount }
						分享：${record.shareCount }
					</b>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div>
						<table>
							<tr>
								<td>
										<img src="${ctx }/images/sy/ICON/72x72.png" alt="icon" /> 
								</td>
								<td>
									<h3 style="color: rgb(66,224,255);">遇见你  听说你</h3>
									<h4>在最美的文字里遇见你</h4>
								</td>
								<td>
									<button class="btn btn-primary" data-toggle="button" data-loading-text="下载" onclick="location.href='http://121.43.150.13/QuestionServer/home.html';">下载</button>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>