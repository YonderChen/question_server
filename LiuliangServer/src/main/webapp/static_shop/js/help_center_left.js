$(function(){
    $('.help_left dl dd a').click(function() {
        $(this).addClass('cur').parents().siblings().children().removeClass('cur').parents().parents().siblings().children().children().removeClass('cur');
    }); 
})  