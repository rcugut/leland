package leland.web.wicket;

import org.apache.wicket.Request;
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
	public LelandAuthWebSession(Request request)
	{
		super(request);
	}

	@Override
	public boolean authenticate(String username, String password)
	{
		return true;
	}

	@Override
	public Roles getRoles()
	{
		return new Roles(Roles.ADMIN);
	}
}
