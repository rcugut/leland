package leland.web.wicket.contract;

import java.util.List;

import leland.dao.ClientManager;
import leland.domain.AbstractService;
import leland.domain.Client;
import leland.web.wicket.contract.services.ContractServiceListEditPanel;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author Radu Cugut
 */
public class ClientServicesPage
		extends ClientPage
{
	@SpringBean(name = "clientManager")
	private ClientManager clientManager;

	
	public ClientServicesPage(PageParameters parameters)
	{
		super(parameters);
		
		final Form<Object> formServicesList = new Form<Object>("form-servicesList")
		{
			@Override
			protected void onSubmit()
			{
				final List<AbstractService> l = client.getAllServicesAsSortedList();
				for(AbstractService service : l)
				{
					clientManager.getDao().save(service);
				}
				clientManager.getDao().save(client);
			}
		};
		this.add(formServicesList);
		formServicesList.add(new ContractServiceListEditPanel("panel-servicesEditList", new PropertyModel<Client>(this, "client"), formServicesList));
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		formServicesList.add(feedbackPanel);
	}
}

