package com.codegym.controller;

import com.codegym.model.Promotion;
import com.codegym.service.impl.PromotionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/promotions")
public class PromotionController {

    @Autowired
    private PromotionServiceImpl promotionService;

    @GetMapping("")
    public ModelAndView promotionHome() {
        ModelAndView modelAndView = new ModelAndView("/promotion/home");
        Iterable<Promotion> promotions = promotionService.findAll();
        modelAndView.addObject("promotion", promotions);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/promotion/create");
        modelAndView.addObject("promotion", new Promotion());
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Promotion promotion,
                               RedirectAttributes redirect) {
        promotionService.save(promotion);
        redirect.addFlashAttribute("message", "Tạo mới thành công");
        return "redirect:/promotions";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deletePromotion(@PathVariable Long id) {
        Optional<Promotion> promotionOptional = promotionService.findById(id);

        if (promotionOptional.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/promotion/delete");
            modelAndView.addObject("promotion", promotionOptional.get());
            return modelAndView;
        }
        return new ModelAndView("/error");
    }

    @PostMapping("/delete/{id}")
    public String deletePromotion(@PathVariable Long id, RedirectAttributes attributes) {
        promotionService.remove(id);
        attributes.addFlashAttribute("message", "Successfully deleted the Promotion");
        return "redirect:/promotions";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Promotion> promotion = promotionService.findById(id);
        if (promotion.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/promotion/update");
            modelAndView.addObject("promotion", promotion.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("promotion") Promotion promotion,
                         RedirectAttributes redirect) {
        promotionService.save(promotion);
        redirect.addFlashAttribute("message", "Update successfully");
        return "redirect:/promotions";
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewsPromotion(@PathVariable Long id) {
        Optional<Promotion> promotions = promotionService.findById(id);

        if (promotions.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/promotion/views");
            modelAndView.addObject("promotion", promotions.get());
            return modelAndView;
        }
        return new ModelAndView("/error");
    }
}

