package com.qifenqian.bms.basemanager.virtual.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.virtual.bean.AgentInfo;
import com.qifenqian.bms.basemanager.virtual.bean.MerchantInfo;
import com.qifenqian.bms.basemanager.virtual.bean.MerchantTradeInfo;
import com.qifenqian.bms.basemanager.virtual.service.VirtualService;

@Controller
@RequestMapping("/basemanager/virtual")
public class VirtualController {

	@Autowired
	private VirtualService virtualService;
	
	@RequestMapping("/list")
	public ModelAndView merchantData(MerchantTradeInfo info,HttpServletRequest request){
		String isFirst = request.getParameter("isFirst");
		if(StringUtils.isEmpty(isFirst)){
			info.setBeginMonth("201801");
			info.setEndMonth("201812");
		}
		ModelAndView mv = new ModelAndView("/basemanager/virtual/list");
		List<MerchantTradeInfo> infos = virtualService.getTradeInfos(info);
		String totalAmt = virtualService.getTotalAmt(info);
		BigDecimal allSum = virtualService.getTotal(infos);
		mv.addObject("infos",infos);
		mv.addObject("queryBean",info);
		mv.addObject("isFirst","No");
		mv.addObject("allSum",allSum);
		mv.addObject("totalAmt",totalAmt);
		return mv;
		
	}
	
	@RequestMapping("/merchantList")
	public ModelAndView merchant(MerchantInfo info,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("/basemanager/virtual/merchantList");
		List<MerchantInfo> infos = virtualService.getInfos(info);
		for(MerchantInfo merchantInfo: infos){
			merchantInfo.setAddress(add());
			merchantInfo.setContact(contact());
			merchantInfo.setPhone(phone());
			merchantInfo.setBank(bank());
			merchantInfo.setBankNum(bankNum());
		}
		mv.addObject("infos",JSONObject.toJSON(infos));
		mv.addObject("queryBean",info);
		return mv;
		
	}
	
	@RequestMapping("/agentList")
	public ModelAndView merchant(AgentInfo info,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("/basemanager/virtual/agentList");
		List<AgentInfo> infos = virtualService.getAgentInfos(info);
		
		
		for(AgentInfo agentInfo: infos){
			agentInfo.setAddress(add());
			agentInfo.setContact(contact());
			agentInfo.setPhone(phone());
			agentInfo.setProtocolId(protoId());
			agentInfo.setProtocolRate(protoRate());
			agentInfo.setBank(bank());
			agentInfo.setBankNum(bankNum());
		}
		mv.addObject("infos",JSONObject.toJSON(infos));
		mv.addObject("queryBean",info);
		return mv;
		
	}
	
	public String protoId(){
		String protocal[]= {"1","2","3","4","5","6","7"};
		Random x = new Random();
		return protocal[x.nextInt(7)];
	}
	
	public String protoRate(){
		String protocalRate[]= {"0.0038","0.0045","0.0035","0.0043","0.0055","0.006","0.007"};
		Random x = new Random();
		return protocalRate[x.nextInt(7)];
	}
	public String add(){
		String address[] ={
				"苏州市吴中区石湖西路188号南京师范大学科技园18楼D2座",
				"深圳市宝安区石岩街道应人石社区天宝路13号雅丽工业园宿舍4栋113-115",
				"深圳市福田区沙头街道新洲九街嘉葆润金座一期A栋605室",
				"深圳市福田区沙头街道上沙塘晏村十二巷2号B106铺",
				"深圳市福田区莲花街道深南大道2008号中国凤凰大厦109-2号",
				"武汉市东湖新技术开发区珞瑜路鲁巷广场西北角武汉光谷中心花园A",
				"深圳市宝安区新安街道翻身路海富一巷中心大厦605",
				"长沙市雨花区迎新路御溪国际1栋",
				"惠阳区淡水上塘西路120号",
				"成都市金牛区黄忠街8号1楼3号",
				"厦门市思明区高雄路18号408室",
				"福建省福州市台江区鳌峰路2号鳌峰广场1号楼6层ZC606#",
				"广州市天河区员村四横路自编10号-6-1",
				"深圳市大鹏新区大鹏街道鹏飞路较场尾较四西五巷2号",
				"东莞市厚街镇新塘社区家具大道名家居世博园9号馆负一楼F06",
				"深圳市福田区皇岗公园一街龙轩豪庭112号",
				"深圳市罗湖区东门街道深南东路3020号百货广场大厦西座13楼72-57",
				"合肥市滨湖区徽州大道6699号滨湖时代广场2幢1202室",
				"深圳市南山区第五工业区朗山路东物商业大楼第一层0101东区北",
				"深圳市南山区南山街道东滨路迪雅大厦一楼泰晟峰服装城A21",
				"河南省南阳市经十路与纬八路交叉口向南200米",
				"浙江省杭州市西湖区黄姑山路29号颐高创业大厦705室",
				"深圳市宝安区福永街道办富桥工业二区蚝业路1号",
				"成都市成华区荆竹坝路91号1栋4层438号",
				"广州市越秀区环市东路371-375号S2207房",
				"北京朝阳区东三环南路25号2至17层内9层911",
				"广东省广州市天河区中山大道中38号加悦大厦1706房",
				"珠海市拱北名門大廈902室",
				"牡丹江市西安区西平安街17号",
				"北京市海淀区中关村大街18号科贸电子城2B55",
				"南京市栖霞区八卦洲街道鹂岛路268号服贸区A区8-132",
				"深圳市龙华新区华清大道200号花半里清湖花园3栋20B",
				"深圳市福田区华强北街道振兴路桑达工业区419栋1F043号",
				"上海市嘉定区真南路4268号2幢J35室",
				"南京市秦淮区汉中路27号702室",
				"广东省深圳市南山区铜鼓路大冲国际中心35楼",
				"深圳市南山区侨香路智慧广场商业中心BM49号",
				"深圳市福田区国利大厦1736号",
				"重庆市九龙坡白市驿镇中心街60号2-2-2",
				"辽宁省大连经济技术开发区润海园中区58号1单元19层2号"
		};
		
		Random x = new Random();
		return address[x.nextInt(40)];
	}
	
	
	public String bankNum(){
		String bankNum[]={
				"6214852104575926",
				"6217858000048721754",
				"6214852109895808",
				"6215583700002096624",
				"6228480799606124870",
				"44027101040001054",
				"6226220620863521",
				"6214832015412831",
				"6555444545454546",
				"6214830108918441",
				"6230200165605093",
				"6230200151725939",
				"6228480128192077377",
				"6230580000025842647",
				"6228480402564890018",
				"6214856550047777",
				"6228480086829480572",
				"6226097805076487",
				"6217852000015046295",
				"6225887861793111",
				"6228480469722890974",
				"6214837601858735",
				"6217003760041060005",
				"362502199308162626",
				"6225212603922003",
				"6214830106767964",
				"6228481466077826479",
				"6222980000643041",
				"9550880204298200177",
				"3602002609200129246",
				"2201080509100072271",
				"44250100013100000929",
				"41017700040014363",
				"416080100100225199",
				"79330155200000865",
				"4000050919100410000"
				
		};
		Random x = new Random();
		return bankNum[x.nextInt(35)];
	}
	public String bank(){
		String bankName[]= {
				"中国银行增城市支行",
				"中国银行韶关市中心支行",
				"中国工商银行沈阳东北大学支行",
				"中国工商银行沈阳砂山街第二支行",
				"中国工商银行牡丹江市分行新华路支行",
				"中国农业银行股份有限公司安平县支行",
				"中国农业银行股份有限公司景县支行",
				"中国农业银行股份有限公司太原新建支行",
				"中国农业银行铁岭分行",
				"中国建设银行股份有限公司萍乡楚萍路支行",
				"中国建设银行股份有限公司萍乡经济开发区支行",
				"中国建设银行股份有限公司九江市分行",
				"交通银行长春德惠路支行",
				"交通银行长春高新技术开发区支行",
				"中信银行杭州富阳支行",
				"中信银行杭州江东支行",
				"中国光大银行上海大华支行",
				"华夏银行股份有限公司青岛胶州支行",
				"中国民生银行深圳分行龙岗支行",
				"招商银行股份有限公司绍兴城西支行",
				"招商银行股份有限公司金华分行",
				"上海浦东发展银行无锡新区支行",
				"江苏银行股份有限公司南通静海支行",
				"长乐市农村信用合作联社沙京分社",
				"中国工商银行股份有限公司成都罗家碾支行",
				"中国工商银行股份有限公司成都创业路支行",
				"中国农业银行股份有限公司上海高行支行",
				"中国农业银行龙海市支行营业部",
				"中国建设银行股份有限公司江阴云亭支行",
				"中国建设银行股份有限公司江阴长江支行",
				"中国建设银行股份有限公司江阴开发区支行",
				"中国建设银行股份有限公司江阴城南支行",
				"交通银行长春明珠支行",
				"交通银行长春朝阳支行",
				"交通银行股份有限公司吉林东大支行",
				"中信银行宁波北仑支行",
				"华夏银行武汉汉口支行",
				"华夏银行股份有限公司广州番禺支行",
				"华夏银行股份有限公司广州海珠支行",
				"中国民生银行股份有限公司郫县支行",
				"中国民生银行股份有限公司重庆分行",
				"中国民生银行股份有限公司重庆冉家坝支行"
				};
		Random x = new Random();
		return bankName[x.nextInt(42)];
	}
	
	public String contact(){
		String contact[] ={
			"曾维佳",	
			"麦鲁新",	
			"谢周苗",	
			"徐利贤",	
			"程志康",	
			"朱雁明",	
			"刘刚毅",	
			"付日祥",	
			"王海燕",	
			"伍红军",	
			"吾笑会",	
			"李小楚",	
			"袁美丽",	
			"周国中",	
			"黄彬",	
			"吴金如",	
			"王鑫",	
			"季佰印",	
			"钟开亮",	
			"仇楚云",	
			"张志奇",	
			"唐芳艳",	
			"田静",	
			"尤佳丽",	
			"李名珍",	
			"苏月勇",	
			"洪彩欣",	
			"李华",	
			"云中鹤",
			"程金鹏",
			"曹文浩",
			"妙帮贷",
			"叶伟山",
			"林足",
			"王林",
			"李娜",
			"覃光宁",
			"吕朝杰",
			"欧林娜",
			"毛彩轩",
			"黄伟明",
			"邓豪"
			
		};
		
		Random x = new Random();
		return contact[x.nextInt(42)];
	}
	
	
	public String phone(){
		String phone[] ={
			"18971115808",	
			"18521595799",	
			"18588656800",	
			"15013181822",	
			"18321261720",	
			"13886070003",	
			"13301241021",	
			"18612828839",	
			"13238042588",	
			"13548117844",	
			"13202187434",	
			"18612611345",	
			"15814439086",	
			"13715336681",	
			"13871293123",	
			"18919194086",	
			"13590407633",	
			"13510746689",	
			"13133935367",	
			"15959511531",	
			"15158811215",	
			"15955149114",	
			"13724379592",	
			"13018078000",	
			"13670193575",	
			"18801785491",	
			"13534282003",	
			"13760176508",	
			"18613238223",
			"13728626013",
			"18319778777",
			"18201319042",
			"13655013107",
			"13430795184",
			"13549822223",
			"15107384868",
			"15311831918",
			"18938803362",
			"13670038065",
			"18601981219",
			"18659517788",
			"15108228317"
		};
		
		Random x = new Random();
		return phone[x.nextInt(42)];
	}
}
