package com.sim.api.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:app.properties")
public class AppProperties {

	@Value("${softcake.datasource.jndiName}")
	private String jndiName;
	
	@Value("${softcake.datasource.driverClassName}")
	private String driverClassName;
	
	@Value("${softcake.mail.host}")
	private String mailHost;
	
	@Value("${softcake.mail.port}")
	private int mailPort;
	
	@Value("${softcake.mail.username}")
	private String mailUsername;
	
	@Value("${softcake.mail.password}")
	private String mailPassword;
	
	@Value("${softcake.mail.properties.mail.smtp.starttls.enable}")
	private boolean smtpEnable;
	
	@Value("${softcake.mail.properties.mail.smtp.starttls.required}")
	private boolean smtpRequire;
	
	@Value("${softcake.mail.properties.mail.smtp.auth}")
	private boolean mailAuth;
	
	@Value("${softcake.mail.properties.mail.smtp.connectiontimeout}")
	private String mailConnectionTimeout;
	
	@Value("${softcake.mail.properties.mail.smtp.timeout}")
	private String mailTimeout;
	
	@Value("${softcake.mail.properties.mail.smtp.writetimeout}")
	private String mailWriteTimeout;
	
	@Value("${softcake.mail.properties.mail.smtp.protocol}")
	private String mailProtocol;
	
	@Value("${softcake.mail.properties.mail.smtp.debug}")
	private boolean mailDebug;
	
	@Value("${softcake.web.url}")
	private String webUrl;
	
	public String getJndiName() {
		return jndiName;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getMailHost() {
		return mailHost;
	}

	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	public int getMailPort() {
		return mailPort;
	}

	public void setMailPort(int mailPort) {
		this.mailPort = mailPort;
	}

	public String getMailUsername() {
		return mailUsername;
	}

	public void setMailUsername(String mailUsername) {
		this.mailUsername = mailUsername;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public boolean isSmtpEnable() {
		return smtpEnable;
	}

	public void setSmtpEnable(boolean smtpEnable) {
		this.smtpEnable = smtpEnable;
	}

	public boolean isSmtpRequire() {
		return smtpRequire;
	}

	public void setSmtpRequire(boolean smtpRequire) {
		this.smtpRequire = smtpRequire;
	}

	public boolean isMailAuth() {
		return mailAuth;
	}

	public void setMailAuth(boolean mailAuth) {
		this.mailAuth = mailAuth;
	}

	public String getMailConnectionTimeout() {
		return mailConnectionTimeout;
	}

	public void setMailConnectionTimeout(String mailConnectionTimeout) {
		this.mailConnectionTimeout = mailConnectionTimeout;
	}

	public String getMailTimeout() {
		return mailTimeout;
	}

	public void setMailTimeout(String mailTimeout) {
		this.mailTimeout = mailTimeout;
	}

	public String getMailWriteTimeout() {
		return mailWriteTimeout;
	}

	public void setMailWriteTimeout(String mailWriteTimeout) {
		this.mailWriteTimeout = mailWriteTimeout;
	}

	public String getMailProtocol() {
		return mailProtocol;
	}

	public void setMailProtocol(String mailProtocol) {
		this.mailProtocol = mailProtocol;
	}

	public boolean isMailDebug() {
		return mailDebug;
	}

	public void setMailDebug(boolean mailDebug) {
		this.mailDebug = mailDebug;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
		
	
}
