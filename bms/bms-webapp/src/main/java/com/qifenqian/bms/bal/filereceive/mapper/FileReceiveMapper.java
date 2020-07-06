package com.qifenqian.bms.bal.filereceive.mapper;

import java.util.List;

import com.qifenqian.bms.bal.filereceive.bean.FileReceive;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;




/**
 * 
 * @author li.shi
 * @date 2017年2月23日
 *
 */

@MapperScanCombinedmaster 
public interface FileReceiveMapper {
	
	List<FileReceive> selectFileReceiveList(FileReceive queryBean);

	FileReceive selectByFileName(FileReceive queryBean);

	int updateFile(FileReceive updateBean);

	int insertFile(FileReceive insertBean);
	
	/*public List<AgentMerInfo> selectAgentMerInfo(AgentMerInfo agentMerInfo);*/
	
}
