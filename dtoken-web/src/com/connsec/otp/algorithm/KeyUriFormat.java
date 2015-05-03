package com.connsec.otp.algorithm;

public class KeyUriFormat {
	
	public class Types{
		public static final String HOTP ="hotp";
		public static final  String TOTP ="totp";
		
	}

	/**
	 * @param type
	 * @param secret
	 */
	public KeyUriFormat(String type, String secret) {
		this.type = type;
		this.secret = secret;
	}

	

	/**
	 * @param type
	 * @param secret
	 * @param issuer
	 */
	public KeyUriFormat(String type, String secret, String issuer) {
		this.type = type;
		this.secret = secret;
		this.issuer = issuer;
	}



	/**
	 * 
	 */
	public KeyUriFormat() {

	}


	String crypto="HmacSHA1";
	String type;
	String secret;
	String issuer;
	int digits =6;
	//just for hotp
	Long counter=0L;
	//just for totp
	int period =30;
	
	String account;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * @param secret the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * @return the issuer
	 */
	public String getIssuer() {
		return issuer;
	}

	/**
	 * @param issuer the issuer to set
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	/**
	 * @return the digits
	 */
	public int getDigits() {
		return digits;
	}

	/**
	 * @param digits the digits to set
	 */
	public void setDigits(int digits) {
		this.digits = digits;
	}

	/**
	 * @return the counter
	 */
	public Long getCounter() {
		return counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public void setCounter(Long counter) {
		this.counter = counter;
	}

	/**
	 * @return the period
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * @param period the period to set
	 */
	public void setPeriod(int period) {
		this.period = period;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	
	
	/**
	 * @return the crypto
	 */
	public String getCrypto() {
		return crypto;
	}



	/**
	 * @param crypto the crypto to set
	 */
	public void setCrypto(String crypto) {
		this.crypto = crypto;
	}



	public String format(){
		return format(this.account);
	}
	
	public String format(String account){
		StringBuffer uri=new StringBuffer("otpauth://");
		uri.append(type).append("/").append(account);
		if(null!=issuer){
			uri.append("@").append(issuer);
		}
		uri.append("?secret=").append(secret);
		
		if(null!=issuer){
			uri.append("&issuer=").append(issuer);
		}
		if(digits!=6){
			uri.append("&digits=").append(digits);
		}
		
		if(type.equalsIgnoreCase(Types.TOTP)){
			if(period!=30){
				uri.append("&period=").append(period);
			}
		}
		
		if(type.equalsIgnoreCase(Types.HOTP)){
			uri.append("&counter=").append(counter);
		}
		
		return uri.toString();
	}
	
}
