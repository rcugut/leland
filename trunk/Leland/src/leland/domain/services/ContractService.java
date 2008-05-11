package leland.domain.services;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("0")
public final class ContractService
		extends OfferService
{
	private double contractedPrice;

	
	public double getContractedPrice()
	{
		return this.contractedPrice;
	}
	public void setContractedPrice(double contractedPrice)
	{
		this.contractedPrice = contractedPrice;
	}
}
