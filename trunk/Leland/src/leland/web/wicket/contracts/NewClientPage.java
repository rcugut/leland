package leland.web.wicket.contracts;

import java.util.Arrays;

import leland.domain.Client;
import leland.domain.ContractGenericService;
import leland.domain.ContractInternetService;
import leland.domain.ContractLocationConnectionService;
import leland.domain.enums.ClientType;
import leland.domain.enums.ServiceType;
import leland.web.wicket.AuthorizedBasePage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
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
	private final Client client = new Client();

	
	public NewClientPage(PageParameters parameters)
	{
		super(parameters);
		this.add(new NewClientForm("form-newClient"));
	}
	
	/**
	 * 
	 * @author Radu CUGUT
	 */
	class NewClientForm extends Form
	{
		private ServiceType serviceTypeToAdd = ServiceType.CONNECTION;
		
		public NewClientForm(String id)
		{
			super(id);
			

			
			final Label labelClientPinType = new Label("label-clientPersonalNumberType", new AbstractReadOnlyModel()
			{
				@Override
				public Object getObject()
				{
					Object ct = client.getType();
					return ((ct != null) && (ct == ClientType.COMPANY)) ? "CUI" : "CNP";
				}
			});
			this.add(labelClientPinType.setOutputMarkupId(true));

			

			
			
			final Label labelClientRegNoType = new Label("label-clientRegNumberType", new AbstractReadOnlyModel()
			{
				@Override
				public Object getObject()
				{
					Object ct = client.getType();
					return ((ct != null) && (ct == ClientType.COMPANY)) ? "Nr Reg.Comer." : "CI/BI/Pasaport";
				}
			});
			this.add(labelClientRegNoType.setOutputMarkupId(true));
			
			
			final DropDownChoice choiceClientType = new DropDownChoice("select-clientType", new PropertyModel(client, "type"), 
					Arrays.asList(ClientType.values()), new ChoiceRenderer("name"))
			{
			};
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
			
			
			final TextField textClientPin = new TextField("input-clientPersonalNumber", new PropertyModel(client, "personalNumber"));
			this.add(textClientPin);

			final TextField textClientRegNo = new TextField("input-clientRegNumber", new PropertyModel(client, "regNumber"));
			this.add(textClientRegNo);

			final TextField textClientName = new TextField("input-clientName", new PropertyModel(client, "fullName"));
			this.add(textClientName);
			
			
			final DropDownChoice choiceDesigners = new DropDownChoice("input-serviceType", new PropertyModel(this, "serviceTypeToAdd"), Arrays.asList(ServiceType.values()));
			this.add(choiceDesigners);
			
			this.add(new AjaxButton("input-addService")
			{
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form form)
				{
					ContractGenericService service;
					switch(serviceTypeToAdd)
					{
						case GENERIC:
							service = new ContractGenericService();
						case CONNECTION:
							service = new ContractLocationConnectionService();
						case INTERNET:
							service = new ContractInternetService();
					}//switch

					
//					Project project = (Project) modelForObject.getObject();
//					project.getDesigners().add(getSelectedDesigner());
//					target.addComponent(designersPanel);
				}
			});
			
		}

		@Override
		protected void onSubmit()
		{
			super.onSubmit();
		}
		
		
		public ServiceType getServiceTypeToAdd()
		{
			return serviceTypeToAdd;
		}
		public void setServiceTypeToAdd(ServiceType serviceTypeToAdd)
		{
			this.serviceTypeToAdd = serviceTypeToAdd;
		}
	}

	
	
	@Override
	protected String getInSitePositionString()
	{
		return "Leland > Clients > New";
	}
}

