package com.qifenqian.bms.expresspay.jgkjfileupload.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.expresspay.jgkjfileupload.bean.FileOffer;
import com.qifenqian.bms.expresspay.jgkjfileupload.mapper.FileOfferMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class FileOfferDao {
	@Autowired
	private FileOfferMapper fileOfferMapper;

	@Page
	public List<FileOffer> selectByBean(FileOffer queryBean) {

		return fileOfferMapper.selectByBean(queryBean);
	}
}
