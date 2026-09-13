package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Entity.Role;
import org.springframework.ui.Model;
import com.example.demo.Repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DashboardController {

    private final MouseRepository mouseRepository;
    private final ColonyRepository colonyRepository;
    private final CageRepository cageRepository;
    private final BreedingRepository breedingRecordRepository;
    private final HealthRecordRepository healthRecordRepository;
    private final ExperimentRepository experimentRepository;
    private final InventoryRepository inventoryRepository;
    private final UserRepository userRepository;

    public DashboardController(
            MouseRepository mouseRepository,
            UserRepository userRepository,
            ColonyRepository colonyRepository,
            CageRepository cageRepository,
            BreedingRepository breedingRecordRepository,
            HealthRecordRepository healthRecordRepository,
            ExperimentRepository experimentRepository,
            InventoryRepository inventoryRepository) {

        this.mouseRepository = mouseRepository;
        this.userRepository = userRepository;
        this.colonyRepository = colonyRepository;
        this.cageRepository = cageRepository;
        this.breedingRecordRepository = breedingRecordRepository;
        this.healthRecordRepository = healthRecordRepository;
        this.experimentRepository = experimentRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/admin/members")
    public String members(Model model) {
        model.addAttribute("members", userRepository.findAll());
        model.addAttribute("roles", Role.values());
        return "admin-members";
    }

    @PostMapping("/admin/members/{id}/role")
    public String changeRole(
            @PathVariable Long id,
            @RequestParam("role") Role role) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
        user.setRole(role);
        userRepository.save(user);
        return "redirect:/admin/members?success=Role%20updated%20successfully";
    }

    @GetMapping("/researcher")
    public String researcher() {
        return "researcher";
    }

    @GetMapping("/technician")
    public String technician() {
        return "technician";
    }

    @GetMapping("/veterinarian")
    public String veterinarian() {
        return "veterinarian";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalMice", mouseRepository.count());

        model.addAttribute("totalColonies", colonyRepository.count());

        model.addAttribute("totalCages", cageRepository.count());

        model.addAttribute(
                "totalBreedingRecords",
                breedingRecordRepository.count()
        );

        model.addAttribute(
                "totalHealthRecords",
                healthRecordRepository.count()
        );

        model.addAttribute(
                "totalExperiments",
                experimentRepository.count()
        );

        model.addAttribute(
                "totalInventoryItems",
                inventoryRepository.count()
        );

        return "dashboard";
    }
}
