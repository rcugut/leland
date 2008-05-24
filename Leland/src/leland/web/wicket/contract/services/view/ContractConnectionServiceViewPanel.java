package leland.web.wicket.contract.services.view;

import leland.domain.ContractConnectionService;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * @author Radu Cugut
 */
public class ContractConnectionServiceViewPanel
		extends AbstractContractServiceViewPanel<ContractConnectionService>
{
	public ContractConnectionServiceViewPanel(String id, IModel<ContractConnectionService> model)
	{
		super(id, model);

		this.add(new Label<String>("label-address", service.getAddress().getAddresForDisplay()));
		
		this.add(new Label<String>("label-details", service.getDetails()));
	}
}

