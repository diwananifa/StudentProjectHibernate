package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Student;

public class StudentDao {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	static EntityManager em = emf.createEntityManager();
	static EntityTransaction et = em.getTransaction();

	public boolean saveStudent(Student s) {
		try {
			et.begin();
			em.persist(s);
			et.commit();
			return true;	
		}catch(Exception e) {
			et.commit();
		}
		return false;
		}

	public Student getStudentById(int id) {

		Student s = em.find(Student.class, id);

		if (s != null) {
			return s;
		} else {
			return null;
		}
	}
	
	public List<Student> getAllStudent(){
		
		return em.createQuery("select s from Student s").getResultList();
	}
	
	public boolean deleteStudent(int id) {

		Student s = em.find(Student.class, id);

		if (s != null) {	
		et.begin();
		em.remove(s);
		et.commit();
		return true;
	}else {
		return false;
	}
	}
	
	public boolean updateStudent(Student s) {
		et.begin();
		em.merge(s);
		et.commit();
		return true;
	}


	
}
