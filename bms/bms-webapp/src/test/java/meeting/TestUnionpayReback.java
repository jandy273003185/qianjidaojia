package meeting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stc.gateway.unionpay.AsynResponseListener;
import com.stc.gateway.unionpay.impl.refund.bean.RefundAsynRequestBean;

/**
 * @project sevenpay-bms-web
 * @fileName UserTest.java
 * @author huiquan.ma
 * @date 2015年5月8日
 * @memo 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/ApplicationContext-http.xml")
public class TestUnionpayReback{

	@Autowired
	private AsynResponseListener<RefundAsynRequestBean> asynResponseListener;
	@Test
	public void insert() {
		try {
			RefundAsynRequestBean requestBean = new RefundAsynRequestBean();
			asynResponseListener.execute(requestBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


