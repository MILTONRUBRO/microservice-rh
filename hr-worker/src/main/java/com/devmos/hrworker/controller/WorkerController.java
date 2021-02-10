package com.devmos.hrworker.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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
	
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(WorkerController.class);
	
	@Value("${test.config}")
	private String testConfig;
	
	@Autowired
	private Environment enviroment;
	private WorkerRepository workerRepository;
	
	@Autowired
	public WorkerController(WorkerRepository workerRepository) {
		this.workerRepository = workerRepository;
	}
	
	@GetMapping(value = "/configs")
	public ResponseEntity<Void> getConfigs(){
		logger.info("CONFIG = " + testConfig );
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Worker>> findAll(){
		List<Worker> workers =  workerRepository.findAll();
		return ResponseEntity.ok(workers);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Worker> findById(@PathVariable("id") Long id){
		logger.info("PORT = " + enviroment.getProperty("local.server.port"));
		
		Optional<Worker> possibleWorker = workerRepository.findById(id);
		
		if(!possibleWorker.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(possibleWorker.get());
	}

}
