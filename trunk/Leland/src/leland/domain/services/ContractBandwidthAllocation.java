package leland.domain.services;

import javax.persistence.Entity;

import leland.domain.base.BaseEntity;

@Entity
public class ContractBandwidthAllocation
		extends BaseEntity
{
	protected int contractedCir; //Committed Information Rate - min garantat
	protected int contractedMir; //Maximum Information Rate - max posibil

	
	
	public int getContractedCir()
	{
		return this.contractedCir;
	}
	public void setContractedCir(int cir)
	{
		this.contractedCir = cir;
	}
	public int getContractedMir()
	{
		return this.contractedMir;
	}
	public void setContractedMir(int mir)
	{
		this.contractedMir = mir;
	}
}
