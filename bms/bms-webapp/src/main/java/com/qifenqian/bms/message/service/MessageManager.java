package com.qifenqian.bms.message.service;

/**
 * 发送各种消息
 * 
 * @author admin
 *
 */
public interface MessageManager {
  /**
   * 给收银员发送消息
   */
  void noticeCashier(String message);
}
