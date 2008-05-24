package leland.web.wicket.contract.services;

import java.util.Arrays;
import java.util.List;

import leland.domain.AbstractService;
import leland.domain.Address;
import leland.domain.Client;
import leland.domain.ContractConnectionService;
import leland.domain.ContractGenericService;
import leland.domain.ContractInternetService;
import leland.domain.enums.ServiceType;
import leland.web.wicket.contract.services.edit.AbstractContractServiceEditPanel;
import leland.web.wicket.contract.services.view.AbstractContractServiceViewPanel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IFormVisitorParticipant;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * @author Radu Cugut
 */
public final class ContractServiceListEditPanel
		extends Panel<Object>
{
	private final Client client;
	private final ModalWindow modalWindowEditService = new ModalWindow("modalwindow-editService");
	private boolean modalWindowOkButtonPressed = false;
	private ServiceType serviceTypeToAdd = ServiceType.CONNECTION;
	private final WebMarkupContainer containerServicesList;
	
	@SuppressWarnings("unchecked")
	public ContractServiceListEditPanel(String id, IModel<Client> model, Form form)
	{
		super(id);
		this.client = model.getObject();
		
		
		modalWindowEditService.setCookieName("modalwindow-editService");
		modalWindowEditService.setCloseButtonCallback(new ModalWindow.CloseButtonCallback()
        {
            public boolean onCloseButtonClicked(AjaxRequestTarget target)
            {
            	modalWindowOkButtonPressed = false;
            	return true;
            }
        });
		this.add(modalWindowEditService);
		
		
		
		this.containerServicesList = new WebMarkupContainer("container-servicesList");
		this.add(this.containerServicesList.setOutputMarkupId(true));
		final ListView<AbstractService> listServices = 
			new ListView<AbstractService>("listview-services",
					new PropertyModel<List<AbstractService>>(client, "allServicesAsList"))
		{
			@Override
			protected void populateItem(ListItem<AbstractService> item)
			{
				item.add(new ServiceViewPanel("panel-service", item.getModelObject()));
			}
		};
		this.containerServicesList.add(listServices);



		
		final DropDownChoice<ServiceType> choiceDesigners = new DropDownChoice<ServiceType>("input-serviceType",
				new PropertyModel<ServiceType>(this, "serviceTypeToAdd"), Arrays.asList(ServiceType.values()));
		this.add(choiceDesigners);
		
		this.add(new AjaxButton<Object>("input-addService")
		{
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form)
			{
				final AbstractService service;
				switch(serviceTypeToAdd)
				{
					case GENERIC: 		service = new ContractGenericService(); break;
					case CONNECTION:	service = new ContractConnectionService().setAddress(new Address()); break;
					default:
					case INTERNET:		
						if(client.getContractedConnectionServices().size() == 0)
						{
							target.appendJavascript("alert('Atentie: Nu am gasit nici o locatie pentru clientul curent.\\nTrebuie sa adaugati cel putin un serviciu de conectare pantru a putea adauga serviciu Internet.')");
							return;
						}
						if(client.getContractedInternetService() != null)
						{
							target.appendJavascript("alert('Exista deja un serviciu Internet. Nu poate fi adaugat mai mult de un astfel de serviciu. Pentru a aduce modificari, editati-l pe cel existent.')");
							return;
						}
						
						service = new ContractInternetService();
						
						break;
				}

				
				modalWindowEditService.setTitle("Adaugare serviciu");
				modalWindowEditService.setContent(new ServiceEditPanel(modalWindowEditService.getContentId(), service));

				modalWindowEditService.setWindowClosedCallback(new ModalWindow.WindowClosedCallback()
				{
					public void onClose(AjaxRequestTarget target)
					{
						if(modalWindowOkButtonPressed)
						{
							client.addService(service);
							target.addComponent(containerServicesList);
						}
					}
				});

				modalWindowEditService.show(target);
				

			}//onSubmit
		});//AjaxButton		
	}
	
	
	
	
	public ServiceType getServiceTypeToAdd()
	{
		return serviceTypeToAdd;
	}
	public void setServiceTypeToAdd(ServiceType serviceTypeToAdd)
	{
		this.serviceTypeToAdd = serviceTypeToAdd;
	}
	
	
	
	
	
	/**
	 * 
	 * @author Radu CUGUT
	 */
	class ServiceEditPanel extends Panel<Object>
	{
		@SuppressWarnings("unchecked")
		public ServiceEditPanel(final String id, final AbstractService service)
		{
			super(id);

			final Form form = new Form("form-serviceEdit");
			this.add(form);
			
			final AbstractContractServiceEditPanel serviceEditPanel = 
				ContractServicePanelFactory.getEditPanel(service, "panel-serviceEdit", form);
			
			form.add(serviceEditPanel);
			
			
			
			form.add(new AjaxButton("input-submit", form)
			{
				@Override
				protected void onSubmit(final AjaxRequestTarget target, final Form form)
				{
					modalWindowOkButtonPressed = true;
					modalWindowEditService.close(target);
				}

				@Override
				protected void onError(final AjaxRequestTarget target, final Form form)
				{
//					target.addComponent(serviceEditPanel.getFeedbackPanel());
					form.visitFormComponents(new IVisitor()
					{
						public Object formComponent(IFormVisitorParticipant formVisitor)
						{
							if (formVisitor instanceof FormComponent)
							{
								FormComponent formComponent = (FormComponent) formVisitor;

								if (!formComponent.isValid())
								{
//									formComponent.add(new SimpleAttributeModifier("class", "error")); 
									target.addComponent(formComponent);
									target.appendJavascript("$('"+ formComponent.getMarkupId() +"').addClassName('error');");
								}
							}
							return formVisitor;
						}
					});
				}
			});
			
			
			form.add(new AjaxButton("input-cancel", form)
			{
				@Override
				protected void onSubmit(final AjaxRequestTarget target, final Form form)
				{
					modalWindowOkButtonPressed = false;
					modalWindowEditService.close(target);
				}
			}.setDefaultFormProcessing(false));
			
		}
	}//ServiceEditPanel
	
	
	
	
	/**
	 * 
	 * @author Radu CUGUT
	 */
	class ServiceViewPanel extends Panel<Object>
	{
		@SuppressWarnings("unchecked")
		public ServiceViewPanel(String id, final AbstractService service)
		{
			super(id);

			this.add(new Label<String>("label-typeName", new PropertyModel<String>(service, "serviceTypeName")));

			final AbstractContractServiceViewPanel<AbstractService> serviceViewPanel =
				ContractServicePanelFactory.getViewPanel(service, "panel-serviceView");
			this.add(serviceViewPanel);
			
			this.add(new AjaxLink("link-editService")
			{
				@Override
				public void onClick(AjaxRequestTarget target)
				{
					modalWindowEditService.setTitle("Modificare serviciu");
					modalWindowEditService.setContent(new ServiceEditPanel(modalWindowEditService.getContentId(), service));

						modalWindowEditService.setWindowClosedCallback(new ModalWindow.WindowClosedCallback()
				        {
				            public void onClose(AjaxRequestTarget target)
				            {
				                if(modalWindowOkButtonPressed)
				                {
				                	target.addComponent(containerServicesList);
				                }
				            }
				        });

					modalWindowEditService.show(target);
				}
			});
			
			
			this.add(new AjaxLink("link-removeService")
			{
				@Override
				public void onClick(AjaxRequestTarget target)
				{
					if(service.getServiceType() == ServiceType.CONNECTION &&
							client.getContractedConnectionServices().size() == 1 &&
							client.getContractedInternetService() != null)
					{
						target.appendJavascript("alert('Nu puteti sterge aceasta locatie. Serviciul Internet existent are nevoie de cel putin o locatie.');");
						return;
					}
					client.removeService(service);
					target.addComponent(containerServicesList);
				}
			});
		}
	}//ServiceViewPanel
}

