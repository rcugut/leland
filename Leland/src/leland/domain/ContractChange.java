package leland.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import leland.domain.base.EntityWithName;

@Entity
@Table(name="LND_CONTRACT_CHANGE")
public class ContractChange
		extends EntityWithName
{
	private String changeDetails;

	public ContractChange()
	{
		super();
	}
	
	public ContractChange(String name, String changeDetails)
	{
		super(name);
		this.changeDetails = changeDetails;
	}



	@Column(length=50000)
	public String getChangeDetails()
	{
		return this.changeDetails;
	}
	public void setChangeDetails(String changeDetails)
	{
		this.changeDetails = changeDetails;
	}
}
