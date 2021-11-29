package ua.edu.chdtu.klymchuk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.edu.chdtu.klymchuk.db.User;
import ua.edu.chdtu.klymchuk.db.UserManager;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginGet() {
        System.out.println("LoginController GetMapping");

        return "login";
    }

    @PostMapping("/login")
    public @ResponseBody
    String loginPost(@RequestParam String username, @RequestParam String password) {
        System.out.println("LoginController PostMapping");

        UserManager manager = new UserManager();
        User user = manager.select(username, password.hashCode());

        return user != null ? "Sign in OK" : "Not sign in";
    }

}
