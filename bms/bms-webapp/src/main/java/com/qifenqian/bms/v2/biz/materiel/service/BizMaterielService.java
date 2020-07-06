package com.qifenqian.bms.v2.biz.materiel.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.materiel.bean.Materiel;
import com.qifenqian.bms.materiel.mapper.MaterielMapper;
import com.qifenqian.bms.merchant.equipment.bean.MerchantSign;
import com.qifenqian.bms.merchant.equipment.mapper.MerchantSignMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/28 11:24
 */
@Service
public class BizMaterielService extends BaseService {

    @Autowired
    private MerchantSignMapper merchantSignMapper;
    @Autowired
    private MaterielMapper materielMapper;

    public PageInfo<Materiel> findMaterielList(Materiel materiel) {
        List<Materiel> materielList = materielMapper.selectMaterielList(materiel);
        return new PageInfo<>(materielList);
    }

    public ResultData add(Materiel materiel) {
        Materiel exist = materielMapper.selectMaterielByMachineId(materiel.getMachineId());
        if (exist != null) {
            throw new BizException("机器编号已存在!");
        }
        int result = materielMapper.insertMaterielSingle(materiel);
        if (result < 1) {
            throw new BizException("添加保存物料信息异常!");
        }
        return ResultData.success();
    }

    public ResultData update(Materiel materiel) {
        //查询需要修改的物料信息是否存在
        Materiel exist = materielMapper.selectMaterielById(materiel.getId());
        if (exist == null) {
            throw new BizException("物料信息不存在!");
        }
        Materiel currentMateriel = materielMapper.selectMaterielByMachineId(materiel.getMachineId());
        if (currentMateriel != null && !(currentMateriel.getId().equals(materiel.getId()))) {
            throw new BizException("修改的机器编号已经存在!");
        }
        int result = materielMapper.updateMaterielById(materiel);
        if (result < 1) {
            throw new BizException("修改物料信息异常!");
        }
        return ResultData.success();
    }

    public ResultData delete(Materiel materiel) {
        Materiel exist = materielMapper.selectMaterielById(materiel.getId());
        if (null == exist) {
            throw new BizException("删除的物料信息不存在");
        }
        MerchantSign merSign = new MerchantSign();
        merSign.setTerminalNo(materiel.getMachineId());
        MerchantSign merchantSign = merchantSignMapper.selectMerchantSignByMerIdAndTerNo(merSign);
        if (null != merchantSign) {
            throw new BizException("需要删除的物料信息与商户存在绑定关系，设备编号为：" + materiel.getMachineId());
        }
        int result = materielMapper.deleteByPrimaryKey(materiel.getId());
        if (result < 1) {
            throw new BizException("删除物料信息异常!");
        }
        return ResultData.success();
    }
}
