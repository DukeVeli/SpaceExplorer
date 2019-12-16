package space.space.web.filters;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import space.space.data.models.CreditAccount;
import space.space.data.models.Planet;
import space.space.data.repositories.CreditAccountRepository;
import space.space.data.repositories.PlanetRepository;
import space.space.services.services.AuthenticatedUserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@AllArgsConstructor
@Component
public class LoggedInUserFilter implements Filter {
    private final AuthenticatedUserService authenticatedUserService;
    @Autowired
    private final PlanetRepository planetRepository;
    @Autowired
    private final CreditAccountRepository creditAccountRepository;



    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        String username = authenticatedUserService.getUsername();
        if(username.equals("anonymousUser")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
        session.setAttribute("username", username);

        Planet planet =  planetRepository.getByUserUsername(username).get();
        session.setAttribute("planet", planet);

        CreditAccount account = creditAccountRepository.getByUserUsername(username).get();
        session.setAttribute("money", account);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}