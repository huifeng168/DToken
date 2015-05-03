package com.connsec.domain;


public class ChangePassword extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2362608803392892403L;

	private String uid;
	private String username;
	
	private String oldPassword;
	private String password;
	private String confirmpassword;
	private String decipherable;
	
	/**
	 * 
	 */
	public ChangePassword() {

	}

	
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}


	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}


	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the confirmpassword
	 */
	public String getConfirmpassword() {
		return confirmpassword;
	}


	/**
	 * @param confirmpassword the confirmpassword to set
	 */
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}


	/**
	 * @return the decipherable
	 */
	public String getDecipherable() {
		return decipherable;
	}


	/**
	 * @param decipherable the decipherable to set
	 */
	public void setDecipherable(String decipherable) {
		this.decipherable = decipherable;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChangePassword [uid=" + uid + ", username=" + username
				+ ", password=" + password + ", confirmpassword="
				+ confirmpassword + ", decipherable=" + decipherable + "]";
	}
	
}
