package space.space.web.base;

import space.space.data.models.CreditAccount;
import space.space.services.models.auth.LoginUserServiceModel;

import javax.servlet.http.HttpSession;

public class BaseController {
    protected String getUsername(HttpSession session) {
        return session.getAttribute("username").toString();
    }

    protected String getPlanetName(HttpSession session) {
        return session.getAttribute("planetName").toString();
    }
    protected CreditAccount getAccount(HttpSession session) {
        return  (CreditAccount)session.getAttribute("account");
    }

}
