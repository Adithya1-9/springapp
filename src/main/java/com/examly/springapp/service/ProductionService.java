package com.examly.springapp.service;

import com.examly.springapp.model.Production;
import com.examly.springapp.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionService {
    
    @Autowired
    private ProductionRepository productionRepository;
    
    public Production addProduction(Production production) {
        return productionRepository.save(production);
    }
    
    public List<Production> getAllProductions() {
        return productionRepository.findAll();
    }
    
    public void deleteProduction(Long id) {
        productionRepository.deleteById(id);
    }

    public Production updateProduction(Long id, Production production) {
        return productionRepository.findById(id)
                .map(existing -> {
                    existing.setShowTitle(production.getShowTitle());
                    existing.setPhase(production.getPhase());
                    existing.setStartDate(production.getStartDate());
                    existing.setEndDate(production.getEndDate());
                    existing.setBudget(production.getBudget());
                    existing.setCrew(production.getCrew());
                    return productionRepository.save(existing);
                })
                .orElseGet(() -> {
                    production.setId(id);
                    return productionRepository.save(production);
                });
    }
}