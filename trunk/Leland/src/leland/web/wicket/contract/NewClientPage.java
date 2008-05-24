package leland.web.wicket.contract;

import java.util.Arrays;

import leland.domain.Client;
import leland.domain.ContractGenericService;
import leland.domain.enums.ClientType;
import leland.web.wicket.AuthorizedBasePage;
import leland.web.wicket.contract.services.ContractServiceListEditPanel;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.PropertyModel;

/**
 * @author Radu Cugut
 */
public class NewClientPage
		extends AuthorizedBasePage
{

	
	public NewClientPage(PageParameters parameters)
	{
		super(parameters);
		this.add(new NewClientForm("form-newClient"));
	}
	
	/**
	 * 
	 * @author Radu CUGUT
	 */
	class NewClientForm extends Form<Object>
	{
		private final Client client = new Client();
		
		public NewClientForm(String id)
		{
			super(id);
			
			client.setType(ClientType.PERSON);
			
			client.getContractedGenericServices().add(new ContractGenericService());

			
			final Label<String> labelClientPinType = new Label<String>("label-clientPersonalNumberType", new AbstractReadOnlyModel<String>()
			{
				@Override
				public String getObject()
				{
					Object ct = client.getType();
					return ((ct != null) && (ct == ClientType.COMPANY)) ? "CUI" : "CNP";
				}
			});
			this.add(labelClientPinType.setOutputMarkupId(true));

			
			final Label<String> labelClientRegNoType = new Label<String>("label-clientRegNumberType", new AbstractReadOnlyModel<String>()
			{
				@Override
				public String getObject()
				{
					Object ct = client.getType();
					return ((ct != null) && (ct == ClientType.COMPANY)) ? "Nr Reg.Comer." : "CI/BI/Pasaport";
				}
			});
			this.add(labelClientRegNoType.setOutputMarkupId(true));
			
			
			final DropDownChoice<ClientType> choiceClientType = new DropDownChoice<ClientType>("select-clientType",
					new PropertyModel<ClientType>(client, "type"), Arrays.asList(ClientType.values()), new ChoiceRenderer<ClientType>("name"));
			choiceClientType.add(new AjaxFormComponentUpdatingBehavior("onchange")
			{
				@Override
				protected void onUpdate(AjaxRequestTarget target)
				{
					target.addComponent(labelClientPinType);
					target.addComponent(labelClientRegNoType);
				}
			});
			this.add(choiceClientType);
			
			
			final TextField<String> textClientPin = new TextField<String>("input-clientPersonalNumber", new PropertyModel<String>(client, "personalNumber"));
			this.add(textClientPin); 

			final TextField<String> textClientRegNo = new TextField<String>("input-clientRegNumber", new PropertyModel<String>(client, "regNumber"));
			this.add(textClientRegNo);

			final TextField<String> textClientName = new TextField<String>("input-clientName", new PropertyModel<String>(client, "fullName"));
			this.add(textClientName);
			
			

			this.add(new ContractServiceListEditPanel("panel-servicesEditList", new PropertyModel<Client>(this, "client"), this));
		}

		@Override
		protected void onSubmit()
		{
			super.onSubmit();
		}
		
		
		public Client getClient()
		{
			return this.client;
		}
	}

	
	
	@Override
	protected String getInSitePositionString()
	{
		return "Leland > Clients > New";
	}
}

