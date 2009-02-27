package leland.web.wicket;

import leland.web.wicket.billing.ClientNewInvoice;
import leland.web.wicket.billing.ClientNewPayment;
import leland.web.wicket.billing.ClientBillingInfo;
import leland.web.wicket.contract.ClientPage;
import leland.web.wicket.contract.ClientsPage;
import leland.web.wicket.contract.NewClientPage;

import org.apache.wicket.Page;
import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.target.coding.MixedParamUrlCodingStrategy;
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

		mountBookmarkablePage("/clients", ClientsPage.class);

		mountBookmarkablePage("/clients/new", NewClientPage.class);
		
		mount(new MixedParamUrlCodingStrategy("/client",
				ClientPage.class, new String[] { "id" }));

		mount(new MixedParamUrlCodingStrategy("/client/billing",
				ClientBillingInfo.class, new String[] { "id" }));
		
		mount(new MixedParamUrlCodingStrategy("/client/invoices/new",
				ClientNewInvoice.class, new String[] { "id" }));

		mount(new MixedParamUrlCodingStrategy("/client/payments/new",
				ClientNewPayment.class, new String[] { "id" }));

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
