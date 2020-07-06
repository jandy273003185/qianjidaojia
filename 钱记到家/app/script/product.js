var pageIndex = 1; //当前页
var size = 4;
var parentId;

//获取产品类型
function getProductType() {
	var url = 'api/Goods/GetProductType';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) {
			$('.allScreenLoading').hide();
			if(data.data.length) {
				var arrText = doT.template($("#classifyNavList").text());
				$("#classifyNavBox").html(arrText(data.data));
				parentId = $('#classifyNavBox .list li').eq(0).attr('data-id');
				goodsList(parentId, 0, pageIndex, size, '', '', $('.classifyTabShow').dropload());
				$('#classifyNavBox .list li').click(function() {
					parentId = $(this).attr('data-id');
					$('.classifyTabShow').dropload().unlock();
					pageIndex = 1;
					$(".productListBox").html("");
					$(this).addClass('active').siblings().removeClass('active');
					goodsList(parentId, 0, pageIndex, size, '', '', $('.classifyTabShow').dropload());
				});

			} else {
				var strHtml = '<div class="emptybox center" style="padding:.5rem .1rem;"><p class="tips">暂无数据 </p></div>';
				$("#classifyNavBox").append(strHtml);

			}

		} else if(data.code == 99) {
			layer.open({
				content: '服务器开了点小差，请重新刷新!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}

	}
	sc.get(url, {}, callback);
}

//获取产品的分类
function getProductClass(typeId, parentId) {
	var url = 'api/Goods/GetProductClass';
	var callback = function(data) { //parentId，-1为查询全部分类，0为顶级分类，大于0为查询分类的下级分类
		//console.log(data);
	}
	sc.post(url, {
		"typeId": typeId,
		"parentId": parentId
	}, callback);
}

//根据产品分类获取数据
function goodsList(Pid, sort, index, pageSize, hot, searchkey, el) {
	var url = 'api/Goods/GoodsList';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) {
			if(data.data.length > 0) {
				var arrText = doT.template($("#productList").text());
				$(".productListBox")[0].innerHTML += arrText(data.data);
				pageIndex++;
				console.log(pageIndex);
				// 如果没有数据
			} else {
				// 锁定
				el.lock();
				// 无数据
				//el.noData();
				layer.open({
					content: '没有数据了!',
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
			}
			el.resetload();
		} else if(data.code == 99) {
			layer.open({
				content: '服务器开了点小差，请重新刷新!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}

	}
	var error = function(data) {
		el.resetload();
	}
	sc.post(url, {
		"cid": Pid,
		"sortorder": sort,
		"page": index,
		"pageSize": pageSize,
		"hot": hot,
		"searchkey": searchkey
	}, callback, error);
}

$(function() {
	$('.classifyTabShow').dropload({
		scrollArea: $('.classifyTabShow'),
		loadDownFn: function(em) {
			//			console.log(em);
			//			console.log("___________");
			//			console.log($('.classifyTabShow').dropload());
			goodsList(parentId, 0, pageIndex, size, '', '', em);
		}
	});
});