package com.qifenqian.bms.materiel.dao;

import com.qifenqian.bms.materiel.bean.Materiel;
import com.qifenqian.bms.materiel.mapper.MaterielMapper;
import com.qifenqian.bms.platform.web.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * dao层，一般分页需要
 * 
 * @project qifenqian-bms
 * @fileName MaterielDAO.java
 * @author wuzz
 * @date 2019年11月4日
 * @memo
 */
@Repository
public class MaterielDAO{

	@Autowired
	private MaterielMapper matrielMapper;
	
	/**
	 * 分页查询物料列表
	 * @return
	 */
	@Page
	public List<Materiel> selectMaterielListByPage(Materiel materiel) {
		return matrielMapper.selectMaterielList(materiel);
	}
}


