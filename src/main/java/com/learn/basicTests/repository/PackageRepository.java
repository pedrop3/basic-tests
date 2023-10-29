package com.learn.basicTests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.basicTests.model.PackageGLS;


@Repository
public interface PackageRepository extends JpaRepository<PackageGLS, Long> {

}
