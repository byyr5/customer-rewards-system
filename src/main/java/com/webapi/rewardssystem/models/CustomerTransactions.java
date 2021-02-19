package com.webapi.rewardssystem.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="CUSTOMERTRANSACTIONS")
public class CustomerTransactions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CUSTOMER_TRANSACTIONS_GEN")
	@SequenceGenerator(name="CUSTOMER_TRANSACTIONS_GEN", sequenceName = "CUSTOMER_TRANSACTIONS_SEQ", allocationSize=1)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="CUSTOMERNAME")
	private String customerName;
	
	@Column(name="ITEMDESCRIPTION")
	private String itemDescription;
	
	@Column(name="PURCHASEPRICE")
	private Integer purchasePrice;
	
	@Column(name="PURCHASEDATE")
	private Date purchaseDate;
	
	private transient Integer rewardPoints;

	public CustomerTransactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerTransactions(Integer id, String customerName, String itemDescription, Integer purchasePrice,
			Date purchaseDate, Integer rewardPoints) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.itemDescription = itemDescription;
		this.purchasePrice = purchasePrice;
		this.purchaseDate = purchaseDate;
		this.rewardPoints = rewardPoints;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Integer getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Integer purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Integer getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	@Override
	public String toString() {
		return customerName + "  " + itemDescription + " " +
				+ purchasePrice +  " " + purchaseDate + " " + rewardPoints ;
	}
}
