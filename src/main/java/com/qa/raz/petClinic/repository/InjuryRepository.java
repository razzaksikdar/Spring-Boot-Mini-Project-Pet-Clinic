package com.qa.raz.petClinic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.raz.petClinic.model.Injury;



@Repository 
public interface InjuryRepository extends JpaRepository<Injury, Long> {
	Page<Injury> findByPetsId(Long petsId, Pageable pageable);
}
