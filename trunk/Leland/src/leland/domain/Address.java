package leland.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import leland.domain.base.EntityWithName;

import org.apache.wicket.util.string.Strings;

@Entity
@Table(name="LND_ADDRESS")
public final class Address
		extends EntityWithName
{
	private String street;
	private String number;
	private String detailsOfStreetAddress;
	private String city;
	private String county; // judet
	private String zip; // cod postal
	private String otherInfo;

	
	public Address()
	{
		super();
	}
	
	
	@Transient
	public String getAddresForDisplay()
	{
		return this.street +
		(Strings.isEmpty(this.number) ? "" : ", nr. " + this.number) +
		(Strings.isEmpty(this.detailsOfStreetAddress) ? "" : ", " + this.detailsOfStreetAddress) +
		(Strings.isEmpty(this.city) ? "" : ", " + this.city) +
		(Strings.isEmpty(this.county) ? "" : ", " + this.county) +
		(Strings.isEmpty(this.zip) ? "" : ", " + this.zip) +
		(Strings.isEmpty(this.otherInfo) ? "" : " (" + this.otherInfo + ")");
	}
	
	
	
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
	
	@Basic(optional=false)
	public String getNumber()
	{
		return this.number;
	}
	public Address setNumber(String number)
	{
		this.number = number;
		return this;
	}
	
	public String getDetailsOfStreetAddress()
	{
		return this.detailsOfStreetAddress;
	}
	public Address setDetailsOfStreetAddress(String otherInfo)
	{
		this.detailsOfStreetAddress = otherInfo;
		return this;
	}
	
	@Basic(optional=false)
	public String getCity()
	{
		return this.city;
	}
	public Address setCity(String city)
	{
		this.city = city;
		return this;
	}
	
	@Basic(optional=false)
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

	public String getOtherInfo()
	{
		return this.otherInfo;
	}
	public void setOtherInfo(String otherInfo)
	{
		this.otherInfo = otherInfo;
	}
}
