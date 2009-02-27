package leland.domain.billing;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import leland.domain.base.BaseEntity;
import leland.domain.enums.PaymentType;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name="LND_CLIENT_PAYMENT")
public final class ClientPayment
		extends BaseEntity
{
	private PaymentType paymentType;
	private double amount;
	private ClientInvoice invoice;
	
//	private Map<String, String> details = new HashMap<String, String>();


	
	public ClientPayment()
	{
		super();
		this.paymentType = PaymentType.CASH;
	}

	
	
	
	
	
	@Column(columnDefinition="smallint")
	@Type(type="leland.domain.hibernate.GenericEnumUserType",
			parameters={
			@Parameter(name="enumClass", value="leland.domain.enums.PaymentType"),
			@Parameter(name="identifierMethod", value="toInt"),
			@Parameter(name="valueOfMethod", value="fromInt")			
	})
	public PaymentType getPaymentType()
	{
		return this.paymentType;
	}
	public void setPaymentType(PaymentType paymentType)
	{
		this.paymentType = paymentType;
	}

	
	public double getAmount()
	{
		return this.amount;
	}
	public void setAmount(double amount)
	{
		this.amount = amount;
	}


	@ManyToOne
	public ClientInvoice getInvoice()
	{
		return this.invoice;
	}
	public void setInvoice(ClientInvoice invoice)
	{
		this.invoice = invoice;
	}

	
//	public Map<String, String> getDetails()
//	{
//		return this.details;
//	}
//	public void setDetails(Map<String, String> details)
//	{
//		this.details = details;
//	}
}
