package project.marketplace.controller;

import java.time.LocalDate;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;

import project.marketplace.daos.AccountDao;
import project.marketplace.daos.UserAlreadyExistsException;
import project.marketplace.models.User;
import project.marketplace.registration.OnRegistrationCompleteEvent;

/**
 * Creates a class for controller of the entire website. Controls the I/O of each page.
 */
@Controller
public class UBuyCController {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    private MessageSource messages;

    private final AccountDao dao;

    public UBuyCController(AccountDao dao) {
        this.dao = dao;
    }

    /**
     * Displays the login screen
     * 
     * @return login.html file
     */
    @GetMapping({"/", "login"})
    public String login() { 
        return "login";
    }

    /**
     * Displays the signup screen and binds the User object to the form
     * 
     * @param request
     * @param model the model that the User object binds to
     * @return signup.html file
     */
    @GetMapping("/signup")
    public String loadSignupPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    /**
     * Posts a request to the accounts database to try and add a new user. 
     * 
     * Redirects to verification page if successful and sends an OTP email
     * to user on successful account creation.
     * 
     * Redirects to emailError page if email cannot be sent
     * 
     * @param user the user DTO being sent to the database
     * @param request
     * @param errors thrown errors
     * @return verfication.html on success or emailError.html otherwise
     */
    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute("user") @Valid User user, HttpServletRequest request, Error errors) { 
        
        try {
            User registeredUser = dao.createUser(user);
            //eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser, request.getLocale()));
        } catch (UserAlreadyExistsException e) {
            return new ModelAndView().addObject("message", "An account for that email already exists.");
        } catch (RuntimeException e) {
            return new ModelAndView("emailError", "user", user);
        }
        return new ModelAndView("verification", "user", user);
        
    }

    /** Displays the verfication screen
     * 
     * @return verification.html
     */
    @GetMapping("/verification")
    public String loadVerificationPage(Model model) { 
        String otp = new String();
        model.addAttribute("otp", otp);
        return "verification";
    }

    // @PostMapping("/verification")
    // public ModelAndView verifyOtp(@ModelAttribute("otp") @Valid String otp, @ModelAttribute("user") @Valid User user, WebRequest request) {
    //     Locale locale = request.getLocale();
    //     int otpToken = dao.getOtpByUser(user);
    //     LocalDate expiryDate = dao.getTokenExpiryDateByUser(user);
        
    //     if (otpToken != Integer.parseInt(otp)) {
    //         String invalidMessage = messages.getMessage("auto.message.invalid", null, locale);
    //         return new ModelAndView("badUser", "message", invalidMessage);
    //     }

    //     if (expiryDate.isAfter(LocalDate.now())) {
    //         String expiryMessage = messages.getMessage("auth.message.expired", null, locale);
    //         return new ModelAndView("badUser", "message", expiryMessage);
    //     }

    //     user.setValidated(true);
    //     dao.updateValidatedUser(user);
    //     return new ModelAndView("index");
    // }

    /**
     * Displays the account screen
     * 
     * @return account.html file
     */
    @GetMapping("account")
    public String account() { 
        return "account";
    }

    /**
     * Displays the home page
     * 
     * @return index.html file
     */
    @GetMapping("index")
    public String index() { 
        return "index";
    }
}
