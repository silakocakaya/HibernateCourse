package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Person;

public class PersonDemo {
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Person.class)
										.buildSessionFactory();
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			Person person = new Person("Sila", "Kocakaya", "a@a.com");
			session.save(person);
			session.getTransaction().commit();
			
			Session session2 = sessionFactory.getCurrentSession();
			session2.beginTransaction();
			System.out.println("Std Id: " + person.getId());
			Person tempperson = session2.get(Person.class, person.getId());
			System.out.println("Std2 Id: " + tempperson.getId());
			session2.getTransaction().commit();
			
			
			Session session3 = sessionFactory.getCurrentSession();
			session3.beginTransaction();
			Person tempperson2 = (Person) session3.createQuery("from person where id = 1").uniqueResult();
			System.out.println("Std2 Id From: " + tempperson2.getId());
			session3.getTransaction().commit();
			
			
			Session session5 = sessionFactory.getCurrentSession();
			session5.beginTransaction();
			Person personUpdate = session5.get(Person.class, 1);
			personUpdate.setFirstName("Koray");
			session5.getTransaction().commit();
			
			Session session6 = sessionFactory.getCurrentSession();
			session6.beginTransaction();
			session6.createQuery("update person set last_name = 'KOCAKAYA'").executeUpdate();
			session6.getTransaction().commit();
			
			Session session4 = sessionFactory.getCurrentSession();
			session4.beginTransaction();
			List<Person> personList = session4.createQuery("from person").list();
			
			for (Person person2 : personList) {
				System.out.println(person2);
			}
			session4.getTransaction().commit();
			
		} finally {
			sessionFactory.close();
		}
	}

}
