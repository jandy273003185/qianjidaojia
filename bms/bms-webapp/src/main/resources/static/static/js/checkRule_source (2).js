
/****
 *删除字符串两侧的空格
 */
function trim(str)
{
	str = this != window? this : str;
	return str.replace(/^\s+/g, '').replace(/\s+$/g, '');
}

function isNull(o) 
{
	var str = o.value;
	
	if (str.length==0)
	{
		return true;
	}
	else
	{
		return false;
	}
}

//验证银行卡格式
function checkBankCardFormat($_obj)
{
	if(!isNumber($_obj[0]) || $_obj.val().length < 12 || $_obj.val().length > 19)
	{
		return false;
	}
	
	return true;
}
/************************************ 
 * (1)电话号码由数字和－组成 /^[1][358]\d{9}$/;
 * (2)电话号码为 7 到 13 位
 * (3)手机号码必须为 11 位
************************************/
function isPhoneNo(controller) 
{ 
	var str=trim(controller.value); 
	var rst = str.substr(0,1);
	var reg = /^((\d{3,4}\-)?\d{7,8}(\-\d)?)$/;
	
	if (str.length==11)
	{
		return true;
	}
	else
	{
		if (!reg.test(str))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	

}

/************************************ 
 * (1)座机电话号码由数字和－组成 /^[1][358]\d{9}$/;
************************************/
function isSetPhone(controller)
{
	  var partten1 = /^0\d{10,11}$/; //固定电话不带“-”
  	var partten2 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;//固定电话带“-”
  	var str=trim(controller.value); 
		if(partten1.test(str) || partten2.test(str))
		{
			return true;
		}
		else
		{
			return false;
		}
}
function isMobilePhone(controller)
{
	var str=trim(controller.value); 
	var myreg = /^(((13[0-9]{1})|14[0-9]{1}|15[0-9]{1}|16[0-9]{1}|17[0-9]{1}|18[0-9]{1}|19[0-9]{1}|)+\d{8})$/;
	
	 if(str.length!=11)
	 {
	    return false;
	 }
	 else if(!myreg.test(str))
     {
	    return false;
	 }
	 else
	 {
		 return true;
	 }
}

/************************************
*Method:	checkNumber(controller)
*purpose:	检测输入是否为正整数数字，如果结果为非给出相应提示
*parameters :	控件名称，提示信息
*return value :  true/false.
*condition:  controller:must;message:optional
************************************/
function isNumber(controller)
{
	var value=controller.value;
	/*var count=0;*/
	if (value.length!=0)
	{
		for (var i=0;i<value.length;i++)
		{
			
			if (value.charAt(i)>"9" || value.charAt(i)<"0")
			{
				return false ;
			}
		}
	}
	else
	{
		return false;
	}
	/*controller.value=value;*/
	return true;
}



/**
 * 检查是否为日期类型
 * @param o
 * @return
 */
function isDate(o)   
{   
	  var  str = o.value;
      var   reg   =   /^(\d{4})-(\d{2})-(\d{2})$/;   
      var   arr   =   reg.exec(str);   

      if(!reg.test(str)&&RegExp.$2<=12&&RegExp.$3<=31)
      {
        return false;
      }  
      else
      {
          return true;   
      }  
}  

/**
 * js内部使用
 * @param o
 * @return
 */
function checkDate(o)   
{   
	  var  str = o;
      var   reg   =   /^(\d{4})-(\d{2})-(\d{2})$/;   
      var   arr   =   reg.exec(str);   

      if(!reg.test(str)&&RegExp.$2<=12&&RegExp.$3<=31)
      {
        return false;
      }  
      else
      {
          return true;   
      }  
} 



/**
 * 检测数字是否在某两个指之间
 * @param o
 * @param min
 * @param max
 * @return
 */
function checkDigitBetween(o,min,max)
{
	var vle = o.value;
	if (isNumber(o))
	{	
		if (vle>=min && vle<=max)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	else
	{
		return false;
	}
	
}


function checkDateDelay(o,delay)
{
	
    var bgdate =o.value;
    
    if (checkDate(bgdate))
    {
	    var bgd =bgdate.split("-");
	    var urodz= new Date(bgd[0],bgd[1]-1,bgd[2]);
	
	    var now = new Date();
	    var ile = now.getTime()-urodz.getTime();
	    var dni = Math.floor(ile / (1000 * 60 * 60 * 24));
	
	    var ile2 = urodz.getTime()-now.getTime();
	    var dni2 = Math.floor(ile2 / (1000 * 60 * 60 * 24));

	    if (dni>0)
	    {
	    	return false;
	    }
	    if (dni2>delay)
	    {
	    	return false;
	    }
	    else
	    {
	    	return true;
	    }
    }
    else
    {
    	return false;
    }
}

function verifyEmailAddress(o)  
{  
		var email = o.value;  
		var pattern =  /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;  
		/*var pattern = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;*/
		
		flag = pattern.test(email);  
		if(flag)  
		{  
			return true;  
		}  
		else  
		{  
			return false;
		}  
 } 



function addZero(o)
{
	var str = o.value;  
	if (str.length==0)
	{
		return false;
	}
	else
	{
		var zero="000000000000000000";
		o.value=zero.substring(0,18-str.length)+str;
		return true;
	}
}


function checkStringLength(o,min,max) 
{
	var str = o.value;
	if (str.length<min ||str.length>max)
	{
		return false;
	}
	else
	{
		return true;
	}
}

function checkBirthsday(o,min,max)
{
    var bgdate =o.value;
    if (checkDate(bgdate))
    {
        var birthday=new Date(bgdate.replace(/-/g, "\/")); 
		var d=new Date(); 
		var age = d.getFullYear()-birthday.getFullYear()-((d.getMonth()<birthday.getMonth()|| d.getMonth()==birthday.getMonth() && d.getDate()<birthday.getDate())?1:0);

	
	    if (min<= age && age<max)
		{
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
    }
    else
    {
    	return false;
    }
   
}

function check_ID_card(o) {    
    var ID_card = o.value;    
    var IDcardLen = ID_card.length;    
    var fMod = 11;            //身份证算法求模关键值    
    var oldIDLen = 15;   //旧身份证长度      
    var newIDLen = 18;       //新身份证长度    
    var yearFlag = "19";     //新身份证年份标志       
    var Wi = new Array();       //位权值数组    
    var varArray = new Array();    
    var birthday = '';  //生日    
    var checkDigit = '';    //校验位    
    var numSum = 0;         //数字和    
    var regStr = /^\d{15,17}([\dxX]{1})?$/;    
   
    if ((IDcardLen != 15) && (IDcardLen != 18)) {    
        alert("身份证长度必须为15或18位.");    
        return false;    
    }    
    if(!regStr.test(ID_card)){    
        alert("身份证号码必须为数字(18位的新身份证最后一位可以是x).");    
        return false;    
    }    
    if(IDcardLen == 15){    
        birthday = "19" + ID_card.substring(6,8) + "-" + ID_card.substring(8,10) + "-" + ID_card.substring(10,12);    
    }else{    
        birthday = ID_card.substring(6,10) + "-" + ID_card.substring(10,12) + "-" + ID_card.substring(12,14);    
    }    
    if(!isDate(birthday)){    
        alert("身份证号码中的日期格式不对(第6-12位).");    
        return false;    
    }    
    if(IDcardLen == 18)
    {    
     
    	var valnum; 
    	var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
    	var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
    	var nTemp = 0, i; 
    	for(i = 0; i < 17; i ++){ 
    	nTemp += ID_card.substr(i, 1) * arrInt[i]; 
    	} 
    	valnum = arrCh[nTemp % 11]; 
    	
    	if (valnum != ID_card.substr(17, 1))
    	{ 
	    	alert('18位身份证的校验码不正确！应该为：' + valnum); 
	    	return false;
    	}
    	else
    	{
    		return true;
    	}
    	
    	
    }    
  
    return true;    
} 


function isCertNo(o) {    
    var ID_card = o.value;    
    var IDcardLen = ID_card.length;    
    var fMod = 11;            //身份证算法求模关键值    
    var oldIDLen = 15;   //旧身份证长度      
    var newIDLen = 18;       //新身份证长度    
    var yearFlag = "19";     //新身份证年份标志       
    var Wi = new Array();       //位权值数组    
    var varArray = new Array();    
    var birthday = '';  //生日    
 
    var numSum = 0;         //数字和    
    var regStr = /^\d{15,17}([\dxX]{1})?$/;    
   
    if ((IDcardLen != 15) && (IDcardLen != 18)) 
    {    
        return false;   
    }    
    if(!regStr.test(ID_card))
    {    
    	 return false;  
    }    
    if(IDcardLen == 15)
    {    
        birthday = "19" + ID_card.substring(6,8) + "-" + ID_card.substring(8,10) + "-" + ID_card.substring(10,12);    
    }
    else
    {    
        birthday = ID_card.substring(6,10) + "-" + ID_card.substring(10,12) + "-" + ID_card.substring(12,14);    
    }   
    
    if(!checkDate(birthday))
    {    
    	return false;
    }    
    if(IDcardLen == 18)
    {    
    	var valnum; 
    	var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
    	var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
    	var nTemp = 0, i; 
    	for(i = 0; i < 17; i ++){ 
    	nTemp += ID_card.substr(i, 1) * arrInt[i]; 
    	} 
    	valnum = arrCh[nTemp % 11]; 
    	
    	if (valnum != ID_card.substr(17, 1))
    	{ 
	    	return false;
    	}
    	else
    	{
    		return true;
    	}
    }    
  
    return true;    
} 

function getSexFromCertNo(o)
{
    var ID_card = o.value;    
    var IDcardLen = ID_card.length; 
    var xingbie;
    var sex = '9';
    
    if(IDcardLen == 15)
    {    
        xingbie=ID_card.substr(14,1);    
	}
	else
	{    
	    xingbie=ID_card.substr(16,1);
	}  
    
    if ("13579".indexOf(xingbie)!=-1)
    {
    	sex='1';
    }
    else
    {
    	sex='2';
    }
    return sex;
}

function getBirthdayFromCertNo(o)
{
    var ID_card = o.value;    
    var IDcardLen = ID_card.length; 
    var birthday = '';
    
    if(IDcardLen == 15)
    {    
        birthday = "19" + ID_card.substring(6,8) + "-" + ID_card.substring(8,10) + "-" + ID_card.substring(10,12);    
	}
	else
	{    
	    birthday = ID_card.substring(6,10) + "-" + ID_card.substring(10,12) + "-" + ID_card.substring(12,14);    
	}  
    
    return birthday;
}

function length(o)
{
	var value = o.value;
	return value.length;
}

function substring(o,begin,end)
{
	var value = o.length;
	return value.substring(begin,end);
}

function isLeapYear(o)
{
	var value = o.value;
	var date = value.split("-");
	var year= date[0];
	if((year %4==0 && year %100!=0) || (year %400==0))
	{
		return true;
	}
	else 
	{
		return false;
	}
}

function isLeapYearBefore228(o)
{
	var value = o.value;
	var date = value.split("-");
	var year= date[0];
	var nextYear = year+1;
	if((year %4==0 && year %100!=0) || (year %400==0))
	{
		if (date[1]<=2 && date[2]<=28)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	else if ((nextYear %4==0 && nextYear %100!=0) || (nextYear %400==0))
	{
		if (date[1]>=3)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	else 
	{
		return false;
	}	
}

function getDaysOfMonthFromInsuredDate(o)
{
	var value = o.value;
	var date = value.split("-");
	var year= date[0];
	var day=0;
	
	if (date[1]==1||date[1]==3||date[1]==5||date[1]==7||date[1]==8||date[1]==10 ||date[1]==12)
	{
		day=31;
	}
	else if (date[1]==4||date[1]==6||date[1]==9||date[1]==11)
	{
		day=30;
	}
	else
	{
		if((year %4==0 && year %100!=0) || (year %400==0))
		{
			day=29;
		}
		else 
		{
			day=28;
		}
	}
	return day;
}

function getMonthFromInsuredDate(o)
{
	var value = o.value;
	var date = value.split("-");
	return  date[1];
}

//校验密码格式   为6-20个英文字母、数字或符号，不能是纯数字
function checkPwdPattern(pwd)
{
	var regx = /^(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}$/;
	return regx.test(pwd);
}
/**
 * 验证是否为金额，最多16位整数。小数点后最多两个数字
 */
function isAmount(amountStr)
{
	var regx = /^\-?(([1-9]([\d]{0,15}))|\d)(.[0-9]{1,2})?$/;
	return regx.test(amountStr);
}

/**
 * 验证是否为整数
 * @param str
 * @returns
 */
function isInteger(str){
	var regx = /^-?[1-9]\d*$/;
	return regx.test(str);
	
}



	/**
	 验证组织机构代码
	*/
	  function isValidateNtpCode(code) {
	       var flag = false;
	       
	       if(code.length==9||code.length==10){
	    	   
	      	 var patrn=/^[0-9A-Z]+$/;
	 
	   var reg = /^([0-9A-Z]){8}-[0-9|X]$/;
	   
	   var lastpatrn=/^[0-9X]+$/; 
	   
	   if (reg.test(code)||patrn.test(code)) {
	  	 var lastpatrn=/^[0-9X]+$/;
	          	 code=code.charAt(code.length - 1);
	          	 if(lastpatrn.test(code)){
	          		 flag= true;
	          	 }
	           }
	       }
	       return flag;
	  }
	  
	  
	  /**
	   * 验证营业执照注册号
	   * */
	  
	  function isValidBusCode(busCode){
	      var ret=false;
		  if(busCode.length==15){
		    var sum=0;
	                  var s=[];
	                  var p=[];
	                  var a=[];
	                  var m=10;
	                  p[0]=m;
		    for(var i=0;i<busCode.length;i++){
		       a[i]=parseInt(busCode.substring(i,i+1),m);
	                     s[i]=(p[i]%(m+1))+a[i];
	                     if(0==s[i]%m){
	                       p[i+1]=10*2; 
	                     }else{
	                       p[i+1]=(s[i]%m)*2;
	                      }    
		    }                                       
		    if(1==(s[14]%m)){
		       //营业执照编号正确! 
	    ret=true;
	}else{
	   //营业执照编号错误!
	          ret=false;
	       }
	  }else if(""==busCode){
	  ret=true;
	  }
	     return ret;
}
 
// 验证图片格式
function checkAttach(obj){
	var objFile = obj.value;
	if(""==objFile){
		return false;
	}
	 var objtype=objFile.substring(objFile.lastIndexOf(".")).toLowerCase();

	    var fileType=new Array(".jpg",".jpeg",".gif",".bmp",".png");

	    for(var i=0; i<fileType.length; i++){

	        if(objtype==fileType[i])
	        {
              return true;
              break;
 	        }
	    }
	    return false;
}



