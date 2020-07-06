var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
var memberId = sc.utils.getQueryString("sharememberId");
var parms = sc.utils.getQueryString("parms");
var ActivityProductType = 0;
var pro_Id = sc.utils.getQueryString("id");
var spreadcode = sc.utils.getQueryString("spreadcode");//团长编号
var PreferentialId = sc.utils.getQueryString("PreferentialId");
var limitNum = 0;
var xq = sc.utils.getQueryString("xq");
if(!memberId) {
	memberId = 0;
}
if (!PreferentialId) {
    PreferentialId = 0;
}
$(function () {
    if (xq == "xq") {
        $(".dd_footer").hide();
        $(".pro-Store").hide();
        $("#youhuiquan").hide();
        $(".headtou").hide();
        $("#gotoPraiseList").hide();
        $(".icons_home").hide();
    }
	$('.shadebg,.shade .btn-close').click(function() {
		var that = $(this).parents('.shade');
		that.fadeOut();
		that.find('.shade-item').animate({
			bottom: "-100%"
		});
    });
	//正式开始
    productInfo(pro_Id,spreadcode);
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
                    if (ActivityProductType == 1) {
                        window.location.href = "/activityJump.html?activity=1&proId=" + pro_Id + "&action=login";
                    } else {
                        window.location.href = "/login.html";
                    }
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
    swiper.slideTo(1, 100, false);
}
//根据产品id获取产品详情
function productInfo(proId,spreadcode) {
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

            if (xq == "xq") {
                $(".icons_home").hide();
                $(".share_img").hide();
                $(".copy_img").hide();
            }

            //收益
            var arrText = doT.template($("#productGroup_tpl").text());
            $(".grouptuan").html(arrText(data.data));
			ddBanner();
			//渲染详情产品信息				
			//	通用产品信息
			
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
			//$('#proSocre').text(data.data.ProductScore + (data.data.ProductScore > 4.5 ? "高" : "低")); //商品（宝贝）评分
			//$('#serviceScore').text(data.data.ServiceScore + (data.data.ServiceScore > 4.5 ? "高" : "低")); //服务评分
			//$('#logisticsScore').text(data.data.LogisticsScore + (data.data.LogisticsScore > 4.5 ? "高" : "低")); //服务评分
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
			//联系客服
			if(data.data.ShopQQ){
				$(".btn-Service").attr("href", "http://wpa.qq.com/msgrd?v=3&uin=" + data.data.ShopQQ + "&site=qq&menu=yes"); 
			}else{
				$(".btn-Service").attr("href", "http://wpa.qq.com/msgrd?v=3&uin=506234937&site=qq&menu=yes");
			}			
			//渲染商品详情中的图文详情
			if(data.data.ContentDetail) {
				$('.pro_Detail .dd-Bd').html(data.data.ContentDetail);
			} else {
				$('.pro_Detail .dd-Bd').html('<p style="text-align:center;font-size:.15rem;color:#505050;padding:.4rem .15rem;">暂无数据</p>');
			}

			//弹出的sku选择渲染
            $('.productInfo .name').html(data.data.ProductName); //商品名称
            $('#new_price').html(data.data.ProductPrice); //售价			
            $('#old_price').html(data.data.MarketPrice); //市场价
            $('#stock').html(data.data.Stock); //商品库存	
          

            var strhtml1 = "";
        
            

            if (PreferentialId > 0) {
                //加载新人专享数据
                sc.post("api/Goods/getNewUserPreferentialPro", {
                    "ProId": proId,
                    "PreferentialId": PreferentialId,
                    "userId": userId,
                    "token": token
                }, function (Preferentialdata) {
                    if (Preferentialdata.code == "0") {
                        $('.productInfo .name').html(Preferentialdata.data.ProductTitle); //商品名称
                        $('#new_price').html(Preferentialdata.data.NewUserPrice.toFixed(2)); //售价		
                        $('.productInfo .price').html(Preferentialdata.data.NewUserPrice.toFixed(2)); //商品名称
                        limitNum = Preferentialdata.data.limitNum;
                        strhtml1 += '<h4 class="name">' + Preferentialdata.data.ProductTitle + '</h4>';
                        strhtml1 += '<p class="desc">' + data.data.Synopsis + '</p>';
                        strhtml1 += '新人专享价<p class="price">￥' + Preferentialdata.data.NewUserPrice.toFixed(2) + '</p>';
                        strhtml1 += '<div class="counterPrice">￥' + data.data.ProductPrice + '</div>';
                        strhtml1 += '</p>';
                        strhtml1 += '<div class="pro-bd_ft clear">';
                        if (Preferentialdata.data.freight == 0) {
                            strhtml1 += '<span class="Postage fl">快递：免运费</span>';
                        } else if (Preferentialdata.data.freight == 2) {
                            strhtml1 += '<span class="Postage fl">快递：到付</span>';
                        } else {
                            strhtml1 += '<span class="Postage fl">快递：' + Preferentialdata.data.freight + '元</span>';
                        }
                        if (Preferentialdata.data.SpecList != []) {
                   
                            NewUserPreferentialromanceSku(data.data.SpecificationValue, Preferentialdata.data.SpecList);
                            NewUserPreferentialskuClickFun(data.data.SpecificationValue, Preferentialdata.data.SpecList);
                        } else {
                            romanceSku(data.data.SpecificationValue, data.data.ProductSpecList);
                            skuClickFun(data.data.SpecificationValue, data.data.ProductSpecList);
                        }
                    } else {
                        strhtml1 += '<h4 class="name">' + data.data.ProductName + '</h4>';
                        strhtml1 += '<p class="desc">' + data.data.Synopsis + '</p>';
                        strhtml1 += '<p class="price">￥' + data.data.ProductPrice + '</p>';
                        if (data.data.DiscountNum && data.data.DiscountNum < 10) {
                            // strhtml1 += '<p class="Tags"><span class="rebate">' + data.data.DiscountNum + '折</span>';
                            strhtml1 += '<div class="counterPrice">￥' + data.data.MarketPrice + '</div>';
                            strhtml1 += '</p>';
                        }
                        strhtml1 += '<div class="pro-bd_ft clear">';
                        if (data.data.Postage == 0) {
                            strhtml1 += '<span class="Postage fl">快递：免运费</span>';
                        } else if (data.data.Postage == 2) {
                            strhtml1 += '<span class="Postage fl">快递：到付</span>';
                        } else {
                            strhtml1 += '<span class="Postage fl">快递：' + data.data.Postage + '元</span>';
                        }
                        romanceSku(data.data.SpecificationValue, data.data.ProductSpecList);
                        skuClickFun(data.data.SpecificationValue, data.data.ProductSpecList);
                    }

                    
                    strhtml1 += '<span class="sale_ed fr">已售' + data.data.SalesVolume + '份</span>';
                    strhtml1 += '</div>';
                    $('.infoItem.pro-bd').append(strhtml1);
                    $('#prohaibao .pro_info').append(strhtml1);
               });
                $(".btnGroup .add-Cart").hide();

            } else {
                strhtml1 += '<h4 class="name">' + data.data.ProductName + '</h4>';
                strhtml1 += '<p class="desc">' + data.data.Synopsis + '</p>';
                strhtml1 += '<p class="price">￥' + data.data.ProductPrice + '</p>';
                if (data.data.DiscountNum && data.data.DiscountNum < 10) {
                    // strhtml1 += '<p class="Tags"><span class="rebate">' + data.data.DiscountNum + '折</span>';
                    strhtml1 += '<div class="counterPrice">￥' + data.data.MarketPrice + '</div>';
                    strhtml1 += '</p>';
                }
                strhtml1 += '<div class="pro-bd_ft clear">';
                if (data.data.Postage == 0) {
                    strhtml1 += '<span class="Postage fl">快递：免运费</span>';
                } else if (data.data.Postage == 2) {
                    strhtml1 += '<span class="Postage fl">快递：到付</span>';
                } else {
                    strhtml1 += '<span class="Postage fl">快递：' + data.data.Postage + '元</span>';
                }
                strhtml1 += '<span class="sale_ed fr">已售' + data.data.SalesVolume + '份</span>';
                strhtml1 += '</div>';
                $('.infoItem.pro-bd').append(strhtml1);
                $('#prohaibao .pro_info').append(strhtml1); //海报赋值
                romanceSku(data.data.SpecificationValue, data.data.ProductSpecList);
                skuClickFun(data.data.SpecificationValue, data.data.ProductSpecList);
            }
           
			//商品图
            $('#prohaibao .proImg img').attr('src', data.data.ProductImgList[0].PicUrl);				
			$('.specifications_shade .m-pro img').attr('src', data.data.ProductImgList[0].PicUrl);

			//			弹出skuz选择
			$('.add-Cart').click(function() { //显示sku弹窗  加入购物车弹出的sku
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
            });

			$('.btn-buy').click(function() { //显示sku弹窗  立即购买弹出的sku
				if(data.data.Stock=="0"){
					layer.open({
						content: '库存不足！',
						skin: 'msg',
						time: 2 //2秒后自动关闭
					});
				}else{
					$('#orderBuy').show();
					$('#addCart').hide();
					$('.shade').fadeIn();
					$('.specifications_shade').animate({
						bottom: "0"
					});
				}
			});

			//数量增加
			$('.selnum .more').click(function() {
                if (ValSelectedSku()) {
                    if (limitNum > 0) {
                        numMore($('.selnum input'), data.data.ProductSpecList, limitNum, data.data.Stock);
                    } else {
                        numMore($('.selnum input'), data.data.ProductSpecList, data.data.MaxBuyNum, data.data.Stock);
                    }
					
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
                        location.href = 'smOrder.html?Pid=' + proId + '&num=' + count + '&spec=' + escape(skuString) + '&smType=0' + '&sharememberId=' + memberId + "&PreferentialId=" + PreferentialId + "&parms=" + parms;
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
                            if (ActivityProductType == 1) {
                                window.location.href = "/activityJump.html?activity=1&proId=" + pro_Id+"&action=login";
                            } else {
                                window.location.href = "/login.html";
                            }
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
                        yes: function (index) {
                            if (ActivityProductType == 1) {
                                window.location.href = "/activityJump.html?activity=1&proId=" + pro_Id + "&action=login";
                            } else {
                                window.location.href = "/login.html";
                            }
							layer.close(index);
						},
						no: function(index) {
							layer.close(index);

						}
					});
				}
			});
            //隐藏口罩
            var tit = $(".pro_info .name").html();
            if (tit.indexOf("到付") > -1 ) {
                $(".Postage").html("快递：到付");
            }
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
            $(".zhezhao").click(function () {
                $(".coupTan").css("display", "none")
            });      

            //生成分享海报
            var sdetail = setInterval(function () {
                if (!iswx() || (iswx() && isWxLoginOver == true)) {
                    clearInterval(sdetail);
                    userId = sc.utils.getCookieValue("UserId");
                    token = sc.utils.getCookieValue("Token");
                    loadactivity();
                    InitHB(data.data.ProductName, data.data.Synopsis, data.data.ProductImgList[0].PicUrl);
                }
            }, 300);
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
        "spreadcode": spreadcode,
		"UserId": userId,
		"Token": token
	}, callback);
}
function guanle(obj) {
    var v = $(obj).attr("data-v");
    if (v == "0") {
        $(obj).removeClass("clamp");
        $(obj).attr("data-v","1");
    }
    else {
        $(obj).addClass("clamp");
        $(obj).attr("data-v", "0");
    }
}
function loadactivity() {
    if (parms == null) {
    }
    var openId = "";
    if (parms != "Info") {
        var userInfo = sc.auth.getUserInfo();
        if (userInfo != null) {
            var uInfo = JSON.parse(userInfo);
            openId = uInfo.Openid;
        }
       
    }
    var url = 'api/Goods/activityProduct';
    var activitycallback = function (data) {
        if (data.code != "0") {
            layer.open({
                content: data.msg,
                skin: 'msg',
                time: 1 //1秒后自动关闭
            });
            setTimeout(function () {
                window.location.href = "/indexv.html";
            }, 1000);
        } else if (data.msg == "true") {
            $(".icon-minus").hide();
            $(".icon-add").hide();
            $(".add-Cart").hide();
            ActivityProductType = 1;
        }
    }
    sc.post(url, {
        "proId": parseInt(pro_Id),
        "parms": parms,
        "openId": openId
    }, activitycallback);
}       


//生成分享海报
function InitHB(title,desc,picurl) { 
    var callback = function (data) {        
        if (data.code == 0) {   
            var elText = window.location.protocol + "//" + window.location.host + "/details.html?id=" + pro_Id + "&spreadcode=" + data.data.SpreadCode;
            if (parms && parms != null && parms != "Info") {
                elText = window.location.protocol + "//" + window.location.host + "/activityJump.html?activity=1&proId=" + pro_Id + "&spreadcode=" + data.data.SpreadCode;
            }
            //var elText = window.location.href + "&spreadcode=" + data.data.SpreadCode;
            $("#share_img").click(function () {
                    var idx = layer.open({
                        type: 2
                        , content: 'loading...'
                    }); 
                    console.log(elText);
                    InitDrawImg(elText, idx);
            });
            $("#copy_img").click(function () {
                new Clipboard('.copy_img', {
                    text: function () {
                        return elText;
                    }
                });
                layer.open({
                    content: "已复制",
                    skin: 'msg',
                    time: 2
                });
            });
            if (data.data.isTuanUser == true) {
                var shareurl = window.location.href + "&spreadcode=" + data.data.SpreadCode;
                getWxfxConfig(0, title, desc, picurl, shareurl);
                setTimeout(function () {
                    if (xq == "xq") {
                        $(".icons_home").hide();
                        $(".share_img").hide();
                        $(".copy_img").hide();
                        $(".grouptuan").hide();
                    } else {
                        $("#share_img").show();
                        $("#copy_img").show();
                        $(".grouptuan").show();
                    }
                }, 1000);
                if (!sc.utils.isNullOrEmpty(data.data.NickName))
                    $("#copyPro .nickname").html(data.data.NickName);
                if (!sc.utils.isNullOrEmpty(data.data.Headimgurl))
                    $("#copyPro .avatar").attr("src", data.data.Headimgurl);
            } else {
                $(".grouptuan").html("");
            }
        }
    }
    var url = 'api/Goods/ProductTuanUserInfo';
    sc.post(url, {
        "proId": pro_Id,
        "spreadcode": spreadcode,
        "UserId": userId,
        "Token": token
    }, callback);
}
function NewUserPreferentialskuClickFun(SpecificationValue, skuSpecList, isLimit) {
    //	isLimit:1为限时抢购
    $('.skuList li').click(function () {
        var that = $(this);
        var selectedArr = []; //所有已经选择的
        var filterJson = {};
        var allCanArr = [];
        var objParents = that.parents('.natureSku');
        //搜出不是其兄弟元素
        var filter = that.not(objParents).parents('.natureSku').siblings('.natureSku').find(".skuList");
        console.log(filter);

        if (that.hasClass('disabled')) {
            that.click(function (event) {
                event.preventDefault();
            });
        } else {
            if (that.hasClass('active')) {
                that.removeClass('active');
                $('.skuList li').each(function () {
                    if (!$(this).hasClass('forever')) {
                        $(this).removeClass('disabled');
                    }
                })
            } else {
                that.addClass('active').siblings().removeClass('active');
                var json = {};
                var typeName = that.parents('.natureSku').find('.skuTitle').text();
                var typeNameIndex = that.parents('.natureSku').index();
                json[typeName] = that.text();
                selectedArr[typeNameIndex] = json;
                filter.each(function () {
                    var filterArr = [];
                    var self = $(this);
                    console.log($(self));
                    var selfParentIndex = self.parents('.natureSku').index();
                    var selfParenName = self.parents('.natureSku').find('.skuTitle').text();
                    self.find('li').each(function (index) {
                        filterArr.push($(this).text());
                    });
                    filterJson[selfParenName] = filterArr;
                    for (var key in filterJson) {
                        if (key == objParents.children('.skuTitle').text()) {
                            delete filterJson[key];
                        }
                    }
                });

                //				console.log(filterJson);
                var allCanArr = canGroupSku(that, filterJson, SpecificationValue);
                console.log(allCanArr);
                //将搜出来的有可能的组合进行是否有库存的判断
                var md = skuProStock(allCanArr, allProStock(skuSpecList));
                console.log(md);
                filter.each(function () {
                    var self = $(this);
                    self.find('li').each(function () {
                        if (!disableSku($(this), md)) { //这个是要置为不可点击的
                            $(this).addClass('disabled');
                        } else {
                            if (!$(this).hasClass('forever')) {
                                $(this).removeClass('disabled');
                            }
                        }
                    });
                });
            }
        }
        var allSelectedSku = selectedSku();
        var allKeys = getSpecificationValueKey(SpecificationValue);
        if (allSelectedSku.length == allKeys.length) {
            var record = selectedAllMateSku(allSelectedSku, skuSpecList);
            console.log(record);
           
            $('.productInfo .price').text(record.NewUserPrice.toFixed(2));
            if (record.SpecImage) {
                $('.specifications_shade .productInfo img').attr("src", record.SpecImage);
            }
            $('#stock').text(record.ProStock);
        }

    });

}

//渲染sku
function NewUserPreferentialromanceSku(specificationValue, productSpecList) {
    if (specificationValue) {
        var skuType = JSON.parse(specificationValue);
        var strHtml = '';
        for (var key in skuType) {
            strHtml += '<div class="skuBox natureSku">';
            strHtml += '<h2 class="skuTitle">' + key + '</h2>';
            strHtml += '<div class="skuCon">';
            strHtml += '<ul class="skuList">';
            for (var i = 0; i < skuType[key].length; i++) {
                var json = {};
                json[key] = skuType[key][i].name;
                if (foreverDisabled(JSON.stringify(json), specificationValue, productSpecList)) {
                    strHtml += '<li class="forever disabled">' + skuType[key][i].name + '</li>';
                } else {
                    strHtml += '<li>' + skuType[key][i].name + '</li>';
                }
                //              strHtml += '<li>' + skuType[key][i].name + '</li>';
            }
            strHtml += '</ul>';
            strHtml += '</div>';
            strHtml += '</div>';
        }

        $('#skuBoxContent').append(strHtml);
    }

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
		"SpecText": specText,
		"ShareMemberId": memberId
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

//生成分享海报
var iscreate = false;
function InitDrawImg(elText, idx) {  
    if (iscreate) {
        layer.close(idx);
        $("#myshareimg").show();
    }
    else {
        var elText = elText;
        $('#haibaoqrcanvas').qrcode({
            render: "canvas",
            text: elText
        }, "haibaoqrcanvas001");
        var drawing = document.getElementById("haibaoqrcanvas001");
        var context = drawing.getContext('2d');
        var imgURI = drawing.toDataURL("image/png");
        var image = document.createElement("img");
        image.src = imgURI;
        $("#haibaoqrcode").attr("src", image.src);

        //$("#copyPro #prohaibao .proImg img").attr("src", $(".product #productBanner_div").find(".img").css("background-image").replace("url(\"", "").replace("\")", ""));

        var img = new Image();
        img.src = $("#prohaibao .proImg img").attr("src");
        img.setAttribute("crossOrigin", 'Anonymous');
        img.onload = function (e) {                      
            var tx = $("#copyPro .pro_info .counterPrice").html();
            $("#copyPro .pro_info .counterPrice").html("<div>" + tx + "</div><div class='line'></div>");
            CreateDrawImg(idx);
        }
        img.onerror = function (e) { //图片未下载下来，使用默认图    
            $("#prohaibao .proImg img").attr("src", "/images/noPic.jpg");
            var tx = $("#copyPro .pro_info .counterPrice").html();
            $("#copyPro .pro_info .counterPrice").html("<div>" + tx + "</div><div class='line'></div>");
            CreateDrawImg(idx);
        }        
    }
    
}
function CreateDrawImg(idx) {
    $("#copyPro").show();
    var shareContent = document.querySelector('#copyPro');
    var width = shareContent.offsetWidth;
    var height = shareContent.offsetHeight;
    var canvas = document.createElement("canvas");
    var scale = 2;
    canvas.width = width * scale;
    canvas.height = height * scale;
    canvas.getContext("2d").scale(scale, scale);
    var rect = shareContent.getBoundingClientRect();//获取元素相对于视察的偏移量
    canvas.getContext("2d").translate(-rect.left, -rect.top);
    var opts = {
        dpi: window.devicePixelRatio * 2,
        scale: scale,
        canvas: canvas,
        logging: true,
        width: width,
        height: height,
        useCORS: true
    };
    html2canvas(shareContent, opts).then(function (canvas) {
        var img = Canvas2Image.convertToImage(canvas, canvas.width, canvas.height);
        $(img).addClass("haibaoimg");
        $("#resultImg").html(img);
        $("#copyPro").hide();
        iscreate = true;
        $("#myshareimg").show();
        layer.close(idx);
    });
}
function convertCanvasToImage(canvas) {
    var image = new Image();
    image.src = canvas.toDataURL("image/png");
    return image;
}
function CloseCeng() {
    $("#myshareimg").hide();
}