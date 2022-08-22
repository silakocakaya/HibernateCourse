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
@Table(name = "repo")
public class Repo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String repoName;
	
	@OneToMany(mappedBy = "repo", 
			cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private List<Product> productList;
	
	public Repo() {
		super();
	}

	public Repo(String repoName) {
		super();
		this.repoName = repoName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRepoName() {
		return repoName;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	@Override
	public String toString() {
		return "Repo [id=" + id + ", repoName=" + repoName + "]";
	}
	
	public void addProduct(Product product) {
		
		if(productList == null) 
			productList = new ArrayList<Product>();
		productList.add(product);
	}
	
}
