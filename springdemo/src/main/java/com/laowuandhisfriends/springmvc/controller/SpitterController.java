package com.laowuandhisfriends.springmvc.controller;

import com.laowuandhisfriends.springmvc.entity.Spitter;
import com.laowuandhisfriends.springmvc.service.SpitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
    private final SpitterService spitterService;

    @Autowired
    public SpitterController(SpitterService spitterService) {
        this.spitterService = spitterService;
    }

    @RequestMapping(value = "/{username}/spittles", method = GET)
    public String listSpittlesForSpitter(@PathVariable String username, Model model) {
        Spitter spitter = spitterService.getSpitter(username);
        model.addAttribute(spitter);
        model.addAttribute(spitterService.getSpittlesForSpitter(username));
        return "spittles/list";
    }

    @RequestMapping(method = GET, params = "new")
    public String createSpitterProfile(Model model) {
        model.addAttribute(new Spitter());
        return "spitters/edit";
    }

    @RequestMapping(method = POST)
    public String addSpitterFromForm(@Valid Spitter spitter,
                                     BindingResult bindingResult) {
        System.out.println("@Valid Spitter spitter: " + spitter);
        if (bindingResult.hasErrors()) {
            return "spitters/edit";
        }
        spitterService.saveSpitter(spitter);
        return "redirect:/spitter/" + spitter.getUsername();
    }

    @RequestMapping(value = "/{username}", method = GET)
    public String showSpitterProfile(@PathVariable String username, Model model) {
        model.addAttribute(spitterService.getSpitter(username));
        return "spitters/view";
    }
}
