package com.webapi.rewardssystem.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapi.rewardssystem.models.CustomerTransactions;
import com.webapi.rewardssystem.repositories.RewardsCalculatorRepository;

@Service
public class RewardsCalculatorService {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(RewardsCalculatorService.class);
	
	@Autowired
	RewardsCalculatorRepository rewardsCalculatorRepository;

	public List<CustomerTransactions> getCustomerTransactionsOrdered() {
		List<CustomerTransactions> customerTransactions = rewardsCalculatorRepository.getCustomerTransactionsOrdered();
		
		String tempCustomerName = null;
		int totalCustomerRewardPoints = 0;
		Integer tempMonth = null;
		int totalCustomerRewardPointsPerMonth = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

		for(CustomerTransactions customerTransaction: customerTransactions) {
			rewardsCalculation(customerTransaction);
			int purchaseDateMonth = getMonthFromDate(customerTransaction.getPurchaseDate());
			
			if(tempCustomerName == null) {
				tempCustomerName = customerTransaction.getCustomerName();				
			} 
			if(tempCustomerName.equals(customerTransaction.getCustomerName())) {
				if(tempMonth == null) {
					tempMonth = purchaseDateMonth;
				}
				if(tempMonth == purchaseDateMonth) {
					totalCustomerRewardPointsPerMonth = totalCustomerRewardPointsPerMonth + customerTransaction.getRewardPoints();
				} else {
					logger.info("** {} total reward points for month  {} is {} ", tempCustomerName, tempMonth + 1, totalCustomerRewardPointsPerMonth); 
							tempMonth = purchaseDateMonth;
							totalCustomerRewardPointsPerMonth = customerTransaction.getRewardPoints();
					tempMonth = purchaseDateMonth;
					totalCustomerRewardPointsPerMonth = customerTransaction.getRewardPoints();

				}	
				totalCustomerRewardPoints = totalCustomerRewardPoints + customerTransaction.getRewardPoints();
			} else {
				logger.info("** {} total reward points for month  {} is {} ", tempCustomerName, tempMonth + 1, totalCustomerRewardPointsPerMonth);
				logger.info("**** {} total points over the course of three months is {}", tempCustomerName, totalCustomerRewardPoints); 
				
				tempCustomerName = customerTransaction.getCustomerName();
				tempMonth = purchaseDateMonth;
				
				totalCustomerRewardPoints = customerTransaction.getRewardPoints();
				totalCustomerRewardPointsPerMonth = customerTransaction.getRewardPoints();
			}
		}
		logger.info("** {} total reward points for month  {} is  {} ", tempCustomerName, tempMonth + 1, totalCustomerRewardPointsPerMonth);
		logger.info("****** {} total points over the course of three months is {}", tempCustomerName, totalCustomerRewardPoints); 
		
		return customerTransactions;
	}
	
	private void rewardsCalculation(final CustomerTransactions customerTransaction) {
		int purchaseAmount = customerTransaction.getPurchasePrice();
		if (purchaseAmount > 100) {
			customerTransaction.setRewardPoints(50 + (purchaseAmount-100)*2);
		} else if(purchaseAmount > 50) {
			customerTransaction.setRewardPoints(purchaseAmount - 50);
		} else {
			customerTransaction.setRewardPoints(0);
		}
		
	}

	private int getMonthFromDate(final Date date) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}

}
