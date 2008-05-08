package leland.domain.services;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class PerLocationCustomServiceDefinition
		extends ServiceDefinition
{
	protected InternetLocation location;

	@OneToOne(cascade=CascadeType.ALL)
	public InternetLocation getLocation()
	{
		return this.location;
	}
	public void setLocation(InternetLocation location)
	{
		this.location = location;
	}
}
