package leland.domain.services;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import leland.domain.base.EntityWithName;

@Entity
@Table(name="LND_SERVICE_DEFINITION")
@Inheritance(strategy=InheritanceType.JOINED)
public class ServiceDefinition
		extends EntityWithName
{
	protected String details;

	public ServiceDefinition()
	{
		super();
	}
	
	public ServiceDefinition(String name)
	{
		super(name);
	}




	public String getDetails()
	{
		return this.details;
	}
	public void setDetails(String details)
	{
		this.details = details;
	}
}
