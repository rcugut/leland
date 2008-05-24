package leland.web.wicket.contract.services.edit;

import leland.domain.Address;
import leland.domain.ContractConnectionService;
import leland.web.wicket.general.AddressEditPanel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * @author Radu Cugut
 */
public class ContractConnectionServiceEditPanel
		extends AbstractContractServiceEditPanel<ContractConnectionService>
{
	public ContractConnectionServiceEditPanel(String id, IModel<ContractConnectionService> model, Form<Object> form)
	{
		super(id, model, form);
		
		this.add(new AddressEditPanel("panel-address",
				new PropertyModel<Address>(service, "address"), this.form));

		this.add(new TextArea<String>("input-serviceDetails", 
				new PropertyModel<String>(this.service, "details")));
	}
	
	

	@Override
	protected String getServiceTypeName()
	{
		return "Conectare locatie";
	}
}

