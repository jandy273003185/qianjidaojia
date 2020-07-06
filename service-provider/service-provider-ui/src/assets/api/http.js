import axios from "axios";
import Qs from "qs";
import router from '../../router'
import store from '../../store'
import {
    Dialog
} from 'vant'; //弹窗函数，可直接调用

var cancel;

const CancelToken = axios.CancelToken;
axios.defaults.timeout = 30000;
/*axios.defaults.baseURL = '';*/ //统一设置baseURL
// 请求发送之前的拦截器
axios.interceptors.request.use(config => {
    if (store.state.token) { //已登录登陆
        //console.log(store.state.token);
        config.headers.common['token'] = store.state.token
    }
    /*   config.headers.common['token']='eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzZWNyZXQiOiJvaEV0bnN3Z28xRFMzaG1wd3lvOHd3eGd2UDNNIiwiZXhwIjoxNTc0NzcxODU4LCJ1c2VySWQiOiJTMTIyMjIyMjIyMjIifQ.7ZxQsvnRUfgje1jPDwOLoZNvlfoqpmMnzQWFnNGcq38';
     */
    return config
}, error => {
    return Promise.reject(error)
});

// 请求响应拦截器
axios.interceptors.response.use(res => {
    //console.log(res);
    if (res) {
        if (res.data) {
            const data = res.data;
            if (data.code != 200) {
                Dialog.alert({
                    title: data.msg
                }).then(() => {
                    if (data.code == 201 || data.code == 401) {
                        router.replace({
                            path: '/selectServiceMerchant'
                        })
                    }
                })
            }
        }
    }
    return res
}, error => {
    if (error && error.response) {
        console.log(error);
    } else {
        console.log("http发生错误～～");
    }
    return Promise.resolve(error.response);

});
export default {
    get(url, param) {
        return new Promise((resolve, reject) => {
            axios({
                    method: "get",
                    url: url,
                    params: param,
                    cancelToken: new CancelToken(c => {
                        cancel = c;
                        window.console.log(cancel);
                    })
                })
                .then(res => {
                    resolve(res);
                })
                .catch(err => {
                    reject(err);
                });
        });
    },
    post(url, param) {
        return new Promise((resolve, reject) => {
            axios({
                    method: "post",
                    data: param,
                    /*Qs.stringify(param),*/
                    url: url,
                    cancelToken: new CancelToken(c => {
                        cancel = c;
                    })
                })
                .then(res => {
                    resolve(res);
                })
                .catch(err => {
                    reject(err);
                });
        });
    },
    post1(url, param) {
        return new Promise((resolve, reject) => {
            axios({
                    method: "post",
                    data: Qs.stringify(param),
                    url: url,
                    cancelToken: new CancelToken(c => {
                        cancel = c;
                    })
                })
                .then(res => {
                    resolve(res);
                })
                .catch(err => {
                    reject(err);
                });
        });
    }
};