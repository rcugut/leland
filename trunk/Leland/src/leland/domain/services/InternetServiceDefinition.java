package leland.domain.services;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import leland.domain.networking.BandwidthAllocation;



@Entity
public class InternetServiceDefinition
		extends PerLocationCustomServiceDefinition
{
	protected BandwidthAllocation bandwidthAllocation;

	@OneToOne(cascade=CascadeType.ALL, optional=false)
	public BandwidthAllocation getBandwidthAllocation()
	{
		return this.bandwidthAllocation;
	}
	public void setBandwidthAllocation(BandwidthAllocation bandwidthAllocation)
	{
		this.bandwidthAllocation = bandwidthAllocation;
	}
}
