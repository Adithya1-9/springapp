package com.examly.springapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "production")
@Data
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "show_title")
    private String showTitle;
    
    private String phase;
    
    @Column(name = "start_date")
    private String startDate;
    
    @Column(name = "end_date")
    private String endDate;
    
    private Long budget;
    private Integer crew;
}