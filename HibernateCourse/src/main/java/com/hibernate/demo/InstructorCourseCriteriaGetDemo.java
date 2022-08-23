package com.hibernate.demo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.hibernate.entity.mapping.Course;
import com.hibernate.entity.mapping.Instructor;
import com.hibernate.entity.mapping.InstructorDetail;
import com.hibernate.entity.mapping.Review;
import com.hibernate.entity.mapping.Student;

public class InstructorCourseCriteriaGetDemo {
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.addAnnotatedClass(Student.class)
										.addAnnotatedClass(Review.class)
										.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Instructor.class);
			
			Criteria insCriteria = criteria.createCriteria("courseList", JoinType.INNER_JOIN);
			
			insCriteria.add(Restrictions.eq("title", "Course1zz For 2"));
			
			System.out.println(criteria.list());
			
			
		} finally {
			sessionFactory.close();
		}
	}

}

