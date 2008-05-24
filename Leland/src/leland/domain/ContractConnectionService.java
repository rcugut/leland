package leland.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import leland.domain.enums.BillingMethod;
import leland.domain.enums.ServiceType;



@Entity
@Table(name="LND_CONTRACT_SERVICE_CONNECTION")
public class ContractConnectionService
		extends AbstractService
{
	protected Address address;

	
	public ContractConnectionService()
	{
		super();
		this.billingMethod = BillingMethod.ONE_TIME;
	}
	
	
	
	public Address getAddress()
	{
		return this.address;
	}
	public ContractConnectionService setAddress(Address address)
	{
		this.address = address;
		return this;
	}



	@Override
	@Transient
	public ServiceType getServiceType()
	{
		return ServiceType.CONNECTION;
	}
}
