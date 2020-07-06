package com.qifenqian.bms.materiel.service;

import com.qifenqian.bms.materiel.bean.Materiel;
import com.qifenqian.bms.materiel.dao.MaterielDAO;
import com.qifenqian.bms.materiel.mapper.MaterielMapper;
import com.qifenqian.bms.merchant.equipment.bean.MerchantSign;
import com.qifenqian.bms.merchant.equipment.service.MerchantSignService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户服务层
 * 
 * @project qifenqian-bms
 * @fileName MaterielService.java
 * @author wuzz
 * @date 2019年11月4日
 * @memo
 */
@Service
public class MaterielService {
	
	@Autowired
	private MaterielDAO materielDAO;
	@Autowired
	private MaterielMapper materielMapper;
	@Autowired
	private MerchantSignService merchantSignService;
	
	private static final String XLS = "xls";
	private static final String XLSK = "xlsx";
		
    public int importExcel(MultipartFile file) throws Exception {
        List<Materiel> list = new ArrayList<Materiel>();
     
        Workbook workbook = null;
        String fileName = file.getOriginalFilename();
        if(fileName.endsWith(XLS)) {
            //2003
            try {
                workbook = new HSSFWorkbook(file.getInputStream());
            } catch (Exception e) {
                e.printStackTrace( );
            }
            
        }else if(fileName.endsWith(XLSK)) {
            try {
                //2007
                workbook = new XSSFWorkbook(file.getInputStream());
            } catch (Exception e) {
                e.printStackTrace( );
            }
        }else {
            throw new Exception("文件不是Excel文件");
        }
        Sheet sheet = workbook.getSheet("Sheet1");
        //指定行数。一共多少+
        int rows = sheet.getLastRowNum();
        if(rows==0) {
            throw new Exception("请填写行数");
        }
        //查询出已存在的机器编号
        List<Materiel> materielList= materielMapper.selectMaterielList(null);
        Map<String,String> map = new HashMap<String, String>();
        for (int i = 1; i < rows+1; i++) {
            //读取左上端单元格
            Row row = sheet.getRow(i);
            //行不为空
            if(row != null) {
                //读取cell
            	Materiel materiel = new Materiel();
                //机器编号
            	String machineId;
            	if(row.getCell(0) != null) {
            		machineId = row.getCell(0).getStringCellValue();
            		materiel.setMachineId(machineId);
            	}else {
            		throw new IllegalArgumentException("第"+i+"行机器编号为空");
            	}
                                             
                //判断文件内是否有相同的机器编号
                if(map.containsKey(machineId)) {
                	throw new IllegalArgumentException("第"+i+"行文件中存在重复机器编号："+machineId);
                }else {
                	map.put(machineId, machineId);
                }
                //判断机器编号是否已经存在              
                for(Materiel m:materielList) {
                	if(machineId.equals(m.getMachineId())) {
                		throw new IllegalArgumentException("第"+i+"行机器编号："+machineId+"已存在");
                	}
                }
                
                //设备类型                
                if(row.getCell(1) !=null) {
                	String machineType = row.getCell(1).getStringCellValue();                    
                    materiel.setMachineType(machineType);
                }
                
                //领取人                               
                if(row.getCell(2) !=null) {
                	String receiver = row.getCell(2).getStringCellValue();
                    materiel.setReceiver(receiver);
                }
                
                //所用商户                
                if(row.getCell(3) !=null) {
                	String usedMerchant = row.getCell(3).getStringCellValue();                    
                    materiel.setUsedMerchant(usedMerchant);
                }
                
                //所用门店
                if(row.getCell(4) !=null) {
                	String usedStores = row.getCell(4).getStringCellValue();                
                    materiel.setUsedStores(usedStores);
                }
                               
                //供应商
                if(row.getCell(5) !=null) {
                	String supplier = row.getCell(5).getStringCellValue();
                    if (StringUtils.isEmpty(supplier)) {
            			throw new IllegalArgumentException("第"+i+"行供应商为空");
            		}
                    materiel.setSupplier(supplier);
                }else {
                	throw new IllegalArgumentException("第"+i+"行供应商为空");
                }
                                
                //机器状态
                if(row.getCell(6) !=null) {
                	String machineState = row.getCell(6).getStringCellValue();
                    if (StringUtils.isEmpty(machineState)) {
            			throw new IllegalArgumentException("第"+i+"行机器状态为空");
            		}
                    if("已领用".equals(machineState)) {
                    	machineState = "0";
                    }
                    if("未领用".equals(machineState)) {
                    	machineState = "1";
                    }
                    if("已激活".equals(machineState)) {
                    	machineState = "2";
                    }
                    if("未激活".equals(machineState)) {
                    	machineState = "3";
                    }                
                    materiel.setMachineState(machineState);
                }else {
                	throw new IllegalArgumentException("第"+i+"行机器状态为空");
                }
                                
                //备注
                if(row.getCell(7) !=null) {
                	String memo = row.getCell(7).getStringCellValue();                
                    materiel.setMemo(memo);
                }
                
                //服务商名称
                if(row.getCell(8) !=null) {
                	String serviceParenterName = row.getCell(8).getStringCellValue();                
                    materiel.setServiceParenterName(serviceParenterName);
                }
                //创建人
                materiel.setCreator(WebUtils.getUserInfo().getUserName());
                
                //把实数据放入集合里
                list.add(materiel);
            }
        }
              
        return materielMapper.addBatchMateriel(list);
    }
	
	


	/**
	 * 新增单个物料信息
	 * 
	 * @param materiel
	 * @return
	 */
	public int insertMaterielSingle(Materiel materiel) {
		if (null == materiel) {
			throw new IllegalArgumentException("物料对象为空");
		}
		if (StringUtils.isEmpty(materiel.getMachineId())) {
			throw new IllegalArgumentException("设备编号为空");
		}
		
		if (StringUtils.isEmpty(materiel.getMachineState())) {
			throw new IllegalArgumentException("设备状态为空");
		}
		if (StringUtils.isEmpty(materiel.getSupplier())) {
			throw new IllegalArgumentException("供应商为空");
		}
		return materielMapper.insertMaterielSingle(materiel);
	}

	
	/**
	 * 查询物料管理列表-分页
	 * 
	 * @return
	 */
	public List<Materiel> selectMaterielListByPage(Materiel materiel) {
		return materielDAO.selectMaterielListByPage(materiel);
	}

	

	/**
	 * 根据物料编号删除物料信息
	 * 
	 * @param id
	 * @return
	 */
	public int deleteMaterielById(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("物料编号为空");
		}
		//删除物料需要查看是否已经绑定了商户
		Materiel materiel = materielMapper.selectMaterielById(id);
		if (null == materiel) {
			throw new IllegalArgumentException("删除的物料信息不存在");
		}
		if (StringUtils.isEmpty(materiel.getMachineId())) {
			throw new IllegalArgumentException("设备编号不存在");
		}else {
			MerchantSign merSign = new MerchantSign();
			merSign.setTerminalNo(materiel.getMachineId());
			MerchantSign merchantSign = merchantSignService.selectMerchantSignByMerIdAndTerNo(merSign);
			if (null != merchantSign) {
				throw new IllegalArgumentException("需要删除的物料信息与商户存在绑定关系，设备编号为："+materiel.getMachineId());
			}
		}
		return materielMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据物料编号查询物料信息
	 * 
	 * @param id
	 * @return
	 */
	public Materiel selectMaterielById(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("物料编号为空");
		}
		return materielMapper.selectMaterielById(id);
	}

	
	/**
	 * 根据机器编号查询物料信息
	 * 
	 * @param id
	 * @return
	 */
	public Materiel selectMaterielByMachineId(String machineId) {
		if (StringUtils.isEmpty(machineId)) {
			throw new IllegalArgumentException("机器编号为空");
		}
		return materielMapper.selectMaterielByMachineId(machineId);
	}
	
	/**
	 * 修改物料信息
	 * 
	 * @param materiel
	 * @return
	 */
	public int updateMateriel(Materiel materiel) {
		// 检查
		if (null == materiel) {
			throw new IllegalArgumentException("物料对象为空");
		}
		if (materiel.getId() < 1) {
			throw new IllegalArgumentException("物料编号为空");
		}
		
		return materielMapper.updateMaterielById(materiel);
	}

	
}
