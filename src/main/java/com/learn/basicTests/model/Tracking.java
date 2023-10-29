package com.learn.basicTests.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Tracking {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private PackageGLS packageGls;

    private LocalDateTime regisitred;

    private LocalDateTime lastUpdate;
}