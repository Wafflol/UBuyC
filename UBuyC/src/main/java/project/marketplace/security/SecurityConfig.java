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

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.successHandler = customAuthenticationSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/login", "/signup", "/verification").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(successHandler)
                        .permitAll())
                .logout(form -> form
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/account")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                        )
                .csrf(form -> form.disable());
        return http.build();
    }

   @Bean
   public UserDetailsService userDetailsService() {
       return customUserDetailsService;
   }

   @Bean
   public AuthenticationManager authManager(HttpSecurity http) throws Exception {
       AuthenticationManagerBuilder authenticationManagerBuilder =
               http.getSharedObject(AuthenticationManagerBuilder.class);
       authenticationManagerBuilder.authenticationProvider(authenticationProvider());
       return authenticationManagerBuilder.build();
   }

   @Bean
   public DaoAuthenticationProvider authenticationProvider() {
       DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
       authenticationProvider.setUserDetailsService(customUserDetailsService);
       authenticationProvider.setPasswordEncoder(new PasswordEncoder() {
           @Override
           public String encode(CharSequence rawPassword) {
               String rawPass = rawPassword.toString();
               System.out.println("SecurityConfig.java: PASSWORD RAW: " + rawPass);
               return User.encryptPassword(rawPass);
           }

           @Override
           public boolean matches(CharSequence rawPassword, String encodedPassword) {
               System.out.println("SecurityConfig.java: RAWPASSWORD: " + rawPassword);
               System.out.println("SecurityConfig.java: ENCODED PASS: " + encodedPassword);
               System.out.println("SecurityConfig.java: BCRYPT CHECK: " + BCrypt.checkpw(rawPassword.toString(), encodedPassword));
               boolean success = BCrypt.checkpw(rawPassword.toString(), encodedPassword);
               System.out.println("SecurityConfig.java: rawPass match encoded?: " + success);
               return success;
           }
       });
       return authenticationProvider;
   }
}
