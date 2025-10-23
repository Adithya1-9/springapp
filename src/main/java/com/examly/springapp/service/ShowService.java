package com.examly.springapp.service;

import com.examly.springapp.model.Show;
import com.examly.springapp.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {
    
    @Autowired
    private ShowRepository showRepository;
    
    public Show addShow(Show show) {
        return showRepository.save(show);
    }
    
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }
    
    public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }

    public Show updateShow(Long id, Show show) {
        return showRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(show.getTitle());
                    existing.setGenre(show.getGenre());
                    existing.setReleaseYear(show.getReleaseYear());
                    existing.setRating(show.getRating());
                    existing.setDuration(show.getDuration());
                    return showRepository.save(existing);
                })
                .orElseGet(() -> {
                    show.setId(id);
                    return showRepository.save(show);
                });
    }
}