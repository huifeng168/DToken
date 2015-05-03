package com.connsec.domain;

/**
 * @author Crystal.Sea
 * 
 */
public class UserInfo extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6402443942083382236L;
	//

	protected String 	username;
	protected String 	password;
	protected String 	sharedSecret; 
	protected String 	sharedCounter; 
	/**
	 * "Employee", "Supplier","Dealer","Contractor",Partner,Customer "Intern",
      "Temp", "External", and "Unknown"
	 */
	protected String 	userType;	
	
	//for user name
	protected String 	displayName;
	protected String 	nameZHShortSpell;
	protected String 	givenName;
	protected String 	middleName;
	protected String 	familyName;
	protected int 		gender;

	
	//for security
	protected String 	email;
	protected int 		emailVerified;
	protected String 	mobile;
	protected int 		mobileVerified;
	
	//for company
	protected String 	employeeNumber;
	protected String 	organization;
	protected String 	division;
	protected String 	departmentId;
	protected String 	department;
	protected String 	jobTitle;
		

	
	public static class GENDER{
		//未知
		public static int UNKNOWN=0;
		//女性
		public static int FEMALE=1;
		//男性
		public static int MALE=2;	
	}
	

	/**
	 * 
	 */
	public UserInfo() {
		super();
	}

	/**
	 * @param username
	 */
	public UserInfo(String username) {
		super();
		this.username = username;
	}

	/**
	 * @param username
	 * @param password
	 */
	public UserInfo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSharedSecret() {
		return sharedSecret;
	}

	public void setSharedSecret(String sharedSecret) {
		this.sharedSecret = sharedSecret;
	}

	public String getSharedCounter() {
		return sharedCounter;
	}

	public void setSharedCounter(String sharedCounter) {
		this.sharedCounter = sharedCounter;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	public String getNameZHShortSpell() {
		return nameZHShortSpell;
	}

	public void setNameZHShortSpell(String nameZHShortSpell) {
		this.nameZHShortSpell = nameZHShortSpell;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(int emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getMobileVerified() {
		return mobileVerified;
	}

	public void setMobileVerified(int mobileVerified) {
		this.mobileVerified = mobileVerified;
	}


	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}




}
