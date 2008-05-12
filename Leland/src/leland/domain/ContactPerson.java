package leland.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

import leland.domain.base.BaseEntity;

@Entity
@Table(name="LND_CONTACT_PERSON")
public final class ContactPerson
		extends BaseEntity
{
	private int typeBitMask; // [tech][financial][sales]
	private String fullName;
	
	private String phone;
	private String mobile;
	private String fax;
	private String email;

	private String otherInfo;
	
	
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

	public String getOtherInfo()
	{
		return this.otherInfo;
	}
	public void setOtherInfo(String otherInfo)
	{
		this.otherInfo = otherInfo;
	}
	
	public int getTypeBitMask()
	{
		return this.typeBitMask;
	}
	public void setTypeBitMask(int typeBitMask)
	{
		this.typeBitMask = typeBitMask;
	}
}
