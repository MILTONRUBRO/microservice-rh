package com.devmos.hrworker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devmos.hrworker.entities.Worker;
import com.devmos.hrworker.repositories.WorkerRepository;

@RestController
@RequestMapping("api/workers")
public class WorkerController {
	
	private WorkerRepository workerRepository;
	
	@Autowired
	public WorkerController(WorkerRepository workerRepository) {
		this.workerRepository = workerRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<Worker>> findAll(){
		List<Worker> workers =  workerRepository.findAll();
		return ResponseEntity.ok(workers);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Worker> findById(@PathVariable("id") Long id){
		Optional<Worker> possibleWorker = workerRepository.findById(id);
		
		if(!possibleWorker.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(possibleWorker.get());
	}

}
