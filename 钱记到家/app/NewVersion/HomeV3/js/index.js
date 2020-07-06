var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
var id = "";
var pageIndex = 1; //当前页
var pageSize = 8;
//随机滚动的假订单数据
var fakeDate = [{
    'txPic': '/images/tx/f_1.jpg',
    'name': 'Lidan',
    'time': 1
}, {
    'txPic': '/images/tx/f_2.jpg',
    'name': '天使***',
    'time': 2
}, {
    'txPic': '/images/tx/f_3.jpg',
    'name': 'andiy',
    'time': 3
}, {
    'txPic': '/images/tx/f_4.jpg',
    'name': '美丽***',
    'time': 4
}, {
    'txPic': '/images/tx/f_5.jpg',
    'name': 'LiLi',
    'time': 5
}, {
    'txPic': '/images/tx/f_6.jpg',
    'name': '少女**',
    'time': 3
}, {
    'txPic': '/images/tx/f_7.jpg',
    'name': '萝莉病',
    'time': 3
}, {
    'txPic': '/images/tx/f_8.jpg',
    'name': '长安*落花',
    'time': 2
}, {
    'txPic': '/images/tx/f_9.jpg',
    'name': '猫性**',
    'time': 6
}, {
    'txPic': '/images/tx/f_10.jpg',
    'name': '云朵*甜',
    'time': 6
}, {
    'txPic': '/images/tx/f_11.jpg',
    'name': '尘世美',
    'time': 5
}, {
    'txPic': '/images/tx/f_12.jpg',
    'name': '沐小悠',
    'time': 7
}, {
    'txPic': '/images/tx/f_13.jpg',
    'name': '苏陌染',
    'time': 7
}, {
    'txPic': '/images/tx/f_14.jpg',
    'name': '花开宿语',
    'time': 16
}, {
    'txPic': '/images/tx/f_15.jpg',
    'name': '1克拉**',
    'time': 8
}, {
    'txPic': '/images/tx/f_15.jpg',
    'name': '1克拉**',
    'time': 8
}, {
    'txPic': '/images/tx/f_16.jpg',
    'name': '风月*闲',
    'time': 8
}, {
    'txPic': '/images/tx/f_17.jpg',
    'name': '浣歌',
    'time': 11
}, {
    'txPic': '/images/tx/f_18.jpg',
    'name': '徒旅暮',
    'time': 4
}, {
    'txPic': '/images/tx/f_19.jpg',
    'name': '当个祖宗',
    'time': 3
}, {
    'txPic': '/images/tx/f_20.jpg',
    'name': '天涯浪人',
    'time': 3
}, {
    'txPic': '/images/tx/f_21.jpg',
    'name': '清洒弧幻',
    'time': 2
}, {
    'txPic': '/images/tx/f_22.jpg',
    'name': '沾染**气',
    'time': 10
}, {
    'txPic': '/images/tx/f_23.jpg',
    'name': '流光**年华',
    'time': 10
}, {
    'txPic': '/images/tx/f_24.jpg',
    'name': '回忆**香',
    'time': 9
}, {
    'txPic': '/images/tx/f_25.jpg',
    'name': '二货**萌',
    'time': 3
}, {
    'txPic': '/images/tx/f_26.jpg',
    'name': '染指徒留',
    'time': 4
}, {
    'txPic': '/images/tx/f_27.jpg',
    'name': '怀揣**梦',
    'time': 5
}, {
    'txPic': '/images/tx/f_28.jpg',
    'name': '舞动**魂',
    'time': 5
}, {
    'txPic': '/images/tx/f_29.jpg',
    'name': '魅惑**眼',
    'time': 8
}, {
    'txPic': '/images/tx/f_30.jpg',
    'name': '南故笙烟',
    'time': 6
}];

function radomShowInfo() {
    var index = parseInt(Math.random() * (fakeDate.length - 1));
    var data = fakeDate[index];
    var str = '<div class="tx"><img src="' + data.txPic + '" /></div><p class="text">最新订单来自' + data.name + ',' + data.time + '分钟前</p>';
    $('.neworder').append(str);
    setTimeout(function () {
        $('.neworder').html('');
    }, 6000);
}
//var show = true;
$(function () {
    setInterval(radomShowInfo, parseInt(Math.random() * 120000 + 60000));

    // 底部调用
    $("#index-footer").load("footerV.html");
    $("#distribution").click(function () {
        IsOwner($(this));
    })
    //充值有礼、每日签到、邀请有礼都需要登录之后才能进入	
    $('#gotoRecharge').click(function () {
        if (!sc.auth.isLogin()) {
            layer.open({
                content: '您还没有登录，是否登录?',
                btn: ['确定', '取消'],
                yes: function (index) {
                    location.href = '/login.html';
                    layer.close(index);
                },
                no: function (index) {
                    layer.close(index);
                }

            });

        } else {

            location.href = '/Recharge.html';
        }
    });
    $('#gotoInvite').click(function () {
        if (!sc.auth.isLogin()) {
            layer.open({
                content: '您还没有登录，是否登录?',
                btn: ['确定', '取消'],
                yes: function (index) {
                    location.href = '/login.html';
                    layer.close(index);
                },
                no: function (index) {
                    layer.close(index);
                }

            });

        } else {
            //获取用户信息
            location.href = '/Invite.html';
        }
    });


    if (!sc.auth.isLogin()) {
        $('.icon-menu-news').click(function () {
            layer.open({
                content: '您还没有登录，是否登录?',
                btn: ['确定', '取消'],
                yes: function (index) {
                    location.href = '/login.html';
                    layer.close(index);
                },
                no: function (index) {
                    layer.close(index);
                }
            });
        });
    } else {
        //获取用户信息
        $('.icon-menu-news').click(function () {
            location.href = '/Message.html';
        })
        noReadNewNum();
    }
    //获取未读消息的总数
    function noReadNewNum() {
        var url = 'api/News/NewsCount';
        var callback = function (data) {
            if (data.code === 0) {
                if (data.count > 0) {
                    $('.icon-menu-news').html('<span>' + data.count + '</span>');
                }
                if (data.count > 99) {
                    $('.icon-menu-news').html('<span>99+</span>');
                }
            }
        }
        sc.post(url, {
            "UserId": userId,
            "Token": token
        }, callback);
    }

    //	$('#productListBox').load("productListTemp.html");
    //		选择城市
    //	$("#citypick").click(function() {
    ////		citypicker();
    //	})
    //		清除搜索框文字
    $('input.search').on('focus', function () {
        $('.search-reset').show();
    });
    $('.search-reset').click(function () {
        $('input.search').val('');
        $('.search-reset').hide();
    });
    //scDrag($("#redBag"));
    $("#redBag").click(function () {
        if (sc.auth.isLogin()) {
            location.href = '/member/receiveCoupon.html';
        } else {
            layer.open({
                content: '您还没有登录，是否登录?',
                btn: ['确定', '取消'],
                yes: function (index) {
                    location.href = '/login.html';
                    layer.close(index);
                },
                no: function (index) {
                    layer.close(index);
                }

            });
        }
    });

    //点击搜素的时候跳到搜素页面
    $('#gotoSearchProduct').click(function () {
        location.href = '/searchProduct.html'
    });

    getBanner(1); //banner
    //getBanner(3);
    getNews(); //系统消息滚动
    goodsList('', '', '', '', 1, '', 'j', 1); //爆款直降,搜推荐
    goodsList('', '', '', 1, '', '', 's', 0); //当季新品



});

//判断是否开通小店
function IsOwner(obj) {
    var url = 'api/User/GetMemberInfo';
    var callback = function (data) {
        if (data.code == 0) { //成功
            var Ownerstatus = data.data.OwnerStatus;
            obj.attr('data-status', Ownerstatus);
            if (!sc.auth.isLogin()) {
                layer.open({
                    content: '您还没有登录，是否登录?',
                    btn: ['确定', '取消'],
                    yes: function (index) {
                        location.href = '/login.html';
                        layer.close(index);
                    },
                    no: function (index) {
                        layer.close(index);
                    }
                });
            } else {
                if (Ownerstatus === 0) {
                    location.href = '/member/setUpShop.html';
                } else if (Ownerstatus === 1) {
                    location.href = '/member/applysuccess.html';
                } else if (Ownerstatus === 2) { //已开通小店
                    location.href = '/member/subShopIndex.html';
                } else {
                    location.href = '/member/applysuccess.html';
                }
            }
        }
        if (data.code == 2) { //登录超时
            layer.open({
                content: data.msg,
                skin: 'msg',
                time: 1 //1秒后自动关闭
            });
            setTimeout(function () {
                location.href = "/login.html";
            }, 1000);
        }
    }
    sc.post(url, {
        "UserId": userId,
        "Token": token
    }, callback);
}
function Scroll() { }
Scroll.prototype.upScroll = function (dom, _h, interval) {
    var dom = document.getElementById(dom); var timer = setTimeout(function () { var _field = dom.firstElementChild; _field.style.marginTop = _h; clearTimeout(timer); }, 1000)
    setInterval(function () {
        var _field = dom.firstElementChild; _field.style.marginTop = "0px"; dom.appendChild(_field); var _field = dom.firstElementChild
        _field.style.marginTop = _h;
    }, interval)
}
var myScroll = new Scroll();
//系统消息
function getNews() {
    var url = "/api/News/NoticeList";
    var calback = function (data) {
        if (data.code == 0) {
            var interText = doT.template($("#SystemNews").text());
            $("#noticelist").html(interText(data));
            myScroll.upScroll("noticelist", "-34px", 3000);
        }
    }
    sc.post(url, {
        "pagesize": 20,
        "page": 1,
        "type": 0
    }, calback)
}

function getBanner(type) {
    var url = "/api/Banner/BannerList";
    var calback = function (data) {
  
        if (data.code == 0) {
            if (type === 1) {
                //首页轮播banner
                var interText = doT.template($("#bannerTemp").text());
                $("#bannerImg").html(interText(data.data));
                var olText = doT.template($("#bannerolTemp").text());
                $("#bannerol").html(olText(data.data));
            
            }
            if (type === 3) {
                //首页产品广告banner
                var interText2 = doT.template($("#proBannerTemp").text());
                $(".proBanner").html(interText2(data.data));
            }
        }
    }
    sc.post(url, {
        "type": type
    }, calback);
}

function goodsList(Pid, hot, recommend, newProduct, explosive, searchkey, sortname, sortorder) {
    var url = 'api/Goods/GoodsList';
    var callback = function (data) {
        if (data.code == 0) {
            if (data.data.length > 0) {
                if (explosive) {
                    var arrText = doT.template($("#productList").text()); //爆款直降
               
                    $("#productListBox")[0].innerHTML += arrText(data.data);
                } else if (newProduct) {
                    //当季新品
                    var arrText2 = doT.template($("#NewproductList").text()); //当季新品
                    $("#productListBox2")[0].innerHTML += arrText2(data.data);
                }

            }
        } else if (data.code == 99) {
            layer.open({
                content: '服务器开了点小差，请重新刷新!',
                skin: 'msg',
                time: 2 //2秒后自动关闭
            });
        }
    }
    var error = function (data) {

    }
    sc.post(url, {
        "cid": Pid,
        "page": pageIndex,
        "pageSize": pageSize,
        "hot": hot,
        "recommend": recommend,
        "newProduct": newProduct,
        "explosive": explosive,
        "searchkey": searchkey,
        "sortname": sortname, //j为按价格，s为时间排序
        "sortorder": sortorder //0为倒叙，1为升序
    }, callback, error);
}