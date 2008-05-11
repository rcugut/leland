package leland.domain.services;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;




@Entity
public class InternetServiceDefinition
		extends PerLocationCustomServiceDefinition
{
	protected ContractBandwidthAllocation bandwidthAllocation;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
	public ContractBandwidthAllocation getBandwidthAllocation()
	{
		return this.bandwidthAllocation;
	}
	public void setBandwidthAllocation(ContractBandwidthAllocation bandwidthAllocation)
	{
		this.bandwidthAllocation = bandwidthAllocation;
	}
}
