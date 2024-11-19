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

    @RequestMapping(value="/index", method = RequestMethod.GET)
    @ResponseBody
    public String index() { 
        return "Your first return";
    }

    @GetMapping("home")
    public String home(ModelMap modelMap){
        modelMap.addAttribute("email", dao.getEmail());
        return "home";
    }
    
}
