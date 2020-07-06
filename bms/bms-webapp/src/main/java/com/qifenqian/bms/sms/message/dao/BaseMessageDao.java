package com.qifenqian.bms.sms.message.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.platform.web.page.Page;
import com.qifenqian.bms.sms.message.bean.BaseMessage;
import com.qifenqian.bms.sms.message.mapper.BaseMessageMapper;
/**
 * 
 * @author shen
 *
 */
@Repository
public class BaseMessageDao {
	
	@Autowired
	private BaseMessageMapper baseMessageMapper;
	
	@Page
	public List<BaseMessage> selectBaseMessageList(BaseMessage queryBean) {
		return baseMessageMapper.selectBaseMessageList(queryBean) ;
	}

}
