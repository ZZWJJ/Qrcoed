$(function(){
    var $slide=$('.col-carousel .swiper-slide');
    if($slide.length<=1){
        $('.col-carousel .swiper-pagination').remove();
    }
    var swiper3 = new Swiper('.col-carousel', {
        slidesPerView: 1,
        autoplayDisableOnInteraction:false,
        pagination : '.swiper-pagination'       
    });
    var swiper4 = new Swiper('.zxsp_swiper', {
        slidesPerView: 1,
        autoplayDisableOnInteraction:false,
        pagination : '.swiper-pagination'       
    });
    var swiper = new Swiper('.swiper-containerbg', {
        pagination: '.swiper-pagination',
        slidesPerView:1.2,
        paginationClickable: true,
        spaceBetween: 6,
        freeMode: true
    });
});
