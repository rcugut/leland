package leland.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import leland.domain.base.BaseEntity;
import leland.domain.billing.ClientInvoice;
import leland.domain.billing.ClientPayment;
import leland.domain.enums.ClientType;
import leland.domain.networking.BandwidthAllocation;
import leland.domain.networking.Location;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name="LND_CLIENT")
public class Client
		extends BaseEntity
{
	private ClientType type;
	
	private String fullName;
	
	private String personalNumber; // CNP sau CUI
	private String regNumber; // nrBuletin sau nrRegComert (optional)
	
	private Address billingAddress; // adresa facturare
	
	private Set<ContactPerson> contactPersons = new HashSet<ContactPerson>();

	// tech
	private Set<Location> locations = new HashSet<Location>();
	private Set<BandwidthAllocation> bandwidthAllocations = new HashSet<BandwidthAllocation>();
	
	// contract
	private List<ContractDocument> contractDocuments = new ArrayList<ContractDocument>();
	
	private Set<ContractGenericService> contractedGenericServices = new HashSet<ContractGenericService>();
	private Set<ContractConnectionService> contractedConnectionServices = new HashSet<ContractConnectionService>();
	private ContractInternetService contractedInternetService;
	
	// billing
	private Set<ClientInvoice> invoices = new HashSet<ClientInvoice>(); 
	private Set<ClientPayment> payments = new HashSet<ClientPayment>();

	
	public Client()
	{
		super();
	}
	
	
	public Client(ClientType clientType)
	{
		super();
		this.type = clientType;
	}


	////////////////////////////////////////////////////////////////////////////////////////////

	public void addService(AbstractService service)
	{
		if(service instanceof ContractGenericService)
		{
			this.getContractedGenericServices().add((ContractGenericService)service);
		}
		else if(service instanceof ContractConnectionService)
		{
			this.getContractedConnectionServices().add((ContractConnectionService)service);
		}
		else// if(service instanceof ContractInternetService)
		{
			this.contractedInternetService = (ContractInternetService)service;
		}
	}

	public void removeService(AbstractService service)
	{
		if(service instanceof ContractGenericService)
		{
			this.getContractedGenericServices().remove((ContractGenericService)service);
		}
		else if(service instanceof ContractConnectionService)
		{
			this.getContractedConnectionServices().remove((ContractConnectionService)service);
		}
		else// if(service instanceof ContractInternetService)
		{
			this.contractedInternetService = null;
		}
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@Column(columnDefinition="smallint")
	@Type(type="leland.domain.hibernate.GenericEnumUserType",
			parameters={
			@Parameter(name="enumClass", value="leland.domain.enums.ClientType"),
			@Parameter(name="identifierMethod", value="toInt"),
			@Parameter(name="valueOfMethod", value="fromInt")			
	})
	public ClientType getType()
	{
		return this.type;
	}
	public Client setType(ClientType type)
	{
		this.type = type;
		return this;
	}
	
	@Basic(optional=false)
	public String getFullName()
	{
		return this.fullName;
	}
	public Client setFullName(String fullName)
	{
		this.fullName = fullName;
		return this;
	}
	
	@Basic(optional=false)
	@Column(unique=true)
	public String getPersonalNumber()
	{
		return this.personalNumber;
	}
	public Client setPersonalNumber(String personalNumber)
	{
		this.personalNumber = personalNumber;
		return this;
	}

	public String getRegNumber()
	{
		return this.regNumber;
	}
	public Client setRegNumber(String idNumber)
	{
		this.regNumber = idNumber;
		return this;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	public Address getBillingAddress()
	{
		return this.billingAddress;
	}
	public Client setBillingAddress(Address address)
	{
		this.billingAddress = address;
		return this;
	}

	
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	public Set<ContactPerson> getContactPersons()
	{
		return this.contactPersons;
	}
	public Client setContactPersons(Set<ContactPerson> contactPersons)
	{
		this.contactPersons = contactPersons;
		return this;
	}	
	


	public Client addAdditionalDocument(ContractDocument doc)
	{
		if(this.contractDocuments.size()==0)
			this.getContractDocuments().add(null); // add fake base document on position 0
		this.getContractDocuments().add(doc);
		return this;
	}
	
	@Transient
	public ContractDocument getBaseDocument()
	{
		return this.contractDocuments.size()>0 ? this.contractDocuments.get(0) : null; 
	}
	public Client setBaseDocument(ContractDocument baseDocument)
	{
		if(this.contractDocuments.size()>0)
			this.contractDocuments.set(0, baseDocument);
		else
			this.contractDocuments.add(0, baseDocument);
		return this;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@org.hibernate.annotations.IndexColumn(name="index", base=0)
	@JoinColumn(name="contract_id", nullable=false)
	public List<ContractDocument> getContractDocuments()
	{
		return this.contractDocuments;
	}
	public Client setContractDocuments(List<ContractDocument> additionalDocuments)
	{
		this.contractDocuments = additionalDocuments;
		return this;
	}
	

	@Transient
	public ContractDocument getLastContractDocument()
	{
		return this.contractDocuments.size()>0 ? this.contractDocuments.get(this.contractDocuments.size()-1) : null; 
	}
	
	
	
	// CONTRACT

	@Transient
	public List<AbstractService> getAllServicesAsList()
	{
		final List<AbstractService> list = new ArrayList<AbstractService>(this.contractedGenericServices);
		list.addAll(this.contractedConnectionServices);
		if(this.contractedInternetService != null)
			list.add(this.contractedInternetService);
		return list;
	}

	@Transient
	public List<AbstractService> getAllServicesAsSortedList()
	{
		final List<AbstractService> list = this.getAllServicesAsList();
		Collections.sort(list);
		return list;
	}

	

	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	public Set<ContractGenericService> getContractedGenericServices()
	{
		return this.contractedGenericServices;
	}
	public Client setContractedGenericServices(Set<ContractGenericService> contractedGenericServices)
	{
		this.contractedGenericServices = contractedGenericServices;
		return this;
	}
	
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	public Set<ContractConnectionService> getContractedConnectionServices()
	{
		return this.contractedConnectionServices;
	}
	public Client setContractedConnectionServices(Set<ContractConnectionService> contractedConnectionServices)
	{
		this.contractedConnectionServices = contractedConnectionServices;
		return this;
	}
	
	

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, optional=true)
	public ContractInternetService getContractedInternetService()
	{
		return this.contractedInternetService;
	}
	public Client setContractedInternetService(ContractInternetService contractedInternetService)
	{
		this.contractedInternetService = contractedInternetService;
		return this;
	}

	
	
	
	
	
	// TECH
	
	
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	public Set<Location> getLocations()
	{
		return this.locations;
	}
	public void setLocations(Set<Location> location)
	{
		this.locations = location;
	}

	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	public Set<BandwidthAllocation> getBandwidthAllocations()
	{
		return this.bandwidthAllocations;
	}
	public void setBandwidthAllocations(Set<BandwidthAllocation> bandwidthAllocations)
	{
		this.bandwidthAllocations = bandwidthAllocations;
	}
	
	
	
	
	
	// BILLING

	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	public Set<ClientInvoice> getInvoices()
	{
		return this.invoices;
	}
	public void setInvoices(Set<ClientInvoice> invoices)
	{
		this.invoices = invoices;
	}
	
	
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	public Set<ClientPayment> getPayments()
	{
		return this.payments;
	}
	public void setPayments(Set<ClientPayment> payments)
	{
		this.payments = payments;
	}
}
