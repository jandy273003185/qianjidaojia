<template>
  <div class="step clearfix">
    <div class="item" v-for="(item,index) in steps" v-bind:key="index" :class="{'last':item.step==4}">
      <div class="circle" :class="{'active':currStep>=item.step}">
        <span class="icon iconfont icon-step"></span>
        <span class="num">{{item.step}}</span>
      </div>
      <div :class="{'line':item.step!=4}"></div>
       <div class="line" :class="{'hide':item.step==4}"></div>
      <span class="name">{{item.name}}</span>
    </div>
  </div>
</template>
<script>
export default {
  name: "step",
  props: {
    currStep: null
  },
  data() {
    return {
        steps:[{'step':1,'name':'基本信息'},{'step':2,'name':'法人信息'},{'step':3,'name':'结算信息'},{'step':4,'name':'产品签约'}]
    };
  },
  watch: {},
  mounted() {},
  methods: {}
};
</script>
<style  lang="scss" scoped>
@import "../style/common/common.scss";
</style>
