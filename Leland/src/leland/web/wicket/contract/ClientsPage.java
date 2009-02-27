package leland.web.wicket.contract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import leland.dao.ClientManager;
import leland.domain.Client;
import leland.util.StringUtil;
import leland.web.wicket.AuthorizedBasePage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.DefaultDataProvider;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

/**
 * @author Radu Cugut
 */
public class ClientsPage
		extends AuthorizedBasePage
{
	@SpringBean(name = "clientManager")
	private ClientManager clientManager;

	
	private List<Client> clientsList = new ArrayList<Client>();
	
	
	
	@SuppressWarnings("unchecked")
	public ClientsPage(PageParameters parameters)
	{
		super(parameters);
		
		this.add(new BookmarkablePageLink("link-contracts.NewClientPage", NewClientPage.class));
		

		final WebMarkupContainer<Object> containerSearchResults = new WebMarkupContainer<Object>("container-searchResults");
		this.add(containerSearchResults.setOutputMarkupId(true));
		
		final Label<String> labelSearchResults = new Label<String>("label-searchResultsText", new AbstractReadOnlyModel<String>()
				{
					@Override
					public String getObject()
					{
						int s = clientsList.size();
						if(s==1)
							return "S-a gasit 1 client";
						else if(s>1)
							return "S-au gasit " + s + " clienti";
						return "Nu s-a gasit nici un client care sa corespunda criteriilor de cautare";
					}
				});
		containerSearchResults.add(labelSearchResults.setVisible(false));
		
		
		final WebMarkupContainer<Object> containerClientsList = new WebMarkupContainer<Object>("container-clientsList");
		containerSearchResults.add(containerClientsList.setOutputMarkupId(true).setVisible(false));
		
		
		
		final ValueMap searchParameters = new ValueMap();
		
		final Form<Object> searchForm = new Form<Object>("form-searchClients");
		this.add(searchForm);
		
		final TextField<String> textClientName = new TextField<String>("input-clientName",
				new PropertyModel<String>(searchParameters, "clientName"));
		searchForm.add(textClientName);

		final TextField<String> textContractNumber = new TextField<String>("input-contractNumber",
				new PropertyModel<String>(searchParameters, "contractNumber"));
		searchForm.add(textContractNumber);
		
		searchForm.add(new AjaxButton<Object>("input-submit")
				{
					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form)
					{
						clientsList = clientManager.getDao().getAllActive(Client.class);
						containerClientsList.setVisible(clientsList.size()>0);
						labelSearchResults.setVisible(true);
						target.addComponent(containerSearchResults);
					}
				});

		
		
		IDataProvider<Client> clientsListDataProvider = new DefaultDataProvider()
		{
			@Override
			public Iterator<Client> iterator(int first, int count)
			{
				return clientsList.subList(first, first + count).iterator();
			}

			@Override
			public IModel model(Object object)
			{
				return new Model((Serializable) ((Client)object));
			}

			@Override
			public int size()
			{
				return clientsList.size();
			}
		};
		
		DataView clientsListDataView = new DataView<Client>("row-client", clientsListDataProvider, 20)
		{
			@Override
			protected void populateItem(Item<Client> item)
			{
				final Client client = item.getModelObject();
				
				final BookmarkablePageLink clientLink = new BookmarkablePageLink("link-client",
						ClientPage.class, new PageParameters("id=" + client.getId()));
				item.add(clientLink);
				
				clientLink.add(new Label<String>("label-clientId", String.valueOf(client.getId())));
				
				item.add(new Label<String>("label-clientType", client.getType().toString()));
				item.add(new Label<String>("label-clientName", client.getFullName()));
				item.add(new Label<String>("label-clientPin", client.getPersonalNumber()));
				item.add(new Label<String>("label-contractNumber", client.getBaseDocument().getNumber()));
				item.add(new Label<String>("label-contractStartDate", StringUtil.formatDateShortYMD(client.getBaseDocument().getSignDate())));
			}
		};
		
		containerClientsList.add(clientsListDataView);
		
		
		containerClientsList.add(new AjaxPagingNavigator("navigator-clientsList", clientsListDataView)
		{
			@Override
			protected void onAjaxEvent(AjaxRequestTarget target)
			{
				target.addComponent(containerClientsList);
			}				
		});
	}
}

