package project.marketplace.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;

import project.marketplace.daos.AccountDao;
import project.marketplace.daos.ListingDao;
import project.marketplace.daos.UserAlreadyExistsException;
import project.marketplace.models.Listing;
import project.marketplace.models.Login;
import project.marketplace.models.User;
import project.marketplace.registration.OnRegistrationCompleteEvent;

/**
 * Creates a class for controller of the entire website. Controls the I/O of each page.
 */
@Controller
@SessionAttributes("user")
public class UBuyCController {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    private MessageSource messages;

    @ModelAttribute("user")
    public User createUserModel() {
        return new User();
    }

    private final AccountDao dao;
    private final ListingDao listingDao;

    public UBuyCController(AccountDao dao, ListingDao listingDao) {
        this.dao = dao;
        this.listingDao = listingDao;
    }

    /**
     * Displays the login screen
     * 
     * @return login.html file
     */
    @GetMapping({"/", "/login"})
    public String loadLoginPage(Model model) { 
        Login login = new Login();
        model.addAttribute("login", login);
        return "login";
    }

    @PostMapping({"/", "/login"})
    public String login(@ModelAttribute("login") @Valid Login login, HttpServletRequest request, Model model) {
        if (dao.checkPassword(login) && dao.checkValidation(login)) {
            return "redirect:/index";
        } else if (dao.checkPassword(login) && !dao.checkValidation(login)) {
            User user = dao.getUser(login);
            model.addAttribute("user", user);
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale()));
            return "redirect:/verification";
        } else {
            model.addAttribute("message", "Invalid email or password");
            return "login";
        }
    }

    /**
     * Displays the signup screen and binds the User object to the form
     * @return signup.html file
     */
    @GetMapping("/signup")
    public ModelAndView loadSignupPage() throws InvalidEmailException {
        User user = new User();
        ModelAndView model = new ModelAndView("signup", "user", user);
        return model;
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
     * @return verification.html on success or emailError.html otherwise
     */
    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute("user") @Valid User user, HttpServletRequest request, Error errors) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.ubc\\.ca$";
        if (!user.getEmail().matches(regex)) {
            System.out.println("Bad email entered, doesn't end in ubc.ca, or doesn't match an email format");
            return new ModelAndView().addObject("message", "Email does not match @_.ubc.ca");
        }
        try {
            User registeredUser = dao.createUser(user);
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser, request.getLocale()));
        } catch (UserAlreadyExistsException e) {
            return new ModelAndView().addObject("message", "An account for that email already exists.");
        } catch (RuntimeException e) {
            System.out.println(e);
            return new ModelAndView("emailError", "user", user); 
        }
        return new ModelAndView("redirect:/verification", "user", user);
        
    }

    /** Displays the verification screen
     * 
     * @return verification.html
     */
    @GetMapping("/verification")
    public ModelAndView loadVerificationPage(@ModelAttribute("user") @Valid User user) { 
        String otp = new String();
        ModelAndView model = new ModelAndView("verification");
        model.addObject("otp", otp);
        model.addObject("user", user);
        return model;
    }

    /**
     * Checks if the OTP entered by the user matches any token in the database that is linked to their email
     */
    @PostMapping("/verification")
    public ModelAndView verifyOtp(@ModelAttribute("otp") @Valid String otp, @ModelAttribute("user") @Valid User user, WebRequest request) {
        Locale locale = request.getLocale();
        int otpToken = dao.getOtpByUser(user);
        LocalDate expiryDate = dao.getTokenExpiryDateByUser(user);
        System.out.println("verifyOTP: otp = " + otp);
        System.out.println("verifyOTP: otpToken = " + otpToken);
        System.out.println("verifyOTP: expiryDate = " + expiryDate);
        System.out.println("verifyOTP: currentDate = " + LocalDate.now());
        System.out.println("verifyOTP: user.email = " + user.getEmail());
        System.out.println("verifyOTP: user.validated (before) = " + user.getValidation());

        if (otpToken != Integer.parseInt(otp)) {
            //String invalidMessage = messages.getMessage("auto.message.invalid", null, locale);
            return new ModelAndView("badUser", "message", "invalid OTP!");
        }

        if (expiryDate.isBefore(LocalDate.now())) { // TODO: make it so its not date but rather time
            //String expiryMessage = messages.getMessage("auth.message.expired", null, locale);
            return new ModelAndView("badUser", "message", "OTP is expired!");
        }
        
        user.setValidated(true);
        System.out.println("verifyOTP: user.validated (after) = " + user.getValidation());
        dao.updateValidatedUser(user);
        return new ModelAndView("redirect:/index", "user", user);
    }

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
    @GetMapping("/index")
    public String index(Model model) { 
        Listing listing = new Listing();
        model.addAttribute("listing", listing);
        return "index";
    }

    /**
     * Posts a request to the database upon valid listing form completion.
     * 
     * @param user The user currently in session
     * @param listing The listing to be added
     * @return index.html file
     */
    @PostMapping("/index")
    public String createNewListing(@ModelAttribute("user") User user, @ModelAttribute("listing") Listing listing) {
        System.out.println("createNewListing: listing = " + listing);
        System.out.println("createNewListing: user.email = " + user.getEmail());
        listing.setEmail(user.getEmail());
        listing.setListingAge(LocalDateTime.now());
        listingDao.createListing(listing);
        return "index";
    }
}
