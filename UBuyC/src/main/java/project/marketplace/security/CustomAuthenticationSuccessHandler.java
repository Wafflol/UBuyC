package project.marketplace.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import project.marketplace.daos.AccountDao;
import project.marketplace.models.User;

import java.io.IOException;

/**
 * This component is called if the username and password match on login
 * It checks if the account is verified and decides where to send the user
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AccountDao dao; // Your DAO to fetch the user

    /**
     * Sets the accountdao to allow access to users from the database
     * @param dao the accountdao
     */
    public CustomAuthenticationSuccessHandler(AccountDao dao) {
        this.dao = dao;
    }

    /**
     * Runs after user logs in with correct credential
     * Checks if user is authenticated, and sends them back to verification or to index
     * @param request the request the client made
     * @param response the response the server sends
     * @param authentication auth info from the client
     * @throws IOException if the input is bad
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String email = authentication.getName();
        System.out.println("CustomAuthHandler.java: EMAIL IS: " + email);
        User user = dao.getUserByEmail(email);

        request.getSession().setAttribute("user", user);

        if (!user.getValidation()) {
            request.getSession().setAttribute("verified", false);
            response.sendRedirect("/verification");
            return;
        }

        request.getSession().setAttribute("verified", true);
        response.sendRedirect("/index");
    }
}
