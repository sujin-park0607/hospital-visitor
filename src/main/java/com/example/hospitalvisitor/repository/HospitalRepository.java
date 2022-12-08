package com.example.hospitalvisitor.repository;

import com.example.hospitalvisitor.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
