package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.mapping.Course;
import com.hibernate.entity.mapping.Instructor;
import com.hibernate.entity.mapping.InstructorDetail;
import com.hibernate.entity.mapping.Review;

public class CourseReviewSaveDemo {
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Review.class)
										.addAnnotatedClass(Course.class)
										.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			
			Course course = new Course("C1");
			
			Review review = new Review("R1");
			Review review2 = new Review("R2");
			Review review3 = new Review("R3");
			
			course.addReview(review);
			course.addReview(review2);
			course.addReview(review3);

			session.save(course);
			
			session.getTransaction().commit();
			
		} finally {
			sessionFactory.close();
		}
	}

}


