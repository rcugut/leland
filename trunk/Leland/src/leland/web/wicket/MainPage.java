package leland.web.wicket;

import leland.web.wicket.contracts.NewClientPage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;


/**
 * @author Radu Cugut
 */
public class MainPage
		extends AuthorizedBasePage
{
	public MainPage()
	{
		this(null);
	}

	public MainPage(PageParameters parameters)
	{
		super(parameters);
		
		this.setVersioned(false);
		
		this.add(new BookmarkablePageLink("link-contracts.NewClientPage", NewClientPage.class));
	}

	@Override
	protected String getInSitePositionString()
	{
		return "Home";
	}
}

