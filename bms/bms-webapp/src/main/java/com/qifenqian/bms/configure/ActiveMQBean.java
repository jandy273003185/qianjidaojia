package com.qifenqian.bms.configure;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class ActiveMQBean {

	@Value("${MQ_URL}")
	private String MQ_URL;

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(MQ_URL);
		factory.setUserName("admin");
		factory.setPassword("admin");
		return factory;
	}

	@Bean
	public ActiveMQQueue createQueue() {
		ActiveMQQueue queue = new ActiveMQQueue("OFFICIALMSG.IMQ");
		return queue;
	}

	@Bean
	public JmsTemplate template(ActiveMQConnectionFactory connectionFactory, ActiveMQQueue queueDestination) {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultDestination(queueDestination);
		template.setReceiveTimeout(10000);
		return template;
	}

}
