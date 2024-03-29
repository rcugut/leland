package leland.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import leland.domain.enums.BillingMethod;
import leland.domain.enums.ServiceType;


@Entity
@Table(name="LND_CONTRACT_SERVICE_GENERIC")
public class ContractGenericService
		extends AbstractService
{
	public ContractGenericService()
	{
		super();
		this.billingMethod = BillingMethod.ONE_TIME;
	}

	@Override
	@Transient
	public ServiceType getServiceType()
	{
		return ServiceType.GENERIC;
	}
}
