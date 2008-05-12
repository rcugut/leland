package leland.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import leland.domain.base.BaseEntity;
import leland.domain.networking.NetworkAddress;

@Entity
@Table(name="LND_LOCATION")
public class Location
		extends BaseEntity
{
	protected Address address;
	protected Set<NetworkAddress> networkAddresses = new HashSet<NetworkAddress>();
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
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
