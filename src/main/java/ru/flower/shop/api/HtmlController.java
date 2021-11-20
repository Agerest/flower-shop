package ru.flower.shop.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/basket")
    public String basket() {
        return "basket";
    }

    @GetMapping("/create-flower")
    public String createFlower() {
        return "create-flower";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/flower")
    public String flower() {
        return "flower";
    }

    @GetMapping("/edit-flower")
    public String editFlower() {
        return "edit-flower";
    }
}
