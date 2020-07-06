<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<html>
<head>
	<meta charset="utf-8" />
	<title>商户信息</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		ul,li{
		list-style:none 
		}
		.agreement p{
            margin-bottom: 5px;
        }
        .agreement .title {
            text-align: center;
            padding-bottom: 40px;
        }
        .userInfo li div {
            line-height: 1.5;
        }
        .userInfo li>.fl{
            width: 40%;
        }
        .userInfo li>.fr{
            width: 60%;
        }
        .userInfo li .fl {
            float: left;
        }

        .userInfo li .fr {
            float: right;
        }
        .clearfix:after {
            content: ".";
            display: block;
            clear: both;
            visibility: hidden;
            height: 0;
            font-size: 0;
        }
        .clearfix {
            *zoom: 1;
        }

        .content p, .content h4, .application p{
            text-indent: 2em;
        }
        .application{
            margin-top: 60px;
        }
        .application h3{
            text-align: center;
        }
		
	</style>
</head>
<script type="text/javascript">
	$(function(){
		$('.clearVirtual').click(function(){
			$('#id').val('');
		});
		
		var authList= ${infos};
		var authTrList=$("tr.merchantInfo");
		$.each(authList,function(i,value){
			$.data(authTrList[i],"merchantInfo",value);
		});
		
	});
	function merchantInfo(obj){
		var merchant = $.data($(obj).parent().parent()[0],"merchantInfo");
		
		
				if(merchant.protocolId == '1'){
					$("#updateMerchant .merchantProduct").text("扫码支付");
				}else if(merchant.protocolId == '2'){
					$("#updateMerchant .merchantProduct").text("APP支付");
				}else if(merchant.protocolId == '3'){
					$("#updateMerchant .merchantProduct").text("公众号支付");
				}else if(merchant.protocolId == '4'){
					$("#updateMerchant .merchantProduct").text("H5支付");
				}else if(merchant.protocolId == '5'){
					$("#updateMerchant .merchantProduct").text("小程序支付");
				}else if(merchant.protocolId == '6'){
					$("#updateMerchant .merchantProduct").text("蓝海计划");
				}else if(merchant.protocolId == '7'){
					$("#updateMerchant .merchantProduct").text("代付");
				}
				
		$("#updateMerchant .merchantRate").text(merchant.protocolRate);
		
		$("#updateMerchant .merchantName").text(merchant.merchantName);
		$("#updateMerchant .address").text(merchant.address);
		$("#updateMerchant .contact").text(merchant.contact);
		$("#updateMerchant .phone").text(merchant.phone);
		$("#updateMerchant .bank").text(merchant.bank);
		$("#updateMerchant .bankNum").text(merchant.bankNum);
		
	};
	
	function selectMonth() {  
        WdatePicker({ dateFmt: 'yyyyMM', isShowToday: false, isShowClear: false });  
    }  
</script>
<body>
	<!-- 用户信息 -->
	<%@ include file="/include/top.jsp"%>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>
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
						<!-- 查询条件 -->
							<form action='<c:url value="/basemanager/virtual/merchantList"/>' method="post">
							<table class="search-table">
								<tr>
									<td class="td-left" width="18%">商户编号</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="id" id="id"  value="${queryBean.id}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" width="18%">商户名称</td>
									<td class="td-right" > 
										<span class="input-icon">
											<input type="text"  name="merchantName" id="merchantName"  value="${queryBean.merchantName}">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<button type="submit" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button> 
											<button class="btn btn-purple btn-sm btn-margin clearVirtual" >
												清空
												<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
										</span>
									</td>
								</tr>
							</table>
							</form>
							
							<div class="list-table-header">
								商户列表
							</div> 
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="20%">商户编号</th>
											<th width="30%">商户名称</th>
											<th width="10%">商户状态</th>
											<th width="10%">商户协议</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${infos}" var="tradeInfo" varStatus="status">
											<tr class="merchantInfo" >
												<td>${tradeInfo.id }</td>
												<td>${tradeInfo.merchantName }</td>
												<td>
												激活
												 </td>
												<td>
													<button type="button" onclick="merchantInfo(this);" data-toggle='modal'  data-target="#updateMerchant"  class="btn btn-primary btn-xs" >预览</button> 
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty infos}">
											<tr><td colspan="8" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty infos}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div><!-- /.page-content -->
				<!-- 底部-->
				<%@ include file="/include/bottom.jsp"%>
			</div><!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
		</div><!-- /.main-container-inner -->
		<!-- 向上置顶 -->
		<%@ include file="/include/up.jsp"%>
	</div><!-- /.main-container -->
	
	<div class="modal fade" style="z-index:1040;" id="updateMerchant" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:60%;z-index:90;" >
		      <div class="modal-content" style="width:950px;" id="merchantDiv">
		         <div class="modal-header" style="background-color:0099CC">
		            <button type="button" class="close" data-dismiss="modal" id="updateMerchantClose"  aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel">商户协议信息</h4>
		         </div>
		         <div class="modal-body">
						<table id="sample-table-2" class="list-table" >
						
						<tr>
                            <td>
                                <div class="agreement">
                                    <div class="title">
                                        <h3>七分钱支付服务合作协议</h3>
                                    </div>
                                    <ul class="userInfo">
                                        <li class="clearfix">
                                            <div class="fl">甲方：<span class="merchantName"></span></div>
                                            <div class="fr">乙方：深圳市七分钱网络科技有限公司</div>
                                        </li>
                                        <li class="clearfix">
                                            <div class="fl">地址：<span class="address"></span></div>
                                            <div class="fr">
                                               		地址： 深圳市福田区金田路4018号安联大厦B区 33楼B01
                                        
                                            </div>
                                        </li>
                                        <li class="clearfix">
                                            <div class="fl">联系人：<span class="contact"></span></div>
                                            <div class="fr">联系人：钱 军</div>
                                        </li>
                                        <li class="clearfix">
                                            <div class="fl">电话:<span class="phone"></span></div>
                                            <div class="fr">电话：15007558008</div>
                                        </li>
                                    </ul>
                                    <div class="content">
                                        <p>鉴于：乙方是一家依据中国正式颁布的法律设立并合法存续的有限责任公司，具备开发、运营、提供
                                            以支付为主的集成服务的资格和能力，并有权使用、维护其服务系统。</p>
                                        <p>甲方是一家依据中国正式颁布的法律设立并合法存续的有限责任公司，甲方承诺只将乙方提供的电子
                                            支付服务应用于本协议及附件约定的业务中。</p>
                                        <p>双方根据各自专长特长，愿意共同合作，乙方愿意在双方商定的领域内开展为甲方提供服务并收取支
                                            付服务费的合作业务。</p>
                                        <p>为充分发挥双方各自的优势，经友好协商，并依据中华人民共和国有关法律法规的要求，就双方在双
                                            方商定的区域内针对乙甲方相关服务的合作事宜达成一致，并签订协议如下：</p>
                                        <h4>第1条
                                            名词和定义</h4>
                                        <p>1.1七分钱服务：是指乙方向甲方及其客户所提供的，运用于电脑或移动电子设备的，以款项收付、账
                                            务结算、账户管理为核心的一系列支付服务：</p>
                                        <p>1.1.2网银支付：是七分钱向甲方及其客户提供的一种基于个人银行卡的在线支付服务。甲方的客户可
                                            通过该服务使用已开通网上银行服务的银行卡完成支付。</p>
                                        <p>1.1.3七分钱余额支付：甲方及其客户可使用存储在其七分钱账户中的现金余额完成支付。七分钱账户
                                            是指甲方、站内经营者或其客户在七分钱支付系统开立的电子支付账户。</p>
                                        <p>1.1.4快捷支付：是七分钱提供的无需开通网银的银行卡小额支付服务。甲方客户在线输入银行卡号，
                                            手机号等信息后，根据提示信息输入银行卡密码和/或手机验证码即可完成支付。</p>
                                        <p>1.1.5自助提现：是七分钱提供给甲方，由甲方登录七分钱企业版自助将七分钱账户内余额划拨至甲方
                                            指定的银行账户的提现服务。</p>
                                        <p>1.1.6委托提现：是七分钱提供给甲方，由甲方授权后，代为将其七分钱账户内余额定时或定额划拨至
                                            甲方指定银行账户的提现服务。</p>
                                        <p>1.2平台经营者:指从事第三方交易平台运营并为交易双方提供服务的经营者；站内经营者：在第三方
                                            交易平台上从事交易及有关服务活动的卖方。</p>
                                        <p>1.3客户：指向站内经营者购买商品、服务,并使用七分钱服务付款给站内经营者的买方。</p>
                                        <p>1.4七分钱服务接口（以下简称“接口”）：指七分钱开发的七分钱互联网即时到账和移动支付服务系统
                                            的接口，与甲方网站、手机APP的系统对接后，即可通过甲方系统使用“七分钱服务”。</p>
                                        <p>1.5交易流量：指由“七分钱”支付服务系统所统计的甲方及其客户通过其选定服务类型使用“七分钱
                                            服务”所完成交易的总金额。</p>
                                        <h4>第2条 合作内容</h4>
                                        <p>2.1乙方应根据本协议的规定，向甲方提供下列七分钱服务：</p>
                                        <p>2.1.1乙方提供网上安全支付服务，详细服务项目清单参见附件二。</p>
                                        <p>2.1.2提供一站式技术支持：乙方为甲方提供订单信息传输的接口规范，配置安全传输协议，后台管理
                                            权限设定；为甲方提交的订单等重要信息提供高质量的网络传输加密通道。</p>
                                        <p>2.1.3提供在线查询服务：乙方向甲方提供七分钱账户、以及基于七分钱账户的管理服务系统，可用于
                                            甲方每天二十四小时即时在线查询使用七分钱服务过程中甲方的款项收付账务明细。该查询系统列明相关
                                            交易的时间、金额等。</p>
                                        <p>2.2合作区域：双方同意合作区域为中华人民共和国境内（不含香港、澳门和台湾）。</p>
                                        <h4>第3条 支付服务费及结算</h4>
                                        <p>3.1甲方需依照2.1.1条款的约定，向乙方支付服务费：</p>
                                        <p>3.2 客户确认收货付款时，乙方将付款划转至卖方的七分钱账户内；甲方可以与站内经营者约定收取
                                            服务费，乙方按其收费标准从站内经营者（卖方）应收货款中扣付给甲方；双方认可计算过程中因手续费
                                            金额之小数点后两位进位而导致的微小误差。</p>
                                        <p>3.3甲方的客户如使用七分钱服务，向甲方成功完成付款，乙方将扣除支付服务费后的款项（无利息，
                                            下同）打款至甲方七分钱账户内。</p>
                                        <p>3.4手续费结算方式：</p>
                                        <p>3.4.1支付手续费：甲方客户支付手续费由甲方承担，乙方按附件二中约定的手续费标准直接从甲方结
                                            算款项中扣收。且按每笔交易金额计算；双方认可计算过程中因服务费金额之小数点后两位进位而导致的
                                            微小误差。</p>
                                        <p>3.4.2乙方应在每月底完成当月手续费核对并经双方确认后十[10]个工作日内，向甲方开具相应金额之
                                            正式发票。</p>
                                        <p>3.5乙方应在根据本协议附件中约定的结算周期向银行发送转账要求，实际到账时间依各银行实际情况而定。如因银行
                                            原因造成打款延误的，乙方不承担责任。甲方承担因其提供的银行账户信息不正确而造
                                            成的相应损失。</p>
                                        <p>3.6甲乙双方以人民币为结算货币。双方结算的依据是七分钱服务系统的查询结果以及银行对账凭
                                            证。两者存在差异的，以银行对账凭证为准；后者没有记录而前者有记录的，以前者的查询结果为准。</p>
                                        <p>3.7甲方提现银行账户信息，参见附件一。</p>
                                        <h4>第4条 权利与义务</h4>
                                        <p>为履行本协议第 2条所确定之合作内容，双方约定各自的权利与义务如下：</p>
                                        <p>4.1乙方权利和义务：</p>
                                        <p>4.1.1乙方负责七分钱支付系统的建设、运行和管理，保证按照乙方网站所公布的相关管理规则和运行
                                            中的技术和非技术规定向甲方提供本协议附件二中双方确认开通的各项服务。</p>
                                        <p>4.1.2乙方仅负责受理涉及“七分钱”服务本身问题的投诉并处理因此产生的纠纷。</p>
                                        <p>4.1.3因七分钱账户、密码被泄漏或被未经授权的人使用的，账户所有人应及时通知乙方，乙方应立即
                                            采取合理的措施（包括但不限于临时冻结七分钱账户及账户内资金、交易），避免损失的扩大。</p>
                                        <p>4.1.4乙方应设立业务咨询和联系电话，负责解答甲方在使用“七分钱”服务过程中遇到的各种疑问，
                                            并及时解决双方在数据对账、资金流转过程中的有关问题。</p>
                                        <p>4.1.5乙方应确保“七分钱”服务不违反现行法律、法规和规章的规定。</p>
                                        <p>4.1.6因甲方、站内经营者违规或违法操作、出现风险事件、违反协议规定等情况，乙方有权立即终止
                                            本协议。</p>
                                        <p>4.1.7乙方有合理的理由怀疑甲方平台上各方有疑似欺诈或违法交易的，乙方有权暂扣有关交易结算资
                                            金，直至风险事件调查处理完毕，风险状况消除，但应提前5天通知甲方。</p>
                                        <p>4.1.8甲方出现以下情形且经乙方通知、协商未能改进的，乙方有权终止对甲方的服务，但应提前5
                                            天通知甲方：</p>
                                        <p>4.1.8.1在正式运行后3个月内无交易；</p>
                                        <p>4.1.8.2一个月内遭到客户两次以上投诉，或遭到客户投诉未能在三个工作日内妥善处理的；</p>
                                        <p>4.1.8.3违反保密义务；</p>
                                        <p>4.1.8.4有其他严重影响双方合作行为，或给乙方造成损失的。</p>
                                        <p>4.2甲方权利和义务：</p>
                                        <p>4.2.1甲方作为平台经营者，有义务要求其站内经营者遵守本协议对甲方的约定，站内经营者违反本协
                                            议约定造成的任何投诉、纠纷或损失，甲方应先行赔付，甲方有权利向站内经营者追偿。甲方作为平台经
                                            营者同时在平台上从事站内经营业务的，应当将平台服务与站内经营业务分开，并适用于本协议对站内经
                                            营者的约定。</p>
                                        <p>4.2.2甲方应保证其向乙方提供的全部资料的真实性，如实向乙方提供有关资质文件，并在该资料发生
                                            变更后三个工作日内书面通知乙方。甲方应承担因身份资料不准确、不真实、不及时和不完整而产生的一
                                            切责任（包括但不限于发票误寄、不能联系导致的业务不能开展、乙方不能识别甲方新的身份发送的指令
                                            从而不执行甲方指令）。</p>
                                        <p>4.2.3如未向乙方提出特殊约定，甲方应在签署本协议后
                                            15个工作日内完成“七分钱”服务接口的有
                                            效对接，包括制定出可识别来自“七分钱”支付服务系统各种交易付款状态信息的软件程序。</p>
                                        <p>4.2.4甲方应在其与本服务相关的网站上，体现乙方作为其支付结算服务提供商的地位、身份，从视觉
                                            上体现乙方的产品 LOGO和服务链接，并通过甲方网站引导甲方客户进入“七分钱”支付服务。除前述约
                                            定外，甲方未经乙方书面许可的情况下，不得使用乙方的名称及标识。</p>
                                        <p>4.2.5甲方只能在本协议附件一约定的网站上使用乙方服务。不得为他人获取乙方服务，或者使用乙方
                                            提供的支付接口为其他网站或企业提供有偿或无偿的商业性服务。甲方承诺，不在第三方网站上使用乙方
                                            的接口，不将乙方提供的接口技术、安全协议及证书等以任何方式提供给第三方网站使用，不使用乙方提
                                            供的接口为第三方网站提供商业服务，甲方不得将第三方发生的网上支付交易并入或作为自己的支付交易
                                            数据，不以乙方产品为名义提供非乙方的服务链接，引导用户进入非“七分钱”服务系统提交服务申请。</p>
                                        <p>4.2.6甲方应当保证其经营活动和范围的合法性，不得超越工商营业执照核准的经营范围。甲方承诺，
                                            在使用“七分钱”服务时遵守各项法律法规及规范性文件，接受并遵守乙方网站不时公布的相关管理规
                                            则和运行中的技术和非技术规定。甲方承诺，平台内的交易行为不违反相关法律法规规定和国家有关金融
                                            监管政策要求，也不会违反乙方网站所不时公布的相关规则、具体使用“七分钱”服务过程中展现的限制
                                            或使用提示、说明。</p>
                                        <p>4.2.7甲方应妥善保管七分钱账户和密码，所有使用甲方七分钱账户、密码操作即为甲方的（授权）操
                                            作行为，因甲方七分钱账户、密码遗失、泄露、被窃所导致的损失由甲方独自承担。任何使用甲方的七分
                                            钱账户、密码向七分钱系统发出的支付指令构成甲方不可撤销的授权付款指示，乙方对于依照该指示进行
                                            支付的行为及其结果不承担任何责任。</p>
                                        <p>4.2.8甲方将在技术及网络条件许可的条件下保障相关产品的正常运行和维护，甲方有义务按《消费者
                                            权益保护法》及其他相关法规、规章维护甲方客户等消费者的合法权益，并对甲方产品或服务本身产生的
                                            投诉、纠纷等相关问题自行处理并承担责任。如果由于甲方提供的产品或服务本身原因产生的客户问题及
                                            法律责任而给乙方带来损失，甲方须承担全部赔偿责任。</p>
                                        <p>4.2.9甲方不得利用本服务从事侵害他人合法权益之行为，否则应承担所有相关法律责任，因此导致甲
                                            方受损的，应承担全部赔偿责任。上述行为包括但不限于：侵害他人名誉权、隐私权、商业秘密等合法权
                                            益；违反依法定或约定之保密义务；冒用他人名义使用本服务；从事不法交易行为，如洗钱等其他乙方认
                                            为不得使用本服务进行交易的物品等。</p>
                                        <p>4.2.10甲方同意乙方在必要时有权将其相关交易信息提供给乙方的合作银行、政府监管部门进行核查，
                                            但乙方应告知甲方获得该等信息的银行和政府监管部门的名称及披露的交易信息内容。</p>
                                        <p>4.2.11甲方有权就其账户内未涉及任何被冻结、被暂扣的款项，进行结算等操作。</p>
                                        <p>4.2.12当乙方由于任何原因暂时或者永久变更所提供的服务，包括但不限于服务内容的增加、减少、中
                                            止或终止，应提前7天以书面、电子邮件、网站公告或其他形式通知甲方；甲方应将变更的内容通过各种渠道
                                            对其自身客户进行广泛而详细的说明和解释，承担由此产生的一切责任。</p>
                                        <p>4.2.13因甲方客户盗用他人身份、银行账户、资金等导致的投诉、纠纷，乙方有义务协助甲方调查相关
                                            交易情况和账户资金收付情况，除有明确证据证明系乙方支付系统安全问题导致的损失外，均由甲方全额
                                            赔付，甲方有权向该客户追偿。</p>
                                        <p>4.2.14甲方有义务采取必要措施对甲方客户进行身份识别，保存身份资料，并对于其与甲方客户之间的
                                            交易数据资料妥善保管，保管期限不少于 5年，因保管不当或遗失导致的损失由甲方全部承担。</p>
                                        <p>4.2.15甲方应在本协议的约定范围内制定或修改完善平台运营规则，以便乙方为甲方提供相应配套支付
                                            服务，包括但不限于站内经营者准入规则，交易规则，退换货规则，消费者权益保护规则，客户服务规则，
                                            投诉、纠纷处理规则等。甲方调整相关规则应事先通知乙方，因甲方擅自调整相关规则导致乙方的支付服
                                            务出现差错的，相关责任应由甲方承担。</p>
                                        <p>4.2.16甲方应当为乙方提供交易的真实背景，至少要在交易备注里体现出来，乙方会定期审核实际运营
                                            内容与企业营业执照内的范围是否相符，若不相符，甲方必须及时整改，若整改期间产生盗刷或其他风险，
                                            责任由甲方承担。乙方将有权限制甲方交易额度以及关闭通道。</p>
                                        <h4>第5条 协议有效期限</h4>
                                        <p>本合同自双方正式盖章之日起生效，有效期为一年（12个自然月）。本合同期满后，如双方在合同有
                                            效期到期前30天未提出异议，合同自动延续一年。</p>
                                        <p>本合同自双方正式盖章之日起生效，有效期为一年（12个自然月）。本合同期满后，如双方在合同有
                                            效期到期前30天未提出异议，合同自动延续一年。</p>
                                        <h4>第6条 客户服务</h4>
                                        <p>6.1乙方仅负责处理由于“七分钱”服务问题所引起的客户咨询、申告和投诉。</p>
                                        <p>6.2
                                            甲方负责承担因其销售产品或提供服务而引起的客户咨询和投诉，甲方应告诉客户在使用这项服
                                            务时可能碰到的问题及相应的解决办法，并在其宣传资料和网站的突出位置标明其客户服务热线和客户服
                                            务办法等相关内容。</p>
                                        <p>6.3乙方收到应由甲方受理的客户投诉的，应转到甲方处理，妥善引导客户转向甲方的客服热线进行
                                            咨询，并有权督促甲方妥善解决客户投诉。对于各种非系统技术原因引起的用户的投诉，甲方负责在收到
                                            乙方转交问题后的5个工作日内最终妥善解决用户的问题。</p>
                                        <h4>第7条 知识产权</h4>
                                        <p>甲乙双方通过签订本协议视为互相授权对方为合作方，在广告宣传、用户引导等方面使用对方公司
                                            名称、LOGO等，须提前告知对方，并取得对方同意后方可使用。因履行本协议所使用对方的软件、系统、
                                            技术、商标等，均不视为知识产权的任何转让。甲方不得侵犯乙方的知识产权和商业秘密，不得在未经乙
                                            方书面同意的情况下，将乙方提供的相关软件和技术文档等提供给关联或无关联的第三方使用。</p>
                                        <h4>第8条 保密条款</h4>
                                        <p>8.1甲乙双方应共同采取一切必要措施，对双方的合作内容以及因通过本次合作而从对方取得的商业
                                            秘密采取保密措施，以防止商业秘密被泄露、使用、被公开或落入未经授权人士手中。</p>
                                        <p>8.2获取商业秘密的一方(下称“获取方”)不得将从提供商业秘密的一方(下称“提供方”)处所获得
                                            的商业秘密资料在未获得提供方授权，或未获得本合同许可的情况下，进行部分或全部复制或复印；在本
                                            合同终止后将商业秘密资料悉数返还提供方，或在取得提供方书面同意的情况下，将商业秘密销毁。</p>
                                        <p>8.3获取方应只在本合同规定范围内对商业秘密进行使用。并且，获取方已促成或将促成其被告知商
                                            业秘密的雇员和顾问签署内容实质与本合同本条规定相类似的保密合同。</p>
                                        <p>8.4双方同意对本次合作涉及的所有结算价格、以及其他的敏感信息，应严格保守秘密，如因某一方
                                            的原因导致上述信息泻露，责任方应承担相应的赔偿责任。</p>
                                        <p>8.5除非依据法律规定允许向社会公开某项商业秘密或经商业秘密提供方书面同意公开或某项商业
                                            秘密已经依法进入公有领域，本条规定的保密义务没有期限限制，具有永久性，不受本合同是否成立、生
                                            效、终止和无效的影响。</p>
                                        <p>8.6甲方应严格遵守《银联卡账户及交易数据安全管理规则》的有关规定，包括但不限于：甲方不得
                                            将客户的账户信息和交易数据用于除此之外的任何其他用途；未经客户同意，不得将账户信息以及交易数
                                            据信息披露给关联或无关联的第三方；甲方不得以任何方式保存客户的银行卡磁道信息、卡片验证码、密
                                            码及卡片有效期等信息；甲方应将存有客户账号信息的介质保存在安全领域，并只允许经授权人员接触，
                                            严禁泄漏给任何关联或无关联的第三方。甲方需承担因自身管理不善，导致发生账户信息安全问题而造成
                                            的损失。</p>
                                        <h4>第9条 协议的解除</h4>
                                        <p>9.1除法律规定外，若发生下列情况之一，甲乙双方任一方有权在书面通知对方后，决定单方解除本协议：</p>
                                        <p>9.1.1乙方或甲方拥有的相关从业资格被政府行政部门取消或注销；</p>
                                        <p>9.1.2乙方或甲方违反其各自在本协议中所做的关于其主体真实性与合法性的陈述与担保；</p>
                                        <p>9.1.3本协议的任何一方依法进入破产或清算程序；</p>
                                        <p>9.1.4本协议任何一方对本协议的任何违反，且在另一方以书面形式告知违约方违约事实五(5)日后，违
                                            约方仍未采取措施纠正的。</p>
                                        <p>9.2如一方没有正当理由迟延履行本协议，且在另一方以书面形式告知违约方迟延之事实五(5)日后，违
                                            约方仍未采取措施改正，则另一方有权要求违约方继续履行协议或决定解除协议，并要求违约方承担全部
                                            损失赔偿责任。</p>
                                        <p>9.3本协议终止后，对于协议终止前的交易，乙方仍有权向甲方及其站内经营者查询及追索。</p>
                                        <h4>第10条 不可抗力与责任免除</h4>
                                        <p>10.1本协议任一方对其因不可抗力而不能或延迟履行本协议项下任何义务给对方造成的任何损失不
                                            承担任何责任。如果发生不可抗力事件，则受影响的一方应立即以可能的最为快捷的方式通知对方，并在
                                            不可抗力事件发生十五天内向对方出具能有效证明该不可抗力事件发生的文件。遭受不可抗力影响的一方
                                            应采取积极有效的措施以尽量减少因本协议不能或延迟履行而给对方造成的损失。一方因不可抗力而延迟
                                            履行其相关义务的事件应与不可抗力持续的时间相同。</p>
                                        <p>10.2乙方对于因为以下不可抗力原因或其他不可归因于乙方的原因而造成的甲方或他方损失不承担
                                            责任：</p>
                                        <p>10.2.1政府行为，如禁令、强制行为、管制等；</p>
                                        <p>10.2.2自然灾害，如地震、洪水、海啸等；</p>
                                        <p>10.2.3战争、动乱等；</p>
                                        <p>10.2.4黑客攻击、计算机病毒侵入或发作；</p>
                                        <p>10.2.5计算机系统遭到不可归因于乙方原因的破坏、瘫痪或无法正常使用而导致信息或纪录的丢失以
                                            及不能提供本协议项下之销售服务；</p>
                                        <p>10.2.6电信部门技术调整、线路改造等导致之重大影响；</p>
                                        <p>10.2.7因政府命令而造成的暂时性关闭等；</p>
                                        <p>10.2.8其它非乙方造成的原因。</p>
                                        <h4>第11条 违约责任</h4>
                                        <p>11.1甲乙双方应当严格按照本协议的约定履行义务，任何一方违约，均应对守约方的损失承担全部赔
                                            偿责任。</p>
                                        <p>11.2为有效提供服务，乙方的“七分钱”服务将不定期进行维护和检测，对此乙方将提前5天在乙方
                                            网站公告并邮件通知甲方，因此产生的服务中断或不稳定，不视乙方违约。</p>
                                        <p>11.3本合同有效期内，因国家相关主管部门颁布、变更的法令、政策以及其他非乙方原因而导致乙方
                                            不能提供约定服务的，不视为乙方违约，双方应根据相关的法令、政策以及实际情况等变更合同内容。</p>
                                        <p>11.4任何一方欲终止本协议的合作事项的，应当提前一个月以书面形式通知合作他方，如任何一方违
                                            反协议约定或无故解除协议均应承担相应的违约责任。</p>
                                        <p>11.5违约方在收到守约方要求纠正其违约行为的书面通知之日，应立即停止其违约行为，并在十（10）
                                            个工作日内赔偿守约方因此受到的全部损失。</p>
                                        <h4>第12条 其他</h4>
                                        <p>12.1双方理解并且同意，协议双方均为独立签约人。本协议不包含任何可能理解成为双方之间设立一
                                            种合伙或合资关系的内容。</p>
                                        <p>12.2未经协议双方书面确认，无论以任何方式或形式，各方均不得将本协议或其部分进行转让。</p>
                                        <p>12.3通知：任何本协议要求或允许的通知应以书面方式并应通过邮寄或传真方式按协议载明的通讯地
                                            址寄达对方。通知自收到之日起生效。</p>
                                        <p>12.4本协议在履行过程中如有未尽事宜，或因业务发展需要对本协议现有内容进行补充、变更、修改
                                            时，由双方或任何一方提出补充、变更、修改的建议和方案，经双方协商并达成统一意见后，以书面形式
                                            表达并经双方同意并盖章后，成为本协议的补充协议，与本协议具有同等法律效力，补充协议如与本协议
                                            不一致的，以补充协议为准。</p>
                                        <p>12.5本协议任何部分被视作无效或不可执行，将不会因此而影响本协议其它条款或部分的有效性与可
                                            执行性。</p>
                                        <p>12.6本协议的订立、效力、解释、履行和争议的解决均适用中华人民共和国法律。因本协议所产生的
                                            以及因履行本协议而产生的任何争议，双方均应本着友好协商的原则加以解决。协商解决未果，任何一方
                                            有权向乙方住所地人民法院提起诉讼。</p>
                                        <p>12.7本协议在双方盖章后正式生效。自本协议生效后，双方应严格按照本协议的约定履行本协议中产
                                            生的所有权利和义务。</p>
                                        <p>12.8本协议附件一：《七分钱支付服务开通申请表>>，本协议附件二：《七分钱支付服务开通价
                                            格表>></p>
                                        <p>12.9本协议附件作为本协议不可分割的一部分，与本协议具有同等效力，但本协议附件与本协议主体
                                            约定不一致的，以本协议附件为准。</p>
                                        <p>12.10本协议及其附件以中文做成，本协议一式两份，双方各执一份，均具有同等法律效力。</p>
                                    </div>
                                    <ul class="userInfo">
                                        <li class="clearfix">
                                            <div class="fl">甲方：（盖章）<span class="merchantName"></span></div>
                                            <div class="fr">乙方：（盖章）深圳市七分钱网络科技有限公司</div>
                                        </li>
                                        <li class="clearfix">
                                            <div class="fl">地址：<span class="address"></span></div>
                                            <div class="fr">
                                                	地址：深圳市福田区金田路4018号安联大厦B区 33楼B01
                                                
                                            </div>
                                        </li>
                                        <li class="clearfix">
                                            <div class="fl">联系人：<span class="contact"></span></div>
                                            <div class="fr">联系人：钱 军</div>
                                        </li>
                                        <li class="clearfix">
                                            <div class="fl">电话:<span class="phone"></span></div>
                                            <div class="right">电话：15007558008</div>
                                        </li>
                                        <li class="clearfix">
                                            <div class="fl">时间:　2014　年 2 月   1 日</div>
                                            <div class="fr">时间:　2014　年 2 月   1 日</div>
                                        </li>
                                    </ul>
                                    <div class="application">
                                        <div class="table1">
                                            <h3>七分钱支付服务开通申请表（附件一）</h3>
                                            <p>本表格为贵司与深圳市七分钱网络科技有限公司签署的《七分钱支付服务合作协议》（以下简称“主协
                                                议”）的附件，系主协议不可分割的一部分，签署本表格即表示贵司已充分知悉、理解主协议项下所有条款
                                                的含义，充分认知、认同相应的权利、义务与责任，同时保证上述信息真实、合法、有效，并承诺将支付服
                                                务用于正当合法的用途，否则愿承担一切法律责任。</p>
                                            <table border="1" width="100%">
                                                <tr>
                                                    <td colspan="3" style="text-align: center">甲方信息</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3">甲方网站网址:</td>
                                                </tr>
                                                <tr>
                                                    <td rowspan="3" width="20%">甲方提现银行<br/>账户信息</td>
                                                    <td width="25%">账户名称</td>
                                                    <td width="50"><span class="contact"></span></td>
                                                </tr>
                                                <tr>
                                                    <td>开户银行(精确到分行)</td>
                                                    <td><span class="bank"></span></td>
                                                </tr>
                                                <tr>
                                                    <td>银行账号</td>
                                                    <td><span class="bankNum"></span></td>
                                                </tr>
                                                <tr>
                                                    <td>结算周期</td>
                                                    <td colspan="2">结算周期是指乙方在收到该提现申请后向银行发送转账要求的时间（如遇法定节假日
                                                        顺延），实际到账时间依各银行实际情况而定。
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3" style="text-align: center">乙方信息</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3">乙方网站网址 :www.qifenqian.com</td>
                                                </tr>
                                                <tr>
                                                    <td rowspan="3" width="20%">乙方账户信息</td>
                                                    <td width="25%">账户名称</td>
                                                    <td width="50">深圳市七分钱网络科技有限公司</td>
                                                </tr>
                                                <tr>
                                                    <td>开户银行(精确到分行)</td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td>银行账号</td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td>备注</td>
                                                    <td colspan="2"></td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="table2">
                                            <h3>七分钱支付服务开通价格表（附件二）</h3>
                                            <table border="1" width="100%" style="text-align: center">
                                                <tr>
                                                    <td colspan="4" style="text-align: center">申请服务内容与费率</td>
                                                </tr>
                                                <tr>
                                                    <td>序号</td>
                                                    <td>服务项目</td>
                                                    <td>服务内容</td>
                                                    <td>手续费</td>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td class="merchantProduct"></td>
                                                    <td>微信.支付宝</td>
                                                    <td class="merchantRate"></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
						</table>
		         </div>
		         <div class="modal-footer">
						<button type="button" class="btn btn-default closeBtn" data-dismiss="modal">关闭</button>
		         </div>
		      </div><!-- /.modal-content -->
		    </div>
		</div>
	
</body>
</html>

