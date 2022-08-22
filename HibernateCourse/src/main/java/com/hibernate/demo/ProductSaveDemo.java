package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.mapping.Market;
import com.hibernate.entity.mapping.Product;
import com.hibernate.entity.mapping.Repo;

public class ProductSaveDemo {
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Product.class)
										.addAnnotatedClass(Repo.class)
										.addAnnotatedClass(Market.class)
										.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			//Repo ve market once save olmali
			Repo repo = new Repo("Repo1");
			Market market = new Market("Market1");
			
			session.save(repo);
			session.save(market);
			
			Product product = new Product("Pr1");
			product.setRepo(repo);
			product.setMarket(market);
			
			repo.addProduct(product);
			market.addProduct(product);
			
			session.save(product);
			
			session.getTransaction().commit();
			
		} finally {
			sessionFactory.close();
		}
	}

}

