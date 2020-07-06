var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
//倒计时
function countDown(time, hour_elem, minute_elem, second_elem, uu_elem) {
	//if(typeof end_time == "string")
	var end_time = new Date(time).getTime(), //月份是实际月份-1
		//current_time = new Date().getTime(),
		sys_second = (end_time - new Date().getTime());
	var timer = setInterval(function() {
		if(sys_second > 0) {
			sys_second -= 10;
			var day = Math.floor((sys_second / 1000 / 3600) / 24);
			var hour = Math.floor((sys_second / 1000 / 3600) % 24);
			var minute = Math.floor((sys_second / 1000 / 60) % 60);
			var second = Math.floor(sys_second / 1000 % 60);
			var haomiao = Math.floor(sys_second % 1000);
			//day_elem && $(day_elem).text(day+'天');//计算天
			$(hour_elem).text(hour < 10 ? "0" + hour : hour); //计算小时
			$(minute_elem).text(minute < 10 ? "0" + minute : minute); //计算分
			$(second_elem).text(second < 10 ? "0" + second : second); // 计算秒						
			$(uu_elem).text(haomiao); // 计算秒
		} else {
			clearInterval(timer);
		}
	}, 10);
}

function GetRTime(obj) {
	var getEndTime = sc.utils.dateFormat(sc.utils.toDate(obj.attr("data-endtime")), "yyyy/MM/dd hh:mm:ss");
	var EndTime = new Date(getEndTime); //结束时间
	var timer = setInterval(function() {
		var NowTime = new Date(); //当前时间
		//console.log(NowTime);
		var t = EndTime.getTime() - NowTime.getTime();
		if(t > 0) {
			var d = Math.floor(t / 1000 / 60 / 60 / 24); //天
			var h = Math.floor(t / 1000 / 60 / 60 % 24); //时
			var m = Math.floor(t / 1000 / 60 % 60); //分
			var s = Math.floor(t / 1000 % 60); //秒
			if(parseInt(d) < 1) {
				d = "";
			} else {
				d = d + "天";
			}
			if(parseInt(h) < 10) {
				h = "0" + h;
			}
			if(parseInt(m) < 10) {
				m = "0" + m;
			}
			if(parseInt(s) < 10) {
				s = "0" + s;
			}
			obj.text(d + h + ":" + m + ":" + s);
		} else {
			clearInterval(timer);
			obj.text('秒杀结束');
			if(obj.text() == '秒杀结束') {
				obj.parents('.m-pro').addClass("oversell").attr("href", "javascript:;");
			}
		}
	}, 1000);

}

//获取限时时间
var pageNum = 0,
	pageSize = 6;
var starTime = '';
var timeType = 0;//0表示当前，1表示过时，2表示未开始
//判断当前时间是否在两个时间段内
function nowInDateBetwen(d1, d2) {
	//如果时间格式是正确的，那下面这一步转化时间格式就可以不用了
	// var dateBegin = new Date(d1.replace(/-/g, "/"));//将-转化为/，使用new Date
	// var dateEnd = new Date(d2.replace(/-/g, "/"));//将-转化为/，使用new Date
	var dateBegin = new Date(d1); //将-转化为/，使用new Date
	var dateEnd = new Date(d2); //将-转化为/，使用new Date
	var dateNow = new Date(); //获取当前时间
	var beginDiff = dateNow.getTime() - dateBegin.getTime(); //时间差的毫秒数       
	var beginDayDiff = Math.floor(beginDiff / (24 * 3600 * 1000)); //计算出相差天数
	var endDiff = dateEnd.getTime() - dateNow.getTime(); //时间差的毫秒数
	var endDayDiff = Math.floor(endDiff / (24 * 3600 * 1000)); //计算出相差天数       
	if(endDayDiff < 0) { //已过期
		timeType=1;
		return false;
	}
	if(beginDayDiff < 0) { //没到开始时间
		timeType=2;
		return false;
	}
	return true;
}
function FlashSaleTimeList() {
	var url = 'api/Goods/FlashSaleTimeList';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) { //成功	
			if(data.data.length){
				var strhtml = '';			
				for(var i = 0; i < data.data.length; i++) {
					strhtml += '<li data-time="' + data.data[i].StartTime + '">';
					strhtml += '<a href="javascript:;"><span class="time">' + data.data[i].StartTimeStr + '</span><span class="staus">疯抢中</span></a>';
					strhtml += '</li>';
				}			
				$('#timelist').append(strhtml);	
				var _index = $("#timelist li.on").index();
				$('.timeItem').navbarscroll({
					defaultSelect: _index
				});	
				$('#timelist li').each(function() {					
					var time1 = ""; //倒计时时间
					var mydate = new Date().toLocaleDateString(); //当前日期
					var miaoTime1 = $(this).find('.time').text(); //时间1
					var miaoTime2 =$(this).next().find('.time').text();//时间2
					var miaoTime1_1 = miaoTime1 + ":00"; //时间
					var miaoTime2_1 = miaoTime2 + ":00"; //时间
					var d1 = mydate + " " + miaoTime1_1;
					var d3=mydate + " " + miaoTime2_1;
					if(nowInDateBetwen(d1, d3)) {	
						$(this).addClass('on').siblings('li').removeClass('on');
						$(this).find('.staus').text("疯抢中");
						$(this).attr('data-type','0');
						$(this).prevAll('li').attr('data-type','1');
						$(this).prevAll('li').find('.staus').text("已开抢");
						$(this).nextAll('li').attr('data-type','2');
						$(this).nextAll('li').find('.staus').text("即将开始");
						//time1 = d3;
						starTime = $(this).attr('data-time');				
						timeType=$(this).attr('data-type');
						$(".dropload-down").remove();
						$('#prolist2_Div').html("");
						limitdropload(timeType);
					}
					if(timeType==2){
						$(this).find('.staus').text("即将开始");
						$(this).attr('data-type','2');
						$('#timelist li').eq(0).addClass('active').siblings('li').removeClass('active');
						starTime = $('#timelist li').eq(0).attr('data-time');
						$(".dropload-down").remove();
						limitdropload(2);
					}						
					//countDown(time1,".Countdown .hh",".Countdown .mm",".Countdown .ss",".Countdown .uu");
					$(this).click(function() {
						pageNum = 0;
						$('#prolist2_Div').html("");
						$(".dropload-down").remove();
						starTime = $(this).attr('data-time');
						$(this).addClass('active').siblings('li').removeClass('active');
						timeType=$(this).attr('data-type');
						limitdropload(timeType);
					})
				})
				
			}else{
				var emptystr = "";
				emptystr += '<div class="emptybox">';
				emptystr += '<div class="iconimg"><img src="/images/icons/empty2.png"/></div>';
				emptystr += '<p class="tips center">暂无数据 </p>';
				emptystr += '</div>';
				$('#prolist2_Div').html(emptystr);
			}
		}
	}
	sc.get(url, {

	}, callback);
}

//获取限时抢购产品列表
function FlashSaleGoodList(pageNum, pageSize, time, successCallback, el) {
	var url = 'api/Goods/FlashSaleGoodList';
	var callback = function(data) {		
		if(data.code == 0) { //成功						
			successCallback(data);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '数据异常，请重试！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	var error = function(data) {
		el.resetload();
	}
	sc.post(url, {
		"page": pageNum,
		"pageSize": pageSize,
		"timeNum": time
	}, callback, error);
}
//按时间分段加载
function limitdropload(TimeType) {
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
			FlashSaleGoodList(pageNum, pageSize, starTime, function(data) {
				var gettpl = document.getElementById('prolist2_Tpl').innerHTML;
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
						$('#prolist2_Div').html(emptystr);
					}
				} else if(data.data.length < 6) {
//					setTimeout(function() {
						laytpl(gettpl).render(data.data, function(html) {
							//得到的模板渲染到html
							$('#prolist2_Div').append(html);
						});
						$('.m-pro').each(function() {
							var timediv = $(this).find('.endTime');
							GetRTime(timediv);
							if($(this).hasClass('oversell')) {
								$(this).click(function(event) {
									event.preventDefault();
								})
							}
							if(TimeType==2){
								$(this).addClass('nobegin');
								$(this).find('.limit').hide();
//								$(this).click(function(event) {
//									event.preventDefault();
//								})
							}else{
								$(this).removeClass('nobegin');
								$(this).find('.limit').show();
							}
						})
						me.resetload();
//					}, 1000);
					me.lock("down");
					me.noData();
				} else {
					if(pageNum == 1) {
						$("#prolist2_Div").html("");
					}
//					setTimeout(function() {
						laytpl(gettpl).render(data.data, function(html) {
							//得到的模板渲染到html
							$('#prolist2_Div').append(html);
						});
						$('.m-pro').each(function() {
							var timediv = $(this).find('.endTime');
							GetRTime(timediv);
							if($(this).hasClass('oversell')) {
								$(this).click(function(event) {
									event.preventDefault();
								})
							}
						})
						me.resetload();
//					}, 1000);
				}
			}, me);

		}
	})
}