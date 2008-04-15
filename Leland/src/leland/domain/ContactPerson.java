package leland.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;

import leland.domain.base.BaseEntity;

@Entity
public class ContactPerson
		extends BaseEntity
{
//	private int typeBitMask; // [tech][financial][sales]
	private String fullName;
	
	private String phone;
	private String mobile;
	private String fax;
	private String email;

	
	
	@Basic(optional=false)
	public String getFullName()
	{
		return this.fullName;
	}
	public ContactPerson setFullName(String fullName)
	{
		this.fullName = fullName;
		return this;
	}
	
	public String getPhone()
	{
		return this.phone;
	}
	public ContactPerson setPhone(String phone)
	{
		this.phone = phone;
		return this;
	}
	
	public String getMobile()
	{
		return this.mobile;
	}
	public ContactPerson setMobile(String mobile)
	{
		this.mobile = mobile;
		return this;
	}
	
	public String getFax()
	{
		return this.fax;
	}
	public ContactPerson setFax(String fax)
	{
		this.fax = fax;
		return this;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	public ContactPerson setEmail(String email)
	{
		this.email = email;
		return this;
	}
}
