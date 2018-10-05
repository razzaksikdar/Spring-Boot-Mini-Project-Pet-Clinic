package com.qa.raz.petClinic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.raz.petClinic.exception.ResourceNotFoundException;
import com.qa.raz.petClinic.model.Injury;
import com.qa.raz.petClinic.repository.InjuryRepository;
import com.qa.raz.petClinic.repository.MySpringBootRepository;


@RestController
@RequestMapping("/api")
public class PetsController {

	
	@Autowired 
	private InjuryRepository injuryRepo;
	
	@Autowired
	private MySpringBootRepository myRepo;
	
	
	
	@GetMapping("/pets/{petsId}/injuries")
	public Page<Injury> getAllInjurysBypetsId(@PathVariable (value ="pets_id") Long petsId, Pageable pageable)
	{
		return injuryRepo.findByPetsId(petsId, pageable);
	}
	
	
	@PostMapping("/pets/{petsId}/injuries")
	public Injury createComment(@PathVariable (value="petsId") Long petsId,
			@Valid@RequestBody Injury injury)
	{
		return myRepo.findById(petsId).map(myRepo -> {injury.setPets(myRepo);
		return injuryRepo.save(injury);}).orElseThrow(() -> new ResourceNotFoundException("Pets", "id", injury));
	}
	
	
	@PutMapping("/pets/{petsId}/injuries/{injuryId}")
	public Injury updateInjury(@PathVariable (value="petsId") Long petsId,
			@PathVariable(value="injuryId") Long injuryId,
			@Valid@RequestBody Injury injuryRequest)
	{
		if(!myRepo.existsById(petsId))
		{
			throw new ResourceNotFoundException("Pets", "id", injuryRequest);
		}
		return injuryRepo.findById(injuryId).map(injury -> {injury.setTitle(injuryRequest.getTitle());
		return injuryRepo.save(injury);}).orElseThrow(() -> new ResourceNotFoundException("Injury Id", "id", injuryRequest));
	}
	
	@DeleteMapping("/pets/{petsId}/injuries/{injuryId}")
	public ResponseEntity<?> deleteComment (@PathVariable (value="petsId") Long petsId,
			@PathVariable(value="injuryId") Long injuryId)
	{
		if(!myRepo.existsById(petsId))
		{
			throw new ResourceNotFoundException("Pets", "id", petsId);
		}
		return injuryRepo.findById(injuryId).map(injury -> {injuryRepo.delete(injury);
		return ResponseEntity.ok().build();}).orElseThrow(() -> new ResourceNotFoundException("Injury Id", injuryId.toString(), null));

	}
	
	
	
	
	
	
	
}
