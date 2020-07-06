var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");

var activityId = 0;  
var timer = null;
var flag = true;
var step = 100; //转动速度
var count = 0;
var stop = false;
var lottery = {
    Init: function () {
        //加载奖项
        var url = 'api/User/LotteryConfigList';
        var callback = function (data) {
            $(".loading").hide();
            if (data.code == 0) { //成功     
                if (data.data == "") {
                    layer.open({
                        content: "<div id='toast' class='toast'>活动已结束！</div>",
                        shade: 0,
                        skin: 'myskin'
                    });
                    return false;
                }
                activityId = data.data[0].ActivityId;
                var arrText = doT.template($("#lotteryconfiglist").text());
                $("#lotteryconfigBox")[0].innerHTML += arrText(data.data);                
                if (data.msg == "ok") {
                    lottery.Draw();
                    lottery.LoanTimes();
                    lottery.DM();
                    //活动规则
                    $("#activitytime").html(data.Data.activitytime);
                    $("#activityremark").html(data.Data.activityremark);
                    $("#activities,.rulebg").show();
                    $("#close").click(function () {
                        $("#activities,.rulebg").fadeOut(800);
                    });
                } else {
                    layer.open({
                        content: "<div id='toast' class='toast'>您不具备资格！</div>",
                        shade: 0,
                        skin: 'myskin'
                    });
                    return false;
                }
            } else {
                layer.open({
                    content: "<div id='toast' class='toast'>" + data.msg + "</div>",
                    shade: 0,
                    skin: 'myskin'
                });
            }
        }
        sc.post(url, {
            "UserId": userId,
            "Token": token,
            "type": 3
        }, callback);
    },
    Draw: function () {
        $('#lottery_btn').on("click", function () {
            if (!flag) return;
            var _t = parseInt($("#times").html());
            if (_t <= 0) {
                layer.open({
                    content: "<div id='toast' class='toast'>您当前没有多余的抽奖次数！</div>",
                    shade: 0,
                    time: 2,
                    skin: 'myskin'
                });
                $('.item').removeClass('active');
                return;
            } else {
                count = 0;
                flag = false;
                var url = 'api/User/LotteryDraw';
                var callback = function (data) {
                    //console.log(data);
                    var level = data.data.level;
                    if (data.code == 0) { //成功
                        stop = true;
                    } else {
                        stop = true;
                        layer.open({
                            content: "<div id='toast' class='toast'>" + data.msg + "</div>",
                            shade: 0,
                            time: 2,
                            skin: 'myskin'
                        });
                        if (data.code == -1) { //登录超时
                            setTimeout(function () {
                                location.href = "/login.html";
                            }, 1000);
                        }
                    }
                    clearInterval(timer);
                    timer = setInterval(function () {
                        $('.item').removeClass('active');
                        $('.item').eq(count % 8).addClass('active');
                        if (stop) {
                            if ((data.code < 0 || (data.code == 0 && count > 40)) && (count % 8) == (level-1)) { //保证至少转了5个来回 5*8=40,并且正好转到当前位置
                                flag = true;
                                clearInterval(timer);
                                $('.item').removeClass('active');
                                if (level > 0) {
                                    stop = false;
                                    $('.item').eq(level - 1).addClass('active');
                                    if (level != 7) {
                                        layer.open({
                                            content: "<div id='toast' class='toast'>恭喜您获得" + data.data.lotteryname + "，请到我的奖品处点击领取</div>",
                                            shade: 0,
                                            skin: 'myskin',
                                            time: 2
                                        });
                                    } else {
                                        layer.open({
                                            content: "<div id='toast' class='toast'>没有中奖，再接再励！</div>",
                                            shade: 0,
                                            time: 2,
                                            skin: 'myskin'
                                        });
                                    }
                                    var _t = parseInt($("#times").html());
                                    $("#times").html(_t - 1);
                                }
                            }
                        }
                        count++;
                    }, step);
                }
                sc.post(url, {
                    "UserId": userId,
                    "Token": token,
                    "type": 3,
                    "ActivityId": activityId
                }, callback);
            }
        });
    },
    LoanTimes: function () {
        //剩余抽奖次数
        var url = 'api/User/GetDrawTimes';
        var callback = function (data) {
            if (data.code == 0) { //成功      
                $(".num").show();
                $("#times").html(data.data);
            }
            if (data.code == -1) { //登录超时
                setTimeout(function () {
                    location.href = "/login.html";
                }, 1000);
            }
        }
        sc.post(url, {
            "UserId": userId,
            "Token": token,
            "type": 3
        }, callback);
    },
    DM: function () {
        //弹幕
        var url = 'api/User/LotteryerList';
        var callback = function (data) {
            var h = $(window).height();            
            if (data.code == 0) { //成功  
                $.ajaxSettings.async = false;
                //每条弹幕发送间隔
                var looper_time = 3 * 1000;
                var items = data.data;
                //弹幕总数
                var total = data.data.length;
                //是否首次执行
                var run_once = true;
                //弹幕索引
                var index = 0;
                //先执行一次
                barrager();
                function barrager() {
                    if (run_once) {
                        //如果是首次执行,则设置一个定时器,并且把首次执行置为false
                        looper = setInterval(barrager, looper_time);
                        run_once = false;
                    }
                    var btom = lottery.randomNum(50, 300);  
                    items[index].bottom = h - btom;
                    $('#dm').barrager(items[index]);
                    //索引自增
                    index++;
                    //所有弹幕发布完毕，清除计时器。
                    if (index == total) {
                        //clearInterval(looper);
                        index = 0;
                        //run_once = true;
                        //barrager();                        
                        //return false;
                    }
                }
            }
        }
        sc.post(url, {
            "ActivityId": activityId
        }, callback);
    },
    randomNum: function (minNum, maxNum) {
        switch (arguments.length) {
            case 1:
                return parseInt(Math.random() * minNum + 1, 10);
                break;
            case 2:
                return parseInt(Math.random() * (maxNum - minNum + 1) + minNum, 10);
                break;
            default:
                return 0;
                break;
        }
    },
};

var mylottery = {
    Init: function () {
        //加载奖项
        var url = 'api/User/MyLotterList';
        var callback = function (data) {
            if (data.code == 0) { //成功                
                var arrText = doT.template($("#mylotterylist").text());                
                $("#mylotterylistBox")[0].innerHTML += arrText(data.data);
                lottery.Draw();
            }
            if (data.code == -1) { //登录超时
                setTimeout(function () {
                    location.href = "/login.html";
                }, 1000);
            }
        }
        sc.post(url, {
            "UserId": userId,
            "Token": token,
        }, callback);
    },   
};
var addrId = 0;
var loatteyId = 0;
var myAddress = {
    Init: function (Id) {
        //地址列表
        var pageNum = 1;
        var pageSize = 9;
        loatteyId = Id;
        AddressList(pageNum, pageSize, false, function (data) {
            if (data.data == "") {
                addressId = 0;
                addrId = 0;
                $('#pop-addrEadit').show();
                $('#pop-addrlist').hide();
                $('#pop-addrEadit #address_form .ipt0').val("");
                $('#address .selected-address li').text("请选择").removeClass('active');
                $('#address .address-content ul').html("");    
                $('#mylotterylistBox').hide();
            } else {
                var gettpl = document.getElementById('Addrlist_Tpl').innerHTML;
                laytpl(gettpl).render(data.data, function (html) {
                    $('#mylotterylistBox').hide();
                    //得到的模板渲染到html
                    $('#Addrlist_Div').html(html).show();
                    $('#pop-addrlist').show();
                });
            }
        });
        //编辑地址
        $('#Addrlist_Div').on('click', '.editbtn', function (e) {
            e.stopPropagation();
            addrId = parseInt($(this).parents('.delivery-item').attr('data-id'));            
            $('#pop-addrEadit').show().siblings('#Addrlist_Div,#mylotterylistBox,#pop-addrlist').hide();
            GetAddressInfo(addrId, function (data) {
                var selectedName = [];
                selectedName[0] = data.data.province;
                selectedName[1] = data.data.city;
                selectedName[2] = data.data.district;
                selectedName[3] = data.data.street;
                $('#name').val(data.data.name);
                $('#tel').val(data.data.tel);
                $('#selectCity').attr('data-code', selectedName.join(","));
                $('#selectCity').val(data.data.addressstr);
                $('#fullAddress').val(data.data.address);
            });
        });
        //新增地址
        $('#pop-addrlist').on('click', '.btnEadit', function (e) {        
            addressId = 0;
            addrId = 0;
            $('#pop-addrEadit').show();
            $('#pop-addrlist').hide();
            $('#pop-addrEadit #address_form .ipt0').val("");
            $('#address .selected-address li').text("请选择").removeClass('active');
            $('#address .address-content ul').html("");            
        });
        selectCity(); //弹出城市选择四级联动
        //确认领取
        $('#pop-addrlist').on('click', '.delivery-item', function (e) {
            var id = $(this).attr("data-id");
            var addressInfo = $(this).attr("data-addressinfo");
            var url = 'api/User/UpdateMyLotterAddress';
            var callback = function (data) {
                if (data.data == 1) {
                    $(".pop-addrlist,#pop-addrlist").hide();
                    $("#mylotterylistBox").show();
                    $("#mylotterylistBox .await").each(function () {
                        if ($(this).attr("data-id") == loatteyId) {
                            $(this).find(".right").html("已领取");
                            $(this).removeClass("await2").removeClass("await3").addClass("receive");
                        }
                    });
                }
                layer.open({
                    content: "<div id='toast' class='toast'>"+data.msg+"</div>",
                    shade: 0,
                    time: 2,
                    skin: 'myskin'
                });
                if (data.code == -1) { //登录超时
                    setTimeout(function () {
                        location.href = "/login.html";
                    }, 1000);
                }
            }
            sc.post(url, {
                "UserId": userId,
                "Token": token,
                "AddressId": id,
                "AddressInfo": addressInfo,
                "LoatteyId": loatteyId,
            }, callback);
        });
    },
    SaveAddress: function(){
        var txt_name = $('#name').val();
        txt_tel = $('#tel').val(),
            isDefault = 0,
            txt_fullAddress = $('#fullAddress').val(),
            txt_Zipcode = "",
            selectCityTxt = $('#selectCity').val(),
            selectCityCode = $('#selectCity').attr("data-code"),
            selectedArr = [];
        selectedArr.push(selectCityCode.split(","));
        var txt_Province = selectedArr[0][0],
            txt_City = selectedArr[0][1],
            txt_District = selectedArr[0][2],
            txt_Street = selectedArr[0][3];
        if (valCreateAddress(txt_name, txt_tel, selectCityTxt, txt_fullAddress)) {
            if (addrId) { //表示修改地址
                UpdateAddress(addrId, txt_name, txt_tel, isDefault, txt_Province, txt_City, txt_District, txt_Street, txt_fullAddress, txt_Zipcode, 1);
            } else { //表示添加地址
                AddAddress(txt_name, txt_tel, isDefault, txt_Province, txt_City, txt_District, txt_Street, txt_fullAddress, txt_Zipcode, 1);
            }
            
        }
    },
}
