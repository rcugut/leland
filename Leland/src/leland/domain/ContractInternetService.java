package leland.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import leland.domain.enums.BillingMethod;
import leland.domain.enums.ServiceType;

@Entity
@Table(name="LND_CONTRACT_SERVICE_INTERNET")
public class ContractInternetService
		extends AbstractService
{
	private int contractedTotalUploadCir; //Committed Information Rate - min garantat
	private int contractedTotalUploadMir; //Maximum Information Rate - max posibil

	private int contractedTotalDownloadCir; //Committed Information Rate - min garantat
	private int contractedTotalDownloadMir; //Maximum Information Rate - max posibil

	
	private int nbOfIpAddresses = 1;
	
	
	
	public ContractInternetService()
	{
		super();
		this.billingMethod = BillingMethod.MONTHLY;
	}
	
	public ContractInternetService(int cir, int mir, int nbOfNetworkAddresses)
	{
		this();
		this.contractedTotalUploadCir = cir;
		this.contractedTotalUploadMir = mir;
		this.nbOfIpAddresses = nbOfNetworkAddresses;
	}





	public int getContractedTotalUploadCir()
	{
		return this.contractedTotalUploadCir;
	}
	public ContractInternetService setContractedTotalUploadCir(int cir)
	{
		this.contractedTotalUploadCir = cir;
		return this;
	}
	
	public int getContractedTotalUploadMir()
	{
		return this.contractedTotalUploadMir;
	}
	public ContractInternetService setContractedTotalUploadMir(int mir)
	{
		this.contractedTotalUploadMir = mir;
		return this;
	}
	
	public int getContractedTotalDownloadMir()
	{
		return this.contractedTotalDownloadMir;
	}
	public void setContractedTotalDownloadMir(int contractedTotalDownloadMir)
	{
		this.contractedTotalDownloadMir = contractedTotalDownloadMir;
	}
	
	public int getContractedTotalDownloadCir()
	{
		return this.contractedTotalDownloadCir;
	}
	public void setContractedTotalDownloadCir(int contractedTotalDownloadCir)
	{
		this.contractedTotalDownloadCir = contractedTotalDownloadCir;
	}

	
	public int getNbOfIpAddresses()
	{
		return this.nbOfIpAddresses;
	}
	public ContractInternetService setNbOfIpAddresses(int nbOfNetworkAddresses)
	{
		this.nbOfIpAddresses = nbOfNetworkAddresses;
		return this;
	}

	@Override
	@Transient
	public ServiceType getServiceType()
	{
		return ServiceType.INTERNET;
	}
}