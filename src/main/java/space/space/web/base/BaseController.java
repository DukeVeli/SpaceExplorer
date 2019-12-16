package space.space.web.base;

import space.space.services.models.auth.LoginUserServiceModel;

import javax.servlet.http.HttpSession;

public class BaseController {
    protected String getUsername(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("user")).getUsername();
    }

    protected String getPlanetName(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("planet")).getPlanetName();
    }
 /*   protected String getAccount(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("account")).getCreditCount();
    }*/

}
