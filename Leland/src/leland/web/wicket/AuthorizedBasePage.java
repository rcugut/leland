package leland.web.wicket;

import leland.web.wicket.contract.ClientsPage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

@SuppressWarnings("serial")
@AuthorizeInstantiation(value={Roles.USER , Roles.ADMIN})
public abstract class AuthorizedBasePage
		extends BasePage
{
	public AuthorizedBasePage(PageParameters parameters)
	{
		super(parameters);
		
		this.add(new BookmarkablePageLink("link-Clients", ClientsPage.class));
	}
}
