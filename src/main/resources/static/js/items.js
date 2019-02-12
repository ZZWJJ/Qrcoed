var lm=[],zx=[],num=0,num2=0;
$(function(){
	//核心业务下拉列表
	$(".items_o .lm .dsbf").click(function(){
		if ($(this).attr('data-open') === 'false') {
            $(this).attr('data-open', 'true');
            $(this).children(".ck").html("点击收起")
            $(this).children("img").attr("src","images/hx_s.png")
			$(this).siblings(".debf").slideDown()
        }
		else{
			$(this).attr('data-open', 'false');
			$(this).children(".ck").html("查看更多")
			$(this).children("img").attr("src","images/hx_xia.png")
			$(this).siblings(".debf").slideUp()
		}
	})
	
	//点击搜索按钮
	$(".btn_search").click(function(){
		var that=$(this),$search=$("input[name=search]");
		if($search.val() == ""){
			return false;
		}else{
			that.attr('disabled','true');
			$.ajax({
				cache: false,
				"type":"GET",
				"url":"http://g.sunaomei.com/core/search",
				"data":$('#itemsSearch').serialize(),
                "dataType": "jsonp",
                 jsonpCallback:"callback",
                "error": function(XMLHttpRequest, textStatus, errorThrown){
                       alert('提交失败');
                       that.removeAttr("disabled");
                },
                "success": function(data) {
                	console.log(data)
                    that.removeAttr("disabled");
                    $(".items_o").hide();
					$(".items_t").show();
					$(".items_t_yw .data").html("");
					$(".items_t_zx .data").html("");
					lm=[],zx=[],num=0,num2=0;
					$('.yw_more').show().html('<img src="images/hx_gd.png" alt="" /><span>更多业务详情</span>');
					 $('.zx_more').show().html('<img src="images/hx_gd.png" alt="" /><span>更多资讯详情</span>');
					lm=data["栏目"];
                    zx=data["文章"];
                    companyInfor.init();
                    companyInfor2.init();
                }
			});
		}
		return false;
	})
	//点击关闭按钮
	$(".items_t_gb").click(function(){
		$(".items_o").show();
		$(".items_t").hide();
	})
	$('.yw_more').on('click',function(){
		if(num>lm.length){
	       return false;
	    }
       companyInfor.loadMore();
    });
//  $('.zx_more').on('click',function(){
//  	if(num2>zx.length){
//         return false;
//      }
//     companyInfor2.loadMore();
//  });
    
})
	window.addEventListener("touchstart",function(e){
			//  	e.preventDefault();
    		starty = e.changedTouches[0].pageY;
    })
    window.addEventListener("touchmove",function(e){
	    	if ($(window).scrollTop() + $(window).height() == $(document).height()){
	//  	e.preventDefault();
	    	movey = e.changedTouches[0].pageY;
	    	x = movey - starty;
	    	if(x < 0){
	    		if(num2>zx.length){
		           return false;
		        }
		       companyInfor2.loadMore();
	    	}
	      }
	    })
//业务介绍
var companyInfor = {
	_default:2, //默认显示个数
    _loading:4,  //每次点击按钮后加载的个数
    init:function(){
		var len = lm.length;
		if(len == 0){
			$(".items_t_yw .data").html("<p style='line-height:30px;padding-left: 0.42rem;'>暂时没有业务介绍相关信息</p>");
			$('.yw_more').hide();
			return false;
		}else if(len >0 && len<=companyInfor._default){
			$('.yw_more').hide();
		}
    	for(var n = 0;n < companyInfor._default; n++){
    		if( n >= len.length){
    			break;
    		}
    		var str = '';
    		str+='<div class="ywlb clearfix"><a href="items_show.html/'+lm[n]['url2']+'/'+lm[n]['id']+'">';
    		str+='<div class="tu fl"><img src="http://g.sunaomei.com/'+lm[n]['bigpic']+'" alt=""></div>';
    		str+='<div class="wz fr">';
    		str+='<p>'+lm[n]['name']+'</p>';
    		str+='<span>'+limitSize(lm[n]['intro'],30)+'</span>';
    		str+='</div></a></div>';
    		$(".items_t_yw .data").append(str);
    	}
    	num=companyInfor._default;
    },
    loadMore:function(){
    	for(var i =num;i<companyInfor._loading+num;i++){
    	  if(i == lm.length-1){
    	  	$('.yw_more').html("<p style='color:#544865;font-size:0.24rem;'>！ 没有更多了</p>");
    	  }
          if( i >= lm.length){
          	$('.yw_more').html("<p style='color:#544865;font-size:0.24rem;'>！ 没有更多了</p>");
          	break;
          }
          var str="";
          str+='<div class="ywlb clearfix"><a href="items_show.html/'+lm[i]['url2']+'/'+lm[i]['id']+'">';
    		str+='<div class="tu fl"><img src="http://g.sunaomei.com/'+lm[i]['bigpic']+'" alt=""></div>';
    		str+='<div class="wz fr">';
    		str+='<p>'+lm[i]['name']+'</p>';
    		str+='<span>'+limitSize(lm[i]['intro'],30)+'</span>';
    		str+='</div></a></div>';
    		$(".items_t_yw .data").append(str);
        }
    	num+=companyInfor._loading;
    }
}
//业务咨训
var companyInfor2 = {
	_default:2, //默认显示个数
    _loading:4,  //每次点击按钮后加载的个数
    init:function(){
		var len = zx.length;
		if(len == 0){
			$(".items_t_zx .data").html("<p style='line-height:30px;padding-left: 0.42rem;'>暂时没有业务介绍相关信息</p>");
			$('.zx_more').hide();
			return false;
		}else if(len >0 && len<=companyInfor2._default){
			$('.zx_more').hide();
		}
    	for(var n = 0;n < companyInfor2._default; n++){
    		if( n >= zx.length){
    			break;
    		}
    		var str = '';
    		str+='<a class="zxlm" href="news_show.html">';
    		str+='<div class="zxlm_s clearfix"><span class="fl">'+limitSize(zx[n]['title'],15)+'</span>';
    		str+='<p class="fr">'+zx[n]['date']+'</p></div>';
    		str+='<div class="zxlm_x">'+limitSize(zx[n]['intro'],45)+'</div></a>';
    		$(".items_t_zx .data").append(str);
    	}
    	num2=companyInfor2._default;
    },
    loadMore:function(){
    	for(var i =num2;i<companyInfor2._loading+num2;i++){
    	  if( i == zx.length-1){
          	$('.zx_more').html("<p style='color:#544865;font-size:0.24rem;'>！ 没有更多了</p>");
          }
          if( i >= zx.length){
          	$('.zx_more').html("<p style='color:#544865;font-size:0.24rem;'>！ 没有更多了</p>");
          	break;
          }
          var str = '';
    		str+='<a class="zxlm" href="news_show.html">';
    		str+='<div class="zxlm_s clearfix"><span class="fl">'+limitSize(zx[i]['title'],15)+'</span>';
    		str+='<p class="fr">'+zx[i]['date']+'</p></div>';
    		str+='<div class="zxlm_x">'+limitSize(zx[i]['intro'],45)+'</div></a>';
    		$(".items_t_zx .data").append(str);
        }
    	num2+=companyInfor2._loading;
    }
}
function callback (result) {
    var data = JSON.stringify(result); //json对象转成字符串
 }
function limitSize(obj,nums){
    if(obj=='' || obj==null){
      return obj;
      return false;
    }
    var len = obj.length;
    if(len > nums){
       return obj.substring(0,nums)+'...';
    }
    return obj;
 }