package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.mapping.Course;
import com.hibernate.entity.mapping.Instructor;
import com.hibernate.entity.mapping.InstructorDetail;
import com.hibernate.entity.mapping.Product;
import com.hibernate.entity.mapping.Repo;

public class ProductRepoDetachDemo {
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Product.class)
										.addAnnotatedClass(Repo.class)
										.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			
			Product product = new Product("apple");
			Repo repo = new Repo("Repo1");
			product.setRepo(repo);
			
			//TODO SILA
			session.persist(product);
			session.flush();
			
			System.out.println(session.contains(product));
			System.out.println(session.contains(repo));

		    //session. (product);
		    
			System.out.println(session.contains(product));
			System.out.println(session.contains(repo));
			
			Instructor instructor = session.get(Instructor.class, 2);
			
			Course course = new Course("Course1 For 2");
			Course course2 = new Course("Course2 For 2");
			
			course.setInstructor(instructor);
			course2.setInstructor(instructor);

			//Add etmesek de course.setInstructor'dan dolayi create ediyor.
			instructor.addCourse(course);
			instructor.addCourse(course2);
			
			session.save(course);
			session.save(course2);
			
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

}

