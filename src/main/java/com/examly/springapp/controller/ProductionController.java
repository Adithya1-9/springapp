package com.examly.springapp.controller;

import com.examly.springapp.model.Production;
import com.examly.springapp.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/production")
@CrossOrigin(originPatterns = "*")
public class ProductionController {
    
    @Autowired
    private ProductionService productionService;
    
    @GetMapping("/all")
    public ResponseEntity<List<Production>> getAllProductions() {
        List<Production> productions = productionService.getAllProductions();
        return ResponseEntity.ok(productions);
    }
    
    @PostMapping("/add")
    public ResponseEntity<Production> addProduction(@RequestBody Production production) {
        System.out.println("Received production: " + production);
        Production savedProduction = productionService.addProduction(production);
        return ResponseEntity.ok(savedProduction);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduction(@PathVariable Long id) {
        productionService.deleteProduction(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Production> updateProduction(@PathVariable Long id, @RequestBody Production production) {
        Production updated = productionService.updateProduction(id, production);
        return ResponseEntity.ok(updated);
    }
}