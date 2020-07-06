$(function() {

    var ua = navigator.userAgent.toLowerCase();
    var isWeixin = ua.indexOf('micromessenger') != -1;

	if(isWeixin) {
		$.ajax({
			type: 'POST',
			url: sc.serverUrl + 'api/Help/GetWxConfig',
			data: {
				"Iswx": "1",
				"ShareUrl": location.href,
				"Iscz": 0
			},
			dataType: "json",
			error: function(XMLHttpRequest, textStatus, errorThrown) {},
            success: function (msg) {
            
                if (msg.code === 0) {
                    wx.config({
                        debug: false,
						appId: msg.data.appid, // 必填，公众号的唯一标识
						timestamp: msg.data.timestamp, // 必填，生成签名的时间戳
						nonceStr: msg.data.nonceStr, // 必填，生成签名的随机串
						signature: msg.data.signature, // 必填，签名，见附录1
						jsApiList: ['checkJsApi', 'onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo', 'onMenuShareQZone', 'getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
					});
					wx.ready(function() {
                        wx.getLocation({
                            type: 'wgs84',
							success: function(res) {
								$.ajax({
									type: "GET",
                                    url: "http://api.map.baidu.com/reverse_geocoding/v3/?ak=C23omiZXvqdLDBzKD0aVEnaozbIBDVaA&output=json&coordtype=wgs84ll&location=" + res.latitude + "," + res.longitude,
									data: {

									},
									dataType: "jsonp",
									jsonp: "callback",
									success: function(data) {
                                     
										$('#cityname').html(data.result.addressComponent.city.toString().split("市")[0]);
                                    },
                                    error: function (err) {
                                       
                                    }
								});
							}
						})
					})

				}
			}

		});
	} else {
		$.ajax({
			type: "GET",
            url: "http://api.map.baidu.com/location/ip?ip=&ak=C23omiZXvqdLDBzKD0aVEnaozbIBDVaA",
			data: {

			},
			dataType: "jsonp",
			jsonp: "callback",
			success: function(data) {
				//alert("当前所在城市经度："+data.content.point.x);
				//alert("当前所在城市纬度："+data.content.point.y);
				$('#cityname').html(data.content.address_detail.city.toString().split("市")[0]);
			}
		});
	}
});