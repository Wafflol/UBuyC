package project.marketplace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;

import project.marketplace.daos.AccountDao;
import project.marketplace.daos.UserAlreadyExistsException;
import project.marketplace.models.User;


@Controller
public class UBuyCController {

    private final AccountDao dao;

    public UBuyCController(AccountDao dao) {
        this.dao = dao;
    }

    @GetMapping({"/", "login"})
    public String login() { 
        return "login";
    }

    @GetMapping("/signup")
    public String loadSignupPage(WebRequest request, Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute("user") @Valid User user, HttpServletRequest request, Error errors) { 
        
        try {
            dao.createUser(user);
        } catch (UserAlreadyExistsException e) {
            return new ModelAndView().addObject("message", "An account for that email already exists.");
        }
        return new ModelAndView("verification", "user", user);
        
    }

    @GetMapping("account")
    public String account() { 
        return "account";
    }

    @GetMapping("verification")
    public String verification() { 
        return "verification";
    }

    @GetMapping("index")
    public String index() { 
        return "index";
    }

    // @GetMapping("home")
    // public String home(ModelMap modelMap){
    //     modelMap.addAttribute("email", dao.getEmail());
    //     return "home";
    // }
    
}
