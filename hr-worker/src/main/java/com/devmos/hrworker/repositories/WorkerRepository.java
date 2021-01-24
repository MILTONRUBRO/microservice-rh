package com.devmos.hrworker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmos.hrworker.entities.Worker;

public interface WorkerRepository  extends JpaRepository<Worker, Long>{

}
