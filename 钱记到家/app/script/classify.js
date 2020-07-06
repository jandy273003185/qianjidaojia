var pageIndex = 1; //当前页
var pageSize = 20;
var parentId;
var sort = 0;
var hot;
var searchkey;
var pages;
var loading = false;
var newProduct;
var typeId = sc.utils.getQueryString("typeid");//类型id
//获取产品类型
function getProductType() {
	var url = 'api/Goods/GetProductType';
	var callback = function(data) {
		console.log(data);
		$('.allScreenLoading').hide();
		if(data.code == 0) {
			if(data.data.length) {
				var arrText = doT.template($("#classifyNavList").text());
				$("#classifyNavBox").html(arrText(data.data));
				var navli=$('#classifyNavBox .list li');
				if(typeId){
					navli.each(function(){
						var atype = $(this).attr('data-id');
						if(atype==typeId){
							parentId=typeId;
							$(this).addClass('active').siblings().removeClass('active');
						}
					})								
				}else{
					navli.eq(0).addClass('active').siblings().removeClass('active');
					parentId = navli.eq(0).attr('data-id');				
				}	
				getProductClass(parentId, 0);
				navli.click(function() {
					parentId = $(this).attr('data-id');
					$(".productListBox").html("");
					$(this).addClass('active').siblings().removeClass('active');
					getProductClass(parentId, 0);

                });

            } else {
                var strHtml = '<div class="emptybox center" style="padding:.5rem .1rem;color:#999;"><p class="tips">暂无数据 </p></div>';
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
	var error = function(data) {
		$('.allScreenLoading').hide();
	}
	sc.get(url, {}, callback, error);
}

//获取产品的分类
function getProductClass(typeId, parentId) {
	var url = 'api/Goods/GetProductClass';
	var callback = function(data) { //parentId，-1为查询全部分类，0为顶级分类，大于0为查询分类的下级分类
		console.log(data);
		if(data.code == 0){
			if(data.data.length) {
				var arrText = doT.template($("#Subclassify").text());
				$(".classifylist").html(arrText(data.data));
            } else {
                //$(".classifylist").remove();
				var strHtml = '<div class="emptybox center" style="padding:.5rem .1rem;color:#999;"><p class="tips">暂无数据 </p></div>';
				$(".classifylist").html(strHtml);

			}
		}
	}
	sc.post(url, {
		"typeId": typeId,
		"parentId": parentId
	}, callback);
}

//根据产品分类获取数据
function goodsList() {
	var url = 'api/Goods/GoodsList';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) {
			if(data.data.length) {

				var arrText = doT.template($("#productList").text());
				$(".productListBox")[0].innerHTML += arrText(data.data);
				pageIndex++;
				loading = false;
				nums = data.count;
				//判断页数
				if(parseInt(nums) % pageSize === 0) {
					pages = parseInt(nums) / pageSize
				} else {
					pages = Math.floor((parseInt(nums) / pageSize) + 1);
				}
				if(pages == pageIndex - 1) {
					$('.productListBox').append("<p class='center' style='font-size:.14rem;padding:.2rem 0;color:#999;'>我也是有底线的</p>");
				}

			} else {
				loading = true;
				$('.productListBox').html("<p class='center' style='font-size:.14rem;padding:.5rem 0;color:#999;color:#999;'>暂无数据</p>");
			}
		} else if(data.code == 99) {
			loading = true;
			layer.open({
				content: '服务器开了点小差，请重新刷新!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}

	sc.post(url, {
		"cid": parentId,
		"sortorder": sort,
		"page": pageIndex,
		"pageSize": pageSize,
		"hot": hot,
		"searchkey": searchkey,
		"newProduct": newProduct
	}, callback);
}

$(function() {
	getProductType();	
});