package com.example.demo2.controller.student;

import com.example.demo2.domian.User;
import com.example.demo2.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Map;

@Controller
@RequestMapping("/student_form")
public class FormController {
    @Autowired
    private UserService userService;

    @PostMapping("update_password")
    public String password(String id, String newPassword, String reNewPassword, Model model, HttpServletResponse response) throws IOException {
        if(newPassword.equals(reNewPassword)){
            User byid = userService.findByid(Long.parseLong(id));
            Map<String, Object> map = userService.updateUser(Long.parseLong(id), byid.getUsername(), newPassword, reNewPassword, byid.getEmail(), String.valueOf(byid.getType()), String.valueOf(byid.getCreateTime()));
            model.addAttribute("error3", map.get("error"));
            if ((boolean) map.get("ok") == false) {
                return "studentHTML/password";
            }
        }
        PrintWriter out = response.getWriter();
        out.print("<script>window.parent.location.href='/user/login';</script>");
        out.flush();
        out.close();
        return "redirect:/user/login";
    }
}
