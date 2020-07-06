package com.qifenqian.bms.v2.biz.system.function.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.platform.web.admin.function.bean.Function;
import com.qifenqian.bms.platform.web.admin.function.mapper.FunctionMapper;
import com.qifenqian.bms.v2.biz.system.function.bean.vo.FunctionTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description: 菜单功能
 * @date 2020/4/2 09:59
 */
@Service
public class SysFunctionService {

    @Autowired
    private FunctionMapper functionMapper;

    public PageInfo<Function> findFunctionByPage(Function function) {
        List<Function> functions = functionMapper.selectFunctionsByIdAndName(function);
        return new PageInfo(functions);
    }

    public List<FunctionTreeVO> findFunctionTree(){
        List<FunctionTreeVO> functionTree = functionMapper.findFunctionTree( null);
        return functionTree;
    }
}
