package arctic.example.pi.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/cookie")

@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")




public class CookieController {


    @GetMapping("/get")
    public String getCookie(@CookieValue(value = "color",
            defaultValue = "No color found in cookie") String color) {

        return "Sky is: " + color;
    }

    @GetMapping("/set")
    public String setCookie(HttpServletResponse response) {
        // set a new cookie
        Cookie cookie = new Cookie("color", "blue");
        // add cookie in server response
        response.addCookie(cookie);

        return "Beta Cookies";
    }

    @GetMapping("/create")
    @ResponseBody
    public String createCookie(HttpServletResponse response) {

        Cookie cookie = new Cookie("username", "websparrow.org");
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        cookie.setSecure(true); // for https only
        cookie.setHttpOnly(true); // cookie not accessible via JavaScript

        response.addCookie(cookie);

        return "Cookie created successfully!";
    }


    // Header :  Cookie : username=zarga.org
    // http://localhost:9091/cookie/show
    @GetMapping("/show")
    @ResponseBody
    public String showCookie(@CookieValue(value = "username", defaultValue = "none") String username) {

        return "The value of username cookie is " + username;
    }


    @GetMapping("/expiry")
    public String setCookieExpiry(HttpServletResponse response) {

        int cookieAgeInSeconds = 86400;

        Cookie cookie = new Cookie("website", "https://websparrow.org");
        cookie.setMaxAge(cookieAgeInSeconds); // expire in 1 day
        response.addCookie(cookie);

        return "Cookie will expire in " + cookieAgeInSeconds + "seconds.";
    }



    @GetMapping("/delete")
    @ResponseBody
    public String deleteCookie(HttpServletResponse response) {

        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return "Cookie deleted successfully!";
    }

}
