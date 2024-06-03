package com.swp.DiamondAssesment.repository;

import com.swp.DiamondAssesment.model.AssessmentRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface asrRepository extends JpaRepository<AssessmentRequests,Integer> {
}
