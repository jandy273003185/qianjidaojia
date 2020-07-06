function upImage() {
    var input = document.getElementById("fileImg");
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
    function onReadUpload(that, type, size) { // 上传图片压缩处理
        console.log(that);
        var fileBase64='';
        var result = '<div style="display:none" class="result"><img src="' + fileBase64 + '"  alt=""/><div class="file-panel">          <span class="cancel">删除</span></div></div>';
        var divBox = document.createElement('div');
        divBox.innerHTML = result;
        divBox['className'] = 'upload-img';
        document.getElementById('imgdiv').appendChild(divBox); //插入页面
        var myimg = divBox.getElementsByTagName('img')[0];
        /*   myimg.src = base64; */
        myimg.parentNode.style.display = 'block';
        var delbtn = divBox.getElementsByClassName('file-panel')[0];
        // 大于2MB的图片都缩小像素上传
        if (size > 1048576 * 2) {
            console.log("大于2M");
            let canvas = document.createElement('canvas') // 创建Canvas对象(画布)
            let context = canvas.getContext('2d')
            let img = new Image()
            img.src = that.result; // 指定图片的DataURL(图片的base64编码数据)
            img.onload = () => {
                let width = 2400;
                let height = (width * img.height) / img.width;
                canvas.width = width;
                canvas.height = height;
                context.drawImage(img, 0, 0, canvas.width, canvas.height)
                fileBase64 = canvas.toDataURL(type, 0.92) // 0.92为默认压缩质量
                myimg.src = fileBase64;
            }
        } else { //小于2M直接上传
            console.log("小于2M");
            myimg.src = that.result;
           
        }
        delbtn.onclick = function () {
            $(this).parents('.upload-img').remove(); // 在页面中删除该图片元素	
            $('.btnUploadAll').show();
        }
        if ($('.upload-img').length > 7) {
            $('.btnUploadAll').hide();
        }
    }

    function readFile() {
        fd = new FormData();
        var iLen = this.files.length;;
        for (var i = 0; i < iLen; i++) {
            if (iLen < 9) {
                if (!input['value'].match(/.jpg|.jpeg|.gif|.png|.bmp/i)) { //判断所选文件格式
                    alert("上传的图片格式不正确，请重新选择");
                    return false;
                }
                var reader = new FileReader();
                var size = this.files[i].size;
                var type = this.files[i].type;
                fd.append(i, this.files[i]);
                reader.readAsDataURL(this.files[i]); //转成base64
                reader.onload = function (e) {
                    onReadUpload(this, type, size);

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
}