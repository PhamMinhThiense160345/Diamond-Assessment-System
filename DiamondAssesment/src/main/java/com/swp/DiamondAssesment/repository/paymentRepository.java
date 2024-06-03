package com.swp.DiamondAssesment.repository;

import com.swp.DiamondAssesment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface paymentRepository extends JpaRepository<Payment,Integer> {
}
