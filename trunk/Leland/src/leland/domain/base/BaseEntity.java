package leland.domain.base;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@MappedSuperclass
public class BaseEntity
		implements Serializable
{
	protected int id;
	protected Date dateCreated = Calendar.getInstance().getTime();
	protected Date dateUpdated;
	protected boolean active = true;

	
	public BaseEntity()
	{
		super();
	}

	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateCreated()
	{
		return this.dateCreated;
	}
	public void setDateCreated(Date dateCreated)
	{
		this.dateCreated = dateCreated;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateUpdated()
	{
		return this.dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated)
	{
		this.dateUpdated = dateUpdated;
	}

	@Basic
	public boolean isActive()
	{
		return this.active;
	}
	public void setActive(boolean active)
	{
		this.active = active;
	}
}
