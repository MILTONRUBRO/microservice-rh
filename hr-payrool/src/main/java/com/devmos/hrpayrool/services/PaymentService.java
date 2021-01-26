package com.devmos.hrpayrool.services;

import org.springframework.stereotype.Service;

import com.devmos.hrpayrool.entities.Payment;

@Service
public class PaymentService {
	
	public Payment getPayment(Long workerId, Integer days) {
		return new Payment("Jared", 200.0, days);
	}

}
