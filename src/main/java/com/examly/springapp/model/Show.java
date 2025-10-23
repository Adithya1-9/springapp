package com.examly.springapp.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "shows")
@Data
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String genre;
    
    @JsonProperty("release_year")
    @Column(name = "release_year")
    private Integer releaseYear;
    
    private Integer rating;
    private Integer duration;
}