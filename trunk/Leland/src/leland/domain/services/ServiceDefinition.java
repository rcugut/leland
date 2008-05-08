package leland.domain.services;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import leland.domain.base.EntityWithName;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class ServiceDefinition
		extends EntityWithName
{
	protected String details;


	
	public String getDetails()
	{
		return this.details;
	}
	public void setDetails(String details)
	{
		this.details = details;
	}
}
