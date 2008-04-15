package leland.domain.services;

import javax.persistence.Entity;

import leland.domain.networking.BandwidthAllocation;

@Entity
public class InternetServiceDefinition
		extends ServiceDefinition
{
	protected InternetLocation location;
	protected BandwidthAllocation bandwidthAllocation;
}
