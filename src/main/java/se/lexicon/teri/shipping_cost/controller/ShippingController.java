package se.lexicon.teri.shipping_cost.controller;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.lexicon.teri.shipping_cost.entity.Box;
import se.lexicon.teri.shipping_cost.exception.ArgumentException;
import se.lexicon.teri.shipping_cost.service.BoxService;

import javax.validation.Valid;

@Controller
@RequestMapping("/shipping")
public class ShippingController {

    private BoxService boxService;

    @Autowired
    public void setBoxService(BoxService boxService) {
        this.boxService = boxService;
    }

    @GetMapping("/")
    public String showList(Model model) {
        model.addAttribute("boxes", boxService.getAll());
        return "show-box-list";
    }

    @GetMapping("/add-form")
    public String showForm(Model model) {
        Box box = new Box();
        model.addAttribute("box", box);
        return "add-box-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("box") @Valid Box box, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "add-box-form";
        }

        box.setCost(box.calcShippingCost());
//        box = null;
        boxService.save(box);

        return "redirect:/shipping/";
    }

}
