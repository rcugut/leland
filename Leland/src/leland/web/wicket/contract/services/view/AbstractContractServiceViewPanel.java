package leland.web.wicket.contract.services.view;

import leland.domain.AbstractService;
import leland.util.StringUtil;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * @author Radu Cugut
 */
public abstract class AbstractContractServiceViewPanel<T extends AbstractService>
		extends Panel<T>
{
	protected final T service;
	
	public AbstractContractServiceViewPanel(String id, IModel<T> model)
	{
		super(id, model);
		this.service = model.getObject();
		
		this.add(new Label<String>("label-dateCreated", StringUtil.formatDateLongDMY(this.service.getDateCreated())));
//		this.add(new Label<String>("label-dateUpdated", StringUtil.formatDateLongDMYHM(this.service.getDateUpdated())));
		this.add(new Label<String>("label-price", StringUtil.formatMoney(this.service.getContractedPrice())));
		this.add(new Label<String>("label-billingMethod", "("+ this.service.getBillingMethod().toString()+ ")" ));
	}
}

