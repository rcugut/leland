package leland.domain.billing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import leland.domain.Client;
import leland.domain.base.BaseEntity;

@Entity
public class Invoice
		extends BaseEntity
{
	private Client client;
	
	private String code;
	private String number;
	
//	private String 
	
	private Date issueDate;
	private Date dueDate;

	private List<InvoiceRow> rows = new ArrayList<InvoiceRow>();

	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	public Client getClient()
	{
		return this.client;
	}
	public void setClient(Client client)
	{
		this.client = client;
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice", fetch=FetchType.LAZY)
	public List<InvoiceRow> getRows()
	{
		return this.rows;
	}
	public void setRows(List<InvoiceRow> rows)
	{
		this.rows = rows;
	}
}
