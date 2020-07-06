//优惠券列表

function CouponCenter(couId,parms,shopId,pageNum,pageSize,successCallback,errorCallback) {
	var url = "api/Coupon/CouponCenter";
	var callback = function(data) {
		console.log(data);
		if(data.code === 0) {			
			successCallback(data);			
		}
		if(data.code === 1) {
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code === 2) {
			layer.open({
				content: '亲，您已掉线，请重新登录!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code === 99) {
			layer.open({
				content: '服务器开了个小差!',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}	
		var error = function(data) {
			errorCallback(data);
		}
	sc.post(url, {
		'UserId': userId,
		'Token': token,
		'ShopId': shopId,
		"page":pageNum,
        "pageSize": pageSize,
        "CouponId": couId,
        "parms": parms
	}, callback,error)
}

//领取优惠券
function ReceiveCoupon(obj, couponId,couDetailId) {
    if (!couDetailId) {
        couDetailId = 0;
    }
	var url = "api/Coupon/ReceiveCoupon";
	var callback = function(data) {
		console.log(data)
        if (data.code === 1) {
            layer.open({
                content: data.msg,
                skin: 'msg',
                time: 2 //2秒后自动关闭
            });
        }
        else if (data.code === 2) {
            layer.open({
                content: '亲，您已掉线，请重新登录!',
                skin: 'msg',
                time: 2 //2秒后自动关闭
            });
        }
        else if (data.code === 0) {
            layer.open({
                content: '领取成功!',
                skin: 'msg',
                time: 2 //2秒后自动关闭
            });
        } else if (data.code == 5) {
            layer.open({
                content: '领取成功!',
                skin: 'msg',
                time: 2 //2秒后自动关闭
            });
            obj.addClass("false__item");
        }
	}
	sc.post(url, {
		'UserId': userId,
		'Token': token,
		'CouponId': couponId,
		'CounponDetailId': couDetailId
	}, callback)
}