var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
$(function() {
	//		详情轮播
	var swiper = new Swiper('.swiper-container', {
		pagination: {
			el: '.swiper-pagination',
			type: 'fraction',
		},
	});
	//			弹出分享
	$('.sharebtn').click(function() {
		var str = '';
		str += '<div class="pop-share">';
		str += '<div class="pop-hd">分享给新朋友</div>';
		str += '<ul class="sharelist li20 clear">';
		str += '<li class="active"><i class="icon icon-weixin"></i><span>微信好友</span></li>';
		str += '<li><i class="icon icon-qq"></i><span>QQ好友</span></li>';
		str += '<li><i class="icon icon-weibo"></i><span>微博</span></li>';
		str += '<li><i class="icon icon-quan"></i><span>微信朋友圈</span></li>';
		str += '<li><i class="icon icon-qqroom"></i><span>QQ空间</span></li>';
		str += '</ul>';
		str += '<a href="javascript:;" class="btn cancelbtn">取消</a>';
		str += '</div>';
		layer.open({
			type: 1,
			content: str,
			anim: 'up',
			style: 'position:fixed; bottom:0; left:0; width: 100%;',
		});
		$('.cancelbtn').click(function() {
			layer.closeAll() //关闭所有层
		})
	})
	//弹出拼团规则
	$('.rulebtn').click(function() {
		var str = '';
		str += '<div class="pop-rule">';
		str += '<div class="pop-hd">活动规则</div>';
		str += '<div class="pop-bd text_l">';
		str += '<div class="rulelist">';
		str += '<p>1、拼团采用人数越多相对应价格递减。</p>';
		str += '<p>2、达到10人则结束拼团。</p>';
		str += '<p>3、团长（发起拼团的人）提前结束拼团，结束时的价格以当时成团人数计算时的价格为准。</p>';
		str += '<p>4、系统设定一个时间段，时间截止自动结束，人数不满设定人数时间结束也成功。</p>';
		str += '</div>';
		str += '<span class="line"></span>';
		str += '<div class="c_999">例如：甲发起拼团，该商品价格原价为100元，邀请了乙方，则价格为99元；邀请了丙方，则价格为98元，甲邀请了丁，则价格...，直到成团，系统自动计算增加人数后的价格。</div>';
		str += '</div>';
		str += '</div>';
		layer.open({
			content: str,
		});
	})

	//			查看更多拼团
	$('.Pinmore').click(function() {
		var str = '';
		str += '<div class="pop-pintuan">';
		str += '<span class="icon btn_close"></span>';
		str += '<div class="pop-hd">正在拼团</div>';
		str += '<div class="pop-bd text_l">';
		str += '<ul class="pinlist">';
		str += '<li>';
		str += '<div class="box clear">';
		str += '<div class="img fl"><img src="images/tx/tx_015.png"/></div>';
		str += '<div class="right fr">';
		str += '<a href="javascript:;" class="btn pinbtn">去拼团</a>';
		str += '</div>';
		str += '<div class="txtbox">';
		str += '<div class="name text">大参林东甲大参林东甲大参林东甲……</div>';
		str += '<p class="c_999">剩余<span class="time">20:28:26</span></p>	';
		str += '</div>';
		str += '</div>';
		str += '</li>';
		str += '</ul>';
		str += '</div>';
		str += '</div>';
		layer.open({
			content: str,
		});
		$('.btn_close').click(function() {
			layer.closeAll() //关闭所有层
		})
	})
	$('.shadebg,.shade .btn-close').click(function() {
		var that = $(this).parents('.shade');
		that.fadeOut();
		that.find('.shade-item').animate({
			bottom: "-100%"
		});
	});
	
	//正式开始
	var pro_Id = sc.utils.getQueryString("id");	
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
			location.href = '/cart.html?shopId='+shopId;
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

//计算折扣
function toPercent(num, total) { 
    return (Math.round(num / total * 1000) / 100.0);// 小数点后1位
}
//倒计时
function countDown(time, hour_elem, minute_elem, second_elem) {
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
			//day_elem && $(day_elem).text(day+'天');//计算天
			$(hour_elem).text(hour < 10 ? "0" + hour : hour); //计算小时
			$(minute_elem).text(minute < 10 ? "0" + minute : minute); //计算分
			$(second_elem).text(second < 10 ? "0" + second : second); // 计算秒						
			//$(uu_elem).text(haomiao); // 计算秒
		} else {
			clearInterval(timer);
			$(hour_elem).text('00'); //计算小时
			$(minute_elem).text('00'); //计算分
			$(second_elem).text('00'); // 计算秒	
		}
	}, 10);
}
//根据产品id获取产品详情
function productInfo(proId) {
	var url = 'api/Goods/ProductInfo';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) {
			shopId = data.data.ShopId;
			sc.utils.getQueryString("shopId");
			//渲染详情banner
			var arrText = doT.template($("#productBanner_tpl").text());
			$("#productBanner_div").html(arrText(data.data));
			//渲染详情产品信息
				//拼团
				if(isLimit){
					var p=$('.pro_info .activity.pin');
					$('.pro_info .activity.pin').show();
					p.find('.price').text(data.data.TimePrice);
					p.find('.counterPrice').text('￥'+data.data.ProductPrice);
					p.find('.people').text('10人团');
					var limitTime=sc.utils.dateFormat(sc.utils.toDate(data.data.FlashSaleEndTime), "yyyy/MM/dd hh:mm:ss");
					countDown(limitTime, '.Countdown .hh', '.Countdown .mm', '.Countdown .ss')
				}
				
				//	通用产品信息
			var strhtml1="";
				strhtml1 +='<h4 class="name">'+data.data.ProductName+'</h4>';
				strhtml1 +='<p class="desc">'+data.data.Synopsis+'</p>';
				
			$('.infoItem.pro-bd').append(strhtml1);
			if(data.data.Score>0){
				$('.infoItem.pro-youhui .txt_r').append('<p>购买可得积分<span class="red">'+data.data.Score+'</span>积分</p>');
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

			//弹出的sku选择渲染
			romanceSku(data.data.SpecificationValue, data.data.ProductSpecList);
			$('.productInfo .name').html(data.data.ProductName); //商品名称
			$('#stock').html(data.data.Stock); //商品库存
			
			$('#new_price').html(data.data.ProductPrice); //售价			
			$('#old_price').html(data.data.MarketPrice); //市场价
			
			//			弹出skuz选择
			$('.add-Cart').click(function() { //显示sku弹窗
				$('.shade').fadeIn();
				$('.specifications_shade').animate({
					bottom: "0"
				});
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
			skuClickFun(data.data.SpecificationValue, data.data.ProductSpecList);
			
			//店铺优惠券列表
			$('#youhuiquan').click(function() {
				CouponCenter();
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
				content: '数据异常,请重新登录!',
				skin: 'msg',
				time: 2
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 2000);
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
				content: '系统错误！',
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
		"IsFlashSale":isLimit
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