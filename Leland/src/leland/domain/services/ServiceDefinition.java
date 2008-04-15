package leland.domain.services;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import leland.domain.base.BaseEntity;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class ServiceDefinition
		extends BaseEntity
{

}
