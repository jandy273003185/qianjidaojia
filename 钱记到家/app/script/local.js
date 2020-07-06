$(function () {
    var city = sc.utils.getCookieValue("LocalCity");
    if (city != undefined && city != null&&city != "") {
        $('#cityname').text(city);
    } else {
        var ua = navigator.userAgent.toLowerCase();
        var isWeixin = ua.indexOf('micromessenger') != -1;
        if (isWeixin) {

            $.ajax({
                type: 'POST',
                url: sc.serverUrl + 'api/Help/GetWxConfig',
                data: {
                    "Iswx": "1",
                    "ShareUrl": location.href,
                    "Iscz": 0
                },
                dataType: "json",
                error: function (XMLHttpRequest, textStatus, errorThrown) { },
                success: function (msg) {
                    if (msg.code === 0) {
                        wx.config({
                            debug: false,
                            appId: msg.data.appid, // 必填，公众号的唯一标识
                            timestamp: msg.data.timestamp, // 必填，生成签名的时间戳
                            nonceStr: msg.data.nonceStr, // 必填，生成签名的随机串
                            signature: msg.data.signature, // 必填，签名，见附录1
                            jsApiList: ['checkJsApi', 'onMenuShareTimeline','setNavigationBarButtons', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo', 'onMenuShareQZone', 'getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                        });

                        wx.ready(function () {
                            wx.getLocation({
                                type: 'wgs84',
                                success: function (res) {

                                    $.ajax({
                                        type: "GET",
                                        data: {

                                        },
                                        url: "https://api.map.baidu.com/reverse_geocoding/v3/?ak=C23omiZXvqdLDBzKD0aVEnaozbIBDVaA&output=json&callback=renderReverse&coordtype=wgs84ll&location=" + res.latitude + "," + res.longitude,
                                        dataType: "jsonp",
                                        jsonp: "callback",
                                        success: function (data) {
                                            sc.setDefaultCity(data.result.addressComponent.city.toString().split("市")[0]);
                                            $('#cityname').text(data.result.addressComponent.city.toString().split("市")[0]);
                                        }

                                    });

                                }
                            });
                        });

                    }
                }

            });
        } else {
            $.ajax({
                type: "GET",
                url: "http://api.map.baidu.com/location/ip?ip=&ak=lNvsUvT9oTzUGe8nTDHb1zgqhTyMVmtn",
                data: {

                },
                dataType: "jsonp",
                jsonp: "callback",
                success: function (data) {
                    //				alert("当前所在城市经度："+data.content.point.x);
                    //				alert("当前所在城市纬度："+data.content.point.y);
                    $('#cityname').text(data.content.address_detail.city.toString().split("市")[0]);
                    sc.setDefaultCity(data.content.address_detail.city.toString().split("市")[0]);
                }
            });
        }
    }
});