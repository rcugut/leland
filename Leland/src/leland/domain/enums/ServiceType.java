package leland.domain.enums;

/**
 * 
 * @author Radu CUGUT
 */
public enum ServiceType
{
	GENERIC(10),
	CONNECTION(20),
	INTERNET(30);


	
	private int value;

	ServiceType(int value)
	{
		this.value = value;
	}

	// the identifierMethod
	public int toInt()
	{
		return value;
	}

	// the valueOfMethod
	public static ServiceType fromInt(int value)
	{
		switch (value)
		{
			default:
				return GENERIC;
			case 20:
				return CONNECTION;
			case 30:
				return INTERNET;
//			case 40:
//				return TELEPHONY;
//			case 50:
//				return TELEVISION;
		}
	}

	@Override
	public String toString()
	{
		switch (this)
		{
			default:
				return "Generic";
			case CONNECTION:
				return "Instalare locatie";
			case INTERNET:
				return "Internet";
//			case TELEPHONY:
//				return "Telefonie";
//			case TELEVISION:
//				return "Televiziune";
		}
	}
}