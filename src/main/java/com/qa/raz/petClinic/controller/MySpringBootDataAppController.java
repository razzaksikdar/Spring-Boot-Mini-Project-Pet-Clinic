package com.qa.raz.petClinic.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.qa.raz.petClinic.model.MySpringBootDataModel;
import com.qa.raz.petClinic.repository.MySpringBootRepository;

@RestController
@RequestMapping("/api")
public class MySpringBootDataAppController {
	
	@Autowired
	MySpringBootRepository Repo;

	// method to create a pets 
	@PostMapping("/pets")
	private MySpringBootDataModel createPets(@Valid @RequestBody MySpringBootDataModel mSDM) {
		return Repo.save(mSDM);
	}
	
	
	@GetMapping("/pets/{id}")
	public MySpringBootDataModel getPetsbyID(@PathVariable(value="id")Long petsID){
		
		return Repo.findById(petsID).orElseThrow(()->new ResourceNotFoundException("MySpringBootDataModel", "id", petsID));
	}
	
	
	@GetMapping("/pets")
	public List<MySpringBootDataModel> getAllPets()
	{
		return Repo.findAll();
	}
	
	@PutMapping("/pets/{id}")
	public MySpringBootDataModel updatePets(@PathVariable(value="id")Long petsID,
			@Valid@RequestBody MySpringBootDataModel petsDetails)
	{
		MySpringBootDataModel mSDM = Repo.findById(petsID).orElseThrow(()->new ResourceNotFoundException("MySpringBootDataModel", "id", petsID));
		
		mSDM.setName(petsDetails.getName());
		mSDM.setPetType(petsDetails.getPetType());
		mSDM.setBreed(petsDetails.getBreed());
		mSDM.setAge(petsDetails.getAge());
		
		MySpringBootDataModel updateData = Repo.save(mSDM);
		return updateData;
	}
	
	@DeleteMapping("/pets/{id}")
	public ResponseEntity<?> deletepets(@PathVariable(value="id")Long petsID)
	{
		MySpringBootDataModel mSDM = Repo.findById(petsID).orElseThrow(()->new ResourceNotFoundException("MySpringBootDataModel", "id", petsID));
		
		Repo.delete(mSDM);
		return ResponseEntity.ok().build();
	}
	
	

}
