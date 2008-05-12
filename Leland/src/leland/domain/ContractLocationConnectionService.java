package leland.domain;

import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name="LND_CONTRACT_SERVICE_CONNECTION")
public class ContractLocationConnectionService
		extends ContractGenericService
{
	private Location location;

	
	
	
	
	public Location getLocation()
	{
		return this.location;
	}
	public void setLocation(Location location)
	{
		this.location = location;
	}
}
