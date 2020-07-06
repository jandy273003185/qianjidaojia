package com.qifenqian.bms.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.qifenqian.bms.demo.ActBankSevenFlow;
import com.qifenqian.bms.demo.ActBankSevenMapper;
import com.qifenqian.bms.platform.web.admin.function.bean.Function;
import com.qifenqian.bms.platform.web.admin.function.service.FunctionService;

/**
 * 
 * oper:业务系统
 * 		所有的mapper添加注解 MapperScan
 * 		事务使用 @Transcation("oper")/@Transcation
 * core:核心系统'
 * 		所有的mapper添加注解MapperScanCore
 * 		事务使用 @Transcation("core")
 * 
 * 	tips: 暂不支持双事务，务必拆分事务，各自保证成功
 * 		双事务需要JTA分布式事务，但听说性能很差，先不搞了
 * 
 * 
 * 
 * 
 * 
 * @project sevenpay-bms-web
 * @fileName TestDataSource.java
 * @author huiquan.ma
 * @date 2015年7月22日
 * @memo 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/ApplicationContext.xml")
public class TestDataSource {
	
	@Autowired
	private FunctionService functionService;
	@Autowired
	private ActBankSevenMapper actBankSevenMapper;

	@Test
	@Transactional("oper")
	public void testOper() {
		List<Function> functionList = functionService.selectAllFuntion();
		for(Function f : functionList) {
			System.out.println(f.getFunctionId());
		}
	}
	
	@Test
	@Transactional("core")
	public void testCore() {
		List<ActBankSevenFlow> flowList = actBankSevenMapper.selectBankList();
		for(ActBankSevenFlow f : flowList) {
			System.out.println(f.getAcctId());
		}
	}
	
	@Test
	public void testOperAndCore() {
		List<Function> functionList = functionService.selectAllFuntion();
		for(Function f : functionList) {
			System.out.println(f.getFunctionId());
		}
		List<ActBankSevenFlow> flowList = actBankSevenMapper.selectBankList();
		for(ActBankSevenFlow f : flowList) {
			System.out.println(f.getAcctId());
		}
	}
}


