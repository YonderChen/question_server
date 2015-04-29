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
		
		<link href="${ctx}/jPlayer-2.9.2/dist/skin/blue.monday/css/jplayer.blue.monday.min.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/jPlayer-2.9.2/lib/jquery.min.js"></script>
		<script type="text/javascript" src="${ctx}/jPlayer-2.9.2/dist/jplayer/jquery.jplayer.min.js"></script>
		<script type="text/javascript">
//<![CDATA[
$(document).ready(function(){

	$("#jquery_jplayer_1").jPlayer({
		ready: function (event) {
			$(this).jPlayer("setMedia", {
				title: "遇见你  听你说",
				mp3: "${ctx}${record.voiceUrl }",
				m4a: "${ctx}${record.voiceUrl }",
				oga: "${ctx}${record.voiceUrl }"
			});
		},
		swfPath: "${ctx}/jPlayer-2.9.2/dist/jplayer",
		supplied: "mp3, m4a, oga",
		wmode: "window",
		useStateClassSkin: true,
		autoBlur: false,
		smoothPlayBar: true,
		keyEnabled: true,
		remainingDuration: true,
		toggleDuration: true
	});
});
//]]>
</script>
<style>
<!--
.jp-audio{width:270px}
.jp-audio .jp-controls{width:250px;padding:20px 10px 0}
.jp-audio .jp-type-single .jp-progress{left:110px;width:126px}
.jp-audio .jp-type-single .jp-time-holder{left:110px;width:126px}
-->
</style>
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
					<b style="font-style: normal; color: blue; font-size: 10px; margin-right: 10px;"><a href="http://121.43.150.13/QuestionServer/home.html">遇见你, 听你说</a></b>
				</td>
			</tr>
			<tr>
				<td colspan="2">
<div id="jquery_jplayer_1" class="jp-jplayer"></div>
<div id="jp_container_1" class="jp-audio" role="application" aria-label="media player">
	<div class="jp-type-single">
		<div class="jp-gui jp-interface">
			<div class="jp-controls">
				<button class="jp-play" role="button" tabindex="0">play</button>
				<button class="jp-stop" role="button" tabindex="0">stop</button>
			</div>
			<div class="jp-progress">
				<div class="jp-seek-bar">
					<div class="jp-play-bar"></div>
				</div>
			</div>
			<div class="jp-time-holder">
				<div class="jp-current-time" role="timer" aria-label="time">&nbsp;</div>
				<div class="jp-duration" role="timer" aria-label="duration">&nbsp;</div>
			</div>
		</div>
		<div class="jp-details">
			<div class="jp-title" aria-label="title">&nbsp;</div>
		</div>
		<div class="jp-no-solution">
			<span>Update Required</span>
			To play the media you will need to either update your browser to a recent version or update your <a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a>.
		</div>
	</div>
</div>
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
								<button class="btn btn-primary" data-toggle="button" data-loading-text="下载" onclick="location.href='http://121.43.150.13/QuestionServer/home.html';">下载</button>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>