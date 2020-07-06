package com.qifenqian.bms.v2.biz.common.controller;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.qifenqian.bms.v2.biz.common.service.BizCommonService;
import com.qifenqian.bms.v2.biz.system.function.bean.vo.FunctionTreeVO;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiBin
 * @Description: 测试类
 * @date 2020/5/8 10:42
 */
@RestController
public class DemoController extends BaseController {

    @Autowired
    private BizCommonService bizCommonService;

    // 定义一个是String类型的远程缓存
    @CreateCache(name = "i5xforyou.username", expire = 120, cacheType = CacheType.REMOTE)
    private Cache<String, String> userNameCache;
    // 定义一个是User对象的二级缓存(本地+远程)
    @CreateCache(name = "i5xforyou.user", localExpire = 60, localLimit = 100, expire = 120, cacheType = CacheType.BOTH)
    private Cache<String, CurrentAccount> currentAccountCache;

    @GetMapping(value = "/demo/getUser")
    public ResultData getUserInfo() {
        userNameCache.put("123", "userName");
        System.out.println("userNameCache : " + userNameCache.get("123"));
        CurrentAccount user = new CurrentAccount();
        user.setName("user.userName");
        currentAccountCache.put("1234", user);
        System.out.println("userCache: " + currentAccountCache.get("1234").getName());
        return ResultData.success();
    }

    @GetMapping(value = "/demo/findFunctionTree")
    public List<FunctionTreeVO> findFunctionTree() {
        return bizCommonService.findFunctionTree("11111");
    }
}