package leland.web.wicket.billing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import leland.domain.billing.ClientInvoice;
import leland.domain.billing.ClientPayment;
import leland.util.StringUtil;
import leland.web.wicket.contract.ClientPage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.DefaultDataProvider;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author Radu Cugut
 */
public class ClientBillingInfo
		extends ClientPage
{
	@SuppressWarnings("unchecked")
	public ClientBillingInfo(final PageParameters parameters)
	{
		super(parameters);
		
		final List<ClientInvoice> invoicesList = new ArrayList<ClientInvoice>(client.getInvoices());
		
		final WebMarkupContainer<Object> containerInvoicesList = new WebMarkupContainer<Object>("container-invoicesList");
		this.add(containerInvoicesList.setVisible(invoicesList.size()>0));
		
		final IDataProvider<ClientInvoice> invoicesListDataProvider = new DefaultDataProvider()
		{
			@Override
			public Iterator<ClientInvoice> iterator(int first, int count)
			{
				return invoicesList.subList(first, first + count).iterator();
			}

			@Override
			public IModel model(Object object)
			{
				return new Model((Serializable) ((ClientInvoice)object));
			}

			@Override
			public int size()
			{
				return invoicesList.size();
			}
		};
		
		final DataView invoicesListDataView = new DataView<ClientInvoice>("row-invoice", invoicesListDataProvider, 10)
		{
			@Override
			protected void populateItem(Item<ClientInvoice> item)
			{
				final ClientInvoice invoice = item.getModelObject();
				
				item.add(new Label<String>("label-invoiceNumber", String.valueOf(invoice.getId())));
				item.add(new Label<String>("label-issueDate", StringUtil.formatDateShortYMD(invoice.getIssueDate())));
				
				item.add(new Label<String>("label-amountWithoutTax", StringUtil.formatMoney(invoice.getValueWithoutTax())));
				item.add(new Label<String>("label-amountWithTax", StringUtil.formatMoney(invoice.getValueWithTax())));
				PageParameters p = new PageParameters(parameters);
				p.put("invoiceId", invoice.getId());
				item.add(new BookmarkablePageLink("link-addPayment", ClientNewPayment.class, p));
			}
		};
		
		containerInvoicesList.add(invoicesListDataView);

		containerInvoicesList.add(new AjaxPagingNavigator("navigator-invoicesList", invoicesListDataView)
		{
			@Override
			protected void onAjaxEvent(AjaxRequestTarget target)
			{
				target.addComponent(containerInvoicesList);
			}				
		});
		
		
		
		
		
		final List<ClientPayment> paymentsList = new ArrayList<ClientPayment>(client.getPayments());
		
		final WebMarkupContainer<Object> containerPaymentsList = new WebMarkupContainer<Object>("container-paymentsList");
		this.add(containerPaymentsList.setVisible(paymentsList.size()>0));
		
		final IDataProvider<ClientPayment> paymentsListDataProvider = new DefaultDataProvider()
		{
			@Override
			public Iterator<ClientPayment> iterator(int first, int count)
			{
				return paymentsList.subList(first, first + count).iterator();
			}

			@Override
			public IModel model(Object object)
			{
				return new Model((Serializable) ((ClientPayment)object));
			}

			@Override
			public int size()
			{
				return paymentsList.size();
			}
		};
		
		final DataView paymentsListDataView = new DataView<ClientPayment>("row-payment", paymentsListDataProvider, 10)
		{
			@Override
			protected void populateItem(Item<ClientPayment> item)
			{
				final ClientPayment payment = item.getModelObject();
				
				item.add(new Label<String>("label-paymentType", payment.getPaymentType().getName()));
				item.add(new Label<String>("label-paymentDate", StringUtil.formatDateShortYMD(payment.getDateCreated())));
				item.add(new Label<String>("label-invoice", payment.getInvoice().getCodeAndNumber()));
				item.add(new Label<String>("label-amount", StringUtil.formatMoney(payment.getAmount())));
			}
		};
		
		containerPaymentsList.add(paymentsListDataView);
		
		containerPaymentsList.add(new AjaxPagingNavigator("navigator-paymentsList", paymentsListDataView)
		{
			@Override
			protected void onAjaxEvent(AjaxRequestTarget target)
			{
				target.addComponent(containerPaymentsList);
			}				
		});		
	}
}

