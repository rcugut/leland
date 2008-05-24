package leland.domain.base;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class EntityWithName
		extends BaseEntity
{
	protected String name;


	public EntityWithName()
	{
		super();
	}
	public EntityWithName(String name)
	{
		super();
		this.name = name;
	}


	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
}
