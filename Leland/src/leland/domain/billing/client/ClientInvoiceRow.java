package leland.domain.billing.client;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import leland.domain.base.EntityWithName;

@Entity
@Table(name="LND_CLIENT_INVOICE_ROW")
public final class ClientInvoiceRow
		extends EntityWithName
{
	private ClientInvoice invoice;
	
	private String description;
	private int quantity;

	private double vatRate; //cota tva in %
	private double price; // fara TVA
//	private double discount; // %

	
	
	@ManyToOne()
	public ClientInvoice getInvoice()
	{
		return this.invoice;
	}
	public void setInvoice(ClientInvoice invoice)
	{
		this.invoice = invoice;
	}
	
	
	
	public String getDescription()
	{
		return this.description;
	}
	public void setDescription(String description)
	{
		this.description = description;
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
}
