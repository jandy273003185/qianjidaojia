package com.qifenqian.bms.expresspay.jgkjfileupload.mapper;

import java.util.List;

import com.qifenqian.bms.common.annotation.MapperScanCore;
import com.qifenqian.bms.expresspay.jgkjfileupload.bean.FileOffer;

@MapperScanCore
public interface FileOfferMapper {
	
	int insert(FileOffer insertBean);

	List<FileOffer> selectByBean(FileOffer queryBean);
	
	int update(FileOffer updateBean);
	
	FileOffer selectByFileName(String fileName);
	
	FileOffer selectByFileId(String fileId);

	int deleteByFileId(String fileId);
	
}
