//尺寸
(function (doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            var clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            docEl.style.fontSize =100*(clientWidth /750)+ 'px';
        };
    // Abort if browser does not support addEventListener
    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document,window);
var Wheight;
$(function(){
   document.addEventListener('touchstart',function(){},false);
   Wheight=$(window).height();
   $('.full').height(Wheight);
   //导航按钮
   $('.dh_close').on('click',function(e){
      $('.nav').toggleClass('nav-active');
   });
   $('.nav_cuo').on('click',function(e){
      $('.nav').removeClass('nav-active');
   });
   $(window).scroll(function(){
     var top=$(window).scrollTop();
     if(top>=Wheight){
       $('.header').addClass('bgBlack');
     }else{
       $('.header').removeClass('bgBlack');
     }
   })

   displayAll($('.n_s'),400);//新闻详情-显示按钮
   $('.join_part2_txt').each(function(){//人才招聘-显示按钮
      var obj1=$(this);
      displayAll(obj1,120);
   });
    $('.display_all .drop').on('click',function(){
      $(this).parent().hide();
      $(this).parent().prev().removeClass('limitH');
      $(this).parent().parent().removeClass('limitH');
    });
})
//内容页-是否显示全部按钮
function displayAll(obj,height){
   var h=obj.height();    
   if(h>=height){
       obj.parent().find('.display_all').show();
       obj.addClass('limitH');
    }else{
      obj.parent().find('.display_all').hide();
      obj.removeClass('limitH');
    }
}
