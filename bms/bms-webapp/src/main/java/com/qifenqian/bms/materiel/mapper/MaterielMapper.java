package com.qifenqian.bms.materiel.mapper;

import com.qifenqian.bms.materiel.bean.Materiel;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * 物料管理持久层
 * 
 * @project qifenqian-bms
 * @fileName MatrielMapper.java
 * @author wuzz
 * @date 2019年11月4日
 * @memo
 */
@MapperScan
public interface MaterielMapper {
	
	/**
	 * 根据ID查询物料信息
	 * 
	 * @return
	 */
	public Materiel selectMaterielById(int id);
	
	/**
	 * 根据机器ID查询物料信息
	 * 
	 * @return
	 */
	public Materiel selectMaterielByMachineId(String machineId);

	/**
	 * 新增单个物料信息
	 * 
	 * @param materiel
	 * @return
	 */
	public int insertMaterielSingle(Materiel materiel);
	

	/**
	 * 查询物料信息列表
	 * 
	 * @return
	 */
	public List<Materiel> selectMaterielList(Materiel materiel);

	/**
	 * 修改物料信息
	 * 
	 * @param user
	 * @return
	 */
	public int updateMaterielById(Materiel materiel);


	/**
	 * 删除物料信息
	 * @param requestUser
	 * @return
	 */
	public int deleteByPrimaryKey(int id);
	
	
	/**
	 * 批量导入物料信息
	 * @param list
	 * @return
	 */
	public int addBatchMateriel(List<Materiel> list);
}
