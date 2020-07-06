package com.qifenqian.bms.v2.biz.aggregatepayment.channelroute.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.channelroute.bean.ChannelRouteBean;
import com.qifenqian.bms.basemanager.channelroute.mapper.ChannelRouteMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BizChannelRouteService extends BaseService {

    @Autowired
    public ChannelRouteMapper channelRouteMapper;

    public PageInfo<ChannelRouteBean> list(ChannelRouteBean queryBean) {
        List<ChannelRouteBean> channelRouteBeans = channelRouteMapper.selectRoute(queryBean);
        return new PageInfo<>(channelRouteBeans);
    }

    public ResultData delete(ChannelRouteBean channelRouteBean) {
        try {
            channelRouteMapper.deleteChannelRoute(channelRouteBean.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    @Transactional
    public ResultData add(ChannelRouteBean channelRouteBean) {
        try {
            channelRouteMapper.addChannelRoute(channelRouteBean);
            buildRouteRf(channelRouteBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    @Transactional
    public ResultData update(ChannelRouteBean channelRouteBean) {
        try {
            channelRouteMapper.updateChannelRoute(channelRouteBean);
            //根据路由ID先删去原渠道ID，再添加新的渠道ID
            channelRouteMapper.deleteRouteId(channelRouteBean);
            buildRouteRf(channelRouteBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    private void buildRouteRf(ChannelRouteBean channelRouteBean) {
        //取checkbox中数据
        String codeString = channelRouteBean.getChannels();
        if (StringUtils.isBlank(codeString)) {
            String[] codes = codeString.split(",");
            List<ChannelRouteBean> routeBeans = new ArrayList<ChannelRouteBean>();
            for (int i = 0; i < codes.length; i++) {
                //取对应channelID
                routeBeans = channelRouteMapper.selectId(codes[i]);
                channelRouteBean.setChannelId(routeBeans.get(0).getId());
                //取对应channel_route_id
                if (StringUtils.isBlank(channelRouteBean.getId())) {
                    List<ChannelRouteBean> channelRouteBeans = channelRouteMapper.selectRoute(channelRouteBean);
                    channelRouteBean.setId(channelRouteBeans.get(0).getId());
                }
                channelRouteMapper.addRouteRf(channelRouteBean);
            }
        }
    }

}
