package leland.web.wicket.general;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class EntityEditPanel
		extends Panel<Object>
{
	protected final Form<Object> form;

	public EntityEditPanel(String id, Form<Object> form)
	{
		super(id);
		this.form = form;
	}
}
