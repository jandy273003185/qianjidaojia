// components/modal/modal.js
Component({
    /* 启用插槽 */
    options: {
        multipleSlots: true
    },
    /**
     * 组件的属性列表
     */
    properties: {
        isModal:{
            type:"boolean",
            value:false
        }
    },

    /**
     * 组件的初始数据
     */
    data: {
        isModal:false

    },

    /**
     * 组件的方法列表
     */
    methods: {
        confirm(){
            this.setData({
                isModal: false
            });
            this.triggerEvent('confirm',{'click':'confirm'});
        },
        concel() {
            this.setData({
                isModal:false
            });
            this.triggerEvent('concel', { 'click': 'concel' });
        },

    }
})
