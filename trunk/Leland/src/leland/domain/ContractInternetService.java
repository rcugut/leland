package leland.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="LND_CONTRACT_SERVICE_INTERNET")
@Inheritance(strategy=InheritanceType.JOINED)
public class ContractInternetService
		extends ContractGenericService
{
	private int cir; //Committed Information Rate - min garantat
	private int mir; //Maximum Information Rate - max posibil

	private int nbOfNetworkAddresses = 1;
	
	
	
	public ContractInternetService()
	{
		super();
	}
	
	public ContractInternetService(int cir, int mir, int nbOfNetworkAddresses)
	{
		super();
		this.cir = cir;
		this.mir = mir;
		this.nbOfNetworkAddresses = nbOfNetworkAddresses;
	}





	public int getCir()
	{
		return this.cir;
	}
	public void setCir(int cir)
	{
		this.cir = cir;
	}
	public int getMir()
	{
		return this.mir;
	}
	public void setMir(int mir)
	{
		this.mir = mir;
	}
	
	public int getNbOfNetworkAddresses()
	{
		return this.nbOfNetworkAddresses;
	}
	public void setNbOfNetworkAddresses(int nbOfNetworkAddresses)
	{
		this.nbOfNetworkAddresses = nbOfNetworkAddresses;
	}
}