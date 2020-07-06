var idcardimg1 = "";
var idcardimg2 = "";
var businessimg = "";
var has_click = false;
var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
var codeType; //没有注册过的为5 ，注册过的重新申请的为13
var Shopid="";
$(function() {

	//首先要判断是否填写了
	memberMerchantStatuss();

	//上传营业执照将图片转化为Base64
	document.getElementById("file1_input").onchange = function() {
		//gen_base64();
		var file = $_('file1_input').files[0];
		r = new FileReader(); //本地预览
		r.onload = function() {
			businessimg = r.result;
			//EditHeadImage(imgres);
			document.getElementById('Business').src = this.result;
		}
		r.readAsDataURL(file); //Base64

	};
	//手持身份证正面照
	document.getElementById("file2_input").onchange = function() {
		//gen_base64();
		//changepic();
		var file = $_('file2_input').files[0];
		r = new FileReader(); //本地预览
		r.onload = function() {
			idcardimg1 = r.result;
			//EditHeadImage(imgres);
			document.getElementById('Positive').src = this.result;
		}
		r.readAsDataURL(file); //Base64

	};
	//手持身份证反面照
	document.getElementById("file3_input").onchange = function() {
		var file = $_('file3_input').files[0];
		r = new FileReader(); //本地预览
		r.onload = function() {
			idcardimg2 = r.result;
			//var imgres = r.result;
			//EditHeadImage(imgres);
			document.getElementById('Negative').src = this.result;

		}
		r.readAsDataURL(file); //Base64

	};

	$('#submit').click(function() {
		if(valPhone() && valOther()) {
			if(codeType==5){
				MerchantRegister(); //商家注册
			}else if(codeType==13){
				MerchantUpdateApplication();//编辑资料
			}		
		}
	});

});

function $_(id) {
	return document.getElementById(id);
}

function gen_base64() {
	var file = $_('fileImg').files[0];
	r = new FileReader(); //本地预览
	r.onload = function() {
		var imgres = r.result;
		EditHeadImage(imgres);
	}
	r.readAsDataURL(file); //Base64
}

//获取验证码倒计时
function timerStart() {
	var i = 119;
	var timer = "";
	timer = setInterval(function() {
		if(i == 0) {
			$('#sendCode span').html('获取验证码');
			has_click = false;
			clearInterval(timer);
		} else {
			$('#sendCode span').html(i + 's后再试');
			--i;
		}
	}, 1000);
}

//验证手机号
function valPhone() {
	var r_phone = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
	var phoneNumber = $("#mobile").val().trim();
	if(phoneNumber == "") {
		layer.open({
			content: '手机号不能为空!',
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
		return false;
	}
	if(!r_phone.test(phoneNumber)) {
		layer.open({
			content: '请输入正确的手机格式!',
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
		return false;
	}
	return true;
}

//其他验证
function valOther() {
	var pwd = $.trim($('#shopwd').val());
	var pwd2 = $.trim($('#reshopwd').val());
	var code = $("#txtCode").val();
	var shopname = $("#shopName").val();
	var shopNick = $("#shopNick").val();
	var idcard = $("#idcard").val();
	var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	if(pwd == "" || pwd2 == "") {
		layer.open({
			content: '密码不能为空!',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(pwd.length < 6) {
		layer.open({
			content: '密码长度不能小于6个字符!',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(pwd != pwd2) {
		layer.open({
			content: '密码不一致,请重新设置',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(businessimg == "") {
		layer.open({
			content: '请上传营业执照',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(idcardimg2 == "" || idcardimg1 == "") {
		layer.open({
			content: '请上传手持身份证正反照片',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(code == "") {
		layer.open({
			content: '验证码不能为空',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}

	if(shopNick == "") {
		layer.open({
			content: '店铺名称不能为空',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(shopname == "") {
		layer.open({
			content: '申请人姓名不能为空',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(!pattern.test(idcard) || idcard == "") {
		layer.open({
			content: '请输入正确的身份证号码!',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}

	return true;
}


//申请商家
function MerchantRegister() {	
	var	url = "api/Shop/MerchantRegister";	
	var idcard = $("#idcard").val();
	var shopname = $("#shopName").val();
	var shopNick = $("#shopNick").val();
	var company = $("#company").val();
	var mobile = $("#mobile").val();
	var pwd = $("#shopwd").val();
	var code = $("#txtCode").val();
	var address = $("#address").val();
	var callback = function(data) {
		if(data.code == 0) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			$('.sellerEnterStatusPage').show(); //审核状态
            $('.testStatus .verify').show();
			//setTimeout(function () {
			//location.href = "/login.html?gotoType=1";
			//}, 2000);
		} else if(data.code == 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		} else if(data.code == 99) {
			layer.open({
				content: "服务器开了个小差！",
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"Idcard": idcard, //身份证号            公司名称不是必填，商家名称是必填
		"IdcardPositive": idcardimg1, //身份证正面照
		"IdcardNegative": idcardimg2, //身份证反面照
		"BusinessLicense": businessimg, //营业执照
		"ShopNick": shopNick, //店铺昵称
		"ShopName": shopname, //申请人姓名（商家名称）
		"CompanyName": company, //公司名称
		"Mobile": mobile, //手机号
		"PassWord": pwd, //密码
		"VerifyCode": code //验证码
	}, callback)
}
/*编辑资料*/
function MerchantUpdateApplication() {
	var	url = "api/Shop/MerchantUpdateApplication";		
	var idcard = $("#idcard").val();
	var shopname = $("#shopName").val();
	var shopNick = $("#shopNick").val();
	var company = $("#company").val();
	var mobile = $("#mobile").val();
	var pwd = $("#shopwd").val();
	var code = $("#txtCode").val();
	var address = $("#address").val();
	var callback = function(data) {
		if(data.code == 0) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});			
			$('.sellerEnterStatusPage').show(); //审核状态			
			$('.testStatus .verify').show();
			$('.testStatus .verifyFail').hide();
			//setTimeout(function () {
			//location.href = "/login.html?gotoType=1";
			//}, 2000);
		} else if(data.code == 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		} else if(data.code == 99) {
			layer.open({
				content: "服务器开了个小差！",
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"Idcard": idcard, //身份证号            公司名称不是必填，商家名称是必填
		"IdcardPositive": idcardimg1, //身份证正面照
		"IdcardNegative": idcardimg2, //身份证反面照
		"BusinessLicense": businessimg, //营业执照
		"ShopNick": shopNick, //店铺昵称
		"ShopName": shopname, //申请人姓名（商家名称）
		"CompanyName": company, //公司名称
		"Mobile": mobile, //手机号
		"PassWord": pwd, //密码
		"VerifyCode": code, //验证码
		"ShopId":Shopid
	}, callback)
}

//发送验证码接口
function sendCode(tel) {
	var url = 'api/Shop/GetMerchantSms';
	var code;
	var callback = function(data) {
		if(data.code == 0) {
			timerStart();
			has_click = true;
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
			});
		}
	}

	sc.post(url, {
		"Mobile": tel,
		"VerifyType": codeType
	}, callback);
}

//商家入驻的申请的审核状态
function memberMerchantStatuss() {
	var url = 'api/shop/MemberMerchantStatus';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) {
			if(data.data.ShopStatus == -1) {
				//没有注册申请过
				$('.sellerEnterEaditPage').show();
				$('.sellerEnterStatusPage').hide();
				codeType = 5;
				//点击发送验证码
				$('#sendCode').click(function() {
					var phoneNumber = $.trim($("#mobile").val());
					if(valPhone()) {
						if(!has_click) {
							sendCode(phoneNumber);
						}

					}
				});
			} else if(data.data.ShopStatus == 0) {
				//已经注册申请过了待审核
				$('.sellerEnterEaditPage').hide();
				$('.sellerEnterStatusPage').show();
				$('.testStatus .verify').show();
			} else if(data.data.ShopStatus == 1) {
				//入驻申请成功
				$('.sellerEnterEaditPage').hide();
				$('.sellerEnterStatusPage').show();
				$('.testStatus .verifySuccess').show();
				$('#gotoStoreIndex').click(function(){
					location.href = '/member/storeIndex.html?shopId='+data.data.ShopId+'';
				});
			} else if(data.data.ShopStatus == 2) {
				//审核失败
				$('.sellerEnterEaditPage').hide();
				$('.sellerEnterStatusPage').show();
				$('.testStatus .verifyFail').show();
				codeType = 13;
				Shopid=data.data.ShopId;
				//点击发送验证码
				$('#sendCode').click(function() {
					var phoneNumber = $.trim($("#mobile").val());
					if(valPhone()) {
						if(!has_click) {
							sendCode(phoneNumber);
						}

					}
				});
				//重新填写申请资料
				$('#againEadit').click(function() {
					$('.sellerEnterStatusPage').hide();
					$('.sellerEnterEaditPage').show();
					//入驻申请审核失败调取信息
					var url = 'api/Shop/GetMerchantDetail';
					var callbackFn = function(result) {
						console.log(result);
						if(result.code == 0) {
							$('#company').val(result.data.CompanyName) //公司名称
							$('#shopNick').val(result.data.ShopNick); //店铺名称
							$('#Business').attr("src", result.data.BusinessLicense); //营业执业照
							businessimg = result.data.BusinessLicense;
							$('#shopName').val(result.data.Name); //申请人姓名
							$('#shopwd').val(result.data.PassWord); //输入密码
							$('#reshopwd').val(result.data.PassWord);
							$('#mobile').val(result.data.Mobile); //手机号且手机号不能更改了
							$('#mobile').attr("readonly", true);
							$('#idcard').val(result.data.Idcard);
							$('#Positive').attr("src",result.data.IdcardPositive);  //身份证正面照
							idcardimg1 = result.data.IdcardPositive;
							$('#Negative').attr("src",result.data.IdcardNegative);  //身份证反面照
							idcardimg2 = result.data.IdcardNegative;
						}
					}
					sc.post(url, {
						"ShopId": data.data.ShopId
					}, callbackFn);
				});
			} else if(data.data.ShopStatus == 3) {
				//冻结
				$('.sellerEnterEaditPage').hide();
				$('.sellerEnterStatusPage').show();
				$('.testStatus .freeze').show();
			}
		} else if(data.code == 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		} else if(data.code == 2) {
			layer.open({
				content: "亲，您已经掉线，请重新登录！",
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 2000);
		} else if(data.code == 99) {
			layer.open({
				content: "服务器开了个小差！",
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token
	}, callback);
}