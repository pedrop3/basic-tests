package com.learn.basicTests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.basicTests.model.Tracking;


@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {

}
