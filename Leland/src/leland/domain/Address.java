package leland.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;

import leland.domain.base.BaseEntity;

@Entity
public class Address
		extends BaseEntity
{
	private String street;
	private String number;
	private String otherInfo;
	private String city;
	private String county; // judet
	private String zip; // cod postal

	
	@Basic(optional=false)
	public String getStreet()
	{
		return this.street;
	}
	public Address setStreet(String street)
	{
		this.street = street;
		return this;
	}
	public String getNumber()
	{
		return this.number;
	}
	public Address setNumber(String number)
	{
		this.number = number;
		return this;
	}
	public String getOtherInfo()
	{
		return this.otherInfo;
	}
	public Address setOtherInfo(String otherInfo)
	{
		this.otherInfo = otherInfo;
		return this;
	}
	public String getCity()
	{
		return this.city;
	}
	public Address setCity(String city)
	{
		this.city = city;
		return this;
	}
	public String getCounty()
	{
		return this.county;
	}
	public Address setCounty(String county)
	{
		this.county = county;
		return this;
	}
	
	public String getZip()
	{
		return this.zip;
	}
	public Address setZip(String zip)
	{
		this.zip = zip;
		return this;
	}
}
