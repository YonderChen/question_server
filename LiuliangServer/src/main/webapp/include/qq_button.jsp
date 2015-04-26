<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<style type="text/css">
* {
	padding: 0;
	margin: 0;
	font-family: Microsoft YaHei, Simsun;
}

.main {
	height: 102px;
	padding-top: 175px;
	background-repeat: no-repeat;
}

.bg {
	background-image:
		url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHkAAAEVCAMAAADzZbruAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MEFBMzNDREQ1QTMwMTFFMkE2QzVGM0E5RkRGREM0OTYiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MEFBMzNDREU1QTMwMTFFMkE2QzVGM0E5RkRGREM0OTYiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDowQUEzM0NEQjVBMzAxMUUyQTZDNUYzQTlGREZEQzQ5NiIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDowQUEzM0NEQzVBMzAxMUUyQTZDNUYzQTlGREZEQzQ5NiIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PrpsMxwAAAMAUExURfviVJxgYuHu9XjK8vhQOLrq/9L0/3eLl4XQ9ZdjJ/zONHC22I2LjiMgJu0yJAMEB14fHDM2SPibHdLk7eYpJczx//j8/Wh6g9Hl8O7y9c3j8ElbaMLt/6Hb9yIfKtvf4dpfSFl0hLjJ2dgnJv2mG+jn6DhLVpLV9oLP9bgiJJ+1zf63F8zh7BYWIdXV1lhjZ1prdnjB5dno8pzZ9szZ5UF0pZUzLWV0e4nS9dbo8vA2JCgnMsfIyqje+IWhwVJZXL3r/3/O9crg67XDzPr8/sqtr/L09rO2uLQYGKbW7v7GG9Pm8UxRVMYkJfH2+3GkvYPL7trj7Pq3JuH6/93f4Y3U9piqtOb8/5LZ9aWkps3e6d3r9FWArMLQ18Xu/6TG1nzN9GxKJmeMs4sZGo+bo77S3NWRHqG2zmaImXKGjxEfKeb2/LHh+N35/4Ssv6S0v8TT4P7eLN3q8e3z+JOhqv39/UBCWm6Aicnd6GlmaZPL5jU1PM/b5sHN1Hh2eYzS9dzr9Nrq87jk+bDZ7HaXupjE2JnR7ERHTKW3wPj5+a56IH7M9Ku7wqyTlqre92Btc33N8728v8WBIIq2y3WWpuvKyqseH4O71WySpNj2/y8TEejs8qy/1Kbd9/f6/XnL86h6fN4fJeDr8s+mIN6xHMPo+qWts67g+CI2RLykp2abtsEWGeT7/4fT8dvs9ojQ8jUrJ66/yajCzFBreNTg6u0tJIOUncvg65OtxXvM88nW333K8Mbh7Slmnd4vJc0kI2CElro7LoKhr5scHs7i5dbr74zW89fn8C4uO8Xc6JfX9js8TPv//565xOrw99vx9EPE9Pf9//g/LT0bIPn+//b299jv80tMY/v8/ePv9qrP347B2o1EPVxweh4dIglZlnfK8jzC9H3N9bjo/8Ds/8jv/4/Y9Pv7++bx9Es3KPPg4L7m+fDv78bn88vq9V9eYYIlI/r088Pr/PX6/PP5/Pj7/fL4/Pn8/vX5/PP4/PX6/ff7/fb6/fT5/P///////yIXGEsAAAEAdFJOU////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////wBT9wclAAAaWUlEQVR42szbC1wT15oAcK3uBYQ4PijYsokaIDzsQBFRa3gsloJUEdML3stKVwEVTb25VIqvcHELVHwVRV20Aop0t7jVVnrxWYtoubdSaOnval1ptZnq7l5/rN3VNqbYBOJ+58xMMjN58HAS74cEMknmn+8735w5Azji0aN0D7Fivf9gYj08M/3RoxGP1mtS40SKt/5tMPFWXFyqZv2jEemaOM9EkcI7czDhnZjoWadJH+GRKho8eDnRM9VjhEdc4m0Hwd3p7YFjWaL3bG6cOLFo0QnmlhOZ3mjXcXyZx1jV0NBQQXKCt8XuwTuTI3guioraH0Xf8GjvxNuJt7HMyxIr1u9ReOIItQYrWx+mty6L8uSmHPXaa695e3vDLW+7ZxTaex3IdbvpgD154mewDLrnCR+zN0OgrfiO5QmWDfAgvS0xKhS/gPlYhND3Eb/ohCdnO1R7926cMwPDvmZnhnSEhGRuxruHPYa8vygkM/NGesemTfB182ZojxAU9DNmWzfAo5mzPXdEeXLixKIoRAMctYi73TMKcXTOy5YtwxnPDumITldHIxwxIR37ifTojugDxJZVR9Nhq+L9Drh3NBo9A4UivaOjY//RGx0oQjJnh0aFcoXQ897pAKd7n/fky4fB48oIlsY1K+qk0WhH8C6imwOk0jqphkhtPKDe0hGSrj4grVNMjYuDpwDf8T5xQCoNIJjYnzk7KpQXWH4HZP7mqGV8OdRzFcELhfQGsTc1rrJRQ5SVpe5N3wL5Keo0lse3REs1AdIDq+C9wccNYn/I7ChPHhzl/eGHHz71oXfU+UQn8u7Q0MwOaeVU7ZrGykqck7Sy+fk1Ac0eAQr6jeyFTXVxWkXpl0RZY6UCygF39x6Fp8dVVlYieTMvucQo74nzJ70/aeJE76gdnM0WeXXcORS7Ez0zO+pS1fvWrGlsbo6TSqOle9XfaiR7Feu/I85+WZYKb0a6JUCj+LZ0H/HWGo16ixTekSZgb2ljY+Oaxsa3iBsg7+Yc4+ej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)
		;
}

.bg { *
	background-image:
		url(http://combo.b.qq.com/crm/wpa/release/3.3/wpa/views/b03.png)
}

.btn {
	position: absolute;
	display: block;
}

.btnText {
	top: 250px;
	left: 18px;
	display: block;
	width: 84px;
	height: 22px;
	font-size: 12px;
	font-weight: normal;
	color: #000;
	text-align: center;
	text-decoration: none;
	line-height: 22px;
}

.offlineBtnText {
	display: none;
}

.offline .onlineBtnText {
	display: none;
}

.offline .offlineBtnText {
	display: inline;
}

.closeBtn {
	top: 0;
	right: 6px;
	width: 39px;
	height: 26px;
}

.content .subTitle {
	height: 18px;
	font-size: 14px;
	font-weight: bold;
	color: #095996;
	line-height: 18px;
	text-align: center;
	overflow-y: hidden;
}

.content .plainText {
	height: 28px;
	margin-top: 14px;
	padding: 0 5px;
	font-size: 12px;
	color: #2f6b99;
	line-height: 14px;
	text-align: center;
	word-wrap: break-word;
	overflow-y: hidden;
}
</style>

<script type="text/javascript">
function colse_qq_web(){
	$("#qq_web_div").hide();
}
</script>           

	</head>
	<body>
		<div id="qq_web_div" class="main bg">
			<a id="launchBtn" class="btnText onlineBtnText btn" href="tencent://message/?uin=88888888&amp;Site=有事Q我&amp;Menu=yes">QQ交谈</a>
			<a id="closeBtn" class="closeBtn btn" href="javascript:colse_qq_web();"></a>
			<div class="content">
				<h2 id="subTitle" class="subTitle">
					点击即可发起会话
				</h2>
				<p id="plainText" class="plainText">
					时间：9:00-21:00
				</p>
			</div>
		</div>
	</body>
</html>