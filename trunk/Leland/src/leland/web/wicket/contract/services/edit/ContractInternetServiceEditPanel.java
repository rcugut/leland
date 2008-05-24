package leland.web.wicket.contract.services.edit;

import leland.domain.ContractInternetService;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * @author Radu Cugut
 */
public class ContractInternetServiceEditPanel
		extends AbstractContractServiceEditPanel<ContractInternetService>
{
	public ContractInternetServiceEditPanel(String id, IModel<ContractInternetService> model, Form<Object> form)
	{
		super(id, model, form);
		
		this.add(new RequiredTextField<Integer>("input-ulCir",
				new PropertyModel<Integer>(service, "contractedTotalUploadCir")).setOutputMarkupPlaceholderTag(true));
		
		this.add(new RequiredTextField<Integer>("input-ulMir",
				new PropertyModel<Integer>(service, "contractedTotalUploadMir")).setOutputMarkupPlaceholderTag(true));
		
		this.add(new RequiredTextField<Integer>("input-dlCir",
				new PropertyModel<Integer>(service, "contractedTotalDownloadCir")).setOutputMarkupPlaceholderTag(true));
		
		this.add(new RequiredTextField<Integer>("input-dlMir",
				new PropertyModel<Integer>(service, "contractedTotalDownloadMir")).setOutputMarkupPlaceholderTag(true));
		
		this.add(new RequiredTextField<Integer>("input-nbOfIpAddresses",
				new PropertyModel<Integer>(service, "nbOfIpAddresses")).setOutputMarkupPlaceholderTag(true));
		
		this.add(new TextArea<String>("input-serviceDetails", 
				new PropertyModel<String>(this.service, "details")).setOutputMarkupPlaceholderTag(true));
	}


	@Override
	protected String getServiceTypeName()
	{
		return "Internet";
	}
}

