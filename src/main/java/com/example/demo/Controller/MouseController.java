package com.example.demo.Controller;

import com.example.demo.Entity.Mouse;
import com.example.demo.Repository.CageRepository;
import com.example.demo.Repository.ColonyRepository;
import com.example.demo.Repository.MouseRepository;
import com.example.demo.service.MouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mice")
public class MouseController {

    private final MouseService mouseService;
    private final CageRepository cageRepository;
    private final ColonyRepository colonyRepository;
    private final MouseRepository mouseRepository;

    public MouseController(
            MouseService mouseService,
            CageRepository cageRepository,
            ColonyRepository colonyRepository,
            MouseRepository mouseRepository) {

        this.mouseService = mouseService;
        this.cageRepository = cageRepository;
        this.colonyRepository = colonyRepository;
        this.mouseRepository = mouseRepository;
    }

    @GetMapping
    public String mice(Model model) {

        model.addAttribute(
                "mice",
                mouseService.getAllMice()
        );

        return "mice";
    }


    @GetMapping("/add")
    public String addMousePage(Model model) {

        model.addAttribute(
                "mouse",
                new Mouse()
        );

        model.addAttribute(
                "colonies",
                colonyRepository.findAll()
        );

        model.addAttribute(
                "cages",
                cageRepository.findAll()
        );

        model.addAttribute(
                "femaleMice",
                mouseRepository.findByGender("FEMALE")
        );

        model.addAttribute(
                "maleMice",
                mouseRepository.findByGender("MALE")
        );

        return "add-mouse";
    }


    @PostMapping("/save")
    public String saveMouse(
            @ModelAttribute("mouse") Mouse mouse) {

        System.out.println("Mouse ID: " + mouse.getMouseId());
        System.out.println("DOB: " + mouse.getDateOfBirth());

        mouseService.saveMouse(mouse);

        return "redirect:/mice";
    }

    @PostMapping("/update/{id}")
    public String updateMouse(
            @PathVariable Long id,
            @ModelAttribute("mouse") Mouse mouse) {

        mouseService.updateMouse(id, mouse);

        return "redirect:/mice";
    }


    @GetMapping("/view/{id}")
    public String viewMouse(
            @PathVariable Long id,
            Model model) {

        Mouse mouse = mouseService.getMouseById(id);

        model.addAttribute(
                "mouse",
                mouse
        );

        return "mouse-details";
    }


    @GetMapping("/edit/{id}")
    public String editMouse(
            @PathVariable Long id,
            Model model) {

        Mouse mouse = mouseService.getMouseById(id);

        model.addAttribute("mouse", mouse);

        model.addAttribute(
                "colonies",
                colonyRepository.findAll()
        );

        model.addAttribute(
                "cages",
                cageRepository.findAll()
        );

        model.addAttribute(
                "femaleMice",
                mouseRepository.findByGender("FEMALE")
        );

        model.addAttribute(
                "maleMice",
                mouseRepository.findByGender("MALE")
        );

        return "add-mouse";
    }


    @GetMapping("/delete/{id}")
    public String deleteMouse(
            @PathVariable Long id) {

        mouseService.deleteMouse(id);

        return "redirect:/mice";
    }

    @GetMapping("/family/{id}")
    public String familyTree(
            @PathVariable Long id,
            Model model) {

        Mouse mouse = mouseService.getMouseById(id);

        List<Mouse> children =
                mouseService.getChildren(mouse);

        model.addAttribute("mouse", mouse);
        model.addAttribute("children", children);

        return "family-tree";
    }

    @GetMapping("/search/barcode")
    public String searchByBarcode(
            @RequestParam String barcode,
            Model model) {

        List<Mouse> mice = mouseService.searchByBarcode(barcode);

        model.addAttribute("mice", mice);

        return "mice";
    }

    @GetMapping("/search/strain")
    public String searchByStrain(
            @RequestParam String strain,
            Model model) {

        List<Mouse> mice = mouseService.searchByStrain(strain);

        model.addAttribute("mice", mice);

        return "mice";
    }
}