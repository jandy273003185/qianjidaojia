package com.qifenqian.bms.message.service.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.message.service.MessageManager;

@Service
public class MessageManagerImpl implements MessageManager {

  @Autowired
  private JmsTemplate jmsTemplate;

  /**
   * 给收银员发送消息
   */
  @Override
  public void noticeCashier(final String message) {


    jmsTemplate.send(jmsTemplate.getDefaultDestination(), new MessageCreator() {
      public Message createMessage(Session session) throws JMSException {
        return session.createTextMessage(message);
      }
    });

  }

}
