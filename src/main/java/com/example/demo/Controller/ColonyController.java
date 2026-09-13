package com.example.demo.Controller;

import com.example.demo.Entity.Colony;
import com.example.demo.service.ColonyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/colonies")
public class ColonyController {

    private final ColonyService colonyService;

    public ColonyController(ColonyService colonyService) {
        this.colonyService = colonyService;
    }

    @GetMapping
    public String colonies(Model model) {

        model.addAttribute(
                "colonies",
                colonyService.getAllColonies()
        );

        return "colonies";
    }


    @GetMapping("/add")
    public String addColonyPage(Model model) {

        model.addAttribute(
                "colony",
                new Colony()
        );

        return "add-colony";
    }


    @GetMapping("/edit/{id}")
    public String editColony(
            @PathVariable Long id,
            Model model) {

        Colony colony = colonyService.getColonyById(id);

        model.addAttribute(
                "colony",
                colony
        );

        return "add-colony";
    }


    @PostMapping("/save")
    public String saveColony(
            @ModelAttribute Colony colony) {

        colonyService.saveColony(colony);

        return "redirect:/colonies";
    }


    @GetMapping("/view/{id}")
    public String viewColony(
            @PathVariable Long id,
            Model model) {

        Colony colony = colonyService.getColonyById(id);

        model.addAttribute(
                "colony",
                colony
        );

        return "colony-details";
    }


    @GetMapping("/delete/{id}")
    public String deleteColony(
            @PathVariable Long id) {

        colonyService.deleteColony(id);

        return "redirect:/colonies";
    }
}