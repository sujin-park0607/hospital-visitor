package com.example.hospitalvisitor.repository;

import com.example.hospitalvisitor.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
