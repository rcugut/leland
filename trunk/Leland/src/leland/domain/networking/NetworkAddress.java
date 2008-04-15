package leland.domain.networking;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import leland.domain.base.BaseEntity;

@Entity
public class NetworkAddress
		extends BaseEntity
{
	protected NetworkAddress parent;
	
	protected String cidrNotation; // i.e. x.x.x.x/y

	
	@OneToOne
	public NetworkAddress getParent()
	{
		return this.parent;
	}
	public void setParent(NetworkAddress parent)
	{
		this.parent = parent;
	}

	public String getCidrNotation()
	{
		return this.cidrNotation;
	}
	public void setCidrNotation(String cidrNotation)
	{
		this.cidrNotation = cidrNotation;
	}
}
