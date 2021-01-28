package com.devmos.hrpayrool.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.devmos.hrpayrool.entities.Payment;
import com.devmos.hrpayrool.entities.Worker;

@Service
public class PaymentService {
	
	@Value("${hr-worker.host}")
	private String workerHost;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Payment getPayment(Long workerId, Integer days) {		
		Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, getUriVariables(workerId));
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}

	private Map<String, String> getUriVariables(Long workerId) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", String.valueOf(workerId));
		
		return uriVariables;
	}

}
