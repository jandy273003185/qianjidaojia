/**
 * 
 */
package com.qifenqian.bms.basemanager.app.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.app.bean.TdAppEditionControl;

/**
* @ClassName: TdAppEditionControlMapping
* @Description: TODO(这里用一句话描述这个类的作用)
* @author LvFeng
* @date 2019年12月18日
*
*/
@MapperScan
public interface TdAppEditionControlMapper {
	List<TdAppEditionControl> listTdAppEditionControl(TdAppEditionControl tdAppEditionControl);
	
	int updateTdAppEditionControl(TdAppEditionControl tdAppEditionControl);
	
	int insertTdAppEditionControl(TdAppEditionControl tdAppEditionControl);

	int updateTdAppEditionControlLose(TdAppEditionControl tdAppEditionControl);
}
