package leland.web.wicket;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author Radu Cugut
 */
public class PageFooterPanel
		extends Panel
{
	public PageFooterPanel(String id)
	{
		super(id);
		this.add(new Label("label-webappVersion", "1.0-beta1"));
	}
}

