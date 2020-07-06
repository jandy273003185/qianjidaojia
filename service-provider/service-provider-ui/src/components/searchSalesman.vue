<template>
    <div>
        <van-nav-bar class="navBar" left-text="返回" title="搜索业务员" left-arrow @click-left="onClickLeft"/>
        <van-search
                v-model="userName"
                placeholder="请输入业务员名字"
                show-action autofocus="autofocus"
                shape="round"
                @search="onSearch"
        >
            <div slot="action" @click="onSearch">搜索</div>
        </van-search>
            <ul>
            <li v-for="(item, index) in nameList" :key="index" @click="into_result(item.userName,item.salesmanId)">
                {{ item.userName }}
            </li>
        </ul>
    </div>

</template>

<script type="text/ecmascript-6">
    import { adminIndex } from "../assets/api/interface";
    import {mapState,mapMutations} from 'vuex';
export default {
    name: "searchShop",
    data(){
        return {
            userName:'',
            nameList:[]
        }
    },
    computed:{
          ...mapState(['roleId','userId','role','custId'])
    },
    mounted(){

    },
    methods: {
        onClickLeft() {
            this.$router.go(-1);
        },

        onSearch(){//将this.value传到后台
            if(this.userName){
                this.searchSales();
            }else {
                this.$toast('请输入业务员名称');
                return;
            }
        },

        into_result(userName,userId){
            this.selectName({name:userName,id:userId});
            this.$router.push('salesmanResult');
        },

        async searchSales(){//搜索业务员
            const params = {
                userName:this.userName,
                custId:this.role == 'finance'?this.custId:this.userId,
            };
            const sales = await adminIndex.searchSales(params);
            if(sales.data){
                console.log(sales.data);
                this.nameList = sales.data.data;
            }
        },

        ...mapMutations(['selectName'])


    }
}
</script>

<style scoped lang="stylus" ref="stylesheet/stylus">
    @import "../style/common/base.styl";
        ul{
            width: 100%;
            margin-top: vw(20);
            background-color: #fff;

            li{
                width: vw(670);
                height: vw(94);
                margin: 0 auto;
                display: flex;
                justify-content:space-between;
                align-items: center;
                border-bottom: 1px solid #EEEEEE;

                div{
                    height: 100%;
                    margin: 0;
                    display: flex;
                    flex-direction: column;
                    justify-content:space-around;
                    align-items: flex-start;
                }
            }
        }



</style>
