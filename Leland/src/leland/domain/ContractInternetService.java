package leland.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="LND_CONTRACT_SERVICE_INTERNET")
@Inheritance(strategy=InheritanceType.JOINED)
public class ContractInternetService
		extends ContractGenericService
{
	protected Location location;
	protected ContractBandwidthAllocation bandwidthAllocation;


	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
	public Location getLocation()
	{
		return this.location;
	}
	public void setLocation(Location location)
	{
		this.location = location;
	}

	
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
