package com.example.demo.service;

import com.example.demo.Entity.Mouse;
import com.example.demo.Repository.MouseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MouseService {

    private final MouseRepository mouseRepository;

    public MouseService(MouseRepository mouseRepository) {
        this.mouseRepository = mouseRepository;
    }

    public List<Mouse> getAllMice() {
        return mouseRepository.findAll();
    }

    public Mouse getMouseById(Long id) {
        return mouseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Mouse not found"));
    }

    public Mouse getMouseByMouseId(String mouseId) {
        return mouseRepository.findByMouseId(mouseId)
                .orElseThrow(() ->
                        new RuntimeException("Mouse not found"));
    }

    public Mouse saveMouse(Mouse mouse) {
        return mouseRepository.save(mouse);
    }

    public Mouse updateMouse(Long id, Mouse updatedMouse) {

        Mouse existingMouse = mouseRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Mouse not found with id: " + id
                        )
                );

        existingMouse.setMouseId(updatedMouse.getMouseId());
        existingMouse.setStrain(updatedMouse.getStrain());
        existingMouse.setGender(updatedMouse.getGender());
        existingMouse.setDateOfBirth(updatedMouse.getDateOfBirth());
        existingMouse.setColor(updatedMouse.getColor());
        existingMouse.setStatus(updatedMouse.getStatus());
        existingMouse.setRfid(updatedMouse.getRfid());
        existingMouse.setBarcode(updatedMouse.getBarcode());
        existingMouse.setImageUrl(updatedMouse.getImageUrl());

        existingMouse.setColony(updatedMouse.getColony());
        existingMouse.setCage(updatedMouse.getCage());
        existingMouse.setMother(updatedMouse.getMother());
        existingMouse.setFather(updatedMouse.getFather());

        return mouseRepository.save(existingMouse);
    }

    public void deleteMouse(Long id) {
        mouseRepository.deleteById(id);
    }

    public List<Mouse> getChildren(Mouse mouse) {

        List<Mouse> children = new ArrayList<>();

        children.addAll(mouseRepository.findByMother(mouse));
        children.addAll(mouseRepository.findByFather(mouse));

        return children;
    }

    public List<Mouse> searchByBarcode(String barcode) {
        return mouseRepository.findByBarcodeContainingIgnoreCase(barcode);
    }

    public List<Mouse> searchByStrain(String strain) {
        return mouseRepository.findByStrainContainingIgnoreCase(strain);
    }
}