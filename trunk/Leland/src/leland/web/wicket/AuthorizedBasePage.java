package leland.web.wicket;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;

@SuppressWarnings("serial")
@AuthorizeInstantiation(value={Roles.USER , Roles.ADMIN})
public abstract class AuthorizedBasePage
		extends BasePage
{
	public AuthorizedBasePage(PageParameters parameters)
	{
		super(parameters);
		
		this.add(new Label<String>("label-inSitePosition", getInSitePositionString()));
	}
	
	protected abstract String getInSitePositionString();
}
