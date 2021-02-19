package com.webapi.rewardssystem.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webapi.rewardssystem.models.CustomerTransactions;
import com.webapi.rewardssystem.services.RewardsCalculatorService;

@Repository
public interface RewardsCalculatorRepository extends JpaRepository<CustomerTransactions, Integer> {
	
//	@Query(value="SELECT * FROM CUSTOMERTRANSACTIONS WHERE PURCHASEDATE >= TO_DATE('23-APR-2020') - 90 ORDER BY CUSTOMERNAME, PURCHASEDATE DESC", nativeQuery=true)
	@Query("SELECT cp FROM CustomerTransactions cp WHERE cp.purchaseDate >= TO_DATE('23-APR-2020') - 90 ORDER BY cp.customerName, cp.purchaseDate DESC")
	public List<CustomerTransactions> getCustomerTransactionsOrdered();

	

}
