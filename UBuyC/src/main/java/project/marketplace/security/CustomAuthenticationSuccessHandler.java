package project.marketplace.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import project.marketplace.daos.AccountDao;
import project.marketplace.models.User;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AccountDao dao; // Your DAO to fetch the user

    public CustomAuthenticationSuccessHandler(AccountDao dao) {
        this.dao = dao;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String email = authentication.getName();
        System.out.println("CustomAuthHandler.java: EMAIL IS: " + email);
        User user = dao.getUserByEmail(email);

        request.getSession().setAttribute("user", user);

        // Redirect to the index page
        response.sendRedirect("/index");
    }
}
