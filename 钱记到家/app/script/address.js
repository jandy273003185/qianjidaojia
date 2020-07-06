var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
var liIndex = 0;
var pageSize = 50;
//无数据显示模板
function emptytpl(emptydiv) {
	var emptystr = "";
	emptystr += '<div class="emptybox">';
	emptystr += '<div class="iconimg"><img src="/images/icons/empty2.png"/></div>';
	emptystr += '<p class="tips">暂无数据 </p>';
	emptystr += '</div>';
	emptydiv.html(emptystr);
}
//获取收货地址列表
function AddressList(pageNum, pageSize, hasDropload,successCallback, el) {
	var url = 'api/Address/AddressList';
	var callback = function(data) {
        console.log(data);
		if(data.code == 0) { //成功
			successCallback(data);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}

	var error = function(data) {
		if(hasDropload) {
			el.resetload();
		}

	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"page": pageNum,
		"pageSize": 50
	}, callback, error);
}
//获取收货地址详细信息
function GetAddressInfo(id, successCallback) {
	var url = "api/Address/GetInfo";
	var callback = function(data) {
		successCallback(data);
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Id": id
	}, callback);
}
//添加收货地址
function AddAddress(name, tel, isDefault, Province, City, District, Street, fullAddress, Zipcode, sourceType) {
	var url = 'api/Address/AddAddress';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) { //成功
			layer.open({
				content: '新增地址成功！',
				skin: 'msg',
				time: 1 //1秒后自动关闭
            });
			if(sourceType == 1) {
				$('#Addrlist_Div').html("");
				$('#pop-addrlist').show().siblings('.fixed__defaultPage').hide();
                //地址列表
				AddressList(1, pageSize,false,function(re) {
					var gettpl = document.getElementById('Addrlist_Tpl').innerHTML;
					laytpl(gettpl).render(re.data, function(html) {
						//得到的模板渲染到html
						document.getElementById('Addrlist_Div').innerHTML = html;
					});
				});
				//location.href = '/smOrder.html?addressId=' + data.data.id + '';
			} else {
				setTimeout(function() {
					location.href = '/member/address.html';
				}, 2000);

			}
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) {
			layer.open({
				content: '你已掉线，请重新登录！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 2000);
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Consignee": name,
		"Mobile": tel,
		"IsDefault": isDefault,
		"ProvinceCode": Province,
		"CityCode": City,
		"DistrictCode": District,
		"StreetCode": Street,
		"FullAddress": fullAddress,
		"PostCode": Zipcode
	}, callback);
}
//修改收货地址
function UpdateAddress(id, name, tel, isDefault, Province, City, District, Street, fullAddress, Zipcode, sourceType) {
	var url = "api/Address/UpdateAddress";
	console.log(sourceType);
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '修改地址成功！',
				skin: 'msg',
				time: 1 //1秒后自动关闭
            });
            if (sourceType == 1) {
				console.log("sourceType:" + sourceType);
				//表示从提交订单过来的
				$('#pop-addrEadit').hide();
				$('#pop-addrlist').show();
				//再执行一遍地址列表
				AddressList(1, pageSize,false, function(re) {
					var gettpl = document.getElementById('Addrlist_Tpl').innerHTML;
					laytpl(gettpl).render(re.data, function(html) {
						//得到的模板渲染到html
						document.getElementById('Addrlist_Div').innerHTML = html;
					});
				});
			} else {
				setTimeout(function() {
					location.href = '/member/address.html';
				}, 2000);
			}

			$('#Addrlist_Div').html("");
			$('#address .lastarea').text("请选择").removeClass('active');
			$('#address .address-content ul').html("").show();

		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) {
			layer.open({
				content: '你已掉线，请重新登录！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 2000);
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Id": id,
		"Consignee": name,
		"Mobile": tel,
		"IsDefault": isDefault,
		"ProvinceCode": Province,
		"CityCode": City,
		"DistrictCode": District,
		"StreetCode": Street,
		"FullAddress": fullAddress,
		"PostCode": Zipcode
	}, callback);
}
//验证新增地址
function valCreateAddress(name, tel, selectCity, fullAddress) {
	var r_phone = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
	var txt_name = $.trim(name), //收件人
		txt_tel = $.trim(tel), //收件人手机号
		txt_selectCity = $.trim(selectCity),
		txt_fullAddress = $.trim(fullAddress); //详细地址
	if(!txt_name) {
		layer.open({
			content: '请输入收件人！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(!txt_tel) {
		layer.open({
			content: '请输入手机号码！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(!r_phone.test(txt_tel)) {
		layer.open({
			content: '请输入正确的手机号！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(!txt_selectCity) {
		layer.open({
			content: '请选择所在城市！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(!txt_fullAddress) {
		layer.open({
			content: '请输入详细地址！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	return true;
}

//获取省市区列表
function GetSrea(Types, Code, obj) {
	var url = "api/Address/GetSrea";
	var callback = function(data) {
		console.log(data);
		obj.html("");
		var str = "";
		if(data.data.length) {
			for(var i = 0; i < data.data.length; i++) {
				if(data.data[i].id != 0) {
					str += '<li city="" id="' + data.data[i].id + '" data-code="' + data.data[i].id + '" data-name="' + data.data[i].name + '">' + data.data[i].name + '</li>';
				}
			}
		} else {
			str += '<li id="" data-code="" data-name="">暂不选择</li>';
		}
		obj.append(str);
	}
	sc.post(url, {
		"Types": Types,
		"Code": Code
	}, callback);
}
//弹出城市四级联动效果
function selectCity() {
     var selected = [];
     var selectedName = [];
	// 地址选择器遮罩层打开与关闭
	$(".from-item-City").click(function(e) {
		$("#addressSelectWrapper").show();
		e.stopPropagation();
		selectCityArea();
	});
	$(document).click(function() {
		$("#addressSelectWrapper").hide();
	});
	$("#cancel").click(function() {
		$("#addressSelectWrapper").hide();
	});
	$("#addressSelect").click(function(e) {
		e.stopPropagation();
	});

	function selectCityArea() {
		var type = ["Province", "City", "District", "Street"],
			code = "",
			navLi = $('.selected-address li'),
			conUl = $('.address-content ul');
		//初始化省份
		GetSrea(type[0], code, conUl.eq(0));
		navLi.eq(liIndex).addClass('active').siblings().removeClass('active');

		conUl.each(function() {
			var that = $(this);
			that.find('li').each(function() {
				if($(this).hasClass('active')) {
					selected[that.index()] = $(this).attr('data-code');
					selectedName[that.index()] = $(this).attr('data-name');
				}
			});
		});

		conUl.on("click", "li", function() {
			var index = 0;
			var PIndex = liIndex = $(this).parents('ul').index();
			navLi.eq(liIndex).addClass('active').siblings().removeClass('active');
			var dataName = $(this).attr('data-name');
			code = $(this).attr('data-code');
			selected[PIndex] = code;
			selectedName[PIndex] = dataName;
			index = PIndex + 1;
			$(this).addClass("active").siblings("li").removeClass('active');
			if(index == 3) { //选到第3级后
				$('#selectCity').attr("data-code", selected.join(","));
				$('#selectCity').val(selectedName.join(" "));
				$("#addressSelectWrapper").hide();
			} else {
				GetSrea(type[index], code, conUl.eq(index));
				$(this).parents('ul').hide();
				conUl.eq(index).show();
				navLi.eq(PIndex).text($(this).text());
				navLi.eq(PIndex).attr('data-code', code);
				navLi.eq(PIndex).nextAll().text("请选择");
				navLi.eq(index).addClass('active').siblings().removeClass('active');
			}
			console.log(selectedName);
			console.log(selected);
		});
		navLi.click(function() {
			var index = $(this).index();
			$(this).addClass("active").siblings().removeClass('active');
			for(var i = index; i < selected.length; i++) {
				selected.splice(i, selected.length - i);
				selectedName.splice(i, selected.length - i);
			}
			conUl.eq(index).show().siblings().hide();
		});
	}
}

//删除收货地址
function DeleteAddress(id, successCallback) {
	var url = 'api/Address/DeleteAddress';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) { //成功
			successCallback(data);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '删除地址失败，请重试！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Id": id
	}, callback);
}