
var userId = sc.utils.getCookieValue("UserId");
var	token = sc.utils.getCookieValue("Token");
var codePage = 1; //二维码列表页码
var codePageSize = 11; //二维码列表页码
var cuntPage=0;//二维码总页数
var isLoad = true;
var thisUrl = window.location.href.split("//")[0] + "//" + window.location.href.split("//")[1].split("/")[0];
$(function() {	
	if(!sc.auth.isLogin()) { //如果已经登录的
		layer.open({
			content: '登录超时，请重新登录!',
			skin: 'msg',
			time: 1 //1秒后自动关闭
		});
		setTimeout(function() {
			location.href = "/login.html";
		}, 1000);
    }
})
//无数据显示模板
function emptytpl(emptydiv) {
	var emptystr = "";
	emptystr += '<div class="emptybox">';
	emptystr += '<div class="iconimg"><img src="/images/icons/empty2.png"/></div>';
	emptystr += '<p class="tips">暂无数据 </p>';
	emptystr += '</div>';
	emptydiv.html(emptystr);
}
// 点击复制指定内容
function copyUrl2() {
	$('#orderNoTxt').select(); // 选择对象
	document.execCommand("Copy"); // 执行浏览器复制命令
}
//拨打电话
function Call(obj) {
	var telnumber = $(obj).attr("data-tel");
	layer.open({
		content: telnumber,
		btn: ['呼叫', '取消'],
		yes: function(index) {
			window.location.href = "tel:" + telnumber;
			layer.close(index);
		},
	})
}

function OrderList(status, pageNum, pageSize, successCallback, el) {
	var url = 'api/Order/OrderList';
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
			el.resetload();
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
			el.resetload();
		}
	}
	var error = function(data) {
		el.resetload();
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Status": status,
		"page": pageNum,
		"PageSize": pageSize,
		"type": 2
	}, callback, error);
}

function OrderPage(status) {
	var pageNum = 0;
	var pageSize = 10;
	//初始化
	$('#Orderlist_Div').html("");
	$(".dropload-down").remove();
	//dropload
	$('#Orderlist').dropload({
		scrollArea: window,
		domDown: {
			domClass: 'dropload-down',
			domRefresh: '<div class="dropload-refresh">↑上拉加载更多</div>',
			domLoad: '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
			domNoData: '<div class="dropload-noData">已经到底了</div>'
		},
		loadDownFn: function(me) {
			pageNum++;
			OrderList(status, pageNum, pageSize, function(data) {
				var gettpl = document.getElementById('Orderlist_Tpl').innerHTML;
				if(data.data.length == 0 && !data.isok) {
					me.lock();
					me.noData();
					me.resetload();
					if(pageNum == 1) {
						$(".dropload-down").remove();
						emptytpl($("#Orderlist_Div"));
						//                      clickCode()
					}
				} else if(data.data.length < pageNum) {
					//setTimeout(function () {
					laytpl(gettpl).render(data.data, function(html) {
						//得到的模板渲染到html
						$('#Orderlist_Div').append(html);
						//								clickCode()
					});

					//},1000);
					me.lock("down");
					me.noData();
					me.resetload();
				} else {
					if(pageNum == 1) {
						$("#Orderlist_Div").html("");
					}
					//setTimeout(function () {
					laytpl(gettpl).render(data.data, function(html) {
						//得到的模板渲染到html
						$('#Orderlist_Div').append(html);
						//							clickCode();
					});
					me.resetload();
					//},1000);
				}
			}, me)
		}
	});


    var source = sc.utils.getQueryString("source");
    if (!sc.utils.isNullOrEmpty(source) && source == "pay") {
        $(".fcrs").show();
    }
    $(".know").click(function () {
        $(".fcrs").hide();
    });
}

function clickEWM(obj) {    
    codePage = 1;
    var order_no = $(obj).parents(".orderitem").attr("data-no");
    var expiretime = $(obj).parents(".orderitem").attr("data-expiretime");
    var email = $(obj).parents(".orderitem").attr("data-email");
    var proImg = $(obj).parents(".orderitem").attr("data-proImg") + "?v=" + new Date();
    var isExpire = $(obj).parents(".orderitem").attr("data-IsExpire");
   
    var img = new Image();
    img.src = proImg;
    img.setAttribute("crossOrigin", 'Anonymous')
    $(".proImg").attr("src", img.src);
    
    img.onload = function (e) {
        e.stopPropagation();
        // 执行合成流程
        $("body").addClass("scrollFix");
        $(".zhezhao").show();
        GetGiftCode(obj, order_no, expiretime, email);
    }
    img.onerror = function (e) { //图片未下载下来，使用默认图        
        $(".proImg").attr("src", "/images/noPic.jpg");
        e.stopPropagation();
        // 执行合成流程
        $("body").addClass("scrollFix");
        $(".zhezhao").show();
        GetGiftCode(obj, order_no, expiretime, email, isExpire);
    }
   
}



function CloseCeng()
{
    layer.closeAll() //关闭所有层
    $("body").removeClass("scrollFix");
    $(".zhezhao").hide();
    $(".expiredv").hide();
    $(".codeDiv").show();
    $(".mychaoemail").html("").hide();
    $('#canvaslist').html("");
    $("#mycodelist").html("");
    //$(".codeDiv").css("background", "url(\"../images/codebg.png\")");
}
//获取二维码列表**********************/
var currentObj = null;
function GetGiftCode(obj, order_no, expiretime, email, isExpire) {	
    currentObj = obj;
	var url = "api/Gift/GetGiftCode";
	var callback = function(data) {
		if (data.count >= codePageSize) {
	        if (data.count % codePageSize == 0) {
	          cuntPage = data.count / codePageSize;
	        } else {
	          cuntPage = parseInt(data.count / codePageSize) + 1;
	        }
        }        
        if (isExpire == "true") {
            $(".expiredv").show();
            $(".pro-txt1").html("您的兑换礼包已过期！").addClass("gqtext");
        } else {
            $(".expiredv").hide();
            $(".pro-txt1").html("<div>长按二维码保存到本地(左右滑动查看更多)</div>").removeClass("gqtext");
        }
       
        $(".pop-valid").html("有效期：" + expiretime);
        $(".codeDiv").show();
        if (data.data.length > 10) {
            $(".page2").hide();
            $(".page1").show();
            var str = '';
            str += '<div class="chao10"><div class="eml">您的订单已超过10份,请输入邮箱领取全部礼包二维码</div>';           
            str += '<input onblur="settop()" onclick="inputclick()" class="input" type="text" placeholder="请输入邮箱" id="qrcodeByEmail" name="qrcodeByEmail" value="' + email + '">';
            if (isExpire == "true") {
                str += '<div class="chaoqi1"><div class="chaoqi2">您的兑换礼包已过期！</div></div>';
            }
            if (email == "")
                str += '<div class="tijiao" onclick="SendEmail(\'' + order_no + '\')">提 交</div>';
            else
                str += '<div class="tijiao" onclick="SendEmail(\'' + order_no+'\')">再次提交</div>';
            str += "</div>";
            $("#fcode").hide();
            $(".mychaoemail").html(str).show();  //
            
        } else {
            for (var i = 0; i < data.data.length; i++) {
                var _this = $(this);
                var thisCode = "qrcode" + data.data[i].Id;
                var receiveNo = data.data[i].ReceiveNo;
                var id = data.data[i].Id;
                var elText = thisUrl + "/receiveGit.html?receiveNo=" + receiveNo + "&id=" + id;

                $('#canvaslist').qrcode({
                    render: "canvas",
                    text: elText
                }, "canvas" + thisCode);

                CreateDrawImg($('#canvaslist canvas').eq(i).attr("id"), (i + 1));

                if (i == data.data.length-1) {
                    $(".page2").fadeIn(100);
                    $(".pro-txt1").fadeIn(100);
                    $(".page1").hide();
                   // $(".codeDiv").css("background","none");
                }
            }
            
            var mySwiper = new Swiper('.swiper-container', {
                observer: true,
                observeParents: true,
                navigation: {
                    nextEl: '.swiper-button-next',
                    prevEl: '.swiper-button-prev',
                }
            });
        }      		
	}
    sc.post(url, {
        UserId: userId,
        Token: token,
        OrderNo: order_no,
        page: codePage,
        pageSize: codePageSize
    }, callback);
}
var stop = 0;
function settop() {
    $(window).scrollTop(stop); 
    if (isAndroid() == true) {
        $(".redv").css("bottom", "0");
    }
}
function inputclick() {
    stop = $(window).scrollTop();
    if (isAndroid() == true) {
        $(".redv").css("bottom", "40%");
    }
}

document.onkeydown = function (event) {
    let key = event.keyCode;
    if (key == 13) {
        document.getElementById('qrcodeByEmail').blur(); // ProjectName 为事件触发的函数
    }
}

var me = this
var oriWinHeight = window.innerHeight
window.onresize = function () {
    me.isKeybordAvail = true
    let newHeight = window.innerHeight
    // 阀值大于140判断为键盘收起
    if (newHeight - oriWinHeight > 140) {
        document.getElementById("qrcodeByEmail").blur();
    }
    oriWinHeight = newHeight
}

// 判断安卓
function isAndroid() {
    var u = navigator.userAgent;
    if (u.toLowerCase().indexOf("android") > -1 || u.indexOf("linux") > -1) {
        return true;
    }
    return false;
}

//发送邮件
function SendEmail(order_no) {
    var idx=layer.open({
        type: 2
        , content: '邮件发送中...'
    });    
    var qrcodeByEmail = $.trim($("#qrcodeByEmail").val());
    var reg = /^\w+@[a-zA-Z0-9]{2,10}(?:\.[a-z]{2,4}){1,3}$/;
    if (reg.test(qrcodeByEmail)) {//保存邮箱
        var callback = function (data) {
            layer.close(idx);
            $(currentObj).parents(".orderitem").attr("data-email", qrcodeByEmail);
            layer.open({
                type: 0,
                content: data.msg,
                skin: 'msg',
                time: 2 //2秒后自动关闭
            });
            setTimeout(function () {
                CloseCeng();
            }, 2500);
        }
        var url = "api/Gift/UpdateQrCodeEmail";
        sc.post(url, {
            UserId: userId,
            Token: token,
            OrderNo: order_no,
            QrcodeByEmail: qrcodeByEmail,
            ProductImg: $(".proImg").attr("src")
        }, callback);
    } else {
        layer.open({
            type: 0,
            content: '邮箱格式不正确',
            skin: 'msg',
            time: 2 //2秒后自动关闭
        });
    }
}
function CreateDrawImg(CanvasId, idx) {    
    var drawing = document.getElementById(CanvasId);
    var context = drawing.getContext('2d');
    var imgURI = drawing.toDataURL("image/png");
    var image = document.createElement("img");
    image.src = imgURI;
    $("#currentImg").attr("src", image.src);
    //生成编号
    $("#fcode .txts").html(idx);
    var shareContent = document.querySelector('#fcode');
    var width = shareContent.offsetWidth;
    var height = shareContent.offsetHeight;
    var canvas = document.createElement("canvas");
    var scale = 2;
    canvas.width = width * scale;
    canvas.height = height * scale;
    canvas.getContext("2d").scale(scale, scale);
    var rect = shareContent.getBoundingClientRect();//获取元素相对于视察的偏移量
    canvas.getContext("2d").translate(-rect.left, -rect.top);
    var opts = {
        dpi: window.devicePixelRatio * 2,
        scale: scale,
        canvas: canvas,
        logging: true,
        width: width,
        height: height,
        useCORS: true
    };
    html2canvas(shareContent, opts).then(function (canvas) {
        var img = Canvas2Image.convertToImage(canvas, canvas.width, canvas.height);        
        $("#mycodelist").append(img);
        $(img).css({
            "width": canvas.width / 2 + "px",
            //"height":"auto", //canvas.height / 2 + "px",
        });
        $(img).addClass("swiper-slide");
    });
}
function convertCanvasToImage(canvas) {
    var image = new Image();
    image.src = canvas.toDataURL("image/png");
    return image;
}
//修改二维码发送状态
function SendGiftCode(id) {
	var url = "api/Gift/SendGiftCode";
	var callback = function(data) {
		console.log(data)
	}
	sc.post(url, {
		UserId: userId,
		Token: token,
		Id: id
	}, callback)
}

function OrderPageint() {
	//0全部 1 待付款；2 待发货 ,已付款，3 已发货；4 待评论，已收货；5 退货中的订单
	var orderType = sc.utils.getQueryString("orderType");
	if(orderType) {
		$('.dd_tabList li').each(function() {
			var atype = $(this).attr('data-type');
			if(atype == orderType) {
				$(this).addClass('active').siblings().removeClass('active');
			}
		})
		OrderPage(orderType);
	} else {
		OrderPage(0);
	}
	//	$('.dd_tabList li').click(function() {
	//		$('#Orderlist_Div').html("");
	//		$(".dropload-down,.dropload-up").remove();
	//		var status = $(this).children('a').attr('data-type');
	//		$(this).addClass('active').siblings().removeClass('active');
	//		OrderPage(status);
	//	})
}
//获取订单详情
function OrderDetails(No, successCallback) {
	var url = 'api/Order/OrderDetails';
	var callback = function(data) {
		if(data.code == 0) { //数据异常
			successCallback(data);
			console.log(data);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '数据异常,请重试！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No
	}, callback);
}
//获取订单列表中Item项的详情
function GetOrderListItemDetail(No, PId, successCallback) {
	var url = 'api/Order/GetOrderListItemDetail';
	var callback = function(data) {
		if(data.code == 0) { //数据异常
			successCallback(data);
			console.log(data);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '数据异常,请重试！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"ProductId": PId
	}, callback);
}

//取消订单接口
function CancelOrders(No, Reason) {
	var url = 'api/Order/CancelOrders';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '订单已取消！',
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			setTimeout(function() {
				layer.closeAll();
				location.reload();
			}, 2000);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"ReMarks": Reason
	}, callback);
}
//取消订单原因，退款原因
function CancelReason(obj) {
	var url = 'api/Order/CancelReason';
	var callback = function(data) {
		if(data.code == 0) { //成功
			var opt = "";
			for(var i = 0; i < data.data.length; i++) {
				opt += '<li data-code="' + data.data[i].code + '">' + data.data[i].message + '</li>';
			}
			obj.append(opt);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '数据异常，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.get(url, {}, callback);
}
//取消订单弹出
function CancelBtn(obj) {
	if($('.footerBtn').length) {
		var self = $(obj).parents('.footerBtn');
	} else {
		var self = $(obj).parents('.orderitem');
	}
	var orderNo = self.attr('data-no');
	var str = '<div class="pop-Reason">';
	str = '<div class="pop-hd center c_333">请选择取消订单原因</div>';
	str += '<ul class="wenList center reasonList">';
	str += '</ul>';
	str += '</div>';
	layer.open({
		type: 1,
		content: str,
		anim: 'up',
		style: 'position:fixed; bottom:0; left:0; width: 100%;'
	});
	CancelReason($('.reasonList'));
	$('.reasonList').on('click', 'li', function() {
		$(this).addClass('active').siblings().removeClass('active');
		var Reason = $(this).text();
		CancelOrders(orderNo, Reason);
		setTimeout(function() {
			layer.closeAll();
		}, 2000);
	})
}

//获取退货原因
function GetRefundReason(obj) {
	var url = 'api/Order/GetRefundReason';
	var callback = function(data) {
		if(data.code == 0) { //成功
			var opt = "";
			for(var i = 1; i < data.data.length; i++) {
				opt += '<li data-code="' + data.data[i].code + '">' + data.data[i].message + '</li>';
			}
			obj.append(opt);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '数据异常，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.get(url, {}, callback);
}
//提交退货申请（整单退）
function SubmitRefund(No, ContentTxt, Reason) {
	var url = 'api/Order/SubmitRefund';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '退货申请成功!',
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/member/order.html?orderType=5";
			}, 2000);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"RefundContent": ContentTxt,
		"RefundReasonId": Reason
	}, callback);
}
//单个产品退货申请
function SubmitRefundSingle(No, Pids, ContentTxt, Reason) {
	var url = 'api/Order/SubmitRefundSingle';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) { //成功
			layer.open({
				content: '退货申请成功!',
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/member/order.html?orderType=5";
			}, 2000);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"OrderItemId": Pids,
		"RefundContent": ContentTxt,
		"RefundReasonId": Reason
	}, callback);
}
//提交退款申请（已付款，商家未发货）
function SubmitReturn(No, ContentTxt, Reason) {
	var url = 'api/Order/SubmitReturn';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '退款申请成功!!',
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/member/order.html?orderType=5";
			}, 1100);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"RefundContent": ContentTxt,
		"RefundReasonId": Reason
	}, callback);
}

//单个产品退款申请
function SubmitReturnSingle(No, Pids, ContentTxt, Reason) {
	var url = 'api/Order/SubmitReturnSingle';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) { //成功
			layer.open({
				content: '退款申请成功!',
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/member/order.html?orderType=5";
			}, 2000);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"OrderItemId": Pids,
		"RefundContent": ContentTxt,
		"RefundReasonId": Reason
	}, callback);
}

//撤销退货退款申请
function CanelRefund(No) {
	var url = 'api/Order/CanelRefund';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '撤销申请成功!',
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			setTimeout(function() {
				location.reload();
			}, 1000);
		}
		if(data.code == 1) { //失败
			layer.open({
				content: '操作失败,请稍后重试~',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
	}, callback);
}
//点击撤销退货退款申请
function CanelRefundBtn(obj) {
	if($('.footerBtn').length) {
		var self = $(obj).parents('.footerBtn');
	} else {
		var self = $(obj).parents('.orderitem');
	}
	var orderNo = self.attr('data-No');
	CanelRefund(orderNo);
}
//删除订单接口
function DeleteOrders(No, successCallback) {
	var url = 'api/Order/DeleteOrders';
	var callback = function(data) {
		if(data.code == 0) { //成功			
			successCallback(data);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '删除失败，请重试！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No
	}, callback);
}
//删除订单
function DelOrderBtn(obj) {
	if($('.footerBtn').length) {
		var self = $(obj).parents('.footerBtn');
	} else {
		var self = $(obj).parents('.orderitem');
	}
	var orderNo = self.attr('data-No');
	DeleteOrders(orderNo, function(data) {
		layer.open({
			content: '订单已删除！',
			skin: 'msg',
			time: 1 //1秒后自动关闭
		});
		if($('.footerBtn').length) {
			setTimeout(function() {
				location.href = "/member/order.html?orderType=0";
			}, 1000);
		} else {
			self.remove();
		}
	});
}
//确认收货接口
function ConfirmReceipt(No) {
	var url = 'api/Gift/updateGift';
	var callback = function(data) {
		console.log(data)
		if(data.code == 0) { //成功
			layer.open({
				content: '确认收货成功！',
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			setTimeout(function() {
				location.reload();
			}, 2000);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '订单异常，请联系客服！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"Id": No
	}, callback);
}
//点击确认收货方法
function ReceiptBtn(obj) {
	if($('.footerBtn').length) {
		var self = $(obj).parents('.footerBtn');
	} else {
		var self = $(obj).parents('.orderitem');
	}
	var orderNo = self.attr('data-Id');
	ConfirmReceipt(orderNo);
}

//提醒发货接口
function Remind(No) {
	var url = 'api/Order/Remind';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: '提醒卖家成功！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code === 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No
	}, callback);
}
//提醒发货方法
function RemindBtn(obj) {
	if($('.footerBtn').length) {
		var self = $(obj).parents('.footerBtn');
	} else {
		var self = $(obj).parents('.orderitem');
	}
	var orderNo = self.attr('data-No');
	Remind(orderNo);
}

//获取物流详细信息
function GetLogistics(No) {
	var url = 'api/Order/GetLogistics';
	var callback = function(data) {
		if(data.code == 0) { //成功
			var jsonData = JSON.parse(data.data);
			if(jsonData.com) {
				$('#expressNo').html('' + jsonData.com + ': ' + jsonData.nu + '');
			}
			var str = "";
			str += '<ul class="logisticsList">';
			if(jsonData.returnCode == 500 || jsonData.returnCode == 400) {
				str += '<li>';
				str += '<span class="dian"></span>';
				str += '<dl>';
				str += '<dt></dt>';
				str += '<dd>' + jsonData.message + '</dd>';
				str += '</dl>';
				str += '</li>';
			} else {
				for(var i = 0; i < jsonData.data.length; i++) {
					if(i == 0) {
						str += '<li class="active">';
					} else {
						str += '<li>';
					}
					str += '<span class="dian"></span>';
					str += '<dl>';
					str += '<dt><span class="time">' + jsonData.data[i].time + '</span></dt>';
					str += '<dd>' + jsonData.data[i].context + '</dd>';
					str += '</dl>';
					str += '</li>';
				}
			}
			str += '</ul>';
			$('.logistics').append(str);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '商家还没有填写发货物流信息！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No
	}, callback);
}
//获取物流公司名称
function GetExpressCompanyList(obj) {
	var url = 'api/Order/GetExpressCompanyList';
	var callback = function(data) {
		var result = JSON.parse(data.data);
		if(data.code == 0) { //成功
			var opt = "";
			for(var i = 0; i < result.length; i++) {
				opt += '<option value="' + result[i].Id + '">' + result[i].company + '</option>';
			}
			obj.append(opt);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '数据异常，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.get(url, {}, callback);
}
//提交退货快递信息
function SubmitRefundExpress(No, ExpressName, ExpressNo) {
	var url = 'api/Order/SubmitRefundExpress';
	var callback = function(data) {
		console.log(data);
		if(data.code == 0) { //成功
			layer.open({
				content: '寄回信息提交成功！',
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			//			setTimeout(function() {
			//				layer.closeAll();
			//			}, 2000);			
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '数据异常,请重试！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"ExpressName": ExpressName,
		"ExpressNo": ExpressNo
	}, callback);
}

//提交退货快递信息验证
function VerifyExpress(ExpressName, ExpressNo) {
	var txt_ExpressName = $.trim(ExpressName),
		txt_ExpressNo = $.trim(ExpressNo);
	if(txt_ExpressName == '0') {
		layer.open({
			content: '请选择物流公司！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	if(!ExpressNo) {
		layer.open({
			content: '请输入物流单号！',
			skin: 'msg',
			time: 1 //2秒后自动关闭
		});
		return false;
	}
	return true;
}
//评论商品
function CommentProduct(No, Pid, Piclist, Text, PicNo, Star) {
	var url = 'api/Order/CommentProduct';
	var callback = function(data) {
		if(data.code == 0) { //成功
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 1 //2秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/member/order.html?orderType=4";
			}, 1100);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No,
		"Pid": Pid,
		"PicList": Piclist,
		"Content": Text,
		"PicNo": PicNo,
		"Starnum": Star
	}, callback);
}
//获取售后订单详情
function RefundOrderInfo(No, successCallback) {
	var url = "api/Order/RefundOrderInfo";
	var callback = function(data) {
		if(data.code == 0) { //数据异常
			successCallback(data);
			console.log(data);
		}
		if(data.code == 1) { //数据异常
			layer.open({
				content: '订单不存在！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
		if(data.code == 2) { //登录超时
			layer.open({
				content: '登录超时，请重新登录!',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code == 99) { //数据异常
			layer.open({
				content: '服务器开小差了，请稍后！',
				skin: 'msg',
				time: 2 //2秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"OrderNo": No
	}, callback);
}