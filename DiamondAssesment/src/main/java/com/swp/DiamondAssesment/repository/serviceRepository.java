package com.swp.DiamondAssesment.repository;

import com.swp.DiamondAssesment.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface serviceRepository extends JpaRepository<Service,Integer> {
}
