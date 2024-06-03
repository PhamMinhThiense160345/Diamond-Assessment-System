package com.swp.DiamondAssesment.repository;

import com.swp.DiamondAssesment.model.AssessmentRequestsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface asrDetailRepository extends JpaRepository<AssessmentRequestsDetail,Integer> {
}
