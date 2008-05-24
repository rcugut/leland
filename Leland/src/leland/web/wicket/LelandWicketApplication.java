package leland.web.wicket;

import leland.web.wicket.contract.NewClientPage;

import org.apache.wicket.Page;
import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class LelandWicketApplication
		extends AuthenticatedWebApplication
{
	@Override
	public String getConfigurationType()
	{
		return super.getConfigurationType();
//		return KfWicketWebApplication.DEPLOYMENT;
	}	

	
	@Override
	protected void init()
	{
		super.init();

		// THIS LINE IS IMPORTANT - IT INSTALLS THE COMPONENT INJECTOR THAT WILL
		// INJECT NEWLY CREATED COMPONENTS WITH THEIR SPRING DEPENDENCIES
		addComponentInstantiationListener(new SpringComponentInjector(this));
		
		
		mountBookmarkablePage("/login", SignInPage.class);
		mountBookmarkablePage("/main", MainPage.class);

		mountBookmarkablePage("/client/new", NewClientPage.class);
	}	


	@Override
	public Class<? extends Page> getHomePage()
	{
		return MainPage.class;
	}
	
	
	@Override
	protected Class<? extends WebPage> getSignInPageClass()
	{
		return SignInPage.class;
	}

	@Override
	protected Class<? extends AuthenticatedWebSession> getWebSessionClass()
	{
		return LelandAuthWebSession.class;
	}
}
