<template>
    <div>
        <van-nav-bar class="navBar" left-text="返回" :title="LookName+'业绩数据'" left-arrow @click-left="onClickLeft"/>
        <div class="information">
        <!--时间选择栏-->
            <div class="items timeSelect">
                <p  readonly>{{timeValue}}</p>
                <img  @click="timeSelect()" src="../assets/images/home/time.png" alt="">

            </div>
        <!--业务员数据栏-->
            <div class="items shop">
                <div>
                    <span class="number">{{detailList.commercialNum}}</span>
                    <span>进件商户数</span>

                </div>
                <div>
                    <span class="number">{{detailList.passingRateNum}}</span>
                    <span>开通商户数</span>
                </div>
            </div>
            <div class="items shop ">
                <div>
                    <span class="number">{{detailList.transactionNum}}</span>
                    <span>交易笔数(笔)</span>
                </div>
                <div>
                    <span class="number">{{detailList.transactionAmount}}</span>
                    <span>交易金额(元)</span>
                </div>
            </div>

            <div class="items shop border_b">
                <div>
                    <span class="number">{{detailList.perfectSum}}</span>
                    <span>待完善商户数</span>
                </div>
                <div>
                    <span class="number">{{detailList.reviewSum}}</span>
                    <span>待审核商户数</span>
                </div>
            </div>
            <div class="items shop">
                <div>
                    <span class="number">{{detailList.effectiveSum}}</span>
                    <span>有效商户数</span>
                </div>
                <div>
                    <span class="number">{{detailList.tradeAmtAvg | keepTwoDecimals}}</span>
                    <span>交易笔均金额(元)</span>
                </div>
            </div>
            <!--遮罩弹窗出时间选择页-->
            <div class="modal-wrap" v-show="showTime">
                <div class="timePopup">
                    <div class="text flex_center"><span class="titleBar">时间</span></div>
                    <div class="btn flex_s_b">
                        <button :class="{active:isActive==1}" @click="time_7()">近7天</button>
                        <button :class="{active:isActive==2}" @click="time_30()">近30天</button>
                        <button :class="{active:isActive==3}" @click="timeCustom()">自定义时间</button>
                    </div>
                    <div class="text flex_s_b" plain type="primary" @click="showPopFn('timeStart')">
                        <span>开始时间</span>
                        <span>{{timeStart}}</span>
                    </div>
                    <div class="text flex_s_b"  plain type="primary" @click="showPopFn('timeEnd')">
                        <span>结束时间</span>
                        <span>{{timeEnd}}</span>
                    </div>
                    <van-popup v-model="show" position="bottom" :style="{ height: '40%' }">
                        <van-datetime-picker v-model="currentDate" type="date" :max-date="maxDate" @change="changeFn()" @confirm="confirmFn()" @cancel="cancelFn()" />
                    </van-popup>
                    <div class="btn flex_r">
                        <button class="cancel" @click="cancel">取消</button>
                        <button class="affirm" @click="affirm">确认</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script type="text/ecmascript-6">
    /*import Cookie from 'js-cookie'*/
    import { Dialog } from "vant";
     import {mapState} from 'vuex';
    import { adminIndex } from "../assets/api/interface";
    export default {
        name: "timeSelect",
        data(){
            return{

                maxDate: new Date(),
                currentDate: new Date(),
                changeDate: new Date(),
                show: false, // 用来显示弹出层
                timeValue:'近七天',//弹出框的左上角时间
                timeStart:'',//选取的开始时间
                timeEnd:'',//选取的结束时间

                showTime:false,//点击选取时间时打开的遮罩层判断
                isActive:1,     //动态绑定class使用
                type:'',//判断是请求开始时间还是结束时间

                detailList:{}

            }
        },

        computed:{
            ...mapState(['salesID','LookName'])
        },
        mounted(){
            /*this.nowTime();*/
            this.time_7();
            this.salesDetail();
        },
        methods:{
            onClickLeft() {
                this.$router.go(-1);
            },
            showPopFn(tm) {
                if(this.isActive==3){
                    this.show = true;
                }
                this.type = tm;
            },

            changeFn() { // 值变化是触发
                this.changeDate = this.currentDate // Tue Sep 08 2020 00:00:00 GMT+0800 (中国标准时间)
            },
            confirmFn() { // 日历上的确定按钮
                if(this.type=='timeStart'){
                    this.timeStart = this.timeFormat(this.currentDate);
                }
                if(this.type=='timeEnd'){
                    this.timeEnd = this.timeFormat(this.currentDate);
                }
                this.show = false;
            },
            cancelFn(){//取消按钮
                this.show = false;
            },
            timer(time){//将个位数日期变两位数
                return time < 10 ? '0' + time : '' + time;
            },
            timeFormat(time) { // 时间格式化 2019-09-08
                let year = time.getFullYear();
                let month = time.getMonth() + 1;
                month = this.timer(month);
                let day = time.getDate();
                day = this.timer(day);
                return year + '-' + month + '-' + day
            },
            nowTime(){
                let time = new Date();
                this.timeValue = this.timeFormat(time);
            },
            timeSelect(){
                this.showTime = true;
            },
            cancel(){//取消查看日期
                this.showTime = false;
            },
            affirm(){//确认查看日期

                if(this.isActive==1){
                    this.timeValue = '近七天';
                    this.showTime = false;
                }else {
                    if(this.isActive==2){
                        this.timeValue = '近三十天';
                        this.showTime = false;
                    }
                    if(this.isActive==3){
                        this.startMs = new Date(this.timeStart).getTime();//开始日期的毫秒数
                        this.endMs = new Date(this.timeEnd).getTime();//结束日期的毫秒数
                        if(this.startMs <= this.endMs){
                            this.showTime = false;
                            this.timeValue = this.timeStart + '到' + this.timeEnd;
                            console.log(this.timeStart,this.timeEnd);
                        }else {
                            Dialog({ message: '查看的开始时间必须小于或等于结束时间！！' });
                            return;
                        }
                    }
                    this.salesDetail();
                }


            },
            time_7(){
                this.isActive = 1;
                this.fun_date(7);
            },
            time_30(){
                this.isActive = 2;
                this.fun_date(30);
            },
            timeCustom(){
                this.isActive = 3;
            },
            fun_date(num) {
                var date1 = new Date();
                //今天时间
                this.timeEnd = this.timeFormat(date1);
                var date2 = new Date(date1);
                date2.setDate(date1.getDate() - num);
                //num是正数表示之后的时间，num负数表示之前的时间，0表示今天
                this.timeStart = this.timeFormat(date2);
            },
            async salesDetail(){
                const params = {
                    id:this.salesID ,
                    queryStartDate: this.timeStart,
                    queryEndDate:this.timeEnd,
                    roleId:'3'
                };
                console.log(params.id);
                console.log(params.queryStartDate);
                console.log(params.queryEndDate);

                const detailResult = await adminIndex.salesDetail(params);
                console.log(detailResult);
                if(detailResult || detailResult.data){
                    this.detailList = detailResult.data.data;
                }

            }
        },
        filters:{
             keepTwoDecimals(amount){
                return Number(amount).toFixed(2);
            }
        }

    }
</script>

<style scoped lang="stylus" ref="stylesheet/stylus">
    @import "../style/common/base.styl";
    .information{
        width: vw(690);
       /* height: 157px;*/
        margin: vw(20) auto;
        border: vw(1) solid #c5c5c5;
        background-color:#FFFFFF;

        .modal-wrap{
            width: 100%;
            height: 100%;
            position: fixed;
            top:0;
            right: 0;
            bottom: 0;
            left: 0;
            background-color: rgba(0,0,0,0.5);
            z-index: 999;
    .timePopup{
        width: 95%;
        margin: auto;
        /* height: vw(340);*/
        border-radius: vw(10);
        position: relative;
        top: 30%;
        background-color: #FFFFFF;
        padding:0 vw(30);
        box-sizing: border-box;
    .text{
        height: vw(60);
        .titleBar{
            font-size: vw(40);
        }
    }
    .btn{
        height: vw(100);

    button{
        border: 1px solid #797979;
        width: vw(170);
        height: vw(60);
        border-radius: 3px;
        background-color: #fff;
    &.active{
         border-color: #33CCFF;
         color:#33CCFF;
     }
    }
    .cancel{
        border:none;
        color: #699dd7;
    }
    .affirm{
        background-color: #699dd7;
        color: #fff;
        border: none;
        margin-left: vw(10);
    }
    }
    }
        }

        .items{
            width: vw(614);
            margin: 0 auto ;

        }

        .timeSelect{
            height: vw(80);
            display:flex;
            align-items:center;
            justify-content:space-between;

            p{
                width: vw(440);
                height: vw(50);
                line-height: vw(50);
                font-size: vw(36);
                color: #949494;
            }
            img{
                width: vw(60);
                height: vw(60);
            }
        }

        .shop{
            height:vw(120);
            display: flex;
            justify-content: space-around;
            align-items: center;
            border-top: 1px solid #c5c5c5;
            font-weight:bold;

            div{
                height: vw(80);
                display: flex;
                flex-direction:column;
                justify-content: center;
                align-items: center;
                color: #9e9e9e;

                span:first-child{
                    color: #292929;
                }
            }
        }
        .border_b{
            border-top: vw(10) solid #c5c5c5;
        }
    }
</style>
