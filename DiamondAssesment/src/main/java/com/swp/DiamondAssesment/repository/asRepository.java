package com.swp.DiamondAssesment.repository;

import com.swp.DiamondAssesment.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;



@Repository
public interface asRepository extends JpaRepository<Assessment,Integer>, JpaSpecificationExecutor<Assessment> {

}