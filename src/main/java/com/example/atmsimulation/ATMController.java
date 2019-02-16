package com.example.atmsimulation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
public class ATMController {

    @Autowired
    ATMService atmService;

    @GetMapping("/")
    public String homePage(Model model) {
        return "home";
    }

    @GetMapping("/initial-note")
    public String homePage(Model model, HttpSession session) {
        ATM atm = null;
        if(session.getAttribute("atm") == null){
            atm = new ATM();
            session.setAttribute("atm", atm);
        }else{
            atm = (ATM)session.getAttribute("atm") ;
        }
        model.addAttribute("atm", atm);
        return "initial-note";
    }

    @GetMapping("/withdrawn")
    public String withdrawn( HttpSession session){
        if(session.getAttribute("atm") == null){
            session.setAttribute("atm", new ATM());
        }
        return "withdrawn";
    }

    @PostMapping("/withdrawn-result")
    public String withdrawnResult(Model model, @RequestParam int amount, HttpSession session) throws ATMException {
        try{
            ATM atm = (ATM) session.getAttribute("atm");
            this.atmService.withdraw(atm, new BigDecimal(amount));
            model.addAttribute("atm", atm);
            session.setAttribute("atm", atm);
            return "withdrawn-result";
        }catch (ATMException e){
            model.addAttribute("errorMsg", e.getMessage());
            return "withdrawn-error";
        }
    }

    @PostMapping("/initial-note-result")
    public String greetingSubmit(Model model, @ModelAttribute ATM atm, HttpSession session) {
        model.addAttribute("atm", atm);
        session.setAttribute("atm", atm);
        return "initial-note-result";
    }


}
