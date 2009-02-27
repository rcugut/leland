package leland.web.wicket.billing;

import leland.domain.billing.ClientInvoice;
import leland.domain.billing.ClientPayment;
import leland.domain.enums.PaymentType;
import leland.util.StringUtil;
import leland.web.wicket.contract.ClientPage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

/**
 * @author Radu Cugut
 */
public class ClientNewPayment
		extends ClientPage
{
	private final ClientInvoice invoice;
	
	public ClientNewPayment(PageParameters parameters)
	{
		super(parameters);
		this.invoice = clientManager.getDao().get(ClientInvoice.class, parameters.getInt("invoiceId"));
		
		this.add(new Label<String>("label-invoiceNumber", String.valueOf(invoice.getId())));
		this.add(new Label<String>("label-issueDate", StringUtil.formatDateShortYMD(invoice.getIssueDate())));
		
		this.add(new Label<String>("label-amountWithoutTax", StringUtil.formatMoney(invoice.getValueWithoutTax())));
		this.add(new Label<String>("label-amountWithTax", StringUtil.formatMoney(invoice.getValueWithTax())));

		
		final ClientPayment	payment = new ClientPayment();
		payment.setInvoice(invoice);
		payment.setPaymentType(PaymentType.CASH);
		
		final Form<Object> form = new Form<Object>("form-newPayment")
		{
			@Override
			protected void onSubmit()
			{
				if(payment.getAmount() != invoice.getValueWithTax())
				{
					error("Valoare de plata invalida!");
					return;
				}
				
				invoice.setPaid(true);
				client.getPayments().add(payment);
				
				clientManager.getDao().persist(payment);
				clientManager.getDao().save(invoice);
			}
		};
		this.add(form);
		
		form.add(new RequiredTextField<Double>("input-paymentAmount", new PropertyModel<Double>(payment, "amount")));
		form.add(new FeedbackPanel("feedback"));
	}
}

