$(function() {
	var classTypeId;
	//GetClassify();
    PagenewProductList(0);
	$(window).scroll(function() {
		if(($(this).scrollTop() + $('.head').height() + 45) >= $('.navFixed').offset().top) {
			$('.navFixed').children(".nav-wrap").addClass('fixed');
		} else {
			$('.navFixed').children(".nav-wrap").removeClass('fixed');
		}
	});
	//获取产品分类
	function GetClassify() {
		var url = 'api/Goods/GetProductClass';
		var callback = function(data) {
			if(data.code == 0) {
				if(data.data.length) {
					var interText = doT.template($("#classifyNavList").text());
					$(".classifyNavBox").html(interText(data.data));
					classTypeId = $('.classifyNavBox .dd_tabList li').eq(0).attr("data-id");
					var _index = $(".nav-wrap li.active").index();
					$('.nav-wrap').navbarscroll({
						fingerClick: 1,
						defaultSelect: _index
					});

					PagenewProductList(classTypeId);
					$('.classifyNavBox .dd_tabList li').click(function() {
						classTypeId = $(this).attr('data-id');
						pageNum = 0;
						$(".prolist2").html("");
						$(this).addClass('active').siblings().removeClass('active');
						PagenewProductList(classTypeId);
					});
				}
			}

		}
		sc.post(url, {
			"parentId": 0
		}, callback);
	}

	function PagenewProductList(Pid) {
		//初始化
		var pageNum = 0,
			pageSize = 10;
		$(".dropload-down").remove();
		$('.ProItem').dropload({
			scrollArea: window,
			domDown: {
				domClass: 'dropload-down',
				domRefresh: '<div class="dropload-refresh">↑上拉加载更多</div>',
				domLoad: '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
				domNoData: '<div class="dropload-noData">已经到底了</div>'
			},
			loadDownFn: function(me) {
				pageNum++;
				newProductList(Pid, pageNum, pageSize, function(data) {
					//var interText = doT.template($("#shopProductListTemp").text());
					if(data.data.length == 0 && !data.isok) {
						me.lock();
						me.noData();
						me.resetload();
						if(pageNum == 1) {
							$(".dropload-down").remove();
							var emptystr = "";
							emptystr += '<div class="emptybox">';
							emptystr += '<div class="iconimg"><img src="/images/icons/empty2.png"/></div>';
							emptystr += '<p class="tips">暂无数据 </p>';
							emptystr += '</div>';
							$('.prolist2').html(emptystr);
						}
					} else if(data.data.length < pageSize) {
						//								setTimeout(function () {
						var interText = doT.template($("#productList").text());
						$(".prolist2")[0].innerHTML += interText(data.data);
						me.lock("down");
						me.noData();
						//								},1000);
						me.resetload();
					} else {
						if(pageNum == 1) {
							$(".prolist2").html("");
						}
						//								setTimeout(function () {
						var interText = doT.template($("#productList").text());
						$(".prolist2")[0].innerHTML += interText(data.data);

						//								},1000);
						me.resetload();
					}
				}, me)
			}
		})

	}

	//产品列表
	function newProductList(Pid, pageNum, pageSize, successCallback, el) {
		var url = 'api/Goods/GoodsList';
		var callback = function(data) {
			console.log(data);
			if(data.code == 0) {
				successCallback(data);
			}

		}
		var error = function(data) {
			el.resetload();
		}
		sc.post(url, {
			"cid": Pid,
			"sortorder": 0,
			"page": pageNum,
			"pageSize": pageSize,
			"newProduct": 1
		}, callback, error);
	}
})