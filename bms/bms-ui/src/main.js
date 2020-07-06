import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import mixins from './tools/mixins'
import Custom from './tools/custom'
import { lstorage, flatten } from '@/tools/utils'

import {
  Button, Option, Select, Form, FormItem, Menu, MenuItem, Submenu, Pagination, Table, DatePicker, TableColumn,
  Input, Checkbox, CheckboxGroup, Header, Main, Aside, Container, Breadcrumb, BreadcrumbItem, Message, Radio,
  RadioGroup, Dialog, Upload, Tabs, TabPane, Col, Autocomplete, Loading, CascaderPanel, Image
} from 'element-ui'

import SiteIcon from './components/SiteIcon'
import PageModel from './components/PageModel'
import CsBtn from './components/CsBtn'

import '@/assets/css/base.scss';
import '@/assets/css/common.scss';
import '@/assets/css/mixins.scss';
import 'element-ui/lib/theme-chalk/index.css';


/* eslint-disable */
Message.install = function (Vue, options) {
  Vue.prototype.$message = Message
}
/* eslint-enable*/

function dynamicRouterHandel(arr) {
  return arr.map(
    ({ name = '', path = '', children = [] }) => {
      if (children.length) {
        return { name, path, children: dynamicRouterHandel(children) };
      }
      return { name, path };
    }
  );
}

Vue.filter('dealFields', (v) => {
  const dealMapData = {
    FREEZE: '冻结',
    LEAVE: '离职',
    LOGIN: '已登陆',
    VALID: '有效',
    DISABLE: '无效',
    Y: '是',
    N: '否',
    MEN: '男',
    WOMEN: '女',
    '1': '可用',
    '0': '不可用',
    "00": "有效",
    "01": "待审核",
    "02": "注销",
    "03": "冻结",
    "04": "审核不通过",
    SK: '刷卡',
    SM: '扫码',
    NORMAL: '正常',
    PENDING: '准备',
    ALIPAY: '支付宝',
    WEIXIN: '微信',
  };
  return dealMapData[v] || '';
});

router.beforeEach((to, form, next) => {
  const menuTree = lstorage.get('menuTree') || [];
  const dynamicRouter = dynamicRouterHandel(menuTree) || [];
  const dynamicRouterMapMenu = flatten(dynamicRouter);
  // 判断当前路由是否存在于系统路由
  const isEffectiveRoute = dynamicRouterMapMenu.some(({ path = '' }) => to.path === path);

  const data = JSON.parse(localStorage.getItem('userinfo')) || {};
  const { token, userinfo } = data;

  if (to.path === '/login') {
    next();
    return;
  }

  const reg1 = new RegExp('merchants/list/operation');//商户管理>商户列表>(新增+操作处理页面)
  const reg2 = new RegExp('merchants/service/operation');//商户管理>服务商列表>(新增+操作处理页面)

  if (isEffectiveRoute || to.path === '/welcome' || to.path === '/error' || reg1.test(to.path) || reg2.test(to.path)) {
    if (store.state.token) {
      next();
    } else if (token) {
      store.commit('setToken', token);
      store.commit('setUserInfo', userinfo);
      next();
    } else {
      next({ path: '/login' });
    }
  } else {
    next('/error');
  }
})

Vue
  .use(Custom).use(Button).use(Option).use(Select).use(Form).use(FormItem).use(Menu).use(MenuItem).use(Submenu).use(Pagination).use(Table).use(TableColumn)
  .use(Input).use(Checkbox).use(CheckboxGroup).use(Header).use(Main).use(Aside).use(Container).use(BreadcrumbItem).use(Breadcrumb).use(DatePicker).use(Image)
  .use(Message).use(Radio).use(RadioGroup).use(Dialog).use(Upload).use(Tabs).use(TabPane).use(Col).use(Autocomplete).use(Loading).use(CascaderPanel)

Vue.prototype.$ELEMENT = { size: 'mini' };

Vue.component('site-icon', SiteIcon);
Vue.component('PageModel', PageModel);
Vue.component('CsBtn', CsBtn);

Vue.config.productionTip = false

Vue.mixin(mixins)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
