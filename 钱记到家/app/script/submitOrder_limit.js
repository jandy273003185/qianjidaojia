var pageNum = 1;
var pageSize = 9;
var addressId = 0;
var invoiceId; //发票id
var invoiceEmail;
var couponId;
var invoiceType = 0; //发票类型
var userId;
var token;
var invoiceTitle = 0; //发票抬头类型

var payAllMoney = 0;
var couponList = [];
var orderSType; //1表示从购物车进来的，0表示从详情页进来的
var proId;
var skuTxt = ''; //从详情过来的sku
var remarkTxt = ''; //商品详情页过来的商家留言
var detailProNum = 2;
var freightmoney = 0; //直接下单的邮费
var isWallet = 0; //是否使用余额
var balance = 0; //余额
var total_Txt = 0,
	total4_Txt = 0; //原始总计
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

	orderSType = sc.utils.getQueryString("smType");
	userId = sc.utils.getCookieValue("UserId");
	token = sc.utils.getCookieValue("Token");
	if(sc.utils.getQueryString("spec")) {
		skuTxt = sc.utils.getQueryString("spec").split(",").join(" ");
	}
	if(sc.utils.getQueryString("num")) {
		detailProNum = Number(sc.utils.getQueryString("num"));
	}
	if(sc.utils.getQueryString("Pid")) {
		proId = sc.utils.getQueryString("Pid");
	}
	//获取余额
	hasDefaultAddress(); //获取默认地址
	getInvoiceList(); //获取优惠券
	$('.fixed__defaultPage .btn_back').click(function() {
		$(this).parents('.fixed__defaultPage').hide();
	});

	//弹出优惠券
	$('#couponBtn').click(function() {
		$('#pop-CouponTpl').show().siblings(".shadeAll").hide();
	});

	//没有地址的时候要新添加地址,新增地址
	$('#noAddress,#btnEadit').click(function() {
		addressId = 0;
		addrId =0;
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
	AddressList(pageNum, pageSize,false, function(data) {
		var gettpl = document.getElementById('Addrlist_Tpl').innerHTML;
		laytpl(gettpl).render(data.data, function(html) {
			//得到的模板渲染到html
			$('#Addrlist_Div').append(html);
		});
	});

	$('#Addrlist_Div').on('click', '.addritem', function() {
		$('#pop-addrlist').hide();
		addressId = $(this).attr("data-id");
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
		if($('#txtInvoiceType').val() == '公司') {
			invoiceTitle = 1;
			$('.taxNo__weui-cell,.addInvoice__weui-cells2').show();
		} else {
			$('.taxNo__weui-cell,.addInvoice__weui-cells2').hide();
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

							$('.fixed__defaultPage').hide();
							$('.shade').hide();
							resetPage();
						}
					});
				}
			}
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
				"RegAddress": $("#txtRegAbout").val().trim(),
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

	//去支付
	$('#btnPay').click(function() {
		invoiceEmail = $("#invoice_email").val();
		remarkTxt = $.trim($('.to_shop_msg').val());
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
			if(checkClick()) {
			BuyFlashSaleeCreateOrder();
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

	});

});

//查看是否有默认地址
function hasDefaultAddress() {
	var url = 'api/Address/defaultaddress_New';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) {
			//成功
			if(data.data) {
				addressId = data.data.id;
				romanceSmAddress(data.data);
				$('#noAddress').hide();

			} else {
				//没有默认地址的时候
				$('#noAddress').show();

			}
			if(orderSType == 0) { //表示从详情页面进来的
				getProductInfo()
			}
			getMyCoupon();
		} else if(data.code == 1) {
			//			$('#noAddress').show();

			//1:失败
		} else if(data.code == 2) {
			//需要重新登录
		} else if(data.code == 99) {
			//系统错误
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token
	}, callback);
}

//渲染已经选择地址的html,或是查出有默认地址的时候渲染
function romanceSmAddress(data) {
	console.log("ddd");
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

//获取可用的优惠券
function getMyCoupon() {
	var url = "/api/Order/GetCouponList";
	var callback = function(data) {
		console.log(data)
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
		couponId = 0;
		$("#couponBtn .couponText").text("不使用");
		couponList = data.data;
		//优惠券渲染模板
		var usablecoupon = document.getElementById('usablecoupon').innerHTML;
		laytpl(usablecoupon).render(data, function(html) {
			document.getElementById('usablecouponhtml').innerHTML = html;
		});
		$('#usablecouponhtml li').click(function() {
			$(this).addClass('active').siblings().removeClass('active');
			$(this).parents(".shadeAll").hide();
			couponId = parseInt($(this).attr("data-coupon"));			
			if(couponId > 0) {
				$("#couponBtn .couponText").text($(this).find("span").text());
				if(orderSType == 0) {
					var allPricenum=0;
					$('#detailsSureOrder .cartItem').each(function() {
						var that = $(this);
						var unitPrice = parseFloat(that.find('.price .num').text()).toFixed(2); //单价
						var num = parseInt(that.find('.buyNum').text()); //数量
						allPricenum = Number(allPricenum) + Number(unitPrice) * num;
					});
					payAllMoney = parseFloat(allPricenum + freightmoney).toFixed(2);
					for(var i = 0; i < couponList.length; i++) {
						if(couponList[i].Id === couponId) {
							if(couponList[i].DiscountType === 1) {
								if(couponList[i].Denomination>=payAllMoney){
									payAllMoney=0;
									$("input[id='use__usablePrice']").prop("checked", false);
									$("input[id='use__usablePrice']").attr("disabled","disabled");
								}else{
									$("input[id='use__usablePrice']").removeAttr("disabled");
									payAllMoney -= couponList[i].Denomination;
								}	
							} else {
								payAllMoney = parseFloat(allPricenum * couponList[i].Denomination + freightmoney).toFixed(2);
							}
							
						}
					}
					allPrice();
				}
			}else{
				$("input[id='use__usablePrice']").removeAttr("disabled");
				$("#couponBtn .couponText").text("不使用");
				allPrice();
			}
		});
	};
	if(orderSType == 1) {
		sc.post(url, {
			"UserId": userId,
			"Token": token,
			"CartIds": cartString,
			"Type": 1
		}, callback)
	}
	if(orderSType == 0) {
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
				$('.fixed__defaultPage').show();
			});
			$("#myinvoicelist .select_invoice").click(function() {
				$(this).find(".IconsCK").addClass("checked");
				$(this).siblings(".select_invoice").find(".IconsCK").removeClass("checked");
				invoiceId = parseInt($(this).attr("data-invoiceid"));
				for(var i = 0; i < data.data.length; i++) {
					if(invoiceId === data.data[i].Id) {
						$("#invoiceBtn .invoice_info").text(data.data[i].InvoiceTitlestr + "：" + data.data[i].HeaderName);						
					}
					if(invoiceId === 0){
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
		}
	};
	sc.post(url, {
		"userId": userId,
		"token": token
	}, calback)
}

//发票信息保存表单验证
function Authentication() {
	if($("#txtHeaderName").val() == "") {
		layer.open({
			content: '请输入抬头名称',
			skin: 'msg',
			time: 3 //1秒后自动关闭
		});
		return false;
	}
	if($('#txtInvoiceType').val() == '公司') {
		if($("#txtTaxNumber").val() == "") {
			layer.open({
				content: '请输入税号',
				skin: 'msg',
				time: 3 //1秒后自动关闭
			});
			return false;
		}
		var calltext = $("#txtRegCall").val()
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

		var bankno = $("#txtBankAccount").val();
		if(!sc.utils.isNullOrEmpty(bankno) && !sc.luhmCheck(bankno)) {
			layer.open({
				content: '银行卡号格式错误!',
				skin: 'msg',
				time: 3 //1秒后自动关闭
			});
			return false;
		}
	}
	return true;
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
			window.location.href = "/login.html?returnUrl=" + sc.utils.getCurrentPathname();
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
			$('#detailsSureOrder .m-pro .buyNum').text(detailProNum); //传过来的购买量
			if(data.data.ProductImage) {
				$("#detailsSureOrder .m-pro img").attr("src", data.data.ProductImage);
			} else {
				$("#detailsSureOrder .m-pro img").attr("src", '/images/noPic.jpg');
			}

			$("#detailsSureOrder .m-pro .name").text(data.data.ProductName);
			$("#detailsSureOrder .skuType .type").text(skuTxt);
			$("#detailsSureOrder .m-pro .price .num").text(data.data.Price);
			$('#detailsSureOrder .storeName .name').text(data.data.ShopName);
			$('#proNum').text(detailProNum);
			allPrice();
           getFreight();
		}
		
	};
	sc.post(url, {
		"userId": userId,
		"token": token,
		"proId": proId,
		"proSpecText": skuTxt,
		"productType": 1 //0代表普通，1代表特卖,2代表一元购
	}, calback)
}
//获取邮费
function getFreight() {
	var url = "api/Order/BuyNowToFreight";
	var calback = function(data) {
		console.log(data)
		if(data.code === 2) {
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			window.location.href = "/login.html?returnUrl=" + sc.utils.getCurrentPathname();
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
			var freightText = freightmoney > 0 ? '￥'+parseFloat(freightmoney).toFixed(2) : "包邮";
			$("#detailsSureOrder .shop_freight").html(freightText);
			allPrice();
		}
	};
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"ProId": proId,
		"AddressId": addressId,
		"Number": detailProNum,
		"SpecText":skuTxt
	}, calback)
}

function BuyFlashSaleeCreateOrder() {
	var url = 'api/Order/BuyFlashSaleeCreateOrder';
	var callback = function(data) {
		console.log(data);

		if(data.code === 200) {
		    layer.open({
				content: '下单支付成功!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			setTimeout(function() {
				window.location.href = "member/order.html?orderType=0";
			}, 1000);
		} else {
			if(data.code === 2) {
				layer.open({
					content: '登录超时，请重新登录!',
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
				window.location.href = "/login.html";
			}
			else if(data.code === 1) {
				layer.open({
					content: data.msg,
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
			}
			else if(data.code === 99) {
				layer.open({
					content: data.msg,
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
			} else {
				var urldata = encodeURIComponent("orderno=" + data.data+'&gotoType=0');
				window.location.href = "pay.html?" + urldata;
			}

		}
	}
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
		"IsPayWallet": isWallet
	}, callback);
}



//计算总价格
function allPrice(){
	var allPrice = 0;
	$('#detailsSureOrder .cartItem').each(function() {
		var that = $(this);
		var unitPrice = parseFloat(that.find('.price .num').text()).toFixed(2); //单价
		var num = parseInt(that.find('.buyNum').text()); //数量
	    allPrice = Number(allPrice) + Number(unitPrice) * num;
	});
	$('#detailsSureOrder .orderTotal').text(parseFloat(allPrice).toFixed(2));
	total_Txt = total4_Txt = parseFloat(allPrice + freightmoney).toFixed(2);
	$('#total').text(total_Txt);
	$("#total4").text(total4_Txt);
	if(couponId > 0) { //使用优惠券
		var noCouponMoney=payAllMoney;
		if($('#use__usablePrice').is(":checked")) { //使用余额
			if(balance > payAllMoney) {
				$("#total").text(payAllMoney);
				$("#total4").text(0);
			} else {
				$("#total").text(payAllMoney);
				noCouponMoney -= balance;
				$("#total4").text(noCouponMoney);
			}
		}else{
			$("#total").text(payAllMoney);
			$("#total4").text(payAllMoney);
		}
	}else{
		var CouponMoney = parseFloat(allPrice + freightmoney).toFixed(2);
		if($('#use__usablePrice').is(":checked")) { //使用余额
			if(balance > CouponMoney) {
				$("#total").text(CouponMoney);
				$("#total4").text(0);
			} else {
				$("#total").text(CouponMoney);
				CouponMoney -= balance;
				$("#total4").text(CouponMoney);
			}
		}else{
			$('#total').text(total_Txt);
			$("#total4").text(total4_Txt);
		}
	}
}
