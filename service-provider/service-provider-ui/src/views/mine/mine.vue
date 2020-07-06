<template>
    <div class="mine">
        <div class="info">
            <img class="photo"
                 src="../../assets/images/login/login.png"
            />
            <div class="text">
                <!--<span>xxxx有限公司</span>-->
                <div class="name">
                    <span v-if="role=='agent'">管理员:</span>
                    <span v-if="role=='finance'">财务员:</span>
                    <span v-if="role=='salesman'">业务员:</span>
                    <span>{{showName}}</span>
                </div>
            </div>
        </div>
        <div class="menu">
            <router-link to="/amendPsd" class="item">修改登录密码
                <van-icon class="icon" name="arrow"/>
            </router-link>
            <router-link to="/salesManage" class="item" v-if="role=='agent'">业务员管理
                <van-icon class="icon" name="arrow"/>
            </router-link>
            <router-link to="/financialManage" class="item" v-if="role=='agent'">财务管理
                <van-icon class="icon" name="arrow"/>
            </router-link>
            <router-link to="/shareProfit" class="item" v-if="role=='agent'||role=='finance'">分润参考
                <van-icon class="icon" name="arrow"/>
            </router-link>
            <!-- <div class="item" v-if="role=='finance'" @click="getBill">获取账单
                <van-icon class="icon" name="arrow"/>
            </div>  -->
            <router-link to="/about" class="item">关于
                <van-icon class="icon" name="arrow"/>
            </router-link>
            <div class="item">
                <span>联系客服</span>
                <span>400-633-0707</span>
            </div>
        </div>
        <div class="logout unbind" @click="unbind">解除绑定</div>
        <div class="logout" @click="logout">退出登录</div>
        <BaseHeader></BaseHeader>
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
                 <div class="text flex_s_b"  plain type="primary" @click="showPopFn('timeEnd')">
                    <span>邮箱地址</span>
                    <input placeholder="请输入账单邮箱地址"/>
                </div>
                <van-popup v-model="show" position="bottom" :style="{ height: '40%' }">
                    <van-datetime-picker v-model="currentDate" type="date" :max-date="maxDate" @change="changeFn()" @confirm="confirmFn()" @cancel="cancelFn()" />
                </van-popup>
                <div class="btn flex_r">
                    <button class="cancel" @click="cancel">取消</button>
                    <button class="affirm" @click="affirm">发送账单</button>
                </div>
            </div>
        </div>

        <!--遮罩弹窗出发送账单成功-->
        <div class="modal-wrap" v-show="billSuccess">
            <div class="timePopup">
                <div class="bill-sucess">
                    <div><img src="../../assets/images/ok.png"/></div>
                    <p class="success-title">提交成功</p>
                    <p class="success-content">预计 <span>1-2</span> 个工作日内发送至邮箱</p>
                    <p class="success-email">{{email}}</p>
                    <p class="back">返回</p>
                    <p class="auto-back"><span>{{second}}s</span> 后自动返回首页</p>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    import {mineInfo,login } from "@/assets/api/interface";
    import {mapState} from 'vuex';
    import BaseHeader from "@/components/baser-header.vue";
    import { Dialog } from 'vant';//弹窗函数，可直接调用
    export default {
        name: "mine",
        components: {
            BaseHeader
        },
        data(){
            return {
                showName:'',
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

                billSuccess:false,
                email:'888888888@qq.com',
                second:3
            };
        },
        computed: {
                ...mapState(['roleId','role','userId','userName','token'])
        },
        created(){

        },
        mounted(){
            //this.getUserName();
            // if(this.roleId == '3'){
                this.showName=this.userName;
            // }
            this.time_7();
        },
        methods: {
            async getUserName(){//获取管理员
                if(this.roleId == '2'){//管理员
                    const infoData=await mineInfo.agent({custId:this.userId});
                    console.log('管理员信息');
                    console.log(infoData);
                    if(infoData.data.data){
                        this.showName=infoData.data.data
                    }else {
                        this.showName='战略服务商';
                    }

                }
               /* if(this.roleId == '3'){//业务员
                    const infoData=await mineInfo.salesman({id:this.userId});
                    console.log('业务员信息');
                    console.log(infoData);
                }*/
            },
            getBill(){//获取账单
                this.showTime = true;
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
            affirm(){//发送账单
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
                this.billSuccess = true;
                this.threeTime();
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
            threeTime(){
                let time = 2;
                const interval = setInterval(() => {
                    this.second = time;
                    --time;
                    if(time < 0){clearInterval(interval)}
                }, 1000);
            },
            unbind(){//解除绑定
                Dialog.confirm({
                    title: '是否确定解除绑定'
                }).then(() => {
                    login.userLoginRelate({userId:this.userId,userType:this.role});
                }).catch(() => {
                // on cancel
                });
            },
            logout(){//退出登录
                Dialog.confirm({
                    title: '是否确定退出登录'
                }).then(async () => {
                    const result = await login.loginout(this.token);
                    console.log('result-----------------'+result);
                    if(result.data.code == 200){
                        this.$router.replace({
                        name: "selectServiceMerchant",
                        params: {
                            fname: "selectServiceMerchant"
                        }
                        });
                    }
                }).catch(() => {
                // on cancel
                }); 
            }
        }
    };
</script>
<style lang="scss" scoped>
    @import "../../style/views/mine.scss";

    .mine {
        .info{
            display: flex;
            justify-content: flex-start;
            align-items: center;
            .photo{
                width: vw(80);
                height: vw(80);
            }
        }
        .menu {

            .item {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

        }
    }

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
    }
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
     }
     .text{
        height: vw(60);
    }
    .titleBar{
        font-size: vw(40);
    }
    .btn{
        height: vw(100);
    }
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
    .flex_s_b input{
        text-align: right;
    }
    .bill-sucess{
        display: flex;
        justify-content: center;
        flex-direction: column;
        align-items: center;
    }
    .bill-sucess img{
        width: vw(130);
        height: vw(130);
        margin-top: vw(40);
    }
    .success-title{
        font-size: vw(35);
        font-weight: 500;
        margin:vw(20) 0 vw(10) 0;
    }
    .success-content{
        color: #a29c9c;
        font-size: vw(30);
    }
    .success-content span,.auto-back span{
        color: #699dd7;
    }
    .success-email{
        font-size: vw(30);
        color: #464242;
    }
    .back{
        font-size: vw(30);
        width: 100%;
        border-top: 1px solid #f8f4f4;
        border-bottom: 1px solid #f8f4f4;
        text-align: center;
        padding: vw(26);
        margin: vw(50) 0 vw(26) 0;
    }
    .auto-back{
        margin-bottom: vw(28);
        color: #a29c9c;
        font-size: vw(28);
    }
</style>