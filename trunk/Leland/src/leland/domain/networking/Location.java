package leland.domain.networking;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import leland.domain.Address;
import leland.domain.base.EntityWithName;

@Entity
@Table(name="LND_LOCATION")
public final class Location
		extends EntityWithName
{
	private Address address;
	private Set<NetworkAddress> networkAddresses = new HashSet<NetworkAddress>();
	
	
	
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	public Address getAddress()
	{
		return this.address;
	}
	public void setAddress(Address location)
	{
		this.address = location;
	}

	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	public Set<NetworkAddress> getNetworkAddresses()
	{
		return this.networkAddresses;
	}
	public void setNetworkAddresses(Set<NetworkAddress> networkAddresses)
	{
		this.networkAddresses = networkAddresses;
	}
}
