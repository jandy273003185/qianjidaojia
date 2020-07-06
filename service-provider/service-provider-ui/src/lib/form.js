const form = {
  validParams: function (that, params) { //校验必填信息 多形态   添加进件
    let arr = Object.keys(params);
    let errCount = 0;
    arr.map(function (item) {
      if (!params[item] &&item!='electronicSignaturePhoto'&&item!='businessLicense'&& item!='businessTermStart'&&item!='businessTermEnd'&&item!='specialBusiness'&&item != 'otherPhoto1' && item != "otherPhoto2" && item != "custId" && item != 'userId' && item != 'roleId' && item != 'custId' && item != 'licenceForOpeningAccounts' && item != 'bankCardFront') {
        console.log(item);
        that.$toast("请先将进件信息补充完整！");
        errCount++;
      } else {
        if(that.params.custType!='0'){
          if(!that.params.businessLicense){
            that.$toast("请填写营业执照编号");
            errCount++;
          }
          if(!that.params.businessTermStart){
            that.$toast("请填写营业执照有效期开始时间");
            errCount++;
          }
          if(!that.params.businessTermEnd){
            that.$toast("请填写营业执照有效期结束时间");
            errCount++;
          }
        }
        if (that.params.compMainAcctType == '01' && !params['licenceForOpeningAccounts']) {
          that.$toast("未上传开户许可证");
          errCount++;
        }
        if (that.params.compMainAcctType == '02' && !params['bankCardFront']) {
          that.$toast("未上传银行卡照片");
          errCount++;
        }
        if (item == 'merchantAccount' || item == 'contactMobile') {
          if (!(/^1[3456789]\d{9}$/.test(params[item]))) {
            that.$toast("手机号码格式有误！")
            errCount++;
          }
        }
        if(item=='compMainAcct'&&params[item].length<5){
          that.$toast("银行卡号有误！")
          errCount++;
        }
        if (item == 'contactPhone') {
          if (!/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(params[item])) {
            that.$toast('电话号码格式有误！');
            errCount++;
          }
        }
      }
    })
    console.log("errCount:" + errCount);
    return errCount;
  },
   throttle: function (fn, delay) { //节流函数
    let delays = delay || 800,
      timer=null;
    return function () {
      const context=this;
      let args=arguments;
      if(!timer){
        timer = setTimeout(() => {
          fn.apply(context, args)
          clearTimeout(timer);
        }, delays)
      }
      
    }
  },
}
export default form