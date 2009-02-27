package leland.domain.billing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import leland.domain.base.BaseEntity;

@Entity
@Table(name="LND_CLIENT_INVOICE")
public final class ClientInvoice
		extends BaseEntity
{
	private String code;
	private String number;
	
	private Date issueDate;
	private Date dueDate;

	private List<ClientInvoiceRow> rows = new ArrayList<ClientInvoiceRow>();

	private boolean paid = false;

	private double valueWithTax = Double.NaN;
	private double valueWithoutTax = Double.NaN;
	
	
	public ClientInvoice()
	{
		super();
		this.issueDate = GregorianCalendar.getInstance().getTime();
		Calendar c = GregorianCalendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, 20);
		this.dueDate = c.getTime();
	}
	
	
	@Transient
	public String getCodeAndNumber()
	{
		return this.code+"-"+this.number;
	}
	
	
	
	@Transient
	public double getValueWithoutTax()
	{
		if(Double.isNaN(this.valueWithoutTax))
		{
			double v = 0;
			for(ClientInvoiceRow r : this.rows)
			{
				v = v + r.getPrice() * r.getQuantity();
			}
			this.valueWithoutTax = v;
		}
		return this.valueWithoutTax;
	}

	@Transient
	public double getValueWithTax()
	{
		return this.getValueWithTax(false);
	}
	
	public double getValueWithTax(boolean forceRecaltulate)
	{
		if(Double.isNaN(this.valueWithTax) || forceRecaltulate)
		{
			double v = 0;
			for(ClientInvoiceRow r : this.rows)
			{
				v = v + r.getPrice() * r.getQuantity() * r.getVatRate();
			}
			this.valueWithTax = v;
		}
		return this.valueWithTax;
	}

	
	
	
	@Basic(optional=false)
	public String getCode()
	{
		return this.code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}

	@Basic(optional=false)
	public String getNumber()
	{
		return this.number;
	}
	public void setNumber(String number)
	{
		this.number = number;
	}

	public Date getIssueDate()
	{
		return this.issueDate;
	}
	public void setIssueDate(Date issueDate)
	{
		this.issueDate = issueDate;
	}

	public Date getDueDate()
	{
		return this.dueDate;
	}
	public void setDueDate(Date dueDate)
	{
		this.dueDate = dueDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	public List<ClientInvoiceRow> getRows()
	{
		return this.rows;
	}
	public void setRows(List<ClientInvoiceRow> rows)
	{
		this.rows = rows;
	}

	public boolean isPaid()
	{
		return this.paid;
	}
	public void setPaid(boolean paid)
	{
		this.paid = paid;
	}
}
