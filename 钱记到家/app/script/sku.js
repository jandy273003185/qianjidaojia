function getSpecificationValueKey(SpecificationValue) {
	var arr = [];
	var type = JSON.parse(SpecificationValue);
	for(var key in type) {
		arr.push(key);
	}
	return arr;
}

function skuClickFun(SpecificationValue, skuSpecList,isLimit) {
//	isLimit:1为限时抢购
	$('.skuList li').click(function() {
		var that = $(this);
		var selectedArr = []; //所有已经选择的
		var filterJson = {};
		var allCanArr = [];
		var objParents = that.parents('.natureSku');
		//搜出不是其兄弟元素
		var filter = that.not(objParents).parents('.natureSku').siblings('.natureSku').find(".skuList");
		console.log(filter);

		if(that.hasClass('disabled')) {
			that.click(function(event) {
				event.preventDefault();
			});
		} else {
			if(that.hasClass('active')) {
				that.removeClass('active');
				$('.skuList li').each(function() {
					if(!$(this).hasClass('forever')) {
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
				filter.each(function() {
					var filterArr = [];
					var self = $(this);
					console.log($(self));
					var selfParentIndex = self.parents('.natureSku').index();
					var selfParenName = self.parents('.natureSku').find('.skuTitle').text();
					self.find('li').each(function(index) {
						filterArr.push($(this).text());
					});
					filterJson[selfParenName] = filterArr;
					for(var key in filterJson) {
						if(key == objParents.children('.skuTitle').text()) {
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
				filter.each(function() {
					var self = $(this);
					self.find('li').each(function() {
						if(!disableSku($(this), md)) { //这个是要置为不可点击的
							$(this).addClass('disabled');
						} else {
							if(!$(this).hasClass('forever')) {
								$(this).removeClass('disabled');
							}
						}
					});
				});
			}
		}
		var allSelectedSku = selectedSku();
		var allKeys = getSpecificationValueKey(SpecificationValue);
		if(allSelectedSku.length == allKeys.length) {
			var record = selectedAllMateSku(allSelectedSku, skuSpecList);
			console.log(record);
			if(isLimit !=1){
			  $('#new_price').text(record.PunitPrice.toFixed(2));	//不是限时购的时候，选完sku价格不用变
			}
			if(record.SpecImage){
				$('.specifications_shade .productInfo img').attr("src",record.SpecImage);
			}
			$('#stock').text(record.ProStock);
		}

	});

}

//可以组合的
function canGroupSku(obj, group, SpecificationValue) { //obj是当前点击的li
	var objPIndex = obj.parents('.natureSku').index();
	var name = obj.text();
	var sKeys = getSpecificationValueKey(SpecificationValue);
	var allArr = [
		[]
	];
	var arr = [];
	for(var key in group) {
		//		var index = keyIndex(key,sKeys);
		arr.push(group[key]);
	}
	arr.length = sKeys.length - 1;
	for(var i = 0; i < arr.length; i++) {
		var tarr  = [];
		for(var k = 0; k < allArr.length; k++) {
			if(arr[i]) {
				for(var j = 0; j < arr[i].length; j++) {
					tarr.push(allArr[k].concat(arr[i][j]));
				}
			}

		}
		allArr = tarr;
	}
	for(var i = 0; i < allArr.length; i++) {
		allArr[i].splice(objPIndex, 0, name);
	}
	return allArr;
}

//搜出所有有库存的信息
function allProStock(ProductSpecList) {
	var arr = [];
	for(var i = 0; i < ProductSpecList.length; i++) {
		if(ProductSpecList[i].ProStock > 0) {
			arr.push(ProductSpecList[i]);
		}
	}
	return arr;
}

//把已选的可能的组合跟有库存的组合进行筛选
function skuProStock(arr, ProductSpecList) {
	var hasArr = [];
	for(var i = 0; i < arr.length; i++) {
		for(var j = 0; j < ProductSpecList.length; j++) {
			if(arr[i].join("_") == ProductSpecList[j].SpecText) {
				hasArr.push(ProductSpecList[j]);
			}
		}
	}
	return hasArr;
}

/** 把已经筛选好的(可能的组合在有库存的组合筛选出来)记录,用属性值是否存在记录中,把不存在的属性值进行不可点击操作*/
function disableSku(obj, selectedSpecList) {
	var has = false;
	var objParentName = obj.parents('.natureSku').children('.skuTitle').text();
	var name = obj.text();
	for(var i = 0; i < selectedSpecList.length; i++) {
		var specValue = JSON.parse(selectedSpecList[i].SpecValue);
		var arr = [];
		for(var key in specValue) {
			if(objParentName == key && name == specValue[key]) {
				has = true;
			}
		}
	}
	return has;
}

//判断一开始sku的属性是否都没有库存
function foreverDisabled(objValue, sfValue, skus) {
	var hasDisabled = false;
	var obj = JSON.parse(objValue);
	var num = 1;
	var noStockNum = 0;
	var objType;
	for(var key in obj) {
		objType = key;
	}
	var parentsType = JSON.parse(sfValue);

	for(var key in parentsType) {
		if(objType != key) {
			num = num * parentsType[key].length;
		}
	}
	for(var i = 0; i < skus.length; i++) {
		var objSku = JSON.parse(skus[i].SpecValue);
		for(var key in objSku) {
			if(key == objType && obj[objType] == objSku[key]) {
				if(skus[i].ProStock == 0) {
					noStockNum++;
				}
			}
		}
	}
	if(num == noStockNum) {
		hasDisabled = true;
	}
	return hasDisabled;
}

//把所有选中的sku属性进行匹配
function selectedAllMateSku(arr, selectedSpecList) {
	var json = {};
	for(var i = 0; i < selectedSpecList.length; i++) {
		if(arr.join("_") == selectedSpecList[i].SpecText) {
			json = selectedSpecList[i];
		}
	}
	return json;
}

//渲染sku
function romanceSku(specificationValue, productSpecList) {
    
	if(specificationValue) {
		var skuType = JSON.parse(specificationValue);
		var strHtml = '';
		for(var key in skuType) {
			strHtml += '<div class="skuBox natureSku">';
			strHtml += '<h2 class="skuTitle">' + key + '</h2>';
			strHtml += '<div class="skuCon">';
			strHtml += '<ul class="skuList">';
			for(var i = 0; i < skuType[key].length; i++) {
				var json = {};
				json[key] = skuType[key][i].name;
				if(foreverDisabled(JSON.stringify(json), specificationValue, productSpecList)) {
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

//数量增加
function numMore(obj, selectedSpecList, limitBuyNum, stock) {
	var txt = parseInt(obj.val());
	//var txt = 1;
	var record;
	var hasSkuStock = false;
	var resultStock = stock;
	console.log("传进来的库存:"+stock);
	//这里要先判断选中的sku的库存时候足够
	if(selectedSpecList.length > 0) {
		hasSkuStock = true;
		record = selectedAllMateSku(selectedSku(), selectedSpecList);//选sku库存
		resultStock = record.ProStock;
		console.log("有sku的时候的库存:"+resultStock);
	}
	var limitNum =parseInt(limitBuyNum);
	console.log("库存:"+stock);
	console.log("sku库存:"+resultStock);
	console.log("最大限制"+limitBuyNum);
	if(limitNum===0) { //没有限制的时候
		console.log("没有限制的时候");
		if(resultStock > txt) {
			txt = txt + 1;
		} else {
			layer.open({
				content: '库存不足!',
				skin: 'msg',
				time: 2
			});
		}

	} else {
		console.log("有限制的时候");
		//有限制的时候
		if(resultStock > txt) {
			if(txt < limitNum) {
				txt = txt + 1;
			} else {
				layer.open({
					content: '该商品最大限购' + limitNum + '!',
					skin: 'msg',
					time: 2
				});
			}
		} else {
			layer.open({
				content: '库存不足!',
				skin: 'msg',
				time: 2
			});
		}
	}
	obj.val(txt);

}
//数量减少
function numLess(obj, limitBuyNum) {
	var txt = parseInt(obj.val());
	var limitNum = limitBuyNum;
	if(txt >= 2) {
		if(txt > limitNum) {
			txt = txt - 1;
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
	obj.val(txt);
}


//验证提示 ，有sku的时候时候选择了sku
function ValSelectedSku() {
	var result = true;
	if($('.natureSku')) {
		$('.natureSku').each(function() {
			var that = $(this);
			if(!that.find('.skuList li').hasClass("active")) {
				var type = that.children('.skuTitle').text();
				layer.open({
					content: '请选择' + type + '！',
					skin: 'msg',
					time: 2
				});
				result = false;
			}
		});
	}
	return result;
}

//筛选出所有选中的sku并返回
function selectedSku() {
	var selected = [];
	$('.natureSku .skuList li').each(function() {
		var that = $(this);
		if(that.hasClass("active")) {
			selected.push($(this).text());
		}
	})
	return selected;
}

//获取购物车数量
function cartNum() {
	var url = 'api/Cart/GetAllCartNumber';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) {
			if(data.data.AllNumber > 0) {
				$('.dd_footer .cartNum').show();
				if(data.data.AllNumber > 99){
					$('.dd_footer .cartNum').text('99+');
				}else{
					$('.dd_footer .cartNum').text(data.data.AllNumber);
				}
			} else {
				$('.dd_footer .cartNum').hide();
			}
		} else if(data.code == 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2
			});
		}else if(data.code == 99) {
			layer.open({
				content: '服务器开了个小差!',
				skin: 'msg',
				time: 2
			});
		}

	}
	sc.post(url, {
		"UserId": userId,
		"Token": token
	}, callback);
}
