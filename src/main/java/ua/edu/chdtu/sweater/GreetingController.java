package ua.edu.chdtu.sweater;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {
    @GetMapping("/greeting")
//    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model  ) {
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String, Object> model) {

        System.out.println("GreetingController");

//        model.addAttribute("name", name);
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String maim(Map<String,Object> model) {
        model.put("mainStr", "Hello Loch");
        return "main";
    }
}