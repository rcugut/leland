package leland.web.wicket;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;

public class SignOutPage
	extends WebPage
{
	public SignOutPage()
	{
		this(null);
	}
	
	public SignOutPage(PageParameters parameters)
	{
		this.getSession().invalidate();
	}
}
