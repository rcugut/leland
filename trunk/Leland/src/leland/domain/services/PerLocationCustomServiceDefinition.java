package leland.domain.services;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PerLocationCustomServiceDefinition
		extends ServiceDefinition
{
	protected InternetLocation location;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
	public InternetLocation getLocation()
	{
		return this.location;
	}
	public void setLocation(InternetLocation location)
	{
		this.location = location;
	}
}
