package com.example.demo.Controller;

import com.example.demo.Entity.Cage;
import com.example.demo.service.CageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cages")
public class CageController {

    private final CageService cageService;

    public CageController(CageService cageService) {
        this.cageService = cageService;
    }

    // Cage list
    @GetMapping
    public String cages(Model model) {

        model.addAttribute(
                "cages",
                cageService.getAllCages()
        );

        return "cages";
    }

    // Add cage page
    @GetMapping("/add")
    public String addCagePage(Model model) {

        model.addAttribute(
                "cage",
                new Cage()
        );

        return "add-cage";
    }

    // Save cage
    @PostMapping("/save")
    public String saveCage(
            @ModelAttribute Cage cage) {

        cageService.saveCage(cage);

        return "redirect:/cages";
    }

    // View cage
    @GetMapping("/view/{id}")
    public String viewCage(
            @PathVariable Long id,
            Model model) {

        Cage cage = cageService.getCageById(id);

        model.addAttribute(
                "cage",
                cage
        );

        return "cage-details";
    }

    // Update cage
    @PostMapping("/update/{id}")
    public String updateCage(
            @PathVariable Long id,
            @ModelAttribute Cage cage) {

        cageService.updateCage(id, cage);

        return "redirect:/cages/view/" + id;
    }

    // Delete cage
    @GetMapping("/delete/{id}")
    public String deleteCage(
            @PathVariable Long id) {

        cageService.deleteCage(id);

        return "redirect:/cages";
    }
}