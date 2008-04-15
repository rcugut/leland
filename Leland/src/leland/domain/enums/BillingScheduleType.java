package leland.domain.enums;

/**
 * 
 * @author Radu CUGUT
 */
public enum BillingScheduleType
{
	ONE_TIME(10),
	DAILY(20),
	WEEKLY(30),
	MONTHLY(40),
	YEARLY(50);


	
	private int value;

	BillingScheduleType(int value)
	{
		this.value = value;
	}

	// the identifierMethod
	public int toInt()
	{
		return value;
	}

	// the valueOfMethod
	public static BillingScheduleType fromInt(int value)
	{
		switch (value)
		{
			default:
				return ONE_TIME;
			case 20:
				return DAILY;
			case 30:
				return WEEKLY;
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
				return "O singura data";
			case DAILY:
				return "Zilnic";
			case WEEKLY:
				return "Saptamanal";
			case MONTHLY:
				return "Lunar";
			case YEARLY:
				return "Anual";
		}
	}
}