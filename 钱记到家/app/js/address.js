var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
//获取收货地址列表
function AddressList(pageNum, successCallback,errorCallback) {
	var url = 'api/Address/AddressList';
	var callback = function(data) {
		if(data.code == 0) { //成功
			successCallback(data);
		}
		if(data.code == 1) { //数据异常
			errorCallback(data);
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"page": pageNum,
	}, callback);
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
function AddAddress(name, tel, isDefault, Province, City, District, Street, fullAddress, Zipcode) {
	var url = 'api/Address/AddAddress';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '新增地址成功！',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/member/address.html";
			}, 2000);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '添加地址失败，请重试！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
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
function UpdateAddress(id,name, tel, isDefault, Province, City, District, Street, fullAddress, Zipcode) {
	var url = "api/Address/UpdateAddress";
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '修改地址成功！',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/member/address.html";
			}, 2000);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '修改地址失败，请重试！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Id":id,
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
    
	var liIndex = 0;
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
		var selected = [],//编码
			selectedName = [],//地名
//			selectCityName = $('#selectCity').val(),
//			selectCityCode = $('#selectCity').attr('data-code'),
			type = ["Province", "City", "District", "Street"],
			code = "",
			navLi = $('.selected-address li'),
			conUl = $('.address-content ul');
		//初始化省份
		GetSrea(type[0], code, conUl.eq(0));
		navLi.eq(liIndex).addClass('active').siblings().removeClass('active');
		
//		if(selectCityName!=""){
//			var NameArr=[],
//				CodeArr=[];				
//			NameArr.push(selectCityName.split(" "));
//			CodeArr.push(selectCityCode.split(","));
//			for(var i=0; i<navLi.length;i++){
//				if(NameArr[0][i]!=""){
//					navLi.eq(i).text(NameArr[0][i]);
//				}
//				navLi.eq(i).attr('data-code',CodeArr[0][i]);
//			}
//			
//		}
		
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
			if(index == 4) { //选到第四级后
                $('#' + addrElement).attr("data-code", selected.join(","));
                $('#' + addrElement).val(selectedName.join(" "));
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
function DeleteAddress(id){
	var url='api/Address/DeleteAddress';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) { //成功
			layer.open({
				content: '删除地址成功！',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/member/address.html";
			}, 2000);
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
		"Id":id
	}, callback);
}
