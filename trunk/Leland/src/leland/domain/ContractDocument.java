package leland.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import leland.domain.base.BaseEntity;
import leland.domain.services.ContractService;

@Entity
public final class ContractDocument
		extends BaseEntity
{
	private Client client;
	
	private String number;

	private Date beginDate; // data semnarii
	private Date stopDate; // valabilitate

	private Set<ContractService> contractedServices = new HashSet<ContractService>();
	
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="client_id", insertable=false, updatable=false, nullable=false)
	public Client getClient()
	{
		return this.client;
	}
	public void setClient(Client client)
	{
		this.client = client;
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

	@Basic(optional=false)
	@Temporal(TemporalType.DATE)
	public Date getBeginDate()
	{
		return this.beginDate;
	}
	public void setBeginDate(Date beginDate)
	{
		this.beginDate = beginDate;
	}

	@Temporal(TemporalType.DATE)
	public Date getStopDate()
	{
		return this.stopDate;
	}
	public void setStopDate(Date stopDate)
	{
		this.stopDate = stopDate;
	}
}
