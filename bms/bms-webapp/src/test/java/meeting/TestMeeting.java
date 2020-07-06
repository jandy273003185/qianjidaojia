package meeting;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qifenqian.bms.accounting.utils.DictionaryUtils;
import com.qifenqian.bms.meeting.MeetingConstant;
import com.qifenqian.bms.meeting.helper.JPushService;
import com.qifenqian.bms.meeting.prize.bean.PrizeBean;
import com.qifenqian.bms.meeting.prize.mapper.PrizeMapper;
import com.qifenqian.bms.meeting.prize.service.PrizeService;
import com.qifenqian.bms.meeting.prize.type.AmountType;
import com.qifenqian.bms.meeting.prize.type.CustScope;
import com.qifenqian.bms.meeting.prize.type.IsRepeatWin;
import com.qifenqian.bms.meeting.prize.type.LimitSex;
import com.qifenqian.bms.meeting.prize.type.OverallControlType;
import com.qifenqian.bms.meeting.prize.type.PrizeStatus;
import com.qifenqian.bms.meeting.prize.type.PrizeType;

/**
 * @project sevenpay-bms-web
 * @fileName UserTest.java
 * @author huiquan.ma
 * @date 2015年5月8日
 * @memo 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/ApplicationContext.xml")
public class TestMeeting{

	@Autowired
	private PrizeService prizeService;
	@Autowired
	private PrizeMapper prizeMapper;
	@Autowired
	private DictionaryUtils dictionaryUtils;
	@Autowired
	private JPushService jPushService;
	
	@Test
	public void insert() {
		try {
			PrizeBean insertBean = new PrizeBean();
			insertBean.setPrizeNo("MT011");
			insertBean.setActivityId(2);
			insertBean.setPrizeCode("MT011");
			insertBean.setPrizeName("二等联合随机分配");
			insertBean.setPrizeType(PrizeType.LUCK_DRAW);
			insertBean.setOverallControlType(OverallControlType.JOINT);
			insertBean.setQuotaNumber(30);
			insertBean.setWinNumber(0);
			insertBean.setQuotaAmount(new BigDecimal(333.33));
			insertBean.setWinAmount(new BigDecimal(0));
			insertBean.setOddsFactor(1);
			insertBean.setAmountType(AmountType.RANDOM);
			insertBean.setFixedAmount(new BigDecimal(0));
			insertBean.setRandomAmountMin(new BigDecimal(0));
			insertBean.setRandomAmountMax(new BigDecimal(0));
			insertBean.setPrizeSort(7);
			insertBean.setEffectiveHours(24);
			insertBean.setCustScope(CustScope.ALL);
			insertBean.setIsRepeatWin(IsRepeatWin.N);
			insertBean.setLimitSex(LimitSex.ALL);
			insertBean.setLimitRegisterDateStart(null);
			insertBean.setLimitRegisterDateEnd(null);
			insertBean.setMemo(null);
			insertBean.setStatus(PrizeStatus.INIT);
			insertBean.setInstUser(0);
			
			prizeService.insertPrizeBean(insertBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void start() {
		try {
			prizeService.startPrize(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void sendSingle() {
		try {
			PrizeBean prize = prizeMapper.selectPrizeSingleById(10);
			// 推送内容
			String title = dictionaryUtils.getDataValueByPath(MeetingConstant.MESSAGE_TITLE_KEYWORD)
								.replace("{prizeName}", prize.getPrizeName())
								.replace("{activityName}", prize.getActivityName());
			
			
			String content = dictionaryUtils.getDataValueByPath(MeetingConstant.MESSAGE_CONTENT_KEYWORD)
								.replace("{activityName}", prize.getActivityName())
								.replace("{prizeName}", prize.getPrizeName())
								.replace("{prizeId}", String.valueOf(prize.getPrizeId()));
			
	
			String[] custIdArray = new String[]{
					"5c7aa347ca114bc4b0cdc2f28d3d8dcc",
					"ff29b4b052834809a3bcd25e1d880bee"		
			};
			
			for(String custId : custIdArray) {
				
				jPushService.sendToSpecifiedUser(custId, title, content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


