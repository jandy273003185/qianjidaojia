var pegeindex = 1;
var pageSize = 20;
var addressId = 0;
var invoiceId; //发票id
var invoiceEmail;
var couponId;
var invoiceType = 0; //发票类型
var cartString; //购物车传进来的
var userId;
var token;
var payAllMoney = 0;
var proNum = 0; //计算从购物车过来的总数量
var invoiceTitle = 0; //发票抬头类型
var useBalance; //余额

var couponList = [];
var orderSType; //1表示从购物车进来的，0表示从详情页进来的
var proId; //产品id
var skuTxt = ''; //从详情过来的sku
var remarkTxt = ''; //商品详情页过来的商家留言
var detailProNum = 1;
var freightmoney = 0; //直接下单的邮费
var isWallet = 0; //是否使用余额
var balance = 0;
var shengCode; //省代码
var shiCode; //市代码
var couponMoney = 0; //优惠券面额
var isCheck;//余额是否选中
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
	
	GetMemberWallet();//获取余额
//	$("#use__usablePrice").click(function(){
//		isCheck = $("#use__usablePrice").prop("checked");
//		if(isCheck){
//			$(".payText").text("使用余额: ");
//		}else{
//			$(".payText").text("付款: ");
//		}
//		
//	})
	
	GetMemberInfo(function(data) {
		$('#balance').text(data.data.Wallet);
		balance = data.data.Wallet;
	});
	cartString = sc.utils.getQueryString("cartItem"); //获取传进来的购物车id的string值
	orderSType = parseInt(sc.utils.getQueryString("smType"));
	//	proId = sc.utils.getQueryString("Pid");   
	proId = window.location.href.split("?")[1].split("&")[0].split("=")[1];
	userId = sc.utils.getCookieValue("UserId");
	token = sc.utils.getCookieValue("Token");
	if(sc.utils.getQueryString("spec")) {

		skuTxt = sc.utils.getQueryString("spec");
		console.log(skuTxt);
	}
	//  if (sc.utils.getQueryString("num")) {
	//      detailProNum = Number(sc.utils.getQueryString("num"));
	//  }
	detailProNum = window.location.href.split("?")[1].split("&")[1].split("=")[1];
	//	if(sc.utils.getQueryString("Pid")) {
	//		proId = sc.utils.getQueryString("Pid");
	//	}
	//  if (orderSType === 0) {
	//      $('#detailsSureOrder').show();
	//  } else {
	//      $('#detailsSureOrder').hide();
	//  }
	//	proId = 181;
	//	skuTxt = '湖北';
	//获取余额
	hasDefaultAddress(); //获取默认地址
	getInvoiceList(); 
	$('.fixed__defaultPage .btn_back').click(function() {
		$(this).parents('.fixed__defaultPage').hide();
	});

	//没有地址的时候要新添加地址,新增地址
	$('#noAddress,#btnEadit').click(function() {
		addressId = '';
		$('#pop-addrEadit').show();
		$('#pop-addrEadit .from .ipt0').val("");
		$('#address .selected-address li').text("请选择").removeClass('active');
		$('#address .address-content ul').html("");
	});

	$('#HasAddressBox').click(function() { //显示地址列表页弹窗
		$('#pop-addrlist').show().siblings('.fixed__defaultPage').hide();
	});
	//地址列表

	AddressList(pegeindex, pageSize, function(data) {
		var gettpl = document.getElementById('Addrlist_Tpl').innerHTML;
		laytpl(gettpl).render(data.data, function(html) {
			//得到的模板渲染到html
			document.getElementById('Addrlist_Div').innerHTML = html;
		});
	}, function(data) {
		//
	})
	$(window).scroll(function() {
		var scrollTop = $(this).scrollTop();
		var scrollHeight = $(document).height();
		var windowHeight = $(this).height();
		if(scrollTop + windowHeight == scrollHeight) {
			pegeindex++;
			$('.Morebtn').show();
			AddressList(pegeindex, pageSize, function(data) {
				var gettpl = document.getElementById('Addrlist_Tpl').innerHTML;
				var Divhtml = document.getElementById('Addrlist_Div').innerHTML;
				if(data.data.length) {
					laytpl(gettpl).render(data.data, function(html) {
						//得到的模板渲染到html
						document.getElementById('Addrlist_Div').innerHTML = Divhtml + html;
					});
				} else {
					$('.Morebtn').text('已经到底了');
				}

			})
		}
	});

	$('#Addrlist_Div').on('click', '.addritem', function() {
		$('#pop-addrlist').hide();
		addressId = $(this).attr("data-id");
		$("#use__usablePrice").prop("checked", "false");
		//      if (orderSType === 1) {
		//          payAllMoney = 0;
		//          proNum = 0;
		//          getCartsOrder();
		//      }

		productInfo();

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

	})
	$('#Addrlist_Div').on('click', '.editbtn', function(e) {
		e.stopPropagation();
		var addrId = $(this).parents('.addritem').attr('data-id');
		$("#use__usablePrice").prop("checked", "false");
		$('#pop-addrEadit').show().siblings('.fixed__defaultPage').hide();
		//编辑地址或者添加地址
		if(addrId) {
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
				if(data.data.is_def == 1) {
					$('#switch1').prop({
						checked: true
					})
				} else {
					$('#switch1').prop({
						checked: false
					})
				}
			})
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
								if($(this).attr('data-id') == addrId) {
									$(this).remove();
									if(addrId == addressId) {
										addressId = 0;
									}
									console.log("addressId假的：" + addressId);
								}
							});
							if(!$('#Addrlist_Div').find('.addritem').length) {
								addressId = 0;
								$('#HasAddressBox').html("");
								$('#noAddress').show();
							}
							$('#pop-addrEadit').hide();
							$('#pop-addrlist').show();
						});
					}
				});
			})
		} else {
			$('#Del_addr').remove();
		}

	});

	//selectCity(); //弹出城市选择四级联动
	//		点击是否设置为默认
	$("#switch1").click(function() {
		var def = $(this).attr("data-def");
		if(def == 0) {
			$(this).attr("data-def", "1");
		} else {
			$(this).attr("data-def", "0");
		}
	})
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
			if(addressId) { //表示修改地址
				UpdateAddress(addressId, txt_name, txt_tel, isDefault, txt_Province, txt_City, txt_District, txt_Street, txt_fullAddress, txt_Zipcode, 1);
			} else { //表示添加地址
				AddAddress(txt_name, txt_tel, isDefault, txt_Province, txt_City, txt_District, txt_Street, txt_fullAddress, txt_Zipcode, 1);
			}
		}
	})

	//去支付
	$('#btnPay').click(function() {
		var IsPayWallet;
		var isCheck = $("#use__usablePrice").prop("checked");
		if(isCheck === true){
			IsPayWallet = 1;
		}else{
			IsPayWallet = 0;
		}
		invoiceEmail = $("#invoice_email").val();
		if(checkClick()) {
		buyNowSubmitOrder(IsPayWallet);
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
		if(invoiceType === 1) {
			$(".invoiceEmail").hide();
		} else {
			$(".invoiceEmail").show();
		}
	});
	$("#use__usablePrice").click(function() {
		if($(this).is(':checked')) {
			isWallet = 1;
			payAllMoney=allPrice();			
			$("#total4").text(balance > payAllMoney ? 0 : (payAllMoney - balance).toFixed(2));
		} else {
			isWallet = 0;
			$("#total4").text(allPrice());
		}
	});

	//弹出地址选择
	$(".sheng").click(function() {
		GetSreaSheng();
	});
	$(".shi").click(function() {

		GetSreaShi();
	});
	$("#cancel1").click(function() {
		$("#shengId").css("display", "none");
	});
	$("#cancel2").click(function() {
		$("#shiId").css("display", "none");
	});
});

//获取省
function GetSreaSheng() {
	var url = "api/Address/GetSrea";
	var callback = function(data) {
		console.log(data)
		$("#shengId").css("display", "block");
		var shengStr = "";
		for(var i = 0; i < data.data.length; i++) {
			shengStr += '<li class="shengItem" data-code=' + data.data[i].id + ' data-name=' + data.data[i].name + '>' + data.data[i].name + '</li>';
		}
		$("#province1").html(shengStr);
		$(".shengItem").each(function() {
			if($(this).text() === "请选择") {
				$(this).remove();
			}
			$(this).click(function() {
				shengCode = $(this).attr("data-code")
				console.log(shengCode)
				$(".sheng").text($(this).attr("data-name"))
				$("#shengId").css("display", "none");
				$(".shi").text("-请选择市-")
			})
		})
	};
	sc.post(url, {
		"Types": "Province"
	}, callback)
}

//获取市
function GetSreaShi() {
	console.log(shengCode)
	if(shengCode) {
		var url = "api/Address/GetSrea";
		var callback = function(data) {
			console.log(data)

			$("#shiId").css("display", "block");
			var shengStr = "";
			for(var i = 0; i < data.data.length; i++) {
				shengStr += '<li class="shengItem" data-code=' + data.data[i].id + ' data-name=' + data.data[i].name + '>' + data.data[i].name + '</li>';
			}
			$("#province2").html(shengStr);
			$(".shengItem").each(function() {
				if($(this).text() === "请选择") {
					$(this).remove();
				}
				$(this).click(function() {
					shiCode = $(this).attr("data-code");
					console.log(shiCode)
					$(".shi").text($(this).attr("data-name"))
					$("#shiId").css("display", "none");
				})
			})
		};
		sc.post(url, {
			"Types": "City",
			"Code": shengCode
		}, callback)
	} else {
		layer.open({
			content: '请先选择省',
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
	}

}

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

			//          if (orderSType === 1) {
			//              getCartsOrder(); //表示从购物车过来的
			//          }

			productInfo()

			//			getMyCoupon();
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

//获取发票
function getInvoiceList() {
	var url = "/api/About/AllInvoiceList";
	var calback = function(data) {
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

//获取购买的产品信息
function productInfo() {
	var url = "api/Gift/GetInfo";
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
			maxStock = data.data.Stock;
			minBuyNum = data.data.MinBuyNum;
			maxBuyNum = data.data.MaxBuyNum;
			$('#detailsSureOrder .m-pro .buyNum').text(detailProNum); //传过来的购买量
			if(data.data.PicNo) {
				$("#detailsSureOrder .m-pro img").attr("src", data.data.PicNo.split(",")[0]);
			} else {
				$("#detailsSureOrder .m-pro img").attr("src", '/images/noPic.jpg');
			}
			$(".goDetail").click(function(){
				window.location.href = "./welfareDetails.html?id="+proId;
			});
			$("#detailsSureOrder").find(".name").click(function(){
				window.location.href = "./welfareDetails.html?id="+proId;
			});
			$("#detailsSureOrder .m-pro .name").text(data.data.Title);
			//          $("#detailsSureOrder .skuType .type").text(skuTxt);
			$("#detailsSureOrder .m-pro .price .num").text(data.data.Price);
			//          $('#detailsSureOrder .storeName .name').text(data.data.ShopName);
			$('#proNum').text(detailProNum);
			if(data.data.IsPinkage === 0) {
				$(".shop_freight").text("免邮");
				freightmoney = 0;
			} else {
				freightmoney = parseFloat(data.data.FreightFree*detailProNum).toFixed(2);
				$(".shop_freight").text("￥" + freightmoney);
			}
			$("#storeName .storeName .name").text(data.data.storeName)

			//弹出优惠券
			$('#couponBtn').click(function() {
				$('#pop-CouponTpl').show().siblings(".shadeAll").hide();				
			});
			allPrice();
			CouponList();			
		}
	};
	sc.post(url, {
		"Id": proId,
		"UserId": userId,
		"Token": token
	}, calback)
}

//获取我的优惠券
function CouponList() {
	var url = "api/User/CouponList";
	var callback = function(data) {
		if(data.code === 0){			
			//优惠券模板
			couponId = 0;
			$("#couponBtn .couponText").text("不使用");
			var unitPrice = Number($("#danjia").text()); //单价
			var num = Number($(".buyNum").text()); //数量
			var zongjia = unitPrice*num;
			//优惠券渲染模板
			var couStr = '';
			couStr += '<li class="active" data-Id="0" data-denomination="0" data-discountType="1" data-meetConditions="0"><span class="couponTit">不使用</span></li>';
			for(var i = 0 ;i < data.data.length; i++){				
				if(zongjia >= data.data[i].MeetConditions ){
					couStr += '<li data-Id="'+data.data[i].Id+'" data-denomination="'+data.data[i].Denomination+'" data-discountType="'+data.data[i].DiscountType+'" data-meetConditions="'+data.data[i].MeetConditions+'">';
					couStr += '<span>'+data.data[i].Title+'</span>：';
					if(data.data[i].DiscountType === 1){
						couStr += '<span class="couponTit">省'+data.data[i].Denomination+'元</span>';
					}else{
						couStr += '<span class="couponTit">打'+data.data[i].Denomination*10+'折</span>';
					}
					couStr += '</li>';
				}
			}
			$("#usablecouponhtml").html(couStr);
			$('#usablecouponhtml li').click(function() {
				var unitPrice = Number($("#danjia").text()); //单价
				var num = Number($(".buyNum").text()); //数量
				var couponPrice = Number($(this).attr("data-denomination")); //优惠券面额
				if(Number($(this).attr("data-meetconditions")) <= unitPrice * num){
					$(this).addClass('active').siblings().removeClass('active');
					$(this).parents(".shadeAll").hide();
					couponId = parseInt($(this).attr("data-Id"));
					$(".couponText").text($(this).children(".couponTit").text());
					if($(this).attr("data-discountType") === "1"){
						couponMoney = couponPrice;
					}
					if($(this).attr("data-discountType") === "2"){
						couponMoney = unitPrice * num * (1 - couponPrice);
					}
					console.log("优惠券金额："+couponMoney)
					
					if($("#use__usablePrice").is(':checked')) {
						isWallet = 1;
						payAllMoney=allPrice();			
						$("#total4").text(balance > payAllMoney ? 0 : (payAllMoney - balance).toFixed(2));
					} else {
						isWallet = 0;
						$("#total4").text(allPrice());
					}
				}else{
					layer.open({
		                content: "该优惠券不可用",
		                skin: 'msg',
		                time: 2 //2秒后自动关闭
		            });
				}
				
			});
		}
	}
	sc.post(url, {
		UserId: userId,
		Token: token,
		Status: 2,
		Page: 1,
		PageSize:20,
		Type:1
	}, callback)
}

//获取余额接口
function GetMemberWallet(){
	var url = "api/User/GetMemberWallet";
	var callback = function(data){
		$("#balance").text(data.data.Wallet)
		console.log(data)
	}
	sc.post(url,{
		UserId:userId,
		Token:token
	},callback)
}

function valInvoiceEmail(){
   invoiceEmail = $("#invoice_email").val();
   var reg = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if(sc.utils.isNullOrEmpty(invoiceEmail)) {
		layer.open({
			content: '开电子发票，必须输入邮箱地址',
			skin: 'msg',
			time: 3 //1秒后自动关闭
		});
		return false;
	}
    if(!reg.test(invoiceEmail)) {
		layer.open({
			content: '请输入正确的邮箱地址',
			skin: 'msg',
			time: 3 //1秒后自动关闭
		});
		return false;
	}
    return true;
   
}
//立即购买订单提交
function buyNowSubmitOrder(IsPayWallet) {	
	var url = 'api/Gift/BuyNowSubmitOrder';
	var callback = function(data) {
		console.log(data);
		if(invoiceType === 2){
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
		if(data.code === 0) {
			var urldata = encodeURIComponent("orderno=" + data.data+"&gotoType=1");
			window.location.href = "pay.html?" + urldata;
		}
		else if(data.code === 200){
			layer.open({
				content: '下单支付成功!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			setTimeout(function() {
				window.location.href = "/member/order_fl1.html?type=2&source=pay";
			}, 1000);
//			
//			var yue = Number($("#balance").text());
//			var yukuan = Number($("#total4").text());
//			if(yue > yukuan){
//				window.location.href = "/member/order_fl1.html?type=2";
//			}else{
//				layer.open({
//	                content: "余额不足",
//	                skin: 'msg',
//	                time: 2 //2秒后自动关闭
//	            });
//			}
			
		}
		else{
			layer.open({
                content: data.msg,
                skin: 'msg',
                time: 2 //2秒后自动关闭
            });
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"MemberCouponId": couponId,
		"ProvinceCode": shengCode,
		"CityCode": shiCode,
		"ProId": proId,
		"Number": detailProNum,
		"IsPayWallet":IsPayWallet,
		"InvoiceId": invoiceId,
		"InvoiceType": invoiceType,
		"InvoiceEmail": invoiceEmail
	}, callback);
}

//计算总价格
function allPrice() {
	var allPrice = 0;
	$('#detailsSureOrder .cartItem').each(function() {
		var that = $(this);
		var unitPrice = Number(that.find('.price .num').text()); //单价
		var num = Number(that.find('.buyNum').text()); //数量
		allPrice += unitPrice * num;
	});
	$('#detailsSureOrder .orderTotal').text(allPrice);
	var totalNum=(parseFloat(allPrice) + parseFloat(freightmoney) - parseFloat(couponMoney)).toFixed(2);
	$("#total4").text(totalNum);
	$('#total').text(totalNum);
	return totalNum;
}