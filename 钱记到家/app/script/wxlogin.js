var isWxLoginOver = false; //微信登录是否执行完
//是否是微信（打开）
function iswx() {
    var ua = navigator.userAgent.toLowerCase();
    if (ua.match(/MicroMessenger/i) == "micromessenger") {
        return true;
    } else {
        return false;
    }
}
if (iswx() && typeof sc != "undefined") {
    var uid = sc.auth.getUserId();
    if (sc.auth.isLogin()) {// 登录情况下,需要去绑定
        var userinfo = sc.auth.getUserInfo();        
        if (sc.utils.isNullOrEmpty(userinfo)) { //如果未获取到微信用户信息
            IsHashOpenId();            
        } else {
            auserInfo = JSON.parse(userinfo);           
            if (sc.utils.isNullOrEmpty(auserInfo.Openid)) {
                IsHashOpenId();
            } else {
                isWxLoginOver = true;
            }
        }
    } else { //未登录，需要根据openid去查询是否已经绑定,如果已经绑定，则直接登录        
        if (sc.utils.isNullOrEmpty(sc.auth.getUserInfo())) { //如果未获取到微信用户信息
            wxLogin();           
        } else
            BindLogin();
    }
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    function wxLogin() {
        var appId = 'wxb3c817cc8391cb14';
        var oauth_url = sc.serverUrl + "WxLogin/Login";
        var url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&redirect_uri=" + encodeURIComponent(location.href.split('#')[0]) + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect"
        var code = getUrlParam("code");
        if (!code) {
            window.location = url;
        } else {
            var callback = function (data) {
                if (data.data != null && data.data != "") {                    
                    var auserInfo = JSON.parse(data.data);
                    if (!sc.utils.isNullOrEmpty(auserInfo.Openid)) {                        
                        sc.auth.setUserInfo(data.data);
                        BindLogin();
                    } else
                        isWxLoginOver = true;
                } else               
                    isWxLoginOver = true;
            }
            sc.post("api/Login/WeChatLogin", {
                "wxcode": code,
                "userid": sc.auth.getUserId() == null ? "" : sc.auth.getUserId(),
                "token": sc.auth.getToken() == null ? "" : sc.auth.getToken(),
            }, callback);
        }
    }

    //此处需要获取用户是否存在openid，不存在则走微信授权
    function IsHashOpenId() { //手机号登录情况下，判断是否已经绑定了微信信息
        var callback = function (data) {
            if (!sc.utils.isNullOrEmpty(data.data)) {
                if (!sc.utils.isNullOrEmpty(data.data.Openid)) {
                    sc.auth.setUserInfo(data.data);
                } else
                    wxLogin();
            } else
                wxLogin();
        }
        sc.post("api/Login/HashOpenId", {
            "userid": sc.auth.getUserId(),
            "token": sc.auth.getToken()
        }, callback);
    }
    function BindLogin() { //未登录，需要根据openid去查询是否已经绑定,如果已经绑定，则直接登录        
        var callback = function (data) {
            if (!sc.utils.isNullOrEmpty(data.data)) {                
                if (!sc.utils.isNullOrEmpty(data.data.Token) && !sc.utils.isNullOrEmpty(data.data.UserId)) {
                    sc.auth.setToken(data.data.Token);
                    sc.auth.setUserId(data.data.UserId);
                    isWxLoginOver = true;
                } else
                    isWxLoginOver = true;
            } else 
                isWxLoginOver = true;
        }        
        var userinfo = sc.auth.getUserInfo();
        var auserInfo = JSON.parse(userinfo);        
        if (!sc.utils.isNullOrEmpty(auserInfo)) {
            sc.post("api/Login/HashBindOpenId", {
                "openid": auserInfo.Openid,
            }, callback);
        }
    }
    var itval = setInterval(function () {
        if (isWxLoginOver == true) {
            clearInterval(itval);
            //没有注册
            if (!sc.auth.isLogin()) {
                var url = window.location.href;
                window.location.href = "/register.html?callback=" + encodeURIComponent(url);
            }
        }
    }, 300);


 
}
