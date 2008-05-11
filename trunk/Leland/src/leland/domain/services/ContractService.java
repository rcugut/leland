package leland.domain.services;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import leland.domain.ContractDocument;
import leland.domain.base.BaseEntity;
import leland.domain.services.prefilled.OfferService;

@Entity
@Table(name="LND_CONTRACT_SERVICE")
public final class ContractService
		extends BaseEntity
{
	private ContractDocument contractDocument; // contractul in care a fost adaugat acest serviciu 
	
	private OfferService offerService;
	private double contractedPrice;

	private ServiceDefinition serviceDefinition;


	@OneToOne
	public ContractDocument getContractDocument()
	{
		return this.contractDocument;
	}
	public void setContractDocument(ContractDocument contractDocument)
	{
		this.contractDocument = contractDocument;
	}

	@ManyToOne
	public OfferService getOfferService()
	{
		return this.offerService;
	}
	public void setOfferService(OfferService offerService)
	{
		this.offerService = offerService;
	}

	public double getContractedPrice()
	{
		return this.contractedPrice;
	}
	public void setContractedPrice(double contractedPrice)
	{
		this.contractedPrice = contractedPrice;
	}

	@OneToOne
	public ServiceDefinition getServiceDefinition()
	{
		return this.serviceDefinition;
	}
	public void setServiceDefinition(ServiceDefinition serviceDefinition)
	{
		this.serviceDefinition = serviceDefinition;
	}
}
