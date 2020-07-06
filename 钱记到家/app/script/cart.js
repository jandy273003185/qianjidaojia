var userId;
var token;
var pageIndex = 0;
var pageSize = 10;
var shopid;
//获取购物车列表信息

function getCartList() {
	var url = 'api/Cart/CartList';
	var callback = function(data) {
		if(data.code === 0) {
			if(data.data.length) {
				$('.cartFoot').show();
				var arrText = doT.template($("#cartTemp").text());
				$("#cartList")[0].innerHTML += arrText(data.data);
				//判断一开始进来的时候全选按钮是否为不可选
				if(starCheckboxStatus(data.data)) {
					$('#allSelect').attr("disabled", "disabled");
					$('#allSelect').parents(".IconsCK").addClass('disabled');
				}
				$('#settleNum').text(settleNumber($('#cartList'))); //一开始就计算结算个数，当checkbox改变时又计算一遍
				$('#allPrice').text("￥" + allPrice($('#cartList'))); //一开始的时候计算总价
				//增加数量
				$('.btn_add').click(function(event) {

					if($(this).hasClass('disabled')) {
						event.preventDefault();
					} else {
						//这里要判断库存时候足够
						$(this).parents('.cartItem').find('input[type="checkbox"]').prop("checked", true);
						$(this).parents('.cartItem').find('.IconsCK').addClass('checked');
						numMore($(this).siblings('.pre_txt'));
						//$('#settleNum').text(settleNumber($('#cartList'))); //一开始就计算结算个数，当checkbox改变时又计算一遍
						//$('#allPrice').text("￥" + allPrice($('#cartList'))); //一开始的时候计算总价
					}
				});
				//减少数量
				$('.btn_reduce').click(function(event) {
					if($(this).hasClass('disabled')) {
						event.preventDefault();
					} else {
						$(this).parents('.cartItem').find('input[type="checkbox"]').prop("checked", true);
						$(this).parents('.cartItem').find('.IconsCK').addClass('checked');
						numLess($(this).siblings('.pre_txt'));
						//$('#settleNum').text(settleNumber($('#cartList'))); //一开始就计算结算个数，当checkbox改变时又计算一遍
						//$('#allPrice').text("￥" + allPrice($('#cartList'))); //一开始的时候计算总价

					}

				});

				//点击全选按钮时
				$('#allSelect').change(function() {
					if($(this).prop("checked")) {
						changeAllChecked(1);
						$(this).prop("checked", true);
						$(this).parents('.IconsCK').addClass('checked');
					} else {
						changeAllChecked(0);
						$(this).prop("checked",false);
						$(this).parents('.IconsCK').removeClass('checked');
					}
					$('#settleNum').text(settleNumber($('#cartList'))); //一开始就计算结算个数，当checkbox改变时又计算一遍
					$('#allPrice').text("￥" + allPrice($('#cartList'))); //一开始的时候计算总价

				});
                $('#cartList input[type="checkbox"]').change(function () {
                    var cbxType = $(this).attr("data-cbxType");
                   
                    if ($(this).prop("checked")) {
						$(this).prop("checked", true);
                        $(this).parents('.IconsCK').addClass('checked');
                        if (cbxType == "shop") {
                            var shopId = $(this).attr("data-shopid");
                            changeshopChecked(1, shopId);
                        }
					} else {
						$(this).prop("checked",false);
                        $(this).parents('.IconsCK').removeClass('checked');
                        if (cbxType == "shop") {
                            var shopId = $(this).attr("data-shopid");
                            changeshopChecked(0, shopId);
                        }
					}
					if(isAllChecked()) {
						$('#allSelect').prop("checked", true);
						$('#allSelect').parents('.IconsCK').addClass('checked');
					} else {
						$('#allSelect').prop("checked",false);
						$('#allSelect').parents('.IconsCK').removeClass('checked');
					}
					$('#settleNum').text(settleNumber($('#cartList'))); //一开始就计算结算个数，当checkbox改变时又计算一遍
					$('#allPrice').text("￥" + allPrice($('#cartList'))); //一开始的时候计算总价
				});

				//点击删除购物车的某些数据
				$('#delCart').click(function() {
					var selectArr = selectedRecord($('#cartList'));
					if(selectArr.length) { //已经选择了
						delCart(selectArr);
					} else {
						//没有选择
						layer.open({
							content: '请选择你要删除的数据!',
							skin: 'msg',
							time: 2 //2秒后自动关闭
						});
					}
					console.log(selectArr);
				});

				//点击结算
				$('#settle').click(function() {
					var smArr = selectedRecord($('#cartList'));
					var arr = [];
					for(var i = 0; i < smArr.length; i++) {
						for(var key in smArr[i]) {
							if(key === "CartId") {
								arr.push(smArr[i][key]);
							}
						}
					}
					if(smArr.length) {
                        console.log(arr);

                        location.href = '/smOrder.html?cartItem=' + arr.unique().join(",") + '&smType=1';
					} else {
						//没有选择
						layer.open({
							content: '请选择你要购买的产品!',
							skin: 'msg',
							time: 2 //2秒后自动关闭
						});
					}
				});

				//点击管理或保存的时候
				$('#btnManage').click(function() {
					if($(this).hasClass('btn_save')) {
						$('#settleNum').text(settleNumber($('#cartList'))); //一开始就计算结算个数，当checkbox改变时又计算一遍
						$('#allPrice').text("￥" + allPrice($('#cartList'))); //一开始的时候计算总价
						$(this).text("管理");
						$(this).removeClass('btn_save');
						$('.cartFoot .inner1').show();
						$('.cartFoot .inner2').hide();
						//点击保存的时候，要把一开始的不能点击的按钮给放上去
						recoverDisabled();

					} else {
						$(this).addClass('btn_save');
						$(this).text("保存");
						$('.cartFoot .inner1').hide();
						$('.cartFoot .inner2').show();
						//当点击编辑的时候，要把所有选择购物车的checkbox变成可以操作的,全选也要发生改变
						$('#cartList .IconsCK').removeClass('disabled');
						$('#cartList input[type="checkbox"]').removeAttr("disabled");
						$('#allSelect').removeAttr("disabled");
						$('#allSelect').parents('.IconsCK').removeClass('disabled');
					}
				});

				//领取优惠券
				$(".youhuiquan").click(function() {
					shopid = $(this).attr("data-shopid");
					var pageNum = 1,
						pageSize = 20;
					CouponCenter(0,"",shopid, pageNum, pageSize, function(data) {
						if(data.data.length > 0) {
							var couponLtpl = document.getElementById('couponLtpl2').innerHTML;
							laytpl(couponLtpl).render(data, function(html) {
								//得到的模板渲染到html
								document.getElementById('couponList2').innerHTML = html;
								$("#couponList2 .item").each(function() {
									$(this).click(function() {
										var CouponId = $(this).attr("data-id");
										ReceiveCoupon($(this), CouponId, 0);
									});
								});
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
					});
				});
				$(".zhezhao").click(function() {
					$(".coupTan").css("display", "none");
				});

			} else {
				var strHtml = '<div class="emptybox"><div class="iconimg"><img src="/images/icons/empty2.png"/></div><p class="tips">暂无数据 </p></div>';
				$('.main').append(strHtml);
				$('.cartFoot').hide();

			}

		} else if(data.code === 1) {
			layer.open({
				content: '请重新登录!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 2000);
		} else if(data.code === 2) {
			layer.open({
				content: '请重新登录!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 2000);

		} else if(data.code === 99) {
			layer.open({
				content: '服务器开了个小差!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	};
	var error = function(data) {
		$('.allScreenLoading').hide();
	};
	sc.post(url, {
		"UserId": userId,
		"Token": token
	}, callback, error);

}

//数量增加
function numMore(obj) {
	var txt = parseInt(obj.val());
	var stock = parseInt(obj.parents('.cartItem').attr('data-stock'));
	var limitNum = parseInt(obj.parents('.cartItem').attr("data-maxbuy"));
	if(!limitNum) { //没有限制的时候
		if(stock > txt) {
			txt = txt + 1;
		} else {
			layer.open({
				content: '库存不足!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	} else {
		//有限制的时候
		if(stock > txt) {
			if(txt < limitNum) {
				txt = txt + 1;
			} else {
				layer.open({
					content: '该商品最大限购' + limitNum + '!',
					skin: 'msg',
					time: 2 //2秒后自动关闭
				});
			}
		} else {
			layer.open({
				content: '库存不足!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	var skuText = obj.parents('.cartItem').attr('data-sku');
	var json = {};
	var arr = [];
	json["CartId"] = parseInt(obj.parents('.cartItem').attr("data-id"));
	json["Total"] = txt;
	json["SpecText"] = skuText;
	arr.push(json);
	eaditCart(arr, function() {
		obj.val(txt);
		obj.attr("value", txt);
		console.log(obj.parents('.cartItem').find('input[type="checkbox"]'));
		if(obj.parents('.cartItem').find('input[type="checkbox"]').is(":checked")) {
			if(isAllChecked()) {
				$('#allSelect').attr('checked', true);
				$('#allSelect').parents('.IconsCK').addClass('checked');
			} else {
				$('#allSelect').prop('checked', false);
				$('#allSelect').parents('.IconsCK').removeClass('checked');
			}
			$('#allPrice').text("￥" + allPrice($('#cartList')));
			$('#settleNum').text(settleNumber($('#cartList')));
		}
	});

}
//数量减少
function numLess(obj) {
	var txt = parseInt(obj.val());
	var limitNum = parseInt(obj.parents('.cartItem').attr("data-minbuy"));
	var kcnum= parseInt(obj.parents('.cartItem').attr("data-stock"));
	if(txt >= 2) {
		if(txt > limitNum) {
			txt = txt - 1;
			if(txt==kcnum){
				obj.parents('.cartItem').find('.IconsCK').removeClass('disabled forever_disabled');
				obj.parents('.cartItem').find('input[type="checkbox"]').prop("checked", true).removeAttr('disabled');
			}			
			if(txt>kcnum){
				obj.parents('.cartItem').find('input[type="checkbox"]').prop("checked", false);
				obj.parents('.cartItem').find('.IconsCK').removeClass('checked');
			}
			if(txt<kcnum){
				
				obj.parents('.cartItem').find('.btn_add').removeClass('disabled');
			}
		} else {
			layer.open({
				content: '该商品最少购买量为:' + limitNum + '!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}

	} else {
		layer.open({
			content: '数量不能小于1！',
			skin: 'msg',
			time: 2 //2秒后自动关闭
		});
		txt = 1;
	}
	var skuText = obj.parents('.cartItem').attr('data-sku');
	var json = {};
	var arr = [];
	json["CartId"] = parseInt(obj.parents('.cartItem').attr("data-id"));
	json["Total"] = txt;
	json["SpecText"] = skuText;
	arr.push(json);
	eaditCart(arr, function() {
		obj.val(txt);
		obj.attr("value", txt);
		if(obj.parents('.cartItem').find('input[type="checkbox"]').is(":checked")) {
			if(isAllChecked()) {
				$('#allSelect').prop('checked', true);
				$('#allSelect').parents('.IconsCK').addClass('checked');
			} else {
				$('#allSelect').prop('checked', false);
				$('#allSelect').parents('.IconsCK').removeClass('checked');
			}
			$('#allPrice').text("￥" + allPrice($('#cartList')));
			$('#settleNum').text(settleNumber($('#cartList')));
		}
	});

}

//结算数量
function settleNumber(obj) {
	var num = 0;
	obj.find('.cartItem').each(function() {
		if($(this).find('.IconsCK').hasClass('checked')) {
			num++;
		}
	});
	console.log(num);
	return num;
}

//根据结构上的购物车id查找对应的数据,该函数暂时没有用到，为了以防以后不能通过获取属性值的方式获取数据的时候就启用改函数
function searchCartRecord(id, data) {
	var result;
	for(var i = 0; data.lenght; i++) {
		if(data[i].Id === id) {
			result = data[i];
		}
	}
	return result;
}

//计算总价格
function allPrice(obj) {
	var result = 0;
	obj.find('.cartItem').each(function() {
		if($(this).find('input[type="checkbox"]').prop("checked")) {
			var unitPrice = Number($(this).find('.num').text()); //单价
			var num = parseInt($(this).find('.pre_txt').val()); //数量
			result = Number(result) + Number(unitPrice) * num;
		}
	});
	result = parseFloat(result).toFixed(2);
	return result;
}

//搜出所有已经选择了的数据
function selectedRecord(obj) {
	var idArr = [];
	console.log(obj);
	obj.find('input[type="checkbox"]').each(function() {
		if($(this).prop("checked")) {
			console.log("已经选中");
			var id = $(this).parents('.cartItem').attr('data-id');
			var json = {};
			json["CartId"] = id;
			idArr.push(json);
		}
	});
	console.log(idArr);
	return idArr;
}

//删除购物车列表数据的时候，手动更新
function updateCartList(arr, obj) {
	obj.find('input[type="checkbox"]').each(function() {
		var id = $(this).parents('.cartItem').attr('data-id');
		for(var i = 0; i < arr.length; i++) {
			if(id === parseInt(arr[i].CartId)) {
				$(this).parents('.cartItem').remove();
			}
		}
	});
	if(!obj.html()) {
		var strHtml = '<div class="emptybox"><div class="iconimg"><img src="/images/icons/empty2.png"/></div><p class="tips">暂无数据 </p></div>';
		$('.main').append(strHtml);
		$('.cartFoot').hide();
	}
}

//遍历时候全选中了
function isAllChecked() {
	var allChecked = false;
	var checkboxNum = $('#cartList input[type="checkbox"]');
	var checkedNum = $('#cartList input[type="checkbox"]:checked');
	var disabled = $('#cartList input[disabled="disabled"]');
	if(checkedNum.length === checkboxNum.length - disabled.length) {
		allChecked = true;
	}
	return allChecked;
}
//恢复一开始不能点击的checkbxo属性
function recoverDisabled() {
	var num = 0;
	var checkboxRecord = $('#cartList input[type="checkbox"]');
	console.log(checkboxRecord);
	checkboxRecord.each(function() {
		if($(this).parents('.IconsCK').hasClass('forever_disabled')) {
			//代表是不可选择的
			console.log("这里是恢复不可以点击的！");
			$(this).attr("disabled", true);
			$(this).parents('.IconsCK').addClass('disabled');
			num++;
		}
	});
	if(checkboxRecord.length === num && num) {
		$('#allSelect').attr("disabled", true);
		$('#allSelect').parents('.IconsCK').addClass('disabled');
	}
}

function changeshopChecked(type, shopid) {
    if (type == 1) {
        changeAllChecked(0);
    }
    $('#shopItem_' + shopid + ' input[type="checkbox"]').each(function () {
        if (type == 1) {
            if (!$(this).attr("disabled")) {
                $(this).prop("checked", true);
                $(this).parents('.IconsCK').addClass('checked');
            }
        } else {
            if (!$(this).attr("disabled")) {
                $(this).prop("checked", false);
                $(this).parents('.IconsCK').removeClass('checked');
            }
        }
    });
}

function changeAllChecked(type) {
	//	type:1表示是全选按钮选中时候的判断,0是没选择中的时候
	$('#cartList input[type="checkbox"]').each(function() {
		if(type === 1) {
			if(!$(this).attr("disabled")) {
				$(this).prop("checked", true);
				$(this).parents('.IconsCK').addClass('checked');
			}
		} else if(type === 0) {
			if(!$(this).attr("disabled")) {
				$(this).prop("checked",false);
				$(this).parents('.IconsCK').removeClass('checked');
			}
		}
	});
}

//判断是否一开始的时候全选按钮就不可点击
function starCheckboxStatus(data) {
	var result = false;
	var num = 0;
	for(var i = 0; i < data.length; i++) {
		if(data[i].IsBuy !== 0) {
			num++;
		}
	}
	if(num === data.length) {
		result = true;
	}
	return result;
}

//删除购物车接口
function delCart(dataArr) {
	var url = 'api/Cart/DelCart';
	var callback = function(data) {
		if(data.code === 0) {
			//删除成功
			layer.open({
				content: '删除成功！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			setTimeout(function() {
				location.reload();
			}, 2000);

			//			updateCartList(dataArr, $('#cartList'));
			//			$('#settleNum').text(settleNumber($('#cartList'))); //一开始就计算结算个数，当checkbox改变时又计算一遍
			//			$('#allPrice').text("￥" + allPrice($('#cartList'))); //一开始的时候计算总价
		}
	};
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"data": dataArr
	}, callback);
}

//编辑购物车接口
function eaditCart(dataArr, sucessCallback) {
	var url = 'api/Cart/EditCart';
	var callback = function(data) {
		if(data.code === 0) {
			sucessCallback(data);
		} else {
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
		"data": dataArr
	}, callback);
}

$(function() {
	userId = sc.utils.getCookieValue("UserId");
	token = sc.utils.getCookieValue("Token");
	getCartList();
	//点击管理的时候

});