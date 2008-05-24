package leland.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringUtil
{
	public static String formatDate(Date date, String pattern)
	{
		if(date == null)
			return "-";
		SimpleDateFormat df = new SimpleDateFormat(pattern, new Locale("ro", "RO"));
		return df.format(date);
	}
	
	public static String formatDateShortYMD(Date date)
	{
		return StringUtil.formatDate(date, "yyyy-MM-dd");
	}

	public static String formatDateShortYMDHM(Date date)
	{
		return StringUtil.formatDate(date, "yyyy-MM-dd HH:mm");
	}

	public static String formatDateLongDMY(Date date)
	{
		return StringUtil.formatDate(date, "d MMM yyyy");
	}

	public static String formatDateLongDMYHM(Date date)
	{
		return StringUtil.formatDate(date, "d MMM yyyy HH:mm");
	}
	
	
	/*******************************************************************************************/
	
	public static String formatDouble(double number)
	{
		DecimalFormat df = new DecimalFormat("########0.##");
		return df.format(number);
	}
	
	public static String formatMoney(double number)
	{
		DecimalFormat df = new DecimalFormat("########0.## RON");
		return df.format(number);
	}
	
	/*******************************************************************************************/
	
	
}
