package se.lexicon.teri.shipping_cost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShippingController {

    @GetMapping("/")
    public String showList() {
        return "show-box-list";
    }

    @GetMapping("/add-package")
    public String showForm() {
        return "add-box-form";
    }
}
