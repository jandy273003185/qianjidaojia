var userId = sc.utils.getCookieValue("UserId");
var token = sc.utils.getCookieValue("Token");
var thisUrl = window.location.href.split("//")[0]+"//"+window.location.href.split("//")[1].split("/")[0];

$(function() {
	$("#erweima").click(function() {
		GetGiftCode();
	});
//	with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='+~(-new Date()/36e5)];
	
})


//获取二维码列表
function GetGiftCode() {
	var orderNo = window.location.href.split("?")[1].split("=")[1];
	console.log(orderNo);
	var url = "api/Gift/GetGiftCode";
	var callback = function(data) {
		console.log(data);
		var str = '';
		str += '<div class="pop-qyCode">';
		str += '<span class="icon btn_close"></span>';
		str += '<div class="pop-hd">购买成功</div>';
		str += '<div class="pop-bd">';
		str += '<ul class="codelist">';
		for(var i = 0 ; i < data.data.length ; i++){
			str += '<li class="codeItems" data-id='+data.data[i].Id+' data-IsSend='+data.data[i].IsSend+' data-ReceiveNo='+data.data[i].ReceiveNo+'>';
			str += '<div class="box">';
			str += '<div class="img" id="qrcode'+i+'"></div>';
			str += '<div class="right">';
			str += '<div class="txt">购买成功</div>';
			if(data.data[i].IsSend === 0){
				str += '<a href="javascript:;" class="btn sendcode_btn">保存到手机</a>';
			}else{
				str +='<a href="javascript:;" class="btn sendcode_btn btn_disable">已保存</a>';
			}
			str += '</div>';
			str += '</div>';
			str += '</li>';
		}
		str += '</ul>';
		str += '</div>';
		str += '</div>';
		layer.open({
			content: str,
		});
//		$(".sendcode_btn").each(function(index,elem){
//			console.log(index)
//			var qrcode = new QRCode(document.getElementById("qrcode"));
//			var receiveNo = $(this).parents(".codeItems").attr("data-ReceiveNo");
//			var elText = "./receiveGit.html?"+receiveNo;
//			qrcode.makeCode(elText);
//		});
		$(".sendcode_btn").each(function(index,elem) {
			//生成二维码 start
			var thisCode = $(elem).parents(".box").children(".img").attr("id");
			var qrcode = new QRCode(thisCode);
			var receiveNo = $(this).parents(".codeItems").attr("data-ReceiveNo");
			var id = $(this).parents(".codeItems").attr("data-id");
			var elText = thisUrl+"/receiveGit.html?receiveNo="+receiveNo+"&id="+id;
			qrcode.makeCode(elText);
			//生成二维码 end
			$(this).click(function() {
				if($(this).text()==="保存到手机"){
					var id = $(this).parents(".codeItems").attr("data-id");
					SendGiftCode(id);
					
					//将图片保存在本地 start
	                var imgs = $(this).parents(".box").find("img");
	                var a = document.createElement('a')
	                a.download = '礼包' + new Date().toLocaleString() || '礼包.png'
	                a.href = imgs.attr("src")
	                document.body.appendChild(a);
	                a.click();
	                document.body.removeChild(a);
					//将图片保存在本地 end
		            $(this).addClass("btn_disable");
					$(this).text("已保存");
//					window.location.href = thisUrl+"/receiveGit.html?receiveNo="+receiveNo+"&id="+id;
				}
				
			});
			
			
		});
		
		$('.btn_close').click(function() {
			layer.closeAll() //关闭所有层
		});

	}
	sc.post(url, {
		UserId: userId,
		Token: token,
		OrderNo: orderNo,
		page: 1
	}, callback)
}


//修改发送状态
function SendGiftCode(id){
	var url = "api/Gift/SendGiftCode";
	var callback = function(data){
		console.log(data)
	}
	sc.post(url,{
		UserId:userId,
		Token:token,
		Id:id
	},callback)
}

