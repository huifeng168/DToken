package com.connsec.config;

/**
 * 全局应用程序配置
 * 包含
 * 	1、数据源配置 dataSoruceConfig
 * 	2、字符集转换配置 characterEncodingConfig
 * 	3、webseal认证集成配置 webSealConfig
 * 	4、系统的配置 sysConfig
 *  5、所有用户可访问地址配置  allAccessUrl
 *  
 * 其中1、2、3项在applicationContext.xml中配置，配置文件applicationConfig.properties
 * 4项根据dynamic的属性判断是否动态从sysConfigService动态读取
 * 
 * @author Crystal.Sea
 * 
 */
public class ApplicationConfig {
	
	DataSoruceConfig dataSoruceConfig;
	
	EmailConfig emailConfig;
	
	CharacterEncodingConfig characterEncodingConfig;

	
	LoginConfig loginConfig;
	
	String domainName;
	
	String subDomainName;
	
	String allAccessUrl;
	
	
	public ApplicationConfig() {
		
		super();
	}
	
	public DataSoruceConfig getDataSoruceConfig() {
		return dataSoruceConfig;
	}


	public void setDataSoruceConfig(DataSoruceConfig dataSoruceConfig) {
		this.dataSoruceConfig = dataSoruceConfig;
	}

	/**
	 * @return the characterEncodingConfig
	 */
	public CharacterEncodingConfig getCharacterEncodingConfig() {
		return characterEncodingConfig;
	}



	/**
	 * @param characterEncodingConfig the characterEncodingConfig to set
	 */
	public void setCharacterEncodingConfig(
			CharacterEncodingConfig characterEncodingConfig) {
		this.characterEncodingConfig = characterEncodingConfig;
	}


	public LoginConfig getLoginConfig() {
		return loginConfig;
	}

	public void setLoginConfig(LoginConfig loginConfig) {
		this.loginConfig = loginConfig;
	}

	/**
	 * @return the domainName
	 */
	public String getDomainName() {
		return domainName;
	}

	/**
	 * @param domainName the domainName to set
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
		String []domainSubStrings=domainName.split("\\.");
		this.subDomainName=domainSubStrings[domainSubStrings.length-2]+"."+domainSubStrings[domainSubStrings.length-1];
		
	}



	/**
	 * @return the allAccessUrl
	 */
	public String getAllAccessUrl() {
		return allAccessUrl;
	}
	
	/**
	 * @return the allAccessUrl
	 */
	public String[] getAllAccessUrlArray() {
		return allAccessUrl.split(",");
	}

	/**
	 * @param allAccessUrl the allAccessUrl to set
	 */
	public void setAllAccessUrl(String allAccessUrl) {
		this.allAccessUrl = allAccessUrl;
	}

	/**
	 * @return the emailConfig
	 */
	public EmailConfig getEmailConfig() {
		return emailConfig;
	}

	/**
	 * @param emailConfig the emailConfig to set
	 */
	public void setEmailConfig(EmailConfig emailConfig) {
		this.emailConfig = emailConfig;
	}

	public String getSubDomainName() {
		return subDomainName;
	}

	public void setSubDomainName(String subDomainName) {
		this.subDomainName = subDomainName;
	}
	
}
