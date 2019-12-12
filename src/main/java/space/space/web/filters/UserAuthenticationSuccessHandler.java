package space.space.web.filters;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import space.space.services.models.planets.PlanetDetailsServiceModel;
import space.space.services.models.planets.PlanetServiceModel;
import space.space.services.services.AuthenticatedUserService;
import space.space.services.services.PlanetService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final AuthenticatedUserService authenticatedUserService;
    private final PlanetService planetService;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public UserAuthenticationSuccessHandler(
            AuthenticatedUserService authenticatedUserService,
            PlanetService planetService) {
        super();
        this.authenticatedUserService = authenticatedUserService;
        this.planetService = planetService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
        String username = authenticatedUserService.getUsername();
        try {
            PlanetServiceModel planet = planetService.getByUsername(username);
            httpServletRequest.getSession()
                    .setAttribute("planetName", planet.getName());
        } catch (Exception ex) {
            // do nothing
        }

        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/home");
    }
}
