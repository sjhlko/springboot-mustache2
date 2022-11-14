package com.mustache.springbootmustache2.repository;

import com.mustache.springbootmustache2.domain.dto.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital,Integer> {
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);
}
