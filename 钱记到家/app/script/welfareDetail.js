var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
var pro_Id = 0; //商品id

var memberId = sc.utils.getQueryString("sharememberId");
console.log(memberId);
if (!memberId) {
    memberId = 0;
}

$(function() {

	var proid = window.location.href.split("?")[1].split("=")[1];
	$(".sharebtn").click(function() {
		$(".fenxiang").fadeIn();
	})
	$(".fenxiang").click(function() {
		$(".fenxiang").hide();
	})

	//全局变量，动态的文章ID
	var ShareURL = "";
	//绑定所有分享按钮所在A标签的鼠标移入事件，从而获取动态ID
	$(function() {
		$(".bdsharebuttonbox a").mouseover(function() {
			ShareURL = $(this).attr("data-url") + proid;
		});
	});

	function SetShareUrl(cmd, config) {
		if(ShareURL) {
			config.bdUrl = ShareURL;
		}
		return config;
	}
	//插件的配置部分，注意要记得设置onBeforeClick事件，主要用于获取动态的文章ID
	window._bd_share_config = {
		"common": {
			onBeforeClick: SetShareUrl,
			"bdSnsKey": {},
			"bdText": "",
			"bdMini": "2",
			"bdMiniList": false,
			"bdPic": "http://www.datouwang.com/uploads/pic/jiaoben/2017/jiaoben826_s.jpg",
			"bdStyle": "0",
			"bdSize": "24"
		},
		"share": {}
	};
	//插件的JS加载部分
	with(document) 0[(getElementsByTagName('head')[0] || body)
		.appendChild(createElement('script'))
		.src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' +
		~(-new Date() / 36e5)];

	$('.shadebg,.shade .btn-close').click(function() {
		var that = $(this).parents('.shade');
		that.fadeOut();
		that.find('.shade-item').animate({
			bottom: "-100%"
		});
	});

	//正式开始
	pro_Id = sc.utils.getQueryString("id");
	productInfo(pro_Id);
	//进入评论列表页
	$('#gotoPraiseList').click(function() {
		location.href = '/Praiselist.html?id=' + pro_Id + '';
	});
	//显示购物车的数量
	if(sc.auth.isLogin()) {
		cartNum();
	} else {
		$('#gotoCart .cartNum').hide();
	}

	//点击进入购物车的时候
	$('#gotoCart').click(function() {
		if(sc.auth.isLogin()) {
			location.href = '/cart.html?shopId=' + shopId;
		} else {
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

	$(".zhezhao").click(function() {
		$(".coupTan").css("display", "none");
	})



});

var shopId;
//详情轮播
function ddBanner() {
	var swiper = new Swiper('.swiper-container', {
		pagination: {
			el: '.swiper-pagination',
			type: 'fraction',
		},
	});
}
//根据产品id获取产品详情
function productInfo(proId) {
	var url = 'api/Gift/GetInfo';
	var callback = function(data) {
		if(data.code == 0) {
			shopId = data.data.ShopId;
			sc.utils.getQueryString("shopId");
			getWxfxConfig(0, data.data.Title, data.data.GiftSynopsis, data.data.PicNo.split(',')[0]);
			//渲染详情头部
			var arrText = doT.template($("#productInfo").text());
			$("#productInfoBox").html(arrText(data.data));
			ddBanner();

			$('.pro-youhui .scoreTips').hide();

			//			$('#scoreNum')
			$('.pro-youhui .scoreTips').show();

			//是否显示优惠

			$('.pro-youhui .fuli').hide();

			$('.pro-youhui .fuli').show();

			$('#scoreNum').text(data.data.Score);
			//sku中的数量框中的最少购买量
			if(data.data.MinBuyNum) {
				$('#pro_num').val(data.data.MinBuyNum);
			} else {
				$('#pro_num').val(1);
			}
			//联系客服
			if(data.data.ShopQQ){
				$(".btn-Service").attr("href", "http://wpa.qq.com/msgrd?v=3&uin=" + data.data.ShopQQ + "&site=qq&menu=yes"); 
			}else{
				$(".btn-Service").attr("href", "http://wpa.qq.com/msgrd?v=3&uin=506234937&site=qq&menu=yes");
			}	
			//渲染商品详情中的图文详情
			if(data.data.GiftContentDetail) {
				$('.pro_Detail .dd-Bd').html(data.data.GiftContentDetail);
			} else {
				$('.pro_Detail .dd-Bd').html('<p style="text-align:center;font-size:.15rem;color:#505050;padding:.4rem .15rem;">暂无数据</p>');
			}

			//弹出的sku选择渲染
			$('.productInfo .name').html(data.data.Title); //商品名称
			$('#stock').html(data.data.Stock); //商品库存
			$('#new_price').html(data.data.Price); //售价
			$('#old_price').html(data.data.MarketPrice); //市场价
			var picList = data.data.PicNo.split(',');
			$('.specifications_shade .m-pro img').attr('src', picList[0]);

			//			弹出skuz选择
			$('#gotoSmOrder').click(function() { //显示sku弹窗
				if(data.data.Stock==0){
					layer.open({
						content: '库存不足，暂时无法购买!',
						skin: 'msg',
						time: 2 //2秒后自动关闭
					});
				}else{
					$('.shade').fadeIn();
					$('.specifications_shade').animate({
						bottom: "0"
					});
				}
            });

            // 新增
            $('.add-Cart').click(function () { //显示sku弹窗  加入购物车弹出的sku
                if (data.data.Stock == "0") {
                    layer.open({
                        content: '库存不足！',
                        skin: 'msg',
                        time: 2 //2秒后自动关闭
                    });
                } else {
                    $("#sureGotoSmOrder").data("cart", 1);
                    $('#orderBuy').hide();
                    $('#addCart').show();
                    $('.shade').fadeIn();
                    $('.specifications_shade').animate({
                        bottom: "0"
                    });
                }
            });



			//数量增加
			$('.selnum .more').click(function() {
				numMore($('.selnum input'), '', data.data.MaxBuyNum, data.data.Stock);
			});
			//数量减少
			$('.selnum .less').click(function() {
				numLess($('.selnum input'), data.data.MinBuyNum);
			});

			//加入购物车，先判断是否登录
			$('#sureGotoSmOrder').click(function() {
				var that = $(this).parents('.shade');
				if(sc.auth.isLogin()) { //如果已经登录的
					if(ValSelectedSku()) {
						that.fadeOut();
						that.find('.shade-item').animate({
							bottom: "-100%"
						});
                        var goodsNum = $("#pro_num").val();
                        window.location.href = "./flOrder.html?proId=" + proId + "&goodsNum=" + goodsNum;

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
			$("#youhuiquan").click(function() {
				if(data.data.couponlist.length > 0) {
					$(".coupTan").css("display", "block")
					var couponLtpl = document.getElementById('couponLtpl').innerHTML;
					laytpl(couponLtpl).render(data, function(html) {
						//得到的模板渲染到html
						document.getElementById('couponList1').innerHTML = html;
						$(".item").click(function() {
							var couponid = $(this).attr("data-id");
							ReceiveCoupon2(couponid,0);
						});
					});
				} else {
					layer.open({
						content: '暂无优惠券!',
						skin: 'msg',
						time: 2 //2秒后自动关闭
					});
				}

			});

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
		"Id": proId,
		"UserId": userId,
		"Token": token
	}, callback);
}


function ReceiveCoupon2(couponId, CounponDetailId) {
   
	if(sc.auth.isLogin()) {
	    var url = "api/Coupon/ReceiveCoupon";
	    if (!CounponDetailId)
	        CounponDetailId = 0;
	var callback = function(data) {
		console.log(data)
		if(data.code === 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code === 2) {
			layer.open({
				content: '亲，您已掉线，请重新登录!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code === 0) {
			layer.open({
				content: '领取成功!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		'UserId': userId,
		'Token': token,
		'CouponId': couponId,
		'CounponDetailId': CounponDetailId
	}, callback)
	} else {
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
	
}