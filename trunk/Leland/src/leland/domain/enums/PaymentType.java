package leland.domain.enums;

/**
 *  
 * @author Radu CUGUT
 */
public enum PaymentType
{
	CASH(1),
	BANK(2);


	
	private int value;

	PaymentType(int value)
	{
		this.value = value;
	}

	// the identifierMethod
	public int toInt()
	{
		return value;
	}

	// the valueOfMethod
	public static PaymentType fromInt(int value)
	{
		switch (value)
		{
			default:
				return CASH;
			case 2:
				return BANK;
		}
	}

	@Override
	public String toString()
	{
		switch (this)
		{
			default:
				return "Cash";
			case BANK:
				return "Bank";
		}
	}
	 
	public String getName()
	{
		switch (this)
		{
			default:
				return "Cahs";
			case BANK:
				return "Bank";
		}
	}
}