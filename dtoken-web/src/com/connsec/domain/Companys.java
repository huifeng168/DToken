package com.connsec.domain;

import com.connsec.domain.BaseDomain;

/**
 * Enterprises entity. @author MyEclipse Persistence Tools
 */

public class Companys extends BaseDomain{

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6429696970185514776L;


	  
	  private String fullName;
	  private String shortName;
	  private String division;
	  
	  private String representative;
	  private String category;
	  
	  private String contact;
	  private String webSite;
	  private String phone;
	  private String email;
	  private String fax;
	  private String postalCode;
	  private String license;
	  private String creationDate;
	  
	 
	  
	 
	  private String country;
	  private String region;//province;
	  private String locality;//city;
	  private String street;
	// Constructors
	/**
	 * 
	 */
	public Companys() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	/**
	 * 
	 */
	public Companys(String shortName) {
		super();
		this.shortName=shortName;
	} 
	
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}
	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	/**
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}
	/**
	 * @param division the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}
	/**
	 * @return the representative
	 */
	public String getRepresentative() {
		return representative;
	}
	/**
	 * @param representative the representative to set
	 */
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * @return the webSite
	 */
	public String getWebSite() {
		return webSite;
	}
	/**
	 * @param webSite the webSite to set
	 */
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the license
	 */
	public String getLicense() {
		return license;
	}
	/**
	 * @param license the license to set
	 */
	public void setLicense(String license) {
		this.license = license;
	}
	/**
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the locality
	 */
	public String getLocality() {
		return locality;
	}

	/**
	 * @param locality the locality to set
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Companys [fullName=" + fullName + ", shortName=" + shortName
				+ ", division=" + division + ", representative="
				+ representative + ", category=" + category + ", contact="
				+ contact + ", webSite=" + webSite + ", phone=" + phone
				+ ", email=" + email + ", fax=" + fax + ", postalCode="
				+ postalCode + ", license=" + license + ", creationDate="
				+ creationDate + ", country=" + country + ", region=" + region
				+ ", locality=" + locality + ", street=" + street + "]";
	}
}