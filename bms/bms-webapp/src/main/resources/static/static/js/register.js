/**
 *  微商户注册
 */
Register = {
	/**
	 * 验证微商户二维码编号
	 * @param $merchantCode			传入待验证字段
	 * @param $merchantCodeLabel	错误提示栏
	 * @returns
	 */
	validateMerchantCode:function($merchantCode,$merchantCodeLabel){
		$merchantCodeLabel.text("");
		var reg = /^[a-zA-Z0-9]{1,6}$/;
		var flag = true;
		if ($merchantCode == ""){
			$merchantCodeLabel.text("");
			flag = false;
			return false;
		}
		if (!reg.test($merchantCode)){
			$merchantCodeLabel.text("格式不对，只能输入数字和字母，长度不能超过6位");
			flag = false;
			return false;
		}
		return flag;
	},
	/**
	 * 邮箱验证
	 * @param $email		传入待验证的字段
	 * @param $emailLab		错误提示栏
	 * @returns
	 */
	validateEmail:function($email,$emailLab){
		$emailLab.text("");
		var flag = true;
		var reg = /^\w+@\w+(\.[a-zA-Z0-9-_]+)*\.[a-zA-Z0-9]{2,6}$/;
		if($email == ""){
			$emailLab.text("邮箱不能为空");
			flag = false;
			return false;
		}
		if(!reg.test($email)){
			$emailLab.text("邮箱格式不对,可使用字母、数字、下划线");
			flag = false;
			return false;
		}
		return flag;
	},
	// 验证是否是数字
	isNumber:function(content){
		var value = content.value;
		if(value.length != 0){
			for(var i = 0;i < value.legnth; i++){
				if (value.charAt(i) > "9" || value.charAt(i) < "0"){
					return false ;
				}
			}
		} else {
			return false;
		}
		return true;
	},
	// 验证银行卡格式
	checkBankCardFormat:function(str){
		if(!isNumber($_obj[0]) || $_obj.val().length < 8 || $_obj.val().length > 30){
			return false;
		}
		return true;
	},
	/**
	 * 银行卡号验证
	 * @param $compMainAcct					带验证字段
	 * @param $compMainAcctLabel			错误提示栏
	 */
	validateCompMainAcct:function($compMainAcct,$compMainAcctLabel){
		$compMainAcctLabel.text("");
		// 校验规则是16-19位的数字即可
		var reg = /^\d{16,19}$/;
		var flag = true;
		if($compMainAcct == ""){
			$compMainAcctLabel.text("银行卡号不能为空");
			flag = false;
			return false;
		}
		if(!reg.test($compMainAcct)){
			$compMainAcctLabel.text("银行卡格式不正确");
			flag = false;
			return false;
		}
		return flag;
	},
	/**
	 * 手机号码验证
	 * @param $representativeMobile			待验证字段
	 * @param $representativeMobileLabel	错误提示栏
	 */
	validatePhone:function($representativeMobile,$representativeMobileLabel){
		$representativeMobileLabel.text("");
		//var reg = /^1[3|4|5|8][0-9]\d{8}$/;
		var reg = /^1[0-9]{10}$/;
		var flag = true;
		if($representativeMobile == ""){
			$representativeMobileLabel.text("手机号码不能为空");
			flag = false;
			return false;
		}
		if(!reg.test($representativeMobile)){
			$representativeMobileLabel.text("请输入正确的手机号码");
			flag = false;
			return false;
		}
		return flag;
	},
	/**
	 * 商户名称验证
	 * @param $custName
	 * @param $custNameLabel
	 */
	validateCustName:function($custName,$custNameLabel){
		$custNameLabel.text("");
		var reg = /^[a-zA-Z0-9\u2E80-\u9FFF]+$/;
		var flag = true;
		if($custName == ""){
			$custNameLabel.text("名称不能为空");
			flag = false;
			return false;
		}
//		if(!reg.test($custName)){
//			$custNameLabel.text("商户名称格式不对，只能输入汉字，数字，字母组合");
//			flag = false;
//			return false;
//		}
		return flag;
	},
	
	/**
	 * 商户简称验证
	 * @param $custName
	 * @param $custNameLabel
	 */
	validateCustShopName:function($custName,$custNameLabel){
		$custNameLabel.text("");
		var reg = /^[a-zA-Z0-9\u2E80-\u9FFF]+$/;
		var flag = true;
		if($custName == ""){
			$custNameLabel.text("简称不能为空");
			flag = false;
			return false;
		}
//		if(!reg.test($custName)){
//			$custNameLabel.text("商户名称格式不对，只能输入汉字，数字，字母组合");
//			flag = false;
//			return false;
//		}
		return flag;
	},
	
	/**
	 * 商户名称验证
	 * @param $custName
	 * @param $custNameLabel
	 */
	validateBusinessTerm:function($value,$businessTermLabel){
		$businessTermLabel.text("");
		var reg = /^[a-zA-Z0-9\u2E80-\u9FFF]+$/;
		var flag = true;
		if($value == ""){
			$businessTermLabel.text("选择营业期限");
			flag = false;
			return false;
		}

		return flag;
	},
	
	//校验真实姓名
	validateAgentName:function($agentName,$agentNameLabel){
		$agentNameLabel.text("");
		var reg = /^[a-zA-Z0-9\u2E80-\u9FFF]+$/;
		var flag = true;
		if($agentName == ""){
			$agentNameLabel.text("真实姓名不能为空");
			flag = false;
			return false;
		}
		return flag;
	},
	/**
	 * 校验营业执照注册号
	 * 校验规则是：输入15位阿拉伯数字或英文字母，三证合一的新营业执照编号为18位
	 * @param $businessLicense		营业执照注册号
	 * @param $businessLicenseLab	错误提示栏
	 */
	validateBusinessLicense:function($businessLicense,$businessLicenseLab){
		$businessLicenseLab.text("");
		var reg = /^[a-zA-Z0-9]{15,18}$/;
		var flag = true;
		if ($businessLicense == "") {
			$businessLicenseLab.text("营业执照或者统一社会信用代码不能为空");
			flag = false;
			return false;
		}
		if (!reg.test($businessLicense)) {
			$businessLicenseLab.text("请输入正确的统一社会信用代码或营业执照注册号(15-18位数字和字母组合)");
			flag = false;
			return false;
		}
		return flag;
	},
	/**
	 * 证件类型验证
	 * @param $certifyType			证件类型
	 * @param $certifyTypeLabel		错误提示栏
	 * @param $certifyNo			身份证号码
	 */
	validateCertifyType:function($certifyType,$certifyTypeLabel,$certifyNo){
		$certifyTypeLabel.text("");
		var flag = true;
		if($certifyType == "10"){
			$certifyTypeLabel.text("请选择证件类型");
			flag = false;
			return false;
		}
		// 判断如果已输入了身份证号码再来修改证件类型的情况
		/*if ($certifyNo != "" && $certifyNo != null) {
			switch($certifyType){
				case "00":
					var chinaReg = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
					if (!chinaReg.test($certifyNo)) {
						$certifyTypeLabel.text("请选择正确的证件类型");
						flag = false;
					}
					break;
				case "01":
					var certifyNo = ($certifyNo.substring(0,7)) + ($certifyNo.substring(8,9));
					var checkCode = $certifyNo.substring(8,9);
					if (this.generateValidCode(certifyNo) != checkCode) {
						$certifyTypeLabel.text("请选择正确的证件类型");
						flag = false;
					}
					break;
				case "02":
					var macaoReg = /^[157][0-9]{6}\([0-9]\)$/;
					if (!macaoReg.test($certifyNo)) {
						$certifyTypeLabel.text("请选择正确的证件类型");
						flag = false;
					}
					break;
				case "03":
					var taiwanReg = /^[A-Z][0-9]{9}$/;
					if (!taiwanReg.test($certifyNo)) {
						$certifyTypeLabel.text("请选择正确的证件类型");
						flag = false;
					}
					break;
				default:
					break;
			}
		}*/
		return flag;
	},
	/**
	 * 身份证校验，涉及大陆包含港澳台分开校验
	 * @param $certifyNo		身份证号码
	 * @param $certifyNoLabel	错误提示栏
	 * @param $certifyType		证件类型
	 */
	validateCertifyNo:function($certifyNo,$certifyNoLabel,$certifyType){
		$certifyNoLabel.text("");
		var reg = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
		var flag = true;
		if ($certifyNo == "") {
			$certifyNoLabel.text("身份证号码不能为空");
			flag = false;
			return false;
		}
		switch($certifyType){
			case "10":
				$certifyNoLabel.text("请选择证件类型");
				flag = false;
				break;
			case "00":
				if (!reg.test($certifyNo)) {
					$certifyNoLabel.text("请输入正确的大陆居民身份证");
					flag = false;
				}
				break;
			case "01":
				var certifyNo = ($certifyNo.substring(0,7)) + ($certifyNo.substring(8,9));
				var checkCode = $certifyNo.substring(8,9);
				if (this.generateValidCode(certifyNo) != checkCode) {
					$certifyNoLabel.text("请输入正确的香港居民身份证");
					flag = false;
				}
				break;
			case "02":
				var reg = /^[157][0-9]{6}\([0-9]\)$/;
				if (!reg.test($certifyNo)) {
					$certifyNoLabel.text("请输入正确的澳门居民身份证");
					flag = false;
				}
				break;
			case "03":
				var reg = /^[A-Z][0-9]{9}$/;
				if (!reg.test($certifyNo)) {
					$certifyNoLabel.text("请输入正确的台湾居民身份证");
					flag = false;
				}
				break;
			default:
				break;
		}
		return flag;
	},
	/**
	 * 返回香港身份证校验码
	 * @param strs				香港身份证号码
	 * @returns					返回校验码
	 */
	generateValidCode:function(strs){
		var str = (strs.substring(0,7)) + (strs.substring(8,9));
	    var num1 = (str.substring(0,1).toUpperCase().charCodeAt() - 64) * 8;
	    for(var i = 1;i <= 6;i++){
	    	num1 += parseInt(str.substring(i,i+1)) * (8-i);
	    }
	    var num2 = num1 % 11;
	    var num3 = "";
	    if (num2 == 0) {
	    	num3 = "0";
	    } else if(num2 == 1){
	    	num3 = "A";
	    } else {
	    	num3 = 11 - num2;
	    }
	    return num3;
	},
	/**
	 * 获取所有的错误提示栏值，用于提交验证
	 * @returns {Boolean}
	 */
	allErrorMsgLabelValue:function(){
		var flag = false;
		var $merchantCodeLabel = $("#merchantCodeLab").text();
		var $emailLabel = $("#emailLab").text();
		var $custNameLabel = $("#custNameLabel").text();
		var $representativeMobileLabel = $("#representativeMobileLabel").text();
		//var $fcontactunitNumberLabel = $("#fcontactunitNumberLabel").text();
		var $businessLicenseLabel = $("#businessLicenseLab").text();
		var $businessPhotoLabel = $("#businessPhotoLab").text();
		var $certAttribute1Label = $("#certAttribute1Label").text();
		var $certAttribute2Label = $("#certAttribute2Label").text();
		var $certifyTypeLabel = $("#certifyTypeLabel").text();
		var $certifyNoLabel = $("#certifyNoLabel").text();
		if (($merchantCodeLabel != "") &&
			($emailLabel != "") &&
			($custNameLabel !="") &&
			($representativeMobileLabel !="") &&
			($businessLicenseLabel !="") &&
			($businessPhotoLabel !="") &&
			($certAttribute1Label !="") &&
			($certAttribute2Label !="") &&
			($certifyTypeLabel !="") &&
			($certifyNoLabel !="") 
		) {
			flag = true;
		}
		return flag;
	},
	/**
	 * 清空所有错误提示信息
	 */
	clearAllErrorMsgLabel:function(){
		$("#merchantCodeLab").text("");
		$("#emailLab").text("");
		$("#custNameLabel").text("");
		$("#representativeMobileLabel").text("");
		//$("#fcontactunitNumberLabel").text("");
		$("#businessLicenseLab").text("");
		$("#businessPhotoLab").text("");
		$("#certAttribute1Label").text("");
		$("#certAttribute2Label").text("");
		$("#certifyTypeLabel").text("");
		$("#certifyNoLabel").text("");
		$("#compMainAcctLabel").text("");
		$("#categoryIdLabel").text("");
	}

}



