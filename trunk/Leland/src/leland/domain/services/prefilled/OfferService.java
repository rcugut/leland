package leland.domain.services.prefilled;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import leland.domain.base.EntityWithName;
import leland.domain.enums.BillingScheduleType;
import leland.domain.services.ServiceDefinition;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name="LND_SERVICE")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="isOfferService", discriminatorType=DiscriminatorType.INTEGER)
@DiscriminatorValue("1")
public class OfferService
		extends EntityWithName
{
	protected BillingScheduleType billingType;
	protected double basePrice;	
	protected ServiceDefinition serviceDefinition;
	
	
	public OfferService()
	{
		super();
	}
	
	public OfferService(String name, BillingScheduleType billingType, double basePrice)
	{
		super(name);
		this.billingType = billingType;
		this.basePrice = basePrice;
	}












	public double getBasePrice()
	{
		return this.basePrice;
	}
	public OfferService setBasePrice(double fee)
	{
		this.basePrice = fee;
		return this;
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
	public OfferService setBillingType(BillingScheduleType type)
	{
		this.billingType = type;
		return this;
	}

	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	public ServiceDefinition getServiceDefinition()
	{
		return this.serviceDefinition;
	}
	public void setServiceDefinition(ServiceDefinition serviceDefinition)
	{
		this.serviceDefinition = serviceDefinition;
	}
}