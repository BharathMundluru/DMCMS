package com.example.demo.Controller;

import com.example.demo.Entity.Experiment;
import com.example.demo.Repository.MouseRepository;
import com.example.demo.service.ExperimentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/experiments")
public class ExperimentController {

    private final ExperimentService experimentService;
    private final MouseRepository mouseRepository;

    public ExperimentController(
            ExperimentService experimentService,
            MouseRepository mouseRepository) {

        this.experimentService = experimentService;
        this.mouseRepository = mouseRepository;
    }

    @GetMapping
    public String experiments(Model model) {

        model.addAttribute(
                "experiments",
                experimentService.getAllExperiments()
        );

        return "experiments";
    }

    @GetMapping("/add")
    public String addExperimentPage(Model model) {

        model.addAttribute(
                "experiment",
                new Experiment()
        );

        model.addAttribute(
                "mice",
                mouseRepository.findAll()
        );

        return "add-experiment";
    }

    @PostMapping("/save")
    public String saveExperiment(
            @ModelAttribute Experiment experiment) {

        experimentService.saveExperiment(experiment);

        return "redirect:/experiments";
    }

    @GetMapping("/view/{id}")
    public String viewExperiment(
            @PathVariable Long id,
            Model model) {

        Experiment experiment =
                experimentService.getExperimentById(id);

        model.addAttribute(
                "experiment",
                experiment
        );

        model.addAttribute(
                "mice",
                mouseRepository.findAll()
        );

        return "experiment-details";
    }

    @PostMapping("/update/{id}")
    public String updateExperiment(
            @PathVariable Long id,
            @ModelAttribute Experiment experiment) {

        experimentService.updateExperiment(
                id,
                experiment
        );

        return "redirect:/experiments/view/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteExperiment(
            @PathVariable Long id) {

        experimentService.deleteExperiment(id);

        return "redirect:/experiments";
    }
}