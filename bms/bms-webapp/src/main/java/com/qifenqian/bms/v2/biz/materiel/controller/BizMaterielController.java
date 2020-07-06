package com.qifenqian.bms.v2.biz.materiel.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.materiel.bean.Materiel;
import com.qifenqian.bms.materiel.service.MaterielService;
import com.qifenqian.bms.v2.biz.materiel.service.BizMaterielService;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author LiBin
 * @Description: 物料管理
 * @date 2020/4/28 11:21
 */
@RestController
@Api(tags = "物料管理")
public class BizMaterielController extends BaseController {
    @Autowired
    private MaterielService materielService;
    @Autowired
    private BizMaterielService bizMaterielService;

    @PostMapping(value = "/materiel/list")
    @ApiOperation("物料列表")
    public PageInfo<Materiel> list(PageQuery pageQuery, @RequestBody Materiel materiel) {
        return this.bizMaterielService.findMaterielList(materiel);
    }

    @PostMapping(value = "/materiel/add")
    @ApiOperation("物料添加")
    public ResultData add(CurrentAccount currentAccount, @RequestBody Materiel materiel) {
        if (StringUtils.isBlank(materiel.getMachineId())) {
            throw new BizException("设备编号为空");
        }
        if (StringUtils.isBlank(materiel.getMachineState())) {
            throw new BizException("设备状态为空");
        }
        if (StringUtils.isBlank(materiel.getSupplier())) {
            throw new BizException("供应商为空");
        }
        materiel.setCreator(currentAccount.getLoginId().toString());
        return this.bizMaterielService.add(materiel);
    }

    @PostMapping(value = "/materiel/update")
    @ApiOperation("物料修改")
    public ResultData update(CurrentAccount currentAccount, @RequestBody Materiel materiel) {
        if (StringUtils.isBlank(materiel.getMachineId())) {
            throw new BizException("设备编号为空");
        }
        if (StringUtils.isBlank(materiel.getMachineState())) {
            throw new BizException("设备状态为空");
        }
        if (StringUtils.isBlank(materiel.getSupplier())) {
            throw new BizException("供应商为空");
        }
        materiel.setLastupdateUser(currentAccount.getLoginId().toString());
        return this.bizMaterielService.update(materiel);
    }

    @PostMapping(value = "/materiel/delete")
    @ApiOperation("物料删除")
    public ResultData delete(@RequestBody Materiel materiel) {
        if (null == materiel.getId()) {
            throw new BizException("设备编号为空");
        }
        if (StringUtils.isBlank(materiel.getMachineId())) {
            throw new BizException("设备编号为空");
        }
        return this.bizMaterielService.delete(materiel);
    }


    @PostMapping(value = "/materiel/upload")
    @ApiOperation("批量导入物料")
    public ResultData upload(@RequestParam("file") MultipartFile file) {
        try {
            materielService.importExcel(file);
        } catch (Exception e) {
            throw new BizException("批量导入物料异常!" + e.getMessage());
        }
        return ResultData.success();
    }

}
