var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
$(function() {
	//正式开始
	var pro_Id = sc.utils.getQueryString("id");
	productInfo(pro_Id);

	$('.shadebg,.shade .btn-close').click(function() {
		var that = $(this).parents('.shade');
		that.fadeOut();
		that.find('.shade-item').animate({
			bottom: "-100%"
		});
	});

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

});
var shopId;
var starTimetype;//1表示未开始，2表示已开始
//倒计时
function countDown(time, day_elem, hour_elem, minute_elem, second_elem) {
	//if(typeof end_time == "string")
	var end_time = new Date(time).getTime(), //月份是实际月份-1
		//current_time = new Date().getTime(),
		sys_second = (end_time - new Date().getTime());
	var timer = setInterval(function() {
		if(sys_second > 0) {
			sys_second -= 10;
			var day = Math.floor((sys_second / 1000 / 3600) / 24);
			var hour = Math.floor((sys_second / 1000 / 3600) % 24);
			var minute = Math.floor((sys_second / 1000 / 60) % 60);
			var second = Math.floor(sys_second / 1000 % 60);
			//var haomiao = Math.floor(sys_second % 1000);
			$(day_elem).text(day + '天'); //计算天
			$(hour_elem).text(hour < 10 ? "0" + hour : hour); //计算小时
			$(minute_elem).text(minute < 10 ? "0" + minute : minute); //计算分
			$(second_elem).text(second < 10 ? "0" + second : second); // 计算秒						
			//$(uu_elem).text(haomiao); // 计算秒
			if(day == 0) {
				$(day_elem).hide();
			}
		} else {
			clearInterval(timer);
			$(hour_elem).text('00'); //计算小时
			$(minute_elem).text('00'); //计算分
			$(second_elem).text('00'); // 计算秒	
		}
	}, 10);
}
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
	var url = 'api/Goods/ProductInfo';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) {
			shopId = data.data.ShopId;
			sc.utils.getQueryString("shopId");
			var sharetitle='限时抢购：'+data.data.ProductName;
			getWxfxConfig(0,sharetitle,data.data.Synopsis,data.data.ProductImgList[0].PicUrl);
			//渲染详情banner
			var arrText = doT.template($("#productBanner_tpl").text());
			$("#productBanner_div").html(arrText(data.data));
			ddBanner();
			//渲染详情产品信息
			//秒杀
			var p = $('.pro_info .activity.pin');
			$('.pro_info .activity.pin').show();
			p.find('.price').text(data.data.TimePrice);
			p.find('.counterPrice').text('￥' + data.data.ProductPrice);
			p.find('.people').text('限时秒杀');
			var dateBegin = new Date(data.data.FlashSaleStartTime.replace(/T/g, " ")); //将-转化为/，使用new Date
			var dateNow = new Date(); //获取当前时间
			var beginDiff = dateNow.getTime() - dateBegin.getTime(); //时间差的毫秒数       
			var beginDayDiff = Math.floor(beginDiff / (24 * 3600 * 1000)); //计算出相差天数  
			if(beginDayDiff < 0) { //没到开始时间
				p.find('.lab').text('距离开始还有');
				starTimetype=1;
				var starTime =sc.utils.dateFormat(sc.utils.toDate(data.data.FlashSaleStartTime), "yyyy/MM/dd hh:mm:ss"); 			
				countDown(starTime, '.Countdown .dd', '.Countdown .hh', '.Countdown .mm', '.Countdown .ss');
				$('.dd_footer .btn2').addClass('disable');
			}else{
				p.find('.lab').text('距离结束还剩');
				starTimetype=2;
				var limitTime =sc.utils.dateFormat(sc.utils.toDate(data.data.FlashSaleEndTime), "yyyy/MM/dd hh:mm:ss"); 			
				countDown(limitTime, '.Countdown .dd', '.Countdown .hh', '.Countdown .mm', '.Countdown .ss');
				$('.dd_footer .btn2').removeClass('disable');
			}
			//	通用产品信息
			var strhtml1 = "";
			strhtml1 += '<h4 class="name">' + data.data.ProductName + '</h4>';
			strhtml1 += '<p class="desc">' + data.data.Synopsis + '</p>';
			strhtml1 += '<div class="pro-bd_ft clear">';
			if(data.data.Postage == 0) {
				strhtml1 += '<span class="Postage fl">快递：免运费</span>';
			} else {
				strhtml1 += '<span class="Postage fl">快递：' + data.data.Postage + '元</span>';
			}
			strhtml1 += '<span class="sale_ed fr">已售' + data.data.SalesVolume + '份</span>';
			strhtml1 += '</div>';
			$('.infoItem.pro-bd').append(strhtml1);
			if(data.data.Score > 0) {
				$('.infoItem.pro-youhui .txt_r').append('<p>购买可得积分<span class="red">' + data.data.Score + '</span>积分</p>');
			}

			$('#aprriseNum').html(data.data.EvaluateCount); //商品总评论数
			//sku中的数量框中的最少购买量
			if(data.data.MinBuyNum) {
				$('#pro_num').val(data.data.MinBuyNum);
			} else {
				$('#pro_num').val(1);
			}
			//店铺
			$('.pro-Store .store_logo img').attr("src", data.data.ShopLogo); //店铺logo
			$('.pro-Store .storeinfo .name').text(data.data.ShopName); //店铺名称

			//渲染产品评论
			var interText = doT.template($("#appriseTemp").text());
			$("#appriseList").html(interText(data.data.EvaluateList));
			
			$('.thumbnails').each(function() {
				$(this).find('img').fsgallery();
			});
			//店铺
			if(data.data.IsCollectionShop) {
				$("#isCollectionShop").addClass("active");
				$("#isCollectionShop").text("已关注");
			}
			
			$(".shopTopleft img").attr("src", data.data.ShopLogo);
			$(".shopTopleft span").text(data.data.ShopName);
			$('#proSocre').text(data.data.ProductScore + (data.data.ProductScore > 4.5 ? "高" : "低")); //商品（宝贝）评分
			$('#serviceScore').text(data.data.ServiceScore + (data.data.ServiceScore > 4.5 ? "高" : "低")); //服务评分
			$('#logisticsScore').text(data.data.LogisticsScore + (data.data.LogisticsScore > 4.5 ? "高" : "低")); //服务评分
			$(".lookstore").click(function() {
				window.location.href = "/member/storeIndex.html?shopId=" + data.data.ShopId;
			});
			//收藏店铺
			$("#isCollectionShop").click(function() {
				if(sc.auth.isLogin()) {
					if($(this).hasClass("active")) {
						//取消关注店铺
						shopCollection(data.data.ShopId, 0, $(this));
					} else {
						//关注店铺
						shopCollection(data.data.ShopId, 1, $(this));
					}
				} else {
					layer.open({
						content: '您还没有登录，是否要登录？',
						btn: ['确定', '取消'],
						yes: function(index) {
							window.location.href = "/login.html?returnUrl=" + sc.utils.getCurrentPathname();
							layer.close(index);
						},
						no: function(index) {
							layer.close(index);
						}
					});
				}
			});

			//渲染商品详情中的图文详情
			if(data.data.ContentDetail) {
				$('.pro_Detail .dd-Bd').html(data.data.ContentDetail);
			} else {
				$('.pro_Detail .dd-Bd').html('<p style="text-align:center;font-size:.15rem;color:#505050;padding:.4rem .15rem;">暂无数据</p>');
			}
			//联系客服
			if(data.data.ShopQQ){
				$(".btn-Service").attr("href", "http://wpa.qq.com/msgrd?v=3&uin=" + data.data.ShopQQ + "&site=qq&menu=yes"); 
			}else{
				$(".btn-Service").attr("href", "http://wpa.qq.com/msgrd?v=3&uin=506234937&site=qq&menu=yes");
			}	
			//弹出的sku选择渲染
			romanceSku(data.data.SpecificationValue, data.data.ProductSpecList);
			$('.productInfo .name').html(data.data.ProductName); //商品名称
			$('#stock').html(data.data.Stock); //商品库存
			$('#new_price').html(data.data.TimePrice); //秒杀价		
			$('#old_price').html(data.data.ProductPrice); //原价
			$('.productInfo .hd .img img').attr("src",data.data.ProductImgList[0].PicUrl);

			//			弹出skuz选择
			$('.add-Cart').click(function() { //显示sku弹窗  加入购物车弹出的sku
				if(starTimetype==2){
					if(data.data.Stock=="0"){
						layer.open({
							content: '库存不足！',
							skin: 'msg',
							time: 2 //2秒后自动关闭
						});
					}else{
						$('#orderBuy').hide();
						$('#addCart').show();
						$('.shade').fadeIn();
						$('.specifications_shade').animate({
							bottom: "0"
						});
					}
				}
				if(starTimetype==1){
					layer.open({
						content: '还没有到时间哦！',
						skin: 'msg',
						time: 2 //2秒后自动关闭
					});
				}
			});
			$('.btn-buy').click(function() { //显示sku弹窗  立即购买弹出的sku
				if(starTimetype==2){
					if(data.data.Stock=="0"){
					layer.open({
						content: '库存不足！',
						skin: 'msg',
						time: 2 //2秒后自动关闭
					});
					}else{//秒杀未开始
						$('#orderBuy').show();
						$('#addCart').hide();
						$('.shade').fadeIn();
						$('.specifications_shade').animate({
							bottom: "0"
						});
					}
				}
				if(starTimetype==1){
					layer.open({
						content: '还没有到时间哦！',
						skin: 'msg',
						time: 2 //2秒后自动关闭
					});
				}
				
			});

			//数量增加
			$('.selnum .more').click(function() {
				if(ValSelectedSku()) {
					numMore($('.selnum input'), data.data.ProductSpecList, data.data.MaxBuyNum, data.data.Stock);
				}

			});
			//数量减少
			$('.selnum .less').click(function() {
				numLess($('.selnum input'), data.data.MinBuyNum);
			});

			//立即购买
			$('#orderBuy').click(function() {
				var that = $(this).parents('.shade');
				if(sc.auth.isLogin()) { //如果已经登录的
					if(ValSelectedSku()) {
						var skuString = selectedSku().join("_");
						var count = Number($('#pro_num').val());
						that.fadeOut();
						that.find('.shade-item').animate({
							bottom: "-100%"
						});
						location.href = 'smOrder_limit.html?Pid=' + proId + '&num=' + count + '&spec=' + escape(skuString) + '&smType=0'
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

			//加入购物车，先判断是否登录
			$('#addCart').click(function() {
				var that = $(this).parents('.shade');
				if(sc.auth.isLogin()) { //如果已经登录的
					if(ValSelectedSku()) {
						var skuString = selectedSku().join("_");
						var count = Number($('#pro_num').val());
						addCart(proId, count, skuString);
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
			skuClickFun(data.data.SpecificationValue, data.data.ProductSpecList, 1);

			//店铺优惠券列表
			$('#youhuiquan').click(function() {
				var pageNum = 1,
					pageSize = 20;
				CouponCenter(0,"",shopId, pageNum, pageSize, function(data) {
					if(data.data.length > 0) {
						var couponLtpl = document.getElementById('couponLtpl2').innerHTML;
						laytpl(couponLtpl).render(data, function(html) {
							//得到的模板渲染到html
							document.getElementById('couponList2').innerHTML = html;
							$("#couponList2 .item").each(function() {
								$(this).click(function() {
									var CouponId = $(this).attr("data-id");
									ReceiveCoupon($(this), CouponId, 0);
								})
							})
						});
						$(".coupTan").css("display", "block");
					} else {
						layer.open({
							content: '该店铺暂无优惠券哦!',
							skin: 'msg',
							time: 2 //2秒后自动关闭
						});
					}
				}, function(data) {
					//错误
				})
			});

			$(".zhezhao").click(function() {
				$(".coupTan").css("display", "none")
			})

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
function addCart(proId, count, specText) {
	var url = 'api/Cart/AddCart';
	var callback = function(data) {
		if(data.code == 0) {
			//获取数据成功
			layer.open({
				content: '加入购物车成功！',
				skin: 'msg',
				time: 2
			});
			cartNum();

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
				location.href = "/login.html" + sc.utils.getCurrentPathname() + "";
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
		"SpecText": specText,
		"IsFlashSale": 1
	}, callback);
}

//店铺收藏
function shopCollection(shopid, type, obj) { //type=0;取消 type=1为关注
	var url = "api/Goods/ShopCollection";
	var calback = function(data) {
		if(data.code == 0) {
			if(type == 0) {
				obj.text("关注");
				obj.removeClass("active");
				layer.open({
					content: "取消关注店铺成功!",
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
			} else if(type == 1) {
				obj.text("已关注");
				obj.addClass("active");
				layer.open({
					content: "关注店铺成功!",
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
			}
			console.log("type:" + type);

		}

	};
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"shopId": shopid
	}, calback);
}