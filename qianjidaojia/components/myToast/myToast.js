// components/myToast/myToast.js
Component({
    /**
     * 组件的属性列表
     */
    properties: {
        toastText: {
            type: 'string',
            value: ''
        },
        isToast: {
            type: 'boolean',
            value: false
        },
        toastNum:{
            type:"number",
            value:0
        }
    },

    /**
     * 组件的初始数据
     */
    data: {

    },
    lifetimes: {
        attached: function () {
            // 在组件实例进入页面节点树时执行

        },
        detached: function () {
            // 在组件实例被从页面节点树移除时执行
        },
    },

    /* 监听数据 */

    observers:{
        'toastNum': function (){
            var that = this;
            var timer = setTimeout(function(){
                that.setData({
                    isToast:false 
                });
                clearTimeout(timer);
            },2000);
        }
    },

    /**
     * 组件的方法列表
     */
    methods: {

    },


})
