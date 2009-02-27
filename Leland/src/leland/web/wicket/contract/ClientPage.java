package leland.web.wicket.contract;

import leland.dao.ClientManager;
import leland.domain.Client;
import leland.domain.enums.ClientType;
import leland.util.StringUtil;
import leland.web.wicket.AuthorizedBasePage;
import leland.web.wicket.billing.ClientBillingInfo;
import leland.web.wicket.billing.ClientNewPayment;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author Radu Cugut
 */
public class ClientPage
		extends AuthorizedBasePage
{
	@SpringBean(name = "clientManager")
	protected ClientManager clientManager;
	
	protected final Client client; 
	
	public ClientPage(PageParameters parameters)
	{
		super(parameters);
		int id = parameters.getInt("id");
		this.client = clientManager.getDao().get(Client.class, id);
		
		
		
		this.add(new Label<String>("label-clientType", client.getType().getName()));
		this.add(new Label<String>("label-clientName", client.getFullName()));
		this.add(new Label<String>("label-clientPersonalNumberType", (client.getType() == ClientType.COMPANY) ? "CUI" : "CNP"));
		this.add(new Label<String>("label-clientPersonalNumber", client.getPersonalNumber()));
		this.add(new Label<String>("label-clientRegNumberType", (client.getType() == ClientType.COMPANY) ? "Nr Reg.Comer." : "CI/Pasaport"));
		this.add(new Label<String>("label-clientRegNumber", client.getRegNumber()));
		
		this.add(new Label<String>("label-contractNumber", client.getBaseDocument().getNumber()));
		this.add(new Label<String>("label-contractSignDate", StringUtil.formatDateLongDMY(client.getBaseDocument().getSignDate())));
		this.add(new Label<String>("label-contractEndDate", StringUtil.formatDateLongDMY(client.getLastContractDocument().getStopDate())));
		this.add(new Label<String>("label-contractDocsNumber", String.valueOf(client.getContractDocuments().size()-1)));
		this.add(new BookmarkablePageLink("link-addContractDoc", ClientAddContractDoc.class, parameters));
		

		this.add(new Label<String>("label-servicesNumber", String.valueOf(client.getAllServicesAsList().size())));
		this.add(new Label<String>("label-locationsNumber", String.valueOf(client.getContractedConnectionServices().size())));
		
		this.add(new Label<String>("label-invoicesNumber", String.valueOf(client.getInvoices().size())));
		this.add(new Label<String>("label-paymentsNumber", String.valueOf(client.getPayments().size())));
		this.add(new BookmarkablePageLink("link-showInvoicesAndPayments", ClientBillingInfo.class, parameters));

//		this.add(new BookmarkablePageLink("link-addInvoice", ClientNewInvoice.class, parameters));
		this.add(new BookmarkablePageLink("link-addPayment", ClientNewPayment.class, parameters));
		
		
	}
}
