var shop = sc.utils.getQueryString("id");
var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
//获取产品评论分页
var id = 0; //评价的id
var current = 1; //当前的页数
var pages; //总页数
var Size = 15; //每页有多少条数据

	getEvaluate(shop, id, current, Size);
	//滚动事件触发
	window.onscroll = function() {
		if(getScrollTop() + getClientHeight() == getScrollHeight()) {
			if(current < pages) {
				current++;
				getEvaluate(shop, id, current, Size);
			} else {
				layer.open({
					content: '没有数据了',
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
			}
		}
	}

$("#pingjia a").each(function() {
	$(this).click(function() {
		$(this).addClass("selected").siblings().removeClass("selected");
		id = $(this).attr("pjid");
		//每次点击的时候把当前页和总页数初始化
		pages = '';
		current = 1;
		//把内容选区清零
		$('#testDiv').html("");
		getEvaluate(shop, id, current, Size)
	})
});

function getEvaluate(shop, id, pageIndex, pageSize) {
	var url = "/api/Goods/GetEvaluate";
	var calback = function(data) {
		console.log(data);
		var stthtml = '';
		if(data.data.EvaluateList == ""){
			
			stthtml += '<div class="emptybox">'
			stthtml += '<div class="iconimg">'
			stthtml += '<img src="/images/icons/empty2.png"/>'
			stthtml += '</div>'
			stthtml += '<p class="tips"  style="text-align: center;color: #505050;">暂无评价 </p>'
			stthtml += '</div>'
			$('#testDiv').append(stthtml);
		}
		if(data.code == 0) {
			//current
			console.log("current：" + current);
			var nums = data.data.EvaluateCount; //总条数
			$('#pariseNumber').text(nums);
//			if(id == 0) {
//				$('#pariseNumber').text(nums);
//			}
			//判断页数
			if(parseInt(nums) % pageSize === 0) {
				pages = parseInt(nums) / pageSize
			} else {
				pages = Math.floor((parseInt(nums) / pageSize) + 1);
			}
			var gettpl = document.getElementById('testTpl').innerHTML;
			laytpl(gettpl).render(data, function(html) {
				//得到的模板渲染到html
				//document.getElementById('testDiv').innerHTML = html;
				$('#testDiv').append(html);
			});
//			$(".thumbnails").each(function(index) {
//				$(this).find("img").fsgallery();
//			})
			$('.thumbnails img').fsgallery();
			//展开全部文字，收起效果(超出100字隐藏)
			for (var i = 0; i < $('.item_praise .p_desc').length; i++) {
		            var m = $('.item_praise .p_desc').eq(i);
		            if (m.text().length > 100) {
		                m.attr("content", m.text());
		                m.html(m.text().substr(0, 100) + "...<a class=\"read_more\" href=\"javascript:;\">[查看全文]</a>");
		            }
		        }
	        $(".Praiselist").on("click", "a.read_more", function () {
	            $(this).parent().html($(this).parent().attr("content") + "<a class=\"yinchang\" href=\"javascript:;\">收起</a>");
	        });
	        $(".Praiselist").on("click","a.yinchang", function () {
	            $(this).parent().html($(this).parent().attr("content").substr(0, 100) + "...<a class=\"read_more\" href=\"javascript:;\">[查看全文]</a>");
	        });
		}

	}
    sc.post(url, {
        "userId": userId,
        "token": token,
		"proId": shop,
		"page": pageIndex,
		"pageSize": pageSize,
		"grade": id
	}, calback)
}