package com.qa.raz.petClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.raz.petClinic.model.MySpringBootDataModel;

@Repository 
public interface MySpringBootRepository extends JpaRepository<MySpringBootDataModel, Long>{
public MySpringBootDataModel findByName(String name);
}
