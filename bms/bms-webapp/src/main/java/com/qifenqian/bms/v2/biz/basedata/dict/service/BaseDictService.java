package com.qifenqian.bms.v2.biz.basedata.dict.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.dictData.bean.DictData;
import com.qifenqian.bms.basemanager.dictData.mapper.DictDataMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/27 09:44
 */
@Service
public class BaseDictService extends BaseService {

    @Autowired
    private DictDataMapper dictDataMapper;

    public PageInfo<DictData> findDistDataList(DictData dictData) {
        List<DictData> dictDataList = dictDataMapper.selectListDict(dictData);
        return new PageInfo<>(dictDataList);
    }

    public ResultData add(DictData dictData) {
        try {
            dictDataMapper.insertDict(dictData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("添加保存字典数据异常! " + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData update(DictData dictData) {
        try {
            dictDataMapper.updateDict(dictData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("修改字典数据异常! " + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData delete(DictData dictData) {
        try {
            dictDataMapper.delDict(dictData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("删除字典数据异常! " + e.getMessage());
        }
        return ResultData.success();
    }
}
