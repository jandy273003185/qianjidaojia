/**图片预览**/


function previewImage(divObj,imageObj,fileObj){  
	   try{  
			var value = fileObj.value;  
			   if(value ==""){  
				   imageObj.src="";
				   fileObj.click();
			        return false;  
			   }  
			 // 判断是否为图片类型   
			if(value.toLocaleLowerCase().indexOf('.jpg')<=0 && value.toLocaleLowerCase().indexOf('.bmp')<=0&& value.toLocaleLowerCase().indexOf('.gif')<=0&& value.toLocaleLowerCase().indexOf('.jpeg')<=0&& value.toLocaleLowerCase().indexOf('.png')<=0){  
			        $.sevenpay.alertFailure('请选择正确的图片');
			        imageObj.src="";
			        return false;  
			 }  
	       //FireFox7.0下直接设置img属性  
	       if(fileObj.files && fileObj.files[0]){ 
	          imageObj.style.display="";  
	          imageObj.src = window.URL.createObjectURL(fileObj.files[0]); //7.0的获取方法 
	       }else if(divObj.filters){  //IE浏览器的判断
	          fileObj.select();  
	          var value;  
	          if ($.browser.version > 8) {      
	             divObj.focus();  // IE8以上  
	               
	         }  
	          value = document.selection.createRange().text;  
	          imageObj.style.display="none";  
	          divObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src= value;  
	      }  
	   }catch(ex){    
	       fileObj.outerHTML+="";  
	       return false;  
	   }   
	   return true;  
	}
//毫秒转化为格式化日期
var format1 = function(time, format){
    var t = new Date(time);
    var tf = function(i){return (i < 10 ? '0' : '') + i};
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    });
};


function loadArea(obj){
	$.post(window.Constants.ContextPath +'/basemanager/city/getAreaByAreaId',{
		'areaId' : obj
		},function(data){
			$.each(data.addressList,function(n,value){
				$(".province-city-area #provinceId").val(value.provinceId);
	            var resultCity ="<option value='"+value.cityId+"'>"+value.cityName+"</option>";
	            $(".province-city-area #cityId").html('');
	            $(".province-city-area #cityId").append(resultCity);
	            var resultArea ="<option value='"+value.areaId+"'>"+value.areaName+"</option>";
	            $(".province-city-area #areaId").html('');
	            $(".province-city-area #areaId").append(resultArea);
			});
    },"json");
}

/**
 * 城市级联
 * **/
$(document).ready(function () {
    $(".province-city-area #provinceId").change(function () {
    	$.post(window.Constants.ContextPath +'/basemanager/city/getCityByProvinceId',{
			'provinceId' : $(".province-city-area #provinceId").val()
			},function(data){
                var result = "<option value=''>选择城市</option>";
                $.each(data.cityList,function(n,value){
                    result +="<option value='"+value.cityId+"'>"+value.cityName+"</option>";
                });
                $(".province-city-area #cityId").html('');
                $(".province-city-area #areaId").html('');
                var resultArea = "<option value=''>选择区域</option>";
                $(".province-city-area #areaId").append(resultArea);
                $(".province-city-area #cityId").append(result);
            
        },"json");
    });
    
    $(".province-city-area #cityId").change(function () {
    	$.post(window.Constants.ContextPath +'/basemanager/city/getAreaByCityId',{
			'cityId'	: $(".province-city-area #cityId").val()
			},function(data){
                var result = "<option value=''>选择区域</option>";
                $.each(data.areaList,function(n,value){
                    result +="<option value='"+value.areaId+"'>"+value.areaName+"</option>";
                });
                $(".province-city-area #areaId").html('');
                $(".province-city-area #areaId").append(result);
        },"json");
    });
});

