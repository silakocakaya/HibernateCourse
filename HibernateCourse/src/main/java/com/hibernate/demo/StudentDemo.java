package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Student;

public class StudentDemo {
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			Student student = new Student("Sila", "Kocakaya", "a@a.com");
			session.save(student);
			session.getTransaction().commit();
			
			Session session2 = sessionFactory.getCurrentSession();
			session2.beginTransaction();
			System.out.println("Std Id: " + student.getId());
			Student tempStudent = session2.get(Student.class, student.getId());
			System.out.println("Std2 Id: " + tempStudent.getId());
			session2.getTransaction().commit();
			
			
			Session session3 = sessionFactory.getCurrentSession();
			session3.beginTransaction();
			Student tempStudent2 = (Student) session3.createQuery("from Student where id = 1").uniqueResult();
			System.out.println("Std2 Id From: " + tempStudent2.getId());
			session3.getTransaction().commit();
			
			
			Session session5 = sessionFactory.getCurrentSession();
			session5.beginTransaction();
			Student studentUpdate = session5.get(Student.class, 1);
			studentUpdate.setFirstName("Koray");
			session5.getTransaction().commit();
			
			Session session6 = sessionFactory.getCurrentSession();
			session6.beginTransaction();
			session6.createQuery("update Student set last_name = 'KOCAKAYA'").executeUpdate();
			session6.getTransaction().commit();
			
			Session session4 = sessionFactory.getCurrentSession();
			session4.beginTransaction();
			List<Student> studentList = session4.createQuery("from Student").list();
			
			for (Student student2 : studentList) {
				System.out.println(student2);
			}
			session4.getTransaction().commit();
			
		} finally {
			sessionFactory.close();
		}
	}

}
