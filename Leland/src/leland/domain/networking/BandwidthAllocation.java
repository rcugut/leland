package leland.domain.networking;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import leland.domain.base.BaseEntity;
import leland.domain.networking.NetworkAddress;

@Entity
public class BandwidthAllocation
		extends BaseEntity
{
	protected int cir; //Committed Information Rate - min garantat
	protected int mir; //Maximum Information Rate - max posibil
	
	protected Set<NetworkAddress> networkAddress = new HashSet<NetworkAddress>();
	
	protected int filterPrio = 50;
	protected int classPrio = 50;
	
	
	
	public int getCir()
	{
		return this.cir;
	}
	public void setCir(int cir)
	{
		this.cir = cir;
	}
	public int getMir()
	{
		return this.mir;
	}
	public void setMir(int mir)
	{
		this.mir = mir;
	}
	
	@OneToMany(cascade={CascadeType.REFRESH})
	public Set<NetworkAddress> getNetworkAddress()
	{
		return this.networkAddress;
	}
	public void setNetworkAddress(Set<NetworkAddress> networkAddress)
	{
		this.networkAddress = networkAddress;
	}


	public int getFilterPrio()
	{
		return this.filterPrio;
	}
	public void setFilterPrio(int filterPrio)
	{
		this.filterPrio = filterPrio;
	}

	public int getClassPrio()
	{
		return this.classPrio;
	}
	public void setClassPrio(int classPrio)
	{
		this.classPrio = classPrio;
	}
}
