package leland.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import leland.domain.base.BaseEntity;
import leland.domain.enums.BillingScheduleType;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;


@Entity
@Table(name="LND_CONTRACT_SERVICE_GENERIC")
@Inheritance(strategy=InheritanceType.JOINED)
public class ContractGenericService
		extends BaseEntity
{
//	protected OfferService 
	protected BillingScheduleType billingType;
	protected double contractedPrice;
	protected String details;
	
	public ContractGenericService()
	{
		super();
	}

	
	
	
	
	
	@Column(columnDefinition="smallint")
	@Type(type="leland.domain.hibernate.GenericEnumUserType",
			parameters={
			@Parameter(name="enumClass", value="leland.domain.enums.BillingScheduleType"),
			@Parameter(name="identifierMethod", value="toInt"),
			@Parameter(name="valueOfMethod", value="fromInt")			
	})
	public BillingScheduleType getBillingType()
	{
		return this.billingType;
	}
	public ContractGenericService setBillingType(BillingScheduleType type)
	{
		this.billingType = type;
		return this;
	}
	
	
	public double getContractedPrice()
	{
		return this.contractedPrice;
	}
	public void setContractedPrice(double contractedPrice)
	{
		this.contractedPrice = contractedPrice;
	}

	
	public String getDetails()
	{
		return this.details;
	}
	public void setDetails(String details)
	{
		this.details = details;
	}
}
