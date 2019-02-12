$(function(){
    /*首页banner图轮播*/
    var swiper1 = new Swiper('.banner', {
        autoplay:6000,
        slidesPerView: 1,
        autoplayDisableOnInteraction:false,
        loop: true,
        effect:'fade'
    });
    //点击向下按钮
    $('.banner_wrap .down').on('click',function(){
      $('body,html').scrollTop($(window).height());
    })
    //集团分布
    carousel3D();
    //公司动态
    var swiper2 = new Swiper('.swiper-news', {
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        hashnav: true
    });
})
var carousel3D=function(){
    var cArr=[], $li=$(".field_box li"),ln=$li.length;
    for(i=0;i<ln;i++){
        cArr.push('p1');
    }
    cArr[0]='p4';
    cArr[1]='p3';
    cArr[2]='p2';
   $li.each(function(i,e){
        $(e).removeClass().addClass(cArr[i]);
    })
   $('.field_box .title').text($('.field_box .p3').attr('data-t'));
    $(".field_box .next").click(
        function(){
        nextimg();
        }
    )
    $(".field_box .prev").click(
        function(){
        previmg();
        }
    )   
    //点击class为p2的元素触发上一张照片的函数
    $(document).on("click",".field_box .p2",function(){
        previmg();
        return false;//返回一个false值，让a标签不跳转
    });
    //点击class为p4的元素触发下一张照片的函数
    $(document).on("click",".field_box .p4",function(){
        nextimg();
        return false;
    });
    //上一张
    function previmg(){
        cArr.unshift(cArr[ln-1]);
        cArr.pop();
        //i是元素的索引，从0开始
        //e为当前处理的元素
        //each循环，当前处理的元素移除所有的class，然后添加数组索引i的class
        $li.each(function(i,e){
            $(e).removeClass().addClass(cArr[i]);
        }) 
        show();
    }

    //下一张
    function nextimg(){
        cArr.push(cArr[0]);
        cArr.shift();
        $li.each(function(i,e){
            $(e).removeClass().addClass(cArr[i]);
        })
        show();
    }
    
    function show(){
        $('.field_box .title').text($('.field_box .p3').attr('data-t'));
    }
}

    
    