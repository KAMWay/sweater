package ua.edu.chdtu.sweater;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.edu.chdtu.sweater.db.User;
import ua.edu.chdtu.sweater.db.UserManager;


@Controller
public class LoginController {

    @GetMapping("/signin")
    public String loginGet() {
        System.out.println("LoginController GetMapping");

        return "login";
    }

    @PostMapping("/signin")
    public @ResponseBody
    String loginPost(@RequestParam String login, @RequestParam String password) {
        System.out.println("LoginController PostMapping");


        UserManager manager = new UserManager();
        User user = manager.select(login, password.hashCode());

        return user == null ? "Sign in OK" : "Not sign in";
    }

}
