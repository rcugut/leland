package leland.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import leland.domain.base.BaseEntity;
import leland.domain.billing.ClientInvoice;
import leland.domain.billing.ClientPayment;
import leland.domain.enums.ClientType;
import leland.domain.networking.TechBandwidthAllocation;

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

	private Set<Location> locations = new HashSet<Location>();
	private Set<TechBandwidthAllocation> bandwidthAllocations = new HashSet<TechBandwidthAllocation>();
	private List<ContractDocument> contractDocuments = new ArrayList<ContractDocument>();
	private Set<ContractGenericService> contractedServices = new HashSet<ContractGenericService>();
	

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
	
	
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	public Set<ContractGenericService> getContractedServices()
	{
		return this.contractedServices;
	}
	public void setContractedServices(Set<ContractGenericService> contractedServices)
	{
		this.contractedServices = contractedServices;
	}
	
	
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
	public Set<TechBandwidthAllocation> getBandwidthAllocations()
	{
		return this.bandwidthAllocations;
	}
	public void setBandwidthAllocations(Set<TechBandwidthAllocation> bandwidthAllocations)
	{
		this.bandwidthAllocations = bandwidthAllocations;
	}
	
	

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
