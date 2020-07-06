var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
var productSpecList=[];
var maxBuyNum=0;
var minBuyNum=0;
var allstock=0;
$(function() {
	$('.shade .btn-close').click(function() {
		$(this).parents('.shade').fadeOut();
		$(this).parents('.shade').find('.shade-item').animate({
			bottom: "-100%"
		});
	});

	$('.sectionBd').on('click', '.buybtn', function(event) {
		console.log("打印中");
		event.preventDefault();
		var productId = $(this).parents('.m-pro').attr('data-id');
		console.log("id:" + productId);
		if(!sc.auth.isLogin()) {
			layer.open({
				content: '您还没有登录，是否登录?',
				btn: ['确定', '取消'],
				yes: function(index) {
					location.href = '/login.html';
					layer.close(index);
				},
				no: function(index) {
					layer.close(index);
				}
			});
		} else {
			//弹出sku选择框
			$('.sku__shade').show();
			$('.specifications_shade').animate({
				bottom: "0"
			});
			$('#pro_num').val(1);
			$('#skuBoxContent').html('');
			productInfo2(productId);
		}
	});

//数量增加
			$('.selnum .more').click(function() {
				if(ValSelectedSku()) {
					numMore($(this).siblings('.textWrap').find('input'), productSpecList, maxBuyNum, allstock);
				}
			});
			//数量减少
			$('.selnum .less').click(function() {
				numLess($('.selnum input'), minBuyNum);
			});
});

//产品详情
function productInfo2(proId) {
	console.log("产品id"+proId);
	var url = 'api/Goods/ProductInfo';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) {
			//sku中的数量框中的最少购买量
			if(data.data.MinBuyNum) {
				$('#pro_num').val(data.data.MinBuyNum);
			} else {
				$('#pro_num').val(1);
			}
			console.log("值：" + $('#pro_num').val());

			//弹出的sku选择渲染
			romanceSku(data.data.SpecificationValue, data.data.ProductSpecList);
			$('.productInfo .name').html(data.data.ProductName); //商品名称
			$('#stock').html(data.data.Stock); //商品库存			
			$('#new_price').html(data.data.ProductPrice); //售价			
			$('#old_price').html(data.data.MarketPrice); //市场价
			$('.specifications_shade .m-pro img').attr('src', data.data.ProductImgList[0].PicUrl);

productSpecList=data.data.ProductSpecList;
maxBuyNum=data.data.MaxBuyNum;
allstock= data.data.Stock;
minBuyNum=data.data.MinBuyNum;
//			//数量增加
//			$('.selnum .more').click(function() {
//				if(ValSelectedSku()) {
//					console.log("产品id2"+proId);
//					numMore($(this).siblings('.textWrap').find('input'), data.data.ProductSpecList, data.data.MaxBuyNum, data.data.Stock);
//				}
//			});
//			//数量减少
//			$('.selnum .less').click(function() {
//				numLess($('.selnum input'), data.data.MinBuyNum);
//			});
			//加入购物车，先判断是否登录
            $('#addCart').unbind('click');
            $('#addCart').click(function () {
              
				var that = $(this).parents('.shade');
				if(sc.auth.isLogin()) { //如果已经登录的
					if(ValSelectedSku()) {
						var skuString = selectedSku().join("_");
                        var count = Number($('#pro_num').val());


                        var url = 'api/Cart/AddCart';
                        var callback1 = function (data) {
                            if (data.code == 0) {
                                //获取数据成功
                                layer.open({
                                    content: '加入购物车成功！',
                                    skin: 'msg',
                                    time: 2
                                });

                            } else if (data.code == 1) {
                                layer.open({
                                    content: data.msg,
                                    skin: 'msg',
                                    time: 2
                                });

                            } else if (data.code == 2) {
                                layer.open({
                                    content: '亲，你已掉线，请重新登录！',
                                    skin: 'msg',
                                    time: 2
                                });
                                setTimeout(function () {
                                    location.href = "/login.html";
                                }, 2000);
                            } else if (data.code == 99) {
                                layer.open({
                                    content: data.msg,
                                    skin: 'msg',
                                    time: 2
                                });
                            }
                        }

                        sc.post(url, {
                            "UserId": userId,
                            "Token": token,
                            "ProId": proId,
                            "Count": count,
                            "SpecText": skuString
                        }, callback1);

						//addCart(proId, count, skuString);
						that.fadeOut();
						that.find('.shade-item').animate({
							bottom: "-100%"
						});
					} else {
						that.fadeIn();
						$('.specifications_shade').animate({
							bottom: "0"
						});
					}
				} else { //未登录的
					layer.open({
						content: '您还没有登录，是否要登录？',
						btn: ['确定', '取消'],
						yes: function(index) {
							window.location.href = "/login.html";
							layer.close(index);
						},
						no: function(index) {
							layer.close(index);

						}
					});
				}
			});
			skuClickFun(data.data.SpecificationValue, data.data.ProductSpecList);

		} else if(data.code == 1) {
			layer.open({
				content: '该产品已下架!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		} else if(data.code == 99) {
			layer.open({
				content: '服务器开了点小差，请重新刷新!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}

	}
	sc.post(url, {
		"proId": proId,
		"UserId": userId,
		"Token": token
	}, callback);
}
//加入购物车
function addCart(proId, count, specText, limitType) {
	var url = 'api/Cart/AddCart';
	var callback = function(data) {
		if(data.code == 0) {
			//获取数据成功
			layer.open({
				content: '加入购物车成功！',
				skin: 'msg',
				time: 2
			});

		} else if(data.code == 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2
			});

		} else if(data.code == 2) {
			layer.open({
				content: '亲，你已掉线，请重新登录！',
				skin: 'msg',
				time: 2
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 2000);
		} else if(data.code == 99) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2
			});
		}
	}

	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"ProId": proId,
		"Count": count,
		"SpecText": specText
	}, callback);

}