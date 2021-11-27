package ua.edu.chdtu.sweater;

import db.DBUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
//    @RequestMapping(value="/registration",method=RequestMethod.GET)
    public String registration(@RequestParam(name = "jsonData", required = false, defaultValue = "Rezult:") String name, Map<String, Object> model) {

        System.out.println("RegistrationController GetMapping");

        model.put("jsonData", name);

        return "registration";
    }


@PostMapping("/registration")
//@ResponseStatus(HttpStatus.CREATED)
//@RequestMapping(value="/registration",method=RequestMethod.POST)
    public String setJsonData(@RequestParam String fullName, @RequestParam String email, @RequestParam String phoneCountry,
                              @RequestParam String phone, @RequestParam String job, @RequestParam String password,
                              Map<String, Object> model) {

        System.out.println("RegistrationController PostMapping");

        StringBuilder jsonData = new StringBuilder();
        jsonData.append("{");
        jsonData.append("\"fullName\": \"").append(fullName).append("\",");
        jsonData.append("\"email\": \"").append(email).append("\",");
        jsonData.append("\"phone\": \"").append(phoneCountry).append(phone).append("\",");
        jsonData.append("\"job\": \"").append(job).append("\",");
        jsonData.append("\"password\": \"").append(password).append("\"");
        jsonData.append("}");

        model.put("jsonData", jsonData.toString());

        return "registration";
    }


    public String convertDataToJSON(HttpServletRequest req) {
        StringBuilder jsonData = new StringBuilder();
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String phoneCountry = req.getParameter("phoneCountry");
        String phone = req.getParameter("phone");
        String job = req.getParameter("job");
        String password = req.getParameter("password");

        jsonData.append("{");
        jsonData.append("\"fullName\": \"").append(fullName).append("\",");
        jsonData.append("\"email\": \"").append(email).append("\",");
        jsonData.append("\"phone\": \"").append(phoneCountry).append(phone).append("\",");
        jsonData.append("\"job\": \"").append(job).append("\",");
        jsonData.append("\"password\": \"").append(password).append("\"");
        jsonData.append("}");

        return jsonData.toString();
    }


}
