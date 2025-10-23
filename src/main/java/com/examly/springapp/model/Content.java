package com.examly.springapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "content")
@Data
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 255)
    private String title;
    
    @Column(name = "types", nullable = false, length = 50)
    private String type;
    
    @Column(nullable = false, length = 20)
    private String status = "pending";
    
    @Column(length = 1000)
    private String notes;
}