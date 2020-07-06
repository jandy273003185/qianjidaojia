function upImage() {
    var input = document.getElementById("fileImg");
    var result;
    var dataArr = []; // 储存所选图片的结果(文件名和base64数据)  
    var fd; //FormData方式发送请求    
    var oSelect = document.getElementById("select");
    var oAdd = document.getElementById("add");
    var oSubmit = document.getElementById("submit");
    var oInput = document.getElementById("fileImg");
    if (typeof FileReader === 'undefined') {
        alert("抱歉，你的浏览器不支持 FileReader");
        input.setAttribute('disabled', 'disabled');
    } else {
        input.addEventListener('change', readFile, false);
    }
    // handler

    function readFile() {
        fd = new FormData();
        var iLen = this.files.length;
        for (var i = 0; i < iLen; i++) {
            if (iLen < 9) {
                if (!input['value'].match(/.jpg|.gif|.png|.bmp/i)) {　　 //判断所选文件格式
                    return alert("上传的图片格式不正确，请重新选择");
                }
                var reader = new FileReader();
                fd.append(i, this.files[i]);
                reader.readAsDataURL(this.files[i]); //转成base64
                var fileName = this.files[i].name;
                reader.onload = function (e) {
                    var imgMsg = {
                        name: fileName, //获取文件名
                        base64: this.result //reader.readAsDataURL方法执行完后，base64数据储存在reader.result里
                    }
                    dataArr.push(imgMsg);
                    result = '<div style="display:none" class="result"><img src="' + this.result + '" alt=""/><div class="file-panel">          <span class="cancel">删除</span></div></div>';
                    divBox = document.createElement('div');
                    divBox.innerHTML = result;
                    divBox['className'] = 'upload-img';
                    document.getElementById('imgdiv').appendChild(divBox);　　 //插入页面
                    var img = divBox.getElementsByTagName('img')[0];
                    var delbtn = divBox.getElementsByClassName('file-panel')[0];
                    img.onload = function () {
                        var nowHeight = ReSizePic(this); //设置图片大小
                        this.parentNode.style.display = 'block';
                        var oParent = this.parentNode;
                        if (nowHeight) {
                            oParent.style.paddingTop = (oParent.offsetHeight - nowHeight) / 2 + 'px';
                        }
                    }
                    delbtn.onclick = function () {
                        $(this).parents('.upload-img').remove(); // 在页面中删除该图片元素	
                        $('.btnUploadAll').show();
                    }
                    if ($('.upload-img').length > 7) {
                        $('.btnUploadAll').hide();
                    }
                }
            } else {
                layer.open({
                    content: '最多只能上传8张图片哦！',
                    skin: 'msg',
                    time: 1 //2秒后自动关闭
                });
            }

        }

    }

    function ReSizePic(ThisPic) {
        var RePicWidth = 100; //这里修改为您想显示的宽度值
        var TrueWidth = ThisPic.width; //图片实际宽度
        var TrueHeight = ThisPic.height; //图片实际高度
        if (TrueWidth > TrueHeight) {
            //宽大于高
            var reWidth = RePicWidth;
            ThisPic.width = reWidth;
            //垂直居中
            var nowHeight = TrueHeight * (reWidth / TrueWidth);
            return nowHeight; //将图片修改后的高度返回，供垂直居中用
        } else {
            //宽小于高
            var reHeight = RePicWidth;
            ThisPic.height = reHeight;
        }
    }
}