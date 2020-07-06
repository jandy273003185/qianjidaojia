/**
 * 
 */
package com.qifenqian.bms.basemanager.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.app.bean.TdAppEditionControl;
import com.qifenqian.bms.basemanager.app.mapper.TdAppEditionControlMapper;
import com.qifenqian.bms.platform.web.page.Page;

/**
* @ClassName: AppEditionManagerDao
* @Description: TODO(这里用一句话描述这个类的作用)
* @author LvFeng
* @date 2019年12月18日
*
*/
@Repository
public class AppEditionManagerDao {
	@Autowired
	private TdAppEditionControlMapper appEditionControlMapper;
	@Page
	public List<TdAppEditionControl> listTdAppEditionControl(TdAppEditionControl tdAppEditionControl) {
		return appEditionControlMapper.listTdAppEditionControl(tdAppEditionControl);
	}
	
	public int updateTdAppEditionControl(TdAppEditionControl tdAppEditionControl) {
		return appEditionControlMapper.updateTdAppEditionControl(tdAppEditionControl);
	}
	
	public int insertTdAppEditionControl(TdAppEditionControl tdAppEditionControl) {
		return appEditionControlMapper.insertTdAppEditionControl(tdAppEditionControl);
	}

	public int updateTdAppEditionControlLose(TdAppEditionControl tdAppEditionControl) {
		return appEditionControlMapper.updateTdAppEditionControlLose(tdAppEditionControl);
		
	}
}
