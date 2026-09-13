package com.example.demo.Controller;

import com.example.demo.Entity.HealthRecord;
import com.example.demo.Repository.MouseRepository;
import com.example.demo.service.HealthRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/health")
public class HealthRecordController {

    private final HealthRecordService healthRecordService;
    private final MouseRepository mouseRepository;

    public HealthRecordController(
            HealthRecordService healthRecordService,
            MouseRepository mouseRepository) {

        this.healthRecordService = healthRecordService;
        this.mouseRepository = mouseRepository;
    }

    @GetMapping
    public String health(Model model) {

        model.addAttribute(
                "healthRecords",
                healthRecordService.getAllHealthRecords()
        );

        return "health";
    }

    @GetMapping("/add")
    public String addHealthPage(Model model) {

        model.addAttribute(
                "healthRecord",
                new HealthRecord()
        );

        model.addAttribute(
                "mice",
                mouseRepository.findAll()
        );

        return "add-health";
    }

    @PostMapping("/save")
    public String saveHealth(
            @ModelAttribute HealthRecord healthRecord) {

        healthRecordService.saveHealthRecord(healthRecord);

        return "redirect:/health";
    }

    @GetMapping("/view/{id}")
    public String viewHealth(
            @PathVariable Long id,
            Model model) {

        HealthRecord healthRecord =
                healthRecordService.getHealthRecordById(id);

        model.addAttribute(
                "healthRecord",
                healthRecord
        );

        return "health-details";
    }

    @GetMapping("/edit/{id}")
    public String editHealthPage(
            @PathVariable Long id,
            Model model) {

        HealthRecord healthRecord =
                healthRecordService.getHealthRecordById(id);

        model.addAttribute(
                "healthRecord",
                healthRecord
        );

        model.addAttribute(
                "mice",
                mouseRepository.findAll()
        );

        return "add-health";
    }

    @PostMapping("/update/{id}")
    public String updateHealth(
            @PathVariable Long id,
            @ModelAttribute HealthRecord healthRecord) {

        healthRecordService.updateHealthRecord(
                id,
                healthRecord
        );

        return "redirect:/health/view/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteHealth(
            @PathVariable Long id) {

        healthRecordService.deleteHealthRecord(id);

        return "redirect:/health";
    }
}