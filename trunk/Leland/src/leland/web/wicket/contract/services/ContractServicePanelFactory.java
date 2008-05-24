package leland.web.wicket.contract.services;

import leland.domain.AbstractService;
import leland.domain.ContractConnectionService;
import leland.domain.ContractGenericService;
import leland.web.wicket.contract.services.edit.AbstractContractServiceEditPanel;
import leland.web.wicket.contract.services.edit.ContractConnectionServiceEditPanel;
import leland.web.wicket.contract.services.edit.ContractGenericServiceEditPanel;
import leland.web.wicket.contract.services.edit.ContractInternetServiceEditPanel;
import leland.web.wicket.contract.services.view.AbstractContractServiceViewPanel;
import leland.web.wicket.contract.services.view.ContractConnectionServiceViewPanel;
import leland.web.wicket.contract.services.view.ContractGenericServiceViewPanel;
import leland.web.wicket.contract.services.view.ContractInternetServiceViewPanel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;

public class ContractServicePanelFactory
{
	@SuppressWarnings("unchecked")
	public static AbstractContractServiceEditPanel getEditPanel(final AbstractService service, String markupId, final Form form)
	{
		if(service instanceof ContractGenericService)
		{
			return new ContractGenericServiceEditPanel(markupId,
				new Model(service), form);
		}
		else if(service instanceof ContractConnectionService)
		{
			return new ContractConnectionServiceEditPanel(markupId,
					new Model(service), form);
		}
		else// if(service instanceof ContractInternetService)
		{
			return new ContractInternetServiceEditPanel(markupId,
					new Model(service), form);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static AbstractContractServiceViewPanel getViewPanel(final AbstractService service, String markupId)
	{
		if(service instanceof ContractGenericService)
		{
			return new ContractGenericServiceViewPanel(markupId,
				new Model(service));
		}
		else if(service instanceof ContractConnectionService)
		{
			return new ContractConnectionServiceViewPanel(markupId,
					new Model(service));
		}
		else// if(service instanceof ContractInternetService)
		{
			return new ContractInternetServiceViewPanel(markupId,
					new Model(service));
		}
	}	
}
