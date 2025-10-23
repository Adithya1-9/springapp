package com.examly.springapp.service;

import com.examly.springapp.model.Content;
import com.examly.springapp.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {
    
    @Autowired
    private ContentRepository contentRepository;
    
    public Content addContent(Content content) {
        return contentRepository.save(content);
    }
    
    public List<Content> getAllContent() {
        return contentRepository.findAll();
    }
    
    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }

    public Content updateContent(Long id, Content content) {
        return contentRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(content.getTitle());
                    existing.setType(content.getType());
                    existing.setStatus(content.getStatus());
                    existing.setNotes(content.getNotes());
                    return contentRepository.save(existing);
                })
                .orElseGet(() -> {
                    content.setId(id);
                    return contentRepository.save(content);
                });
    }
}