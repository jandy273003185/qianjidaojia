var recordSize = 0; //只取两条
var recordData; //取到的参团列表数据
var recordId = 0; //参团id
var groupRecordId = 0;
var OriginalPrice = 0; 
var DecreaseMoney = 0;
var userId = "";
$(function() {
	//详情轮播
	function ddBanner() {
		var swiper = new Swiper('.swiper-container', {
			pagination: {
				el: '.swiper-pagination',
				type: 'fraction',
			},
		});
	}
	$('.shadebg,.eq__shade .btn-close').click(function() {
		var that = $(this).parents('.eq__shade');
		that.fadeOut();
		that.find('.shade-item').animate({
			bottom: "-100%"
		});
	});
    userId = sc.utils.getCookieValue("UserId");
	var pro_Id = sc.utils.getQueryString("id");
	if(sc.utils.getQueryString("groupRecordId")) {
        groupRecordId = parseInt(sc.utils.getQueryString("groupRecordId"));
        $(".btnGroup .btn-Open").hide();
	}
	getGroupProductInfo(pro_Id, function(data) {
		//渲染详情banner
		var arrText = doT.template($("#productBanner_tpl").text());
		$("#productBanner_div").html(arrText(data.data));
		getWxfxConfig(0,data.data.GroupProductName,data.data.GroupSynopsis,data.data.ProductImgList[0].PicUrl);
		ddBanner();
		var p = $('.pro_info .activity.pin');
		p.find('.price').text(data.data.FightingPrice);
		p.find('.counterPrice').text('￥' + data.data.OriginalPrice);
		p.find('.people').text(data.data.MaxPeopleNum + "人团");
		var limitTime = data.data.GroupEndTime;
		countDown(limitTime, '.Countdown .dd', '.Countdown .hh', '.Countdown .mm', '.Countdown .ss');
		var strhtml1 = "";
		strhtml1 += '<h4 class="name">' + data.data.GroupProductName + '</h4>';
		strhtml1 += '<p class="desc">' + data.data.GroupSynopsis + '</p>';
		strhtml1 += '<div class="pro-bd_ft clear">';
		if(data.data.Postage == 0) {
			strhtml1 += '<span class="Postage fl">快递：免运费</span>';
		} else {
			strhtml1 += '<span class="Postage fl">快递：' + data.data.Postage + '元</span>';
		}
		strhtml1 += '<span class="sale_ed fr">已团' + data.data.SuccessGroup + '</span>';
		strhtml1 += '</div>';
		$('.infoItem.pro-bd').append(strhtml1);

		$('footer .btn-Open .price').text("￥" + data.data.FightingPrice);

		//弹出拼团规则
		$('.rulebtn').click(function() {
			var str = '';
			str += '<div class="pop-rule">';
			str += '<div class="pop-hd">活动规则</div>';
			str += '<div class="pop-bd text_l">';
			str += '<div class="rulelist">';
			str += '<p>1、拼团采用人数越多相对应价格递减。</p>';
			str += '<p>2、达到' + data.data.MaxPeopleNum + '人则结束拼团。</p>';
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
		});

		//渲染产品评论
		$('#aprriseNum').html(data.data.EvaluateCount); //商品总评论数
		var interText = doT.template($("#appriseTemp").text());
		$("#appriseList").html(interText(data.data.EvaluateList));
		$('.thumbnails').each(function() {
			$(this).find('img').fsgallery();
		});
		//进入评论列表页
		$('#gotoPraiseList').click(function() {
			location.href = '/Praiselist.html?id=' + data.data.ProductId + '';
		});
		//渲染商品详情中的图文详情
		if(data.data.GroupContentDetail) {
			$('.pro_Detail .dd-Bd').html(data.data.GroupContentDetail);
		} else {
			$('.pro_Detail .dd-Bd').html('<p style="text-align:center;font-size:.15rem;color:#505050;padding:.4rem .15rem;">暂无数据</p>');
		}
        //弹出的sku选择渲染

		romanceSku(data.data.SpecificationValue, data.data.ProductSpecList);
		$('.specifications_shade .productInfo img').attr("src", data.data.ProductImgList[0].PicUrl);
		$('.productInfo .name').html(data.data.GroupProductName); //商品名称
		$('#stock').html(data.data.Stock); //商品库存
        $('#new_price').html(data.data.FightingPrice); //团购价	
        OriginalPrice = data.data.FightingPrice;
		$('#old_price').html(data.data.OriginalPrice); //原价

		//获取该产品的拼团记录
		getGroupRecordList(pro_Id, recordSize, function(res) {
			if(res.data.length > 0) {
				var interText2 = doT.template($("#proGroupListTemp").text());
				$(".pinlistBox_ing").html(interText2(res.data));
				//			查看更多拼团,我要参团
				$('.Pinmore,footer .btn-Add').click(function() {
					$('.pinRecord__shadeAll').show();
					var interText3 = doT.template($("#ShadeProGroupListTemp").text());
					$(".shade__pinlistBox_ing").html(interText3(res.data));
					$('.pinlist').each(function() {
						var that = $(this);
						that.find('li').each(function() {
							var timediv = $(this).find('.endtime');
							GetRTime(timediv);
						});
					});
					goPinTuan(1);
				});
			} else {
                $('.pro_Pintuan').html('');
                $(".btnGroup .btn-Open").show();
				$('.btn-Add').click(function(event) {
					if(groupRecordId) {
						layer.open({
							content: '该拼团已经关闭！',
							skin: 'msg',
							time: 2 //2秒后自动关闭
						});
					} else {
						layer.open({
							content: '没有团可以参，您可以开团！',
							skin: 'msg',
							time: 2 //2秒后自动关闭
						});
					}
				});
			}
			recordData = res;
			$('.pinlist').each(function() {
				var that = $(this);
				that.find('li').each(function() {
					var timediv = $(this).find('.endtime');
					GetRTime(timediv);
				});
			})
			$('.pro_Pintuan .PTips').text(res.data.length + "人正在拼团，可直接参团");
			goPinTuan(0);

		});
        


		//关闭正在拼团弹窗
		$('.shadeAll .btn_close').click(function() {
			$(this).parents('.shadeAll').hide();
		});

		//我要开团
        $('footer .btn-Open').click(function () {
            $('#new_price').html(OriginalPrice); 
			$('#Offered').hide();
			$('#openGroup').show();
			$('.sku__shade ').fadeIn();
			$('.specifications_shade').animate({
				bottom: "0"
			});
		});
		//sku中的数量框中的最少购买量
		if(data.data.MinBuyNum) {
			$('#pro_num').val(data.data.MinBuyNum);
		} else {
			$('#pro_num').val(1);
		}

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

		//参团
		$('#Offered').click(function() {
			var that = $(this).parents('.shade');
			if(sc.auth.isLogin()) { //如果已经登录的
				if(ValSelectedSku()) {
					var skuString = selectedSku().join("_");
					var count = Number($('#pro_num').val());
					that.fadeOut();
					that.find('.shade-item').animate({
						bottom: "-100%"
					});
					if(recordId) {
						location.href = 'smOrder_group.html?groupId=' + pro_Id + '&groupRecordId=' + recordId + '&num=' + count + '&spec=' + escape(skuString) + '&smType=0';
					}
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

		//我要开团
		$('#openGroup').click(function() {
			var that = $(this).parents('.shade');
            if (sc.auth.isLogin()) { //如果已经登录的

                $('#new_price').html(OriginalPrice); 
				if(ValSelectedSku()) {
					var skuString = selectedSku().join("_");
					var count = Number($('#pro_num').val());
					that.fadeOut();
					that.find('.shade-item').animate({
						bottom: "-100%"
					});
					location.href = 'smOrder_group.html?groupId=' + pro_Id + '&groupRecordId=0&num=' + count + '&spec=' + escape(skuString) + '';
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
		PtskuClickFun(data.data.SpecificationValue, data.data.ProductSpecList, 0);
		//		$('#Offered')

	});
});

function romanceSku(specificationValue, productSpecList) {
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

function PtskuClickFun(SpecificationValue, skuSpecList, isLimit) {
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
            if (isLimit != 1) {
                $('#new_price').text((record.GroupPic - DecreaseMoney).toFixed(2));	//不是限时购的时候，选完sku价格不用变
                $('#old_price').text(record.PunitPrice.toFixed(2));
            }
            if (record.SpecImage) {
                $('.specifications_shade .productInfo img').attr("src", record.SpecImage);
            }
            $('#stock').text(record.ProStock);
        }

    });

}

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

//去参团
function goPinTuan(type) {
	//去参团
    DecreaseMoney = 0;
	$('.pinlist').on("click", ".pinbtn", function() {
		if(sc.auth.isLogin()) {
			var that = $(this);
			recordId = that.parents('li').attr('data-recordid');
			if(type === 1) { //表示从弹窗过来的
				$('.pinRecord__shadeAll').hide();
            }

            // 重新计算单价
            sc.post("/api/GroupBuy/CalculAssemblePrice", {
                "UserId": userId,
                "GroupRecordId": recordId
            }, function (data) {
                if (data.code == 1) {
                    $('#openGroup').hide();
                    $('#Offered').show();
                    $('.sku__shade ').fadeIn();
                    DecreaseMoney = parseFloat(data.data);
                    $('#new_price').html((parseFloat($("#new_price").html()) - DecreaseMoney).toFixed(2));
                    $('.specifications_shade').animate({
                        bottom: "0"
                    });
                } else {
                    layer.open({
                        content: data.msg,
                        skin: 'msg',
                        time: 2 //2秒后自动关闭
                    });
                    DecreaseMoney = 0;
                }
             });


			
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
}