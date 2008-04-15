package leland.web.wicket;

import org.apache.wicket.Application;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

public class SignInPage
		extends WebPage
{
	public SignInPage()
	{
		super();
		this.add(new SignInPanel("panel-signIn"));
		this.add(new PageFooterPanel("page-footer"));
	}

	
	
	/**
	 * 
	 * @author Radu CUGUT
	 */
	private class SignInPanel
			extends Panel
	{
		private PasswordTextField password;
		private TextField username;

		public SignInPanel(String id)
		{
			super(id);

			final FeedbackPanel feedback = new FeedbackPanel("feedback");
			this.add(feedback);
			
			this.add(new SignInForm("form-signIn"));
		}

		/**
		 * Convenience method to access the password.
		 * 
		 * @return The password
		 */
		public String getPassword()
		{
			return password.getInput();
		}

		/**
		 * Convenience method to access the username.
		 * 
		 * @return The user name
		 */
		public String getUsername()
		{
			return username.getModelObjectAsString();
		}

		/**
		 * Convenience method set persistence for username and password.
		 * 
		 * @param enable
		 *            Whether the fields should be persistent
		 */
		public void setPersistent(final boolean enable)
		{
			username.setPersistent(enable);
		}

		/**
		 * Sign in user if possible.
		 * 
		 * @param username
		 *            The username
		 * @param password
		 *            The password
		 * @return True if signin was successful
		 */
		public boolean signIn(String username, String password)
		{
			return AuthenticatedWebSession.get().signIn(username, password);
		}

		protected void onSignInFailed()
		{
			error("Sign in failed");
		}

		protected void onSignInSucceeded()
		{
			// If login has been called because the user was not yet
			// logged in, than continue to the original destination,
			// otherwise to the Home page
			if (!continueToOriginalDestination())
			{
				setResponsePage(getApplication().getSessionSettings().getPageFactory().newPage(getApplication().getHomePage(), (PageParameters) null));
			}
		}

		/**
		 * Sign in form.
		 */
		public final class SignInForm
				extends Form
		{
			private final ValueMap properties = new ValueMap();

			/**
			 * Constructor
			 */
			public SignInForm(final String id)
			{
				super(id);

				if(Application.get().getConfigurationType().equals(Application.DEVELOPMENT))
				{
					properties.add("username", "admin");
					properties.add("password", "admin");
				}
				
				// Attach textfield components that edit properties map
				// in lieu of a formal beans model
				this.add(username = new TextField("input-username", new PropertyModel(properties, "username")));
				this.add(password = new PasswordTextField("input-password", new PropertyModel(properties, "password")));
			}

			@Override
			public final void onSubmit()
			{
				if (signIn(getUsername(), getPassword()))
				{
					onSignInSucceeded();
				}
				else
				{
					onSignInFailed();
				}
			}
		}//SignInForm
	}//SignInPanel
}
