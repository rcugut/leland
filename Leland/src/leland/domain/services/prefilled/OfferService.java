package leland.domain.services.prefilled;

import javax.persistence.Column;
import javax.persistence.Entity;

import leland.domain.base.EntityWithName;
import leland.domain.enums.BillingScheduleType;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
public class OfferService
		extends EntityWithName
{
	protected BillingScheduleType billingSchedule;
	protected double basePrice;	
	
	
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
	public BillingScheduleType getBillingSchedule()
	{
		return this.billingSchedule;
	}
	public OfferService setBillingSchedule(BillingScheduleType schedule)
	{
		this.billingSchedule = schedule;
		return this;
	}
}