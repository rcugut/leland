package leland.domain.services;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import leland.domain.Location;
import leland.domain.base.BaseEntity;
import leland.domain.networking.NetworkAddress;

@Entity
public class InternetLocation
		extends BaseEntity
{
	protected Location location;
	protected Set<NetworkAddress> networkAddresses = new HashSet<NetworkAddress>();
	
	
}
