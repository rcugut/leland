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
import javax.persistence.Transient;

import leland.domain.base.BaseEntity;
import leland.domain.enums.ClientType;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
public class Client
		extends BaseEntity
{
	private ClientType type;
	
	private String fullName;
	
	private String personalNumber; // CNP sau CUI
	private String regNumber; // nrBuletin sau nrRegComert (optional)
	
	private Address mainAddress;
	
	private String iban;
	private String bank;
	
	private Set<ContactPerson> contactPersons = new HashSet<ContactPerson>();
	

	
	

	
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
	public Address getMainAddress()
	{
		return this.mainAddress;
	}
	public Client setMainAddress(Address address)
	{
		this.mainAddress = address;
		return this;
	}

	public String getIban()
	{
		return this.iban;
	}
	public Client setIban(String iban)
	{
		this.iban = iban;
		return this;
	}

	public String getBank()
	{
		return this.bank;
	}
	public Client setBank(String bank)
	{
		this.bank = bank;
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
	


	private List<ContractDocument> documents = new ArrayList<ContractDocument>();
	
	
	
	public Client addAdditionalDocument(ContractDocument doc)
	{
		if(this.documents.size()==0)
			this.getDocuments().add(null); // add fake base document on position 0
		this.getDocuments().add(doc);
		doc.setClient(this);
		return this;
	}
	
	
	
	@Transient
	public ContractDocument getBaseDocument()
	{
		return this.documents.size()>0 ? this.documents.get(0) : null; 
	}
	public Client setBaseDocument(ContractDocument baseDocument)
	{
		baseDocument.setClient(this);
		if(this.documents.size()>0)
			this.documents.set(0, baseDocument);
		else
			this.documents.add(0, baseDocument);
		return this;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@org.hibernate.annotations.IndexColumn(name="index", base=0)
	@JoinColumn(name="contract_id", nullable=false)
	public List<ContractDocument> getDocuments()
	{
		return this.documents;
	}
	public Client setDocuments(List<ContractDocument> additionalDocuments)
	{
		this.documents = additionalDocuments;
		return this;
	}
}
