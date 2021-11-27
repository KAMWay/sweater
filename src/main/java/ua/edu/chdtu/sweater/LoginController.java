package ua.edu.chdtu.sweater;

import db.DBUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        System.out.println("LoginController");

        Connection connection;
        try {
            connection = DBUtils.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        model.addAttribute("name", name);
        return "login";
    }

}
