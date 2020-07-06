var pageNum = 1;
var pageSize = 9;
var addressId = 0;
var invoiceId; //发票id
var invoiceEmail;
var couponId = 0;
var invoiceType = 0; //发票类型
var cartString; //购物车传进来的
var userId;
var token;
var payAllMoney = 0;
var proNum = 0; //计算从购物车过来的总数量
var invoiceTitle = 0; //发票抬头类型
var useBalance; //余额
var couponIdArr = [];
var addrId = 0;
var obj = null;
var couponList = [];
var orderSType; //1表示从购物车进来的，0表示从详情页进来的
var proId;
var skuTxt = ''; //从详情过来的sku
var remarkTxt = ''; //商品详情页过来的商家留言
var detailProNum = 2;
var freightmoney = 0; //直接下单的邮费
var isWallet = 0; //是否使用余额
var balance = 0;
var total_Txt = 0,
    total4_Txt = 0; //原始总计
var shopId = 0;
var memberId = sc.utils.getQueryString("sharememberId");
var parms = sc.utils.getQueryString("parms");
var PreferentialId = sc.utils.getQueryString("PreferentialId");
var limitNum = 0;
var s = true;
Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};
if (!parms) {
    parms = "Info";
}
if (!PreferentialId) {
    PreferentialId = 0;
}
if(!memberId) {
	memberId = 0;
}
var flagClick = false;
function checkClick() {       
	if(flagClick == true) {        
//		layer.open({
//			content: '订单提交中!',
//			type: 2
//		});        
		return false;      
	}      
	flagClick  = true;      
	setTimeout(function() {        
		flagClick = false;      
	}, 4000);      
	return true;   
} 
$(function() {
	//	
	GetMemberInfo(function(data) {
		$('#balance').text(data.data.Wallet);
		balance = data.data.Wallet;
	});
	cartString = sc.utils.getQueryString("cartItem"); //获取传进来的购物车id的string值
	orderSType = parseInt(sc.utils.getQueryString("smType"));
	//	proId = sc.utils.getQueryString("Pid");   
	userId = sc.utils.getCookieValue("UserId");
	token = sc.utils.getCookieValue("Token");
	if(sc.utils.getQueryString("spec")) {
		skuTxt = sc.utils.getQueryString("spec");
		console.log(skuTxt);
	}
	if(sc.utils.getQueryString("num")) {
		detailProNum = Number(sc.utils.getQueryString("num"));
	}
	if(sc.utils.getQueryString("Pid")) {
		proId = sc.utils.getQueryString("Pid");
	}
	if(orderSType === 0) {
		$('#detailsSureOrder').show();
	} else {
		$('#detailsSureOrder').hide();
	}

	hasDefaultAddress(); //获取默认地址
	getInvoiceList(); //
	$('.fixed__defaultPage .btn_back').click(function() {
		$(this).parents('.fixed__defaultPage').hide();
	});



	//没有地址的时候要新添加地址,新增地址
	$('#noAddress,#btnEadit').click(function() {
		addressId = 0;
		addrId = 0;
		$('#pop-addrEadit').show();
		$('#pop-addrEadit .from .ipt0').val("");
		$('#address .selected-address li').text("请选择").removeClass('active');
		$('#address .address-content ul').html("");
		$('#Del_addr').hide();
		$('#pop-addrEadit .head .title').text('添加收货地址');
	});

	$('#HasAddressBox').click(function() { //显示地址列表页弹窗
		$('#pop-addrlist').show().siblings('.fixed__defaultPage').hide();
	});
	//地址列表
	AddressList(pageNum, pageSize, false, function(data) {
		var gettpl = document.getElementById('Addrlist_Tpl').innerHTML;
		laytpl(gettpl).render(data.data, function(html) {
			//得到的模板渲染到html
			$('#Addrlist_Div').append(html);
		});
	});

	$('#Addrlist_Div').on('click', '.addritem', function() {
		$('#pop-addrlist').hide();
		addressId = $(this).attr("data-id");
		if(orderSType === 1) {
			payAllMoney = 0;
			proNum = 0;
			getCartsOrder();
		}
		if(orderSType === 0) {
			getProductInfo();
		}
		var telTxt = $(this).find('.tel').text(); //手机
		var nameTxt = $(this).find('.name').text(); //姓名
		var addressTxt = $(this).find('.addrtxt').text(); //全称地址
		if($('.HasAddress').length > 0) {
			$('.HasAddress .name').text(nameTxt); //拿到姓名
			$('.HasAddress .tel').text(telTxt); //拿到电话
			$('.HasAddress .weui-cell__bd .txt').text(addressTxt); //拿到地址
		} else {
			$('#noAddress').hide();
			var addressJson = {};
			addressJson["id"] = addressId;
			addressJson["tel"] = telTxt.split("：")[1];
			addressJson["name"] = nameTxt.split("：")[1];
			addressJson["addressinfo"] = addressTxt;
			romanceSmAddress(addressJson);
		}
	});
	$('#Addrlist_Div').on('click', '.editbtn', function(e) {
		e.stopPropagation();
		addrId = parseInt($(this).parents('.addritem').attr('data-id'));
		console.log("选中的地址id:" + addrId);
		$('#pop-addrEadit').show().siblings('.fixed__defaultPage').hide();
		//编辑地址或者添加地址
		$('#pop-addrEadit .head .title').text('编辑收货地址');
		GetAddressInfo(addrId, function(data) {
			var selectedName = [];
			selectedName[0] = data.data.province;
			selectedName[1] = data.data.city;
			selectedName[2] = data.data.district;
			selectedName[3] = data.data.street;
			$('#name').val(data.data.name);
			$('#tel').val(data.data.tel);
			$('#selectCity').attr('data-code', selectedName.join(","));
			$('#selectCity').val(data.data.addressstr);
			$('#fullAddress').val(data.data.address);
			$('#switch1').attr("data-def", data.data.is_def);
			if(data.data.is_def === 1) {
				$('#switch1').prop({
					checked: true
				});
			} else {
				$('#switch1').prop({
					checked: false
				});
			}
		});
		$('#Del_addr').show();
		$('#Del_addr').click(function() {
			layer.open({
				content: '您确定要删除该地址吗？',
				btn: ['删除', '取消'],
				skin: 'footer',
				yes: function(index) {
					DeleteAddress(addrId, function(data) {
						layer.open({
							content: '删除地址成功！',
							skin: 'msg',
							time: 1 //1秒后自动关闭
						});

						$('#Addrlist_Div').find('.addritem').each(function() {
							if(parseInt($(this).attr('data-id')) === addrId) {
								$(this).remove();
								if(addrId === addressId) {
									addressId = 0;
								}
							}
						});
						if(!$('#Addrlist_Div').find('.addritem').length) {
							addressId = 0;
							$('#HasAddressBox').html("");
							$('#noAddress').show();
						}
						$('#pop-addrEadit').hide();
						$('#pop-addrlist').show();
						addrId = 0;
					});
				}
			});
		});
	});

    selectCity(); //弹出城市选择四级联动
	//		点击是否设置为默认
	$("#switch1").click(function() {
		var def = parseInt($(this).attr("data-def"));
		if(def === 0) {
			$(this).attr("data-def", "1");
		} else {
			$(this).attr("data-def", "0");
		}
	});
	$('#submitBtn').click(function() {
		var txt_name = $('#name').val();
		txt_tel = $('#tel').val(),
			isDefault = $("#switch1").attr("data-def"),
			txt_fullAddress = $('#fullAddress').val(),
			txt_Zipcode = "",
			selectCityTxt = $('#selectCity').val(),
			selectCityCode = $('#selectCity').attr("data-code"),
			selectedArr = [];
		selectedArr.push(selectCityCode.split(","));
		var txt_Province = selectedArr[0][0],
			txt_City = selectedArr[0][1],
			txt_District = selectedArr[0][2],
			txt_Street = selectedArr[0][3];
		if(valCreateAddress(txt_name, txt_tel, selectCityTxt, txt_fullAddress)) {
			if(addrId) { //表示修改地址
				UpdateAddress(addrId, txt_name, txt_tel, isDefault, txt_Province, txt_City, txt_District, txt_Street, txt_fullAddress, txt_Zipcode, 1);
			} else { //表示添加地址
				AddAddress(txt_name, txt_tel, isDefault, txt_Province, txt_City, txt_District, txt_Street, txt_fullAddress, txt_Zipcode, 1);
			}
		}
	});

	//去支付
	$('#btnPay').click(function() {
		if(checkClick()) {
			insertOrder();
		}
	});

	/*****发票代码********/
	//选择发票
	$('#invoiceBtn').click(function() {
		$('.selectInvoice__shadeAll').show().siblings(".shadeAll").hide();
	});

	//新增发票抬头
	$('#invoice_add').click(function() {
		$('.selectInvoice__shadeAll').hide();
		$('.addInvoice__defaultPage').show();
	});
	//选择发票抬头类型
	$('#selectInvoiceType').click(function() {
		$('.selectFapiaoType__shadeAll').show();
		$('.selectFapiaoType__shadeAll').css("z-index", "100");
	});
	$('#invoiceTypeList li').click(function() {
		$(this).addClass("active").siblings().removeClass("active");
		invoiceTitle = $(this).attr("data-type");
	});
	$('.shadeContent__hd .btn-cancel,.shadeContent__hd .btn-sure').click(function() {
		$(this).parents('.shadeAll').hide();
		$(this).parents('.shadeAll').css("z-index", "20");
		var selected = '';
		$('#invoiceTypeList li').each(function() {
			if($(this).hasClass('active')) {
				selected = $(this).text();
			}
		});
		if($('#invoice_0').is(":checked")) {
			$('#invoiceBtn .invoice_info').text("不需要");
		}
		$('#txtInvoiceType').val(selected);
		if($('#txtInvoiceType').val() === '公司') {
			invoiceTitle = 1;
			$('.taxNo__weui-cell,.addInvoice__weui-cells2').show();
		} else {
			$('.taxNo__weui-cell,.addInvoice__weui-cells2').hide();
		}

	});

	//保存发票信息
	$("#btnSave").on("click", function() {
		if(Authentication()) {
			var url = "api/About/Addinvoice";
			var callback = function(data) {
				console.log(data);
				if(data.code === 2) {
					layer.open({
						content: '登录超时，请重新登录!',
						skin: 'msg',
						time: 2 //2秒后自动关闭
					});
					window.location.href = "/login.html?returnUrl=" + sc.utils.getCurrentPathname();
					return false;
				}
				if(data.code === 1) {
					layer.open({
						content: data.msg,
						skin: 'msg',
						time: 2 //2秒后自动关闭
					});
				} else {
					layer.open({
						content: data.msg,
						skin: 'msg',
						time: 2 //2秒后自动关闭
							,
						end: function() {
							getInvoiceList();
							$('.addInvoice__defaultPage').hide();
							//$('.shade').hide();
							resetPage();
						}
					});
				}
			};
			sc.post(url, {
				"UserId": userId,
				"Token": token,
				"InvoiceTitle": invoiceTitle,
				"HeaderName": $("#txtHeaderName").val().trim(),
				"RegCall": $("#txtRegCall").val().trim(),
				"IsDefault": 0,
				"TaxNumber": $("#txtTaxNumber").val().trim(),
				"BankName": $("#txtBankName").val().trim(),
				"BankAccount": $("#txtBankAccount").val().trim(),
				"RegAddress": $("#txtRegAbout").val().trim()
			}, callback);
		}
	});

	$(".fapiaoTypeBox span").click(function() {
		invoiceType = parseInt($(this).attr("data-invoicetype"));
		$(this).addClass('active').siblings().removeClass('active');
		if(invoiceType === 2) {
			$(".invoiceEmail").hide();
		} else {
			$(".invoiceEmail").show();
		}
	});
	$("#use__usablePrice").click(function() {
		if($(this).is(':checked')) {
			isWallet = 1;
		} else {
			isWallet = 0;
		}
		allPrice();
	});
});

//查看是否有默认地址
function hasDefaultAddress() {
	var url = 'api/Address/defaultaddress_New';
	var callback = function(data) {
        console.log(data);
        if (data.code === 0 || data.code==3) {
			//成功
			if(data.data) {
				addressId = data.data.id;
				romanceSmAddress(data.data);
				$('#noAddress').hide();
			} else {
				//没有默认地址的时候
				$('#noAddress').show();
			}
            if (data.code == 3) {
                layer.open({
                    content: data.msg,
                    skin: 'msg',
                    time: 2 //2秒后自动关闭
                });
            }
			if(orderSType === 1) {
				getCartsOrder(); //表示从购物车过来的
			}
			if(orderSType === 0) { //表示从详情页面进来的
				getProductInfo();
			}
			//getMyCoupon();
		} else if(data.code === 1) {
			//			$('#noAddress').show();

			//1:失败
		} else if(data.code === 2) {
			//需要重新登录
		} else if(data.code === 99) {
			//系统错误
		}
	};
	sc.post(url, {
		"UserId": userId,
        "Token": token,
        "CartIdList": cartString,
        "proId": proId
	}, callback);
}

//渲染已经选择地址的html,或是查出有默认地址的时候渲染
function romanceSmAddress(data) {
	if(data) {
		var strHtml = '';
		strHtml += '<div class="HasAddress" data-id="' + data.id + '">';
		strHtml += '<div class="flex nameBox">';
		strHtml += '<span class="icon"></span>';
		strHtml += '<div class="flex1 name">收件人：' + data.name + '</div>';
		strHtml += '<div class="flex1 tel">手机：' + data.tel + '</div>';
		strHtml += '</div>';
		strHtml += '<div class="weui-cell">';
		strHtml += '<div class="weui-cell__hd">';
		strHtml += '<span class="icon"><img src="images/icons/dd_addr.png" alt="" class="icon-addr" /></span>';
		strHtml += '</div>';
		strHtml += '<div class="weui-cell__bd">';
		strHtml += '<p class="txt">' + data.addressinfo + '</p>';
		strHtml += '</div>';
		strHtml += '<div class="weui-cell__ft">';
		strHtml += '<span class="icon-arrow icon-arrowRight"></span>';
		strHtml += '</div>';
		strHtml += '</div>';
		strHtml += '</div>';
		$('#HasAddressBox').append(strHtml);
	}

}
//拿到购物车查数据
function getCartsOrder() {
	var url = 'api/Cart/GetConfirmOrderGoods';
	var callback = function(data) {
		console.log(data);
		if(data.code === 2) {
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			window.location.href = "/login.html";
		}
		if(data.code === 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
        //商品渲染模板
        $("#couponBtn").hide();
		var sureOrderList = document.getElementById('sureOrderList').innerHTML;
		laytpl(sureOrderList).render(data, function(html) {
            document.getElementById('sureOrderListHtml').innerHTML = html;
        
            $(".shopStoreOrder .shoporder .sleCoupon").each(function () {
                $(this).click(function () {
                    getMyCouponExt($(this), $(this).attr("data-shopid"));
                });
                
            });
           
		});
		for(var i = 0; i < data.data.length; i++) {
			proNum += data.data[i].Number;
		}
		if(!proNum) {
			$('#proNum').text("0");
		} else {
			$('#proNum').text(proNum);
		}

	};
	sc.post(url, {
		"UserId": userId,
		"Token": token,
        "CartIdList": cartString,
        "MemberCouponIds": couponIdArr.join()
	}, callback);
}

//获取可用的优惠券
function getMyCouponExt(obj,shopid) {
    var that = obj;

    var EproId = that.attr("data-proId");
    var EskuTxt = that.attr("data-spec");
    var EdetailProNum = that.attr("data-Num");
    var url = "/api/Order/GetCouponList";
    var callback = function (data) {
        if (data.code === 2) {
            layer.open({
                content: '登录超时，请重新登录!',
                skin: 'msg',
                time: 2 //2秒后自动关闭
            });
            window.location.href = "/login.html";
        }
        if (data.code === 1) {
            layer.open({
                content: data.msg,
                skin: 'msg',
                time: 2 //2秒后自动关闭
            });
        }
        couponId = 0;
        //$("#couponBtn .couponText").text("不使用");
        couponList = data.data;
        //优惠券渲染模板
        var usablecoupon = document.getElementById('usablecoupon').innerHTML;
        laytpl(usablecoupon).render(data, function (html) {
            document.getElementById('usablecouponhtml').innerHTML = html;
        });
        $('#pop-CouponTpl').show().siblings(".shadeAll").hide();
        $('#usablecouponhtml').on('click', 'li', function () {
            $(this).addClass('active').siblings().removeClass('active');
            $(this).parents(".shadeAll").hide();
            //$("input[id='use__usablePrice']").prop("checked", false);
            couponId = parseInt($(this).attr("data-coupon"));
           
            function orderSType0() {
                
                if (orderSType === 0) { //立即购买
                    var allPricenum = 0;
                    $('#detailsSureOrder .cartItem').each(function () {
                        var that = $(this);
                        var unitPrice = parseFloat(that.find('.price .num').text()).toFixed(2); //单价
                        var num = parseInt(that.find('.buyNum').text()); //数量
                        allPricenum = Number(allPricenum) + Number(unitPrice) * num;
                    });
                    payAllMoney = parseFloat(allPricenum + freightmoney).toFixed(2);
                    for (var i = 0; i < couponList.length; i++) {
                        if (couponList[i].Id === couponId) {
                            if (couponList[i].DiscountType === 1 || couponList[i].couponType == 0 || couponList[i].couponType == 2 || couponList[i].couponType == 3 || couponList[i].couponType==4) {
                                if (couponList[i].Denomination >= payAllMoney) {
                                    payAllMoney = 0;
                                    $("input[id='use__usablePrice']").prop("checked", false);
                                    $("input[id='use__usablePrice']").attr("disabled", "disabled");
                                    
                                } else {
                                    $("input[id='use__usablePrice']").removeAttr("disabled");
                                    payAllMoney -= couponList[i].Denomination;
                                }
                                $("#couponBtn .couponText").html("已减 " + couponList[i].Denomination + " 元");
                            } else {
                                var zAmount = 0;
                                var jAmount = 0;
                                if (couponList[i].MeetConditions > 0) {
                                    if (payAllMoney > couponList[i].MeetConditions) {
                                        zAmount = allPricenum * couponList[i].Denomination;
                                        jAmount = allPricenum - zAmount;
                                    }
                                } else {
                                    zAmount = allPricenum * couponList[i].Denomination;
                                    jAmount = allPricenum - zAmount;
                                }
                                if (couponList[i].MaxAmount > 0) {
                                    zAmount = allPricenum * couponList[i].Denomination;
                                    jAmount = allPricenum - zAmount;
                                    if (jAmount > couponList[i].MaxAmount) {
                                        jAmount = couponList[i].MaxAmount;
                                    }
                                    zAmount = allPricenum - jAmount;
                                }
                                $("#couponBtn .couponText").html("已减 " + jAmount+ " 元");
                                payAllMoney = parseFloat(zAmount + freightmoney).toFixed(2);
                            }
                        }
                    }
                    allPrice();
                } else { //购物车
                    proNum = 0;
                    payAllMoney = 0;
                    getCartsOrder(); //购物车过来的
                   // allPrice();
                    getShopOrderMoney(shopid);
                   
                }
            }
        
            if (couponId > 0) {
                that.find(".couponText").text($(this).find("span").text());
                if (couponIdArr.indexOf(couponId) > -1) {
                } else {
                    var shopId = $(this).attr("data-shopid");
                    for (var i = 0; i < couponList.length; i++) {
                        if (couponList[i].ShopId == parseInt(shopId)) {
                            if (couponIdArr.indexOf(couponList[i].Id) > -1) {
                                couponIdArr.remove(couponList[i].Id);
                            }
                        }
                    }
                    couponIdArr.push(couponId);
                }
                orderSType0();
            } else {
                if (couponList != null && couponList.length > 0) {
                    for (var i = 0; i < couponList.length; i++) {
                        if (couponIdArr.indexOf(couponList[i].Id) > -1) {
                            couponIdArr.remove(couponList[i].Id);
                        }
                    }
                }
                $("input[id='use__usablePrice']").removeAttr("disabled");
                that.find(".couponText").text("不使用");
                orderSType0();
            }
        });
    };
    	if(orderSType === 1) {
		sc.post(url, {
			"UserId": userId,
			"Token": token,
			"CartIds": cartString,
			"Type": 1
		}, callback);
	}
	if(orderSType === 0) {
		sc.post(url, {
			"UserId": userId,
			"Token": token,
			"ProductId": proId,
			"ProductSpec": skuTxt,
			"ProductNumber": detailProNum,
			"Type": 0
		}, callback);
	}
        
}


//获取可用的优惠券
//function getMyCoupon() {
//	var url = "/api/Order/GetCouponList";
//	var callback = function(data) {
//		if(data.code === 2) {
//			layer.open({
//				content: '登录超时，请重新登录!',
//				skin: 'msg',
//				time: 2 //2秒后自动关闭
//			});

//			window.location.href = "/login.html";
//		}
//		if(data.code === 1) {
//			layer.open({
//				content: data.msg,
//				skin: 'msg',
//				time: 2 //2秒后自动关闭
//			});
//		}
//		couponId = 0;
//		$("#couponBtn .couponText").text("不使用");
//		couponList = data.data;
//		//优惠券渲染模板
//		var usablecoupon = document.getElementById('usablecoupon').innerHTML;
//		laytpl(usablecoupon).render(data, function(html) {
//			document.getElementById('usablecouponhtml').innerHTML = html;
//		});
//		$('#usablecouponhtml').on('click', 'li', function() {
//			$(this).addClass('active').siblings().removeClass('active');
//			$(this).parents(".shadeAll").hide();
//			//$("input[id='use__usablePrice']").prop("checked", false);
//			couponId = parseInt($(this).attr("data-coupon"));

//			function orderSType0() {
//				if(orderSType === 0) { //立即购买
//					var allPricenum = 0;
//					$('#detailsSureOrder .cartItem').each(function() {
//						var that = $(this);
//						var unitPrice = parseFloat(that.find('.price .num').text()).toFixed(2); //单价
//						var num = parseInt(that.find('.buyNum').text()); //数量
//						allPricenum = Number(allPricenum) + Number(unitPrice) * num;
//					});
//					payAllMoney = parseFloat(allPricenum + freightmoney).toFixed(2);
//					for(var i = 0; i < couponList.length; i++) {
//						if(couponList[i].Id === couponId) {
//							if(couponList[i].DiscountType === 1) {
//								if(couponList[i].Denomination >= payAllMoney) {
//									payAllMoney = 0;
//									$("input[id='use__usablePrice']").prop("checked", false);
//									$("input[id='use__usablePrice']").attr("disabled", "disabled");
//								} else {
//									$("input[id='use__usablePrice']").removeAttr("disabled");
//									payAllMoney -= couponList[i].Denomination;
//								}

//							} else {
//								payAllMoney = parseFloat(allPricenum * couponList[i].Denomination + freightmoney).toFixed(2);
//							}
//						}
//					}
//					allPrice();
//				} else { //购物车
//					proNum = 0;
//					payAllMoney = 0;
//					getCartsOrder(); //购物车过来的
//					//allPrice();
//				}
//			}
//			if(couponId > 0) {
//				$("#couponBtn .couponText").text($(this).find("span").text());
//				orderSType0();
//			} else {
//				$("input[id='use__usablePrice']").removeAttr("disabled");
//				$("#couponBtn .couponText").text("不使用");
//				orderSType0();
//			}
//		});
//	};
//	if(orderSType === 1) {
//		sc.post(url, {
//			"UserId": userId,
//			"Token": token,
//			"CartIds": cartString,
//			"Type": 1
//		}, callback);
//	}
//	if(orderSType === 0) {
//		sc.post(url, {
//			"UserId": userId,
//			"Token": token,
//			"ProductId": proId,
//			"ProductSpec": skuTxt,
//			"ProductNumber": detailProNum,
//			"Type": 0
//		}, callback);
//	}

//}

//获取产品的价格（购物车）
function getShopOrderMoney(shopId) {
    payAllMoney = 0;
    s = false;
	var url = "/api/Order/BuyCartShopMoney";
	var calback = function(data) {
		console.log(data);
		if(data.code === 2) {
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			window.location.href = "/login.html";
        }
        //if (data.code === 1) {
        //    layer.open({
        //        content: data.msg,
        //        skin: 'msg',
        //        time: 2 //2秒后自动关闭
        //    });
        //}
		if(data.code === 202) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			window.location.href = history.go(-1);
        }
        //if (data.data.ExpressType == 2) { //达达快递
        //    $(".shop_freightlab").html("配送费");
        //    $("#shopnumber_" + shopId).find(".shop_freight").text('￥' + data.data.ShopFreight);
        //} else {
            if (data.data.ShopFreight > 0) {
                $("#shopnumber_" + shopId).find(".shop_freight").text('￥' + data.data.ShopFreight);
            } else {
                $("#shopnumber_" + shopId + " .shopfreight").hide();
            }
        //}
        $("#shopnumber_" + shopId + " .orderTotal").text(data.data.OrderTotal);
        if (data.data.DiscountedMoney != 0) {
            $("#shopnumber_" + shopId + " .couponText").text("已减 " + data.data.DiscountedMoney +" 元");
        }
        $("#shopnumber_" + shopId + " .orderMoney").text(parseFloat(data.data.OrderMoney).toFixed(2));
        var allMoney = 0;
        $(".shoporder .orderMoney").each(function () {
            allMoney += parseFloat($(this).text());
        });
        $("#total").text(parseFloat(allMoney).toFixed(2));
        $("#total4").text(parseFloat(allMoney).toFixed(2));
		//allPrice();
		//		if(payAllMoney==0){
		//			$("input[id='use__usablePrice']").prop("checked", false);
		//			$("input[id='use__usablePrice']").attr("disabled","disabled");
		//		}else{
		//			$("input[id='use__usablePrice']").removeAttr("disabled");
		//		}

	};
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"CartIds": cartString,
		"AddressId": addressId,
        "ShopId": shopId,
        "MemberCouponId": couponId,
        "MemberCouponIds": couponIdArr.join()
	}, calback);
	return "";
}
//购物车过来的提交订单
function insertOrder() {
	invoiceEmail = $("#invoice_email").val();
	if(sc.auth.isLogin()) { //如果已经登录的
		if(addressId <= 0) {
			layer.open({
				content: '必须选择收货地址',
				skin: 'msg',
				time: 3 //1秒后自动关闭
			});
			return false;
		}
		if(invoiceType === 1) {
			if(sc.utils.isNullOrEmpty(invoiceEmail)) {
				layer.open({
					content: '开电子发票，必须输入邮箱地址',
					skin: 'msg',
					time: 3 //1秒后自动关闭
				});
				return false;
			}
			var reg = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			if(!reg.test(invoiceEmail)) {
				layer.open({
					content: '请输入正确的邮箱地址',
					skin: 'msg',
					time: 3 //1秒后自动关闭
				});
				return false;
			}
		}
		if(orderSType === 1) { //购物车过来的			
			var orderRemarksArr = [];
			$("#sureOrderListHtml .shoporder").each(function() {
				var json = {};
				json["ShopId"] = $(this).find(".cartList").attr("data-shopid");
				json["Text"] = $(this).find(".to_shop_msg").val();
				orderRemarksArr.push(json);
			});
			var url = "/api/Order/BuyCart";
			var calback = function(data) {
				console.log(data);
				if(data.code === 2) {
					layer.open({
						content: '登录超时，请重新登录!',
						skin: 'msg',
						time: 2 //2秒后自动关闭
					});
					window.location.href = "/login.html";
				}
				if(data.code === 200) {
					layer.open({
						content: '下单支付成功!',
						skin: 'msg',
						time: 2 //2秒后自动关闭
					});
					setTimeout(function() {
						window.location.href = "member/order.html?orderType=0";
					}, 1000);
				} else if(data.code === 0) {
					var urldata = encodeURIComponent("orderno=" + data.data + '&gotoType=0');
					window.location.href = "pay.html?" + urldata;
				} else {
					layer.open({
						content: data.msg,
						skin: 'msg',
						time: 2 //2秒后自动关闭
					});
				}
			};
			sc.post(url, {
				"UserId": userId,
				"Token": token,
				"CartIds": cartString,
				"AddressId": addressId,
				"InvoiceId": invoiceId,
				"InvoiceType": invoiceType,
				"InvoiceEmail": invoiceEmail,
				"OrderRemarks": orderRemarksArr,
				"MemberCouponId": couponId,
                "IsPayWallet": isWallet,
                "MemberCouponIds": couponIdArr.join(),
                "PreferentialId": PreferentialId
			}, calback);
		}
		if(orderSType === 0) {
			remarkTxt = $.trim($('.to_shop_msg').val());
			buyNowSubmitOrder();
		}

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
//获取发票
function getInvoiceList() {
	var url = "/api/About/AllInvoiceList";
	var calback = function(data) {
		console.log(data);
		if(data.code === 2) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			window.location.href = "/login.html";
			return false;
		}
		if(data.code === 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			return false;
		}
		if(data.code === 0) {
			//发票信息券渲染模板
			var myinvoice = document.getElementById('myinvoice').innerHTML;
			laytpl(myinvoice).render(data, function(html) {
				document.getElementById('myinvoicelist').innerHTML = html;
			});
			$("#invoice_add").click(function() {
				$('.addInvoice__defaultPage').show();
			});
			$("#myinvoicelist").on('click', '.select_invoice', function() {
				$(this).find(".IconsCK").addClass("checked");
				$(this).siblings(".select_invoice").find(".IconsCK").removeClass("checked");
				invoiceId = parseInt($(this).attr("data-invoiceid"));
				for(var i = 0; i < data.data.length; i++) {
					if(invoiceId === data.data[i].Id) {
						$("#invoiceBtn .invoice_info").text(data.data[i].InvoiceTitlestr + "：" + data.data[i].HeaderName);
					}
					if(invoiceId === 0) {
						$('#invoiceBtn .invoice_info').text("不需要");
					}
					$(this).parents('.shadeAll').hide();
				}
				if(invoiceId > 0) {
					$(".fapiaoTypeBox").show();
					invoiceType = $(".fapiaoTypeBox span.active").attr("data-invoicetype");
				} else {
					$(".fapiaoTypeBox").hide();
					invoiceType = 0;
				}
			});
			$('#myinvoicelist').on("click", '.delInvoice', function(event) {
				event.stopPropagation();
				var index = $(this).parents('.select_invoice').index();
				var id = $(this).parents('.select_invoice').attr('data-invoiceid');
				Deleteinvoice(id, function(data) {
					layer.open({
						content: '删除成功',
						skin: 'msg',
						time: 2 //1秒后自动关闭
					});
					$('#myinvoicelist .select_invoice').eq(index).remove();
				});
			});

		}
	};
	sc.post(url, {
		"userId": userId,
		"token": token
	}, calback);
}

//发票信息保存表单验证
function Authentication() {
	if($("#txtHeaderName").val() === "") {
		layer.open({
			content: '请输入抬头名称',
			skin: 'msg',
			time: 3 //1秒后自动关闭
		});
		return false;
    }
    if ($("#txtBankName").val() === "") {
        layer.open({
            content: '请输入开户银行',
            skin: 'msg',
            time: 3 //1秒后自动关闭
        });
        return false;
    }
	if($('#txtInvoiceType').val() === '公司') {
		if($("#txtTaxNumber").val() === "") {
			layer.open({
				content: '请输入税号',
				skin: 'msg',
				time: 3 //1秒后自动关闭
			});
			return false;
		}
		var calltext = $("#txtRegCall").val();
		if(!sc.utils.isNullOrEmpty(calltext)) {
			if(!((/^0\d{2,3}-\d{7,8}$/).test(calltext) || (/^[1][3,4,5,6,7,8][0-9]{9}$/).test(calltext))) {
				layer.open({
					content: '注册电话格式有误!',
					skin: 'msg',
					time: 3
				});
				return false;
			}
		}

		//var bankno = $("#txtBankAccount").val();
		//if(!sc.utils.isNullOrEmpty(bankno) && !sc.luhmCheck(bankno)) {
		//	layer.open({
		//		content: '银行卡号格式错误!',
		//		skin: 'msg',
		//		time: 3 //1秒后自动关闭
		//	});
		//	return false;
		//}
	}
	return true;
}

//删除发票
function Deleteinvoice(fId, successCallback) {
	var url = 'api/About/Deleteinvoice';
	var callback = function(data) {
		console.log(data);
		if(data.code === 0) {
			successCallback(data);
		}
		if(data.code === 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //1秒后自动关闭
			});
		}
		if(data.code === 2) {
			layer.open({
				content: '亲，您已掉线，请重新登录',
				skin: 'msg',
				time: 2 //1秒后自动关闭
			});
			window.location.href = "/login.html";
		}
		if(data.code === 99) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //1秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"userId": userId,
		"token": token,
		"Id": fId
	}, callback);
}

//重置抬头
function resetPage() {
	$(".addInvoice__defaultPage :input").each(function() {
		if($(this).attr("readonly") !== "readonly") {
			$(this).val("");
		}
	});
}
/********产品详情页过来的*******/
//获取购买的产品信息
function getProductInfo() {
	var url = "api/Goods/BuyNowInfo";
	var calback = function(data) {
		console.log(data);
		if(data.code === 2) {
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			window.location.href = "/login.html";
			return false;
		}
		if(data.code === 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			return false;
		}
        if (data.code === 0) {
            //maxStock = data.data.Stock;
            //minBuyNum = data.data.MinBuyNum;
            //maxBuyNum = data.data.MaxBuyNum;
            $('#detailsSureOrder .m-pro .buyNum').text(detailProNum); //传过来的购买量
            if (data.data.ProductImage) {
                $("#detailsSureOrder .m-pro img").attr("src", data.data.ProductImage);
            } else {
                $("#detailsSureOrder .m-pro img").attr("src", '/images/noPic.jpg');
            }
            $("#couponBtn").attr("data-proid", data.data.ProductId);
            $("#couponBtn").attr("data-num", detailProNum);
            $("#couponBtn").attr("data-spec", data.data.ProductSpec);
            $("#couponBtn").attr("data-shopid", data.data.ShopId);
            shopid = data.data.ShopId;
            //弹出优惠券
            $('#couponBtn').click(function () {
                getMyCouponExt($(this), shopid);
            });
            $("#detailsSureOrder .m-pro .name").text(data.data.ProductName);
            $("#detailsSureOrder .skuType .type").text(skuTxt);
            $("#detailsSureOrder .m-pro .price .num").text(data.data.Price);
            $('#detailsSureOrder .storeName .name').text(data.data.ShopName);
            $('#proNum').text(detailProNum);
            if (data.data.IsPinkage == 0) {
                $("#detailsSureOrder .shop_freight").html("包邮");
            } else if (data.data.IsPinkage == 2) {
                $("#detailsSureOrder .shop_freight").html("到付");
            } else {
                getFreight();
            }
            allPrice();
        } else {
            layer.open({
                content: data.msg,
                skin: 'msg',
                time: 1 //1秒后自动关闭
            });
            setTimeout(function () {
                window.location.href = "/indexv.html";
            }, 1000);
            
            return false;
        }
	};
	sc.post(url, {
		"userId": userId,
		"token": token,
		"proId": proId,
        "proSpecText": skuTxt,
        "PreferentialId": PreferentialId,
        "parms": parms
	}, calback);
}
//获取邮费
function getFreight() {
	var url = "api/Order/BuyNowToFreight";
	var calback = function(data) {
		if(data.code === 2) {
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			window.location.href = "/login.html";
		}
		if(data.code === 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code === 0) {
			freightmoney = data.data;
			$("#detailsSureOrder .shop_freight").html('￥' + parseFloat(freightmoney).toFixed(2));
			allPrice();
			console.log('运费：' + data.data);
		}
	};
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"ProId": proId,
		"AddressId": addressId,
		"Number": detailProNum,
        "SpecText": skuTxt,
        "PreferentialId": PreferentialId
	}, calback);
}

//立即购买订单提交
function buyNowSubmitOrder() {
	var url = 'api/Order/BuyNowSubmitOrder';
	var callback = function(data) {
		console.log(data);
		if(data.code === 2) {
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			window.location.href = "/login.html";
		}
		if(data.code === 200) {
			layer.open({
				content: '下单支付成功!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			setTimeout(function() {
				window.location.href = "member/order.html?orderType=0";
			}, 1000);
		} else if(data.code === 0) {
			var urldata = encodeURIComponent("orderno=" + data.data);
			window.location.href = "pay.html?" + urldata;
        } else {
            layer.open({
                content: data.msg,
                skin: 'msg',
                time: 2 //2秒后自动关闭
            });
            
		}
	};
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"ProId": proId,
		"Number": detailProNum,
		"AddressId": addressId,
		"SpecText": skuTxt,
		"InvoiceId": invoiceId,
		"InvoiceType": invoiceType,
		"InvoiceEmail": invoiceEmail,
		"MemberCouponId": couponId,
		"Remark": remarkTxt,
		"IsPayWallet": isWallet,
        "ShareMemberId": memberId,
        "PreferentialId": PreferentialId,
        "parms": parms
	}, callback);
}

function allPrice() {
    var allPrice = 0;
 
	if(orderSType === 1) { //购物车购买
		$('.shopStoreOrder .shoporder').each(function() {
			var xjprice = $(this).find('.orderMoney').text(); //小计之和
			allPrice += Number(xjprice);
        });
       
		total_Txt = total4_Txt = parseFloat(allPrice).toFixed(2);
		//		$('#total').text(total_Txt);
		//		$("#total4").text(total4_Txt);
		if(couponId > 0) { //使用优惠券
			var noCouponMoney = total_Txt;
			if($('#use__usablePrice').is(":checked")) { //使用余额
				if(balance > total_Txt) {
                    $("#total").text(parseFloat(total_Txt).toFixed(2));
					$("#total4").text(0);
				} else {
                    $("#total").text(parseFloat(total_Txt).toFixed(2));
					noCouponMoney -= balance;
					$("#total4").text(parseFloat(noCouponMoney).toFixed(2));
				}
			} else {
                $("#total").text(parseFloat(total_Txt).toFixed(2));
                $("#total4").text(parseFloat(total4_Txt).toFixed(2) );
			}
		} else {
			var CouponMoney = parseFloat(allPrice).toFixed(2);
			if($('#use__usablePrice').is(":checked")) { //使用余额
				if(balance > CouponMoney) {
                    $("#total").text(parseFloat(CouponMoney).toFixed(2));
					$("#total4").text(0);
				} else {
                    $("#total").text(parseFloat(CouponMoney).toFixed(2));
					CouponMoney -= balance;
					$("#total4").text(parseFloat(CouponMoney).toFixed(2));
				}
			} else {
                $('#total').text(parseFloat(total_Txt).toFixed(2));
                $("#total4").text(parseFloat(total4_Txt).toFixed(2));
			}
		}

	} else if(orderSType === 0) { //立即购买
		$('#detailsSureOrder .cartItem').each(function() {
			var that = $(this);
			var unitPrice = parseFloat(that.find('.price .num').text()).toFixed(2); //单价
			var num = parseInt(that.find('.buyNum').text()); //数量
			allPrice = Number(allPrice) + Number(unitPrice) * num;
		});
		$('#detailsSureOrder .orderTotal').text(parseFloat(allPrice).toFixed(2));
		total_Txt = total4_Txt = parseFloat(allPrice + freightmoney).toFixed(2);
        $('#total').text(parseFloat(total_Txt).toFixed(2));
        $("#total4").text(parseFloat(total4_Txt).toFixed(2));
		if(couponId > 0) { //使用优惠券
			var noCouponMoney = payAllMoney;
			if($('#use__usablePrice').is(":checked")) { //使用余额
				if(balance > payAllMoney) {
                    $("#total").text(parseFloat(payAllMoney).toFixed(2));
					$("#total4").text(0);
				} else {
                    $("#total").text(parseFloat(payAllMoney).toFixed(2));
					noCouponMoney -= balance;
                    $("#total4").text(noCouponMoney.toFixed(2));
				}
			} else {
                $("#total").text(parseFloat(payAllMoney).toFixed(2));
                $("#total4").text(parseFloat(payAllMoney).toFixed(2));
			}
		} else {
			var CouponMoney = parseFloat(allPrice + freightmoney).toFixed(2);
			if($('#use__usablePrice').is(":checked")) { //使用余额
				if(balance > CouponMoney) {
                    $("#total").text(parseFloat(CouponMoney).toFixed(2));
					$("#total4").text(0);
				} else {
                    $("#total").text(parseFloat(CouponMoney).toFixed(2));
                    CouponMoney -= balance;
                    $("#total4").text(CouponMoney.toFixed(2));
				}
			} else {
                $('#total').text(parseFloat(total_Txt).toFixed(2));
                $("#total4").text(parseFloat(total4_Txt).toFixed(2));
			}
		}
	}
}