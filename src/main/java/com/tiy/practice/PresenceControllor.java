package com.tiy.practice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by crci1 on 1/25/2017.
 */

//Register user GET Request
@Controller
public class PresenceControllor {

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session) throws Exception {
        String user = new String();
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String registerUser(Model model, HttpSession session) {

        String  savedUser = (String) session.getAttribute("user");
        if (savedUser != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }


        return "home";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
