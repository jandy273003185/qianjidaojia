package com.qifenqian.bms.v2.biz.basedata.trade.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.tradeControl.bean.TradeControl;
import com.qifenqian.bms.basemanager.tradeControl.mapper.TradeControlMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/27 15:21
 */
@Service
public class BaseTradeService extends BaseService {
    @Autowired
    private TradeControlMapper tradeControlMapper;

    public PageInfo<TradeControl> findTradeControlList(TradeControl tradeControl) {
        List<TradeControl> tradeControls = tradeControlMapper.selectAll(tradeControl);
        return new PageInfo<>(tradeControls);
    }

    public ResultData add(TradeControl tradeControl) {
        TradeControl exist = tradeControlMapper.selectTradeControl(tradeControl);
        if (exist != null) {
            throw new BizException("该交易控制类型已经存在");
        }
        try {
            tradeControlMapper.addTradeControl(tradeControl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("添加交易控制类型异常!" + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData update(TradeControl tradeControl) {
        try {
            tradeControlMapper.editTradeControl(tradeControl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("修改交易控制类型异常!" + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData delete(TradeControl tradeControl) {
        try {
            tradeControlMapper.deleteTradeControl(tradeControl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("删除交易控制类型异常!" + e.getMessage());
        }
        return ResultData.success();
    }
}
