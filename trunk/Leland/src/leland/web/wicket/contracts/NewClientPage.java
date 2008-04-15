package leland.web.wicket.contracts;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import leland.domain.enums.ClientType;
import leland.web.wicket.AuthorizedBasePage;

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
	private final Map<String, Object> valueMap = new HashMap<String, Object>();
	
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
		public NewClientForm(String id)
		{
			super(id);

			valueMap.put("clientType", ClientType.PERSON);
			
			final Label labelClientPinType = new Label("label-clientPersonalNumberType", new AbstractReadOnlyModel()
			{
				@Override
				public Object getObject()
				{
					Object ct = valueMap.get("clientType");
					return ((ct != null) && (ct == ClientType.COMPANY)) ? "CUI" : "CNP";
				}
			});
			this.add(labelClientPinType.setOutputMarkupId(true));

			

			
			
			final Label labelClientRegNoType = new Label("label-clientRegNumberType", new AbstractReadOnlyModel()
			{
				@Override
				public Object getObject()
				{
					Object ct = valueMap.get("clientType");
					return ((ct != null) && (ct == ClientType.COMPANY)) ? "Nr Reg.Comer." : "CI/BI/Pasaport";
				}
			});
			this.add(labelClientRegNoType.setOutputMarkupId(true));
			
			
			final DropDownChoice choiceClientType = new DropDownChoice("select-clientType", new PropertyModel(valueMap, "clientType"), 
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
			
			
			final TextField textClientPin = new TextField("input-clientPersonalNumber", new PropertyModel(valueMap, "clientPin"));
			this.add(textClientPin);

			final TextField textClientRegNo = new TextField("input-clientRegNumber", new PropertyModel(valueMap, "clientRegNo"));
			this.add(textClientRegNo);

			final TextField textClientName = new TextField("input-clientName", new PropertyModel(valueMap, "clientName"));
			this.add(textClientName);
		}

		@Override
		protected void onSubmit()
		{
			super.onSubmit();
		}
	}

	
	
	@Override
	protected String getInSitePositionString()
	{
		return "Leland > Clients > New";
	}
}

