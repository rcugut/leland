package leland.web.wicket.contract;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import leland.dao.ClientManager;
import leland.domain.Client;
import leland.domain.ContractDocument;
import leland.domain.ContractGenericService;
import leland.domain.enums.ClientType;
import leland.web.wicket.AuthorizedBasePage;
import leland.web.wicket.contract.services.ContractServiceListEditPanel;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author Radu Cugut
 */
public class NewClientPage
		extends AuthorizedBasePage
{
	@SpringBean(name = "clientManager")
	private ClientManager clientManager;

	
	private final Client client = new Client();
	
	
	public NewClientPage(PageParameters parameters)
	{
		super(parameters);
		
		final Form<Object> form = new Form<Object>("form-newClient")
		{
			@Override
			protected void onSubmit()
			{
				clientManager.getDao().persist(client);
				clientManager.getDao().flush();
				setResponsePage(ClientPage.class, new PageParameters("id=" + client.getId()));
			}
		};
		this.add(form);
		
		
		
		client.setType(ClientType.PERSON);
		
		client.setBaseDocument(new ContractDocument());
		
		client.getContractedGenericServices().add(new ContractGenericService());


		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		form.add(feedbackPanel.setOutputMarkupId(true));
		
		
		final Label<String> labelClientPinType = new Label<String>("label-clientPersonalNumberType", new AbstractReadOnlyModel<String>()
		{
			@Override
			public String getObject()
			{
				Object ct = client.getType();
				return ((ct != null) && (ct == ClientType.COMPANY)) ? "CUI" : "CNP";
			}
		});
		form.add(labelClientPinType.setOutputMarkupId(true));

		
		final Label<String> labelClientRegNoType = new Label<String>("label-clientRegNumberType", new AbstractReadOnlyModel<String>()
		{
			@Override
			public String getObject()
			{
				Object ct = client.getType();
				return ((ct != null) && (ct == ClientType.COMPANY)) ? "Nr Reg.Comer." : "CI/BI/Pasaport";
			}
		});
		form.add(labelClientRegNoType.setOutputMarkupId(true));
		
		
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
		form.add(choiceClientType);

		
		final RequiredTextField<String> textClientName = new RequiredTextField<String>("input-clientName", new PropertyModel<String>(client, "fullName"));
		form.add(textClientName);
		
		final RequiredTextField<String> textClientPin = new RequiredTextField<String>("input-clientPersonalNumber", new PropertyModel<String>(client, "personalNumber"));
		form.add(textClientPin); 

		final TextField<String> textClientRegNo = new TextField<String>("input-clientRegNumber", new PropertyModel<String>(client, "regNumber"));
		form.add(textClientRegNo);

		
		
		
		
		final RequiredTextField<String> textContractNumber = new RequiredTextField<String>("input-contractNumber",
				new PropertyModel<String>(client.getBaseDocument(), "number"));
		form.add(textContractNumber);
		
		
		final DateTextField dateContractStart = new DateTextField("input-contractSignDate",
				new PropertyModel<Date>(client.getBaseDocument(), "signDate"), new StyleDateConverter("S-", true))
		{
			@Override
			public Locale getLocale()
			{
				return new Locale("ro");
			};
			
		};
		form.add(dateContractStart);
		dateContractStart.add(new DatePicker());
		
		
		final DateTextField dateContractStop = new DateTextField("input-contractEndDate",
				new PropertyModel<Date>(client.getBaseDocument(), "stopDate"), new StyleDateConverter("S-", true))
		{
			@Override
			public Locale getLocale()
			{
				return new Locale("ro");
			};
			
		};
		form.add(dateContractStop);
		dateContractStop.add(new DatePicker());
		
		

		form.add(new ContractServiceListEditPanel("panel-servicesEditList", new PropertyModel<Client>(this, "client"), form));
		

	}
	

	
	
	public Client getClient()
	{
		return this.client;
	}
}

