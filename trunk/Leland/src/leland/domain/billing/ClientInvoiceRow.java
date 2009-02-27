package leland.domain.billing;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import leland.domain.AbstractService;
import leland.domain.base.EntityWithName;

@Entity
@Table(name="LND_CLIENT_INVOICE_ROW")
public final class ClientInvoiceRow
		extends EntityWithName
{
	private int quantity = 1;

	private double vatRate = 19; //cota tva in %
	private double price; // fara TVA
	private AbstractService service;

	
	
	public ClientInvoiceRow()
	{
		super();
	}
	
	public ClientInvoiceRow(String name, String description, double price)
	{
		super(name);
		this.price = price;
	}



	public int getQuantity()
	{
		return this.quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public double getVatRate()
	{
		return this.vatRate;
	}
	public void setVatRate(double vatRate)
	{
		this.vatRate = vatRate;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}

	@ManyToOne(optional=true)
	public AbstractService getService()
	{
		return this.service;
	}
	public void setService(AbstractService service)
	{
		this.service = service;
	}
}
