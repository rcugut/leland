package leland.web.wicket.contract.services.view;

import leland.domain.ContractInternetService;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * @author Radu Cugut
 */
public class ContractInternetServiceViewPanel
		extends AbstractContractServiceViewPanel<ContractInternetService>
{
	public ContractInternetServiceViewPanel(String id, IModel<ContractInternetService> model)
	{
		super(id, model);

		this.add(new Label<String>("label-totalDownload", 
				service.getContractedTotalDownloadCir() + "/" + service.getContractedTotalDownloadMir() + " kbps"));
		
		this.add(new Label<String>("label-totalUpload", 
				service.getContractedTotalUploadCir() + "/" + service.getContractedTotalUploadMir() + " kbps"));

		this.add(new Label<String>("label-nrIp",
				String.valueOf(service.getNbOfIpAddresses())));
		
		this.add(new Label<String>("label-details", service.getDetails()));
	}
}

