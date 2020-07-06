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
			getWxfxConfig(0,data.data.ProductName,data.data.Synopsis,data.data.ProductImgList[0].PicUrl);
			//渲染详情banner
			var arrText = doT.template($("#productBanner_tpl").text());
			$("#productBanner_div").html(arrText(data.data));
			ddBanner();
			//渲染详情产品信息
			
					var p=$('.pro_info .activity.onebuy');
					$('.pro_info .activity.onebuy').show();
					p.find('.counterPrice').text('￥'+data.data.ProductPrice);
					if(data.data.MaxBuyNum!=0){
						p.find('.people').text('限购'+data.data.MaxBuyNum+'份');
					}else{
						p.find('.people').remove();
					}
					p.find('.guige').text('仅剩'+data.data.Stock+'份');
				
				//	通用产品信息
			var strhtml1="";
				strhtml1 +='<h4 class="name">'+data.data.ProductName+'</h4>';
				strhtml1 +='<p class="desc">'+data.data.Synopsis+'</p>';
				strhtml1 +='<div class="pro-bd_ft clear">';
				if(data.data.Postage==0){
					strhtml1 += '<span class="Postage fl">快递：免运费</span>';
				}else{
					strhtml1 += '<span class="Postage fl">快递：' + data.data.Postage + '元</span>';
				}
				strhtml1 += '<span class="sale_ed fr">已售' + data.data.SalesVolume + '份</span>';
				strhtml1 +='</div>';
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
			//联系客服
			if(data.data.ShopQQ){
				$(".btn-Service").attr("href", "http://wpa.qq.com/msgrd?v=3&uin=" + data.data.ShopQQ + "&site=qq&menu=yes"); 
			}else{
				$(".btn-Service").attr("href", "http://wpa.qq.com/msgrd?v=3&uin=506234937&site=qq&menu=yes");
			}	
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

			//点击立即购买
			$('#buynow').click(function(){
				location.href = "/oneBuyOrder.html?Pid="+proId+"&smType=0";
			})
		
		} else if(data.code == 1) {
			layer.open({
				content: data.msg,
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