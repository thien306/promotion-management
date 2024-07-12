package com.codegym.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @FutureOrPresent(message = "Start Time cannot be in the past")
    @Column(nullable = false, name = "start_time")
    private LocalDateTime startTime;

    @FutureOrPresent(message = "End Time cannot be in the past")
    @Column(nullable = false, name = "end_time")
    private LocalDateTime endTime;

    private BigDecimal price;

    private String detail;

    public Promotion() {
    }
}
