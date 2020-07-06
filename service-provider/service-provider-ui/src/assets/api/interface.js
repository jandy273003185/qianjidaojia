import http from "./http";
export const common = { //
    getImgInfo: params => http.post1('/common/youTu', params), //图片识别
    uploadImg: params => http.post('/common/upload', params), //图片上传
    getAddress: params => http.post1('/common/province', params), //商户省市区
    bankAddress: params => http.post1('/common/bankProvince', params), //银行省市
    resetPwd: params => http.post1('/salesman/regPassword', params), //重置密码
    updatePwd: params => http.post1('/user/userLoginPw', params), //修改密码
    updateSales: params => http.post1('/salesman/update', params), //更新业务员
    insertSales: params => http.post1('/salesman/insert', params), //新增业务员
    addSales: param => http.post1('/salesman/checkPhone', param), //新增业务员时手机号码校验

    financeDetail: params => http.post1('/finance/getFinanceInfo', params), //财务查看详情
    resetPwd2: params => http.post1('/finance/resetFinancePw', params), //重置密码
    // updatePwd2: params => http.post1('/salesman/updatePassword', params), //财务员修改密码
    updateFinance: params => http.post1('/finance/updateFinance', params), //更新财务员
    insertFinancial: params => http.post1('/finance/addFinance', params), //新增财务员
    addFinancial: param => http.post1('/finance/validate', param), //新增业务员时手机号码校验
};
export const incoming = { //进件
    bankHead: params => http.post1('/common/bankHeadOffice', params), //获取总行信息
    bankBranch: params => http.post1('/common/bankBranch', params), //获取支行信息
    checkPhone: params => http.post1('/merchant/checkMobile', params), //校验商户账号
    insertIncoming: params => http.post1('/merchant/insertMerchant', params), //提交进件
    getIncoming: params => http.post1('/merchant/queryMerchant', params), //获取进件信息
};
export const adminIndex = { //管理员主页
    searchSales: param => http.post1('/salesman/query', param), //搜索业务员
    searchFinance: param => http.post1('/finance/getFinanceList', param), //搜索财务员
    SalesRanking: param => http.post1('/salesman/performance', param), //业务员排名
    salesDetail: param => http.post1('/salesman/get', param), //点击单个业务员查看业绩详情
};
export const login = { //登录接口
    login: param => http.post1('/user/loginBinding', param), //登录页接口
    resetNewPsd: param => http.post1('/user/roleCodeModifyPwd', param), //忘记密码时修改密码
    code: param => http.post1('/common/verifyCode', param), //登录时获取验证码
    forgetCode: param => http.post1('/common/modifyPassword', param), //忘记密码时获取验证码
    fVerifyCode: param => http.post1('/user/forgetPassword', param), //忘记密码时验证验证码
    agentCodeLogin: param => http.post1('/user/smsSpLogin', param), //管理员验证码登录接口
    salesCodeLogin: param => http.post1('/user/smsSmLogin', param), //业务员验证码登录接口
    firstLogin: param => http.post1('/user/login', param), //进入主页时判断是否绑定
    getOpenId: param => http.post1('/wx/callback', param), //获取openid
    getBindingList: param => http.post1('/user/getBindingList', param), //获取服务商列表
    loginout: param => http.post1('/user/loginOut', param), //退出登录
    userLoginRelate: param => http.post1('/user/userLoginRelate', param) //解除绑定
};
export const homeShopInfo = { //首页里的商户信息
    shopInfo: param => http.post1('/merchant/getStatCommercial', param), //业务员首页商户信息
    allShopInfo: param => http.post1('/merchant/getSPStatCommercial', param) //管理员首页商户信息
};
export const shopAuditInfo = { //商户页面审核信息
    allShopAuditInfo: param => http.post1('/salesman/selectSalesmanCommercialInfo', param), //管理员商户页面审核信息接口
    shopAuditInfo: param => http.post1('/merchant/selectCommercialInfo', param), //业务员商户页面审核信息接口
    shopsucceedInfo: param => http.post1('/merchant/queryMerchantById', param), //审核通过信息接口
    shopDefeatedInfo: param => http.post1('/merchant/getCommerAuditCause', param), //审核失败信息接口
    pro_contract: param => http.post1('/merchant/insertProduct', param), //商户审核通过后的产品签约提交
    contractInfo: param => http.post1('/merchant/queryProduct', param) //商户审核通过后的产品已签约信息
};
export const goodsInfo = { //交易栏的商户交易排名信息
    goodsInfo: param => http.post1('/merchant/getDealRanking', param), //业务员交易栏的商户交易排名信息
    allGoodsInfo: param => http.post1('/salesman/getServiceProviderDealRanking', param), //管理员交易栏的商户交易排名信息
    goodsDetails: param => http.post1('/merchant/getSPMerchantOrderList', param) //管理员交易栏的商户交易排名信息
};
export const mineInfo = { //我的信息
    /* salesman:param =>http.post1('/salesman/query',param),  //业务员请求个人信息*/
    agent: param => http.post1('/merchant/getAgentInfo', param), //管理员请求个人信p
    getShareProfit: param => http.post1('/merchant/getShareProfit', param), //分润
};