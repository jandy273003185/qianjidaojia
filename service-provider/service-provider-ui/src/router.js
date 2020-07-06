import Vue from "vue";
import Router from "vue-router";
import Administrator from "./views/Administrator.vue";
import SearchShop from "./components/searchShop.vue";
import SearchSalesman from "./components/searchSalesman.vue";
import WhyFailed from "./components/whyFailed.vue";
import SalesmanResult from "./components/salesmanResult.vue";

Vue.use(Router);
const view = name => () =>
    import (`./views/${name}.vue`);
const view_2 = (name, file) => () =>
    import (`./views/${file}/${name}.vue`);
export default new Router({
    mode: "hash",
    base: process.env.BASE_URL,
    routes: [{
            path: "/",
            redirect: "/selectServiceMerchant" //默认
        },
        {
            path: "/selectServiceMerchant",
            name: "selectServiceMerchant",
            component: view("selectServiceMerchant") //选择服务商
        },
        {
            path: "/Administrator",
            name: "Administrator",
            component: Administrator //管理员主页
        },
        {
            path: "/searchShop",
            name: "searchShop",
            component: SearchShop //搜索商户
        },
        {
            path: "/searchSalesman",
            name: "searchSalesman",
            component: SearchSalesman //搜索业务员
        },
        {
            path: "/salesmanResult",
            name: "salesmanResult",
            component: SalesmanResult //业务员业绩
        },
        {
            path: "/whyFailed",
            name: "whyFailed",
            component: WhyFailed //审核失败原因
        },

        {
            path: "/salesman",
            name: "salesman",
            component: view("salesman") //业务员主页
        },
        {
            path: "/shop",
            name: "shop",
            component: view("shop"), //商户
        },
        {
            path: "/goods",
            name: "goods",
            component: view("goods"), //交易
        },
        {
            path: "/goodsDetails",
            name: "goodsDetails",
            component: view("goodsDetails") //交易详情
        },
        {
            path: "/shareProfit",
            name: "shareProfit",
            component: view("shareProfit") //分润
        },

        {
            path: "/login",
            name: "login",
            component: view("Login") //登录
        },
        /* 进件 */
        {
            path: "/baseInfo",
            name: "baseInfo",
            component: view_2("baseInfo", "incoming")
        },
        {
            path: "/legalInfo",
            name: "legalInfo",
            component: view_2("legalInfo", "incoming")
        },
        {
            path: "/merchant",
            name: "merchant",
            component: view_2("merchant", "incoming")
        },
        {
            path: "/sign",
            name: "sign",
            component: view_2("sign", "incoming")
        },
        /* 我的 */
        {
            path: "/mine",
            name: "mine",
            component: view_2("mine", "mine")
        },
        {
            path: "/salesManage",
            name: "salesManage",
            component: view_2("salesManage", "mine")
        },
        {
            path: "/financialManage",
            name: "financialManage",
            component: view_2("financialManage", "mine")
        },
        {
            path: "/amendPsd",
            name: "amendPsd",
            component: view_2("amendPsd", "mine")
        },
        {
            path: "/about",
            name: "about",
            component: view_2("about", "mine")
        },
        {
            path: "/companyInfo",
            name: "companyInfo",
            component: view_2("companyInfo", "mine")
        },
        {
            path: "/feedback",
            name: "feedback",
            component: view_2("feedback", "mine")
        },
        /* 审核 */
        {
            path: "/audit/pass",
            name: "pass",
            component: view_2("pass", "audit")
        },

    ]
});