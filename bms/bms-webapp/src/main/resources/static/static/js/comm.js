function isMobilePhoneV(mobilePhone)
{
	var str=trim(mobilePhone.value); 
	// var myreg = /^(((13[0-9]{1})|15[0-9]{1}|18[0-9]{1}|)+\d{8})$/;
	var telReg = /^(0|86|17951)?(13[0-9]|15[012356789]|16[0-9]|17[678]|18[0-9]|19[0-9]|14[0-9])[0-9]{8}$/;
	/* var partten1 = /^0\d{10,11}$/; //固定电话不带“-”
	 var partten2 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;//固定电话带“-”
*/	 if(str.length!=11)
	 {
	    return false;
	 }
	 else if(!telReg.test(str))
     {
	    return false;
	 }
	 else
	 {
		 return true;
	 }
}




/****
 *删除字符串两侧的空格
 */
function trim(str)
{
	str = this != window? this : str;
	return str.replace(/^\s+/g, '').replace(/\s+$/g, '');
}