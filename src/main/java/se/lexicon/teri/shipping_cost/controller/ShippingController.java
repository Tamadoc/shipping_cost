package se.lexicon.teri.shipping_cost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.lexicon.teri.shipping_cost.entity.Box;
import se.lexicon.teri.shipping_cost.repository.BoxRepository;

@Controller
public class ShippingController {

    private BoxRepository boxRepository;

    @Autowired
    public void setBoxRepository(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }

    @GetMapping("/")
    public String showList() {
        return "show-box-list";
    }

    @GetMapping("/add-form")
    public String showForm(Model model) {
        Box box = new Box();
        model.addAttribute("box", box);
        return "add-box-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("box") Box box, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "add-box-form";
        }

        boxRepository.save(box);
        attributes.addFlashAttribute("message", "Operation is done. Tracking code: " + box.getId());
        attributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/";
    }

}
