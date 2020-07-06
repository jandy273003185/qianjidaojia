package com.qifenqian.bms.configure;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.qifenqian.bms.platform.common.config.SystemInfo;
import com.qifenqian.bms.platform.common.config.type.ReleaseType;

@Component
public class Beans {
	private String host = "smtp.exmail.qq.com";

	private String username = "webmaster@szgyzb.com";
	private String password = "wm123456";

	private String mail_smtp_auth = "true";
	private String mail_smtp_timeout = "2500";

	/**
	 * 邮件发送
	 * 
	 * @return
	 */
	@Bean("javaMailSender")
	public JavaMailSenderImpl createMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.auth", mail_smtp_auth);
		prop.setProperty("mail.smtp.timeout", mail_smtp_timeout);
		mailSender.setJavaMailProperties(prop);
		return mailSender;
	}

	@Bean
	public SystemInfo createSystemInfo() {
		SystemInfo bean = new SystemInfo();
		bean.setSystemName("七分钱后台管理系统,七分钱商城后台");
		bean.setSystemVersion("1.0.0.0");
		bean.setReleaseDate("2015-07-15");
		bean.setReleaseType(ReleaseType.PRODUCT);
		bean.setDeveloper("国银证保-信息技术部");
		bean.setUser("国银证保-信息技术部");
		bean.setSystemUrl("http://192.168.1.15:5005/sevenpay,http://192.168.1.15:5005/sevenmall");
		return bean;
	}
}
