package com.webapi.rewardssystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapi.rewardssystem.models.CustomerTransactions;
import com.webapi.rewardssystem.repositories.RewardsCalculatorRepository;
import com.webapi.rewardssystem.services.RewardsCalculatorService;

@RestController
@RequestMapping("/rewardsCalculator")
public class RewardsCalculatorController {
	
	@Autowired
	RewardsCalculatorService rewardsCalculatorService;
	
	@GetMapping
	public List<CustomerTransactions> getCustomerTransactionsOrdered() {
		return rewardsCalculatorService.getCustomerTransactionsOrdered();
	}

}
