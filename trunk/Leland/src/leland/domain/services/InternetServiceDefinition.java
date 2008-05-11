package leland.domain.services;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import leland.domain.networking.BandwidthAllocation;



@Entity
public class InternetServiceDefinition
		extends PerLocationCustomServiceDefinition
{
	protected BandwidthAllocation bandwidthAllocation;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
	public BandwidthAllocation getBandwidthAllocation()
	{
		return this.bandwidthAllocation;
	}
	public void setBandwidthAllocation(BandwidthAllocation bandwidthAllocation)
	{
		this.bandwidthAllocation = bandwidthAllocation;
	}
}
