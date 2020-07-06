function getWxfxConfig(iscz, shareTitle, shareDesc, shareImgUrl,shareUrl="") {
	if(shareDesc==""){
		shareDesc=window.location.href;
    }
    if (sc.utils.isNullOrEmpty(shareUrl))
        shareUrl = window.location.href;
	$.ajax({
		type: 'post',
		url: sc.serverUrl + 'api/Help/GetWxConfig',
		data: {
			"Iswx": "1",
			"ShareUrl": location.href,
			"Iscz": iscz
		},
		dataType: "json",
		error: function(XMLHttpRequest, textStatus, errorThrown) {},
		success: function(msg) {
			if(msg.code === 0) {
				wx.config({
					debug: false,
					appId: msg.data.appid, // 必填，公众号的唯一标识
					timestamp: msg.data.timestamp, // 必填，生成签名的时间戳
					nonceStr: msg.data.nonceStr, // 必填，生成签名的随机串
					signature: msg.data.signature, // 必填，签名，见附录1
                    jsApiList: ['checkJsApi', 'onMenuShareTimeline', 'setNavigationBarButtons', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo', 'onMenuShareQZone'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				});
                wx.ready(function () {

                    wx.onMenuShareTimeline({
                        title: shareTitle,
                        desc: shareDesc,
                        link: shareUrl,
                        imgUrl: shareImgUrl,
                        success: function () {
                            // 设置成功
                        }
                    });

					//分享给朋友
					wx.onMenuShareAppMessage({
						title: shareTitle, // 分享标题
						desc: shareDesc, // 分享描述
                        link: shareUrl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
						imgUrl: shareImgUrl, // 分享图标
						success: function() {
							// 设置成功
						}
					});
				});

			}
		}

	});
}
// 拼团分享
function getWxfxConfig_pt(iscz, shareTitle, shareDesc, shareImgUrl,shareUrl) {
	if(shareDesc==""){
		shareDesc=shareUrl;
	}
	$.ajax({
		type: 'post',
		url: sc.serverUrl + 'api/Help/GetWxConfig',
		data: {
			"Iswx": "1",
			"ShareUrl": location.href,
			"Iscz": iscz
		},
		dataType: "json",
		error: function(XMLHttpRequest, textStatus, errorThrown) {},
		success: function(msg) {
            if (msg.code === 0) {
               
                wx.config({
                    debug: false,
					appId: msg.data.appid, // 必填，公众号的唯一标识
					timestamp: msg.data.timestamp, // 必填，生成签名的时间戳
					nonceStr: msg.data.nonceStr, // 必填，生成签名的随机串
					signature: msg.data.signature, // 必填，签名，见附录1
                    jsApiList: ['checkJsApi', 'onMenuShareTimeline', 'setNavigationBarButtons', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo', 'onMenuShareQZone'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				});
				
				wx.ready(function() {
					//分享给朋友
					wx.onMenuShareAppMessage({
						title: shareTitle, // 分享标题
						desc: shareDesc, // 分享描述
						link: shareUrl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
						imgUrl: shareImgUrl, // 分享图标
						success: function() {
							// 设置成功
							//alert(shareLink);
						}
					});
				});

			}
		}

	});
}