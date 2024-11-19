package project.marketplace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UBuyCController {
    
    @RequestMapping(value="index.html", method = RequestMethod.GET)
    @ResponseBody
    public String index() { 
        return "Your first return";
    }
}
