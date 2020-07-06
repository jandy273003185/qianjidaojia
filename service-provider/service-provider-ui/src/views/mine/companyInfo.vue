<template>
    <div class="company">
        <van-nav-bar
                title="关于我们"
                left-text="返回"
                left-arrow
                @click-left="onClickLeft"
        />
        <div class="companyDetail">
            <h4>公司简介</h4>
            <p>深圳市七分钱网络科技有限公司{简称“七分钱”}致力于打造国际专业化的第三方支付平台，旨在为各类企业及个人提供安全、便捷和专业的互联网支付解决方案。七分钱自创立至今，凝聚了横跨产品、研发、互联网金融及部分细分行业的资深专家团队，拥有国内一流的产品研发和创新能力，可以为合作伙伴提供创新、便捷、稳定、专业的支付解决方案和资金清算服务。</p>
            <h4>企业使命</h4>
            <p>为客户提供多元化的支付服务产品与金融解决方案，满足并超越客户个性化的需求</p>
            <h4>企业愿景</h4>
            <p>成为广受赞誉的国际一流的第三方支付平台与互联网金融综合服务提供商</p>
            <h4>核心价值观</h4>
            <p>诚信敬业、勇于创新、力行高效、追求卓越、合作共赢</p>
            <div class="foot foot_top">
                <h5>客服电话：</h5><p class="phone">400-633-0707</p>
            </div>
            <div class="foot">
                <h5>企业名称：</h5><p>深圳市七分钱网络科技有限公司</p>
            </div>
            <div class="foot">
                <h5>企业地址：</h5><p>深圳市南山区软件产业基地4栋A座</p>
            </div>
            <div class="foot">
                <h5>企业网址：</h5><a href="www.qifenqian.com">www.qifenqian.com</a>
            </div>
        </div>
        
    </div>
</template>
<script>
export default {
  name: "feedback",
  components: {

  },
  computed: {},
  created() {
  },
  data() {
    return {
      
    };
  },
  methods: {
      onClickLeft() {
          this.$router.go(-1);
      },
  }
};
</script>
<style lang="scss" scoped>
@import "../../style/views/incoming.scss";
.company{
    width: 100%;
    background-color: #fff;
    /*.van-nav-bar{//topbar修改默认样式
        background-color: #26a7e9;
        .van-icon-arrow-left:before,.van-nav-bar__text,.van-nav-bar__title{
            color: #FFFFFF;
        }
    }*/
    .companyDetail{
        width: vw(690);
        margin:auto;
        p{
            color: #a1a1a1;
            line-height: vw(36);
            text-indent:vw(54);
        }
        h4{
            color: #85baf5;
            height: vw(26);
            line-height: vw(26);
        }
        .foot{
            display: flex;
            height: vw(50);
            justify-content: flex-start;
            align-items: center;
            color: #2d2d2d;
            .phone{
                color: #d45d44;
            }
            p{
                color: #2d2d2d;
                text-indent:vw(0);
            }
        }
        .foot_top{
            margin-top: vw(140);
        }
    }


}





</style>