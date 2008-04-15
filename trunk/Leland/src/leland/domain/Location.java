package leland.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import leland.domain.base.BaseEntity;

@Entity
public final class Location
		extends BaseEntity
{
	private Address address;


	@OneToOne
	public Address getAddress()
	{
		return this.address;
	}
	public void setAddress(Address address)
	{
		this.address = address;
	}
}
