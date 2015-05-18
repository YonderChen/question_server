function showImg(photo_url) {
	$("#showImg").find("img").attr("src", photo_url);  
	var oImg = document.getElementById("showImg_img"); 
	$('#showImg_img').removeAttr("width");
	$('#showImg_img').removeAttr("height");
	var oldWidth = oImg.width;
	if(oImg.width > 520){
		oImg.width = 520;
		oImg.height = oImg.width/oldWidth * oImg.height;
	}
	if(oImg.width > 0){
	 	$("#showImg").css("width", oImg.width);
	 	$("#showImg").css("height", oImg.height);
	} else {
	 	$("#showImg").css("width", 100);
	 	$("#showImg").css("height", 100);
	}
	$('#showImg').modal('show');
	//单击放大后的图片消失  
	$("#showImg").click(function(){  
		$('#showImg').modal('hide');
	});  
}
	
$(document).ready(function(){
   	var oImg = document.getElementById("showImg_img"); 
   	var showImg = document.getElementById("showImg"); 
	$('#showImg_img').bind('mousewheel', function(event, delta) {
        if (delta > 0) { 
        	if(oImg.width < 720){
		   		$("#showImg").css("width", oImg.width * 1.1);
		   		$("#showImg").css("height", oImg.height * 1.1);
	            oImg.width = oImg.width * 1.1; 
	            oImg.height = oImg.height * 1.1;
        	} 
        } 
        else { 
        	if(oImg.width > 120){
	            oImg.width = oImg.width / 1.1; 
	            oImg.height = oImg.height / 1.1; 
		   		$("#showImg").css("width", oImg.width);
		   		$("#showImg").css("height", oImg.height);
        	}
        } 
	    return false;
	});
	
	$("#showImg").keydown(function(event){
		if(event.keyCode == 38){
        	if(oImg.width < 720){
		   		$("#showImg").css("width", oImg.width * 1.1);
		   		$("#showImg").css("height", oImg.height * 1.1);
	            oImg.width = oImg.width * 1.1; 
	            oImg.height = oImg.height * 1.1; 
        	}
	   		return false;
		}
		else if(event.keyCode == 40){
        	if(oImg.width > 80 && oImg.height > 80){
	            oImg.width = oImg.width / 1.1; 
	            oImg.height = oImg.height / 1.1; 
		   		$("#showImg").css("width", oImg.width);
		   		$("#showImg").css("height", oImg.height);
        	}
	   		return false;
		}
	});
});