package com.example.demo.service;

import com.example.demo.Entity.Inventory;
import com.example.demo.Repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAllItems() {
        return inventoryRepository.findAll();
    }

    public Inventory getItemById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Inventory item not found"));
    }

    public Inventory saveItem(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateItem(
            Long id,
            Inventory updatedInventory) {

        Inventory existing = getItemById(id);

        existing.setItemName(updatedInventory.getItemName());
        existing.setQuantity(updatedInventory.getQuantity());
        existing.setMinimumQuantity(
                updatedInventory.getMinimumQuantity()
        );
        existing.setUnit(updatedInventory.getUnit());

        return inventoryRepository.save(existing);
    }

    public void deleteItem(Long id) {
        inventoryRepository.deleteById(id);
    }
}