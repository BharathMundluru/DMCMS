package com.example.demo.Controller;

import com.example.demo.Entity.Breeding;
import com.example.demo.Repository.MouseRepository;
import com.example.demo.service.BreedingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/breeding")
public class BreedingController {

    private final BreedingService breedingService;
    private final MouseRepository mouseRepository;

    public BreedingController(
            BreedingService breedingService,
            MouseRepository mouseRepository) {

        this.breedingService = breedingService;
        this.mouseRepository = mouseRepository;
    }

    @GetMapping
    public String breeding(Model model) {

        model.addAttribute(
                "breedings",
                breedingService.getAllBreedings()
        );

        return "breeding";
    }

    @GetMapping("/add")
    public String addBreedingPage(Model model) {

        model.addAttribute(
                "breeding",
                new Breeding()
        );

        model.addAttribute(
                "maleMice",
                mouseRepository.findByGender("MALE")
        );

        model.addAttribute(
                "femaleMice",
                mouseRepository.findByGender("FEMALE")
        );

        return "add-breeding";
    }

    @GetMapping("/edit/{id}")
    public String editBreedingPage(
            @PathVariable Long id,
            Model model) {

        Breeding breeding =
                breedingService.getBreedingById(id);

        model.addAttribute(
                "breeding",
                breeding
        );

        model.addAttribute(
                "maleMice",
                mouseRepository.findByGender("MALE")
        );

        model.addAttribute(
                "femaleMice",
                mouseRepository.findByGender("FEMALE")
        );

        return "add-breeding";
    }

    @PostMapping("/save")
    public String saveBreeding(
            @ModelAttribute Breeding breeding) {

        breedingService.saveBreeding(breeding);

        return "redirect:/breeding";
    }

    @GetMapping("/view/{id}")
    public String viewBreeding(
            @PathVariable Long id,
            Model model) {

        Breeding breeding =
                breedingService.getBreedingById(id);

        model.addAttribute(
                "breeding",
                breeding
        );

        return "breeding-details";
    }

    @GetMapping("/delete/{id}")
    public String deleteBreeding(
            @PathVariable Long id) {

        breedingService.deleteBreeding(id);

        return "redirect:/breeding";
    }
}