package leland.core;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import leland.dao.ClientManager;
import leland.domain.Client;
import leland.domain.billing.Invoice;


public class BillingEngine
{
	private final ClientManager clientManager;
	
	public BillingEngine(ClientManager clientManager)
	{
		super();
		this.clientManager = clientManager;
	}
	
	
	public final synchronized void generateMonthlyInvoices()
	{
		List<Client> allClients = clientManager.getDao().getAllActive(Client.class);
		for(Client client: allClients)
		{
			Invoice invoice = new Invoice();
			invoice.setCode("");
			invoice.setNumber("");
			invoice.setIssueDate(GregorianCalendar.getInstance().getTime());
			//invoice.setDueDate()
			
			
		}
	}
}
