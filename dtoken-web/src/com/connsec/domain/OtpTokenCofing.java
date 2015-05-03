package com.connsec.domain;


public class OtpTokenCofing extends BaseDomain{


	/**
	 * 
	 */
	private static final long serialVersionUID = -6568485703632720034L;

	public static final int TYPES_HOTP =  1;
	
	public static final int TYPES_TOTP =  2;
	
	/**
	 * 
	 */
	String type;
	String issuer;
	int digits =6;
	//just for hotp
	Long counter=0L;
	//just for totp
	int period =30;
	
	String crypto;
	
	/**
	 * 
	 */
	public OtpTokenCofing() {

	}
	


	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}


	public String getIssuer() {
		return issuer;
	}



	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}



	public int getDigits() {
		return digits;
	}



	public void setDigits(int digits) {
		this.digits = digits;
	}



	public Long getCounter() {
		return counter;
	}



	public void setCounter(Long counter) {
		this.counter = counter;
	}



	public int getPeriod() {
		return period;
	}



	public void setPeriod(int period) {
		this.period = period;
	}



	public String getCrypto() {
		return crypto;
	}



	public void setCrypto(String crypto) {
		this.crypto = crypto;
	}



	@Override
	public String toString() {
		return "OtpTokenCofing [type=" + type + ", issuer=" + issuer
				+ ", digits=" + digits + ", counter=" + counter + ", period="
				+ period + "]";
	}


}
