package ua.edu.chdtu.klymchuk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.edu.chdtu.klymchuk.db.User;
import ua.edu.chdtu.klymchuk.db.UserManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public String registrationGet() {
        System.out.println("RegistrationController GetMapping");
        return "registration";
    }

    @PostMapping("/registration")
    public @ResponseBody
    String registrationPost(@RequestParam String fullName, @RequestParam String email,
                            @RequestParam String phoneCountry, @RequestParam String phone,
                            @RequestParam String job, @RequestParam String password, @RequestParam String confirm) {

        System.out.println("RegistrationController PostMapping");

        if (!isValid(getPattern("email"), email)) {
            return "Not correct email";
        }
        if (!isValid(getPattern("phone"), phone)) {
            return "Not correct phone number";
        }
        if (!password.equals(confirm) || password.equals("")) {
            return "Not correct password and repeat";
        }

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhoneNumber(phoneCountry + phone);
        user.setJob(job);
        user.setPassword(password.hashCode());

        UserManager manager = new UserManager();

        return manager.insert(user) ? "Sign up OK." : "Not sign up.";
    }

    private boolean isValid(Pattern pattern, String str) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    private Pattern getPattern(String typePattern) {
        return Pattern.compile(getPatternString(typePattern));
    }

    private String getPatternString(String typePattern) {
        switch (typePattern) {
            case "email":
                return "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+.[a-zA-Z]{2,4}$";
            case "phone":
                return "^\\(?(\\d{2})\\)?[- ()*]?(\\d{3})[- ()*]?(\\d{2})?[- ()*]?(\\d{2})$";
            default:
                return "";
        }
    }

}

