package ua.edu.chdtu.klymchuk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @GetMapping
    public String maim() {
        return "main";
    }
}
