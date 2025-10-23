package com.examly.springapp.controller;

import com.examly.springapp.model.Show;
import com.examly.springapp.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
@CrossOrigin(originPatterns = "*")
public class ShowController {
    
    @Autowired
    private ShowService showService;
    
    @PostMapping("/addShow")
    public ResponseEntity<Show> addShow(@RequestBody Show show) {
        // Input validation
        if (show.getTitle() == null || show.getTitle().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (show.getGenre() == null || show.getGenre().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        System.out.println("Received show: " + show);
        Show savedShow = showService.addShow(show);
        return ResponseEntity.ok(savedShow);
    }
    
    @GetMapping("/allShows")
    public ResponseEntity<List<Show>> getAllShows() {
        List<Show> shows = showService.getAllShows();
        return ResponseEntity.ok(shows);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Long id, @RequestBody Show show) {
        Show updated = showService.updateShow(id, show);
        return ResponseEntity.ok(updated);
    }
}