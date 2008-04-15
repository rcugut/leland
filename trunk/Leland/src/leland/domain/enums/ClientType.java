package leland.domain.enums;

/**
 *  
 * @author Radu CUGUT
 */
public enum ClientType
{
	PERSON(1),
	COMPANY(2);


	
	private int value;

	ClientType(int value)
	{
		this.value = value;
	}

	// the identifierMethod
	public int toInt()
	{
		return value;
	}

	// the valueOfMethod
	public static ClientType fromInt(int value)
	{
		switch (value)
		{
			default:
				return PERSON;
			case 2:
				return COMPANY;
		}
	}

	@Override
	public String toString()
	{
		switch (this)
		{
			default:
				return "PF";
			case COMPANY:
				return "PJ";
		}
	}
	
	public String getName()
	{
		switch (this)
		{
			default:
				return "Persoana fizica";
			case COMPANY:
				return "Persoana juridica";
		}
	}
}