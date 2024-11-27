package project.marketplace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;

import project.marketplace.daos.UBuyCDao;


@Controller
public class UBuyCController {

    private final UBuyCDao dao;

    public UBuyCController(UBuyCDao dao) {
        this.dao = dao;
    }

    @GetMapping({"/", "login"})
    public String login() { 
        return "login";
    }

    @GetMapping("signup")
    public String signup() { 
        return "signup";
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
