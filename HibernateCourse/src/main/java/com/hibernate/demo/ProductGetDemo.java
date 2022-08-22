package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.mapping.Course;
import com.hibernate.entity.mapping.Instructor;
import com.hibernate.entity.mapping.InstructorDetail;
import com.hibernate.entity.mapping.Market;
import com.hibernate.entity.mapping.Product;
import com.hibernate.entity.mapping.Repo;

public class ProductGetDemo {
	
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
			
			//Repo eager, market lazy loading
			Product product = session.get(Product.class, 1);
			System.out.println(product.getProductName());
			System.out.println(product.getMarket().getMarketName());
			session.getTransaction().commit();
			
		} finally {
			sessionFactory.close();
		}
	}

}

