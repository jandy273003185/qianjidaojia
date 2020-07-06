package com.qifenqian.bms.basemanager.market.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.market.bean.Manager;
import com.qifenqian.bms.basemanager.market.bean.MarketRole;
import com.qifenqian.bms.platform.web.admin.user.bean.User;



@MapperScan
public interface ManagerMapper {

	List<Manager> selectManagerList(Manager manager);

	void updateManagerList(Manager manager);

	List<Manager> selectNewManagerList(Manager manager);

	List<MarketRole> selectManangerJurisdiction(MarketRole role);

	void insertNewManagerList(Manager manager);
	
	Manager getManager(String userCode);

	List<User> selectManageUserInfo();

}
