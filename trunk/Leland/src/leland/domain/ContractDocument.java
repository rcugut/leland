package leland.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import leland.domain.base.BaseEntity;

@Entity
@Table(name="LND_CONTRACT_DOCUMENT")
public final class ContractDocument
		extends BaseEntity
{
	private String number;

	private Date signDate; // data semnarii
	private Date stopDate; // valabilitate

	private List<ContractChange> contractChanges = new ArrayList<ContractChange>();
	
	private String otherNotes;
	
	
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
	public Date getSignDate()
	{
		return this.signDate;
	}
	public void setSignDate(Date beginDate)
	{
		this.signDate = beginDate;
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

	@OneToMany(cascade = CascadeType.ALL)
	@org.hibernate.annotations.IndexColumn(name="index", base=0)
	public List<ContractChange> getContractChanges()
	{
		return this.contractChanges;
	}
	public void setContractChanges(List<ContractChange> contractChanges)
	{
		this.contractChanges = contractChanges;
	}

	public String getOtherNotes()
	{
		return this.otherNotes;
	}
	public void setOtherNotes(String otherNotes)
	{
		this.otherNotes = otherNotes;
	}
}
