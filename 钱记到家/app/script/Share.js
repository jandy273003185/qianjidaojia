$(function() {
	var pro_Id = sc.utils.getQueryString("id");
	//全局变量，动态的文章ID
	var ShareURL = "";
	//			弹出分享
	$('.sharebtn').click(function() {
		$(".fenxiang").fadeIn();
	})
	$(".fenxiang").click(function() {
		$(".fenxiang").hide();
	})
	//绑定所有分享按钮所在A标签的鼠标移入事件，从而获取动态ID
	$(".bdsharebuttonbox a").click(function() {
		ShareURL = $(this).attr("data-url") + pro_Id;
	});
	$('.cancelbtn').click(function() {
		layer.closeAll() //关闭所有层
	})

	function SetShareUrl(cmd, config) {
		if(ShareURL) {
			config.bdUrl = ShareURL;
		}
		return config;
	}
	//插件的配置部分，注意要记得设置onBeforeClick事件，主要用于获取动态的文章ID
	window._bd_share_config = {
		"common": {
			onBeforeClick: SetShareUrl,
			"bdSnsKey": {},
			"bdText": "",
			"bdMini": "2",
			"bdMiniList": false,
			"bdPic": "http://www.datouwang.com/uploads/pic/jiaoben/2017/jiaoben826_s.jpg",
			"bdStyle": "0",
			"bdSize": "24"
		},
		"share": {}
	};
	//插件的JS加载部分
	with(document) 0[(getElementsByTagName('head')[0] || body)
		.appendChild(createElement('script'))
		.src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' +
		~(-new Date() / 36e5)];

})