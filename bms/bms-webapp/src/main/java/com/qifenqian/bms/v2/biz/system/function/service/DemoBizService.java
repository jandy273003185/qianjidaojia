package com.qifenqian.bms.v2.biz.system.function.service;

import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import com.qifenqian.bms.v2.biz.system.function.bean.query.DemoBizQuery;
import com.qifenqian.bms.v2.biz.system.function.bean.vo.DemoBizVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName：DemoBizService
 * Description：TODO
 * Author: LiBin
 * Date：2019/12/27 2:23 下午
 */
@Service
public class DemoBizService extends BaseService {

    public List<DemoBizVO> findListBy(DemoBizQuery demoBizQuery) {
        List<DemoBizVO> demoBizVOS = new ArrayList<>();
        DemoBizVO demoBizVO = new DemoBizVO();
        demoBizVO.setId("123");
        demoBizVOS.add(demoBizVO);
        return demoBizVOS;
    }
}
