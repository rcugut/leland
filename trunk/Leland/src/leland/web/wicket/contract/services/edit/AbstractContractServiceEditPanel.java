package leland.web.wicket.contract.services.edit;


import java.util.Arrays;

import leland.domain.AbstractService;
import leland.domain.enums.BillingMethod;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * @author Radu Cugut
 */
public abstract class AbstractContractServiceEditPanel<T extends AbstractService>
		extends Panel<T>
{
	protected final T service;
	protected final Form<Object> form;
	private final FeedbackPanel feedbackPanel;
	
	public AbstractContractServiceEditPanel(String id, IModel<T> model, Form<Object> form)
	{
		super(id, model);
		this.service = model.getObject();
		this.form = form;

		this.add(new Label<String>("label-typeName", new AbstractReadOnlyModel<String>()
				{
					@Override
					public String getObject()
					{
						return getServiceTypeName();
					}
				}));

		this.feedbackPanel = new FeedbackPanel("panel-feedback");
	    this.add(feedbackPanel.setOutputMarkupId(true));
		
		this.add(new DropDownChoice<BillingMethod>("input-billingMethod",
				new PropertyModel<BillingMethod>(this.service, "billingMethod"), Arrays.asList(BillingMethod.values())));
		
		this.add(new TextField<String>("input-servicePrice", 
				new PropertyModel<String>(this.service, "contractedPrice")).setOutputMarkupPlaceholderTag(true));
	}

	protected abstract String getServiceTypeName();
	
	
	public FeedbackPanel getFeedbackPanel()
	{
		return this.feedbackPanel;
	}
}
