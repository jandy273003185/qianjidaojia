package com.qifenqian.bms.merchant.equipment.service;
import com.qifenqian.bms.materiel.bean.Materiel;
import com.qifenqian.bms.materiel.service.MaterielService;
import com.qifenqian.bms.merchant.equipment.bean.DeviceLogin;
import com.qifenqian.bms.merchant.equipment.bean.MerchantSign;
import com.qifenqian.bms.merchant.equipment.dao.MerchantSignDAO;
import com.qifenqian.bms.merchant.equipment.mapper.MerchantSignMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



/**
 * 商户设备服务层
 * 
 * @project qifenqian-bms
 * @fileName MerchantSignService.java
 * @author wuzz
 * @date 2019年11月9日
 * @memo
 */
@Service
public class MerchantSignService {
	
	@Autowired
	private MerchantSignDAO merchantSignDAO;
	@Autowired
	private MerchantSignMapper merchantSignMapper;
	@Autowired
	private MaterielService materielService;
	
	/**
	 * 查询商户设备列表-分页
	 * 
	 * @return
	 */
	public List<MerchantSign> selectMerchantSignListByPage(MerchantSign merchantSign) {
		
		return merchantSignDAO.selectMerchantSignListByPage(merchantSign);
	}
	/**
	 * @param deviceLogin 
	 * @return  zhanggc 查询设备详情
	 */
	public  List<DeviceLogin> selectDeviceLoginById(DeviceLogin deviceLogin) {
		return merchantSignDAO.selectDeviceLoginById(deviceLogin);
	}

	/**
	 * 新增商户设备信息
	 * 
	 * @param merchantSign
	 * @return
	 */
	public int insertMerchantSignSingle(MerchantSign merchantSign) {
		
		if (null == merchantSign) {
			throw new IllegalArgumentException("商户设备对象为空");
		}
		if (StringUtils.isEmpty(merchantSign.getMerchantId())) {
			throw new IllegalArgumentException("商户编号为空");
		}
		if (StringUtils.isEmpty(merchantSign.getTerminalNo())) {
			throw new IllegalArgumentException("设备编号为空");
		}
		MerchantSign merSign = merchantSignMapper.selectMerchantSignByMerIdAndTerNo(merchantSign);
		if (null != merSign) {
			throw new IllegalArgumentException("商户编号为："+merchantSign.getMerchantId()+"，设备编号为："+merchantSign.getTerminalNo()+"的商户设备信息已经存在");
		}
		return merchantSignMapper.insertMerchantSign(merchantSign);
		
	}


	/**
	 * 修改商户产品信息
	 * 
	 * @param merchantProduct
	 * @return
	 */
	public int updateMerchantSign(MerchantSign merchantSign) {
		if (null == merchantSign) {
			throw new IllegalArgumentException("商户设备对象为空");
		}
		if (StringUtils.isEmpty(merchantSign.getMerchantId())) {
			throw new IllegalArgumentException("商户编号为空");
		}
		if (StringUtils.isEmpty(merchantSign.getTerminalNo())) {
			throw new IllegalArgumentException("设备编号为空");
		}
		if (merchantSign.getId() < 1) {
			throw new IllegalArgumentException("id为空");
		}
		//校验新增的设备编号是否存在
		Materiel materiel = materielService.selectMaterielByMachineId(merchantSign.getTerminalNo());
		if (null == materiel) {
			throw new IllegalArgumentException("该设备编号："+merchantSign.getTerminalNo()+"不存在");
		}
		MerchantSign merSign = merchantSignMapper.selectMerchantSignByMerIdAndTerNo(merchantSign);
		if (null != merSign && !(merSign.getId().equals(merchantSign.getId()))) {
			throw new IllegalArgumentException("商户编号为："+merchantSign.getMerchantId()+"，设备编号为："+merchantSign.getTerminalNo()+"的商户设备信息已经存在");
		}
		return merchantSignMapper.updateMerchantSign(merchantSign);
				
				
	}
	
	/**
	 * 根据物id查询商户设备信息
	 * 
	 * @param id
	 * @return
	 */
	public MerchantSign selectMerchantSignById(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("id为空");
		}
		return merchantSignMapper.selectMerchantSignById(id);
	}
	
	
	/**
	 * 根据ID删除商户设备信息
	 * 
	 * @param id
	 * @return
	 */
	public void deleteMerchantSign(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("id为空");
		}
	
		merchantSignMapper.deleteMerchantSign(id);
		
	}
	
	/**
	 * 根据商户ID和设备编号查询
	 * 
	 * @param merchantSign
	 * @return
	 */
	public MerchantSign selectMerchantSignByMerIdAndTerNo(MerchantSign merchantSign) {
		
		return merchantSignMapper.selectMerchantSignByMerIdAndTerNo(merchantSign);
	}

	
}
