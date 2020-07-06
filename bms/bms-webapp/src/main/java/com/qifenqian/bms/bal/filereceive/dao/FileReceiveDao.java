package com.qifenqian.bms.bal.filereceive.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.bal.filereceive.bean.FileReceive;
import com.qifenqian.bms.bal.filereceive.mapper.FileReceiveMapper;
import com.qifenqian.bms.platform.web.page.Page;


/**
 * 
 * @author li.shi
 * @date 2017年2月23日
 */
@Repository
public class FileReceiveDao {
	
	@Autowired
	private FileReceiveMapper fileReceiveMapper;

	@Page
	public List<FileReceive> selectFileReceiveList(FileReceive queryBean) {
		
		return fileReceiveMapper.selectFileReceiveList(queryBean);
	}
	
	/*public List<AgentMerInfo> selectAgentMerInfo(AgentMerInfo agentMerInfo) {
		return fileReceiveMapper.selectAgentMerInfo(agentMerInfo);
	}*/

}
