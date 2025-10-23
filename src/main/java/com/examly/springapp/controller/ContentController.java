package com.examly.springapp.controller;

import com.examly.springapp.model.Content;
import com.examly.springapp.service.ContentService;
import com.examly.springapp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin(originPatterns = "*")
public class ContentController {
    
    @Autowired
    private ContentService contentService;
    
    @Autowired
    private NotificationService notificationService;
    
    @GetMapping("/all")
    public ResponseEntity<List<Content>> getAllContent() {
        List<Content> content = contentService.getAllContent();
        return ResponseEntity.ok(content);
    }
    
    @PostMapping("/add")
    public ResponseEntity<Content> addContent(@RequestBody Content content) {
        System.out.println("Received content: " + content);
        Content savedContent = contentService.addContent(content);
        
        // Create notification
        notificationService.createNotification(
            "New content '" + savedContent.getTitle() + "' has been submitted for review",
            "info",
            1L // Admin user ID
        );
        
        return ResponseEntity.ok(savedContent);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Content> updateContent(@PathVariable Long id, @RequestBody Content content) {
        Content updated = contentService.updateContent(id, content);
        return ResponseEntity.ok(updated);
    }
}