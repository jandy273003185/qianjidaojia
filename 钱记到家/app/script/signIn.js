var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
//签到/分享接口
function UserRegister(type, successCallback) {
	var url = 'api/User/UserRegister';
	var callback = function(data) {
		console.log(data);
		if(data.code === 0) { //成功
			successCallback(data);
		}
		if(data.code === 1) { //失败
			var strHtml = '<div class="pop"><span class="btn-close"></span><div class="pop-hd" style="color:#505050;">每日签到</div><div class="imgbg" style="width:80%;margin:0 auto;"><img src="../images/icons/signIn-seccess.png" style="-webkit-filter: grayscale(1);filter: gray;filter: grayscale(1);" /></div><p class="tips">今天已经签到过啦<br>记得明天再来哦！</p></div>';
			layer.open({
				content: strHtml
			});
			$('.pop .btn-close').click(function() {
				layer.closeAll() //关闭所有层
			})
//			layer.open({
//				content: data.msg,
//				skin: 'msg',
//				time: 1 //1秒后自动关闭
//			});
		}
		if(data.code === 2) { //登录超时
			layer.open({
				content: data.msg,
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
			setTimeout(function() {
				location.href = "/login.html";
			}, 1000);
		}
		if(data.code === 99) { //系统错误
			layer.open({
				content: '系统开小差了',
				skin: 'msg',
				time: 1 //1秒后自动关闭
			});
		}
	}
	sc.post(url, {
		"UserId": userId,
		"Token": token,
		"type": type
	}, callback);
}
function GetUrlRelativePath(){
 	var urlStr = '';
　　　var url = document.location.toString();
　　　var arrUrl = url.split("//");
　　　var start = arrUrl[1].split("/");
    urlStr = arrUrl[0] + '//' + start[0];
　　　return urlStr;
}
//签到成功 : 分享再送<span class="red">10</span>积分
$(function(){
	$('#signIn').click(function() {
	var type;
	UserRegister(1, function(data) {
		var strHtml = '<div class="pop"><span class="btn-close"></span><div class="pop-hd" style="color:#505050;">签到成功</div><div class="imgbg" style="width:80%;margin:0 auto;"><img src="../images/icons/signIn-seccess.png"/></div><p class="tips">恭喜您获得2积分</br></p><div class="btnGroup btnGroup2" style="display:none"><a href="javascript:;" class="shareBtn btn btn-active" style="width:1rem;margin-left:auto;margin-right:auto;">马上分享</a></div><input type="txt" name="" id="shareUrl" value="" style="position: absolute;opacity: 0;" /></div>';
		layer.open({
			content: strHtml
		});
		$('.pop .btn-close').click(function() {
			layer.closeAll() //关闭所有层
		})
		$('.shareBtn').click(function() {										
			$('#shareUrl').val(GetUrlRelativePath()+'/register.html');
				jsCopy(); //执行浏览器复制命令
				UserRegister(2,function(data){
					layer.open({
						content: '已复制，请粘贴发送给朋友！',
						skin: 'msg',
						time: 2 //2秒后自动关闭
					});					
				})
			
		})
		function jsCopy(){
	        var u = navigator.userAgent;
	        //苹果
	        if (u.match(/(iPhone|iPod|iPad);?/i)) {
	            var copyDOM = document.getElementById("shareUrl");  
	            var range = document.createRange();
	            // 选中需要复制的节点
	            range.selectNode(copyDOM);
	            // 执行选中元素
	            window.getSelection().addRange(range);
	            // 执行 copy 操作
	            var successful = document.execCommand('copy');
	            
	        }
	
	        // 安卓
	        if(u.indexOf('Android') > -1 ){
	            $('#shareUrl')[0].select(); // 选择对象
	            document.execCommand("Copy"); // 执行浏览器复制命令
	        }
	        
	    }
	})
})
});
