import {common} from "@/assets/api/interface";
const uploadImg = {
    async uploadImgRequest(data, name, that) { //图片上传
        that.$toast.loading({
            message: "图片上传中..",
            forbidClick: true,
            duration: 0
        });
        let info = await common.uploadImg(data);
        that.$toast.clear();
        if (info.data && info.data.code && info.data.code == 200) {
            let resultMsg = JSON.parse(info.data.data);
            let fullUrl = resultMsg.uri + "" + resultMsg.url;
            if (name != 'businessPhoto') {
                that.params[name] = fullUrl;
            }
            that.photos[name] = [{
                url: fullUrl
            }];
        } else {
            that.$toast("图片上传失败！");
            if (name != 'businessPhoto') {
                that.params[name] = '';
            }
            that.photos[name] = [];
        }
    },
    dataURLtoFile(dataurl, filename) { // 将base64转换为file文件
        let arr = dataurl.split(',')
        let mime = arr[0].match(/:(.*?);/)[1]
        let bstr = atob(arr[1])
        let n = bstr.length
        let u8arr = new Uint8Array(n)
        while (n--) {
            u8arr[n] = bstr.charCodeAt(n)
        }
        return new File([u8arr], filename, {
            type: mime
        })
    },
    onReadUpload(file, name, that) { // 上传图片压缩处理
        // 大于2MB的图片都缩小像素上传
        if (file.file.size > 1048576 * 2) {
            let canvas = document.createElement('canvas') // 创建Canvas对象(画布)
            let context = canvas.getContext('2d')
            let img = new Image()
            img.src = file.content // 指定图片的DataURL(图片的base64编码数据)
            img.onload = () => {
                let width = 2400;
                let height = (width * img.height) / img.width;
                canvas.width = width;
                canvas.height = height;
                context.drawImage(img, 0, 0, canvas.width, canvas.height)
                file.content = canvas.toDataURL(file.file.type, 0.92) // 0.92为默认压缩质量
                let files = this.dataURLtoFile(file.content, file.file.name)
                const data = new FormData()
                data.append('file', files)
                this.uploadImgRequest(data, name, that);
            }
        } else { //小于2M直接上传
            const data = new FormData()
            data.append('file', file.file)
            this.uploadImgRequest(data, name, that);
        }
    },
    onReadImg(file, name, that) { // 识别图片压缩
        // 大于2MB的图片都缩小像素上传
        if (file.file.size > 1048576 * 2) {
            let canvas = document.createElement('canvas') // 创建Canvas对象(画布)
            let context = canvas.getContext('2d')
            let img = new Image()
            img.src = file.content // 指定图片的DataURL(图片的base64编码数据)
            img.onload = () => {
                let width = 2400;
                let height = (width * img.height) / img.width;
                canvas.width = width;
                canvas.height = height;
                context.drawImage(img, 0, 0, canvas.width, canvas.height)
                file.content = canvas.toDataURL(file.file.type, 0.92) // 0.92为默认压缩质量
                this.getImgInfo(file.content, name, that);
            }
        } else { //小于2M直接上传
            const data = new FormData()
            data.append('file', file.file)
            this.getImgInfo(file.content, name, that);
        }
    },
    async getImgInfo(file, name, that) {
        //识别图片
        that.$toast.loading({
            message: "识别中..",
            forbidClick: true,
            duration: 0
        });
        const params = {
            str: file,
            flag: name
        };
        const info = await common.getImgInfo(params);
        that.$toast.clear();
        if (info.data.data && info.data.data.result && info.data.data.result == "SUCCESS") {
            const imgUrl = info.data.data.uri + "" + info.data.data.url;
            if (name == 'businessPhoto') {
                that.photos.businessPhoto = [{
                    url: imgUrl
                }];
                let businessLicense = info.data.data.businessLicense;
                let businessTermEnd = info.data.data.businessTermEnd;
                let businessTermStart = info.data.data.businessTermStart;
                that.params.businessLicense = businessLicense;
                that.params.businessTermEnd = businessTermEnd;
                that.params.businessTermStart = businessTermStart;
            }
            if (name == "certAttribute1") {
                that.photos.identityCardFront = [{
                    url: imgUrl
                }];
                that.params.identityCardFront = imgUrl;
                that.params.representativeName = info.data.data.cardName;
                that.params.representativeCertNo = info.data.data.cardId;
            }
            if (name == "certAttribute2") {

                that.params.identityCardReverse = imgUrl;
                that.photos.identityCardReverse = [{
                    url: imgUrl
                }];
                let arr = info.data.data.cardValidDate.split("-");
                that.params.idTermStart = arr[0];
                that.params.idTermEnd = arr[1];
            }
        } else {
            if (name == 'businessPhoto') {
                that.photos.businessPhoto = [];
                that.$toast("营业执照信息无法识别！");
            }
            if (name == "certAttribute1") {
                that.photos.identityCardFront = [];
                that.params.identityCardFront = '';
                that.$toast("身份证正面信息无法识别！");
            }
            if (name == "certAttribute2") {
                that.params.identityCardReverse = '';
                that.photos.identityCardReverse = [];
                that.$toast("身份证反面信息无法识别！");
            }

        }
    },
}
export default uploadImg