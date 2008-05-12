package leland.domain.networking;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import leland.domain.base.BaseEntity;

@Entity
@Table(name="LND_NETWORK_ADDRESS")
public class NetworkAddress
		extends BaseEntity
{
	protected NetworkAddress parent;
	
	protected String cidrNotation; // i.e. x.x.x.x/y

	
	@ManyToOne(cascade=CascadeType.REFRESH)
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
