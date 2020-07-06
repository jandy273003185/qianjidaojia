package com.qifenqian.bms.basemanager.photo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.photo.bean.CertificateAuth;
import com.qifenqian.bms.basemanager.photo.mapper.PhotoMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class PhotoDAO {

	@Autowired
	private PhotoMapper photoMapper;
	
	/**
	 * 分页记录
	 * @param photo
	 * @return
	 */
	@Page
	public List<CertificateAuth> list(CertificateAuth queryBean){
		
		return photoMapper.list(queryBean);
	}
}
