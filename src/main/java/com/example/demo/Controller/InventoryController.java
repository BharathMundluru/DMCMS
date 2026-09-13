package com.example.demo.Controller;

import com.example.demo.Entity.Inventory;
import com.example.demo.service.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public String inventory(Model model) {

        model.addAttribute(
                "items",
                inventoryService.getAllItems()
        );

        return "inventory";
    }

    @GetMapping("/add")
    public String addInventoryPage(Model model) {

        model.addAttribute(
                "inventory",
                new Inventory()
        );

        return "add-inventory";
    }

    @PostMapping("/save")
    public String saveInventory(
            @ModelAttribute Inventory inventory) {

        inventoryService.saveItem(inventory);

        return "redirect:/inventory";
    }

    @GetMapping("/view/{id}")
    public String viewInventory(
            @PathVariable Long id,
            Model model) {

        Inventory inventory =
                inventoryService.getItemById(id);

        model.addAttribute(
                "inventory",
                inventory
        );

        return "inventory-details";
    }

    @PostMapping("/update/{id}")
    public String updateInventory(
            @PathVariable Long id,
            @ModelAttribute Inventory inventory) {

        inventoryService.updateItem(
                id,
                inventory
        );

        return "redirect:/inventory/view/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteInventory(
            @PathVariable Long id) {

        inventoryService.deleteItem(id);

        return "redirect:/inventory";
    }
}