package leland.domain.enums;

/**
 * 
 * @author Radu CUGUT
 */
public enum BillingMethod
{
	ONE_TIME(10),
//	DAILY(20),
//	WEEKLY(30),
	MONTHLY(40),
	YEARLY(50);


	
	private int value;

	BillingMethod(int value)
	{
		this.value = value;
	}

	// the identifierMethod
	public int toInt()
	{
		return value;
	}

	// the valueOfMethod
	public static BillingMethod fromInt(int value)
	{
		switch (value)
		{
			default:
				return ONE_TIME;
//			case 20:
//				return DAILY;
//			case 30:
//				return WEEKLY;
			case 40:
				return MONTHLY;
			case 50:
				return YEARLY;
		}
	}

	@Override
	public String toString()
	{
		switch (this)
		{
			default:
				return "Manual";
//			case DAILY:
//				return "Automat - zilnic";
//			case WEEKLY:
//				return "Automat - saptamanal";
			case MONTHLY:
				return "Automat - lunar";
			case YEARLY:
				return "Automat - anual";
		}
	}
}