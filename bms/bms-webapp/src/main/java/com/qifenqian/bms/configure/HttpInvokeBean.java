package com.qifenqian.bms.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpComponentsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
public class HttpInvokeBean {

  @Value("${SYSTEM.CORE.IP}")
  private String SYSTEM_CORE_IP;

  @Value("${GATEWAY.UNIO.IP}")
  private String GATEWAY_UNIO_IP;

  @Value("${SYSTEM.PLUGIN.IP}")
  private String SYSTEM_PLUGIN_IP;

  @Value("${GATEWAY.KINGDEE.IP}")
  private String GATEWAY_KINGDEE_IP;

  @Value("${GATEWAY.CHANNEL.IP}")
  private String GATEWAY_CHANNEL_IP;

  /**
   * 调用核心接口
   *
   * @return
   */
  @Bean
  public HttpInvokerProxyFactoryBean coreHttpInvokerProxy() {
    HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
    bean.setServiceUrl(SYSTEM_CORE_IP + "/remoting/service");
    bean.setServiceInterface(com.sevenpay.invoke.SevenpayCoreServiceInterface.class);
    bean.setHttpInvokerRequestExecutor(new HttpComponentsHttpInvokerRequestExecutor());
    return bean;
  }

  /**
   * 调用交广科技网关的接口
   *
   * @return
   */
  @Bean
  public HttpInvokerProxyFactoryBean expressPayHttpInvokerProxy() {
    HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
    bean.setServiceUrl(SYSTEM_CORE_IP + "/gateway/jgkj");
    bean.setServiceInterface(com.sevenpay.gateway.jgkj.IJgkj.class);
    bean.setHttpInvokerRequestExecutor(new HttpComponentsHttpInvokerRequestExecutor());
    return bean;
  }

  /**
   * 调用银联接口
   *
   * @return
   */
  @Bean
  public HttpInvokerProxyFactoryBean httpInvokerProxyGatewayUnionPay() {
    HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
    bean.setServiceUrl(GATEWAY_UNIO_IP + "/gateway/unionpay");
    bean.setServiceInterface(com.stc.gateway.unionpay.IUnionPay.class);
    bean.setHttpInvokerRequestExecutor(new HttpComponentsHttpInvokerRequestExecutor());
    return bean;
  }
  /**
   * 调用plugin接口:SMS,EMAIL
   *
   * @return
   */
  @Bean
  public HttpInvokerProxyFactoryBean pluginInvokerProxy() {
    HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
    bean.setServiceUrl(SYSTEM_PLUGIN_IP);
    bean.setServiceInterface(com.sevenpay.plugin.IPlugin.class);
    bean.setHttpInvokerRequestExecutor(new HttpComponentsHttpInvokerRequestExecutor());
    return bean;
  }
  /**
   * 调用金蝶接口
   *
   * @return
   */
  @Bean
  public HttpInvokerProxyFactoryBean httpInvokerProxyGatewayKingdeePay() {
    HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
    bean.setServiceUrl(GATEWAY_KINGDEE_IP + "/gateway/k3cloud");
    bean.setServiceInterface(com.sevenpay.gateway.k3cloud.IK3Cloud.class);
    bean.setHttpInvokerRequestExecutor(new HttpComponentsHttpInvokerRequestExecutor());
    return bean;
  }

  /**
   * 下载原数据
   *
   * @return
   */
  @Bean
  public HttpInvokerProxyFactoryBean checkInvokerProxy() {
    HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
    bean.setServiceUrl(GATEWAY_CHANNEL_IP + "/checkApi");
    bean.setServiceInterface(com.seven.micropay.channel.service.ICheckService.class);
    bean.setHttpInvokerRequestExecutor(new HttpComponentsHttpInvokerRequestExecutor());
    return bean;
  }

  /**
   * 下载原数据
   *
   * @return
   */
  @Bean
  public HttpInvokerProxyFactoryBean channelCheckInvokerProxy() {
    HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
    bean.setServiceUrl(GATEWAY_CHANNEL_IP + "/combineApi");
    bean.setServiceInterface(com.seven.micropay.channel.service.api.ICombineApiService.class);
    bean.setHttpInvokerRequestExecutor(new HttpComponentsHttpInvokerRequestExecutor());
    return bean;
  }

  /**
   * 下载原数据
   *
   * @return
   */
  @Bean
  public HttpInvokerProxyFactoryBean iB2eInvokerProxy() {
    HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
    bean.setServiceUrl(GATEWAY_CHANNEL_IP + "/iB2eApiService");
    bean.setServiceInterface(com.seven.micropay.channel.service.api.IB2eApiService.class);
    bean.setHttpInvokerRequestExecutor(new HttpComponentsHttpInvokerRequestExecutor());
    return bean;
  }

  /**
   * 下载原数据
   *
   * @return
   */
  @Bean
  public HttpInvokerProxyFactoryBean iMerChantIntoServiceProxy() {
    HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
    bean.setServiceUrl(GATEWAY_CHANNEL_IP + "/iIMerChantIntoService");
    bean.setServiceInterface(com.seven.micropay.channel.service.IMerChantIntoService.class);
    bean.setHttpInvokerRequestExecutor(new HttpComponentsHttpInvokerRequestExecutor());
    return bean;
  }
  
  /**
   * 下载原数据
   *
   * @return
   */
//  @Bean
//  public HttpInvokerProxyFactoryBean iSuixingPayWeixinHandlerServiceProxy() {
//    HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
//    bean.setServiceUrl(GATEWAY_CHANNEL_IP + "/iSuixingPayWeixinHandlerService");
//    bean.setServiceInterface(com.seven.micropay.channel.service.ISuixingPayWeixinHandlerService.class);
//    bean.setHttpInvokerRequestExecutor(new HttpComponentsHttpInvokerRequestExecutor());
//    return bean;
//  }

  @Bean
  public HttpInvokerProxyFactoryBean iFmBlueOceanServiceProxy() {
    HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
    bean.setServiceUrl(GATEWAY_CHANNEL_IP + "/channel/blueOcean");
    bean.setServiceInterface(com.seven.micropay.channel.service.api.IFmBlueOceanService.class);
    bean.setHttpInvokerRequestExecutor(new HttpComponentsHttpInvokerRequestExecutor());
    return bean;
  }

  @Bean
  public HttpComponentsHttpInvokerRequestExecutor iB2eHttpInvokerRequestExecutor() {
    HttpComponentsHttpInvokerRequestExecutor executor =
        new HttpComponentsHttpInvokerRequestExecutor();
    executor.setConnectTimeout(60000);
    executor.setReadTimeout(30000);
    return executor;
  }
}
