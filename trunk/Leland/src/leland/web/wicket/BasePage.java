package leland.web.wicket;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;

/**
 * @author Radu Cugut
 */
public abstract class BasePage
		extends WebPage
{
	public BasePage(PageParameters parameters)
	{
		super(parameters);
	}
}

