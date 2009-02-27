package leland.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import leland.domain.base.BaseEntity;
import leland.domain.enums.BillingMethod;
import leland.domain.enums.ServiceType;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="LND_CONTRACT_SERVICE")
public abstract class AbstractService
		extends BaseEntity
{
	protected BillingMethod billingMethod = BillingMethod.ONE_TIME;
	protected double contractedPrice;
	protected String details;
	
	
	public AbstractService()
	{
		super();
	}
	
	
	@Transient
	public String getServiceTypeName()
	{
		return getServiceType().toString();
	}

	@Transient
	public abstract ServiceType getServiceType();

	
	
	@Column(columnDefinition="smallint")
	@Type(type="leland.domain.hibernate.GenericEnumUserType",
			parameters={
			@Parameter(name="enumClass", value="leland.domain.enums.BillingMethod"),
			@Parameter(name="identifierMethod", value="toInt"),
			@Parameter(name="valueOfMethod", value="fromInt")			
	})
	public BillingMethod getBillingMethod()
	{
		return this.billingMethod;
	}
	public AbstractService setBillingMethod(BillingMethod method)
	{
		this.billingMethod = method;
		return this;
	}
	
	
	public double getContractedPrice()
	{
		return this.contractedPrice;
	}
	public AbstractService setContractedPrice(double contractedPrice)
	{
		this.contractedPrice = contractedPrice;
		return this;
	}

	
	public String getDetails()
	{
		return this.details;
	}
	public AbstractService setDetails(String details)
	{
		this.details = details;
		return this;
	}
}
