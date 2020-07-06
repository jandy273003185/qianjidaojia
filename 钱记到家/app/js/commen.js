//调用轮播
function Banner() {
    // 轮播图
    var swiper = new Swiper('.swiper-container-carousel', {
        autoplay: {
            delay: 2500,
            disableOnInteraction: false,
        },
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
    });
}
function scrollTxt() {
    // 消息滚动
    var swiper = new Swiper('.swiper-container-news', {
        direction: 'vertical',
        loop: true,
        autoplay: {
            delay: 2500,
            disableOnInteraction: false,
        },
    });
}
//tab切换
function tabList() {						
	$('.tabList ul li').click(function() {
		var that = $(this);
		var index = $(that).index();
		$(that).addClass('active').siblings().removeClass('active');
		$(that).parents('.Tabdiv').find('.tabCon > div').eq(index).fadeIn().siblings().hide();
	});
}
//随意拖动div，传进来的是$('#div');
function scDrag(obj) {
	var isdrag = true;
	var tempX, x, tempY, y;

	function dragStart(e) {
		isdrag = true;
		tempX = parseInt(obj.css("left") + 0);
		tempY = parseInt(obj.css("top") + 0);
		x = e.touches[0].pageX;
		y = e.touches[0].pageY;
	}

	function dragMove(e) {
		if(isdrag) {
			var curX = tempX + e.touches[0].pageX - x;
			var curY = tempY + e.touches[0].pageY - y;
			//边界判断
			curX = curX < 0 ? 0 : curX;
			curY = curY < 0 ? 0 : curY;
			curX = curX < document.documentElement.clientWidth - 72 ? curX : document.documentElement.clientWidth - 72;
			curY = curY < document.documentElement.clientHeight - 72 ? curY : document.documentElement.clientHeight - 72;
			obj.css({
				"left": curX,
				"top": curY
			});
			//禁止浏览器默认事件
			e.preventDefault();
		}
	}

	function dragEnd() {
		isdrag = false;
	}
	obj[0].addEventListener("touchstart", dragStart);
	obj[0].addEventListener("touchmove", dragMove);
    obj[0].addEventListener("touchend", dragEnd);
}

$(function () {
    //点击搜素的时候跳到搜素页面
    $('#gotoSearchProduct').click(function () {
        location.href = '/searchProduct.html'
    });
});
