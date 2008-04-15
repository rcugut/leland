package leland.web.wicket;

import org.apache.wicket.Request;
import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;

/**
 * 
 * @author Radu Cugut
 *
 */
@SuppressWarnings("serial")
public class LelandAuthWebSession
		extends AuthenticatedWebSession
{
	public LelandAuthWebSession(AuthenticatedWebApplication application, Request request)
	{
		super(request);
	}

	@Override
	public boolean authenticate(String arg0, String arg1)
	{
		return true;
	}

	@Override
	public Roles getRoles()
	{
		return new Roles(Roles.ADMIN);
	}
}
