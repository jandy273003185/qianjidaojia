<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.basemanager.toPay.controller.TopayPath"%>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<script src='<c:url value="/static/topayProfit/js/jquery-1.9.1.min.js"/>'></script>
<script src='<c:url value="/static/topayProfit/js/bootstrap.min.js"/>'></script>
<script src='<c:url value="/static/topayProfit/laydate/laydate.js"/>'></script>
<link rel="stylesheet" href='<c:url value="/static/topayProfit/css/bootstrap.min.css"/>' />


<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>代付收益</title>
  <style>
    body{
      box-sizing: border-box;
    }
    .card-wrapper {
      margin-top: 20px;
      display: flex;
      display: -webkit-box;
      display: -ms-flexbox;
      display: -webkit-flex;
      align-items: center;
      -webkit-box-align: center;
      -ms-flex-align: center;
      -webkit-align-items: center;
      flex-direction: column;
      -webkit-box-orient: vertical;
      -ms-flex-direction: column;
      -webkit-flex-direction: column;
    }

    .card-wrapper-title {
      font-size: 18px;
      color: #333;
      padding: 0 50px;
      border: 1px solid #fefefe;
      box-shadow: 0 0 4px #f4f4f4;
      font-weight: bold;
      letter-spacing: 1px;
      line-height: 10px;
    }

    .card {
      background-color: #f5f5f5;
      /* box-shadow: 0 0 2px #999999; */
      width: 600px;
      border-radius: 10px;
      display: flex;
      display: -webkit-box;
      display: -ms-flexbox;
      display: -webkit-flex;
      flex-direction: column;
      -webkit-box-orient: vertical;
      -ms-flex-direction: column;
      -webkit-flex-direction: column;
      align-items: center;
      -webkit-box-align: center;
      -ms-flex-align: center;
      -webkit-align-items: center;
    }

    .card-hd {
      font-size: 18px;
      color: #555555;
      font-weight: bold;
      line-height: 27px;
    }

    .card-hd .rmb {
      font-size: 20px;
      line-height: 27px;
      color: #dd7208;
      margin-left: 10px;
      margin-right: 2px;
    }

    .card-hd .money {
      font-size: 24px;
      line-height: 27px;

      color: #dd7208;
    }

    .card-main {
      width: 100%;
      display: flex;
      display: -webkit-box;
      display: -ms-flexbox;
      display: -webkit-flex;
      align-items: center;
      -webkit-box-align: center;
      -ms-flex-align: center;
      -webkit-align-items: center;
      flex-direction: column;
      -webkit-box-orient: vertical;
      -ms-flex-direction: column;
      -webkit-flex-direction: column;
    }

    .card-calendar {
      display: flex;
      display: -webkit-box;
      display: -ms-flexbox;
      display: -webkit-flex;
      align-items: center;
      -webkit-box-align: center;
      -ms-flex-align: center;
      -webkit-align-items: center;
      justify-content: center;
      -webkit-box-pack: center;
      -ms-flex-pack: center;
      -webkit-justify-content: center;
    }

    .card-query {
      font-size: 16px;
      display: flex;
      display: -webkit-box;
      display: -ms-flexbox;
      display: -webkit-flex;
      align-items: center;
      -webkit-box-align: center;
      -ms-flex-align: center;
      -webkit-align-items: center;
      flex-direction: column;
      -webkit-box-orient: vertical;
      -ms-flex-direction: column;
      -webkit-flex-direction: column;
    }

    .card-query-btn {
      color: #fff;
      border-radius: 3px;
      padding: 0 30px;
      font-size: 14px;
      line-height: 40px;
      background-color: #108ee9;
      text-decoration: none;
      text-align: center;
      margin-left: 10px;
      border: none;
      letter-spacing: 1px;
      margin-bottom: 10px;
    }

    .card-query .inputBox {
      padding: 10px 20px;
      border: 1px solid #D2D2D2;
      border-radius: 2px;
      height: 38px;
      line-height: 1.3;
      box-sizing: border-box;
    }

    .card-query .minus {
      color:  #999999;
      margin: 0 20px;
    }

    .card-result {
      padding: 2px;
      line-height: 24px;
      font-size: 15px;
      font-weight: bold;
    }
    .card-result2{
      font-size: 13px;      
    }
    .card-result .money{
      color: #dd7208;
      font-size: 24px;

    }

    .card-getMoney{
      display: -webkit-box;
      display: -ms-flexbox;
      display: -webkit-flex;
      align-items: center;
      -webkit-box-align: center;
      -ms-flex-align: center;
      -webkit-align-items: center;
      -webkit-box-pack: center;
      -ms-flex-pack: center;
      -webkit-justify-content: center;
      justify-content: center;
      margin-bottom:20px;
    }

    .card2{
      padding-top: 14px;
    }
    /* 按钮样式覆盖 */
    .card-query-btn{
      margin-bottom: 0;
      margin-left: 20px;
    }
    .card-getMoney2{
      display: -webkit-box;
      display: -ms-flexbox;
      display: -webkit-flex;
      align-items: center;
      -webkit-box-align: center;
      -ms-flex-align: center;
      -webkit-align-items: center;
      flex: 1;
    }

    .alertContainer{
      z-index: 99;
    }
    .transfer-dialog{
      margin-top: 40px;
    }
    @media screen and (min-width: 768px){
	.modal-dialog {
	 left: auto;
	}
	}
  </style>
</head>

<body>
<%@ include file="/include/top.jsp"%>
	<div class="main-container" id="main-container">		
		<div class="main-container-inner">
			<!-- 菜单 -->
			<%@ include file="/include/left.jsp"%>
			<div class="main-content">
				<!-- 路径 -->
				<%@ include file="/include/path.jsp"%>
				<!-- 主内容 -->
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
  <div class="card-wrapper">
    <div class="card-wrapper-title">
      <p class="card-title">充值</p>
    </div>
    <div class="card">
      <div class="card-hd">
        <p>今日收益:<span class="rmb">￥</span><span class="money">${todayRechargeProfit}</span>
        </p>
      </div>
      <div class="card-main">
        <div class="card-query">
          <div class="card-calendar">
            <!-- 日历选择器 -->
            <input type="text" class="inputBox " readonly="" value="" id="datetimepicker" data-date-format="yyyy-mm-dd">
            <p class="minus">—</p>
            <input type="text" class="inputBox " readonly="" value="" id="datetimepicker2" data-date-format="yyyy-mm-dd">
            <!-- 日历选择器 -->
          </div>
          <div class="card-result">
            <p> 查询收益：<span class="rmb">￥</span> <span class="money" id="rechargeIncome"> ${todayRechargeProfit}</span></p>
          </div>
          <button class="card-query-btn" onclick="queryProfit('recharge')">收益查询</button>
        </div>
      
      </div>
    </div>
  </div>
    <div class="card-wrapper">
    <div class="card-wrapper-title">
      <p class="card-title">代付</p>
    </div>
    <div class="card">
      <div class="card-hd">
        <p>今日收益:<span class="rmb">￥</span><span class="money">${todayPoundageProfit}</span></p>
      </div>
      <div class="card-main">
        <div class="card-query">
          <div class="card-calendar">
            <!-- 日历选择器 -->
            <input type="text" class="inputBox " readonly="" value="" id="datetimepicker3" data-date-format="yyyy-mm-dd">
            <p class="minus">—</p>
            <input type="text" class="inputBox " readonly="" value="" id="datetimepicker4" data-date-format="yyyy-mm-dd">
            <!-- 日历选择器 -->
          </div>
          <div class="card-result">
            <p> 查询收益：<span class="rmb">￥</span> <span class="money" id="poundageIncome"> ${todayPoundageProfit}</span></p>
          </div>
          <button class="card-query-btn" onclick="queryProfit('poundage')">收益查询</button>
        </div>
      </div>
    </div>
  </div>
  <div class="card-wrapper">
    <div class="card card2">
     <div class="card-hd">
        <p>总收益余额:<span class="rmb">￥</span><span class="money" id="usableAmt">${usableAmt}</span></p >
      </div>
      <div class="card-main">
        <div class="card-query">
          <div class="card-result card-result2">
            <div class="card-getMoney">
              <div class="card-getMoney2"> 今日总收益：<span class="rmb">￥</span><span class="money" id="incomeToday">${todayAllProfit }</span> </div> <button class="card-query-btn card-query-btn2" data-toggle="modal" data-target="#transferModal" onclick="setTransferMoney()">收益提现</button>
            </div>
            
          </div>
        </div>
      </div>
    </div>
  </div>
 
 </div>
 </div>
 </div>
 </div>
 </div>
 </div>
 <!-- 模态框（Modal） -->
  <div class="modal fade" id="transferModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   
    <div class="modal-dialog transfer-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
            &times;
          </button>
          <h4 class="modal-title" id="myModalLabel">
         			 转到银行卡
          </h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal">
                <div class="form-group">
                  <label for="payee" class="col-sm-2 control-label">姓名</label>
                  <input type="hidden" value="${accountName}" id="hidden_accountName">
                  <input type="hidden" value="${accountNumber}" id="hidden_accountNumber">
                 
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="accountName" placeholder="姓名" >
                  </div>
                </div>
                <div class="form-group">
                  <label for="bankCard" class="col-sm-2 control-label">银行卡号</label>
                  <div class="col-sm-10">
                    <input type="number" class="form-control" id="accountNumber" placeholder="银行卡号" >
                  </div>
                </div>
                <div class="form-group">
                  <label for="transferMoney" class="col-sm-2 control-label">金额</label>
                  <div class="col-sm-10">
                    <input type="number" class="form-control" id="transferMoney" placeholder="金额"  >
                  </div>
                </div>
                <div>
                </div>
            </form>
        </div>
      
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal" id="modalClose">关闭
          </button>
          <button class="card-query-btn card-query-btn2"   onclick="confirmTransfer()">确认提现</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal -->
  </div>
  <script type="text/javascript">

    // 防止用户输入非数字字符
    $(".card-getMoney input[name='number']").keyup(function () {
      console.log("121211");
      var c = $(this);
      console.log()
      if (/[^\d]/.test(c.val())) { //替换非数字字符  
        var temp_amount = c.val().replace(/[^0-9.]/g, '');
        $(this).val(temp_amount);
      }
    });
   
   

    // 判断是否为金钱格式
    
  </script>
  <!-- 日期格式化 -->
  <script>
    function formatTime() {
      var newdate = new Date();
      var nowyear = newdate.getFullYear();
      var nowmonth = newdate.getMonth() + 1;
      if (nowmonth >= 1 && nowmonth <= 9) {
        nowmonth = "0" + nowmonth;
      }
      var nowDate = newdate.getDate();
      var formattime = nowyear + "-" + nowmonth + "-" + nowDate;
      return formattime;
    }
  </script>

  <!-- 日历插件 -->
  <script>
    // var value = new Date(),
    var nowValue = formatTime();
    console.log("value" + nowValue);
    var startDate = laydate.render({
      elem: '#datetimepicker',
      format: 'yyyy-MM-dd',
      value: nowValue,
      isInitValue: true,
      theme: '#108ee9',
      trigger: 'click',
      done: function (value, date) {
        console.log(value); //得到日期生成的值，如：2017-08-18
        console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
        console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
        if (value != "") {
          date.month = date.month - 1;
          endDate.config.min = date;
        } else {
          endDate.config.min = startDate.config.min;
        }
      },
    });
    var endDate = laydate.render({
      elem: '#datetimepicker2',
      format: 'yyyy-MM-dd',
      isInitValue: true,
      trigger: 'click',
      value: nowValue,
      theme: '#108ee9',

      done: function (value, date) {
        if (value != "") {
          date.month = date.month - 1;
          startDate.config.max = date;
        } else {
          startDate.config.max = endDate.config.max;
        }
      }
    });
    var startDate2 = laydate.render({
      elem: '#datetimepicker3',
      format: 'yyyy-MM-dd',
      value: nowValue,
      trigger: 'click',
      theme: '#108ee9',

      done: function (value, date) {

        if (value != "") {
          date.month = date.month - 1;
          endDate2.config.min = date;
        } else {
          endDate2.config.min = startDate2.config.min;
        }
      },
    });
    var endDate2 = laydate.render({
      elem: '#datetimepicker4',
      format: 'yyyy-MM-dd',
      value: nowValue,
      // 日期只读
      trigger: 'click',
      theme: '#108ee9',

      done: function (value, date) {
        if (value != "") {
          date.month = date.month - 1;
          startDate2.config.max = date;
        } else {
          startDate2.config.max = endDate2.config.max;
        }
      }
    });
    
   
  </script>


  <script type="text/javascript">
	  function queryProfit(flag){
		var data;
		var rechargeStart;
	  	if(flag == 'recharge'){
	  		rechargeStart = $("#datetimepicker").val();
			var rechargeEnd = $("#datetimepicker2").val();
			data = {
					'rechargeStart':rechargeStart,
					'rechargeEnd':rechargeEnd,
					'flag':flag
					};
	  	}else if(flag == 'poundage'){
	  		var poundageStart = $("#datetimepicker3").val();
			var poundageEnd = $("#datetimepicker4").val();
			data = {
					'poundageStart':poundageStart,
					'poundageEnd':poundageEnd,
					'flag':flag
					};
	  	}else{	  		
	  		alert("查询参数有误，请联系管理员");
	  	}
	  	
	    
		 $.ajax({
	         url: "<%=request.getContextPath()+TopayPath.BASE+TopayPath.QUERYPROFIT%>",
	         type: "POST",
	         dataType:"json",
	         traditional: true,
	         data:data,
	         success : function(data){        	
	             if(data.result == 'SUCCESS'){
	            	 if(data.flag=='recharge'){
	            		 $("#rechargeIncome").text(data.rechargeProfit);
	            	 }else if(data.flag=='poundage'){
	            		 $("#poundageIncome").text(data.poundageProfit);
	            	 }
	            	 alert("查询成功");
				 }else{
					 alert("查询失败,失败原因"+data.message);
				 };
				
	         }
	     });
	  };
	  
	  function confirmTransfer(){
		 var usableAmt = $("#usableAmt").text();
		 var money = $("#transferMoney").val();
		 var accountNumber = $("#accountNumber").val();
		 var accountName = $("#accountName").val();
		 if(Number(money) < 10 ){
			  alert("提现金额不得小于10元,请重新输入提现金额");
			  return false;
		  }
		 if(Number(money) > Number(usableAmt)){
			  alert("提现金额不得超过收益余额,请重新输入提现金额");
			  return false;
		  }
		 $.ajax({
	         url: "<%=request.getContextPath()+TopayPath.BASE+TopayPath.CASHWITHDRAWAL%>",
	         type: "POST",
	         dataType:"json",
	         traditional: true,
	         data:{
	        	 'money':money,
	        	 'accountNumber':accountNumber,
	        	 'accountName':accountName
	         },
	         success : function(data){        	
	             if(data.result == 'SUCCESS'){	            	
	            	 $("#usableAmt").text(data.newUsableAmt);
	            	 alert("操作成功");
	            	 setTimeout(function(){window.location.reload(); }, 1000);
				 }else{
					 alert("提现失败,失败原因"+data.message);
				 };
				 
	         }
	     });
		
		 
	  }
	  
	 function setTransferMoney(){
		 $("#transferMoney").val($("#usableAmt").text());
		 $("#accountName").val($("#hidden_accountName").val());
		 $("#accountNumber").val($("#hidden_accountNumber").val());
	 }
  </script>
</body>

</html>