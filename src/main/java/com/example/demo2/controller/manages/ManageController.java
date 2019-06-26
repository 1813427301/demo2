package com.example.demo2.controller.manages;

import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String managelogin(Model model) {
        model.addAttribute("status", "managelogin");
        return "login";
    }

    @PostMapping("login")
    public String login(String username, String password, Model model, HttpSession session) throws IOException {
        Map<String, Object> map = userService.login(username, password);
        if ((boolean) map.get("ok")) {
            session.setAttribute("user", map.get("user"));
            return "afters/index";
        } else {
            model.addAttribute("error2", map.get("error"));
        }
        model.addAttribute("status", "managelogin");
        return "login";
    }

    @PostMapping("adduser")
    public String adduser(String username, String email, String pass, String repass, String city, Model model ,HttpServletResponse response) throws IOException {
        Map<String, Object> map = userService.create(username, pass, repass, email, city);
        model.addAttribute("error3", map.get("error"));
        if ((boolean) map.get("ok") == false) {
            return "afters/adduser";
        }
        PrintWriter out = response.getWriter();
        out.print("<script>window.parent.location.href='/afterss/userlist';</script>");
        out.flush();
        return "/afterss/userlist";
    }

    @PostMapping("updateuser")
    public String updateuser(String id,String username, String email, String pass, String repass, String city, String date,Model model ,HttpServletResponse response) throws IOException {
        Map<String, Object> map = userService.updateUser(Long.parseLong(id),username, pass, repass, email, city,date);
        model.addAttribute("error3", map.get("error"));
        if ((boolean) map.get("ok") == false) {
            return "afters/updateuser";
        }
        PrintWriter out = response.getWriter();
        out.print("<script>window.parent.location.href='/afterss/userlist';</script>");
        out.flush();
        out.close();
        return "/afterss/userlist";
    }

}
