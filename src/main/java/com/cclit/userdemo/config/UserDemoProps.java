package com.cclit.userdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 *  User demo system properties class
 *  
 *  @author GalenLin
 */
@Component
@ConfigurationProperties(prefix = "userdemo.prop")
public class UserDemoProps {
	
	
	/*
	 *  System defalt infomation constructed enable
	 */
	private Boolean enableInit;

	public Boolean getEnableInit() {
		return enableInit;
	}

	public void setEnableInit(Boolean enableInit) {
		this.enableInit = enableInit;
	}
	
	

}
