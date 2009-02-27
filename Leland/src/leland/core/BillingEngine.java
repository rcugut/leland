package leland.core;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import leland.dao.ClientManager;
import leland.domain.AbstractService;
import leland.domain.Client;
import leland.domain.billing.ClientInvoice;
import leland.domain.billing.ClientInvoiceRow;
import leland.domain.enums.BillingMethod;
import leland.util.Constants;


public class BillingEngine
{
	private final ClientManager clientManager;
	
	public BillingEngine(ClientManager clientManager)
	{
		super();
		this.clientManager = clientManager;
	}
	
	
	public final synchronized void generateMonthlyInvoicesForCurrentMonth()
	{
		final Date date = Calendar.getInstance().getTime();
		final List<Client> allClients = clientManager.getDao().getAllActive(Client.class);
		for(Client client: allClients)
		{
			final ClientInvoice invoice = this.generateInvoiceForMonth(client, date);
			client.getInvoices().add(invoice);
			clientManager.getDao().persist(invoice);
		}
	}
	
	
	public ClientInvoice generateInvoiceForMonth(final Client client, final Date date)
	{
		clientManager.getDao().reassociate(client);

		Calendar c = GregorianCalendar.getInstance();
		c.setTime(date);
		
		// check all invoices and if one exists for the given month in the date, abort everything
		for(ClientInvoice invoice : client.getInvoices())
			if(invoice.getIssueDate().getMonth() == c.get(Calendar.MONTH)
					&& invoice.getIssueDate().getYear() == c.get(Calendar.YEAR))
				return null;
		
		final Set<AbstractService> servicesToBill = new HashSet<AbstractService>();
		
		for(AbstractService service : client.getAllServicesAsList())
		{
			if(service.getBillingMethod() == BillingMethod.MONTHLY)
			{
				servicesToBill.add(service);
			}
//			else if(service.getBillingMethod() == BillingMethod.ONE_TIME && service.getInvoices().size())
//			{
//				servicesToBill.add(service);
//			}
		}
		
		
		final ClientInvoice invoice = new ClientInvoice();

		//TODO: set serie & number
		invoice.setCode("");
		invoice.setNumber("");
		
		for(AbstractService service : servicesToBill)
		{
			final ClientInvoiceRow invoiceRow = new ClientInvoiceRow();
			invoiceRow.setPrice(service.getContractedPrice());

			invoiceRow.setName("Servicii '" + service.getServiceTypeName() + "' pentru luna " + 
					c.getDisplayName(Calendar.MONTH, Calendar.SHORT, Constants.defaultLocale) +
					" anul " + c.getDisplayName(Calendar.YEAR, Calendar.SHORT, Constants.defaultLocale));
//			invoiceRow.setService(service);
		}
		
		return invoice;
	}

}
