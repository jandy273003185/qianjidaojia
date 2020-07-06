import Vue from "vue";
import Vuex from "vuex";
Vue.use(Vuex);
export default new Vuex.Store({
    state: {
        code: '',
        token: '', //localStorage.getItem("token") || null,,
        openId: '', //进入首页即请求openID
        role: '', //进入页面的角色，管理员agent或者业务员salesman，登录时传给后台 财务员finance
        salesID: '', //管理员查找业务员时得到的ID，可用于查看业务员个人信息
        LookName: '', //管理员正在查看的业务员的名字
        userName: '', //管理员或者业务员的名字
        custId: '',
        McustId:'',
        userId: '',
        roleId: '', //2是管理员，3是业务员
        incoming: {

        },
        incomingReturn: { //请求的保存数据
        },
        savephotos: {
            businessLicense: [], //营业执照
            shopFrontDesk: [], //门头照
            shopInterior: [], //店内照
            specialBusiness: [], //特殊行业照
            electronicSignaturePhoto: [], //电子签名照
            otherPhoto1: [], //其他资料照 1
            otherPhoto2: [] //其他资料照 2
        }, //图片
        custScanCopys: [],
        merchantCode:'',
        checkedState: '' //审核状态
    },
    mutations: {
        setMerchantCode(state, obj) {
            state.merchantCode = obj;
        },
        setToken(state, obj) {
            state.token = obj;
        },
        setOpenID(state, obj) {
            state.openId = obj;
        },

        setRole(state, obj) {
            state.role = obj; //确认是哪种角色进入
        },
        selectName(state, sales) { //管理员想查看的业务员的名字
            state.LookName = sales.name;
            state.salesID = sales.id;
        },
        setCustId(state, obj) {//首頁管理員id
            state.custId = obj;
        },
        setMCustId(state, obj) {//商戶id
            state.McustId = obj;
        },
        setUserId(state, obj) {
            state.userId = obj;
        },
        setincoming(state, obj) { //进件参数
            state.incoming = obj;
        },
        setincomingReturn(state, obj) {
            state.incomingReturn = obj;
        },
        custScanCopys(state, obj) { //图片数组
            state.custScanCopys = obj
        },
        setRoleId(state, obj) {
            state.roleId = obj;
        },
        setCheckedState(state, obj) {
            state.checkedState = obj;
        },
        setPhotos(state, obj) {
            state.savephotos = obj;
        },
        setCode(state, obj) {
            state.code = obj;
        },
        setUserName(state, obj) {
            state.userName = obj;
        }
    },
    actions: {

    }
});