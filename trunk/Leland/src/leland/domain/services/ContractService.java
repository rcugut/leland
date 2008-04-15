package leland.domain.services;

import javax.persistence.Entity;

import leland.domain.base.BaseEntity;
import leland.domain.services.prefilled.OfferService;

@Entity
public class ContractService
		extends BaseEntity
{
	protected OfferService offerService;
	protected double contractedPrice;
	
	protected ServiceDefinition details;
}
