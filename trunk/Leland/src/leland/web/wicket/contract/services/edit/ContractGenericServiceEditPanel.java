package leland.web.wicket.contract.services.edit;

import leland.domain.ContractGenericService;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * @author Radu Cugut
 */
public class ContractGenericServiceEditPanel
		extends AbstractContractServiceEditPanel<ContractGenericService>
{
	public ContractGenericServiceEditPanel(String id, IModel<ContractGenericService> model, Form<Object> form)
	{
		super(id, model, form);

		this.add(new TextArea<String>("input-serviceDetails", 
					new PropertyModel<String>(this.service, "details")));
	}

	
	@Override
	protected String getServiceTypeName()
	{
		return "Generic";
	}
}

