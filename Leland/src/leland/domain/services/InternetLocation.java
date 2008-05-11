package leland.domain.services;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import leland.domain.Location;
import leland.domain.base.BaseEntity;
import leland.domain.networking.NetworkAddress;

@Entity
public class InternetLocation
		extends BaseEntity
{
	protected Location location;
	protected Set<NetworkAddress> networkAddresses = new HashSet<NetworkAddress>();
	
	@OneToMany
	public Location getLocation()
	{
		return this.location;
	}
	public void setLocation(Location location)
	{
		this.location = location;
	}

	@ManyToMany
	public Set<NetworkAddress> getNetworkAddresses()
	{
		return this.networkAddresses;
	}
	public void setNetworkAddresses(Set<NetworkAddress> networkAddresses)
	{
		this.networkAddresses = networkAddresses;
	}
}
