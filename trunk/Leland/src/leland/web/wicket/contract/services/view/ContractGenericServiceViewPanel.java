package leland.web.wicket.contract.services.view;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

import leland.domain.ContractGenericService;

/**
 * @author Radu Cugut
 */
public class ContractGenericServiceViewPanel
		extends AbstractContractServiceViewPanel<ContractGenericService>
{
	public ContractGenericServiceViewPanel(String id, IModel<ContractGenericService> model)
	{
		super(id, model);
		
		this.add(new Label<String>("label-details", service.getDetails()));
	}
}

