<template>
  <div class="sales">
    <van-nav-bar
      title="分润参考"
      left-text="返回"
      left-arrow
      @click-left="changePrepage"
    >
    </van-nav-bar>
    
        <div class="total">
          <div class="total-title" @click="selectDate">
            <div><p>近{{countDay}}天</p><p>{{queryStartDate}} 至 {{queryEndDate}}</p></div>
            <img src="../assets/images/home/time.png" alt="">
          </div>
          <div class="total-content">
            <div><p>{{totalAmount}}</p><p>交易额(元)</p></div>
            <div><p>{{number}}</p><p>交易笔数(笔)</p></div>
            <div><p>{{profit}}</p><p>分润金额(元)</p></div>
          </div>
        </div>

        <div class="modal-wrap" v-show="showTime">
          <div class="timePopup">
            <div class="text flex_center"><span class="titleBar">时间</span></div>
            <div class="text flex_s_b" plain type="primary" @click="showPopFn('timeStart')">
              <button>开始时间</button>
              <span>{{timeStart}}</span>
            </div>
            <div class="text flex_s_b "  plain type="primary" @click="showPopFn('timeEnd')">
              <button>结束时间</button>
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
</template>
<script>
import { mineInfo } from "@/assets/api/interface";
import { mapState } from "vuex";
import { Dialog } from "vant"; //弹窗函数，可直接调用

export default {
  name: "sales",
  components: {},
  data() {
    return {
      /*自定义时间*/
      maxDate: new Date(),
      currentDate: new Date(),
      changeDate: new Date(),
      show: false, // 用来显示日期弹出层
      timeStart:'',//选取的开始时间
      startMs:'',//选取的开始时间的毫秒数
      timeEnd:'',//选取的结束时间
      endMs:'',//选取的结束时间的毫秒数
      showTime:false,//点击选取时间时打开的遮罩层判断
      queryStartDate:'',
      queryEndDate:'',
      countDay:0,
      profit: 0, 
      number: 0,
      totalAmount: 0
    };
  },
  computed: {
    ...mapState(["custId","userId"])
  },
  created() {
    console.log(this.custId);
    this.queryStartDate = this.timeFormat(new Date(new Date().getTime() - 7*24*60*60*1000));
    this.queryEndDate = this.timeFormat(new Date());
    this.timeStart = this.timeFormat(new Date(new Date().getTime() - 7*24*60*60*1000));
    this.timeEnd = this.timeFormat(new Date());
    this.countDay = this.datedifference(this.queryEndDate,this.queryStartDate);
  },

  mounted(){
    this.getInitList();
  },
  methods: {
    async getInitList() {
      const params = {custId: this.userId||this.custId,queryStartDate:this.queryStartDate, queryEndDate:this.queryEndDate};
      let list = await mineInfo.getShareProfit(params);
      console.log(list);
      this.profit = list.data.data.profit || 0;
      this.number = list.data.data.number || 0;
      this.totalAmount = list.data.data.totalAmount || 0;
    },
    changePrepage() {
      //;返回
      this.$router.go(-1);
    },
    selectDate(){
      this.showTime = true;
    },
    //时间选择函数
    showPopFn(tm) {//点击选项（开始时间或者结束时间）打开日历，通过传值tm判断是开始时间还是结束时间
      this.show = true;
      this.type = tm;
    },
    changeFn() { // 值变化是触发
      this.changeDate = this.currentDate // Tue Sep 08 2020 00:00:00 GMT+0800 (中国标准时间)
    },
    confirmFn() { // 日历上的确定按钮
      console.log(this.type);
      if(this.type=='timeStart'){//选择开始时间
        this.timeStart = this.timeFormat(this.currentDate);
      }
      if(this.type=='timeEnd'){//选择结束时间
        this.timeEnd = this.timeFormat(this.currentDate);

      }
      this.show = false;
    },
    cancelFn(){//日历上的取消按钮
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
    cancel(){//取消选择自定义日期
      this.showTime = false;
    },
    affirm(){//确认选择的自定义日期
      this.startMs = new Date(this.timeStart).getTime();//开始日期的毫秒数
      this.endMs = new Date(this.timeEnd).getTime();//结束日期的毫秒数
      if(this.startMs && this.endMs){
        if(this.startMs <= this.endMs){
          this.showTime = false;
          console.log(this.timeStart,this.timeEnd);
          this.queryStartDate = this.timeStart;
          this.queryEndDate = this.timeEnd;

          this.countDay = this.datedifference(this.timeEnd,this.timeStart);
          //请求分页数据数据
          this.shopList=[]; //清空数组
          this.customShow = true;//打开list,重新请求数据
          this.getInitList();
        }else {
          Dialog({ message: '查看的开始时间必须小于或等于结束时间！！' });
        }
      }else {
        Dialog({ message: '请选择查看开始时间及结束时间！！' });
      }

    },
    datedifference(sDate1, sDate2) {    //计算天数
        let dateSpan,iDays;
        sDate1 = Date.parse(sDate1);
        sDate2 = Date.parse(sDate2);
        dateSpan = sDate2 - sDate1;
        dateSpan = Math.abs(dateSpan);
        iDays = Math.floor(dateSpan / (24 * 3600 * 1000));
        return iDays;
    }
  }
};
</script>
<style lang="scss" scoped>
@import "../style/views/mine.scss";
.rowTitle{
  font-size: 16px;
  color: #999999;
}
.van-search .van-cell{
  background-color: #efefef;
  border-radius: 6px;
  padding-left: 5px;
}
.van-search__action{
  color: #3f9aff;
}
.van-radio-group{
  display: inline-flex;
  margin-left: 10px;
}
.van-radio{
  margin-right: 14px;
}
.item{
  display: flex;
  align-items: center;
}
.sales .row{
  padding-left: vw(35);
  // height: vw(85);
  // line-height: vw(85);
}
.loaded{
  padding: vw(22);
  color: #999999;
}
select{
  width:vw(150);
  text-align: center;
  text-align-last: center;
}


.total{
  display: flex;
  background-color: #ffffff;
  flex-direction: column;
  margin: vw(30);
  padding: vw(20) vw(20) 0 vw(20)
}
.total-title,.total-title>div{
  display: flex;
  justify-content: space-between;
  line-height: vw(60);
}
.total-title>div>p:first-child{
  margin-right: vw(20);
  font-size: vw(28);
  color: #333333;
  font-weight: 600;
}
.total-title>div>p:last-child{
  font-size: vw(28);
  color: #666666;
}
.total-title img{
  width: vw(50);
  height: vw(50);
}
.total-content{
  display: flex;
  justify-content: space-between;
  border-top: 1px solid #f8f6f6;
  padding: vw(50) 0;
}
.total-content>div{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 50%;
  padding: vw(20);
}
.total-content>div>p:first-child{
  font-size: vw(36);
  color: #333333;
}
.total-content>div>p:last-child{
  font-size: vw(24);
  color: #999999;
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
        height: vw(80);
        .titleBar{
          font-size: vw(40);
        }
        span{
          font-size: vw(30);
        }
        button{
          width: vw(160);
          height: vw(60);
          background-color: #699dd7;
          color: #fff;
          border-radius: 3px;
        }
       }

    .btn{
      height: vw(100);

      button{
        width: vw(170);
        height: vw(60);
        border-radius: 3px;
      }
      .cancel{
        border:none;
        color: #699dd7;
        background-color: #fff;
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
</style>