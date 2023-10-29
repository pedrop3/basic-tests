package com.learn.basicTests.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageGLS {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Person sender;
    
    @ManyToOne
    private Person recipient;

    private Double price;

    @Transient
    Long recipientId;

    @Transient
    Long senderId;
    
}
