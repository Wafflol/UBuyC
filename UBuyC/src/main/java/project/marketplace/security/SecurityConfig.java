package project.marketplace.security;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import project.marketplace.models.User;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationSuccessHandler successHandler;

    /**
     * Sets the custom user details service and custom auth handler objects
     *
     * @param customUserDetailsService the custom user details service
     * @param customAuthenticationSuccessHandler the auth handler
     */
    public SecurityConfig(CustomUserDetailsService customUserDetailsService,
                          CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.successHandler = customAuthenticationSuccessHandler;
    }

    /**
     * Creates the security chain
     * Makes it so that only login, signup, and verification are accessible by anyone
     *
     * @param http the security object to create the chain on
     * @return a security chain indicating what pages are accessible
     * @throws Exception for invalid inupts
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().
                requestMatchers("/login", "/signup", "/verification").permitAll().
                anyRequest().authenticated().
                and().
                formLogin(form -> form.
                        loginPage("/login").
                        usernameParameter("email").
                        passwordParameter("password").
                        successHandler(successHandler).
                        permitAll()).
                logout(form -> form.
                        logoutUrl("/logout").
                        logoutSuccessUrl("/account").
                        invalidateHttpSession(true).
                        clearAuthentication(true).
                        deleteCookies("JSESSIONID").
                        permitAll()
                ).
                csrf(form -> form.disable());
        return http.build();
    }

    /**
     * Creates and returns a custom UserDetailsService
     *
     * @return the UserDetailsService implementation used for loading user-specific data.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    /**
     * Creates and returns an AuthenticationManager bean that is configured
     *
     * @param http theHttpSecurity object used for configuring security settings.
     * @return the configured AuthenticationManager bean.
     * @throws Exception if there is a problem with building the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return authenticationManagerBuilder.build();
    }

    /**
     * Creates and returns a DaoAuthenticationProvider bean, which is used
     * for authentication with user details loaded from a database. It also sets
     * a custom password encoder that encrypts and verifies passwords using BCrypt.
     *
     * @return the configured DaoAuthenticationProvider bean.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(new PasswordEncoder() {
            /**
             * Encodes a raw text password
             * @param rawPassword the password to encode
             * @return the encoded password
             */
            @Override
            public String encode(CharSequence rawPassword) {
                String rawPass = rawPassword.toString();
                System.out.println("SecurityConfig.java: PASSWORD RAW: " + rawPass);
                return User.encryptPassword(rawPass);
            }

            /**
             * Checks if a raw password matches and encoded one
             * @param rawPassword the raw password
             * @param encodedPassword the encoded password
             * @return true if matches
             */
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                System.out.println("SecurityConfig.java: RAWPASSWORD: " + rawPassword);
                System.out.println("SecurityConfig.java: ENCODED PASS: " + encodedPassword);
                System.out.println("SecurityConfig.java: BCRYPT CHECK: "
                        + BCrypt.checkpw(rawPassword.toString(), encodedPassword));
                boolean success = BCrypt.checkpw(rawPassword.toString(), encodedPassword);
                System.out.println("SecurityConfig.java: rawPass match encoded?: " + success);
                return success;
            }
        });
        return authenticationProvider;
    }
}
