var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");

//发现列表
function getFindNewsList(page, pageSize, successCallback, fn) {
	var url = 'api/News/FindNewsList';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) {
			successCallback(data);
		} else {
			$(".dropload-down").remove();
			fn.lock("down");
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	var error = function(data) {
		$(".dropload-down").remove();
		fn.lock("down");
		//fn.resetload();
	}
	sc.post(url, {
		"page": page,
		"pagesize": pageSize,
		"UserId": userId,
		"Token": token
	}, callback, error);
}

//发现详情
function getFindNewsInfo(id, successCallback) {
	var url = 'api/News/FindNewsInfo';
	var callback = function(data) {
		if(data.code == 0) {
			successCallback(data);
			var picurl=sc.fileServerUrl+data.data.PicNo;
			getWxfxConfig(0,data.data.Title,data.data.ContentAbstract,picurl);
		}
	}
	sc.post(url, {
		"FindId": id
	}, callback);
}

//点赞
function findlikeOperation(id, successCallback) {
	var url = 'api/News/FindlikeOperation';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) {
			successCallback(data);
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
		"FindId": id
	}, callback);
}

//发现列表页
function initFindPage() {
	var pageNum = 0;
	var pageSize = 10;
	$('.findBox').dropload({
		scrollArea: window,
		domDown: {
			domClass: 'dropload-down',
			domRefresh: '<div class="dropload-refresh">↑上拉加载更多</div>',
			domLoad: '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
			domNoData: '<div class="dropload-noData">已经到底了</div>'
		},
		loadDownFn: function(me) {
			pageNum++;
			getFindNewsList(pageNum, pageSize, function(data) {

				if(data.data.length == 0 && !data.isok) {
					me.lock();
					me.noData();
					me.resetload();
					if(pageNum == 1) {
						$(".dropload-down").remove();
						var emptystr = "";
						emptystr += '<div class="emptybox">';
						emptystr += '<div class="iconimg"><img src="/images/icons/empty2.png"/></div>';
						emptystr += '<p class="tips center">暂无数据 </p>';
						emptystr += '</div>';
						$('.findBox').html(emptystr);
					}
				} else if(data.data.length < pageSize) {
					setTimeout(function() {
						var interText = doT.template($("#findListTemp").text());
						$(".findList")[0].innerHTML += interText(data.data);
						me.resetload();
					}, 1000);
					me.lock("down");
					me.noData();
				} else {
					if(pageNum == 1) {
						$("#couponList2").html("");
					}
					setTimeout(function() {
						var interText = doT.template($("#findListTemp").text());
						$(".findList")[0].innerHTML += interText(data.data);
						me.resetload();
					}, 1000);
				}
				//点赞
				$('.findList').on('click', '.dz', function() {
					var that = $(this);
					var id = that.parents("li").attr("data-id");
					var isDz = parseInt(that.parents("li").attr("data-like"));
					var num = parseInt(that.parents("li").attr("data-likenum"));
					if(sc.auth.isLogin()) {
						findlikeOperation(id, function(data) {
							if(isDz) {
								that.removeClass('active');
								if(num > 0) {
									that.text(num - 1);
									//num = num - 1;
									that.parents("li").attr("data-likenum", num - 1);
								} else {
									that.text(0);
								}

								that.parents("li").attr("data-like", 0);
							} else {
								that.addClass('active');
								that.text(num + 1);
								//								that.parents("li").attr("data-likenum",num+1)
								//num = num + 1;
								that.parents("li").attr("data-likenum", num + 1);
								that.parents("li").attr("data-like", 1);
							}
							layer.open({
								content: data.msg,
								skin: 'msg',
								time: 2 //2秒后自动关闭
							});
						});
					} else {
						layer.open({
							content: '亲，您还没有登录，是否登录？',
							btn: ['登录', '取消'],
							yes: function(index) {
								layer.close(index);
								location.href = '/login.html'
							},
							no: function(index) {
								layer.close(index);
							}
						});
					}

				});
			}, me);

		}
	});

}

//发现详情页面
function initFindDetailPage() {
	var id = sc.utils.getQueryString("id");
    getFindNewsInfo(id, function (data) {
        document.title = data.data.Title;
        $("#detailTitle").html(data.data.Title);
		var interText = doT.template($("#findDetailTemp").text());
		$(".discoverDetails")[0].innerHTML += interText(data.data);
	});
}