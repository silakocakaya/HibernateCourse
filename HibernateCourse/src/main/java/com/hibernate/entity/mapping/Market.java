package com.hibernate.entity.mapping;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "market")
public class Market {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String marketName;
	
	@OneToMany(mappedBy = "market", 
			cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private List<Product> productList;
	
	public Market() {
		super();
	}

	public Market(String marketName) {
		super();
		this.setMarketName(marketName);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public void addProduct(Product product) {
		
		if(productList == null) 
			productList = new ArrayList<Product>();
		productList.add(product);
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
}
